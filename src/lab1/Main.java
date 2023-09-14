package lab1;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class Main {
    public static void main(String[] args) {
        cesarEncryption("abcde", 1);
        System.out.println();
        cesarBrutDecryption("bruteforceattack", 17, "cryptography");
        System.out.println();
    }



    public static void cesarEncryption(String word, int key) {
        keyVerification(key);
        List<Character> chars = new ArrayList<>();
        for (char ch : word.toUpperCase().replaceAll(" ", "").toCharArray()) {
            chars.add(ch);
        }
        List<Character> alphabetList = getAlphabetList();
        alphabetList.forEach(System.out::print);
        System.out.println();

        encryptionMethod(key, chars, alphabetList);
    }

    public static void cesarDecryption(String word, int key) {
        keyVerification(key);
        List<Character> chars = new ArrayList<>();
        for (char ch : word.toUpperCase().replaceAll(" ", "").toCharArray()) {
            chars.add(ch);
        }
        List<Character> alphabetList = getAlphabetList();
        alphabetList.forEach(System.out::print);
        System.out.println();
        decryptionMethod(key, chars, alphabetList);
    }

    public static void cesarBrutEncryption(String word, int key, String keyWord) {
        keyVerification(key);
        List<Character> chars = new ArrayList<>();
        for (char ch : word.toUpperCase().toCharArray()) {
            chars.add(ch);
        }
        List<Character> alphabetList = getAlphabetList();
        List<Character> keyWordList = new ArrayList<>();
        List<Character> keyWordRemove = new ArrayList<>();
        for (char ch : word.toUpperCase().replaceAll(" ", "").toCharArray()) {
            keyWordList.add(ch);
        }
        for (char ch : keyWord.toUpperCase().replaceAll(" ", "").toCharArray()) {
            keyWordRemove.add(ch);
        }
        keyWordRemove.forEach(alphabetList::remove);
        alphabetList.addAll(0, keyWordList.stream().distinct().toList());
        alphabetList.forEach(System.out::print);
        System.out.println();
        encryptionMethod(key, chars, alphabetList);
    }

    public static void cesarBrutDecryption(String word, int key, String keyWord) {
        keyVerification(key);
        List<Character> chars = new ArrayList<>();
        for (char ch : word.toUpperCase().toCharArray()) {
            chars.add(ch);
        }
        List<Character> alphabetList = getAlphabetList();
        var keyWordList = new ArrayList<>();
        List<Character> keyWordRemove = new ArrayList<>();
        char[] charArray = word.toUpperCase().replaceAll(" ", "").toCharArray();
        for (char ch : charArray) {
            keyWordList.add(ch);
        }
        for (char ch : keyWord.toUpperCase().replaceAll(" ", "").toCharArray()) {
            keyWordRemove.add(ch);
        }
        keyWordRemove.forEach(alphabetList::remove);
        System.out.println(keyWordRemove);
        alphabetList.addAll(0, keyWordRemove.stream().distinct().toList());
        alphabetList.forEach(System.out::print);
        System.out.println();
        decryptionMethod(key, chars, alphabetList);
    }

    private static void keyVerification(int key) {
        if ((key > 25) || (key <= 0)) {
            throw new NoSuchElementException("Introduce 0 < K < 26");
        }
    }

    public static int indexOfElement(List<Character> characterList, Character elementToFind) {
        int index = 0;
        for (Character element : characterList) {
            if (element.equals(elementToFind)) {
                return index;
            }
            index++;
        }
        return -1;
    }

    private static List<Character> getAlphabetList() {
        List<Character> alphabet = new ArrayList<>();
        for (char letter = 'A'; letter <= 'Z'; letter++) {
            alphabet.add(letter);
        }
        return alphabet;
    }

    private static void encryptionMethod(int key, List<Character> chars, List<Character> alphabetList) {
        chars.stream().map(character -> {
            int i = indexOfElement(alphabetList, character);
            if ((i + key) >= alphabetList.size()) {
                return alphabetList.get(indexOfElement(alphabetList, character) - 26 + key);
            }
            return alphabetList.get(indexOfElement(alphabetList, character) + key);
        }).forEach(System.out::print);
    }

    private static void decryptionMethod(int key, List<Character> chars, List<Character> alphabetList) {
        chars.stream().map(character -> {
            int i = indexOfElement(alphabetList, character);
            if ((i - key) < 0) {
                return alphabetList.get(i + 26 - key);
            }
            return alphabetList.get(i - key);
        }).forEach(System.out::print);
    }
}

//qwertyuiopasdfghjklzxcvbnm

//UZHVWSXLRTBCEIJKMNODAGYFQP