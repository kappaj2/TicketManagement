package za.co.ticket.web.rest;

import za.co.ticket.TicketManagementApp;

import za.co.ticket.domain.EquipmentOnLoan;
import za.co.ticket.repository.EquipmentOnLoanRepository;
import za.co.ticket.service.EquipmentOnLoanService;
import za.co.ticket.service.dto.EquipmentOnLoanDTO;
import za.co.ticket.service.mapper.EquipmentOnLoanMapper;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.inject.Inject;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.ZoneOffset;
import java.time.ZoneId;
import java.util.List;

import static za.co.ticket.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the EquipmentOnLoanResource REST controller.
 *
 * @see EquipmentOnLoanResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TicketManagementApp.class)
public class EquipmentOnLoanResourceIntTest {

    private static final Integer DEFAULT_EQUIPMENT_ON_LOAN_ENTRY_ID = 1;
    private static final Integer UPDATED_EQUIPMENT_ON_LOAN_ENTRY_ID = 2;

    private static final Integer DEFAULT_TENANT_ID = 1;
    private static final Integer UPDATED_TENANT_ID = 2;

    private static final Integer DEFAULT_EQUIPMENT_ID = 1;
    private static final Integer UPDATED_EQUIPMENT_ID = 2;

    private static final ZonedDateTime DEFAULT_DATE_BOOKED_OUT = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATE_BOOKED_OUT = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_DATE_INSTALLED_AT_TENANT = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATE_INSTALLED_AT_TENANT = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final Integer DEFAULT_BOOK_OUT_TECHNITIAN = 1;
    private static final Integer UPDATED_BOOK_OUT_TECHNITIAN = 2;

    private static final ZonedDateTime DEFAULT_DATE_REMOVED_FROM_TENANT = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATE_REMOVED_FROM_TENANT = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final Integer DEFAULT_INSTALLATION_TECHNITIAN = 1;
    private static final Integer UPDATED_INSTALLATION_TECHNITIAN = 2;

    private static final Integer DEFAULT_REMOVAL_TECHNITIAN = 1;
    private static final Integer UPDATED_REMOVAL_TECHNITIAN = 2;

    private static final ZonedDateTime DEFAULT_DATE_BOOKED_BACK_IN = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATE_BOOKED_BACK_IN = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final Integer DEFAULT_BOOK_IN_TECHNITIAN = 1;
    private static final Integer UPDATED_BOOK_IN_TECHNITIAN = 2;

    @Inject
    private EquipmentOnLoanRepository equipmentOnLoanRepository;

    @Inject
    private EquipmentOnLoanMapper equipmentOnLoanMapper;

    @Inject
    private EquipmentOnLoanService equipmentOnLoanService;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restEquipmentOnLoanMockMvc;

    private EquipmentOnLoan equipmentOnLoan;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        EquipmentOnLoanResource equipmentOnLoanResource = new EquipmentOnLoanResource();
        ReflectionTestUtils.setField(equipmentOnLoanResource, "equipmentOnLoanService", equipmentOnLoanService);
        this.restEquipmentOnLoanMockMvc = MockMvcBuilders.standaloneSetup(equipmentOnLoanResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EquipmentOnLoan createEntity() {
        EquipmentOnLoan equipmentOnLoan = new EquipmentOnLoan()
                .equipmentOnLoanEntryId(DEFAULT_EQUIPMENT_ON_LOAN_ENTRY_ID)
                .tenantId(DEFAULT_TENANT_ID)
                .equipmentId(DEFAULT_EQUIPMENT_ID)
                .dateBookedOut(DEFAULT_DATE_BOOKED_OUT)
                .dateInstalledAtTenant(DEFAULT_DATE_INSTALLED_AT_TENANT)
                .bookOutTechnitian(DEFAULT_BOOK_OUT_TECHNITIAN)
                .dateRemovedFromTenant(DEFAULT_DATE_REMOVED_FROM_TENANT)
                .installationTechnitian(DEFAULT_INSTALLATION_TECHNITIAN)
                .removalTechnitian(DEFAULT_REMOVAL_TECHNITIAN)
                .dateBookedBackIn(DEFAULT_DATE_BOOKED_BACK_IN)
                .bookInTechnitian(DEFAULT_BOOK_IN_TECHNITIAN);
        return equipmentOnLoan;
    }

    @Before
    public void initTest() {
        equipmentOnLoanRepository.deleteAll();
        equipmentOnLoan = createEntity();
    }

    @Test
    public void createEquipmentOnLoan() throws Exception {
        int databaseSizeBeforeCreate = equipmentOnLoanRepository.findAll().size();

        // Create the EquipmentOnLoan
        EquipmentOnLoanDTO equipmentOnLoanDTO = equipmentOnLoanMapper.equipmentOnLoanToEquipmentOnLoanDTO(equipmentOnLoan);

        restEquipmentOnLoanMockMvc.perform(post("/api/equipment-on-loans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(equipmentOnLoanDTO)))
            .andExpect(status().isCreated());

        // Validate the EquipmentOnLoan in the database
        List<EquipmentOnLoan> equipmentOnLoanList = equipmentOnLoanRepository.findAll();
        assertThat(equipmentOnLoanList).hasSize(databaseSizeBeforeCreate + 1);
        EquipmentOnLoan testEquipmentOnLoan = equipmentOnLoanList.get(equipmentOnLoanList.size() - 1);
        assertThat(testEquipmentOnLoan.getEquipmentOnLoanEntryId()).isEqualTo(DEFAULT_EQUIPMENT_ON_LOAN_ENTRY_ID);
        assertThat(testEquipmentOnLoan.getTenantId()).isEqualTo(DEFAULT_TENANT_ID);
        assertThat(testEquipmentOnLoan.getEquipmentId()).isEqualTo(DEFAULT_EQUIPMENT_ID);
        assertThat(testEquipmentOnLoan.getDateBookedOut()).isEqualTo(DEFAULT_DATE_BOOKED_OUT);
        assertThat(testEquipmentOnLoan.getDateInstalledAtTenant()).isEqualTo(DEFAULT_DATE_INSTALLED_AT_TENANT);
        assertThat(testEquipmentOnLoan.getBookOutTechnitian()).isEqualTo(DEFAULT_BOOK_OUT_TECHNITIAN);
        assertThat(testEquipmentOnLoan.getDateRemovedFromTenant()).isEqualTo(DEFAULT_DATE_REMOVED_FROM_TENANT);
        assertThat(testEquipmentOnLoan.getInstallationTechnitian()).isEqualTo(DEFAULT_INSTALLATION_TECHNITIAN);
        assertThat(testEquipmentOnLoan.getRemovalTechnitian()).isEqualTo(DEFAULT_REMOVAL_TECHNITIAN);
        assertThat(testEquipmentOnLoan.getDateBookedBackIn()).isEqualTo(DEFAULT_DATE_BOOKED_BACK_IN);
        assertThat(testEquipmentOnLoan.getBookInTechnitian()).isEqualTo(DEFAULT_BOOK_IN_TECHNITIAN);
    }

    @Test
    public void createEquipmentOnLoanWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = equipmentOnLoanRepository.findAll().size();

        // Create the EquipmentOnLoan with an existing ID
        EquipmentOnLoan existingEquipmentOnLoan = new EquipmentOnLoan();
        existingEquipmentOnLoan.setId("existing_id");
        EquipmentOnLoanDTO existingEquipmentOnLoanDTO = equipmentOnLoanMapper.equipmentOnLoanToEquipmentOnLoanDTO(existingEquipmentOnLoan);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEquipmentOnLoanMockMvc.perform(post("/api/equipment-on-loans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingEquipmentOnLoanDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<EquipmentOnLoan> equipmentOnLoanList = equipmentOnLoanRepository.findAll();
        assertThat(equipmentOnLoanList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void checkEquipmentOnLoanEntryIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = equipmentOnLoanRepository.findAll().size();
        // set the field null
        equipmentOnLoan.setEquipmentOnLoanEntryId(null);

        // Create the EquipmentOnLoan, which fails.
        EquipmentOnLoanDTO equipmentOnLoanDTO = equipmentOnLoanMapper.equipmentOnLoanToEquipmentOnLoanDTO(equipmentOnLoan);

        restEquipmentOnLoanMockMvc.perform(post("/api/equipment-on-loans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(equipmentOnLoanDTO)))
            .andExpect(status().isBadRequest());

        List<EquipmentOnLoan> equipmentOnLoanList = equipmentOnLoanRepository.findAll();
        assertThat(equipmentOnLoanList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkTenantIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = equipmentOnLoanRepository.findAll().size();
        // set the field null
        equipmentOnLoan.setTenantId(null);

        // Create the EquipmentOnLoan, which fails.
        EquipmentOnLoanDTO equipmentOnLoanDTO = equipmentOnLoanMapper.equipmentOnLoanToEquipmentOnLoanDTO(equipmentOnLoan);

        restEquipmentOnLoanMockMvc.perform(post("/api/equipment-on-loans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(equipmentOnLoanDTO)))
            .andExpect(status().isBadRequest());

        List<EquipmentOnLoan> equipmentOnLoanList = equipmentOnLoanRepository.findAll();
        assertThat(equipmentOnLoanList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkEquipmentIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = equipmentOnLoanRepository.findAll().size();
        // set the field null
        equipmentOnLoan.setEquipmentId(null);

        // Create the EquipmentOnLoan, which fails.
        EquipmentOnLoanDTO equipmentOnLoanDTO = equipmentOnLoanMapper.equipmentOnLoanToEquipmentOnLoanDTO(equipmentOnLoan);

        restEquipmentOnLoanMockMvc.perform(post("/api/equipment-on-loans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(equipmentOnLoanDTO)))
            .andExpect(status().isBadRequest());

        List<EquipmentOnLoan> equipmentOnLoanList = equipmentOnLoanRepository.findAll();
        assertThat(equipmentOnLoanList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkDateBookedOutIsRequired() throws Exception {
        int databaseSizeBeforeTest = equipmentOnLoanRepository.findAll().size();
        // set the field null
        equipmentOnLoan.setDateBookedOut(null);

        // Create the EquipmentOnLoan, which fails.
        EquipmentOnLoanDTO equipmentOnLoanDTO = equipmentOnLoanMapper.equipmentOnLoanToEquipmentOnLoanDTO(equipmentOnLoan);

        restEquipmentOnLoanMockMvc.perform(post("/api/equipment-on-loans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(equipmentOnLoanDTO)))
            .andExpect(status().isBadRequest());

        List<EquipmentOnLoan> equipmentOnLoanList = equipmentOnLoanRepository.findAll();
        assertThat(equipmentOnLoanList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkBookOutTechnitianIsRequired() throws Exception {
        int databaseSizeBeforeTest = equipmentOnLoanRepository.findAll().size();
        // set the field null
        equipmentOnLoan.setBookOutTechnitian(null);

        // Create the EquipmentOnLoan, which fails.
        EquipmentOnLoanDTO equipmentOnLoanDTO = equipmentOnLoanMapper.equipmentOnLoanToEquipmentOnLoanDTO(equipmentOnLoan);

        restEquipmentOnLoanMockMvc.perform(post("/api/equipment-on-loans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(equipmentOnLoanDTO)))
            .andExpect(status().isBadRequest());

        List<EquipmentOnLoan> equipmentOnLoanList = equipmentOnLoanRepository.findAll();
        assertThat(equipmentOnLoanList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllEquipmentOnLoans() throws Exception {
        // Initialize the database
        equipmentOnLoanRepository.save(equipmentOnLoan);

        // Get all the equipmentOnLoanList
        restEquipmentOnLoanMockMvc.perform(get("/api/equipment-on-loans?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(equipmentOnLoan.getId())))
            .andExpect(jsonPath("$.[*].equipmentOnLoanEntryId").value(hasItem(DEFAULT_EQUIPMENT_ON_LOAN_ENTRY_ID)))
            .andExpect(jsonPath("$.[*].tenantId").value(hasItem(DEFAULT_TENANT_ID)))
            .andExpect(jsonPath("$.[*].equipmentId").value(hasItem(DEFAULT_EQUIPMENT_ID)))
            .andExpect(jsonPath("$.[*].dateBookedOut").value(hasItem(sameInstant(DEFAULT_DATE_BOOKED_OUT))))
            .andExpect(jsonPath("$.[*].dateInstalledAtTenant").value(hasItem(sameInstant(DEFAULT_DATE_INSTALLED_AT_TENANT))))
            .andExpect(jsonPath("$.[*].bookOutTechnitian").value(hasItem(DEFAULT_BOOK_OUT_TECHNITIAN)))
            .andExpect(jsonPath("$.[*].dateRemovedFromTenant").value(hasItem(sameInstant(DEFAULT_DATE_REMOVED_FROM_TENANT))))
            .andExpect(jsonPath("$.[*].installationTechnitian").value(hasItem(DEFAULT_INSTALLATION_TECHNITIAN)))
            .andExpect(jsonPath("$.[*].removalTechnitian").value(hasItem(DEFAULT_REMOVAL_TECHNITIAN)))
            .andExpect(jsonPath("$.[*].dateBookedBackIn").value(hasItem(sameInstant(DEFAULT_DATE_BOOKED_BACK_IN))))
            .andExpect(jsonPath("$.[*].bookInTechnitian").value(hasItem(DEFAULT_BOOK_IN_TECHNITIAN)));
    }

    @Test
    public void getEquipmentOnLoan() throws Exception {
        // Initialize the database
        equipmentOnLoanRepository.save(equipmentOnLoan);

        // Get the equipmentOnLoan
        restEquipmentOnLoanMockMvc.perform(get("/api/equipment-on-loans/{id}", equipmentOnLoan.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(equipmentOnLoan.getId()))
            .andExpect(jsonPath("$.equipmentOnLoanEntryId").value(DEFAULT_EQUIPMENT_ON_LOAN_ENTRY_ID))
            .andExpect(jsonPath("$.tenantId").value(DEFAULT_TENANT_ID))
            .andExpect(jsonPath("$.equipmentId").value(DEFAULT_EQUIPMENT_ID))
            .andExpect(jsonPath("$.dateBookedOut").value(sameInstant(DEFAULT_DATE_BOOKED_OUT)))
            .andExpect(jsonPath("$.dateInstalledAtTenant").value(sameInstant(DEFAULT_DATE_INSTALLED_AT_TENANT)))
            .andExpect(jsonPath("$.bookOutTechnitian").value(DEFAULT_BOOK_OUT_TECHNITIAN))
            .andExpect(jsonPath("$.dateRemovedFromTenant").value(sameInstant(DEFAULT_DATE_REMOVED_FROM_TENANT)))
            .andExpect(jsonPath("$.installationTechnitian").value(DEFAULT_INSTALLATION_TECHNITIAN))
            .andExpect(jsonPath("$.removalTechnitian").value(DEFAULT_REMOVAL_TECHNITIAN))
            .andExpect(jsonPath("$.dateBookedBackIn").value(sameInstant(DEFAULT_DATE_BOOKED_BACK_IN)))
            .andExpect(jsonPath("$.bookInTechnitian").value(DEFAULT_BOOK_IN_TECHNITIAN));
    }

    @Test
    public void getNonExistingEquipmentOnLoan() throws Exception {
        // Get the equipmentOnLoan
        restEquipmentOnLoanMockMvc.perform(get("/api/equipment-on-loans/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateEquipmentOnLoan() throws Exception {
        // Initialize the database
        equipmentOnLoanRepository.save(equipmentOnLoan);
        int databaseSizeBeforeUpdate = equipmentOnLoanRepository.findAll().size();

        // Update the equipmentOnLoan
        EquipmentOnLoan updatedEquipmentOnLoan = equipmentOnLoanRepository.findOne(equipmentOnLoan.getId());
        updatedEquipmentOnLoan
                .equipmentOnLoanEntryId(UPDATED_EQUIPMENT_ON_LOAN_ENTRY_ID)
                .tenantId(UPDATED_TENANT_ID)
                .equipmentId(UPDATED_EQUIPMENT_ID)
                .dateBookedOut(UPDATED_DATE_BOOKED_OUT)
                .dateInstalledAtTenant(UPDATED_DATE_INSTALLED_AT_TENANT)
                .bookOutTechnitian(UPDATED_BOOK_OUT_TECHNITIAN)
                .dateRemovedFromTenant(UPDATED_DATE_REMOVED_FROM_TENANT)
                .installationTechnitian(UPDATED_INSTALLATION_TECHNITIAN)
                .removalTechnitian(UPDATED_REMOVAL_TECHNITIAN)
                .dateBookedBackIn(UPDATED_DATE_BOOKED_BACK_IN)
                .bookInTechnitian(UPDATED_BOOK_IN_TECHNITIAN);
        EquipmentOnLoanDTO equipmentOnLoanDTO = equipmentOnLoanMapper.equipmentOnLoanToEquipmentOnLoanDTO(updatedEquipmentOnLoan);

        restEquipmentOnLoanMockMvc.perform(put("/api/equipment-on-loans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(equipmentOnLoanDTO)))
            .andExpect(status().isOk());

        // Validate the EquipmentOnLoan in the database
        List<EquipmentOnLoan> equipmentOnLoanList = equipmentOnLoanRepository.findAll();
        assertThat(equipmentOnLoanList).hasSize(databaseSizeBeforeUpdate);
        EquipmentOnLoan testEquipmentOnLoan = equipmentOnLoanList.get(equipmentOnLoanList.size() - 1);
        assertThat(testEquipmentOnLoan.getEquipmentOnLoanEntryId()).isEqualTo(UPDATED_EQUIPMENT_ON_LOAN_ENTRY_ID);
        assertThat(testEquipmentOnLoan.getTenantId()).isEqualTo(UPDATED_TENANT_ID);
        assertThat(testEquipmentOnLoan.getEquipmentId()).isEqualTo(UPDATED_EQUIPMENT_ID);
        assertThat(testEquipmentOnLoan.getDateBookedOut()).isEqualTo(UPDATED_DATE_BOOKED_OUT);
        assertThat(testEquipmentOnLoan.getDateInstalledAtTenant()).isEqualTo(UPDATED_DATE_INSTALLED_AT_TENANT);
        assertThat(testEquipmentOnLoan.getBookOutTechnitian()).isEqualTo(UPDATED_BOOK_OUT_TECHNITIAN);
        assertThat(testEquipmentOnLoan.getDateRemovedFromTenant()).isEqualTo(UPDATED_DATE_REMOVED_FROM_TENANT);
        assertThat(testEquipmentOnLoan.getInstallationTechnitian()).isEqualTo(UPDATED_INSTALLATION_TECHNITIAN);
        assertThat(testEquipmentOnLoan.getRemovalTechnitian()).isEqualTo(UPDATED_REMOVAL_TECHNITIAN);
        assertThat(testEquipmentOnLoan.getDateBookedBackIn()).isEqualTo(UPDATED_DATE_BOOKED_BACK_IN);
        assertThat(testEquipmentOnLoan.getBookInTechnitian()).isEqualTo(UPDATED_BOOK_IN_TECHNITIAN);
    }

    @Test
    public void updateNonExistingEquipmentOnLoan() throws Exception {
        int databaseSizeBeforeUpdate = equipmentOnLoanRepository.findAll().size();

        // Create the EquipmentOnLoan
        EquipmentOnLoanDTO equipmentOnLoanDTO = equipmentOnLoanMapper.equipmentOnLoanToEquipmentOnLoanDTO(equipmentOnLoan);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restEquipmentOnLoanMockMvc.perform(put("/api/equipment-on-loans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(equipmentOnLoanDTO)))
            .andExpect(status().isCreated());

        // Validate the EquipmentOnLoan in the database
        List<EquipmentOnLoan> equipmentOnLoanList = equipmentOnLoanRepository.findAll();
        assertThat(equipmentOnLoanList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    public void deleteEquipmentOnLoan() throws Exception {
        // Initialize the database
        equipmentOnLoanRepository.save(equipmentOnLoan);
        int databaseSizeBeforeDelete = equipmentOnLoanRepository.findAll().size();

        // Get the equipmentOnLoan
        restEquipmentOnLoanMockMvc.perform(delete("/api/equipment-on-loans/{id}", equipmentOnLoan.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<EquipmentOnLoan> equipmentOnLoanList = equipmentOnLoanRepository.findAll();
        assertThat(equipmentOnLoanList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
