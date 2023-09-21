package pl.coderslab.final_project.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.final_project.domain.Category;


import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query("select c.name from Category c")
    List<String> findAllNames();
}
