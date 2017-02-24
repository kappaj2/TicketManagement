package za.co.ticket.repository;

import za.co.ticket.domain.Tenant;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Spring Data MongoDB repository for the Tenant entity.
 */
@SuppressWarnings("unused")
public interface TenantRepository extends MongoRepository<Tenant,String> {

}
