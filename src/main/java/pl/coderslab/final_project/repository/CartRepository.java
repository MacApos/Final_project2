package pl.coderslab.final_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.coderslab.final_project.domain.Cart;
import pl.coderslab.final_project.domain.User;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Optional;

@Repository
@Transactional
public interface CartRepository extends JpaRepository<Cart, Long> {
    @Query("""
            SELECT sum(ci.quantity * p.price)
            FROM CartItem ci
                     JOIN Product p ON p = ci.product
            WHERE ci.cart = ?1
            """)
    BigDecimal getTotalPriceForCart(Cart cart);

    Optional<Cart> findByUser(User user);

}
