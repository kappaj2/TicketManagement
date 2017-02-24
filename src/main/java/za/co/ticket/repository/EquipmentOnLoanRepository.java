package za.co.ticket.repository;

import za.co.ticket.domain.EquipmentOnLoan;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Spring Data MongoDB repository for the EquipmentOnLoan entity.
 */
@SuppressWarnings("unused")
public interface EquipmentOnLoanRepository extends MongoRepository<EquipmentOnLoan,String> {

}
