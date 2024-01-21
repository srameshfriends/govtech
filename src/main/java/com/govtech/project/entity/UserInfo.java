package com.govtech.project.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity(name = "user_info")
@JsonRootName("user_info")
public class UserInfo  extends BaseEntity {
    @JsonProperty("given_name")
    @Column(name = "given_name")
    private String givenName;

    @Override
    public String toString() {
        return "{" +
                "id='" + getId() + '\'' +
                "rev='" + getRev() + '\'' +
                ", givenName='" + givenName + '\'' +
                '}';
    }
}
