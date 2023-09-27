package pl.coderslab.final_project.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class LogoutController {
    @RequestMapping("/logout")
    public String logout(HttpSession session, Model model) {
        session.removeAttribute("loggedUser");
        session.removeAttribute("cart");
        session.removeAttribute("admin");
        return "redirect:";
    }
}
