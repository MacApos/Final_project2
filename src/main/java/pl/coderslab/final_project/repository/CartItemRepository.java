package pl.coderslab.final_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.coderslab.final_project.domain.Cart;
import pl.coderslab.final_project.domain.CartItem;
import pl.coderslab.final_project.domain.Product;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    @Modifying
    @Query("UPDATE CartItem ci SET ci.cart=?1 WHERE ci.cart=?2")
    void updateAllCartItemsOldCartToNewCart(Cart newCart, Cart oldCart);

//    @Modifying
//    @Query("""
//            UPDATE CartItem ci
//            SET ci.cart = case when (?2, ?2 + 1)
//            where ci.cart = ?4
//            """)
//    void updateAllCartItemsOldCartToNewCart(Integer quantity, Cart updatedCart, Cart newCart, Cart oldCart);

    List<CartItem> findAllByCart(Cart cart);

    Optional<CartItem> findFirstByProductAndCart(Product product, Cart cart);

}
