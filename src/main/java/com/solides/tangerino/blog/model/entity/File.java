package com.solides.tangerino.blog.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "TB_FILE")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "INT_ID_FILE", nullable = false)
    private long id;

    @Column(name = "STR_NAME", length = 200)
    private String name;

    @Column(name = "STR_TYPE", length = 100)
    private String type;

    @Column(name = "BIN_FILE", nullable = false, length = 50000000)
    private byte[] file;

    public File(String originalFilename, String contentType, byte[] bytes) {
        this.name = originalFilename;
        this.type = contentType;
        this.file = bytes;
    }
}
