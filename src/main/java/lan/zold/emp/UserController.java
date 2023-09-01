package lan.zold.emp;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	UserRepository userRepository;

    @CrossOrigin
    @PostMapping("/login")
    public @ResponseBody String login(@RequestBody User user) {



        String token = getToken(user.getName());
        return token;
    }

    public String getToken(String name) {
        String key = "adsfffd";
        Map<String, Object> claims = new HashMap<>();
        String token = Jwts.builder()
        .setSubject(name)
        .setClaims(claims)            
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(new Date(System.currentTimeMillis() * 500_000))
        .signWith(SignatureAlgorithm.HS512, key.getBytes())
        .compact();    
        return token;
    }

    public String checkToken(String token) {
        try {
            // A titkos kulcs, amivel a token alá lett írva
            String key = "adsfffd";

            // Token ellenőrzése és annak tartalmának kiolvasása
            Claims claims = Jwts.parser().setSigningKey(key.getBytes()).parseClaimsJws(token).getBody();

            // Token ellenőrzése, hogy lejárt-e
            Date expirationDate = claims.getExpiration();
            Date currentDate = new Date();
            if (expirationDate.before(currentDate)) {
                return "Lejárt token";
            }

            // Token érvényes
            return "Érvényes token";

        } catch (SignatureException e) {
            // Hibás aláírás esetén
            return "Hibás token";
        } catch (Exception e) {
            // Egyéb hibák esetén
            return "Hiba történt: " + e.getMessage();
        }
    }    

}
