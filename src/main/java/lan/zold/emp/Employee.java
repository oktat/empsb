package lan.zold.emp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String city;
    private double salary;

    public Integer getId() {
        return id;
      }
    
      public void setId(Integer id) {
        this.id = id;
      }
    
      public String getName() {
        return name;
      }
    
      public void setName(String name) {
        this.name = name;
      }
      public String getCity() {
        return city;
      }
    
      public void setCity(String city) {
        this.city = city;
      }
      public double getSalary() {
        return salary;
      }
    
      public void setSalary(double salary) {
        this.salary = salary;
      }       
}
