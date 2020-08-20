package com.hriha.FloorLayout.service;

import com.hriha.FloorLayout.domain.Coordinate;
import com.hriha.FloorLayout.domain.Layout;
import com.hriha.FloorLayout.repos.LayoutRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public List<Coordinate> getXY(Layout layout) {
        List<Coordinate> list = new ArrayList<>();
        String coordinates = layout.getCoordinates();

        String[] arrayList = coordinates.split(" ");

        try {
            for (int i = 0; i < arrayList.length; i = i + 2) {
                Coordinate c = new Coordinate(Integer.parseInt(arrayList[i]), Integer.parseInt(arrayList[i + 1]));
                list.add(c);
            }
        } catch (Exception e) {
            list = null;
        }
        return list;
    }
}
