import java.util.ArrayList;
import java.util.List;

public class Main {
    
    public static void main(String[] args){
        String K = "101101010010" + "100101101011";
        String plaintext = "abc";
        int[] plaintextArray = alphabetToIntArray(plaintext);
        List<String> binaryString = intArrayToBinaryString5(plaintextArray);

        String test1 = bitExpansion("10110101");
        System.out.println(test1);
    }

    //QUESTION #1.1
    //changes a phrase into an array of integers - working
    public static int[] alphabetToIntArray(String string){
        string = string.toLowerCase();
        char[] stringArray = string.toCharArray();
        String alphabet = "abcdefghijklmnopqrstuvwxyz .,?()";
        char[] aplhaArray = alphabet.toCharArray();
        int[] zSpace = new int[stringArray.length];
        
        for(int i = 0; i < stringArray.length; i++){
            for (int j = 0; j < aplhaArray.length; j++){
                if (stringArray[i] == aplhaArray[j]){
                    zSpace[i] = j;
                }
            }
        }

        return zSpace;
    }

    public static List<String> intArrayToBinaryString5(int[] array) {
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

    public static List<String> intArrayToBinaryString4(int[] array) {
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

    //changes a binary string to integer array
    public static int[] binaryStringToIntArray(List<String> string) {
        int[] array = new int[string.size()];

        for (int i = 0; i < array.length; i++){
            array[i] = Integer.parseInt(string.get(i), 2);
        }
    
        return array;
    }


    //QUESTION 1.2
    //exapnds 8-bit bit string into 12 bits - working
    public static String bitExpansion(String string){
        int[][] sBox1 = {
            { 15, 1, 8, 14, 6, 11, 3, 4, 9, 7, 2, 13, 12, 0, 5, 10 },
            { 3, 13, 4, 7, 15, 2, 8, 14, 12, 0, 1, 10, 6, 9, 11, 5 }, 
            { 0, 14, 7, 11, 10, 4, 13, 1, 5, 8, 12, 6, 9, 3, 2, 15 }, 
            { 13, 8, 10, 1, 3, 15, 4, 2, 11, 6, 7, 12, 0, 5, 14, 9 } 
        };
    
        int[][] sBox2 = { 
            { 7, 13, 14, 3, 0, 6, 9, 10, 1, 2, 8, 5, 11, 12, 4, 15 }, 
            { 13, 8, 11, 5, 6, 15, 0, 3, 4, 7, 2, 12, 1, 10, 14, 9 }, 
            { 10, 6, 9, 0, 12, 11, 7, 13, 15, 1, 3, 14, 5, 2, 8, 4 }, 
            { 3, 15, 0, 6, 10, 1, 13, 8, 9, 4, 5, 11, 12, 7, 2, 14 }
        };

        int[] expansion = { 0, 1, 2, 3, 2, 3, 4, 5, 4, 5, 6, 7};
        String expandedBitString = "";
        int[] sBoxVals = new int[6];
        int[] array = new int[2];

        for (int i = 0; i < 12; i++){
            expandedBitString += string.charAt(expansion[i]);
        }

        
        List<String> list = getParts(expandedBitString);
        sBoxVals[0] = Integer.parseInt("00" + list.get(0).charAt(0) + list.get(0).charAt(5), 2);
        sBoxVals[1] = Integer.parseInt(list.get(0).substring(1, 5), 2);
        sBoxVals[2] = Integer.parseInt("00" + list.get(1).charAt(0) + list.get(0).charAt(5), 2);
        sBoxVals[3] = Integer.parseInt(list.get(1).substring(1, 5), 2);

        array[0] = sBox1[sBoxVals[0]] [sBoxVals[1]];
        array[1] = sBox2[sBoxVals[2]] [sBoxVals[3]];
       
        list = intArrayToBinaryString4(array);
        
        expandedBitString = "";
        for (int i = 0; i < 2; i++){
            expandedBitString += list.get(i);
        }
        
        return expandedBitString;
    }

    // working
    public static String XOR(String B, String K) {
        String product = "";
        for (int i = 0; i < 12; i++) {
            if (B.charAt(i) == K.charAt(i)) {
                product += "0";
            } else {
                product += "1";
            }
        }
        return product;
    }

    private static List<String> getParts(String string) {
        List<String> parts = new ArrayList<String>();
        
        parts.add(string.substring(0, string.length()/2));
        parts.add(string.substring(string.length()/2, string.length()));
        
        return parts;
    }
}