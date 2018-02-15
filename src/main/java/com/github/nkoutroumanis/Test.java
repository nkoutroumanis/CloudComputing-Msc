/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.nkoutroumanis;

import java.io.File;
import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.TextInputFormat;
import org.apache.hadoop.mapred.TextOutputFormat;
import org.apache.hadoop.mapred.lib.MultipleInputs;
//import org.apache.hadoop.shaded.org.apache.commons.io.FileUtils;

/**
 *
 * @author nicholaskoutroumanis
 */
public class Test {
    
    //paths in my pc
//    private static final String inputHotelsFilePath = "/Users/nicholaskoutroumanis/Desktop/cloudComputing/hotels-ver1.txt";
//    private static final String inputRestaurantsFilePath = "/Users/nicholaskoutroumanis/Desktop/cloudComputing/restaurants-ver1.txt";
//    private static final String outputFilePath = "/Users/nicholaskoutroumanis/Desktop/cloudComputing/";    

    //paths in hdfs
    private static final String inputHotelsFilePath = "/user/nick/hotels-ver1.txt";
    private static final String inputRestaurantsFilePath = "/user/nick/restaurants-ver1.txt";
    private static final String outputFilePath = "/user/hotels-restaurants/"; 
    
    public static void main(String args[]) throws IOException, Exception {
        
        int radius = Integer.parseInt(args[0]);
        int xSeparations = Integer.parseInt(args[1]);
        int ySeparations = Integer.parseInt(args[2]);
        String keyWords = args[3];// The keywords should be in this form (without spaces) "American,Asian,Greek"

//        FileUtils.deleteDirectory(new File(outputFilePath));       
        JobConf conf = new JobConf(new Configuration(),Test.class);
        conf.setFloat("radius", radius);
        conf.setInt("xCellsDivision", xSeparations+1);
        conf.setInt("yCellsDivision", ySeparations+1);
        conf.setStrings("keywords", keyWords);
        
        conf.setJobName("Test Class for finding the restaurants near Hotels");
        
        MultipleInputs.addInputPath(conf, new Path(inputHotelsFilePath), TextInputFormat.class, HotelsMapper.class);
        MultipleInputs.addInputPath(conf, new Path(inputRestaurantsFilePath), TextInputFormat.class, RestaurantsMapper.class);

        conf.setPartitionerClass(HotelsRestaurantsGridPartitioner.class);
        conf.setNumReduceTasks((xSeparations+1)*(ySeparations+1));

        conf.setMapOutputKeyClass(IntWritable.class);
        conf.setMapOutputValueClass(HotelsRestaurantsWritable.class);
        
        conf.setReducerClass(HotelsRestaurantsReducer.class);
        
        conf.setOutputKeyClass(Text.class);
        conf.setOutputValueClass(Text.class);
        
        conf.setOutputFormat(TextOutputFormat.class);
        FileOutputFormat.setOutputPath(conf, new Path(outputFilePath+"separations-"+xSeparations+""+ySeparations+"-radius-"+radius));
        JobClient.runJob(conf);
        
    }
    
}
