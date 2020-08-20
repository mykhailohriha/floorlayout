package com.hriha.FloorLayout.controller;

import com.hriha.FloorLayout.domain.Layout;
import com.hriha.FloorLayout.repos.LayoutRepo;
import com.hriha.FloorLayout.service.LayoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    @Autowired
    private LayoutService layoutService;

    @GetMapping("/all")
    public String main(Model model) {
        Iterable<Layout> layouts = layoutService.findAll();
        model.addAttribute("layouts", layouts);
        return "main";
    }

    @PostMapping("/add")
    public String addBook(@RequestParam String name,
                          @RequestParam String coordinates) {

        layoutService.saveLayout(name, coordinates);

        return "redirect:/all";
    }

    @GetMapping("/add")
    public String addBook(){
        return "add";
    }

}
