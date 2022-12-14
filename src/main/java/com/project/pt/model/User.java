package com.project.pt.model;

import com.project.pt.model.constant.Gender;
import com.project.pt.model.constant.Type;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "USER")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class User extends BaseEntity{

    private String name;
    private String surname;

    @Column(unique = true)
    private String username;
    private String password;
    private String phoneNumber;

    @Temporal(TemporalType.DATE)
    private Date birthDate;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "customer", orphanRemoval = true)
    private Set<Appointment> customerAppointments = new HashSet<>();

    @OneToMany(mappedBy = "trainer", orphanRemoval = true)
    private Set<Appointment> trainerAppointments = new HashSet<>();

    @OneToMany(mappedBy = "customer", orphanRemoval = true)
    private Set<Review> customerReviews = new HashSet<>();

    @OneToMany(mappedBy = "trainer", orphanRemoval = true)
    private Set<Review> trainerReviews = new HashSet<>();

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "CUSTOMER_TRAINER",
            joinColumns={@JoinColumn(name="trainer_id", referencedColumnName="id")},
            inverseJoinColumns={@JoinColumn(name="customer_id", referencedColumnName="id")}
    )
    private Set<User> trainers;

    @ManyToMany(mappedBy = "trainers")
    private Set<User> customers;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "USER_AUTHORITY",
            joinColumns={@JoinColumn(name="user_id", referencedColumnName="id")},
            inverseJoinColumns={@JoinColumn(name="authority_id", referencedColumnName="id")}
    )
    private Set<Authority> authorities = new HashSet<>();

    public void addTrainer(User user){
        trainers.add(user);
//        user.getCustomers().add(this);
    }

    public void removeTrainer(User user){
        trainers.remove(user);
//        user.getCustomers().remove(this);
    }

    public void addAuthority(Authority authority){
        authorities.add(authority);
//        authority.getUsers().add(this);
    }

    public void removeAuthority(Authority authority){
        authorities.remove(authority);
//        authority.getUsers().remove(authority);
    }

    @Override
    public boolean equals(Object obj) {
        try {
            String idSelf = this.getId().toString();
            String idOther = ((User) obj).getId().toString();
            return idSelf.equals(idOther);
        }catch (Exception e){
            return false;
        }
    }
}
