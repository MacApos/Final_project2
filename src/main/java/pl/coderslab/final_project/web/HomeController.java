package pl.coderslab.final_project.web;

import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.final_project.domain.Category;
import pl.coderslab.final_project.service.CategoryRepository;

import java.util.List;

@Controller("/")
public class HomeController {
    @RequestMapping("/")
    public String home(Model model) {
        return "home/home";
    }

}