package org.howard.edu.lsp.assignment3;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Loader {
    public void writeCsv(Path output, List<String[]> rows) {
        try {
            Files.createDirectories(output.getParent());
        } catch (IOException e) {
            System.err.println("[ERROR] Could not create output directory: " + e.getMessage());
            return;
        }
        try (BufferedWriter w = Files.newBufferedWriter(output, StandardCharsets.UTF_8)) {
            for (String[] r : rows) {
                w.write(String.join(",", r));
                w.newLine();
            }
        } catch (IOException e) {
            System.err.println("[ERROR] Write failed: " + e.getMessage());
        }
    }
}
