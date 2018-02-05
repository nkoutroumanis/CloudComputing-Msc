/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.nkoutroumanis;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.Partitioner;



/**
 *
 * @author nicholaskoutroumanis
 */
public class HotelsRestaurantsGridPartitioner implements Partitioner<IntWritable, HotelsRestaurantsWritable> {

    @Override
    public int getPartition(IntWritable key, HotelsRestaurantsWritable value, int numReduceTasks) {
        return key.get();
    }

    @Override
    public void configure(JobConf jc) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
