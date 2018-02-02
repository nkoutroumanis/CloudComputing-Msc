/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.nkoutroumanis;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;

/**
 *
 * @author nicholaskoutroumanis
 */
public class HotelsRestaurantsReducer extends MapReduceBase implements Reducer<IntWritable, HotelsRestaurantsWritable, Text, Text> {

    private static float radius;
    private static String[] keywords;

    @Override
    public void configure(JobConf job) {
        radius = job.getFloat("radius", 0);
        String[] i = job.getStrings("keywords");
        
        keywords = i[0].split(", ");
    }

    @Override
    public void reduce(IntWritable key, Iterator<HotelsRestaurantsWritable> hotelsRestaurantsValue, OutputCollector<Text, Text> oc, Reporter rprtr) throws IOException {

        List<HotelsRestaurantsWritable> hotels = new ArrayList<>();
        List<HotelsRestaurantsWritable> restaurants = new ArrayList<>();

        while (hotelsRestaurantsValue.hasNext()) {
            HotelsRestaurantsWritable hrw = hotelsRestaurantsValue.next();

            if (hrw.isHotel()) {
                hotels.add(hrw);
            } else {
                restaurants.add(hrw);
            }

        }

        for (HotelsRestaurantsWritable aHotel : hotels) {
            HotelsRestaurantsWritable chosenRestaurant = null;
            float jaccardOfChosenRestaurant;

            for (HotelsRestaurantsWritable aRestaurant : restaurants) {
                if(distance(aHotel.getLatitude(),aHotel.getLongtitude(),aRestaurant.getLatitude(),aRestaurant.getLongtitude())<=radius)
                {
                    String[] keywordsOfARestaurant = aRestaurant.getKeywords().split(", ");
                    
                }

            }
            
            if(chosenRestaurant != null)
            {
                oc.collect(new Text(aHotel.getName()), new Text(chosenRestaurant.getName()));
            }

        }

//        int counter1 = 0;
//        int counter2 = 0;
//        List<HotelsRestaurantsWritable> hotelsRestaurantslist = new ArrayList<>();
//        while(hotelsRestaurantsValue.hasNext())
//        {
//            HotelsRestaurantsWritable hrw = hotelsRestaurantsValue.next();
//            hotelsRestaurantslist.add(hrw);
//            while(counter2 < counter1){
//                if(hotelsRestaurantslist.get(counter1).isHotel() != hotelsRestaurantslist.get(counter2).isHotel())
//                {
//                    
//                }
//            }
//            counter2 = 0;
//            counter1 += 1;
//        }
    }

//    private static float countJaccard(String[] keywordsOfReastaurant)
//    {
//        
//    }
    
    private static float distance(float hotelLatitude, float hotelLongtitude, float restaurantLatitude, float restaurantLongtitude)
    {
        return (float) Math.sqrt(Math.pow(hotelLatitude - restaurantLatitude,2) + Math.pow(hotelLongtitude - restaurantLongtitude,2));
    }

}
