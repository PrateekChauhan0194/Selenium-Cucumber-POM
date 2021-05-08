import java.util.*;

public class JavaPractice {

    public static void main(String[] args) {
        JavaPractice jp = new JavaPractice();

        jp.isAnagram("Mother in law", "Hitler woman");
        jp.isAnagram("Father in law", "Hitler man");
        jp.sortCharArray(new char[]{'z', 'b', 'd', 'c', 'a', 'q'});
    }

    public void getOccurrences(String inputString) {
        System.out.println("\nPRINT OCCURRENCES OF CHARACTERS IN: " + inputString );
        char[] arrInputString = inputString.toCharArray();
        HashMap<Character, Integer> map = new HashMap<>();
        Set<Character> keySet;

        for(char c : arrInputString) {
            if(map.containsKey(c)) {
                map.put(c, map.get(c) + 1);
            }else {
                map.put(c, 1);
            }
        }
        keySet = map.keySet();
        for (char c : keySet) {
            System.out.print(c + "=" + map.get(c) + " ");
        }
        System.out.println();
    }

    public void printDuplicates(String inputString) {
        System.out.println("\nPRINT CHARACTERS HAVING OCCURRENCES IN STRING: " + inputString );
        char[] arrInputString = inputString.toCharArray();
        Set<Character> set = new HashSet<>();
        for(char c : arrInputString) {
            if(!set.add(c)) {
                System.out.print(c + ", ");
            }
        }
        System.out.println();
    }

    public void reverseString(String inputString) {
        System.out.println("\nREVERSING THE STRING: " + inputString + " (using iteration)");
        String strReverse = "";
        for (int i = inputString.length() - 1 ; i >= 0; i--)
            strReverse = strReverse.concat(String.valueOf(inputString.charAt(i)));
        System.out.println(strReverse);

        System.out.println("\nREVERSING THE STRING: " + inputString + " (using stringBuffer)");
        StringBuffer sb = new StringBuffer(inputString);
        System.out.println(sb.reverse());
    }

    public void isAnagram(String str1, String str2) {
        System.out.println("\nCHECKING IS GIVEN STRINGS ARE ANAGRAMS");
        char[] arrStr1 = str1.replace(" ", "").toLowerCase().toCharArray();
        char[] arrStr2 = str2.replace(" ", "").toLowerCase().toCharArray();

        // Sorting using predefined static sort method of Arrays class
        Arrays.sort(arrStr1);
        Arrays.sort(arrStr2);

        // Sorting using self defined sorting method
        arrStr1 = new JavaPractice().sortCharArray(arrStr1);
        arrStr2 = new JavaPractice().sortCharArray(arrStr2);

        if(Arrays.equals(arrStr1, arrStr2))
            System.out.println("'" + str1 + "' and '" + str2 + "' are ANAGRAMS");
        else
            System.out.println("'" + str1 + "' and '" + str2 + "' are not ANAGRAMS");
    }

    public char[] sortCharArray(char[] arrChar) {
        boolean isSorted = false;
        char temp;
        while (!isSorted) {
            isSorted = true;
            for (int i = 0 ; i < arrChar.length - 1 ; i++) {
                if(arrChar[i] > arrChar[i+1]) {
                    temp = arrChar[i];
                    arrChar[i] = arrChar[i+1];
                    arrChar[i+1] = temp;
                    isSorted = false;
                }
            }
        }
        System.out.println(arrChar);
        return arrChar;
    }

    //print occurrences of first character in a string
}
