package com.company;

//Puzzle.main (new String[]{"ABCDEFGHIJAB", "ABCDEFGHIJA", "ACEHJBDFGIAC"});

/*Lahendus 123456789012 + 12345678901 = 135802467913
D = 4
E = 5
F = 6
G = 7
A = 1
B = 2
C = 3
H = 8
I = 9
J = 0
Lahendusi kokku: 2
*/

import java.util.ArrayList;
import java.util.HashMap;

public class Puzzle {

    /** Solve the word puzzle.
     * @param args three words (addend1, addend2 and sum)
     */
    static String addend1;
    static String addend2;
    static String sum;
    static HashMap<String, Integer> map = new HashMap<String, Integer>();
    static ArrayList<String> letters = new ArrayList<String>();
    static int count = 0;


    public static void main (String[] args) {
        args = new String[]{"ABCDEFGHIJAB", "ABCDEFGHIJA", "ACEHJBDFGIAC"};
        if (args.length != 3) throw new RuntimeException("Programm eeldab kolme käsurea parameetrit");
        addend1 = args[0];
        addend2 = args[1];
        sum = args[2];
        addLetters(addend1 + addend2 + sum);
        tryNext(0);
        if (count == 0)
            System.out.println("Lahendust ei leidunud");
        else System.out.println("Lahendusi kokku: " + count);
    }

    public static void tryNext(int current){
        for (int i = 0; i < 10; i++) {
            if (!map.containsValue(i)) {
                map.put(letters.get(current), i);
                if (current < letters.size() - 1){
                    tryNext(current + 1);
                }
                else {
                    long a1 = convert(addend1);
                    long a2 = convert(addend2);
                    long s = convert(sum);
                    if (a1 != -1 && a2 != -1 && s != -1 && a1 + a2 == s){
                        count++;
                        if (count == 1){
                            System.out.println("Lahendus " + a1 + " + " + a2 + " = " + s);
                            for (String key : map.keySet()) {
                                System.out.println(key + " = " + map.get(key));
                            }
                        }
                    }
                }
                map.remove(letters.get(current));
            }
        }
    }

    public static long convert(String word){
        String number = "";
        for (int i = 0; i < word.length(); i++) {
            number = number + map.get(word.charAt(i) + "");
        }
        if (number.startsWith("0"))
            return -1;
        return Long.parseLong(number);
    }

    public static void addLetters(String word){
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) < 'A' || word.charAt(i) > 'Z')
                throw new RuntimeException("Vigane tähemärk :) : " + word.charAt(i));
            if (!letters.contains(word.charAt(i) + ""))
                letters.add(word.charAt(i) + "");
        }
    }

}