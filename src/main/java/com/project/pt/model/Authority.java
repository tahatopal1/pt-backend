package com.project.pt.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "AUTHORITY")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
public class Authority extends BaseEntity{

    private String name;
    private String description;

    @ManyToMany(mappedBy = "authorities")
    private Set<User> users;

}
