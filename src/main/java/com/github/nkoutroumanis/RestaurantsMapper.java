package com.github.nkoutroumanis;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.hadoop.io.ArrayPrimitiveWritable;
import org.apache.hadoop.io.ArrayWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author nicholaskoutroumanis
 */
public class RestaurantsMapper extends MapReduceBase implements Mapper<LongWritable, Text, ArrayPrimitiveWritable, ArrayWritable> {

    @Override
    public void map(LongWritable key, Text value, OutputCollector<ArrayPrimitiveWritable, ArrayWritable> oc, Reporter rprtr) throws IOException {
        String s = value.toString();
        String[] tokens = s.split("|");

        float[] coordinates = new float[2];
        coordinates[0] = Float.valueOf(tokens[3]);
        coordinates[1] = Float.valueOf(tokens[4]);

        if (Grid.INSTANCE.contains(coordinates[0], coordinates[1])) {
            String[] keywords = tokens[5].split(", ");

            String[] array = new String[keywords.length + 1];
            array[0] = tokens[0] + " - " + tokens[1];//the first element of the array is the id and name of restaurant

            for (int i = 1; i < array.length; i++) {
                array[i] = keywords[i - 1];
            }

            oc.collect(new ArrayPrimitiveWritable(coordinates), new ArrayWritable(array));
        }
    }
}
