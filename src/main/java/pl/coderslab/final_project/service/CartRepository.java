package pl.coderslab.final_project.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.coderslab.final_project.domain.Cart;
import pl.coderslab.final_project.domain.User;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public interface CartRepository extends JpaRepository<Cart, Long> {
    @Modifying
    @Query("UPDATE Cart c SET c.itemsQuantity=(SELECT SUM(ci.quantity) FROM CartItem ci where ci.cart.id=?1)" +
            " WHERE c.id=?1")
    void updateCartItemQuantity(Long id);

    Optional<Cart> findByUser(User user);
}
