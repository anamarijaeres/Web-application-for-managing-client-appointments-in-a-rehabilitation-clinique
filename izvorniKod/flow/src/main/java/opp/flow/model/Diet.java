package opp.flow.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.lang.reflect.Field;

@Entity
public class Diet {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String description;

    @Column(unique = true, nullable = false)
    @NotNull
    private String username;

    public Diet(String description, @NotNull String username) {
        this.description = description;
        this.username = username;
    }

    public Diet() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "Diet{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", username='" + username + '\'' +
                '}';
    }

    public void replaceAttributes(Diet update) throws IllegalArgumentException, IllegalAccessException {
        Field[] fields = update.getClass().getDeclaredFields();
        for(Field f:fields) {
            if(f.get(update)!=null) {
                f.set(this, f.get(update));
            }
        }
    }
}
