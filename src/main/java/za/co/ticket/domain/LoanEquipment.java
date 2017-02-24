package za.co.ticket.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A LoanEquipment.
 */

@Document(collection = "loan_equipment")
public class LoanEquipment implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("equipment_id")
    private Integer equipmentId;

    @NotNull
    @Field("equipment_name")
    private String equipmentName;

    @NotNull
    @Field("equipment_description")
    private String equipmentDescription;

    @NotNull
    @Field("initial_upload_date")
    private ZonedDateTime initialUploadDate;

    @NotNull
    @Field("uploaded_by")
    private Integer uploadedBy;

    @NotNull
    @Field("equipment_active")
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

    public LoanEquipment equipmentId(Integer equipmentId) {
        this.equipmentId = equipmentId;
        return this;
    }

    public void setEquipmentId(Integer equipmentId) {
        this.equipmentId = equipmentId;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public LoanEquipment equipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
        return this;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public String getEquipmentDescription() {
        return equipmentDescription;
    }

    public LoanEquipment equipmentDescription(String equipmentDescription) {
        this.equipmentDescription = equipmentDescription;
        return this;
    }

    public void setEquipmentDescription(String equipmentDescription) {
        this.equipmentDescription = equipmentDescription;
    }

    public ZonedDateTime getInitialUploadDate() {
        return initialUploadDate;
    }

    public LoanEquipment initialUploadDate(ZonedDateTime initialUploadDate) {
        this.initialUploadDate = initialUploadDate;
        return this;
    }

    public void setInitialUploadDate(ZonedDateTime initialUploadDate) {
        this.initialUploadDate = initialUploadDate;
    }

    public Integer getUploadedBy() {
        return uploadedBy;
    }

    public LoanEquipment uploadedBy(Integer uploadedBy) {
        this.uploadedBy = uploadedBy;
        return this;
    }

    public void setUploadedBy(Integer uploadedBy) {
        this.uploadedBy = uploadedBy;
    }

    public Boolean isEquipmentActive() {
        return equipmentActive;
    }

    public LoanEquipment equipmentActive(Boolean equipmentActive) {
        this.equipmentActive = equipmentActive;
        return this;
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
        LoanEquipment loanEquipment = (LoanEquipment) o;
        if (loanEquipment.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, loanEquipment.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "LoanEquipment{" +
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
