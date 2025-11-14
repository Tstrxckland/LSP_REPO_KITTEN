package org.howard.edu.lsp.assignment6;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class IntegerSetTest {

    @Test
    public void testAddLengthAndContains_noDuplicates() {
        IntegerSet set = new IntegerSet();
        Assertions.assertTrue(set.isEmpty());
        Assertions.assertEquals(0, set.length());

        set.add(1);
        set.add(2);
        set.add(2);  // duplicate ignored
        set.add(-5);

        Assertions.assertEquals(3, set.length());
        Assertions.assertTrue(set.contains(1));
        Assertions.assertTrue(set.contains(2));
        Assertions.assertTrue(set.contains(-5));
        Assertions.assertFalse(set.contains(100));
    }

    @Test
    public void testClearAndIsEmpty() {
        IntegerSet set = new IntegerSet();
        set.add(1);
        set.add(2);
        Assertions.assertFalse(set.isEmpty());

        set.clear();

        Assertions.assertTrue(set.isEmpty());
        Assertions.assertEquals(0, set.length());
        Assertions.assertEquals("[]", set.toString());
    }

    @Test
    public void testLargestTypicalCase() {
        IntegerSet set = new IntegerSet();
        set.add(3);
        set.add(10);
        set.add(5);

        Assertions.assertEquals(10, set.largest());
    }

    @Test
    public void testSmallestTypicalCase() {
        IntegerSet set = new IntegerSet();
        set.add(3);
        set.add(10);
        set.add(-2);
        set.add(5);

        Assertions.assertEquals(-2, set.smallest());
    }

    @Test
    public void testLargestThrowsOnEmptySet() {
        IntegerSet set = new IntegerSet();
        Assertions.assertTrue(set.isEmpty());

        try {
            set.largest();
            Assertions.fail("Expected IllegalStateException for largest() on empty set");
        } catch (IllegalStateException e) {
            // expected
        }
    }

    @Test
    public void testSmallestThrowsOnEmptySet() {
        IntegerSet set = new IntegerSet();
        Assertions.assertTrue(set.isEmpty());

        try {
            set.smallest();
            Assertions.fail("Expected IllegalStateException for smallest() on empty set");
        } catch (IllegalStateException e) {
            // expected
        }
    }

    @Test
    public void testRemoveExistingAndNonExistingElements() {
        IntegerSet set = new IntegerSet();
        set.add(1);
        set.add(2);
        set.add(3);

        set.remove(2);
        Assertions.assertEquals(2, set.length());
        Assertions.assertFalse(set.contains(2));

        set.remove(100);
        Assertions.assertEquals(2, set.length());
        Assertions.assertTrue(set.contains(1));
        Assertions.assertTrue(set.contains(3));
    }

    @Test
    public void testEqualsSameContentsDifferentOrder() {
        IntegerSet set1 = new IntegerSet();
        set1.add(1);
        set1.add(2);
        set1.add(3);

        IntegerSet set2 = new IntegerSet();
        set2.add(3);
        set2.add(1);
        set2.add(2);

        Assertions.assertTrue(set1.equals(set2));
        Assertions.assertTrue(set2.equals(set1));
    }

    @Test
    public void testEqualsDifferentSetsOrNullOrDifferentType() {
        IntegerSet set1 = new IntegerSet();
        set1.add(1);
        set1.add(2);

        IntegerSet set2 = new IntegerSet();
        set2.add(1);
        set2.add(2);
        set2.add(3);

        Assertions.assertFalse(set1.equals(set2));
        Assertions.assertFalse(set1.equals(null));
    Assertions.assertNotEquals(set1, "hello");
    }

    @Test
    public void testUnionTypicalAndSelfUnion() {
        IntegerSet set1 = new IntegerSet();
        set1.add(1);
        set1.add(2);

        IntegerSet set2 = new IntegerSet();
        set2.add(2);
        set2.add(3);

        set1.union(set2);

        Assertions.assertEquals(3, set1.length());
        Assertions.assertTrue(set1.contains(1));
        Assertions.assertTrue(set1.contains(2));
        Assertions.assertTrue(set1.contains(3));

        set1.union(set1);
        Assertions.assertEquals(3, set1.length());
    }

    @Test
    public void testIntersectTypicalAndWithSelfAndDisjoint() {
        IntegerSet set1 = new IntegerSet();
        set1.add(1);
        set1.add(2);
        set1.add(3);

        IntegerSet set2 = new IntegerSet();
        set2.add(2);
        set2.add(3);
        set2.add(4);

        set1.intersect(set2);

        Assertions.assertEquals(2, set1.length());
        Assertions.assertTrue(set1.contains(2));
        Assertions.assertTrue(set1.contains(3));
        Assertions.assertFalse(set1.contains(1));
        Assertions.assertFalse(set1.contains(4));

        set1.intersect(set1);
        Assertions.assertEquals(2, set1.length());

        IntegerSet disjoint = new IntegerSet();
        disjoint.add(100);
        disjoint.add(200);
        set1.intersect(disjoint);
        Assertions.assertTrue(set1.isEmpty());
    }

    @Test
    public void testDiffTypicalAndSelfDiff() {
        IntegerSet set1 = new IntegerSet();
        set1.add(1);
        set1.add(2);
        set1.add(3);

        IntegerSet set2 = new IntegerSet();
        set2.add(2);
        set2.add(4);

        set1.diff(set2);

        Assertions.assertEquals(2, set1.length());
        Assertions.assertTrue(set1.contains(1));
        Assertions.assertTrue(set1.contains(3));
        Assertions.assertFalse(set1.contains(2));
        Assertions.assertFalse(set1.contains(4));

        set1.diff(set1);
        Assertions.assertTrue(set1.isEmpty());
    }

    @Test
    public void testComplementTypicalAndSelfComplement() {
        IntegerSet base = new IntegerSet();
        base.add(1);
        base.add(2);
        base.add(3);

        IntegerSet reference = new IntegerSet();
        reference.add(2);
        reference.add(3);
        reference.add(4);
        reference.add(5);

        base.complement(reference);

        Assertions.assertEquals(2, base.length());
        Assertions.assertTrue(base.contains(4));
        Assertions.assertTrue(base.contains(5));
        Assertions.assertFalse(base.contains(1));
        Assertions.assertFalse(base.contains(2));
        Assertions.assertFalse(base.contains(3));

        base.complement(base);
        Assertions.assertTrue(base.isEmpty());
    }

    @Test
    public void testToStringFormat() {
        IntegerSet set = new IntegerSet();
        Assertions.assertEquals("[]", set.toString());

        set.add(10);
        set.add(20);
        set.add(30);

        String repr = set.toString();
        Assertions.assertTrue(repr.startsWith("["));
        Assertions.assertTrue(repr.endsWith("]"));
        Assertions.assertEquals("[10, 20, 30]", repr);
    }
}
