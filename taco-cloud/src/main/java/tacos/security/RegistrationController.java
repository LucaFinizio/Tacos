package tacos.security;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import tacos.User;
import tacos.data.UserRepository;

@Controller
@RequestMapping("/register")
public class RegistrationController {
  
  private UserRepository userRepo;
  private PasswordEncoder passwordEncoder;

  public RegistrationController(UserRepository userRepo, PasswordEncoder passwordEncoder) {
    this.userRepo = userRepo;
    this.passwordEncoder = passwordEncoder;
  }
  
  @GetMapping
  public String registerForm() {
    return "registration";
  }
  
  @PostMapping
  public String processRegistration(RegistrationForm form) {
	
	  
	//BUG: The user is not correctly registered in the database (all fields are null). 
	//The following two lines are a workaround
	User utenteDiProva = new User("buzz","infinity","Buzz_Lightyear","via Delli 2","Pescara","Italy","65127","08567876");
    userRepo.save(utenteDiProva);
    
	//userRepo.save(form.toUser(passwordEncoder));
    return "redirect:/login";
  }

}