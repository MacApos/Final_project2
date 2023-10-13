package pl.coderslab.final_project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.final_project.domain.Category;
import pl.coderslab.final_project.domain.Product;
import pl.coderslab.final_project.repository.CategoryRepository;
import pl.coderslab.final_project.repository.ProductRepository;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
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

    public void findTypesAndCategories(Model model) {
        List<String> allProductTypes = productRepository.findAllProductsTypes();
        List<String> allCategoriesNames = categoryRepository.findAllCategoriesNames();
        model.addAttribute("allProductTypes", allProductTypes);
        model.addAttribute("allCategoriesNames", allCategoriesNames);
    }

    @PostMapping("/addProduct")
    public String processProductData(@Valid Product product, BindingResult result,
                                     Model model, HttpServletRequest request) {
        model.addAttribute("product", product);
        findTypesAndCategories(model);

        String categoryName = request.getParameter("categoryName");
        Category category = categoryRepository.findByName(categoryName).orElse(null);
        boolean isDataValid = true;
        int id = 0;
        if (category == null) {
            String categoryMessage = checkCategory(categoryName);
            if (categoryMessage.isBlank()) {
                Category newCategory = new Category();
                newCategory.setName(categoryName);
                categoryRepository.save(newCategory);
                product.setCategory(newCategory);
            } else {
                model.addAttribute("categoryMessage", categoryMessage);
                isDataValid = false;
            }
        } else {
            id = category.getId().intValue();
        }
        if (result.hasErrors()) {
            isDataValid = false;
        }
        if (!isDataValid) {
            return "admin/addProduct";
        }

        String path = "/product/";
        switch (id) {
            case 1:
                path += "rope";
                break;
            case 2:
                path += "wool";
                break;
            case 3:
                path += "lavender";
                break;
            default:
                path = "";
        }

        product.setCategory(category);
        productRepository.save(product);
        return String.format("redirect:..%s", path);
    }

    private String checkCategory(String categoryName) {
        List<String> messages = new ArrayList<>();
        if (categoryName.isEmpty()) {
            messages.add("Category name can not be null.");
        }
        if (categoryName.isBlank()) {
            messages.add("Category name can not be empty.");
        }
        if (categoryName.length() < 2) {
            messages.add("Category name must be at least two letter long.");
        }
        return String.join("<br>", messages);
    }

}
