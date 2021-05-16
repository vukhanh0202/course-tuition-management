package com.uit.coursemanagement.domain.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.uit.coursemanagement.constant.enums.EGender;
import com.uit.coursemanagement.domain.SqlEntity;
import com.uit.coursemanagement.domain.course.Course;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

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
    @Enumerated(EnumType.STRING)
    private EGender gender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    private Role role;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "instructor")
    private Set<Course> courseInstructors = new HashSet<>();

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
//    private Set<TuitionFee> tuitionFees = new HashSet<>();

    public User(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id);
    }
}
