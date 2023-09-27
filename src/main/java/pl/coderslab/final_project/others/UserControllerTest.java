package pl.coderslab.final_project.others;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.final_project.domain.Cart;
import pl.coderslab.final_project.domain.User;
import pl.coderslab.final_project.service.CartItemRepository;
import pl.coderslab.final_project.service.CartRepository;
import pl.coderslab.final_project.service.UserRepository;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

public class UserControllerTest {
    UserRepository userRepository;
    CartRepository cartRepository;
    CartItemRepository cartItemRepository;

    public UserControllerTest(UserRepository userRepository, CartRepository cartRepository,
                          CartItemRepository cartItemRepository) {
        this.userRepository = userRepository;
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
    }

    @GetMapping("/signUp")
    public String insertRegistrationData(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "user/signUp";
    }

    @PostMapping("/signUp")
    public String procesLoginData(@Valid User user, BindingResult result, Model model) {
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
        user.setPassword(hashPassword(password));
        userRepository.save(user);
        model.addAttribute("loggedUser", user);
        return "redirect:..";
    }

    private String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

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

    @GetMapping("/login")
    private String insertLoginData(Model model) {
        model.addAttribute("user", new User());
        return "user/login";
    }

    @PostMapping("/login")
    public String processLoginData(User user, Model model, HttpSession session) {
        String email = user.getEmail();
        String password = user.getPassword();
        user = userRepository.findByEmail(email).orElse(null);
        User existingUser;
        if (user==null) {
            model.addAttribute("emailMessage", "Incorrect email");
            return "user/login";
        }

        if (!BCrypt.checkpw(password, user.getPassword())) {
            model.addAttribute("passwordMessage", "Incorrect password");
            return "user/login";
        }
        model.addAttribute("loggedUser", user);

        Cart dbCart = cartRepository.findByUser(user).orElse(null);
        if (session.getAttribute("cart") != null && // w sesji jest koszyk
                dbCart != null) { // w bazie jest koszyk
            Cart sessCart = (Cart) session.getAttribute("cart");

            if (sessCart.getUser() == null) { // czy koszyk w sesji ma przypisanego użytkownika
                dbCart.setItemsQuantity(dbCart.getItemsQuantity() + sessCart.getItemsQuantity());
                cartItemRepository.updateAllCartItemsOldCartToNewCart(dbCart, sessCart);
                if (cartRepository.findById(sessCart.getId()).isPresent()) { //czy koszyk w sesji jest w bazie
                    cartRepository.delete(sessCart);
                }
                cartRepository.save(dbCart);
                model.addAttribute("cart", dbCart);
            }

        }
        if (session.getAttribute("cart") != null && // w sesji jest koszyk
                dbCart == null) { // w bazie nie ma koszyka
            Cart sessCart = (Cart) session.getAttribute("cart");
            if (sessCart.getUser() == null) {
                sessCart.setUser(user);
                cartRepository.save(sessCart);
                model.addAttribute("cart", sessCart);
            }
        }

        if (session.getAttribute("cart") == null &&
                dbCart != null) {
            model.addAttribute("cart", dbCart);
        }

        return "redirect:..";
    }

    @RequestMapping("/userDetails")
    public String showUser(HttpSession session, Model model) {
        User showUser = (User) session.getAttribute("loggedUser");
        model.addAttribute("showUser", showUser);
        return "user/userDetails";
    }

}
