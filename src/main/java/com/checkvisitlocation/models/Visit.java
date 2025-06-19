package com.checkvisitlocation.models;

import com.checkvisitlocation.visitors.DataExportVisitor;
import jakarta.persistence.*;

import java.time.LocalDate;

/**
 * Сутність "Відвідування" (Visit).
 * Містить інформацію про відвідування користувачем певної локації.
 */
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

    /** Ідентифікатор відвідування. */
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    /** Користувач, який здійснив відвідування. */
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    /** Локація, яку відвідав користувач. */
    public Location getLocation() { return location; }
    public void setLocation(Location location) { this.location = location; }

    /** Дата відвідування. */
    public LocalDate getVisitDate() { return visitDate; }
    public void setVisitDate(LocalDate visitDate) { this.visitDate = visitDate; }

    /** Рейтинг, поставлений користувачем (1-5). */
    public int getRating() { return rating; }
    public void setRating(int rating) { this.rating = rating; }

    /** Враження користувача від локації. */
    public String getImpressions() { return impressions; }
    public void setImpressions(String impressions) { this.impressions = impressions; }

    /**
     * Конструктор за замовчуванням.
     */
    public Visit() {}

    /**
     * Приймає відвідувача для експорту (патерн Visitor).
     * @param visitor відвідувач
     * @return результат експорту
     */
    public String accept(DataExportVisitor visitor) {
        return visitor.visit(this);
    }
}