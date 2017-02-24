package za.co.ticket.service.mapper;

import za.co.ticket.domain.*;
import za.co.ticket.service.dto.TenantDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Tenant and its DTO TenantDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TenantMapper {

    TenantDTO tenantToTenantDTO(Tenant tenant);

    List<TenantDTO> tenantsToTenantDTOs(List<Tenant> tenants);

    Tenant tenantDTOToTenant(TenantDTO tenantDTO);

    List<Tenant> tenantDTOsToTenants(List<TenantDTO> tenantDTOs);
}
