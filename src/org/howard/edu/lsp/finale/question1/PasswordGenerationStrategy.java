package org.howard.edu.lsp.finale.question1;

/**
 * Strategy interface for password generation algorithms.
 */
public interface PasswordGenerationStrategy {
    /**
     * Generate a password of the given length.
     *
     * @param length the desired password length
     * @return the generated password string
     */
    String generate(int length);
}
