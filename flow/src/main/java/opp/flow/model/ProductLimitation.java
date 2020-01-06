package opp.flow.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class ProductLimitation {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    @NotNull
    private String username;

    @Column(unique = true, nullable = false)
    @NotNull
    private Long productID;

    public ProductLimitation(@NotNull String username, @NotNull Long productID) {
        this.username = username;
        this.productID = productID;
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

    public Long getProductID() {
        return productID;
    }

    public void setProductID(Long productID) {
        this.productID = productID;
    }

    @Override
    public String toString() {
        return "ProductLimitation{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", productID=" + productID +
                '}';
    }


}
