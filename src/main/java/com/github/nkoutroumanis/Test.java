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
/**
 *
 * @author nicholaskoutroumanis
 */
public class Test {
    private static final String inputHotelsFilePath = "/Users/nicholaskoutroumanis/Desktop/cloudComputing/hotels-ver1.txt";
    private static final String inputRestaurantsFilePath = "/Users/nicholaskoutroumanis/Desktop/cloudComputing/restaurants-ver1.txt";
    private static final String outputFilePath = "";
    
    public static void main(String args[]) throws IOException
    {
        JobConf conf = new JobConf(Test.class);
        conf.setJobName("Test Class for finding the restaurants near Hotels");
        
        FileInputFormat.addInputPath(conf, new Path(inputHotelsFilePath));
        FileInputFormat.addInputPath(conf, new Path(inputRestaurantsFilePath));
        
        FileOutputFormat.setOutputPath(conf,new Path(inputFilePath));
        
        conf.setMapperClass(CustomMapper.class);
        conf.setReducerClass(CustomReducer.class);
        
        conf.setOutputKeyClass(Text.class);
        conf.setOutputValueClass(Text.class);
        
        JobClient.runJob(conf);
    }
}
