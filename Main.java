import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args){
        String K = "101101010010100101101011";
        String plaintext = "how do you like computer science";
        String plaintextCBC = "cryptography is an important tool for network security." +
        " but there are other issues for network security.";
      
        //QUESTION 1 - ENCRYPTION 
        String encryptString = encryptDES(plaintext, K);
        //OUTPUT CIPHERTEXT - plaintext to ciphertext
        System.out.println("Output for question 1: " + encryptString);

        //QUESTION 2 - DECRYPTION
        String decryptString = decryptDES(encryptString, K);
        //OUTPUT PLAINTEXT - ciphertext decrypted back to plaintext
        System.out.println("Output for question 2: " + decryptString);

        //QUESTION 3 - ENCRYPTION CBC MODE
        String cbcModeEncryptString = encryptDESmodeCBC(plaintextCBC, K);
        //OUTPUT CIPHERTEXT - plaintextCBC encryprted to ciphertext
        System.out.println("Output for question 3: " + cbcModeEncryptString);

        //QUESTION 4 - MILLER-RABIN
        MillerRabin test = new MillerRabin(942979);
        MillerRabin test2 = new MillerRabin(33773);
        // System.out.println("k: " + test.getK() + ", a: " + test.getA() + ", m: " + test.getM());
        // System.out.println("k: " + test2.getK() + ", a: " + test2.getA() + ", m: " + test.getM());
    }


    // QUESTION #1.1
    // changes a phrase into an array of integers - working
    private static int[] alphabetToIntArray(String string) {
        string = string.toLowerCase();
        char[] stringArray = string.toCharArray();
        String alphabet = "abcdefghijklmnopqrstuvwxyz .,?()";
        char[] aplhaArray = alphabet.toCharArray();
        int[] zSpace = new int[stringArray.length];

        for (int i = 0; i < stringArray.length; i++) {
            for (int j = 0; j < aplhaArray.length; j++) {
                if (stringArray[i] == aplhaArray[j]) {
                    zSpace[i] = j;
                }
            }
        }

        return zSpace;
    }

    //takes and int array and transforms to binary string, each int is 5-bit string
    private static List<String> intArrayToBinaryString5(int[] array) {
        List<String> string = new ArrayList<String>();
        int count;

        for (int i = 0; i < array.length; i++) {
            count = Integer.toBinaryString(array[i]).length();
            switch (count) {
                case 1:
                    string.add("0000" + Integer.toBinaryString(array[i]));
                    break;
                case 2:
                    string.add("000" + Integer.toBinaryString(array[i]));
                    break;
                case 3:
                    string.add("00" + Integer.toBinaryString(array[i]));
                    break;
                case 4:
                    string.add("0" + Integer.toBinaryString(array[i]));
                    break;
                case 5:
                    string.add(Integer.toBinaryString(array[i]));
                    break;
                default:
                    System.out.println("Error in element " + array[i]);
            }
        }

        return string;
    }

    //USED FOR INTEGER PARSING
    private static List<String> intArrayToBinaryString4(int[] array) {
        List<String> string = new ArrayList<String>();
        int count;
        for (int i = 0; i < array.length; i++) {
            count = Integer.toBinaryString(array[i]).length();
            switch (count) {
                case 1:
                    string.add("000" + Integer.toBinaryString(array[i]));
                    break;
                case 2:
                    string.add("00" + Integer.toBinaryString(array[i]));
                    break;
                case 3:
                    string.add("0" + Integer.toBinaryString(array[i]));
                    break;
                case 4:
                    string.add(Integer.toBinaryString(array[i]));
                    break;
                default:
                    System.out.println("Error in element " + array[i]);
            }
        }
        return string;
    }

    //Question 1.1 - Inverse int array to alphabet
    private static String intArrayToAlphabet(int[] array){
        String string = "";
        String alphabet = "abcdefghijklmnopqrstuvwxyz .,?()";

        for (int i = 0; i < array.length; i++){
            string += alphabet.charAt(array[i]);
        }

        return string;
    }

    // changes a binary string to integer array
    private static int[] binaryStringToIntArray(List<String> string) {
        int[] array = new int[string.size()];

        for (int i = 0; i < array.length; i++) {
            array[i] = Integer.parseInt(string.get(i), 2);
        }

        return array;
    }

    // QUESTION 1.2
    // encryption function
    private static String functionF(String string, String key) {
        int[][] sBox1 = { { 15, 1, 8, 14, 6, 11, 3, 4, 9, 7, 2, 13, 12, 0, 5, 10 },
                { 3, 13, 4, 7, 15, 2, 8, 14, 12, 0, 1, 10, 6, 9, 11, 5 },
                { 0, 14, 7, 11, 10, 4, 13, 1, 5, 8, 12, 6, 9, 3, 2, 15 },
                { 13, 8, 10, 1, 3, 15, 4, 2, 11, 6, 7, 12, 0, 5, 14, 9 } };

        int[][] sBox2 = { { 7, 13, 14, 3, 0, 6, 9, 10, 1, 2, 8, 5, 11, 12, 4, 15 },
                { 13, 8, 11, 5, 6, 15, 0, 3, 4, 7, 2, 12, 1, 10, 14, 9 },
                { 10, 6, 9, 0, 12, 11, 7, 13, 15, 1, 3, 14, 5, 2, 8, 4 },
                { 3, 15, 0, 6, 10, 1, 13, 8, 9, 4, 5, 11, 12, 7, 2, 14 } };

        int[] expansion = { 0, 1, 2, 3, 2, 3, 4, 5, 4, 5, 6, 7 };
        String expandedBitString = "";
        int[] sBoxVals = new int[6];
        int[] array = new int[2];

        for (int i = 0; i < 12; i++) {
            expandedBitString += string.charAt(expansion[i]);
        }

        expandedBitString = XOR(expandedBitString, key);

        List<String> list = stringToParts(expandedBitString, 6);
        sBoxVals[0] = Integer.parseInt("00" + list.get(0).charAt(0) + list.get(0).charAt(5), 2);
        sBoxVals[1] = Integer.parseInt(list.get(0).substring(1, 5), 2);
        sBoxVals[2] = Integer.parseInt("00" + list.get(1).charAt(0) + list.get(1).charAt(5), 2);
        sBoxVals[3] = Integer.parseInt(list.get(1).substring(1, 5), 2);

        array[0] = sBox1[sBoxVals[0]][sBoxVals[1]];
        array[1] = sBox2[sBoxVals[2]][sBoxVals[3]];

        list = intArrayToBinaryString4(array);

        expandedBitString = stringListToString(list);

        return expandedBitString;
    }

    // xor two strings
    private static String XOR(String B, String K) {
        String product = "";
        for (int i = 0; i < B.length(); i++) {
            if (B.charAt(i) == K.charAt(i)) {
                product += "0";
            } else {
                product += "1";
            }
        }
        return product;
    }

    private static List<String> stringToParts(String string, int n){
        List<String> parts = new ArrayList<String>();
        int len = string.length();
        for (int i=0; i<len; i+=n){
            parts.add(string.substring(i, Math.min(len, i + n)));
        }
        return parts;
    }

    private static String stringListToString(List<String> list){
        String string = "";
        for (int i = 0; i < list.size(); i++){
            string += list.get(i);
        }
        return string;
    }

    private static String encryptDES(String plaintext, String K){
        //Question #1.3 / 1.4 ENCRYPTION
        //Initial Setup
        List<String> key = stringToParts(K, 12);

        int[] plaintextArray = alphabetToIntArray(plaintext);
        List<String> plainArrayList = intArrayToBinaryString5(plaintextArray);
        String concatBinString = stringListToString(plainArrayList);
        List<String> cipherArray = stringToParts(concatBinString, 16);
        
        //Round 0 - Encryption
        List<String> lefString0 = new ArrayList<String>();
        List<String> rightString0 = new ArrayList<String>();
        for (int j = 0; j < cipherArray.size(); j++){ //number of strings in the array
            List<String> string = stringToParts(cipherArray.get(j), 8);
            lefString0.add(string.get(0));
            rightString0.add(string.get(1));
        }

        //Round 1 - Encryption
        List<String> lefString1 = new ArrayList<String>();
        List<String> rightString1 = new ArrayList<String>();
        for (int i = 0; i < lefString0.size(); i++){
            lefString1.add(rightString0.get(i));
            String temp1 = lefString0.get(i);
            String temp2 = functionF(rightString0.get(i), key.get(0));
            rightString1.add(XOR(temp1, temp2));
        }

        //Round 2 - Encryption
        List<String> lefString2 = new ArrayList<String>();
        List<String> rightString2 = new ArrayList<String>();
        for (int i = 0; i < lefString1.size(); i++){
            lefString2.add(rightString1.get(i));
            String temp1 = lefString1.get(i);
            String temp2 = functionF(rightString1.get(i), key.get(1));
            rightString2.add(XOR(temp1, temp2));
        }

        //Putting the pieces back together
        String cipherBitString = "";
        for (int i = 0; i < lefString2.size(); i++){
            cipherBitString += lefString2.get(i);
            cipherBitString += rightString2.get(i);
        }
        List<String> cipherArrayList = stringToParts(cipherBitString, 5);
        int[] ciphertextArray = binaryStringToIntArray(cipherArrayList);
        String ciphertext = intArrayToAlphabet(ciphertextArray);

        return ciphertext;
    }

    private static String decryptDES(String ciphertext, String K){
        //DECRYPTION - QUESTION 2
        List<String> key = stringToParts(K, 12);
        //Initial setup
        int[] ciphertextArray = alphabetToIntArray(ciphertext);
        List<String> cipherArrayList = intArrayToBinaryString5(ciphertextArray);
        String concatBinString = stringListToString(cipherArrayList);
        List<String> plainArray = stringToParts(concatBinString, 16);

        //Round 0 - Decryption
        List<String> dleftString0 = new ArrayList<String>();
        List<String> drightString0 = new ArrayList<String>();
        for (int j = 0; j < plainArray.size(); j++){ //number of strings in the array
            List<String> string = stringToParts(plainArray.get(j), 8);
            dleftString0.add(string.get(0));
            drightString0.add(string.get(1));
        }

        //Round 1 - Decryption
        List<String> dleftString1 = new ArrayList<String>();
        List<String> drightString1 = new ArrayList<String>();
        for (int i = 0; i < dleftString0.size(); i++){
            drightString1.add(dleftString0.get(i));
            String temp1 = drightString0.get(i);
            String temp2 = functionF(dleftString0.get(i), key.get(1));
            dleftString1.add(XOR(temp1, temp2));
        }

        //Round 2 - Decryption
        List<String> dleftString2 = new ArrayList<String>();
        List<String> drightString2 = new ArrayList<String>();
        for (int i = 0; i < dleftString1.size(); i++){
            drightString2.add(dleftString1.get(i));
            String temp1 = drightString1.get(i);
            String temp2 = functionF(dleftString1.get(i), key.get(0));
            dleftString2.add(XOR(temp1, temp2));
        }

        //Putting decryption pieces back together
        String plainBitString = "";
        for (int i = 0; i < dleftString2.size(); i++){
            plainBitString += dleftString2.get(i);
            plainBitString += drightString2.get(i);
        }
        List<String> decryptArrayList = stringToParts(plainBitString, 5);
        int[] plaintextArray = binaryStringToIntArray(decryptArrayList);
        String plaintext = intArrayToAlphabet(plaintextArray);

        return plaintext;
    }

    private static String encryptDESmodeCBC(String plaintext, String K){
        //Question #1.3 / 1.4 ENCRYPTION
        //Initial Setup
        if (plaintext.length() % 16 != 0){
            int add = 16 - (plaintext.length() % 16);
            for (int i = 0; i < add; i++){
                plaintext += 'x';
            }
        }

        String initialVector = "";
        for (int i = 0; i < 16; i++){
            initialVector += (int) ((Math.random() * ((1 - 0) + 1)) + 0);
        }
        //prints initial vector so you know it is random
        System.out.println("Initial vector: " + initialVector);
        
        List<String> key = stringToParts(K, 12);

        int[] plaintextArray = alphabetToIntArray(plaintext);
        List<String> plainArrayList = intArrayToBinaryString5(plaintextArray);
        String concatBinString = stringListToString(plainArrayList);
        List<String> cipherArray = stringToParts(concatBinString, 16);

        List<String> lefString0 = new ArrayList<String>();
        List<String> rightString0 = new ArrayList<String>();
        List<String> lefString1 = new ArrayList<String>();
        List<String> rightString1 = new ArrayList<String>();
        List<String> lefString2 = new ArrayList<String>();
        List<String> rightString2 = new ArrayList<String>();

        for (int a = 0; a < cipherArray.size(); a++){
            // System.out.println("Initial Vector " + initialVector + ", Current Block: " + cipherArray.get(a));
            String xorInitVec = XOR(cipherArray.get(a), initialVector);
            // System.out.println("Initial Vector After XOR: " + xorInitVec);

            //Round 0 - Encryption
            List<String> string = stringToParts(xorInitVec, 8);
            lefString0.add(string.get(0));
            rightString0.add(string.get(1));
            
            //Round 1 - Encryption
            lefString1.add(rightString0.get(a));
            String temp1 = lefString0.get(a);
            String temp2 = functionF(rightString0.get(a), key.get(0));
            rightString1.add(XOR(temp1, temp2));
            
            //Round 2 - Encryption
            lefString2.add(rightString1.get(a));  
            String temp3 = lefString1.get(a);
            String temp4 = functionF(rightString1.get(a), key.get(1));
            rightString2.add(XOR(temp3, temp4));

            //initital vector = ciphertext for block 1
            initialVector = lefString2.get(a) + rightString2.get(a);
        }

        //Putting the pieces back together
        String cipherBitString = "";
        for (int i = 0; i < lefString2.size(); i++){
            cipherBitString += lefString2.get(i);
            cipherBitString += rightString2.get(i);
        }
        List<String> cipherArrayList = stringToParts(cipherBitString, 5);
        int[] ciphertextArray = binaryStringToIntArray(cipherArrayList);
        String ciphertext = intArrayToAlphabet(ciphertextArray);

        return ciphertext;
    }

}