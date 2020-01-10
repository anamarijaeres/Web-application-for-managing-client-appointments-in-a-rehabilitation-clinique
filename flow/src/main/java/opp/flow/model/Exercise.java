package opp.flow.model;

import javax.persistence.*;

@Entity
public class Exercise {

    @Id
    private String name;
    @Lob
    private Byte[] image;
    private String description;
    private double burnedCaloriesEasy;
    private double burnedCaloriesNormal;
    private double burnedCaloriesHard;

    public Exercise(String name, Byte[] image, String description, double burnedCaloriesEasy, double burnedCaloriesNormal, double burnedCaloriesHard) {
        this.name=name;
        this.image = image;
        this.description = description;
        this.burnedCaloriesEasy = burnedCaloriesEasy;
        this.burnedCaloriesNormal = burnedCaloriesNormal;
        this.burnedCaloriesHard = burnedCaloriesHard;
    }

    public Exercise () {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Byte[] getImage() {
        return image;
    }

    public void setImage(Byte[] image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getBurnedCaloriesEasy() {
        return burnedCaloriesEasy;
    }

    public void setBurnedCaloriesEasy(double burnedCaloriesEasy) {
        this.burnedCaloriesEasy = burnedCaloriesEasy;
    }

    public double getBurnedCaloriesNormal() {
        return burnedCaloriesNormal;
    }

    public void setBurnedCaloriesNormal(double burnedCaloriesNormal) {
        this.burnedCaloriesNormal = burnedCaloriesNormal;
    }

    public double getBurnedCaloriesHard() {
        return burnedCaloriesHard;
    }

    public void setBurnedCaloriesHard(double burnedCaloriesHard) {
        this.burnedCaloriesHard = burnedCaloriesHard;
    }
}
