package pl.coderslab.final_project.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import pl.coderslab.final_project.repository.CategoryRepository;

import java.util.HashMap;
import java.util.List;

@ControllerAdvice
public class ControllerAdvisor {
    CategoryRepository categoryRepository;

    public ControllerAdvisor(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public String removeAccents(String input) {
        return StringUtils.stripAccents(input);
    }

    @ModelAttribute("allCategoriesNames")
    public HashMap<String, String> allCategoriesNames(Model model) {
        HashMap<String, String> allCategoriesNames = new HashMap<>();
        List<String> findAllCategoriesNames = categoryRepository.findAllCategoriesNames();

        findAllCategoriesNames.forEach(category -> allCategoriesNames.put(
                category, removeAccents(category.replaceAll(" ", "-"))));
        return allCategoriesNames;
    }
}
