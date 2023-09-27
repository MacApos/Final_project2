package pl.coderslab.final_project.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.final_project.service.CategoryRepository;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class HomeController {
    CategoryRepository categoryRepository;

    public HomeController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @RequestMapping("/")
    public String home(Model model, HttpSession session) {
        return "home/home";
    }

    @ModelAttribute("allCategoriesNames")
    public List<String> allCategoriesNames() {
        return categoryRepository.findAllCategoryNames();
    }

}