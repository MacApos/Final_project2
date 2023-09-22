package pl.coderslab.final_project.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.final_project.domain.Product;
import pl.coderslab.final_project.service.CategoryRepository;
import pl.coderslab.final_project.service.ProductRepository;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {
    ProductRepository productRepository;
    CategoryRepository categoryRepository;

    public ProductController(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @RequestMapping("/rope")
    public String showRopeProducts(Model model) {
        List<Product> allProducts = productRepository.findAllByCategoryId(1L);
        model.addAttribute("allProducts", allProducts);
        return "product/allProducts";
    }

    @RequestMapping("/wool")
    public String showWoolProducts(Model model) {
        List<Product> allProducts = productRepository.findAllByCategoryId(2L);
        model.addAttribute("allProducts", allProducts);
        return "product/allProducts";
    }

    @RequestMapping("/lavender")
    public String showLavenderProducts(Model model) {
        List<Product> allProducts = productRepository.findAllByCategoryId(3L);
        model.addAttribute("allProducts", allProducts);
        return "product/allProducts";
    }

    @RequestMapping("/{id}")
    public String showProduct(@PathVariable("id") Long id, Model model) {
        try {
            Product product = productRepository.findById(id).orElseThrow(IllegalArgumentException::new);
            model.addAttribute("product", product);
            return "product/productDetails";
        } catch (IllegalArgumentException e){
            return "home/404";
        }
    }

    @RequestMapping("/product/error")
    @ResponseBody
    public String productNotFound() {
        return "404";
    }

    public Product getProduct(Long id) {
        try {
            return productRepository.findById(id).orElseThrow(() -> new Exception("No product found"));
        } catch (Exception e) {
            productNotFound();
            return null;
        }
    }
}

