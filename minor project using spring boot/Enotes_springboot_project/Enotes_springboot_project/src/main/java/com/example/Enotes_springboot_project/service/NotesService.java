package com.example.Enotes_springboot_project.service;

import com.example.Enotes_springboot_project.entity.Notes;
import com.example.Enotes_springboot_project.entity.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface NotesService {
    public Notes saveNotes(Notes notes);
    public Notes getNotes(int id);
//    public List<Notes> getNotesByUser(User user);
    public Page<Notes> getNotesByUser(User user, int pageNo);
    public Notes updateNotes(Notes notes);
    public boolean deleteNotesBYId(int id);
}
