package com.example.mutantApi.helpers;

public class DnaHelper {

    public static final int sequenceLength = 4;
    private static final String VALID_CHARACTERS = "ATCG";

    public static boolean checkSequence(String[] dna, int i, int j, int deltaI, int deltaJ) {
        char firstChar = dna[i].charAt(j);
        for (int k = 1; k < sequenceLength; k++) {
            if (dna[i + k * deltaI].charAt(j + k * deltaJ) != firstChar) {
                return false;
            }
        }
        return true;
    }

    public static boolean dnaValidator(String[] dna) {
        if(dna.length == 0) {
            return false;
        }

        int n = dna.length;


        for (String row : dna) {
            if (row.length() != n) {
                return false;
            }

            if (!row.matches("[" + VALID_CHARACTERS + "]+")) {
                return false;
            }
        }
        return true;
    }
}