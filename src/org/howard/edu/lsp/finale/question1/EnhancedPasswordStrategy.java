package org.howard.edu.lsp.finale.question1;

import java.security.SecureRandom;

/**
 * Enhanced password generator: A-Z, a-z, 0-9 using SecureRandom.
 */
public class EnhancedPasswordStrategy implements PasswordGenerationStrategy {
    private static final String ALLOWED =
            "ABCDEFGHIJKLMNOPQRSTUVWXYZ" +
            "abcdefghijklmnopqrstuvwxyz" +
            "0123456789";

    private final SecureRandom secureRandom = new SecureRandom();

    @Override
    public String generate(int length) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int index = secureRandom.nextInt(ALLOWED.length());
            char c = ALLOWED.charAt(index);
            sb.append(c);
        }

        return sb.toString();
    }
}
