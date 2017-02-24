package za.co.ticket.service.dto;

import org.codehaus.groovy.runtime.metaclass.ConcurrentReaderHashMap;

import java.time.ZonedDateTime;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;


/**
 * A DTO for the Employee entity.
 */
public class EmployeeDTO implements Serializable {

    private String id;

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;

    private ZonedDateTime hireDate;

    private Long salary;

    private Long commissionPct;

    private List<String> propList = new ArrayList<>();
    private Map<String, Object> propMap = new ConcurrentReaderHashMap();

    public List<String> getPropList()
    {
        return propList;
    }

    public void setPropList(List<String> propList)
    {
        this.propList = propList;
    }

    public Map<String, Object> getPropMap()
    {
        return propMap;
    }

    public void setPropMap(Map<String, Object> propMap)
    {
        this.propMap = propMap;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public ZonedDateTime getHireDate() {
        return hireDate;
    }

    public void setHireDate(ZonedDateTime hireDate) {
        this.hireDate = hireDate;
    }
    public Long getSalary() {
        return salary;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
    }
    public Long getCommissionPct() {
        return commissionPct;
    }

    public void setCommissionPct(Long commissionPct) {
        this.commissionPct = commissionPct;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EmployeeDTO employeeDTO = (EmployeeDTO) o;

        if ( ! Objects.equals(id, employeeDTO.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "EmployeeDTO{" +
            "id=" + id +
            ", firstName='" + firstName + "'" +
            ", lastName='" + lastName + "'" +
            ", email='" + email + "'" +
            ", phoneNumber='" + phoneNumber + "'" +
            ", hireDate='" + hireDate + "'" +
            ", salary='" + salary + "'" +
            ", commissionPct='" + commissionPct + "'" +
            '}';
    }
}
