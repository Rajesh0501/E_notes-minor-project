package com.example.Enotes_springboot_project.service;

import com.example.Enotes_springboot_project.entity.Notes;
import com.example.Enotes_springboot_project.entity.User;
import com.example.Enotes_springboot_project.repository.NotesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotesServiceImp implements NotesService{
    @Autowired
    NotesRepo notesRepo;
    @Override
    public Notes saveNotes(Notes notes) {
        return notesRepo.save(notes);
    }

    @Override
    public Notes getNotes(int id) {
        return notesRepo.findById(id).get();
    }

    @Override
    public Page<Notes> getNotesByUser(User user, int pageNo) {
        Pageable pageable = PageRequest.of(pageNo,5);
        return notesRepo.findByUser(user,pageable);
    }

    @Override
    public Notes updateNotes(Notes notes) {
        return notesRepo.save(notes);
    }

    @Override
    public boolean deleteNotesBYId(int id) {
        Notes notes = notesRepo.findById(id).get();
        if (notes != null) {
            notesRepo.delete(notes);
            return true;
        }else {
            return false;
        }
    }
}
