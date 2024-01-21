package com.govtech.project.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Setter
@Getter
@MappedSuperclass
public class BaseEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = -9084241049053575624L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonProperty("id")
    private Long id;

    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "rev")
    @JsonProperty("rev")
    private int rev;

    @Column(name = "created_by")
    private Long createdBy;

    @Column(name = "updated_by")
    private Long updatedBy;

    @JsonProperty("created_on")
    @Column(name = "created_on")
    @Temporal(value = TemporalType.TIMESTAMP)
    private LocalDateTime createdOn;

    @JsonProperty("updated_on")
    @Column(name = "updated_on")
    @Temporal(value = TemporalType.TIMESTAMP)
    private LocalDateTime updatedOn;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseEntity that = (BaseEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
