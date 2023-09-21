package pl.coderslab.final_project.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.final_project.domain.User;
import pl.coderslab.final_project.service.UserRepository;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Controller("/user")
public class UserController {
    UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("user/signUp")
    public String insertData(@RequestParam Long id, Model model) {
        User user;
        if (id == 0) {
            user = new User();
        } else {
            user = getUser(id);
        }
        model.addAttribute("user", user);
        return "user/signUp";
    }

    @PostMapping("user/signUp")
    public String processData(@Valid User user, BindingResult result, Model model) {
        List<String> passwordMessages = checkPassword(user.getPassword());
        boolean isDataValid = true;
        if (passwordMessages.size() != 0) {
            model.addAttribute("passwordMessages", passwordMessages);
            isDataValid = false;
        }

        if (result.hasErrors()) {
            isDataValid = false;
        }

        if (!isDataValid) {
            return "user/signUp";
        }

        userRepository.save(user);
        model.addAttribute("user", user);
        return "user/details";
    }

    private List<String> checkPassword(String password) {
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
        return messages;
    }

    public static Boolean findPattern(String regex, String password) {
        Pattern compiledPattern = Pattern.compile(regex);
        return compiledPattern.matcher(password).find();
    }

    private Boolean checkEmail(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    private User getUser(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

}
