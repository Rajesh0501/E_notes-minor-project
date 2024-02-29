package com.example.Enotes_springboot_project.repository;

import com.example.Enotes_springboot_project.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User,Integer> {
      public boolean existsByEmail(String email);
      public User findByEmail(String email);
}
