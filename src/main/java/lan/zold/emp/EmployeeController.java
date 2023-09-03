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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class EmployeeController {

	@Autowired
	EmployeeRepository empRepository;

	@Autowired
	CustomProperties customProperties;


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
	public Employee store(
		@RequestBody Employee emp, 
		@RequestHeader(
			value="Authorization", 
			required=false) 
			String tokenHeader) {
		
		String authOkStr = customProperties.getAuth();
		boolean authOk = Boolean.parseBoolean(authOkStr);
		Employee res = null;
		if(authOk) {
			String token = tokenHeader.replace("Bearer ", "");
			AuthController authController = new AuthController();
			
			try {
				String tokenOk = authController.checkToken(token);
				if(tokenOk.equals("tokenok")) {
					res = empRepository.save(emp);
				}else {
					String msg = "Hiba! A token nem megfelelő!";
					throw new IllegalArgumentException(msg);
				}
			} catch (Exception e) {
				System.err.println("Hiba! A token nem jó!");
			}
		}else {
			res = empRepository.save(emp);
		}
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
