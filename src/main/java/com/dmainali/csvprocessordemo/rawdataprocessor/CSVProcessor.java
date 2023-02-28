package com.dmainali.csvprocessordemo.rawdataprocessor;

import com.dmainali.csvprocessordemo.Entity.Product;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class CSVProcessor {
    public static final String CSVFILE_NAME= "src/main/resources/data/nordstrom product data.csv";

    public static Set<Product> productList = new HashSet<>();

    public Set<Product> processData()  {
        int counter = 0;
        FileReader fr = null;
        BufferedReader br = null;
        try{
            fr = new FileReader(CSVFILE_NAME);
            br = new BufferedReader(fr);
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            br.readLine();
            String line = br.readLine();

            while(line != null){
                line = cleanUpData(line);
                String[] columns = line.split(",");
                Product product = new Product();
                if(columns.length==15){
                    product.setDepartment(columns[0]);
                    product.setCategory(columns[1]);
                    product.setSubcategory(columns[2]);
                    product.setSku(columns[3]);
                    product.setProductName(columns[4]);
                    product.setBrand(columns[5]);
                    product.setGender(columns[6]);
                    product.setPriceRetail(Double.parseDouble(columns[7]));
                    product.setPriceCurrent(Double.parseDouble(columns[8]));
                    product.setReviewRating(Double.parseDouble(columns[9]));
                    product.setReviewCount(Integer.parseInt(columns[10]));
                    product.setPromotion(columns[11]);
                    try {
                        product.setColor(Integer.parseInt(columns[12]));
                    }catch (NumberFormatException ex){
                        product.setColor(666);
                    }
                    try {
                        product.setRunDate(LocalDateTime.parse(columns[13], dateTimeFormatter));
                        product.setInsertUpdateTime(LocalDateTime.parse(columns[14], dateTimeFormatter));
                    }catch(DateTimeParseException ex){
                        log.error(line);
                        log.error(columns[13]);
                    }
                }else {
                    log.warn("Ignored data: "+line);
                }
                productList.add(product);
                counter++;
                line = br.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try{
                if(fr!= null){
                    fr.close();
                }
                if(br!=null){
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        log.info("Inserted "+productList.size()+" items in the list");
        return productList;
    }

    private String cleanUpData(String line) {
        Pattern pattern = Pattern.compile("(\"[^\",]+),([^\"]+\")");
        Matcher matcher = pattern.matcher(line);
        while (matcher.find()){
            String textWithComma = matcher.group();
            String textWithCommaRemoved = matcher.group().replaceAll(",", "");
            line = line.replace(textWithComma, textWithCommaRemoved);
        }
        line = line.replaceAll("\"", "").replaceAll("\\P{Print}", "");
        log.debug("Line with quotes removed={}", line);
        return line;
    }
}
