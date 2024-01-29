package com.solides.tangerino.blog.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "TB_POST")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "INT_ID_POST", nullable = false)
    private Long id;

    @Column(name = "STR_TITLE", nullable = false, length = 200)
    private String title;

    @Column(name = "STR_CONTENT", nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "INT_ID_USER", referencedColumnName = "INT_ID_USER", nullable = false, insertable = false, updatable = false)
    private User user;

    @CreatedDate
    @JsonIgnore
    @Column(name = "DT_CREATED")
    private LocalDateTime created;

}
