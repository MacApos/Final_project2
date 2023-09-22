package pl.coderslab.final_project.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.final_project.domain.User;
import pl.coderslab.final_project.service.UserRepository;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Controller
@RequestMapping("/user")
@SessionAttributes("loggedUser")
public class UserController {
    UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/signUp")
    public String insertRegistrationData(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "user/signUp";
    }

    @PostMapping("/signUp")
    public String processRegistrationData(@Valid User user, BindingResult result, Model model, HttpServletRequest request) {
        String password = user.getPassword();
        String passwordMessage = checkPassword(password);
        String emailMessage = checkEmail(user.getEmail());
        boolean isDataValid = true;
        if (!passwordMessage.equals("")) {
            model.addAttribute("passwordMessage", passwordMessage);
            isDataValid = false;
        }
        if (emailMessage != null) {
            model.addAttribute("emailMessage", emailMessage);
            isDataValid = false;
        }
        if (result.hasErrors()) {
            isDataValid = false;
        }
        if (!isDataValid) {
            return "user/signUp";
        }

        userRepository.save(user);
        model.addAttribute("loggedUser", user);
        return "redirect:..";
    }

//    private String hashPassword(String password) {
//        return BCrypt
//    }

    private String checkPassword(String password) {
        List<String> messages = new ArrayList<>();
        if (!findPattern("[A-Z]+", password)) {
            messages.add("Password must contains at least one upper case letter.");
        }
        if (!findPattern("[a-z]+", password)) {
            messages.add("Password must contains at least one lower case letter.");
        }
        if (!findPattern("[0-9]+", password)) {
            messages.add("Password must contains at least one digit.");
        }
        if (!findPattern("[!@#$%^&*()_+\\-={}\\[\\]:\";'<>?,./]+", password)) {
            messages.add("Password must contains at least one special character.");
        }
        return String.join(" ", messages);
    }

    public static Boolean findPattern(String regex, String password) {
        Pattern compiledPattern = Pattern.compile(regex);
        return compiledPattern.matcher(password).find();
    }

    private String checkEmail(String email) {
        if (userRepository.findByEmail(email).isPresent()) {
            return "Email already exists.";
        }
        return null;
    }

//    private String insertLoginData(){
//
//    }

}
