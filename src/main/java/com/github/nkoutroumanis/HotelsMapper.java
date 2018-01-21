/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.nkoutroumanis;

import java.io.IOException;
import org.apache.hadoop.io.ArrayPrimitiveWritable;
import org.apache.hadoop.io.ArrayWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

/**
 *
 * @author nicholaskoutroumanis
 * @param <Test>
 */
public class HotelsMapper extends MapReduceBase implements Mapper<LongWritable, Text, ArrayPrimitiveWritable, Text> {

    @Override
    public void map(LongWritable key, Text value, OutputCollector<ArrayPrimitiveWritable, Text> oc, Reporter rprtr) throws IOException {
        String s = value.toString();
        String[] tokens = s.split("|");
        
        float[] coordinates = new float[2];
        coordinates[0] = Float.valueOf(tokens[4]);//lat
        coordinates[1] = Float.valueOf(tokens[5]);//lon
        
            oc.collect(new ArrayPrimitiveWritable(coordinates), new Text(tokens[0]+" - "+tokens[1]));                       
    }
}
