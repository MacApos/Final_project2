package pl.coderslab.final_project.others
        ;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.final_project.domain.*;
import pl.coderslab.final_project.service.*;

import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.http.HttpRequest;
import java.util.*;

public class CartControllerTest1_2 {
    CartRepository cartRepository;
    ProductRepository productRepository;
    CartItemRepository cartItemRepository;
    UserRepository userRepository;
    CategoryRepository categoryRepository;

    public CartControllerTest1_2(CartRepository cartRepository, ProductRepository productRepository,
                          CartItemRepository cartItemRepository, UserRepository userRepository,
                          CategoryRepository categoryRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.cartItemRepository = cartItemRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }

    @RequestMapping(value = "/{productId}", params = "add")
    public void add(@Valid CartItem cartItem, @PathVariable("productId") Long productId,
                    BindingResult result, HttpServletRequest request,
                    HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("cartItem", cartItem);
        request.setAttribute("result", result.hasErrors());
        request.getServletContext().getRequestDispatcher(String.format("/cart/addToCart/%s", productId))
                .forward(request, response);
    }

    @RequestMapping(value = "/{productId}", params = "order")
    public void order(@Valid CartItem cartItem, @PathVariable("productId") Long productId,
                      BindingResult result, HttpServletRequest request,
                      HttpServletResponse response, Model model) throws ServletException, IOException {
        Product product = productRepository.findById(productId).orElseThrow(IllegalArgumentException::new);
        if (result.hasErrors()) {
            model.addAttribute("cartItem", cartItem);
            model.addAttribute("product", product);
//            return "product/productDetails";
        }

        request.setAttribute("cartItem", cartItem);
        request.getServletContext().getRequestDispatcher(String.format("/cart/addToCart/%s", productId))
                .forward(request, response);
    }

    @RequestMapping(value = "/addToCart/{productId}")
    public String addToCart(@PathVariable("productId") Long productId, Model model, HttpSession session,
                            HttpServletRequest request) {
        CartItem cartItem = (CartItem) request.getAttribute("cartItem");
        boolean result = (boolean) request.getAttribute("result");
        Product product = productRepository.findById(productId).orElseThrow(IllegalArgumentException::new);
        if (result) {
            model.addAttribute("product", product);
            return "product/productDetails";
        }
        Cart cart = new Cart();


        // dodajesz produkt do koszyka
        if (session.getAttribute("loggedUser") != null) {// jesteś zalogowany
            User user = (User) session.getAttribute("loggedUser");
            // czy masz na koncie koszyk
            Cart existingCart = cartRepository.findByUser(user).orElse(null);
            if (existingCart == null) { //nie masz na koncie koszyka
                cart.setUser(user);  // przypisujesz usera do aktualnego koszyka
            } else { // masz na koncie koszyk
                cart = existingCart; //ustawiasz aktualny koszyk na koszyk usera
            }
        } else if (session.getAttribute("cart") != null) { //nie jesteś zalogowany i masz już produkty w koszyku
            cart = (Cart) model.getAttribute("cart"); //ustawiasz aktualny koszyk na istniejący koszyk
        }
        //nie jesteś zalogowany i nie masz produktów w koszyku - program działa dalej

        cart.setItemsQuantity(cart.getItemsQuantity() + cartItem.getQuantity());
        cartRepository.save(cart);
        CartItem existingCartItem = cartItemRepository.findFirstByProductAndCart(product, cart).orElse(null);

        if (existingCartItem != null) {
            existingCartItem.setQuantity(existingCartItem.getQuantity() + cartItem.getQuantity());
            cartItem = existingCartItem;
        }

        cartItem.setCart(cart);
        cartItem.setProduct(product);

        cartRepository.save(cart);
        cartItemRepository.save(cartItem);
        model.addAttribute("cart", cart);

        List<Product> allProducts = productRepository.findAllByCategoryId(product.getCategory().getId());
        model.addAttribute("allProducts", allProducts);
        return "product/allProducts";
    }

    @RequestMapping("/cartDetails")
    public String showCart(HttpSession session, Model model) {
        List<CartItem> cartItems;
        LinkedHashMap<CartItem, Product> map = new LinkedHashMap<>();
        BigDecimal totalPriceForCart = BigDecimal.valueOf(0L);
        if (session.getAttribute("cart") != null) {
            Cart cart = (Cart) session.getAttribute("cart");
            cartItems = cartItemRepository.findAllByCart(cart);
            cartItems.forEach(cartItem -> map.put(cartItem,
                    productRepository.findById(cartItem.getProduct().getId()).orElse(null)));
            totalPriceForCart = cartRepository.getTotalPriceForCart(cart);
        }
        model.addAttribute("cartItems", map);
        model.addAttribute("totalPriceForCart", totalPriceForCart);
        return "cart/cartDetails";
    }

    @RequestMapping("/deleteFromCart")
    public String deleteFromCart(@RequestParam("id") Long id, Model model) {
        CartItem cartItem = cartItemRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        Cart cart = cartRepository.findById(cartItem.getCart().getId())
                .orElseThrow(IllegalArgumentException::new);
        cart.setItemsQuantity(cart.getItemsQuantity() - cartItem.getQuantity());
        cartRepository.save(cart);
        model.addAttribute("cart", cart);
        cartItemRepository.deleteById(id);
        return "redirect:cartDetails";
    }

//    @RequestMapping(value = "/{productId}", params = "order")
//    @ResponseBody
//    public String order(@Valid CartItem cartItem, BindingResult result,
//                        @PathVariable("productId") Long productId, Model model, HttpSession session) {
//        Product product = productRepository.findById(productId).orElseThrow(IllegalArgumentException::new);
//        if (result.hasErrors()) {
//            model.addAttribute("product", product);
//            return "product/productDetails";
//        }
//
//        Cart cart = new Cart();
//        if (session.getAttribute("cart") != null) { //nie jesteś zalogowany i masz już produkty w koszyku
//            cart = (Cart) model.getAttribute("cart"); //ustawiasz aktualny koszyk na istniejący koszyk
//        }
//
//
//
//        return "order";
//    }
}