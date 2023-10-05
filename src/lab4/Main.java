package lab4;

public class Main {
    public static void main(String[] args) {
        String binaryString1 = "01010101010101010101010101010101";
        String binaryString2 = "11001100110011001100110011001100";

        String concatenatedBinary = concatenateBinaryStrings(binaryString1, binaryString2);

        String performedFinalPermutation = performFinalPermutation(concatenatedBinary);

        String hexadecimalResult = binaryToHexadecimal(performedFinalPermutation);

        System.out.println("Concatenated Binary: " + concatenatedBinary);
        System.out.println("Concatenated Binary after Final Permutation: " + performedFinalPermutation);
        System.out.println("Hexadecimal Result: " + hexadecimalResult);
    }

    // I concatenate 2 strings
    public static String concatenateBinaryStrings(String binaryString1, String binaryString2) {
        return binaryString1 + binaryString2;
    }

    // I convert decimal to hexazecimal
    public static String binaryToHexadecimal(String binary) {
        int remainder = binary.length() % 4; //the binary should be divisor to 4
        if (remainder != 0) {
            int zerosToAdd = 4 - remainder;
            binary = "0".repeat(zerosToAdd) + binary;
        }
        StringBuilder hexadecimal = new StringBuilder();
        for (int i = 0; i < binary.length(); i += 4) {
            String chunk = binary.substring(i, i + 4);
            int decimalValue = Integer.parseInt(chunk, 2);
            String hexDigit = Integer.toHexString(decimalValue).toUpperCase();
            hexadecimal.append(hexDigit);
        }

        return hexadecimal.toString();
    }

//final permutation
    public static String performFinalPermutation(String inputBinary) {
        final int[] FINAL_PERMUTATION_TABLE = {
                40, 8, 48, 16, 56, 24, 64, 32, 39, 7, 47, 15, 55, 23, 63, 31,
                38, 6, 46, 14, 54, 22, 62, 30, 37, 5, 45, 13, 53, 21, 61, 29,
                36, 4, 44, 12, 52, 20, 60, 28, 35, 3, 43, 11, 51, 19, 59, 27,
                34, 2, 42, 10, 50, 18, 58, 26, 33, 1, 41, 9, 49, 17, 57, 25
        };


        if (inputBinary.length() != 64) {
            throw new IllegalArgumentException("Input should be 64 binary");
        }

        char[] outputArray = new char[64];
        char[] inputArray = inputBinary.toCharArray();

        for (int i = 0; i < 64; i++) {
            outputArray[i] = inputArray[FINAL_PERMUTATION_TABLE[i] - 1];
        }

        return new String(outputArray);
    }
}
