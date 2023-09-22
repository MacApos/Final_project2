package pl.coderslab.final_project.web;

import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import pl.coderslab.final_project.domain.Category;
import pl.coderslab.final_project.service.CategoryRepository;

import javax.servlet.http.HttpServletRequest;
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
//        if(session.getAttribute("admin")==null){
//            return "null";
//        } else {
//            model.addAttribute("admin", 0);
//            return (String) session.getAttribute("admin");
//        }
        return "home/home";
    }

    @ModelAttribute("allCategoriesNames")
    public List<String> allCategoriesNames() {
        return categoryRepository.findAllNames();
    }

}