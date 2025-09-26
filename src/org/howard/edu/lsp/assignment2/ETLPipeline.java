package org.howard.edu.lsp.assignment2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class ETLPipeline {

    private static final Path INPUT_PATH  = Paths.get("data", "products.csv");
    private static final Path OUTPUT_PATH = Paths.get("data", "transformed_products.csv");

    public static void main(String[] args) {
        new ETLPipeline().run();
    }

    public void run() {
        int rowsRead = 0;
        int transformed = 0;
        int skipped = 0;

        if (!Files.exists(INPUT_PATH)) {
            System.err.println("[ERROR] Missing input file: " + INPUT_PATH.toString());
            printSummary(rowsRead, transformed, skipped);
            return;
        }

        try {
            Files.createDirectories(OUTPUT_PATH.getParent());
        } catch (IOException e) {
            System.err.println("[ERROR] Could not create output directory: " + e.getMessage());
            return;
        }

        try (BufferedReader reader = Files.newBufferedReader(INPUT_PATH, StandardCharsets.UTF_8);
             BufferedWriter writer = Files.newBufferedWriter(OUTPUT_PATH, StandardCharsets.UTF_8)) {

            writer.write("ProductID,Name,Price,Category,PriceRange");
            writer.newLine();

            String line = reader.readLine();
            if (line == null) {
                printSummary(rowsRead, transformed, skipped);
                System.out.println("Output written to: " + OUTPUT_PATH.toString());
                return;
            }

            while ((line = reader.readLine()) != null) {
                String trimmed = line.trim();
                if (trimmed.isEmpty()) continue;
                rowsRead++;
                String outputRow = transformLine(trimmed);
                if (outputRow == null) {
                    skipped++;
                } else {
                    writer.write(outputRow);
                    writer.newLine();
                    transformed++;
                }
            }
        } catch (IOException e) {
            System.err.println("[ERROR] I/O problem: " + e.getMessage());
        }

        printSummary(rowsRead, transformed, skipped);
        System.out.println("Output written to: " + OUTPUT_PATH.toString());
    }

    private String transformLine(String csvLine) {
        String[] parts = csvLine.split(",", -1);
        if (parts.length != 4) return null;

        String productIdStr = parts[0].trim();
        String name = parts[1].trim();
        String priceStr = parts[2].trim();
        String category = parts[3].trim();

        int productId;
        BigDecimal price;
        try {
            productId = Integer.parseInt(productIdStr);
            price = new BigDecimal(priceStr);
        } catch (NumberFormatException ex) {
            return null;
        }

        String originalCategory = category;
        String upperName = name.toUpperCase();

        BigDecimal finalPrice = price;
        if (category.equalsIgnoreCase("Electronics")) {
            finalPrice = price.multiply(BigDecimal.valueOf(0.90));
        }
        finalPrice = finalPrice.setScale(2, RoundingMode.HALF_UP);

        if (originalCategory.equalsIgnoreCase("Electronics")
                && finalPrice.compareTo(BigDecimal.valueOf(500.00)) > 0) {
            category = "Premium Electronics";
        }

        String priceRange = computePriceRange(finalPrice);

        return productId + "," + upperName + "," + finalPrice.toPlainString() + "," + category + "," + priceRange;
    }

    private static String computePriceRange(BigDecimal finalPrice) {
        int cmpLow10   = finalPrice.compareTo(BigDecimal.valueOf(10.00));
        int cmpLow100  = finalPrice.compareTo(BigDecimal.valueOf(100.00));
        int cmpLow500  = finalPrice.compareTo(BigDecimal.valueOf(500.00));

        if (cmpLow10 <= 0) return "Low";
        if (cmpLow100 <= 0) return "Medium";
        if (cmpLow500 <= 0) return "High";
        return "Premium";
    }

    private static void printSummary(int rowsRead, int transformed, int skipped) {
        System.out.println("=== Run Summary ===");
        System.out.println("Rows read:        " + rowsRead);
        System.out.println("Transformed:      " + transformed);
        System.out.println("Skipped (invalid): " + skipped);
    }
}
