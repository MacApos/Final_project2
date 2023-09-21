package pl.coderslab.final_project.service;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.final_project.domain.Product;


public interface ProductRepository extends JpaRepository<Product, Long> {
}
