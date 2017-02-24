package za.co.ticket.service.dto;

import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;


/**
 * A DTO for the LoanEquipment entity.
 */
public class LoanEquipmentDTO implements Serializable {

    private String id;

    @NotNull
    private Integer equipmentId;

    @NotNull
    private String equipmentName;

    @NotNull
    private String equipmentDescription;

    @NotNull
    private ZonedDateTime initialUploadDate;

    @NotNull
    private Integer uploadedBy;

    @NotNull
    private Boolean equipmentActive;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public Integer getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(Integer equipmentId) {
        this.equipmentId = equipmentId;
    }
    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }
    public String getEquipmentDescription() {
        return equipmentDescription;
    }

    public void setEquipmentDescription(String equipmentDescription) {
        this.equipmentDescription = equipmentDescription;
    }
    public ZonedDateTime getInitialUploadDate() {
        return initialUploadDate;
    }

    public void setInitialUploadDate(ZonedDateTime initialUploadDate) {
        this.initialUploadDate = initialUploadDate;
    }
    public Integer getUploadedBy() {
        return uploadedBy;
    }

    public void setUploadedBy(Integer uploadedBy) {
        this.uploadedBy = uploadedBy;
    }
    public Boolean getEquipmentActive() {
        return equipmentActive;
    }

    public void setEquipmentActive(Boolean equipmentActive) {
        this.equipmentActive = equipmentActive;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        LoanEquipmentDTO loanEquipmentDTO = (LoanEquipmentDTO) o;

        if ( ! Objects.equals(id, loanEquipmentDTO.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "LoanEquipmentDTO{" +
            "id=" + id +
            ", equipmentId='" + equipmentId + "'" +
            ", equipmentName='" + equipmentName + "'" +
            ", equipmentDescription='" + equipmentDescription + "'" +
            ", initialUploadDate='" + initialUploadDate + "'" +
            ", uploadedBy='" + uploadedBy + "'" +
            ", equipmentActive='" + equipmentActive + "'" +
            '}';
    }
}
