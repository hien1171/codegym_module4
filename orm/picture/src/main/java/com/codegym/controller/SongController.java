package com.codegym.controller;

import com.codegym.model.Song;
import com.codegym.service.ISongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/song")
public class SongController {
    @Autowired
    private ISongService songService;

    @ModelAttribute("type")
    public String[] getTypeOfSong(){
        System.out.println("get list of type");
        return new String[]{"EDM","Rock","Acoustic","Soft"};
    }

    @GetMapping("")
    public ModelAndView showList(){
        List<Song> songList=songService.findAll();
        ModelAndView modelAndView=new ModelAndView("list","songList",songList);
        return modelAndView;
    }
    @GetMapping("/create")
    public String showFormCreate(Model model){
        model.addAttribute("song",new Song());
        model.addAttribute("type",new String[]{"EDM","Rock","Acoustic","Soft"});
        return "create";
    }
    @PostMapping("/create")
    public String save(@ModelAttribute Song song,
                       RedirectAttributes redirectAttributes) {
        songService.add(song);
        redirectAttributes.addFlashAttribute("mess","Created Success");
        return "redirect:/song";
    }

    @GetMapping("/detail/{name}")
    public String detail(@PathVariable String name, Model model){
        Song song=songService.findByName(name);
        model.addAttribute("song", song);
        return "detail";
    }

}
