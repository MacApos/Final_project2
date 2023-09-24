package pl.coderslab.final_project.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import pl.coderslab.final_project.domain.Cart;
import pl.coderslab.final_project.domain.CartItem;
import pl.coderslab.final_project.domain.Product;
import pl.coderslab.final_project.domain.User;
import pl.coderslab.final_project.service.CartItemRepository;
import pl.coderslab.final_project.service.CartRepository;
import pl.coderslab.final_project.service.ProductRepository;
import pl.coderslab.final_project.service.UserRepository;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/cart")
@SessionAttributes("cart")
public class CartController {
    CartRepository cartRepository;
    ProductRepository productRepository;
    CartItemRepository cartItemRepository;
    UserRepository userRepository;

    public CartController(CartRepository cartRepository, ProductRepository productRepository,
                          CartItemRepository cartItemRepository, UserRepository userRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.cartItemRepository = cartItemRepository;
        this.userRepository = userRepository;
    }

    @RequestMapping("/addToCart/{id}")
    @ResponseBody
    public String addToCart(@Valid CartItem cartItem, BindingResult result,
                            @PathVariable("id") Long id, Model model, HttpSession session) {
        Product product = productRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        if (result.hasErrors()) {
            model.addAttribute("product", product);
            return "product/productDetails";
        }
        Cart cart = new Cart();

        if (session.getAttribute("cart") == null &&
                session.getAttribute("loggedUser") == null) {
            cart = new Cart();
            cart = cartRepository.save(cart);
            model.addAttribute("cart", cart);
        } else if (session.getAttribute("cart") == null &&
                session.getAttribute("loggedUser") != null) {
            User user = (User) session.getAttribute("loggedUser");
            if (user.getCart() == null) {
                cart = new Cart();
            } else {
                cart = user.getCart();
            }
            cart = cartRepository.save(cart);
            user.setCart(cart);
            user = userRepository.save(user);
            model.addAttribute("cart", cart);
            model.addAttribute("loggedUser", user);
        } else if (session.getAttribute("cart") != null &&
                session.getAttribute("loggedUser") == null) {
            cart = (Cart) session.getAttribute("cart");
            if (cart == null) {
                cart = new Cart();
            }
            cart = cartRepository.save(cart);
            model.addAttribute("cart", cart);
        } else if (session.getAttribute("cart") != null &&
                session.getAttribute("loggedUser") != null) {
            User user = (User) session.getAttribute("loggedUser");
            cart = (Cart) session.getAttribute("cart");
            if (cart == null) {
                if (user.getCart() == null) {
                    cart = new Cart();
                } else {
                    cart = user.getCart();
                }
            } else {
                if (user.getCart() == null) {
                    user.setCart(cart);
                } else {
                    cartItemRepository.updateAllCartByCart(cart, user.getCart());
                }
            }
            cart = cartRepository.save(cart);
            model.addAttribute("cart", cart);
            model.addAttribute("LoggedUser", user);
        }

        cartItem.setCart(cart);
        cartItem.setProduct(product);
        cartItem = cartItemRepository.save(cartItem);
        String message = "";
//        cartItemRepository.findByProductId(product.getId()).isPresent();


//        CartItem cartItem1 = cartItemRepository.findByProduct(product).orElse(null);
//        if (cartItem1 == null) {
//            message = "cart item NOT in database";
//            cartItem = cartItemRepository.save(cartItem);
//        } else {
//            message = "cart item in database";
//            cartItem.setQuantity(cartItem.getQuantity() + cartItem1.getQuantity());
//            cartItem = cartItemRepository.save(cartItem);
//
//        }


        String collect = cartItemRepository.findAllByCart(cart).stream()
                .map(CartItem::toString)
                .collect(Collectors.joining(",<br>"));
        return cart.toString() + "<br>" + cartItem.toString() + "<br><br>" + product + "<br><br>" +
                  "<br><br>" + collect;

    }
}
