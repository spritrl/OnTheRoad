package Controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Entity.UserEntity;
import Repository.UserRepository;

/**
 * @author REALINI Christophe
 *
 *
 */

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/")
public class Controller {

    @Autowired
    private UserRepository userRepository;

	@GetMapping(value = "login/{username}&{password}")
    Boolean login(@PathVariable("username") String username,
    			  @PathVariable("password") String password) {
		if (username == null || password == null) {
            return false;
        }
		
        final Optional<UserEntity> user = userRepository.findById(username);

        if (user.isPresent()) {
        	return user.get().getPassword().compareTo(password)==0;
        } else {
			return false;
        }
    }
	
	@GetMapping(value = "register/{username}&{mail}&{password}")
    Boolean register(@PathVariable("username") String username,
    				 @PathVariable("mail") String mail,
    			     @PathVariable("password") String password) {
		if(username == null || mail == null || password == null) return false;
            final UserEntity newUser = new UserEntity();
            newUser.setUsername(username);
            newUser.setPassword(password);
            newUser.setMail(mail);

            userRepository.save(newUser);
    	return true;
    }
    
}
