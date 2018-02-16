/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.nkoutroumanis;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nicholaskoutroumanis
 */
public class Grid {

    private final float radius;
    private final int xCellsDivision;
    private final int yCellsDivision;
    private final float xLower;
    private final float yLower;
    private final float xUpper;
    private final float yUpper;    

    private Grid(float radius, int xCellsDivision, int yCellsDivision) {
        this.radius = radius;
        this.xCellsDivision = xCellsDivision;
        this.yCellsDivision = yCellsDivision;

        xLower = (radius <= 49) ? 8 - radius : -41;
        yLower = -123;
        xUpper = (radius <= 1) ? 49 + radius : 50;
        yUpper = (radius <= 6) ? 42 + radius : 48;
    }

    public static Grid newInstance(float radius, int xCellsDivision, int yCellsDivision) {
        return new Grid(radius, xCellsDivision, yCellsDivision);

    }
    
//cells are represented by numbers. each cell has a unique number. the starting number of cell is zero. a restaurant may be contained in two cells. so the list will have 2 numbers
    public List<Integer> putRestaurantsInCells(float latitude, float longtitude) {
        
        List<Integer> cellsList = new ArrayList<>();
        
        for (int x = 0; x < xCellsDivision; x++) {
            boolean a = Float.compare(latitude, (xLower + (x * (xUpper - xLower)) / xCellsDivision)-radius) >= 0;//left of cell
            boolean b = Float.compare((xLower + (x + 1) * (xUpper - xLower) / xCellsDivision)+radius, latitude) >= 0;//right of cell
            if (a && b) {
                
                for (int y = 0; y < yCellsDivision; y++) {
                    if (Float.compare(longtitude, (yLower + (y * (yUpper - yLower)) / yCellsDivision)-radius) >= 0 //bottom of cell
                            && Float.compare((yLower + (y + 1) * (yUpper - yLower) / yCellsDivision)+radius, longtitude) >= 0) {//top of cell

                        cellsList.add(x + y * xCellsDivision);
    
                    }
                }
            }
        }
        return cellsList;
    }
    
    
//cells are represented by numbers. each cell has a unique number. the starting number of cell is zero
    public int putHotelsInCells(float latitude, float longtitude) {
        int cell = -1;
        for (int x = 0; x < xCellsDivision; x++) {
            if (cell >= 0) {
                break;
            }
            boolean a = Float.compare(latitude, xLower + (x * (xUpper - xLower)) / xCellsDivision) >= 0;//left of cell
            boolean b = Float.compare(xLower + (x + 1) * (xUpper - xLower) / xCellsDivision, latitude) >= 0;//right of cell
            if (a && b) {
                for (int y = 0; y < yCellsDivision; y++) {
                    if (Float.compare(longtitude, yLower + (y * (yUpper - yLower)) / yCellsDivision) >= 0 //bottom of cell
                            && Float.compare(yLower + (y + 1) * (yUpper - yLower) / yCellsDivision, longtitude) >= 0) {//top of cell

                        cell = x + y * xCellsDivision;

                        break;
                    }
                }
            }

        }
        return cell;
    }

    public boolean contains(float latitude, float longtitude) {

        if ((Float.compare(latitude, xLower) >= 0 && Float.compare(longtitude, yLower) >= 0)
                && (Float.compare(latitude, xUpper) <= 0 && Float.compare(longtitude, yUpper) <= 0)) {
            return true;
        } else {
            return false;
        }
    }
}
