package pl.coderslab.final_project.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime created;
    @ManyToOne
    private CartItem cartItem;

}
