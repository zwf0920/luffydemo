package com.luffy.Reposity;



import com.luffy.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserReposity extends JpaRepository<User,Long> {
    public User findByUsername(String username);
    public User findByUserid(Long userid);

}
