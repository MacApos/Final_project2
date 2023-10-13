package pl.coderslab.final_project.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import pl.coderslab.final_project.repository.CategoryRepository;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

@ControllerAdvice
public class ControllerAdvisor {
    public static LinkedHashMap<String, String> allCategoriesNames;

    CategoryRepository categoryRepository;

    public ControllerAdvisor(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public String normalizeInput(String input) {
        return StringUtils.stripAccents(input)
                .replaceAll(" ", "-")
                .toLowerCase();
    }

    public LinkedHashMap<String, String> findAllCategoriesNames() {
        allCategoriesNames = new LinkedHashMap<>();
        List<String> findAllCategoriesNames = categoryRepository.findAllCategoriesNames();
        findAllCategoriesNames.forEach(categoryName -> allCategoriesNames.put(normalizeInput(categoryName),
                categoryName));
        return allCategoriesNames;
    }

    @ModelAttribute("allCategoriesNames")
    public LinkedHashMap<String, String> allCategoriesNames(Model model) {
        return findAllCategoriesNames();
    }
}
