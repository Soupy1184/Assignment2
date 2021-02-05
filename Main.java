public class Main {
    public static void main(String[] args){
        String plaintext = "Hello world, what a beautiful day (today?).";
        alphabetToIntArray(plaintext);


    }

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
                    System.out.printf("%d, ", zSpace[i]);
                }
            }
        }

        return zSpace;
    }
}