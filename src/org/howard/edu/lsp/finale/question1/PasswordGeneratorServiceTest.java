package org.howard.edu.lsp.finale.question1;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * JUnit tests for PasswordGeneratorService.
 */
public class PasswordGeneratorServiceTest {

    private PasswordGeneratorService service;

    @BeforeEach
    public void setup() {
        service = PasswordGeneratorService.getInstance();
        // ensure we start each test with no algorithm selected
        service.setAlgorithm(null);
    }

    @Test
    public void checkInstanceNotNull() {
        // verify that 'service' is not null
        assertNotNull(service);
    }

    @Test
    public void checkSingleInstanceBehavior() {
        PasswordGeneratorService second = PasswordGeneratorService.getInstance();

        // verify that both variables refer to the SAME object in memory
        assertSame(service, second);
    }

    @Test
    public void generateWithoutSettingAlgorithmThrowsException() {
        PasswordGeneratorService s = PasswordGeneratorService.getInstance();
        s.setAlgorithm(null);  // make sure no algorithm is set

        assertThrows(IllegalStateException.class, () -> {
            s.generatePassword(10);
        });
    }

    @Test
    public void basicAlgorithmGeneratesCorrectLengthAndDigitsOnly() {
        service.setAlgorithm("basic");
        String p = service.generatePassword(10);

        // correct length
        assertEquals(10, p.length());
        // digits only (0-9)
        assertTrue(p.matches("\\d+"));
    }

    @Test
    public void enhancedAlgorithmGeneratesCorrectCharactersAndLength() {
        service.setAlgorithm("enhanced");
        String p = service.generatePassword(12);

        // correct length
        assertEquals(12, p.length());
        // only letters and digits
        assertTrue(p.matches("[A-Za-z0-9]+"));
    }

    @Test
    public void lettersAlgorithmGeneratesLettersOnly() {
        service.setAlgorithm("letters");
        String p = service.generatePassword(8);

        // correct length
        assertEquals(8, p.length());
        // letters only (A-Z, a-z)
        assertTrue(p.matches("[A-Za-z]+"));
    }

    @Test
    public void switchingAlgorithmsChangesBehavior() {
        service.setAlgorithm("basic");
        String p1 = service.generatePassword(10);

        service.setAlgorithm("letters");
        String p2 = service.generatePassword(10);

        service.setAlgorithm("enhanced");
        String p3 = service.generatePassword(10);

        // basic: digits only
        assertTrue(p1.matches("\\d+"));

        // letters: letters only
        assertTrue(p2.matches("[A-Za-z]+"));

        // enhanced: letters and digits only
        assertTrue(p3.matches("[A-Za-z0-9]+"));
    }
}
