package za.co.ticket.repository;

import za.co.ticket.domain.LoanEquipment;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Spring Data MongoDB repository for the LoanEquipment entity.
 */
@SuppressWarnings("unused")
public interface LoanEquipmentRepository extends MongoRepository<LoanEquipment,String> {

}
