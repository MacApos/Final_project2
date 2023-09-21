package pl.coderslab.final_project.service;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.final_project.domain.Cart;


public interface CartRepository extends JpaRepository<Cart, Long> {
}
