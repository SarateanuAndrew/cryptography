package lab3;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static List<Character> getAlphabet(){
        return "AĂÂBCDEFGHIÎJKLMNOPQRSȘTȚUVWXYZ".chars().mapToObj(i -> (char) i).toList();
    }
//    List<Character> alphabetEnglish = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".chars().mapToObj(i -> (char) i).toList();

    public static String encrypt(String plaintext, String key) {
        check(key);
        List<Character> plainList = plaintext.toUpperCase().replaceAll(" ", "").chars().mapToObj(i -> (char) i).toList();
        List<Character> keyList = key.toUpperCase().replaceAll(" ", "").chars().mapToObj(i -> (char) i).toList();
        List<Character> keyFullList = new ArrayList<>();
        int i = 0;
        int j;
        int k = 1;
        while (i < plainList.size()){
            j = i  - (keyList.size()  * (k - 1));
            keyFullList.add(keyList.get(j));
            if((i+1)/k == keyList.size()){
                k++;
            }
            i++;
        }
        List<Integer> plainPosition = plainList.stream().map(character -> getAlphabet().indexOf(character)).toList();
        List<Integer> keyFullPosition = keyFullList.stream().map(character -> getAlphabet().indexOf(character)).toList();
        List<Integer> difference = new ArrayList<>();

        int s = 0;
        while (s < plainPosition.size()){
            difference.add((plainPosition.get(s) + keyFullPosition.get(s)) % getAlphabet().size());
            s++;
        }
        return difference.stream()
                .map(integer -> getAlphabet().get(integer))
                .map(String::valueOf)
                .collect(Collectors.joining());
    }

    public static String decrypt(String ciphertext, String key) {
        check(key);
        StringBuilder plaintext = new StringBuilder();
        ciphertext = ciphertext.toUpperCase();
        key = key.toUpperCase();

        for (int i = 0, j = 0; i < ciphertext.length(); i++) {
            char currentChar = ciphertext.charAt(i);
            if (getAlphabet().contains(currentChar)) {
                int shift = getAlphabet().indexOf(key.charAt(j % key.length()));
                char decryptedChar = getAlphabet().get((getAlphabet().indexOf(currentChar) - shift + getAlphabet().size()) % getAlphabet().size());
                plaintext.append(decryptedChar);
                j++;
            } else {
                plaintext.append(currentChar);
            }
        }

        return plaintext.toString();
    }

    public static void check(String key){
        if (key.length() < 7){
            System.out.println("Is less then 7");
            throw new RuntimeException();
        }
    }

    public static void main(String[] args) {
//        String plaintext = "Per aspera ad astra";
        String plaintext = "Șarpele merge pe carare";
        String key = "Țarănoastra";

        String encryptedText = encrypt(plaintext, key);
        String decryptedText = decrypt(encryptedText, key);

        System.out.println("Original Text: " + plaintext);
        System.out.println("Encrypted Text: " + encryptedText);
        System.out.println("Decrypted Text: " + decryptedText);
    }
}