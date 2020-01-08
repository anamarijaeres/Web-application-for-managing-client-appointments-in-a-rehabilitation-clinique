package opp.flow.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.lang.reflect.Field;
import java.util.Arrays;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    @NotNull
    private String name;

    private double mass;
    @Lob
    private Byte[] image;
    //barkod
    //alergeni
    private String category;

    private double energy;
    private double fat;
    private double saturatedFattyAcids;
    private double carbohydrates;
    private double sugars;
    private double protein;
    private double salt;

    public Product(@NotNull String name, double mass, String category, double energy, double fat, double saturatedFattyAcids, double carbohydrates, double sugars, double protein, double salt) {
        this.name = name;
        this.mass = mass;
       // this.image = image;
        this.category = category;
        this.energy = energy;
        this.fat = fat;
        this.saturatedFattyAcids = saturatedFattyAcids;
        this.carbohydrates = carbohydrates;
        this.sugars = sugars;
        this.protein = protein;
        this.salt = salt;
    }

    public Product() {
    }

    public double getEnergy() {
        return energy;
    }

    public void setEnergy(double energy) {
        this.energy = energy;
    }

    public double getFat() {
        return fat;
    }

    public void setFat(double fat) {
        this.fat = fat;
    }

    public double getSaturatedFattyAcids() {
        return saturatedFattyAcids;
    }

    public void setSaturatedFattyAcids(double saturatedFattyAcids) {
        this.saturatedFattyAcids = saturatedFattyAcids;
    }

    public double getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(double carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    public double getSugars() {
        return sugars;
    }

    public void setSugars(double sugars) {
        this.sugars = sugars;
    }

    public double getProtein() {
        return protein;
    }

    public void setProtein(double protein) {
        this.protein = protein;
    }

    public double getSalt() {
        return salt;
    }

    public void setSalt(double salt) {
        this.salt = salt;
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

    public double getMass() {
        return mass;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    public Byte[] getImage() {
        return image;
    }

    public void setImage(Byte[] image) {
        this.image = image;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", mass=" + mass +
                ", image=" + Arrays.toString(image) +
                ", category='" + category + '\'' +
                ", energy=" + energy +
                ", fat=" + fat +
                ", saturatedFattyAcids=" + saturatedFattyAcids +
                ", carbohydrates=" + carbohydrates +
                ", sugars=" + sugars +
                ", protein=" + protein +
                ", salt=" + salt +
                '}';
    }

    public void replaceAttributes(Product update) throws IllegalArgumentException, IllegalAccessException {
        Field[] fields = update.getClass().getDeclaredFields();
        for(Field f:fields) {
            if(f.get(update)!=null) {
                f.set(this, f.get(update));
            }
        }
    }
}
