package opp.flow.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.lang.reflect.Field;

@Entity
public class ProductCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    @NotNull
    private String name;

    public ProductCategory(@NotNull String name) {
        this.name = name;
    }

    public ProductCategory() {
    }

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

    @Override
    public String toString() {
        return "ProductCategory{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public void replaceAttributes(ProductCategory update) throws IllegalArgumentException, IllegalAccessException {
        Field[] fields = update.getClass().getDeclaredFields();
        for(Field f:fields) {
            if(f.get(update)!=null) {
                f.set(this, f.get(update));
            }
        }
    }
}
