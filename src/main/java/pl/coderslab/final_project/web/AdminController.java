package pl.coderslab.final_project.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.final_project.domain.Category;
import pl.coderslab.final_project.domain.Product;
import pl.coderslab.final_project.service.CategoryRepository;
import pl.coderslab.final_project.service.ProductRepository;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("admin")
public class AdminController {
    ProductRepository productRepository;
    CategoryRepository categoryRepository;

    public AdminController(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/addProduct")
    public String insertProductData(Model model) {
        findTypesAndCategories(model);
        model.addAttribute("product", new Product());
        return "admin/addProduct";
    }

    public void findTypesAndCategories(Model model){
        List<String> allProductTypes = productRepository.findAllProductsTypes();
        List<String> allCategoriesNames = categoryRepository.findAllCategoryNames();
        model.addAttribute("allProductTypes", allProductTypes);
        model.addAttribute("allCategoriesNames", allCategoriesNames);
    }

    @PostMapping("/addProduct")
//    @ResponseBody
    public String processProductData(@Valid Product product, BindingResult result,
                                     Model model, HttpServletRequest request) {
        model.addAttribute("product", product);
        if (result.hasErrors()){
            findTypesAndCategories(model);
            return "admin/addProduct";
        }

        if(product.getColor().isBlank()){
            product.setColor(null);
        }

        if(product.getScent().isBlank()){
            product.setScent(null);
        }

        Category category = categoryRepository.findByName(request.getParameter("category")).orElse(null);
        product.setCategory(category);
        productRepository.save(product);
        return product.toString();
    }

}
