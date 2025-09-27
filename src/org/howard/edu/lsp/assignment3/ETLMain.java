package org.howard.edu.lsp.assignment3;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class ETLMain {
    public static void main(String[] args) {
        System.out.println("Starting ETL process (Assignment 3)…");

        Path input  = Paths.get("data", "products.csv");
        Path output = Paths.get("data", "transformed_products.csv");

        Extractor extractor   = new Extractor();
        Transformer transformer = new Transformer();
        Loader loader         = new Loader();

        // Extract
        List<String[]> rawData = extractor.readCsv(input);

        // Transform
        List<String[]> transformed = transformer.transform(rawData);

        // Load
        loader.writeCsv(output, transformed);

        System.out.println("ETL process completed!");
    }
}
