package com.luffy.Reposity;

import com.luffy.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleReposity extends JpaRepository<Role,Long> {
    public Role findByRoleid(Long roleid);
}
