package pl.coderslab.final_project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.final_project.domain.CartItem;
import pl.coderslab.final_project.domain.Category;
import pl.coderslab.final_project.domain.Product;
import pl.coderslab.final_project.repository.CartItemRepository;
import pl.coderslab.final_project.repository.CategoryRepository;
import pl.coderslab.final_project.repository.ProductRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/product")
public class ProductController {
    ProductRepository productRepository;
    CategoryRepository categoryRepository;
    CartItemRepository cartItemRepository;

    public ProductController(ProductRepository productRepository, CategoryRepository categoryRepository,
                             CartItemRepository cartItemRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.cartItemRepository = cartItemRepository;
    }

    @GetMapping("/{path}")
    public String showAllProducts(@PathVariable("path") String path, Model model) {
        LinkedHashMap<String, String> allCategoriesNames = (LinkedHashMap<String, String>)
                model.getAttribute("allCategoriesNames");
        List<Product> allProductsByCategory = productRepository.findAllByCategoryName(allCategoriesNames.get(path));
        model.addAttribute("allProducts", allProductsByCategory);
        model.addAttribute("path", path);
        return "product/allProducts";
    }

    @RequestMapping("/rope")
    public String showRopeProducts(Model model, HttpServletRequest request) {
        List<Product> allProducts = productRepository.findAllByCategoryId(1L);
        model.addAttribute("allProducts", allProducts);
        model.addAttribute("path", request.getRequestURI());
        return "product/allProducts";
    }

    @RequestMapping("/wool")
    public String showWoolProducts(Model model, HttpServletRequest request) {
        List<Product> allProducts = productRepository.findAllByCategoryId(2L);
        model.addAttribute("allProducts", allProducts);
        model.addAttribute("path", request.getRequestURI());
        return "product/allProducts";
    }

    @RequestMapping("/lavender")
    public String showLavenderProducts(Model model, HttpServletRequest request) {
        List<Product> allProducts = productRepository.findAllByCategoryId(3L);
        model.addAttribute("allProducts", allProducts);
        model.addAttribute("path", request.getRequestURI());
        return "product/allProducts";
    }

    @GetMapping("/{path}/{id}")
    public String showProduct(@PathVariable("id") Long id, @PathVariable("path") String path, Model model) {
        try {
            Product product = productRepository.findById(id).orElseThrow(IllegalArgumentException::new);
            model.addAttribute("product", product);
            model.addAttribute("cartItem", new CartItem());
            return "product/productDetails";
        } catch (IllegalArgumentException e) {
            return "home/404";
        }
    }

    @RequestMapping("/product/error")
    @ResponseBody
    public String productNotFound() {
        return "404";
    }
}

