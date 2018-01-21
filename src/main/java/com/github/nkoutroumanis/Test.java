/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.nkoutroumanis;

import java.io.IOException;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.TextInputFormat;
import org.apache.hadoop.mapred.lib.MultipleInputs;
/**
 *
 * @author nicholaskoutroumanis
 */
public class Test {
    private static final String inputHotelsFilePath = "/Users/nicholaskoutroumanis/Desktop/cloudComputing/hotels-ver1.txt";
    private static final String inputRestaurantsFilePath = "/Users/nicholaskoutroumanis/Desktop/cloudComputing/restaurants-ver1.txt";
    private static final String outputFilePath = "";
    
    public static final float radius = 4;
    
    public static void main(String args[]) throws IOException
    {
        
        JobConf conf = new JobConf(Test.class);
        conf.setJobName("Test Class for finding the restaurants near Hotels");
        
        MultipleInputs.addInputPath(conf, new Path(inputHotelsFilePath), TextInputFormat.class, HotelsMapper.class);
        MultipleInputs.addInputPath(conf, new Path(inputRestaurantsFilePath), TextInputFormat.class, RestaurantsMapper.class);
        //FileOutputFormat.setOutputPath(conf,new Path(inputFilePath));
        //conf.setPartitionerClass();
        //conf.setMapperClass(HotelsMapper.class);
        conf.setReducerClass(CustomReducer.class);
        
        conf.setOutputKeyClass(Text.class);
        conf.setOutputValueClass(Text.class);
        
        JobClient.runJob(conf);
    }
}
