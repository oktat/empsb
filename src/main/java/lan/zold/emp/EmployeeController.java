package lan.zold.emp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/api")
public class EmployeeController {

	@Autowired
	EmployeeRepository empRepository;

	@GetMapping(path="/employees")
	public @ResponseBody Iterable<Employee> index() {
		return empRepository.findAll();
	}
	// public String index() {
	// 	return "Így megy";
	// }    
}
