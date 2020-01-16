package opp.flow.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class CategoryLimitation {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotNull
    private String username;

    @Column(nullable = false)
    @NotNull
    private String categoryName;

    public CategoryLimitation(@NotNull String username, @NotNull String categoryName) {
        this.username = username;
        this.categoryName = categoryName;
    }

    public CategoryLimitation() {
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

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }



}