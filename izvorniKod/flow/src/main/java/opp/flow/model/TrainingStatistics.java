package opp.flow.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class TrainingStatistics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String username;
    private LocalDate date;
    private double burnedCalories;

    public TrainingStatistics(Long id, String username, LocalDate date, double burnedCalories) {

        this.username = username;
        this.date = date;
        this.burnedCalories = burnedCalories;
    }

    public TrainingStatistics () {

    }

    public Long getId() {
        return id;
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

    public double getBurnedCalories() {
        return burnedCalories;
    }

    public void setBurnedCalories(double burnedCalories) {
        this.burnedCalories = burnedCalories;
    }
}
