package pl.coderslab.final_project.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.final_project.domain.CartOrder;
@Repository
public interface CartOrderRepository extends JpaRepository<CartOrder, Long> {
}
