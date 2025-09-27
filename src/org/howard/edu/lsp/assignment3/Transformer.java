package org.howard.edu.lsp.assignment3;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class Transformer {
    public List<String[]> transform(List<String[]> rows) {
        List<String[]> out = new ArrayList<>();
        // add header
        out.add(new String[]{"ProductID","Name","Price","Category","PriceRange"});

        for (String[] parts : rows) {
            if (parts.length != 4) continue;

            String idStr = parts[0].trim();
            String name  = parts[1].trim();
            String priceStr = parts[2].trim();
            String category = parts[3].trim();

            int productId;
            BigDecimal price;
            try {
                productId = Integer.parseInt(idStr);
                price = new BigDecimal(priceStr);
            } catch (NumberFormatException ex) {
                continue;
            }

            String upperName = name.toUpperCase();

            BigDecimal finalPrice = price;
            if (category.equalsIgnoreCase("Electronics")) {
                finalPrice = price.multiply(BigDecimal.valueOf(0.90));
            }
            finalPrice = finalPrice.setScale(2, RoundingMode.HALF_UP);

            if (category.equalsIgnoreCase("Electronics")
                    && finalPrice.compareTo(BigDecimal.valueOf(500.00)) > 0) {
                category = "Premium Electronics";
            }

            String priceRange = computePriceRange(finalPrice);

            out.add(new String[]{
                Integer.toString(productId),
                upperName,
                finalPrice.toPlainString(),
                category,
                priceRange
            });
        }
        return out;
    }

    private String computePriceRange(BigDecimal p) {
        if (p.compareTo(BigDecimal.valueOf(10.00)) <= 0)  return "Low";
        if (p.compareTo(BigDecimal.valueOf(100.00)) <= 0) return "Medium";
        if (p.compareTo(BigDecimal.valueOf(500.00)) <= 0) return "High";
        return "Premium";
    }
}
