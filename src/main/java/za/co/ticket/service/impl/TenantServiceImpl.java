package za.co.ticket.service.impl;

import za.co.ticket.service.TenantService;
import za.co.ticket.domain.Tenant;
import za.co.ticket.repository.TenantRepository;
import za.co.ticket.service.dto.TenantDTO;
import za.co.ticket.service.mapper.TenantMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Tenant.
 */
@Service
public class TenantServiceImpl implements TenantService{

    private final Logger log = LoggerFactory.getLogger(TenantServiceImpl.class);
    
    @Inject
    private TenantRepository tenantRepository;

    @Inject
    private TenantMapper tenantMapper;

    /**
     * Save a tenant.
     *
     * @param tenantDTO the entity to save
     * @return the persisted entity
     */
    public TenantDTO save(TenantDTO tenantDTO) {
        log.debug("Request to save Tenant : {}", tenantDTO);
        Tenant tenant = tenantMapper.tenantDTOToTenant(tenantDTO);
        tenant = tenantRepository.save(tenant);
        TenantDTO result = tenantMapper.tenantToTenantDTO(tenant);
        return result;
    }

    /**
     *  Get all the tenants.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    public Page<TenantDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Tenants");
        Page<Tenant> result = tenantRepository.findAll(pageable);
        return result.map(tenant -> tenantMapper.tenantToTenantDTO(tenant));
    }

    /**
     *  Get one tenant by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    public TenantDTO findOne(String id) {
        log.debug("Request to get Tenant : {}", id);
        Tenant tenant = tenantRepository.findOne(id);
        TenantDTO tenantDTO = tenantMapper.tenantToTenantDTO(tenant);
        return tenantDTO;
    }

    /**
     *  Delete the  tenant by id.
     *
     *  @param id the id of the entity
     */
    public void delete(String id) {
        log.debug("Request to delete Tenant : {}", id);
        tenantRepository.delete(id);
    }
}
