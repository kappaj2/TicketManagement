package za.co.ticket.service.impl;

import za.co.ticket.service.LoanEquipmentService;
import za.co.ticket.domain.LoanEquipment;
import za.co.ticket.repository.LoanEquipmentRepository;
import za.co.ticket.service.dto.LoanEquipmentDTO;
import za.co.ticket.service.mapper.LoanEquipmentMapper;
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
 * Service Implementation for managing LoanEquipment.
 */
@Service
public class LoanEquipmentServiceImpl implements LoanEquipmentService{

    private final Logger log = LoggerFactory.getLogger(LoanEquipmentServiceImpl.class);
    
    @Inject
    private LoanEquipmentRepository loanEquipmentRepository;

    @Inject
    private LoanEquipmentMapper loanEquipmentMapper;

    /**
     * Save a loanEquipment.
     *
     * @param loanEquipmentDTO the entity to save
     * @return the persisted entity
     */
    public LoanEquipmentDTO save(LoanEquipmentDTO loanEquipmentDTO) {
        log.debug("Request to save LoanEquipment : {}", loanEquipmentDTO);
        LoanEquipment loanEquipment = loanEquipmentMapper.loanEquipmentDTOToLoanEquipment(loanEquipmentDTO);
        loanEquipment = loanEquipmentRepository.save(loanEquipment);
        LoanEquipmentDTO result = loanEquipmentMapper.loanEquipmentToLoanEquipmentDTO(loanEquipment);
        return result;
    }

    /**
     *  Get all the loanEquipments.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    public Page<LoanEquipmentDTO> findAll(Pageable pageable) {
        log.debug("Request to get all LoanEquipments");
        Page<LoanEquipment> result = loanEquipmentRepository.findAll(pageable);
        return result.map(loanEquipment -> loanEquipmentMapper.loanEquipmentToLoanEquipmentDTO(loanEquipment));
    }

    /**
     *  Get one loanEquipment by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    public LoanEquipmentDTO findOne(String id) {
        log.debug("Request to get LoanEquipment : {}", id);
        LoanEquipment loanEquipment = loanEquipmentRepository.findOne(id);
        LoanEquipmentDTO loanEquipmentDTO = loanEquipmentMapper.loanEquipmentToLoanEquipmentDTO(loanEquipment);
        return loanEquipmentDTO;
    }

    /**
     *  Delete the  loanEquipment by id.
     *
     *  @param id the id of the entity
     */
    public void delete(String id) {
        log.debug("Request to delete LoanEquipment : {}", id);
        loanEquipmentRepository.delete(id);
    }
}
