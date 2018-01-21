/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.nkoutroumanis;

/**
 *
 * @author nicholaskoutroumanis
 */
public enum Grid { //Singleton Class
    INSTANCE;

    private final float xLower = 8 - Test.radius;//x-lat y-long
    private final float yLower = -123;
    private final float xUpper = 50 + Test.radius;///mipos auto prepei na einai 50?
    private final float yUpper = 42 + Test.radius;

    public boolean contains(float latitude, float longtitude) {

        if((Float.compare(latitude, xLower)>=0 && Float.compare(longtitude, yLower)>=0) 
            && (Float.compare(latitude, xUpper)<=0 && Float.compare(longtitude, yUpper)<=0))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
