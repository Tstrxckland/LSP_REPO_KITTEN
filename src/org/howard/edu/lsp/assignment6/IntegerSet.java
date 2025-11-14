package org.howard.edu.lsp.assignment6;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple set implementation for integers backed by an ArrayList.
 * <p>
 * This class provides basic set operations such as add, remove, union,
 * intersect, diff, and complement. It intentionally preserves insertion
 * order for predictable toString() output.
 */
public class IntegerSet {
    /** Internal storage for set elements. No duplicates are allowed. */
    private List<Integer> set = new ArrayList<Integer>();

    public void clear() {
        set.clear();
    }

    /**
     * Removes all elements from this set.
     */

    public int length() {
        return set.size();
    }

    /**
     * Returns the number of elements currently in the set.
     *
     * @return element count (>= 0)
     */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof IntegerSet)) return false;
        IntegerSet other = (IntegerSet) o;
        if (this.set.size() != other.set.size()) return false;
        return this.set.containsAll(other.set) && other.set.containsAll(this.set);
    }

    /**
     * Compares this set to another object for equality. Two IntegerSet
     * instances are equal when they contain the same elements (order does not matter).
     *
     * @param o object to compare with
     * @return true if equal, false otherwise
     */

    public boolean contains(int value) {
        return set.contains(value);
    }

    /**
     * Checks whether the given value is present in the set.
     *
     * @param value integer to test
     * @return true if present, false otherwise
     */

    public int largest() {
        if (set.isEmpty()) {
            throw new IllegalStateException("Set is empty.");
        }
        int max = set.get(0);
        for (int value : set) {
            if (value > max) {
                max = value;
            }
        }
        return max;
    }

    /**
     * Returns the largest element in the set.
     *
     * @return largest integer
     * @throws IllegalStateException if the set is empty
     */

    public int smallest() {
        if (set.isEmpty()) {
            throw new IllegalStateException("Set is empty.");
        }
        int min = set.get(0);
        for (int value : set) {
            if (value < min) {
                min = value;
            }
        }
        return min;
    }

    /**
     * Returns the smallest element in the set.
     *
     * @return smallest integer
     * @throws IllegalStateException if the set is empty
     */

    public void add(int item) {
        if (!set.contains(item)) {
            set.add(item);
        }
    }

    /**
     * Adds the given integer to the set if not already present.
     *
     * @param item integer to add
     */

    public void remove(int item) {
        set.remove(Integer.valueOf(item));
    }

    /**
     * Removes the given integer from the set if present.
     *
     * @param item integer to remove
     */

    public void union(IntegerSet other) {
        if (other == null) return;
        for (int value : other.set) {
            if (!this.set.contains(value)) {
                this.set.add(value);
            }
        }
    }

    /**
     * Modifies this set to be the union of itself and {@code other}.
     * If {@code other} is null this operation is a no-op.
     *
     * @param other the other IntegerSet to union with
     */

    public void intersect(IntegerSet other) {
        if (other == null) {
            clear();
            return;
        }
        List<Integer> newSet = new ArrayList<Integer>();
        for (int value : set) {
            if (other.set.contains(value)) {
                newSet.add(value);
            }
        }
        set = newSet;
    }

    /**
     * Keeps only the elements present in both this set and {@code other}.
     * If {@code other} is null the set becomes empty.
     *
     * @param other the other IntegerSet to intersect with
     */

    public void diff(IntegerSet other) {
        if (other == null) return;
        List<Integer> newSet = new ArrayList<Integer>();
        for (int value : set) {
            if (!other.set.contains(value)) {
                newSet.add(value);
            }
        }
        set = newSet;
    }

    /**
     * Removes every element from this set that appears in {@code other}.
     * If {@code other} is null this operation is a no-op.
     *
     * @param other the IntegerSet whose elements should be removed from this set
     */

    public void complement(IntegerSet other) {
        if (other == null) {
            clear();
            return;
        }
        List<Integer> newSet = new ArrayList<Integer>();
        for (int value : other.set) {
            if (!this.set.contains(value)) {
                newSet.add(value);
            }
        }
        set = newSet;
    }

    /**
     * Replaces this set with the elements that appear in {@code other} but not in this set.
     * If {@code other} is null this set becomes empty.
     *
     * @param other the reference IntegerSet
     */

    public boolean isEmpty() {
        return set.isEmpty();
    }

    /**
     * Checks whether the set contains no elements.
     *
     * @return true if empty, false otherwise
     */

    @Override
    public String toString() {
        return set.toString();
    }

    /**
     * Returns a string representation of this set. The format is the same as
     * {@link java.util.ArrayList#toString()} for predictability in tests.
     *
     * @return string representation of the set
     */
}
