package org.howard.edu.lsp.finale.question1;

import java.security.SecureRandom;

/**
 * Password generator that uses letters only (A-Z, a-z).
 */
public class LettersPasswordStrategy implements PasswordGenerationStrategy {
    private static final String LETTERS =
            "ABCDEFGHIJKLMNOPQRSTUVWXYZ" +
            "abcdefghijklmnopqrstuvwxyz";

    private final SecureRandom secureRandom = new SecureRandom();

    @Override
    public String generate(int length) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int index = secureRandom.nextInt(LETTERS.length());
            char c = LETTERS.charAt(index);
            sb.append(c);
        }

        return sb.toString();
    }
}
