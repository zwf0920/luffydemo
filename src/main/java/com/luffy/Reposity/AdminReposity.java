package com.luffy.Reposity;

import com.luffy.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminReposity extends JpaRepository<Admin,Long> {
    public Admin findByUsername(String username);
}
