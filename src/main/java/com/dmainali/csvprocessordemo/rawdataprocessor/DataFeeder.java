package com.dmainali.csvprocessordemo.rawdataprocessor;

import com.dmainali.csvprocessordemo.Entity.Product;
import com.dmainali.csvprocessordemo.Entity.ResultWrapper;
import com.dmainali.csvprocessordemo.connection.DBconnection;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class DataFeeder {

    String productSql = "INSERT INTO product(name, category_id) values(?,?)";
    String productVariantSQL = "INSERT INTO product_variant(product_id,sku,size,color,price,description) values(?,?,?,?,?,?)";
    String imageSql = "Insert into image(thumb,full_image,product_variant_id) values(?,?,?)";

    public ResultWrapper saveDemo(Set<Product> products) {
        AtomicInteger insertedProductCount = new AtomicInteger(0);
        AtomicInteger insertedProductVariantCount = new AtomicInteger(0);
        AtomicInteger insertedImageCount = new AtomicInteger(0);

        Map<Product, String> faultyData = new HashMap<>();

        Connection con = DBconnection.getConnection();

        try {
            con.prepareStatement("delete from image").executeUpdate();
            con.prepareStatement("delete from product_variant").executeUpdate();
            con.prepareStatement("delete from product").executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        long start = System.currentTimeMillis();

        products.parallelStream().forEach(product -> {
            try {
                PreparedStatement pstm = con.prepareStatement(productSql, Statement.RETURN_GENERATED_KEYS);
                long productInsertId = 0;
                pstm.setString(1, product.getProductName());
                pstm.setInt(2, 3);
                int affectedRows = pstm.executeUpdate();
                if (affectedRows == 0) {
                    log.error("Creating record failed, no record inserted." + product.getProductName());
                    return;
                }
                ResultSet generatedKeys = pstm.getGeneratedKeys();
                if (generatedKeys.next()) {
                    productInsertId = generatedKeys.getLong(1);
                } else {
                    log.error("Product created but no ID obtained for : " + product.getProductName());
                    return;
                }
                insertedProductCount.incrementAndGet();

                //--------------------------------------------------------------------------------//

                PreparedStatement pvStmt = con.prepareStatement(productVariantSQL, Statement.RETURN_GENERATED_KEYS);
                long productVariantID = 0;
                pvStmt.setLong(1, productInsertId);
                pvStmt.setString(2, product.getSku());
                pvStmt.setString(3, product.calculateColor());
                pvStmt.setString(4, product.calculateSize());
                pvStmt.setDouble(5, product.getPriceCurrent());
                pvStmt.setString(6, product.getSubcategory());
                int affectedRowsInPV = pvStmt.executeUpdate();
                if (affectedRowsInPV == 0) {
                    log.error("Creating product variant failed, no record inserted for productId :" + productInsertId);
                    return;
                }

                ResultSet generatedKeysPV = pvStmt.getGeneratedKeys();
                if (generatedKeysPV.next()) {
                    productVariantID = generatedKeysPV.getLong(1);
                } else {
                    log.debug("Generated productVariantId = " + productVariantID);
                    return;
                }
                insertedProductVariantCount.incrementAndGet();

                //--------------------------------------------------------------------------------------------------

                PreparedStatement iStmt = con.prepareStatement(imageSql);
                iStmt.setString(1, "/path/to/thumb/" + product.getSku());
                iStmt.setString(2, "/path/to/fullImage/" + product.getSku());
                iStmt.setLong(3, productVariantID);
                int affectedRowsInImage = iStmt.executeUpdate();
                if (affectedRowsInImage == 0) {
                    log.error("Creating image failed, no record inserted for productVariantId: " + productVariantID);
                    return;
                }
                insertedImageCount.incrementAndGet();
            } catch (SQLException e) {
                faultyData.put(product, e.getMessage());
            }
        });
        long end = System.currentTimeMillis();
        long timeTaken = end - start;
        log.info("Total Records saved in Product Table : " + insertedProductCount);
        log.info("Total Records saved in Product Variant Table : " + insertedProductVariantCount);
        log.info("Total Records saved in Image Table : " + insertedImageCount);

        return ResultWrapper.builder()
                .excelDataCount(products.size())
                .faultyData(faultyData)
                .timeTaken(timeTaken)
                .insertCount(insertedProductCount.get())
                .build();
    }
}
