package task4;

import java.math.BigDecimal;
import java.util.*;

public class task4 {
    public static final Set<Character> VOWEL_SYMBOLS = new HashSet<>();

    static{
        Collections.addAll(VOWEL_SYMBOLS, 'A', 'E', 'I', 'O', 'U', 'Y', 'a','e','i','o','u','y');
    }

    public static void main(String[] args) {
        System.out.println("1 task:");
        System.out.println(formatByBessie(10, 7,"hello my name is Bessie and this is my essay"));
        System.out.println(" ");
        System.out.println("2 task:");
        System.out.println(split("()()()"));
        System.out.println(split("((()))"));
        System.out.println(split("((()))(())()()(()())"));
        System.out.println(split("((())())(()(()()))"));
        System.out.println(" ");
        System.out.println("3 task:");
        System.out.println(toCamelCase("hello_edabit"));
        System.out.println(toSnakeCase("helloEdabit"));
        System.out.println(toCamelCase("is_modal_open"));
        System.out.println(toSnakeCase("getColor"));
        System.out.println(" ");
        System.out.println("4 task:");
        System.out.println(overTime(9, 17, 30, 1.5));
        System.out.println(overTime(16, 18, 30, 1.8));
        System.out.println(overTime(13.25, 15, 30, 1.5));
        System.out.println(" ");
        System.out.println("5 task:");
        System.out.println(BMI("205 pounds", "73 inches"));
        System.out.println(BMI("55 kilos", "1.65 meters"));
        System.out.println(BMI("154 pounds", "2 meters"));
        System.out.println(" ");
        System.out.println("6 task:");
        System.out.println(bugger(39));
        System.out.println(bugger(999));
        System.out.println(bugger(4));
        System.out.println(" ");
        System.out.println("7 task:");
        System.out.println(toStarShorthand("abbccc"));
        System.out.println(toStarShorthand("77777geff"));
        System.out.println(toStarShorthand(""));
        System.out.println(" ");
        System.out.println("8 task:");
        System.out.println(doesRhyme("Sam I am!" , "Green eggs and ham."));
        System.out.println(doesRhyme("Sam I am!", "Green eggs and HAM."));
        System.out.println(doesRhyme("You are off to the races", "a splendid day."));
        System.out.println(doesRhyme("and frequently do?", "you gotta move."));
        System.out.println(" ");
        System.out.println("9 task:");
        System.out.println(trouble(1222345, 12345));
        System.out.println(trouble(666789, 12345667));
        System.out.println(trouble(33789, 12345337));
        System.out.println(" ");
        System.out.println("10 task:");
        System.out.println(countUniqueBooks("AZYWABBCATTTA",'A'));
        System.out.println(countUniqueBooks("MAAMBBCATTMCMMBM",'M'));
        System.out.println(countUniqueBooks("ZZABCDEF",'Z'));
    }

    public static String formatByBessie(int n, int k, String s){
        String[] words = s.split("\\s");
        StringBuilder result = new StringBuilder();
        int tempLen = 0;
        for (int i = 0; i < n; i++) {
            if (tempLen + words[i].length() <= k){
                result.append(words[i]);
                result.append(" ");
                tempLen += words[i].length();
            }
            else{
                tempLen = words[i].length();
                result.append("\n");
                result.append(words[i]);
                if (i != n - 1){
                    result.append(" ");
                }
            }
        }
        return result.toString();
    }

    public static  List<String> split(String brackets){
        List<String> clusterOfBrackets = new ArrayList<>();
        int flag = 0;
        StringBuilder builder = new StringBuilder();
        for (char bracket : brackets.toCharArray()){
            if (bracket == '('){
                flag +=1;
                builder.append("(");
            }
            else{
                flag -=1;
                builder.append(")");
            }
            if (flag == 0){
                clusterOfBrackets.add(builder.toString());
                builder = new StringBuilder();
            }
        }
        return clusterOfBrackets;
    }

    public static String toCamelCase(String s){
        String[] word = s.split("_");
        StringBuilder result = new StringBuilder();
        result.append(word[0]);
        for (int i = 1; i < word.length; i++) {
            result.append(word[i].substring(0,1).toUpperCase()+word[i].substring(1));
        }
        return result.toString();
    }

    public static String toSnakeCase(String s){
        String[] word = s.split("(?=\\p{Upper})");
        StringBuilder result = new StringBuilder();
        result.append(word[0]);
        for (int i = 1; i < word.length; i++) {
            result.append("_");
            result.append(word[i].substring(0,1).toLowerCase()+word[i].substring(1));
        }
        return result.toString();
    }

    public static String overTime(double start, double end, double salary, double m){
        double result = 0;
        if (end <= 17) {
            result = (end - start) * salary;
        } else {
            result = (end - 17) * m * salary + (17 - start) * salary;
        }
        return "$" + String.valueOf(result);
    }

    public static String BMI(String w, String h){
        Map<String, Double> map = new HashMap<>();
        map.put("pounds", 0.454);
        map.put("kilos", 1.0);
        map.put("meters", 1.0);
        map.put("inches", 0.0254);
        String[] arrayW = w.split("\\s");
        String[] arrayH = h.split("\\s");
        double doubleHeight = Double.parseDouble(arrayH[0]) * map.get(arrayH[1]);
        double doubleWeight = Double.parseDouble(arrayW[0]) * map.get(arrayW[1]);
        double BMI = doubleWeight / (doubleHeight * doubleHeight);
        if (BMI < 18.5){
            return  String.format("%.1f",BMI) + "  Underweight";
        } else if (BMI < 24.9) {
            return String.format("%.1f",BMI) + " Normal weight";
        }
        else{
            return String.format("%.1f",BMI) + " Overweight";
        }
    }

    public static int bugger(int num){
        int c = 0;
        int sum = 0;
        while (num / 10 != 0) {
            c += 1;
            num = multiplyAllDigitOfNumber(num);
        }
        return c;
    }

    private static int multiplyAllDigitOfNumber(int num){
        int mul = 1;
        while (num > 0){
            mul *= num % 10;
            num /= 10;
        }
        return mul;
    }

    public static String toStarShorthand(String s){
        StringBuilder result = new StringBuilder();
        char[] chars = s.toCharArray();
        int c = 1;
        char letter;
        for (int i = 0; i < chars.length - 1; i++) {
            letter = chars[i];
            if (letter == chars[i+1]) {
                c += 1;
                if (i == chars.length-2) {
                    result.append(letter+"*"+c);
                }
            } else {
                if (c == 1) {
                    result.append(letter);
                    continue;
                }
                result.append(letter+"*"+c);
                c = 1;
            }
        }
        return result.toString();
    }

    public static boolean doesRhyme(String s1, String s2){
        String[] firstWords = s1.split("\\s");
        String[] secondWords = s2.split("\\s");
        Set<Character> firstVowelLetters = findVowelLetters(firstWords[firstWords.length-1]);
        Set<Character> secondVowelLetters = findVowelLetters(secondWords[secondWords.length-1]);
        Set<Character> one = new HashSet<>(firstVowelLetters);
        Set<Character> two = new HashSet<>(secondVowelLetters);
        one.removeAll(secondVowelLetters);
        two.removeAll(firstVowelLetters);
        return one.isEmpty() && two.isEmpty();
    }


    private static Set<Character> findVowelLetters(String s){
        Set<Character> vowelLetters = new HashSet<>();
        char[] letters = s.toCharArray();
        for (char letter : letters){
            if (Character.isLetter(letter) && VOWEL_SYMBOLS.contains(letter)){
                vowelLetters.add(Character.toLowerCase(letter));
            }
        }
        return vowelLetters;
    }

    static public boolean trouble(int a, int b){
        char[] first = String.valueOf(a).toCharArray();
        char[] second = String.valueOf(b).toCharArray();
        for (int i=2; i<first.length;i++){
            for (int j=1; j<second.length;j++){
                if (first[i]==first[i-1]) {
                    if (first[i]==first[i-2]) {
                        if (second[j]==second[j-1]){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public static int countUniqueBooks(String s, char b) {
        String[] words = s.split(Character.toString(b), -1);
        StringBuilder result = new StringBuilder();
        for (int i = 1; i < words.length-1; i = i + 2) {
            char[] letters = words[i].toCharArray();
            for (char lt : letters)
                if (result.indexOf(Character.toString(lt)) == -1)
                    result.append(lt);
        }
        return result.length();
    }
}
