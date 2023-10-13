package pl.coderslab.final_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.coderslab.final_project.domain.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByCategoryId(Long categoryId);

    @Query("SELECT DISTINCT p.type FROM Product p")
    List<String> findAllProductsTypes();

}
