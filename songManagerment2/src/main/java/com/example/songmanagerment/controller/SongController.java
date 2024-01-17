package com.example.songmanagerment.controller;

import com.example.songmanagerment.model.Song;
import com.example.songmanagerment.service.ISongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class SongController {
    @Autowired
    ISongService songService;
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String home(Model model){
        model.addAttribute("songs",songService.findAll());
        return "home";
    };
    @RequestMapping(value = "/AddPage", method = RequestMethod.GET)
    public String toAddPage(){
        return "save";
    };
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String addSong(@ModelAttribute Song song){
        songService.save(song);
        return "redirect:/list";
    };
    @RequestMapping(value = "/save", method = RequestMethod.GET)
    public String updateSong(@RequestParam(required = false,name ="id") Integer id, Model model){
        Song song = new Song();
        if (id != null){
            song = songService.findSongById(id);
        }
        model.addAttribute("song", song);
        model.addAttribute("id",id);
        return "save";
    }
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String deleteSong(@RequestParam Integer id){
        songService.delete(id);
        return "redirect:/list";
    };

}
