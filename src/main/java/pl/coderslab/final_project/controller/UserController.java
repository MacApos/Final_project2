package pl.coderslab.final_project.controller;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.final_project.domain.Cart;
import pl.coderslab.final_project.domain.CartItem;
import pl.coderslab.final_project.domain.User;
import pl.coderslab.final_project.repository.CartItemRepository;
import pl.coderslab.final_project.repository.CartRepository;
import pl.coderslab.final_project.repository.UserRepository;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Controller
@RequestMapping("/user")
@SessionAttributes({"loggedUser", "admin", "cart"})
public class UserController {
    UserRepository userRepository;
    CartRepository cartRepository;
    CartItemRepository cartItemRepository;

    public UserController(UserRepository userRepository, CartRepository cartRepository,
                          CartItemRepository cartItemRepository) {
        this.userRepository = userRepository;
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
    }

    @GetMapping("/signUp")
    public String insertRegistrationData(Model model) {
        System.out.println("test");
        User user = new User();
        model.addAttribute("user", user);
        return "user/signUp";
    }

    @PostMapping("/signUp")
    public String processLoginData(@Valid User user, BindingResult result, Model model) {
        String password = user.getPassword();
        String passwordMessage = checkPassword(password);
        String emailMessage = checkEmail(user.getEmail());
        boolean isDataValid = true;
        if (!passwordMessage.isBlank()) {
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
    private String insertLoginData(@RequestParam(name = "path", required = false) String path, Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("path", path);
        return "user/login";
    }

    @PostMapping("/login")
    public String login(User user, Model model, HttpSession session) {
        return processLoginData(user, model, session, "redirect:..");
    }

    @RequestMapping(value = "/orderLogin")
    public String loginAfterOrder(User user, Model model, HttpSession session) {
        return processLoginData(user, model, session, "redirect:../cart/cartDetails");
    }

    public String processLoginData(User user, Model model, HttpSession session, String path) {
        String email = user.getEmail();
        String password = user.getPassword();
        user = userRepository.findByEmail(email).orElse(null);
        if (user == null) {
            model.addAttribute("emailMessage", "Incorrect email");
            return "user/login";
        }

        if (!BCrypt.checkpw(password, user.getPassword())) {
            model.addAttribute("passwordMessage", "Incorrect password");
            return "user/login";
        }

        model.addAttribute("loggedUser", user);
        if (user.getAdmin() == 1) {
            model.addAttribute("admin", 1);
        }
        Cart dbCart = cartRepository.findByUser(user).orElse(null);
        if (session.getAttribute("cart") != null) {
            Cart sessCart = (Cart) session.getAttribute("cart");
            if (dbCart == null) {
                sessCart.setUser(user);
            } else {
                dbCart.setItemsQuantity(dbCart.getItemsQuantity() + sessCart.getItemsQuantity());

                for (CartItem cartItem : cartItemRepository.findAllByCart(dbCart)) {
                    CartItem existingCartItem = cartItemRepository
                            .findFirstByProductAndCart(cartItem.getProduct(), sessCart).orElse(null);
                    if (existingCartItem == null) {
                        cartItemRepository.updateAllCartItemsOldCartToNewCart(dbCart, sessCart);
                    } else {
                        cartItem.setQuantity(cartItem.getQuantity() + existingCartItem.getQuantity());
                        cartItemRepository.delete(existingCartItem);
                    }
                }
                cartRepository.delete(sessCart);
                sessCart = dbCart;
            }
            cartRepository.save(sessCart);
            model.addAttribute("cart", sessCart);
        } else if (dbCart != null) {
            model.addAttribute("cart", dbCart);
        }
        return path;
    }

    @RequestMapping("/userDetails")
    public String showUser(HttpSession session, Model model) {
        User showUser = (User) session.getAttribute("loggedUser");
        model.addAttribute("showUser", showUser);
        return "user/userDetails";
    }

}
