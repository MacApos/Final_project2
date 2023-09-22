package pl.coderslab.final_project.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.final_project.domain.Cart;

@Repository

public interface CartRepository extends JpaRepository<Cart, Long> {
}
