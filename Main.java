import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args){
        String K = "101101010010100101101011";
        String plaintext = "abc";
        int[] plaintextArray = alphabetToIntArray(plaintext);
        List<String> binaryString = intArrayToBinaryString(plaintextArray);

        
        
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

    public static List<String> intArrayToBinaryString(int[] array) {
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
        int[] expansion = { 0, 1, 2, 3, 2, 3, 4, 5, 4, 5, 6, 7};
        String expandedBitString = "";

        for (int i = 0; i < 12; i++){
            expandedBitString += string.charAt(expansion[i]);
        }

        return expandedBitString;
    }

    //working
    public static String XOR(String B, String K) {
        String product = "";
        for (int i = 0; i < 12; i++) {
            if (B.charAt(i) == K.charAt(i)) {
                product += "0";
            }
            else {
                product += "1";
            }
        }
        return product;
    }
}