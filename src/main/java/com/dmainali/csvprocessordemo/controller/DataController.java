package com.dmainali.csvprocessordemo.controller;

import com.dmainali.csvprocessordemo.Entity.Product;
import com.dmainali.csvprocessordemo.Entity.ResultWrapper;
import com.dmainali.csvprocessordemo.rawdataprocessor.CSVProcessor;
import com.dmainali.csvprocessordemo.rawdataprocessor.DataFeeder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.Set;


@RestController
public class DataController {

    @GetMapping(value= "/demo")
    public ResultWrapper demo(){
        CSVProcessor csvProcessor = new CSVProcessor();
        DataFeeder feeder = new DataFeeder();
        Set<Product> products =  csvProcessor.processData();
        return feeder.saveDemo(products);
    }
}
