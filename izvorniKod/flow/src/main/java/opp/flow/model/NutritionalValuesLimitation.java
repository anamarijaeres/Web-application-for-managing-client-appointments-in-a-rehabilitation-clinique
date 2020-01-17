package opp.flow.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.lang.reflect.Field;

@Entity
public class NutritionalValuesLimitation {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    @NotNull
    private String username;

    private double energyLimit;
    private double fatLimit;
    private double saturatedFattyAcidsLimit;
    private double carbohydratesLimit;
    private double sugarsLimit;
    private double proteinLimit;
    private double saltLimit;

    public NutritionalValuesLimitation(@NotNull String username, double energyLimit, double fatLimit, double saturatedFattyAcidsLimit, double carbohydratesLimit, double sugarsLimit, double proteinLimit, double saltLimit) {
        this.username = username;
        this.energyLimit = energyLimit;
        this.fatLimit = fatLimit;
        this.saturatedFattyAcidsLimit = saturatedFattyAcidsLimit;
        this.carbohydratesLimit = carbohydratesLimit;
        this.sugarsLimit = sugarsLimit;
        this.proteinLimit = proteinLimit;
        this.saltLimit = saltLimit;
    }

    public NutritionalValuesLimitation() {
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

    public double getEnergyLimit() {
        return energyLimit;
    }

    public void setEnergyLimit(double energyLimit) {
        this.energyLimit = energyLimit;
    }

    public double getFatLimit() {
        return fatLimit;
    }

    public void setFatLimit(double fatLimit) {
        this.fatLimit = fatLimit;
    }

    public double getSaturatedFattyAcidsLimit() {
        return saturatedFattyAcidsLimit;
    }

    public void setSaturatedFattyAcidsLimit(double saturatedFattyAcidsLimit) {
        this.saturatedFattyAcidsLimit = saturatedFattyAcidsLimit;
    }

    public double getCarbohydratesLimit() {
        return carbohydratesLimit;
    }

    public void setCarbohydratesLimit(double carbohydratesLimit) {
        this.carbohydratesLimit = carbohydratesLimit;
    }

    public double getSugarsLimit() {
        return sugarsLimit;
    }

    public void setSugarsLimit(double sugarsLimit) {
        this.sugarsLimit = sugarsLimit;
    }

    public double getProteinLimit() {
        return proteinLimit;
    }

    public void setProteinLimit(double proteinLimit) {
        this.proteinLimit = proteinLimit;
    }

    public double getSaltLimit() {
        return saltLimit;
    }

    public void setSaltLimit(double saltLimit) {
        this.saltLimit = saltLimit;
    }

    @Override
    public String toString() {
        return "NutritionalValues{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", energyLimit=" + energyLimit +
                ", fatLimit=" + fatLimit +
                ", saturatedFattyAcidsLimit=" + saturatedFattyAcidsLimit +
                ", carbohydratesLimit=" + carbohydratesLimit +
                ", sugarsLimit=" + sugarsLimit +
                ", proteinLimit=" + proteinLimit +
                ", saltLimit=" + saltLimit +
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
