/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.nkoutroumanis;

import java.io.IOException;
import java.util.Iterator;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;

/**
 *
 * @author nicholaskoutroumanis
 */
public class CustomReducer extends MapReduceBase implements Reducer<?, ?, ?, ?> {

    public void reduce(K2 k2, Iterator<V2> itrtr, OutputCollector<K3, V3> oc, Reporter rprtr) throws IOException {

    }
}
