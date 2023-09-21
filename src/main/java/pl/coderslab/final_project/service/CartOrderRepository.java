package pl.coderslab.final_project.service;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.final_project.domain.CartOrder;

public interface CartOrderRepository extends JpaRepository<CartOrder, Long> {
}
