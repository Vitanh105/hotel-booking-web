package com.backend.model;

import com.backend.model.enums.RoleType;
import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(unique = true, nullable = false)
    private RoleType roleType;

    // tiêm roleType bên enum vào đây . khi thay đổi bên enum bên này ít bị ảnh hưởng
    public Role(RoleType roleType){ 
        this.roleType=roleType;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", roleType='" + getRoleType() + "'" +
            "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return Objects.equals(id, role.id) && roleType == role.roleType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, roleType);
    }
}
