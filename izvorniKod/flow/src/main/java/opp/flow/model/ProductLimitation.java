package opp.flow.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class ProductLimitation {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column( nullable = false)
    @NotNull
    private String username;

    @Column( nullable = false)
    @NotNull
    private String productName;

    public ProductLimitation(@NotNull String username, @NotNull String productName) {
        this.username = username;
        this.productName = productName;
    }

    public ProductLimitation() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(Long productID) {
        this.productName = productName;
    }




}