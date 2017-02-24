package za.co.ticket.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A Tenant.
 */

@Document(collection = "tenant")
public class Tenant implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("tenant_id")
    private Integer tenantId;

    @NotNull
    @Field("tenant_name")
    private String tenantName;

    @NotNull
    @Field("creation_date")
    private ZonedDateTime creationDate;

    @Field("date_ceased")
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

    public Tenant tenantId(Integer tenantId) {
        this.tenantId = tenantId;
        return this;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }

    public String getTenantName() {
        return tenantName;
    }

    public Tenant tenantName(String tenantName) {
        this.tenantName = tenantName;
        return this;
    }

    public void setTenantName(String tenantName) {
        this.tenantName = tenantName;
    }

    public ZonedDateTime getCreationDate() {
        return creationDate;
    }

    public Tenant creationDate(ZonedDateTime creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    public void setCreationDate(ZonedDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public ZonedDateTime getDateCeased() {
        return dateCeased;
    }

    public Tenant dateCeased(ZonedDateTime dateCeased) {
        this.dateCeased = dateCeased;
        return this;
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
        Tenant tenant = (Tenant) o;
        if (tenant.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, tenant.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Tenant{" +
            "id=" + id +
            ", tenantId='" + tenantId + "'" +
            ", tenantName='" + tenantName + "'" +
            ", creationDate='" + creationDate + "'" +
            ", dateCeased='" + dateCeased + "'" +
            '}';
    }
}
