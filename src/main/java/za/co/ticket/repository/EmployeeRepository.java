package za.co.ticket.repository;

import za.co.ticket.domain.Employee;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Spring Data MongoDB repository for the Employee entity.
 */
@SuppressWarnings("unused")
public interface EmployeeRepository extends MongoRepository<Employee,String> {

}
