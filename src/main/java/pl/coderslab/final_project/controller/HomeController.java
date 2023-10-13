package pl.coderslab.final_project.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.final_project.repository.CategoryRepository;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

@Controller
public class HomeController {
    CategoryRepository categoryRepository;

    public HomeController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public String removeAccents(String input) {
        return StringUtils.stripAccents(input);
    }

    @RequestMapping("/")
    public String home(Model model, HttpSession session) {
        return "home/home";
    }

}