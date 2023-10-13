package pl.coderslab.final_project.domain;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotNull
    @NotBlank
    @Size(min = 2)
    private String name;

    @Column(nullable = false)
    @NotNull
    @NotBlank
    @Size(min = 2)
    private String description;

    private String size=null;
    private String color=null;
    private String scent=null;

    @DecimalMin("0.01")
    @NotNull
    @Column(scale = 2, nullable = false)
    private BigDecimal price;

    @NotNull
    @NotBlank
    private String img;

    @Column(nullable = false)
    @NotNull
    @NotBlank
    @Size(min = 2)
    private String type;

    @ManyToOne
    @JoinColumn(name = "product_category", nullable = false)
    private Category category;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getScent() {
        return scent;
    }

    public void setScent(String scent) {
        this.scent = scent;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", size='" + size + '\'' +
                ", color='" + color + '\'' +
                ", scent='" + scent + '\'' +
                ", type='" + type + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", img='" + img + '\'' +
                ", category=" + category +
                '}';
    }
}
