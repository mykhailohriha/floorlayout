package com.hriha.FloorLayout.controller;

import com.hriha.FloorLayout.domain.Coordinate;
import com.hriha.FloorLayout.domain.Layout;
import com.hriha.FloorLayout.service.LayoutService;
import com.hriha.FloorLayout.service.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class MainController {

    @Autowired
    private LayoutService layoutService;

    @Autowired
    private Validator validator;

    @GetMapping("/all")
    public String main(Model model) {
        Iterable<Layout> layouts = layoutService.findAll();
        model.addAttribute("layouts", layouts);

        return "main";
    }

    @PostMapping("/add")
    public String addLayout(@RequestParam String name,
                            @RequestParam String coordinates,
                            Model model) {

        if (validate(coordinates, model)) return "errors/error";

        layoutService.saveLayout(name, coordinates.replaceAll(",", " "));

        return "redirect:/all";
    }

    @GetMapping("/add")
    public String addLayout() {
        return "add";
    }

    @GetMapping("/show")
    public String showLayout(@RequestParam Integer id, Model model) {
        Layout layout = layoutService.findLayoutById(id);
        List<Coordinate> coordinates = layoutService.getXY(layout.getCoordinates());

        model.addAttribute("layout", layout);
        model.addAttribute("coordinates", coordinates);
        model.addAttribute("x", coordinates.get(0).getX());
        model.addAttribute("y", coordinates.get(0).getY());

        return "layout";
    }

    @Transactional
    @GetMapping("/delete")
    public String deleteLayout(@RequestParam Integer id) {
        layoutService.deleteLayout(id);
        return "redirect:/all";
    }

    @GetMapping("/update")
    public String updateLayout(@RequestParam Integer id, Model model) {
        Layout layout = layoutService.findLayoutById(id);
        model.addAttribute("layout", layout);

        return "update";
    }

    @Transactional
    @PostMapping("/update")
    public String updateBook(@RequestParam Integer id,
                             @RequestParam String name,
                             @RequestParam String coordinates,
                             Model model) {

        if (validate(coordinates, model)) return "errors/error";

        layoutService.deleteLayout(id);
        layoutService.saveLayout(name, coordinates.replaceAll(",", " "));

        return "redirect:/all";
    }

    @PostMapping("filter")
    public String filter(@RequestParam String filter,
                         Map<String, Object> model) {
        Iterable<Layout> layouts = layoutService.filter(filter);
        model.put("layouts", layouts);

        return "main";
    }

    private boolean validate(@RequestParam String coordinates, Model model) {
        if (!validator.isDataCorrect(coordinates)) {
            model.addAttribute("error", "Data is not correct");
            return true;
        }

        if (validator.isDiagonalPresent(coordinates)) {
            model.addAttribute("error", "The walls are diagonal");
            return true;
        }

        if (validator.isRepeatingCornerPresent(coordinates)) {
             model.addAttribute("error", "The corners are repeated");
             return true;
        }

//        if (!validator.isCoordinateGoingClockwise(coordinates)) {
//            model.addAttribute("error", "Coordinates do not going clockwise");
//            return true;
//        }
        return false;
    }
}
