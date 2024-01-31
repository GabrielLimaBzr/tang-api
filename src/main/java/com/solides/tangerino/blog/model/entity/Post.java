package com.solides.tangerino.blog.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.solides.tangerino.blog.model.enums.PostStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.Set;

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

    @Column(name = "STR_CONTENT", nullable = true)
    private String content;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "INT_ID_USER", referencedColumnName = "INT_ID_USER", nullable = false, updatable = false)
    private User user;

    @Column(name = "INT_ROLE", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private PostStatus status;

    @CreatedDate
    @JsonIgnore
    @Column(name = "DT_CREATED")
    private LocalDateTime created;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "TB_POST_FILE",
            joinColumns = @JoinColumn(name = "INT_ID_POST"),
            inverseJoinColumns = @JoinColumn(name = "INT_ID_FILE"))
    private Set<File> postFiles;

}
