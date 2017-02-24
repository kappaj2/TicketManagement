package za.co.ticket.service;

import za.co.ticket.service.dto.LoanEquipmentDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * Service Interface for managing LoanEquipment.
 */
public interface LoanEquipmentService {

    /**
     * Save a loanEquipment.
     *
     * @param loanEquipmentDTO the entity to save
     * @return the persisted entity
     */
    LoanEquipmentDTO save(LoanEquipmentDTO loanEquipmentDTO);

    /**
     *  Get all the loanEquipments.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<LoanEquipmentDTO> findAll(Pageable pageable);

    /**
     *  Get the "id" loanEquipment.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    LoanEquipmentDTO findOne(String id);

    /**
     *  Delete the "id" loanEquipment.
     *
     *  @param id the id of the entity
     */
    void delete(String id);
}
