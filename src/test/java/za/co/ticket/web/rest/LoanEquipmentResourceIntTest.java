package za.co.ticket.web.rest;

import za.co.ticket.TicketManagementApp;

import za.co.ticket.domain.LoanEquipment;
import za.co.ticket.repository.LoanEquipmentRepository;
import za.co.ticket.service.LoanEquipmentService;
import za.co.ticket.service.dto.LoanEquipmentDTO;
import za.co.ticket.service.mapper.LoanEquipmentMapper;

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
 * Test class for the LoanEquipmentResource REST controller.
 *
 * @see LoanEquipmentResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TicketManagementApp.class)
public class LoanEquipmentResourceIntTest {

    private static final Integer DEFAULT_EQUIPMENT_ID = 1;
    private static final Integer UPDATED_EQUIPMENT_ID = 2;

    private static final String DEFAULT_EQUIPMENT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_EQUIPMENT_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_EQUIPMENT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_EQUIPMENT_DESCRIPTION = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_INITIAL_UPLOAD_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_INITIAL_UPLOAD_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final Integer DEFAULT_UPLOADED_BY = 1;
    private static final Integer UPDATED_UPLOADED_BY = 2;

    private static final Boolean DEFAULT_EQUIPMENT_ACTIVE = false;
    private static final Boolean UPDATED_EQUIPMENT_ACTIVE = true;

    @Inject
    private LoanEquipmentRepository loanEquipmentRepository;

    @Inject
    private LoanEquipmentMapper loanEquipmentMapper;

    @Inject
    private LoanEquipmentService loanEquipmentService;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restLoanEquipmentMockMvc;

    private LoanEquipment loanEquipment;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        LoanEquipmentResource loanEquipmentResource = new LoanEquipmentResource();
        ReflectionTestUtils.setField(loanEquipmentResource, "loanEquipmentService", loanEquipmentService);
        this.restLoanEquipmentMockMvc = MockMvcBuilders.standaloneSetup(loanEquipmentResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static LoanEquipment createEntity() {
        LoanEquipment loanEquipment = new LoanEquipment()
                .equipmentId(DEFAULT_EQUIPMENT_ID)
                .equipmentName(DEFAULT_EQUIPMENT_NAME)
                .equipmentDescription(DEFAULT_EQUIPMENT_DESCRIPTION)
                .initialUploadDate(DEFAULT_INITIAL_UPLOAD_DATE)
                .uploadedBy(DEFAULT_UPLOADED_BY)
                .equipmentActive(DEFAULT_EQUIPMENT_ACTIVE);
        return loanEquipment;
    }

    @Before
    public void initTest() {
        loanEquipmentRepository.deleteAll();
        loanEquipment = createEntity();
    }

    @Test
    public void createLoanEquipment() throws Exception {
        int databaseSizeBeforeCreate = loanEquipmentRepository.findAll().size();

        // Create the LoanEquipment
        LoanEquipmentDTO loanEquipmentDTO = loanEquipmentMapper.loanEquipmentToLoanEquipmentDTO(loanEquipment);

        restLoanEquipmentMockMvc.perform(post("/api/loan-equipments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(loanEquipmentDTO)))
            .andExpect(status().isCreated());

        // Validate the LoanEquipment in the database
        List<LoanEquipment> loanEquipmentList = loanEquipmentRepository.findAll();
        assertThat(loanEquipmentList).hasSize(databaseSizeBeforeCreate + 1);
        LoanEquipment testLoanEquipment = loanEquipmentList.get(loanEquipmentList.size() - 1);
        assertThat(testLoanEquipment.getEquipmentId()).isEqualTo(DEFAULT_EQUIPMENT_ID);
        assertThat(testLoanEquipment.getEquipmentName()).isEqualTo(DEFAULT_EQUIPMENT_NAME);
        assertThat(testLoanEquipment.getEquipmentDescription()).isEqualTo(DEFAULT_EQUIPMENT_DESCRIPTION);
        assertThat(testLoanEquipment.getInitialUploadDate()).isEqualTo(DEFAULT_INITIAL_UPLOAD_DATE);
        assertThat(testLoanEquipment.getUploadedBy()).isEqualTo(DEFAULT_UPLOADED_BY);
        assertThat(testLoanEquipment.isEquipmentActive()).isEqualTo(DEFAULT_EQUIPMENT_ACTIVE);
    }

    @Test
    public void createLoanEquipmentWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = loanEquipmentRepository.findAll().size();

        // Create the LoanEquipment with an existing ID
        LoanEquipment existingLoanEquipment = new LoanEquipment();
        existingLoanEquipment.setId("existing_id");
        LoanEquipmentDTO existingLoanEquipmentDTO = loanEquipmentMapper.loanEquipmentToLoanEquipmentDTO(existingLoanEquipment);

        // An entity with an existing ID cannot be created, so this API call must fail
        restLoanEquipmentMockMvc.perform(post("/api/loan-equipments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingLoanEquipmentDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<LoanEquipment> loanEquipmentList = loanEquipmentRepository.findAll();
        assertThat(loanEquipmentList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void checkEquipmentIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = loanEquipmentRepository.findAll().size();
        // set the field null
        loanEquipment.setEquipmentId(null);

        // Create the LoanEquipment, which fails.
        LoanEquipmentDTO loanEquipmentDTO = loanEquipmentMapper.loanEquipmentToLoanEquipmentDTO(loanEquipment);

        restLoanEquipmentMockMvc.perform(post("/api/loan-equipments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(loanEquipmentDTO)))
            .andExpect(status().isBadRequest());

        List<LoanEquipment> loanEquipmentList = loanEquipmentRepository.findAll();
        assertThat(loanEquipmentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkEquipmentNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = loanEquipmentRepository.findAll().size();
        // set the field null
        loanEquipment.setEquipmentName(null);

        // Create the LoanEquipment, which fails.
        LoanEquipmentDTO loanEquipmentDTO = loanEquipmentMapper.loanEquipmentToLoanEquipmentDTO(loanEquipment);

        restLoanEquipmentMockMvc.perform(post("/api/loan-equipments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(loanEquipmentDTO)))
            .andExpect(status().isBadRequest());

        List<LoanEquipment> loanEquipmentList = loanEquipmentRepository.findAll();
        assertThat(loanEquipmentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkEquipmentDescriptionIsRequired() throws Exception {
        int databaseSizeBeforeTest = loanEquipmentRepository.findAll().size();
        // set the field null
        loanEquipment.setEquipmentDescription(null);

        // Create the LoanEquipment, which fails.
        LoanEquipmentDTO loanEquipmentDTO = loanEquipmentMapper.loanEquipmentToLoanEquipmentDTO(loanEquipment);

        restLoanEquipmentMockMvc.perform(post("/api/loan-equipments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(loanEquipmentDTO)))
            .andExpect(status().isBadRequest());

        List<LoanEquipment> loanEquipmentList = loanEquipmentRepository.findAll();
        assertThat(loanEquipmentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkInitialUploadDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = loanEquipmentRepository.findAll().size();
        // set the field null
        loanEquipment.setInitialUploadDate(null);

        // Create the LoanEquipment, which fails.
        LoanEquipmentDTO loanEquipmentDTO = loanEquipmentMapper.loanEquipmentToLoanEquipmentDTO(loanEquipment);

        restLoanEquipmentMockMvc.perform(post("/api/loan-equipments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(loanEquipmentDTO)))
            .andExpect(status().isBadRequest());

        List<LoanEquipment> loanEquipmentList = loanEquipmentRepository.findAll();
        assertThat(loanEquipmentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkUploadedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = loanEquipmentRepository.findAll().size();
        // set the field null
        loanEquipment.setUploadedBy(null);

        // Create the LoanEquipment, which fails.
        LoanEquipmentDTO loanEquipmentDTO = loanEquipmentMapper.loanEquipmentToLoanEquipmentDTO(loanEquipment);

        restLoanEquipmentMockMvc.perform(post("/api/loan-equipments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(loanEquipmentDTO)))
            .andExpect(status().isBadRequest());

        List<LoanEquipment> loanEquipmentList = loanEquipmentRepository.findAll();
        assertThat(loanEquipmentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkEquipmentActiveIsRequired() throws Exception {
        int databaseSizeBeforeTest = loanEquipmentRepository.findAll().size();
        // set the field null
        loanEquipment.setEquipmentActive(null);

        // Create the LoanEquipment, which fails.
        LoanEquipmentDTO loanEquipmentDTO = loanEquipmentMapper.loanEquipmentToLoanEquipmentDTO(loanEquipment);

        restLoanEquipmentMockMvc.perform(post("/api/loan-equipments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(loanEquipmentDTO)))
            .andExpect(status().isBadRequest());

        List<LoanEquipment> loanEquipmentList = loanEquipmentRepository.findAll();
        assertThat(loanEquipmentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllLoanEquipments() throws Exception {
        // Initialize the database
        loanEquipmentRepository.save(loanEquipment);

        // Get all the loanEquipmentList
        restLoanEquipmentMockMvc.perform(get("/api/loan-equipments?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(loanEquipment.getId())))
            .andExpect(jsonPath("$.[*].equipmentId").value(hasItem(DEFAULT_EQUIPMENT_ID)))
            .andExpect(jsonPath("$.[*].equipmentName").value(hasItem(DEFAULT_EQUIPMENT_NAME.toString())))
            .andExpect(jsonPath("$.[*].equipmentDescription").value(hasItem(DEFAULT_EQUIPMENT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].initialUploadDate").value(hasItem(sameInstant(DEFAULT_INITIAL_UPLOAD_DATE))))
            .andExpect(jsonPath("$.[*].uploadedBy").value(hasItem(DEFAULT_UPLOADED_BY)))
            .andExpect(jsonPath("$.[*].equipmentActive").value(hasItem(DEFAULT_EQUIPMENT_ACTIVE.booleanValue())));
    }

    @Test
    public void getLoanEquipment() throws Exception {
        // Initialize the database
        loanEquipmentRepository.save(loanEquipment);

        // Get the loanEquipment
        restLoanEquipmentMockMvc.perform(get("/api/loan-equipments/{id}", loanEquipment.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(loanEquipment.getId()))
            .andExpect(jsonPath("$.equipmentId").value(DEFAULT_EQUIPMENT_ID))
            .andExpect(jsonPath("$.equipmentName").value(DEFAULT_EQUIPMENT_NAME.toString()))
            .andExpect(jsonPath("$.equipmentDescription").value(DEFAULT_EQUIPMENT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.initialUploadDate").value(sameInstant(DEFAULT_INITIAL_UPLOAD_DATE)))
            .andExpect(jsonPath("$.uploadedBy").value(DEFAULT_UPLOADED_BY))
            .andExpect(jsonPath("$.equipmentActive").value(DEFAULT_EQUIPMENT_ACTIVE.booleanValue()));
    }

    @Test
    public void getNonExistingLoanEquipment() throws Exception {
        // Get the loanEquipment
        restLoanEquipmentMockMvc.perform(get("/api/loan-equipments/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateLoanEquipment() throws Exception {
        // Initialize the database
        loanEquipmentRepository.save(loanEquipment);
        int databaseSizeBeforeUpdate = loanEquipmentRepository.findAll().size();

        // Update the loanEquipment
        LoanEquipment updatedLoanEquipment = loanEquipmentRepository.findOne(loanEquipment.getId());
        updatedLoanEquipment
                .equipmentId(UPDATED_EQUIPMENT_ID)
                .equipmentName(UPDATED_EQUIPMENT_NAME)
                .equipmentDescription(UPDATED_EQUIPMENT_DESCRIPTION)
                .initialUploadDate(UPDATED_INITIAL_UPLOAD_DATE)
                .uploadedBy(UPDATED_UPLOADED_BY)
                .equipmentActive(UPDATED_EQUIPMENT_ACTIVE);
        LoanEquipmentDTO loanEquipmentDTO = loanEquipmentMapper.loanEquipmentToLoanEquipmentDTO(updatedLoanEquipment);

        restLoanEquipmentMockMvc.perform(put("/api/loan-equipments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(loanEquipmentDTO)))
            .andExpect(status().isOk());

        // Validate the LoanEquipment in the database
        List<LoanEquipment> loanEquipmentList = loanEquipmentRepository.findAll();
        assertThat(loanEquipmentList).hasSize(databaseSizeBeforeUpdate);
        LoanEquipment testLoanEquipment = loanEquipmentList.get(loanEquipmentList.size() - 1);
        assertThat(testLoanEquipment.getEquipmentId()).isEqualTo(UPDATED_EQUIPMENT_ID);
        assertThat(testLoanEquipment.getEquipmentName()).isEqualTo(UPDATED_EQUIPMENT_NAME);
        assertThat(testLoanEquipment.getEquipmentDescription()).isEqualTo(UPDATED_EQUIPMENT_DESCRIPTION);
        assertThat(testLoanEquipment.getInitialUploadDate()).isEqualTo(UPDATED_INITIAL_UPLOAD_DATE);
        assertThat(testLoanEquipment.getUploadedBy()).isEqualTo(UPDATED_UPLOADED_BY);
        assertThat(testLoanEquipment.isEquipmentActive()).isEqualTo(UPDATED_EQUIPMENT_ACTIVE);
    }

    @Test
    public void updateNonExistingLoanEquipment() throws Exception {
        int databaseSizeBeforeUpdate = loanEquipmentRepository.findAll().size();

        // Create the LoanEquipment
        LoanEquipmentDTO loanEquipmentDTO = loanEquipmentMapper.loanEquipmentToLoanEquipmentDTO(loanEquipment);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restLoanEquipmentMockMvc.perform(put("/api/loan-equipments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(loanEquipmentDTO)))
            .andExpect(status().isCreated());

        // Validate the LoanEquipment in the database
        List<LoanEquipment> loanEquipmentList = loanEquipmentRepository.findAll();
        assertThat(loanEquipmentList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    public void deleteLoanEquipment() throws Exception {
        // Initialize the database
        loanEquipmentRepository.save(loanEquipment);
        int databaseSizeBeforeDelete = loanEquipmentRepository.findAll().size();

        // Get the loanEquipment
        restLoanEquipmentMockMvc.perform(delete("/api/loan-equipments/{id}", loanEquipment.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<LoanEquipment> loanEquipmentList = loanEquipmentRepository.findAll();
        assertThat(loanEquipmentList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
