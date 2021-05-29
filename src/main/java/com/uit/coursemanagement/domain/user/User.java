package com.uit.coursemanagement.domain.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.uit.coursemanagement.constant.enums.EGender;
import com.uit.coursemanagement.constant.enums.EUserType;
import com.uit.coursemanagement.domain.SqlEntity;
import com.uit.coursemanagement.domain.lecturer.Lecturer;
import com.uit.coursemanagement.domain.student.Student;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

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
    private String avatar;
    @Enumerated(EnumType.STRING)
    private EGender gender;

    @Enumerated(EnumType.STRING)
    private EUserType userType;

    @OneToOne(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private Student student;

    public void setStudent(Student student) {
        if (student == null) {
            if (this.student != null) {
                this.student.setUser(null);
            }
        } else {
            student.setUser(this);
        }
        this.student = student;
    }

    @OneToOne(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private Lecturer lecturer;

    public void setLecturer(Lecturer lecturer) {
        if (lecturer == null) {
            if (this.lecturer != null) {
                this.lecturer.setUser(null);
            }
        } else {
            lecturer.setUser(this);
        }
        this.lecturer = lecturer;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    private Role role;

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
