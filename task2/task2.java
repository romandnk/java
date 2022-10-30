package task2;

import java.util.Arrays;

public class task2 {
    public static void main(String[] args) {
        System.out.println("1 task:");
        System.out.println(repeat("mice",5));
        System.out.println(repeat("hello",3));
        System.out.println(repeat("stop",1));
        System.out.println(" ");
        System.out.println("2 task:");
        System.out.println(differenceMaxMin(new int[]{10, 4, 1, 4, -10, -50, 32, 21}));
        System.out.println(differenceMaxMin(new int[]{44, 32, 86, 19}));
        System.out.println(" ");
        System.out.println("3 task:");
        System.out.println(isAvgWhole(new int[]{1, 3}));
        System.out.println(isAvgWhole(new int[]{1, 2, 3, 4}));
        System.out.println(isAvgWhole(new int[]{1, 5, 6}));
        System.out.println(" ");
        System.out.println("4 task:");
        System.out.println(Arrays.toString(cumulativeSum(new int[]{1, 2, 3})));
        System.out.println(Arrays.toString(cumulativeSum(new int[]{1, -2, 3})));
        System.out.println(Arrays.toString(cumulativeSum(new int[]{3, 3, -2, 408, 3, 3})));
        System.out.println(" ");
        System.out.println("5 task:");
        System.out.println(getDecimalPlaces("43.20"));
        System.out.println(getDecimalPlaces("400"));
        System.out.println(getDecimalPlaces("3.1"));
        System.out.println(" ");
        System.out.println("6 task:");
        System.out.println(fibonacci(3));
        System.out.println(fibonacci(7));
        System.out.println(fibonacci(12));
        System.out.println(" ");
        System.out.println("7 task:");
        System.out.println(isValid("59001"));
        System.out.println(isValid("853a7"));
        System.out.println(isValid("732 32"));
        System.out.println(isValid("393939"));
        System.out.println(" ");
        System.out.println("8 task:");
        System.out.println(isStrangePair("ratio", "orator"));
        System.out.println(isStrangePair("sparkling", "groups"));
        System.out.println(isStrangePair("bush", "hubris"));
        System.out.println(isStrangePair("", ""));
        System.out.println(" ");
        System.out.println("9 task:");
        System.out.println(isPrefix("automation", "auto-"));
        System.out.println(isSuffix("arachnophobia", "-phobia"));
        System.out.println(isPrefix("retrospect", "sub-"));
        System.out.println(isSuffix("vocation", "-logy"));
        System.out.println(" ");
        System.out.println("10 task:");
        System.out.println(boxSeq(0));
        System.out.println(boxSeq(1));
        System.out.println(boxSeq(2));
        System.out.println(boxSeq(3));
        System.out.println(boxSeq(4));
        System.out.println(boxSeq(5));
        System.out.println(boxSeq(6));
    }

    // 1
    public static String repeat(String str, int n){
        StringBuilder builder = new StringBuilder();
        char[] symbols = str.toCharArray();
        for (int i = 0; i < symbols.length; i++) {
            for (int j = 0; j < n; j++) {
                builder.append(symbols[i]);
            }
        }
        return builder.toString();
    }

    //2
    public static int differenceMaxMin(int[] arr){
        Arrays.sort(arr);
        return arr[arr.length-1] - arr[0];
    }

    //3
    public static boolean isAvgWhole(int[] arr){
        double sum = 0;
        for (int j : arr) {
            sum += j;
        }
        double averageValue = sum / arr.length;
        return averageValue == (int) averageValue;
    }

    //4
    public static int[] cumulativeSum(int[] arr){
        int[] resultArray = new int[arr.length];
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {
                sum += arr[j];
            }
            resultArray[i] = sum + arr[i];
            sum = 0;
        }
        return resultArray;
    }

    //5
    public static int getDecimalPlaces(String num){
        String[] arr = num.split("\\.");
        if (arr.length == 2) {
            return arr[1].length();
        }
        return 0;
    }

    //6
    public static int fibonacci(int n){
        int a = 1;
        int b = 1;
        int result = 0;
        for (int i = 0; i < n-1 ; i++) {
            result = a + b;
            a = b;
            b = result;
        }
        return result;
    }

    //7
    public static boolean isValid(String s){
        if (s.length()!=5) {
            return false;
        }
        for (int i = 0; i < s.length(); i++) {
            char letter = s.charAt(i);
            if (!(Character.isDigit(letter))) {
                return false;
            }
        }
        return true;
    }

    //8
    public static boolean isStrangePair(String s1, String s2){
        if (s1.length()==0 && s2.length()==0) {
            return true;
        }
        if (s1.length()== 0 || s2.length() == 0) {
            return false;
        }
        if ((s1.charAt(0) == s2.charAt(s2.length()-1)) && (s1.charAt(s1.length()-1) == s2.charAt(0))) {
            return true;
        }
        return false;
    }

    //9
    public static boolean isPrefix(String word, String prefix){
        return word.startsWith(prefix.replace("-", ""));
    }
    public static boolean isSuffix(String word, String suffix){
        return word.endsWith(suffix.replace("-", ""));
    }

    //10
    public static int boxSeq(int number){
        if (number % 2 == 0) {
            return number;
        } else {
            return number + 2;
        }
    }
}
