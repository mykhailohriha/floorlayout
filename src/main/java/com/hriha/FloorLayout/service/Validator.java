package com.hriha.FloorLayout.service;

import com.hriha.FloorLayout.domain.Coordinate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;

@Service
public class Validator {

    @Autowired
    private LayoutService layoutService;

    public boolean isDataCorrect(String data) {
        String[] list = data.split(",");

        if (list.length % 2 != 0 || list.length < 3) {
            return false;
        }

        return true;
    }

    public boolean isDiagonalPresent(String data) {
        String[] list = data.split(",");
        String[] xy1;
        String[] xy2;

        for (int i = 0; i < list.length; i++) {
            xy1 = list[i].split(" ");

            if (i != list.length - 1) {
                xy2 = list[i + 1].split(" ");
            } else {
                xy2 = list[0].split(" ");
            }

            if (!xy1[0].equals(xy2[0]) && !xy1[1].equals(xy2[1])) {
                return true;
            }
        }

        return false;
    }

    public boolean isCoordinateGoingClockwise(String data) {
        String[] list = data.split(",");
        ArrayList<String> x = new ArrayList<>();
        ArrayList<String> y = new ArrayList<>();

        for (int i = 0; i <list.length; i++) {
            String[] pair = list[i].split(" ");
            x.add(pair[0]);
            y.add(pair[1]);
        }

        int dx1 = Integer.parseInt(x.get(1)) - Integer.parseInt(x.get(0));
        int dx2 = Integer.parseInt(x.get(2)) - Integer.parseInt(x.get(1));
        int dy1 = Integer.parseInt(x.get(1)) - Integer.parseInt(x.get(0));
        int dy2 = Integer.parseInt(x.get(2)) - Integer.parseInt(x.get(1));

        double r = dx1*dy2 - dx2*dy1;;

        if ( r < 0 ) return false;
        else return true;
    }

}
