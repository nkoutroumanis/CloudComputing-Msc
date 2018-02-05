/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.nkoutroumanis;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import org.apache.hadoop.io.Writable;

/**
 *
 * @author nicholaskoutroumanis
 */
public class HotelsRestaurantsWritable implements Writable {

    private boolean isHotel;
    private String name;
    private float latitude;
    private float longtitude;
    private String keywords;

    private HotelsRestaurantsWritable()
    {
        
    }
    
    public HotelsRestaurantsWritable(boolean isHotel, String name, float latitude, float longtitude, String keywords) {
        this.isHotel = isHotel;
        this.name = name;
        this.latitude = latitude;
        this.longtitude = longtitude;
        this.keywords = keywords;
    }

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeBoolean(isHotel());
        out.writeUTF(getName());
        out.writeFloat(getLatitude());
        out.writeFloat(getLongtitude());
        out.writeUTF(getKeywords());
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        isHotel = in.readBoolean();
        name = in.readUTF();
        latitude = in.readFloat();
        longtitude = in.readFloat();
        keywords = in.readUTF();
    }
    
//    public static HotelsRestaurantsWritable read(DataInput in) throws IOException{
//        HotelsRestaurantsWritable hrw = new HotelsRestaurantsWritable();
//        hrw.readFields(in);
//        return hrw;
//    }

    /**
     * @return the isHotel
     */
    public boolean isHotel() {
        return isHotel;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the latitude
     */
    public float getLatitude() {
        return latitude;
    }

    /**
     * @return the longtitude
     */
    public float getLongtitude() {
        return longtitude;
    }

    /**
     * @return the keywords
     */
    public String getKeywords() {
        return keywords;
    }

}
