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

@Entity(name = "topics")
@Table(name = "topics",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_name", columnNames = {"name"}),
                @UniqueConstraint(name = "uk_slug", columnNames = {"slug"})

        },
        indexes = {
                @Index(name = "idx_name", columnList = "name")
        }
)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long topicId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "slug", nullable = false)
    private String slug;

    @Column(name = "description")
    private String description;


    @ManyToMany(mappedBy = "topics")
    private List<Problem> problems;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdDate;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedDate;
}
