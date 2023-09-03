package lan.zold.emp;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordService {
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    public String enPass(String clearPass) {            
      String enPass = encoder.encode(clearPass);
      return enPass;
    }
    public boolean checkPass(String clearPass, String enPass) {
        return encoder.matches(clearPass, enPass);
    }
}
