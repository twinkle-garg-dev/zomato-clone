package com.zomato.auth.entity;

import com.zomato.auth.dto.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@Entity
@Table(name = "user_role")
@SuperBuilder
public class Role extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;
    private String description;
    private String role;
}
