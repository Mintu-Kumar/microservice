package com.lcwd.user.service.entities;



import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="micro_users")
public class User {

    @Id
    @Column(name="ID")
    private String userId;
    @Column(name="NAME", length = 30)
    private String name;
    @Column(name="EMAIL")
    private String email;
    @Column(name="ABOUT")
    private String about;

    //We do not want to store in database thats why we use @Transient,
    // except this all the above entity will be save in database
    @Transient
    private List<Rating> ratings = new ArrayList<>();

}
