package opp.flow.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class ConsumedProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String username;
    private LocalDate date;
    private String productName;
    private String mass;

    public ConsumedProduct(Long id) {
        this.id = id;
    }

    public ConsumedProduct() {
    }

    public ConsumedProduct(String username, LocalDate date, String productName, String mass) {
        this.username=username;
        this.date=date;
        this.productName=productName;
        this.mass=mass;
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getMass() {
        return mass;
    }

    public void setMass(String mass) {
        this.mass = mass;
    }
    @Override
    public String toString() {
        return "Product" + productName+
                "username" + username ;
    }
}
