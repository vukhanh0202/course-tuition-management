package com.uit.coursemanagement.domain.user;

import com.uit.coursemanagement.constant.enums.ERoleType;
import com.uit.coursemanagement.domain.SqlEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name = "role")
public class Role extends SqlEntity {

    @Id
    @Enumerated(EnumType.STRING)
    private ERoleType id;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "role")
    private Set<User> users = new HashSet<>();

    @Column(columnDefinition="TEXT")
    private String description;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return id == role.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Role(ERoleType id) {
        this.id = id;
    }

}