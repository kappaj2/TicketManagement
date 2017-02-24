package za.co.ticket.service.mapper;

import za.co.ticket.domain.*;
import za.co.ticket.service.dto.LoanEquipmentDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity LoanEquipment and its DTO LoanEquipmentDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface LoanEquipmentMapper {

    LoanEquipmentDTO loanEquipmentToLoanEquipmentDTO(LoanEquipment loanEquipment);

    List<LoanEquipmentDTO> loanEquipmentsToLoanEquipmentDTOs(List<LoanEquipment> loanEquipments);

    LoanEquipment loanEquipmentDTOToLoanEquipment(LoanEquipmentDTO loanEquipmentDTO);

    List<LoanEquipment> loanEquipmentDTOsToLoanEquipments(List<LoanEquipmentDTO> loanEquipmentDTOs);
}
