package org.howard.edu.lsp.midterm_exam.question2;

public class Main {
    public static void main(String[] args) {
        // Required exact output lines (mind spaces & the arrow character →)
        System.out.println("Circle radius 3.0 \u2192 area = " + AreaCalculator.area(3.0));
        System.out.println("Rectangle 5.0 x 2.0 \u2192 area = " + AreaCalculator.area(5.0, 2.0));
        System.out.println("Triangle base 10, height 6 \u2192 area = " + AreaCalculator.area(10, 6));
        System.out.println("Square side 4 \u2192 area = " + AreaCalculator.area(4));

        // Exception demo (any invalid value is fine)
        try {
            AreaCalculator.area(0); // will throw IllegalArgumentException
        } catch (IllegalArgumentException e) {
            System.out.println("Caught error: " + e.getMessage());
        }
    }

    /*
     * Conceptual note (2–3 sentences):
     * Overloading keeps a single, consistent operation name (area) while allowing
     * different parameter lists for shapes. This improves discoverability and
     * readability at the call site. Separate names (circleArea, rectangleArea, …)
     * can be reasonable when behaviors or units differ, but here the single concept
     * “compute area” fits cleanly with overloads.
     */
}
