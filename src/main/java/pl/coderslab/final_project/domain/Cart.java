package pl.coderslab.final_project.domain;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @CreationTimestamp
    @Column(columnDefinition = "datetime default now()")
    private Timestamp created;

    @Min(value = 0)
    @Column(columnDefinition = "integer default 0")
    private Integer itemsQuantity = 0;

    @OneToOne
    @JoinColumn(unique = true)
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public Timestamp getCreated() {
        return created;
    }

    public Integer getItemsQuantity() {
        return itemsQuantity;
    }

    public void setItemsQuantity(Integer itemsQuantity) {
        this.itemsQuantity = itemsQuantity;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", created=" + created +
                ", itemsQuantity=" + itemsQuantity +
                ", user=" + user +
                '}';
    }
}
