package homework;

public class Primes {
    public static void main(String[] args) {
        for (int i = 2; i < 101; i++) {
            if (isPrime(i)) {
                System.out.println(i);
            }
        }
    }
    public static boolean isPrime(int n) {
        for (int i = 2; i<=(int)Math.pow(n, 0.5)+1; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
