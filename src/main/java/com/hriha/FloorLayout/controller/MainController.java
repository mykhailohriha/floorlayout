package com.hriha.FloorLayout.controller;

import com.hriha.FloorLayout.domain.Coordinate;
import com.hriha.FloorLayout.domain.Layout;
import com.hriha.FloorLayout.repos.LayoutRepo;
import com.hriha.FloorLayout.service.LayoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    private LayoutService layoutService;

    @GetMapping("/all")
    public String main(Model model) {
        Iterable<Layout> layouts = layoutService.findAll();
        model.addAttribute("layouts", layouts);
        model.addAttribute("js", "javascriptxernya");
        return "main";
    }

    @PostMapping("/add")
    public String addLayout(@RequestParam String name,
                            @RequestParam String coordinates) {

        layoutService.saveLayout(name, coordinates);

        return "redirect:/all";
    }

    @GetMapping("/add")
    public String addLayout() {
        return "add";
    }

    @GetMapping("/show")
    public String showLayout(@RequestParam Integer id, Model model) {
        Layout layout = layoutService.findLayoutById(id);
        List<Coordinate> coordinates = layoutService.getXY(layout);

        model.addAttribute("layout", layout );
        model.addAttribute("coordinates", coordinates);

        return "layout";
    }
}
