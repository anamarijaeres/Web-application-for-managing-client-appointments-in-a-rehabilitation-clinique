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
    private Long categoryID;

    public CategoryLimitation(@NotNull String username, @NotNull Long categoryID) {
        this.username = username;
        this.categoryID = categoryID;
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

    public Long getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(Long categoryID) {
        this.categoryID = categoryID;
    }

    @Override
    public String toString() {
        return "CategoryLimitation{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", categoryID=" + categoryID +
                '}';
    }


}
