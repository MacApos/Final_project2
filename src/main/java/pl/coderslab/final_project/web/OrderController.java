package pl.coderslab.final_project.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.final_project.domain.Cart;
import pl.coderslab.final_project.domain.CartItem;
import pl.coderslab.final_project.service.CartItemRepository;
import pl.coderslab.final_project.service.CartRepository;
import pl.coderslab.final_project.service.CategoryRepository;

import java.util.List;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/order")
public class OrderController {
    CategoryRepository categoryRepository;
    CartItemRepository cartItemRepository;
    CartRepository cartRepository;

    public OrderController(CategoryRepository categoryRepository, CartItemRepository cartItemRepository,
                           CartRepository cartRepository) {
        this.categoryRepository = categoryRepository;
        this.cartItemRepository = cartItemRepository;
        this.cartRepository = cartRepository;
    }

    @RequestMapping("")
    @ResponseBody
    public String order(@RequestParam("cartItemId") Long cartItemId,
                        @RequestParam("cartId") Long cartId) {
        if (cartItemId != null) {
            CartItem cartItem = cartItemRepository.findById(cartItemId).orElse(null);
            if (cartItem != null) {
                return cartItem.toString();
            }
        }

        if (cartId != null) {
            Cart cart = cartRepository.findById(cartId).orElse(null);
            if (cart != null) {
                List<CartItem> cartItems = cartItemRepository.findAllByCart(cart);
                return cartItems.stream().map(CartItem::toString).collect(Collectors.joining("<br>"));
            }
        }
        return "null";
    }


}