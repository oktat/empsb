package lan.zold.emp;

public class EmployeeResponse {
    public Employee employee;
    public String success;
    public Employee getEmployee() {
        return employee;
    }
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
    public String getSuccess() {
        return this.success;
    }
    public void setSuccess(String success) {
        this.success = success;
    }    
}
