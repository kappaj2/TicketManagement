package za.co.ticket.service.mapper;

import za.co.ticket.domain.*;
import za.co.ticket.service.dto.EquipmentOnLoanDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity EquipmentOnLoan and its DTO EquipmentOnLoanDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EquipmentOnLoanMapper {

    EquipmentOnLoanDTO equipmentOnLoanToEquipmentOnLoanDTO(EquipmentOnLoan equipmentOnLoan);

    List<EquipmentOnLoanDTO> equipmentOnLoansToEquipmentOnLoanDTOs(List<EquipmentOnLoan> equipmentOnLoans);

    EquipmentOnLoan equipmentOnLoanDTOToEquipmentOnLoan(EquipmentOnLoanDTO equipmentOnLoanDTO);

    List<EquipmentOnLoan> equipmentOnLoanDTOsToEquipmentOnLoans(List<EquipmentOnLoanDTO> equipmentOnLoanDTOs);
}
