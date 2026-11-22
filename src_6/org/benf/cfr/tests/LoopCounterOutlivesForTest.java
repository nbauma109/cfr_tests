package org.benf.cfr.tests;

public class LoopCounterOutlivesForTest {
    private static int i = 73;

    public int markFirstVacant(int[] values) {
        int cursor = 0;
        for (; cursor < values.length && values[cursor] != 0; ++cursor) {
        }
        if (cursor < values.length) {
            values[cursor] = 9;
        }
        return cursor;
    }

    public int getFieldValue() {
        return i;
    }
}
