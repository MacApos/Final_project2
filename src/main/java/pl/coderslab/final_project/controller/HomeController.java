package pl.coderslab.final_project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.final_project.repository.CategoryRepository;

import javax.servlet.http.HttpSession;

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

//    @ModelAttribute("allCategoriesNames")
//    public HashMap<String, String> allCategoriesNames(Model model) {
//        HashMap<String, String> allCategoriesNames = new HashMap<>();
//        List<String> findAllCategoriesNames = categoryRepository.findAllCategoriesNames();
//
//        findAllCategoriesNames.forEach(category -> allCategoriesNames.put(
//                category, removeAccents(category.replaceAll(" ", "-"))));
//        return allCategoriesNames;
//    }

}