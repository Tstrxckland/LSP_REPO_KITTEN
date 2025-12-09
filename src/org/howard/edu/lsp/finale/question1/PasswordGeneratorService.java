package org.howard.edu.lsp.finale.question1;

/*
 * Design Patterns Used:
 * 1. Singleton — ensures only one shared instance of PasswordGeneratorService exists.
 *    This satisfies the requirement to provide a single shared access point.
 *
 * 2. Strategy — each password algorithm is implemented as a separate class
 *    that implements PasswordGenerationStrategy. This allows the behavior
 *    to be selected at runtime, expanded in the future, and swapped
 *    without modifying client code.
 *
 * Why These Patterns:
 * - The Singleton pattern ensures only one instance of the service is used across
 *   the application, matching the exam requirement for a single access point.
 *
 * - The Strategy pattern ensures password-generation algorithms are modular,
 *   interchangeable, and easy to extend — matching the requirements for future
 *   expansion, runtime selection, and swappable behavior.
 */

/**
 * Service class responsible for generating passwords using different algorithms.
 * Implements the Singleton and Strategy design patterns.
 */
public class PasswordGeneratorService {

    /** The one shared instance (Singleton). */
    private static PasswordGeneratorService instance;

    /** The currently selected password generation strategy. */
    private PasswordGenerationStrategy strategy;

    /** Private constructor to prevent external instantiation. */
    private PasswordGeneratorService() {}

    /**
     * Returns the single shared instance of the password generator service.
     *
     * @return the singleton instance
     */
    public static PasswordGeneratorService getInstance() {
        if (instance == null) {
            instance = new PasswordGeneratorService();
        }
        return instance;
    }

    /**
     * Selects the password-generation algorithm by name.
     *
     * @param name the algorithm name ("basic", "enhanced", or "letters")
     */
    public void setAlgorithm(String name) {
        if (name == null) {
            this.strategy = null;
            return;
        }

        switch (name.toLowerCase()) {
            case "basic":
                this.strategy = new BasicPasswordStrategy();
                break;

            case "enhanced":
                this.strategy = new EnhancedPasswordStrategy();
                break;

            case "letters":
                this.strategy = new LettersPasswordStrategy();
                break;

            default:
                throw new IllegalArgumentException("Unknown algorithm: " + name);
        }
    }

    /**
     * Generates a password by delegating to the selected algorithm.
     *
     * @param length the desired password length
     * @return the generated password
     * @throws IllegalStateException if no algorithm has been set
     */
    public String generatePassword(int length) {
        if (strategy == null) {
            throw new IllegalStateException(
                    "Algorithm must be set before generating a password."
            );
        }

        return strategy.generate(length);
    }
}
