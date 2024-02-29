package com.example.Enotes_springboot_project.controller;

import com.example.Enotes_springboot_project.entity.Notes;
import com.example.Enotes_springboot_project.entity.User;
import com.example.Enotes_springboot_project.repository.UserRepo;
import com.example.Enotes_springboot_project.service.NotesService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    NotesService notesService;
    @Autowired
    private UserRepo userRepo;
    @ModelAttribute
    public User getUser(Principal p, Model m){
        String email = p.getName();
        User user = userRepo.findByEmail(email);
        m.addAttribute("user",user);
        return user;
    }

    @GetMapping("/addNotes")
    public String addNotes(){
        return "add_notes";
    }
    @GetMapping("/editNotes/{id}")
    public String editNotes(@PathVariable int id,Model m){
        Notes notes = notesService.getNotes(id);
        m.addAttribute("n",notes);
        return "edit_notes";
    }


    @GetMapping("/viewNotes")
    public String viewNotes(Model m,Principal p,@RequestParam(defaultValue = "0") Integer pageNo){
        User user = getUser(p,m);
        Page<Notes> notes = notesService.getNotesByUser(user,pageNo);
        m.addAttribute("currentPage",pageNo);
        m.addAttribute("totalElements",notes.getTotalElements());
        m.addAttribute("totalPages",notes.getTotalPages());
        m.addAttribute("notesList",notes.getContent());

        return "view_notes";
    }


    //for save notes
    @PostMapping("/saveNotes")
    public String saveNotes(@ModelAttribute Notes notes, HttpSession session,Principal p, Model m){
          notes.setDate(LocalDate.now());
          notes.setUser(getUser(p,m));
          Notes saveNotes = notesService.saveNotes(notes);
          if (saveNotes!=null){
               session.setAttribute("msg","save notes successfully");
          }
          else {
              session.setAttribute("msg","Something wrong on the server");
          }
          return "redirect:/user/addNotes";
    }

    @PostMapping("/updateNotes")
    public String updateNotesNotes(@ModelAttribute Notes notes, HttpSession session,Principal p, Model m){
        notes.setDate(LocalDate.now());
        notes.setUser(getUser(p,m));
        Notes saveNotes = notesService.saveNotes(notes);
        if (saveNotes!=null){
            session.setAttribute("msg","notes update successfully");
        }
        else {
            session.setAttribute("msg","Something wrong on the server");
        }
        return "redirect:/user/viewNotes";
    }

    @GetMapping("/deleteNotes/{id}")
    public String deleteNotes(@PathVariable int id,HttpSession session){
        boolean f = notesService.deleteNotesBYId(id);
        if (f){
            session.setAttribute("msg","delete successfully");
        }
        else {
            session.setAttribute("msg","something wrong with server");
        }
        return "redirect:/user/viewNotes";
    }

}
