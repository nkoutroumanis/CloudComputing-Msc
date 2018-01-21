/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.nkoutroumanis;

import org.apache.hadoop.io.ArrayPrimitiveWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

/**
 *
 * @author nicholaskoutroumanis
 */
public class HotelsGridPartitioner extends Partitioner<ArrayPrimitiveWritable, Text> {

    @Override
    public int getPartition(ArrayPrimitiveWritable key, Text value, int numReduceTasks) {
        int[] coordinates = (int[]) key.get();
    }
    
}
