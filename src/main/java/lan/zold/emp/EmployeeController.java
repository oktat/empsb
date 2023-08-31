package lan.zold.emp;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class EmployeeController {

	@Autowired
	EmployeeRepository empRepository;

	@CrossOrigin
	@GetMapping("/employees")
	public @ResponseBody Iterable<Employee> index() {
		return empRepository.findAll();
	}
	@CrossOrigin
	@GetMapping("/employees/{id}")
	public @ResponseBody Employee show(@PathVariable Integer id) {
		return empRepository.findById(id).get();
	}
	@CrossOrigin
	@PostMapping(path="/employees")
	public Employee store(@RequestBody Employee emp) {
		Employee res = empRepository.save(emp);
		return res;
	}
	@CrossOrigin
	@PutMapping("/employees/{id}")
	public Employee update(@RequestBody Employee emp, @PathVariable Integer id) {
		
		Optional<Employee> orig = empRepository.findById(id);
		if(orig.isPresent()) {
			Employee emp2 = orig.get();
			emp2.setName(emp.getName());
			emp2.setCity(emp.getCity());
			emp2.setSalary(emp.getSalary());
			return empRepository.save(emp2);
		}else {
			return emp;
		}
	}
	@CrossOrigin
	@DeleteMapping("/employees/{id}")
	public Employee delete(@PathVariable Integer id) {
		Optional<Employee> orig = empRepository.findById(id);
		empRepository.deleteById(id);
		return orig.get();
	}
}
