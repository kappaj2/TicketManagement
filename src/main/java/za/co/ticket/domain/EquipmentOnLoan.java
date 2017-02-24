package za.co.ticket.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A EquipmentOnLoan.
 */

@Document(collection = "equipment_on_loan")
public class EquipmentOnLoan implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("equipment_on_loan_entry_id")
    private Integer equipmentOnLoanEntryId;

    @NotNull
    @Field("tenant_id")
    private Integer tenantId;

    @NotNull
    @Field("equipment_id")
    private Integer equipmentId;

    @NotNull
    @Field("date_booked_out")
    private ZonedDateTime dateBookedOut;

    @Field("date_installed_at_tenant")
    private ZonedDateTime dateInstalledAtTenant;

    @NotNull
    @Field("book_out_technitian")
    private Integer bookOutTechnitian;

    @Field("date_removed_from_tenant")
    private ZonedDateTime dateRemovedFromTenant;

    @Field("installation_technitian")
    private Integer installationTechnitian;

    @Field("removal_technitian")
    private Integer removalTechnitian;

    @Field("date_booked_back_in")
    private ZonedDateTime dateBookedBackIn;

    @Field("book_in_technitian")
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

    public EquipmentOnLoan equipmentOnLoanEntryId(Integer equipmentOnLoanEntryId) {
        this.equipmentOnLoanEntryId = equipmentOnLoanEntryId;
        return this;
    }

    public void setEquipmentOnLoanEntryId(Integer equipmentOnLoanEntryId) {
        this.equipmentOnLoanEntryId = equipmentOnLoanEntryId;
    }

    public Integer getTenantId() {
        return tenantId;
    }

    public EquipmentOnLoan tenantId(Integer tenantId) {
        this.tenantId = tenantId;
        return this;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }

    public Integer getEquipmentId() {
        return equipmentId;
    }

    public EquipmentOnLoan equipmentId(Integer equipmentId) {
        this.equipmentId = equipmentId;
        return this;
    }

    public void setEquipmentId(Integer equipmentId) {
        this.equipmentId = equipmentId;
    }

    public ZonedDateTime getDateBookedOut() {
        return dateBookedOut;
    }

    public EquipmentOnLoan dateBookedOut(ZonedDateTime dateBookedOut) {
        this.dateBookedOut = dateBookedOut;
        return this;
    }

    public void setDateBookedOut(ZonedDateTime dateBookedOut) {
        this.dateBookedOut = dateBookedOut;
    }

    public ZonedDateTime getDateInstalledAtTenant() {
        return dateInstalledAtTenant;
    }

    public EquipmentOnLoan dateInstalledAtTenant(ZonedDateTime dateInstalledAtTenant) {
        this.dateInstalledAtTenant = dateInstalledAtTenant;
        return this;
    }

    public void setDateInstalledAtTenant(ZonedDateTime dateInstalledAtTenant) {
        this.dateInstalledAtTenant = dateInstalledAtTenant;
    }

    public Integer getBookOutTechnitian() {
        return bookOutTechnitian;
    }

    public EquipmentOnLoan bookOutTechnitian(Integer bookOutTechnitian) {
        this.bookOutTechnitian = bookOutTechnitian;
        return this;
    }

    public void setBookOutTechnitian(Integer bookOutTechnitian) {
        this.bookOutTechnitian = bookOutTechnitian;
    }

    public ZonedDateTime getDateRemovedFromTenant() {
        return dateRemovedFromTenant;
    }

    public EquipmentOnLoan dateRemovedFromTenant(ZonedDateTime dateRemovedFromTenant) {
        this.dateRemovedFromTenant = dateRemovedFromTenant;
        return this;
    }

    public void setDateRemovedFromTenant(ZonedDateTime dateRemovedFromTenant) {
        this.dateRemovedFromTenant = dateRemovedFromTenant;
    }

    public Integer getInstallationTechnitian() {
        return installationTechnitian;
    }

    public EquipmentOnLoan installationTechnitian(Integer installationTechnitian) {
        this.installationTechnitian = installationTechnitian;
        return this;
    }

    public void setInstallationTechnitian(Integer installationTechnitian) {
        this.installationTechnitian = installationTechnitian;
    }

    public Integer getRemovalTechnitian() {
        return removalTechnitian;
    }

    public EquipmentOnLoan removalTechnitian(Integer removalTechnitian) {
        this.removalTechnitian = removalTechnitian;
        return this;
    }

    public void setRemovalTechnitian(Integer removalTechnitian) {
        this.removalTechnitian = removalTechnitian;
    }

    public ZonedDateTime getDateBookedBackIn() {
        return dateBookedBackIn;
    }

    public EquipmentOnLoan dateBookedBackIn(ZonedDateTime dateBookedBackIn) {
        this.dateBookedBackIn = dateBookedBackIn;
        return this;
    }

    public void setDateBookedBackIn(ZonedDateTime dateBookedBackIn) {
        this.dateBookedBackIn = dateBookedBackIn;
    }

    public Integer getBookInTechnitian() {
        return bookInTechnitian;
    }

    public EquipmentOnLoan bookInTechnitian(Integer bookInTechnitian) {
        this.bookInTechnitian = bookInTechnitian;
        return this;
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
        EquipmentOnLoan equipmentOnLoan = (EquipmentOnLoan) o;
        if (equipmentOnLoan.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, equipmentOnLoan.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "EquipmentOnLoan{" +
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
