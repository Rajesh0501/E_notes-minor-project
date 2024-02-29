package com.example.Enotes_springboot_project.repository;

import com.example.Enotes_springboot_project.entity.Notes;
import com.example.Enotes_springboot_project.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotesRepo extends JpaRepository<Notes,Integer> {
   public Page<Notes> findByUser(User user, Pageable pageable);
}
