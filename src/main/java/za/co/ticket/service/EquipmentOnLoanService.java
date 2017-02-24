package za.co.ticket.service;

import za.co.ticket.service.dto.EquipmentOnLoanDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * Service Interface for managing EquipmentOnLoan.
 */
public interface EquipmentOnLoanService {

    /**
     * Save a equipmentOnLoan.
     *
     * @param equipmentOnLoanDTO the entity to save
     * @return the persisted entity
     */
    EquipmentOnLoanDTO save(EquipmentOnLoanDTO equipmentOnLoanDTO);

    /**
     *  Get all the equipmentOnLoans.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<EquipmentOnLoanDTO> findAll(Pageable pageable);

    /**
     *  Get the "id" equipmentOnLoan.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    EquipmentOnLoanDTO findOne(String id);

    /**
     *  Delete the "id" equipmentOnLoan.
     *
     *  @param id the id of the entity
     */
    void delete(String id);
}
