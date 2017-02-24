package za.co.ticket.service.dto;

import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;


/**
 * A DTO for the Tenant entity.
 */
public class TenantDTO implements Serializable {

    private String id;

    @NotNull
    private Integer tenantId;

    @NotNull
    private String tenantName;

    @NotNull
    private ZonedDateTime creationDate;

    private ZonedDateTime dateCeased;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public Integer getTenantId() {
        return tenantId;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }
    public String getTenantName() {
        return tenantName;
    }

    public void setTenantName(String tenantName) {
        this.tenantName = tenantName;
    }
    public ZonedDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(ZonedDateTime creationDate) {
        this.creationDate = creationDate;
    }
    public ZonedDateTime getDateCeased() {
        return dateCeased;
    }

    public void setDateCeased(ZonedDateTime dateCeased) {
        this.dateCeased = dateCeased;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TenantDTO tenantDTO = (TenantDTO) o;

        if ( ! Objects.equals(id, tenantDTO.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "TenantDTO{" +
            "id=" + id +
            ", tenantId='" + tenantId + "'" +
            ", tenantName='" + tenantName + "'" +
            ", creationDate='" + creationDate + "'" +
            ", dateCeased='" + dateCeased + "'" +
            '}';
    }
}
