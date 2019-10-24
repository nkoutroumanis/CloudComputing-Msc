/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.nkoutroumanis;

import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

/**
 *
 * @author nicholaskoutroumanis
 * @param <Grid>
 * @param <Test>
 */
public class HotelsMapper extends MapReduceBase implements Mapper<LongWritable, Text, IntWritable, HotelsRestaurantsWritable> {

    private static Grid grid;
    private int prevPos = 0;
    private int nextPos = 0;
    
    @Override
    public void configure(JobConf job) {
        grid = Grid.newInstance(job.getFloat("radius", 0), job.getInt("xCellsDivision", 1), job.getInt("yCellsDivision", 1));
    }
    
    private String getNextToken(String str) {
        if (nextPos == str.length())
            return null;
        if (nextPos != 0) {
            prevPos = nextPos + 2;
        }
        nextPos = str.indexOf("\\|", prevPos);
        if (nextPos < 0)
            nextPos = str.length();
        return str.substring(prevPos, nextPos);
    }

    private String getNextToken(String str, int skip) {
        if (skip < 0) {
            return null;
        }
        int count = 0;
        String res;
        do {
            res = getNextToken(str);
        }
        while ((count++ < skip) && (res != null));
        return res;
    }
    
    @Override
    public void map(LongWritable key, Text value, OutputCollector<IntWritable, HotelsRestaurantsWritable> oc, Reporter rprtr) throws IOException {
        String s = value.toString();

        String name = getNextToken(str);
        String address = getNextToken(str);
        
        float[] coordinates = new float[2](Float.parseFloat(getNextToken(str, 2), getNextToken(str));
        
        if(grid.contains(coordinates[0], coordinates[1]))
        {
            IntWritable iw = new IntWritable(grid.putHotelsInCells(coordinates[0], coordinates[1]));
            HotelsRestaurantsWritable hrw = new HotelsRestaurantsWritable(true, name+" - "+address, coordinates[0], coordinates[1], "");
            
            oc.collect(iw, hrw);
            
        }
                                    
    }
}
