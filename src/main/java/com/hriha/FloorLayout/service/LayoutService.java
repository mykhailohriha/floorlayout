package com.hriha.FloorLayout.service;

import com.hriha.FloorLayout.domain.Layout;
import com.hriha.FloorLayout.repos.LayoutRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LayoutService {

    @Autowired
    private LayoutRepo layoutRepo;

    public List<Layout> findAll() {
        return (List<Layout>) layoutRepo.findAll();
    }

    public void saveLayout(String name, String coordinates) {
        Layout layout = new Layout(name, coordinates);
        layoutRepo.save(layout);
    }

    public Layout findLayoutById(Integer id) {
        return layoutRepo.findLayoutById(id);
    }
}
