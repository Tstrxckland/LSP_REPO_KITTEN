package org.howard.edu.lsp.finale.question1;

import java.util.Random;

/**
 * Basic password generator: digits only (0-9) using java.util.Random.
 */
public class BasicPasswordStrategy implements PasswordGenerationStrategy {
    private static final String DIGITS = "0123456789";
    private final Random random = new Random();

    @Override
    public String generate(int length) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(DIGITS.length());
            char c = DIGITS.charAt(index);
            sb.append(c);
        }

        return sb.toString();
    }
}
