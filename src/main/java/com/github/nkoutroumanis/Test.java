/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.nkoutroumanis;

import java.io.File;
import java.io.IOException;
//import org.apache.commons.io.FileUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.RunningJob;
import org.apache.hadoop.mapred.TextInputFormat;
import org.apache.hadoop.mapred.TextOutputFormat;
import org.apache.hadoop.mapred.lib.MultipleInputs;
import org.apache.hadoop.mapreduce.Job;
//import org.apache.hadoop.shaded.org.apache.commons.io.FileUtils;

/**
 *
 * @author nicholaskoutroumanis
 */
public class Test {
 
    private static final String inputHotelsFilePath = "hdfs://master:9000/user/nick/hotels-ver1.txt";
    private static final String inputRestaurantsFilePath = "hdfs://master:9000/user/nick/restaurants-ver1.txt";    
    private static final String outputFilePath = "hdfs://master:9000/user/nick/output/";
    
//    private static final String inputHotelsFilePath = "/Users/nicholaskoutroumanis/Desktop/cloudComputing/hotels-ver1.txt";
//    private static final String inputRestaurantsFilePath = "/Users/nicholaskoutroumanis/Desktop/cloudComputing/restaurants-ver1.txt";
//    private static final String outputFilePath = "/Users/nicholaskoutroumanis/Desktop/cloudComputing/ooo.txt";    
    
    public static void main(String args[]) throws IOException, Exception {
        
        
        //FileUtils.deleteDirectory(new File(outputFilePath));
//        
//        Configuration conf = new Configuration();
//        Job job = new Job(conf);
        JobConf conf = new JobConf(new Configuration(),Test.class);
        
        conf.setFloat("radius", 1);
        conf.setInt("xCellsDivision", 2);
        conf.setInt("yCellsDivision", 2);
        conf.setStrings("keywords", "Coffee, American");
        
        conf.setJobName("Test Class for finding the restaurants near Hotels");
        
        MultipleInputs.addInputPath(conf, new Path(inputHotelsFilePath), TextInputFormat.class, HotelsMapper.class);
        MultipleInputs.addInputPath(conf, new Path(inputRestaurantsFilePath), TextInputFormat.class, RestaurantsMapper.class);

        conf.setPartitionerClass(HotelsRestaurantsGridPartitioner.class);
        conf.setNumReduceTasks(9);

        conf.setMapOutputKeyClass(IntWritable.class);
        conf.setMapOutputValueClass(HotelsRestaurantsWritable.class);
        
        conf.setReducerClass(HotelsRestaurantsReducer.class);
        
        conf.setOutputKeyClass(Text.class);
        conf.setOutputValueClass(Text.class);
        
        conf.setOutputFormat(TextOutputFormat.class);
        FileOutputFormat.setOutputPath(conf, new Path(outputFilePath));
//        myCustomRunJob(conf);
        JobClient.runJob(conf);
        
    }
    
    public static RunningJob myCustomRunJob(JobConf job) throws Exception{
        JobClient jc = new JobClient(job);
        RunningJob rj = jc.submitJob(job);
        if(!jc.monitorAndPrintJob(job, rj)){
            throw new IOException("Job failed with info: "+rj.getFailureInfo());
        }
        return rj;
    }
}
