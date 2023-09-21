package pl.coderslab.final_project.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class CartOrder {
    @Id
    private Long id;
    private int orderNumber;
    @OneToOne
    @JoinColumn
    private Cart cart;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
