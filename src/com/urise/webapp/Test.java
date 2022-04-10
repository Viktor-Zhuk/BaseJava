package com.urise.webapp;

import java.util.Arrays;

public class Test {

    static int[] vik = {55,13,5,3,10,16,78,1,6,45};

    public static void main(String[] args) {
        int index = 0;
        while (index < vik.length) {
            int indexNew = Arrays.binarySearch(vik, 0, vik.length, vik[index]);
            int indexNewAbs = Math.abs(indexNew) - 1;

            if (indexNew < 0 && index < indexNewAbs) {
                changeFromEnd(index, indexNewAbs);
            } else if (indexNew < 0 && index > indexNewAbs) {
                changeFromStart(index, indexNewAbs);
            } else {
                index++;
            }
        }
    }

    private static void changeFromStart(int index, int indexNew) {
        int numberForChange = vik[index];
        for (int i = index; i >= indexNew; i--) {
            if (i == indexNew) {
                vik[i] = numberForChange;
            } else {
                vik[i] = vik[i - 1];
            }
        }
    }

    private static void changeFromEnd(int index, int indexNew) {
        int numberForChange = vik[index];
        for (int i = index; i < indexNew; i++) {
            if (i == indexNew - 1) {
                vik[i] = numberForChange;
            } else {
                vik[i] = vik[i + 1];
            }
        }
    }

    private static void print() {
        for (int i = 0; i < vik.length; i++) {
            System.out.print(vik[i] + " ");
        }
    }
}
