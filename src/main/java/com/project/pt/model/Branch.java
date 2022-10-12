package com.project.pt.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "BRANCH")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Branch extends BaseEntity{

    private String name;
    private String description;


}
