package za.co.ticket.service.impl;

import za.co.ticket.service.EquipmentOnLoanService;
import za.co.ticket.domain.EquipmentOnLoan;
import za.co.ticket.repository.EquipmentOnLoanRepository;
import za.co.ticket.service.dto.EquipmentOnLoanDTO;
import za.co.ticket.service.mapper.EquipmentOnLoanMapper;
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
 * Service Implementation for managing EquipmentOnLoan.
 */
@Service
public class EquipmentOnLoanServiceImpl implements EquipmentOnLoanService{

    private final Logger log = LoggerFactory.getLogger(EquipmentOnLoanServiceImpl.class);
    
    @Inject
    private EquipmentOnLoanRepository equipmentOnLoanRepository;

    @Inject
    private EquipmentOnLoanMapper equipmentOnLoanMapper;

    /**
     * Save a equipmentOnLoan.
     *
     * @param equipmentOnLoanDTO the entity to save
     * @return the persisted entity
     */
    public EquipmentOnLoanDTO save(EquipmentOnLoanDTO equipmentOnLoanDTO) {
        log.debug("Request to save EquipmentOnLoan : {}", equipmentOnLoanDTO);
        EquipmentOnLoan equipmentOnLoan = equipmentOnLoanMapper.equipmentOnLoanDTOToEquipmentOnLoan(equipmentOnLoanDTO);
        equipmentOnLoan = equipmentOnLoanRepository.save(equipmentOnLoan);
        EquipmentOnLoanDTO result = equipmentOnLoanMapper.equipmentOnLoanToEquipmentOnLoanDTO(equipmentOnLoan);
        return result;
    }

    /**
     *  Get all the equipmentOnLoans.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    public Page<EquipmentOnLoanDTO> findAll(Pageable pageable) {
        log.debug("Request to get all EquipmentOnLoans");
        Page<EquipmentOnLoan> result = equipmentOnLoanRepository.findAll(pageable);
        return result.map(equipmentOnLoan -> equipmentOnLoanMapper.equipmentOnLoanToEquipmentOnLoanDTO(equipmentOnLoan));
    }

    /**
     *  Get one equipmentOnLoan by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    public EquipmentOnLoanDTO findOne(String id) {
        log.debug("Request to get EquipmentOnLoan : {}", id);
        EquipmentOnLoan equipmentOnLoan = equipmentOnLoanRepository.findOne(id);
        EquipmentOnLoanDTO equipmentOnLoanDTO = equipmentOnLoanMapper.equipmentOnLoanToEquipmentOnLoanDTO(equipmentOnLoan);
        return equipmentOnLoanDTO;
    }

    /**
     *  Delete the  equipmentOnLoan by id.
     *
     *  @param id the id of the entity
     */
    public void delete(String id) {
        log.debug("Request to delete EquipmentOnLoan : {}", id);
        equipmentOnLoanRepository.delete(id);
    }
}
