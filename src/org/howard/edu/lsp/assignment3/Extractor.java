package org.howard.edu.lsp.assignment3;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Extractor {
    public List<String[]> readCsv(Path input) {
        List<String[]> rows = new ArrayList<>();
        if (!Files.exists(input)) {
            System.err.println("[ERROR] Missing input file: " + input.toString());
            return rows;
        }
        try (BufferedReader reader = Files.newBufferedReader(input, StandardCharsets.UTF_8)) {
            String line = reader.readLine(); // header
            if (line == null) return rows;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;
                rows.add(line.split(",", -1));
            }
        } catch (IOException e) {
            System.err.println("[ERROR] Read failed: " + e.getMessage());
        }
        return rows;
    }
}
