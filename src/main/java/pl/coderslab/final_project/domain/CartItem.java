package pl.coderslab.final_project.domain;

import javax.persistence.*;
import javax.validation.constraints.Min;

@Entity
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "integer default 1")
    @Min(value = 1)
    private Integer quantity=1;
    @ManyToOne
    private Product product;
    @ManyToOne
    private Cart cart;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product products) {
        this.product = products;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", products=" + product +
                ", cart=" + cart +
                '}';
    }
}
