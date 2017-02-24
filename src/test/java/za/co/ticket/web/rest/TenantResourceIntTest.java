package za.co.ticket.web.rest;

import za.co.ticket.TicketManagementApp;

import za.co.ticket.domain.Tenant;
import za.co.ticket.repository.TenantRepository;
import za.co.ticket.service.TenantService;
import za.co.ticket.service.dto.TenantDTO;
import za.co.ticket.service.mapper.TenantMapper;

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
 * Test class for the TenantResource REST controller.
 *
 * @see TenantResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TicketManagementApp.class)
public class TenantResourceIntTest {

    private static final Integer DEFAULT_TENANT_ID = 1;
    private static final Integer UPDATED_TENANT_ID = 2;

    private static final String DEFAULT_TENANT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_TENANT_NAME = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_CREATION_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CREATION_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_DATE_CEASED = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATE_CEASED = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    @Inject
    private TenantRepository tenantRepository;

    @Inject
    private TenantMapper tenantMapper;

    @Inject
    private TenantService tenantService;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restTenantMockMvc;

    private Tenant tenant;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        TenantResource tenantResource = new TenantResource();
        ReflectionTestUtils.setField(tenantResource, "tenantService", tenantService);
        this.restTenantMockMvc = MockMvcBuilders.standaloneSetup(tenantResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Tenant createEntity() {
        Tenant tenant = new Tenant()
                .tenantId(DEFAULT_TENANT_ID)
                .tenantName(DEFAULT_TENANT_NAME)
                .creationDate(DEFAULT_CREATION_DATE)
                .dateCeased(DEFAULT_DATE_CEASED);
        return tenant;
    }

    @Before
    public void initTest() {
        tenantRepository.deleteAll();
        tenant = createEntity();
    }

    @Test
    public void createTenant() throws Exception {
        int databaseSizeBeforeCreate = tenantRepository.findAll().size();

        // Create the Tenant
        TenantDTO tenantDTO = tenantMapper.tenantToTenantDTO(tenant);

        restTenantMockMvc.perform(post("/api/tenants")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tenantDTO)))
            .andExpect(status().isCreated());

        // Validate the Tenant in the database
        List<Tenant> tenantList = tenantRepository.findAll();
        assertThat(tenantList).hasSize(databaseSizeBeforeCreate + 1);
        Tenant testTenant = tenantList.get(tenantList.size() - 1);
        assertThat(testTenant.getTenantId()).isEqualTo(DEFAULT_TENANT_ID);
        assertThat(testTenant.getTenantName()).isEqualTo(DEFAULT_TENANT_NAME);
        assertThat(testTenant.getCreationDate()).isEqualTo(DEFAULT_CREATION_DATE);
        assertThat(testTenant.getDateCeased()).isEqualTo(DEFAULT_DATE_CEASED);
    }

    @Test
    public void createTenantWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tenantRepository.findAll().size();

        // Create the Tenant with an existing ID
        Tenant existingTenant = new Tenant();
        existingTenant.setId("existing_id");
        TenantDTO existingTenantDTO = tenantMapper.tenantToTenantDTO(existingTenant);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTenantMockMvc.perform(post("/api/tenants")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingTenantDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Tenant> tenantList = tenantRepository.findAll();
        assertThat(tenantList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void checkTenantIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = tenantRepository.findAll().size();
        // set the field null
        tenant.setTenantId(null);

        // Create the Tenant, which fails.
        TenantDTO tenantDTO = tenantMapper.tenantToTenantDTO(tenant);

        restTenantMockMvc.perform(post("/api/tenants")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tenantDTO)))
            .andExpect(status().isBadRequest());

        List<Tenant> tenantList = tenantRepository.findAll();
        assertThat(tenantList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkTenantNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = tenantRepository.findAll().size();
        // set the field null
        tenant.setTenantName(null);

        // Create the Tenant, which fails.
        TenantDTO tenantDTO = tenantMapper.tenantToTenantDTO(tenant);

        restTenantMockMvc.perform(post("/api/tenants")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tenantDTO)))
            .andExpect(status().isBadRequest());

        List<Tenant> tenantList = tenantRepository.findAll();
        assertThat(tenantList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkCreationDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = tenantRepository.findAll().size();
        // set the field null
        tenant.setCreationDate(null);

        // Create the Tenant, which fails.
        TenantDTO tenantDTO = tenantMapper.tenantToTenantDTO(tenant);

        restTenantMockMvc.perform(post("/api/tenants")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tenantDTO)))
            .andExpect(status().isBadRequest());

        List<Tenant> tenantList = tenantRepository.findAll();
        assertThat(tenantList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllTenants() throws Exception {
        // Initialize the database
        tenantRepository.save(tenant);

        // Get all the tenantList
        restTenantMockMvc.perform(get("/api/tenants?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tenant.getId())))
            .andExpect(jsonPath("$.[*].tenantId").value(hasItem(DEFAULT_TENANT_ID)))
            .andExpect(jsonPath("$.[*].tenantName").value(hasItem(DEFAULT_TENANT_NAME.toString())))
            .andExpect(jsonPath("$.[*].creationDate").value(hasItem(sameInstant(DEFAULT_CREATION_DATE))))
            .andExpect(jsonPath("$.[*].dateCeased").value(hasItem(sameInstant(DEFAULT_DATE_CEASED))));
    }

    @Test
    public void getTenant() throws Exception {
        // Initialize the database
        tenantRepository.save(tenant);

        // Get the tenant
        restTenantMockMvc.perform(get("/api/tenants/{id}", tenant.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(tenant.getId()))
            .andExpect(jsonPath("$.tenantId").value(DEFAULT_TENANT_ID))
            .andExpect(jsonPath("$.tenantName").value(DEFAULT_TENANT_NAME.toString()))
            .andExpect(jsonPath("$.creationDate").value(sameInstant(DEFAULT_CREATION_DATE)))
            .andExpect(jsonPath("$.dateCeased").value(sameInstant(DEFAULT_DATE_CEASED)));
    }

    @Test
    public void getNonExistingTenant() throws Exception {
        // Get the tenant
        restTenantMockMvc.perform(get("/api/tenants/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateTenant() throws Exception {
        // Initialize the database
        tenantRepository.save(tenant);
        int databaseSizeBeforeUpdate = tenantRepository.findAll().size();

        // Update the tenant
        Tenant updatedTenant = tenantRepository.findOne(tenant.getId());
        updatedTenant
                .tenantId(UPDATED_TENANT_ID)
                .tenantName(UPDATED_TENANT_NAME)
                .creationDate(UPDATED_CREATION_DATE)
                .dateCeased(UPDATED_DATE_CEASED);
        TenantDTO tenantDTO = tenantMapper.tenantToTenantDTO(updatedTenant);

        restTenantMockMvc.perform(put("/api/tenants")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tenantDTO)))
            .andExpect(status().isOk());

        // Validate the Tenant in the database
        List<Tenant> tenantList = tenantRepository.findAll();
        assertThat(tenantList).hasSize(databaseSizeBeforeUpdate);
        Tenant testTenant = tenantList.get(tenantList.size() - 1);
        assertThat(testTenant.getTenantId()).isEqualTo(UPDATED_TENANT_ID);
        assertThat(testTenant.getTenantName()).isEqualTo(UPDATED_TENANT_NAME);
        assertThat(testTenant.getCreationDate()).isEqualTo(UPDATED_CREATION_DATE);
        assertThat(testTenant.getDateCeased()).isEqualTo(UPDATED_DATE_CEASED);
    }

    @Test
    public void updateNonExistingTenant() throws Exception {
        int databaseSizeBeforeUpdate = tenantRepository.findAll().size();

        // Create the Tenant
        TenantDTO tenantDTO = tenantMapper.tenantToTenantDTO(tenant);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restTenantMockMvc.perform(put("/api/tenants")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tenantDTO)))
            .andExpect(status().isCreated());

        // Validate the Tenant in the database
        List<Tenant> tenantList = tenantRepository.findAll();
        assertThat(tenantList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    public void deleteTenant() throws Exception {
        // Initialize the database
        tenantRepository.save(tenant);
        int databaseSizeBeforeDelete = tenantRepository.findAll().size();

        // Get the tenant
        restTenantMockMvc.perform(delete("/api/tenants/{id}", tenant.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Tenant> tenantList = tenantRepository.findAll();
        assertThat(tenantList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
