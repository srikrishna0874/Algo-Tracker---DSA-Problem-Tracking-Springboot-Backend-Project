package com.algotracker.AlgotrackerProject.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "problems")
@Table(name = "problems",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_link", columnNames = {"link"})
        },
        indexes = {
                @Index(name = "idx_title", columnList = "title")
        }
)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Problem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long problemId;

    @Column(name = "title", comment = "Used to store problem name", nullable = false)
    private String title;

    @Column(name = "description", comment = "Used to store description of the problem")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "difficulty", nullable = false, comment = "Used to store difficulty level of the problem")
    private Difficulty difficulty;

    @Column(name = "link")
    private String link;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "problem_topics",
            joinColumns = @JoinColumn(name = "problem_id"),
            inverseJoinColumns = @JoinColumn(name = "topic_id")
    )
    private List<Topic> topics;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime created;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updated;
}
