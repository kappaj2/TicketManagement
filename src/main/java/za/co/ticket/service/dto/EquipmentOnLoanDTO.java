package za.co.ticket.service.dto;

import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;


/**
 * A DTO for the EquipmentOnLoan entity.
 */
public class EquipmentOnLoanDTO implements Serializable {

    private String id;

    @NotNull
    private Integer equipmentOnLoanEntryId;

    @NotNull
    private Integer tenantId;

    @NotNull
    private Integer equipmentId;

    @NotNull
    private ZonedDateTime dateBookedOut;

    private ZonedDateTime dateInstalledAtTenant;

    @NotNull
    private Integer bookOutTechnitian;

    private ZonedDateTime dateRemovedFromTenant;

    private Integer installationTechnitian;

    private Integer removalTechnitian;

    private ZonedDateTime dateBookedBackIn;

    private Integer bookInTechnitian;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public Integer getEquipmentOnLoanEntryId() {
        return equipmentOnLoanEntryId;
    }

    public void setEquipmentOnLoanEntryId(Integer equipmentOnLoanEntryId) {
        this.equipmentOnLoanEntryId = equipmentOnLoanEntryId;
    }
    public Integer getTenantId() {
        return tenantId;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }
    public Integer getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(Integer equipmentId) {
        this.equipmentId = equipmentId;
    }
    public ZonedDateTime getDateBookedOut() {
        return dateBookedOut;
    }

    public void setDateBookedOut(ZonedDateTime dateBookedOut) {
        this.dateBookedOut = dateBookedOut;
    }
    public ZonedDateTime getDateInstalledAtTenant() {
        return dateInstalledAtTenant;
    }

    public void setDateInstalledAtTenant(ZonedDateTime dateInstalledAtTenant) {
        this.dateInstalledAtTenant = dateInstalledAtTenant;
    }
    public Integer getBookOutTechnitian() {
        return bookOutTechnitian;
    }

    public void setBookOutTechnitian(Integer bookOutTechnitian) {
        this.bookOutTechnitian = bookOutTechnitian;
    }
    public ZonedDateTime getDateRemovedFromTenant() {
        return dateRemovedFromTenant;
    }

    public void setDateRemovedFromTenant(ZonedDateTime dateRemovedFromTenant) {
        this.dateRemovedFromTenant = dateRemovedFromTenant;
    }
    public Integer getInstallationTechnitian() {
        return installationTechnitian;
    }

    public void setInstallationTechnitian(Integer installationTechnitian) {
        this.installationTechnitian = installationTechnitian;
    }
    public Integer getRemovalTechnitian() {
        return removalTechnitian;
    }

    public void setRemovalTechnitian(Integer removalTechnitian) {
        this.removalTechnitian = removalTechnitian;
    }
    public ZonedDateTime getDateBookedBackIn() {
        return dateBookedBackIn;
    }

    public void setDateBookedBackIn(ZonedDateTime dateBookedBackIn) {
        this.dateBookedBackIn = dateBookedBackIn;
    }
    public Integer getBookInTechnitian() {
        return bookInTechnitian;
    }

    public void setBookInTechnitian(Integer bookInTechnitian) {
        this.bookInTechnitian = bookInTechnitian;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EquipmentOnLoanDTO equipmentOnLoanDTO = (EquipmentOnLoanDTO) o;

        if ( ! Objects.equals(id, equipmentOnLoanDTO.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "EquipmentOnLoanDTO{" +
            "id=" + id +
            ", equipmentOnLoanEntryId='" + equipmentOnLoanEntryId + "'" +
            ", tenantId='" + tenantId + "'" +
            ", equipmentId='" + equipmentId + "'" +
            ", dateBookedOut='" + dateBookedOut + "'" +
            ", dateInstalledAtTenant='" + dateInstalledAtTenant + "'" +
            ", bookOutTechnitian='" + bookOutTechnitian + "'" +
            ", dateRemovedFromTenant='" + dateRemovedFromTenant + "'" +
            ", installationTechnitian='" + installationTechnitian + "'" +
            ", removalTechnitian='" + removalTechnitian + "'" +
            ", dateBookedBackIn='" + dateBookedBackIn + "'" +
            ", bookInTechnitian='" + bookInTechnitian + "'" +
            '}';
    }
}
