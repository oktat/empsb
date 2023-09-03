package lan.zold.emp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {
	
  @Autowired
	UserRepository userRepository;

  @Autowired
  PasswordService passwordService;

  @CrossOrigin
  @PostMapping("/registry")
  @ResponseStatus(code = HttpStatus.CREATED)    
  public User store(@RequestBody User user) {
    String enpass = passwordService.enPass(user.getPassword());
    user.setPassword(enpass);
    User res = userRepository.save(user);
    return res;
  }

}
