package pl.coderslab.final_project.domain;

import javax.persistence.*;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String size;
    private String color;
    private String scent;
    private String description;
    @ManyToOne
    @JoinColumn(name = "product_category")
    private Category category;
    public void setId(Long id) {
        this.id = id;
    }
    public Long getId() {
        return id;
    }
}
