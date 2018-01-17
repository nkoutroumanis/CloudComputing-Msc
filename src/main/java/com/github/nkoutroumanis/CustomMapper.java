/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.nkoutroumanis;

import java.io.IOException;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

/**
 *
 * @author nicholaskoutroumanis
 */
public class CustomMapper extends MapReduceBase implements Mapper<?, ?, ?, ?> {

    public void map(K1 k1, V1 v1, OutputCollector<K2, V2> oc, Reporter rprtr) throws IOException {

    }
}
