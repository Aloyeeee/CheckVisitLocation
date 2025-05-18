package com.checkvisitlocation.models;

import com.checkvisitlocation.visitors.DataExportVisitor;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "visits")
public class Visit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;

    @Column(name = "visit_date", nullable = false)
    private LocalDate visitDate;

    @Column(nullable = false)
    private int rating;

    @Column(length = 1000)
    private String impressions;

    // Конструктори, гетери та сетери
    public Visit() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public Location getLocation() { return location; }
    public void setLocation(Location location) { this.location = location; }

    public LocalDate getVisitDate() { return visitDate; }
    public void setVisitDate(LocalDate visitDate) { this.visitDate = visitDate; }

    public int getRating() { return rating; }
    public void setRating(int rating) { this.rating = rating; }

    public String getImpressions() { return impressions; }
    public void setImpressions(String impressions) { this.impressions = impressions; }

    // Додано метод accept для патерну Visitor
    public String accept(DataExportVisitor visitor) {
        return visitor.visit(this);
    }
}