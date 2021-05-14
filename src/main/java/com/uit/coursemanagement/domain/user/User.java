package com.uit.coursemanagement.domain.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.uit.coursemanagement.constant.enums.EGender;
import com.uit.coursemanagement.domain.SqlEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "`user`")
public class User extends SqlEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String username;
    @JsonIgnore
    private String password;
    @Column(unique = true)
    private String email;
    private String fullName;
    private String firstName;
    private String lastName;
    private String avatar;
    private EGender gender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    private Role role;

    public User(Long id) {
        this.id = id;
    }
}
