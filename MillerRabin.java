public class MillerRabin {
    int k;
    int m;
    long n;
    long a;
    boolean isPrime;

    public MillerRabin(long n){
        this.n = n;

        for (int i = 0; i < 256; i++){
            findKM(n);
            chooseA(n);
            isPrime();
            if (isPrime == true){
                break;
            }
        }
        
        if(isPrime){
            System.out.println("The number " + n + " is prime.");
        }
        else{
            System.out.println("The number " + n + " is not prime.");
        }

    }

    private void findKM(long n){
        for (int i = 1; i < 20; i = i + 2){
            if (((n-1) / Math.pow(2, i)) % 1 == 0){
                this.k = i;
                this.m = (int) ((n - 1) / Math.pow(2, i));
            }
            // System.out.println(((n-1) / Math.pow(2, i)));
        }
    }

    private void chooseA(long n){
        this.a = (long) ((Math.random() * (((n-1) - 1) + 1)) + 1);
    }

    public void isPrime() {
        long b = (long) (Math.pow(a, m) % n);
        
        if (b == 1 % n){
            isPrime = true;
        }
        else{
            for(int i = 0; i < k - 1; i++){
                if (b == -1 % n){
                    isPrime = true;
                    break;
                }
                else{
                    b = (int) (Math.pow(b, 2) % n);
                }
            }
            isPrime = false;
        }  
    }

    public String getM(){
        return String.valueOf(m);
    }

    public String getK(){
        return String.valueOf(k);
    }

    public String getA(){
        return String.valueOf(a);
    }
}
