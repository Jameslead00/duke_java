package duke_java.module2.week4.vigenereProgram;
import java.util.*;
import edu.duke.*;
//import java.io.*;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder sb = new StringBuilder();
        for(int i = whichSlice; i < message.length(); i += totalSlices){
            sb.append(message.charAt(i));
        }

        return sb.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        //WRITE YOUR CODE HERE
        CaesarCracker cc = new CaesarCracker(mostCommon);
        for(int i = 0; i < klength; i++){
            String sliced = sliceString(encrypted, i, klength);
            key[i] = cc.getKey(sliced);
        }
        return key;
    }

    public void breakVigenere () {
        //WRITE YOUR CODE HERE
        FileResource fr = new FileResource();
        String msg = fr.asString();
        FileResource dict = new FileResource("dictionaries/English");
        //int [] key = tryKeyLength(msg,38, 'e');
        HashSet<String> dictionary = readDictionary(dict);
        //VigenereCipher vc = new VigenereCipher(key);
        //String decrypted = vc.decrypt(msg);
        //System.out.println(decrypted);
        String decrypted = breakForLanguage(msg, dictionary);
        System.out.println(decrypted.substring(0,100));
        int count = countWords(decrypted, dictionary);
        System.out.println("Count: " + count);
    }

    public HashSet<String> readDictionary(FileResource fr){
        HashSet<String> words = new HashSet<String>();
        for(String word : fr.words()){
            word = word.toLowerCase();
            words.add(word);
        }
        return words;
    }
    
    public int countWords(String message, HashSet<String> dictionary){
        int count = 0;
        for(String word : message.split("\\W+")){
            word = word.toLowerCase();
            if(dictionary.contains(word)){
                count++;
            }
        }
        return count;
    }

    public String breakForLanguage(String encrypted, HashSet<String> dictionary){
        String decrypted = "";;
        int[] keys = {};
        VigenereCipher vc;
        int count = 0;
        int max = 0;
        int keyLen = 0;
        for(int i = 1; i <= 100; i++){
            keys = tryKeyLength(encrypted, i, 'e');
            vc = new VigenereCipher(keys);
            String decrypt = vc.decrypt(encrypted);
            count = countWords(decrypt, dictionary);
            if(count > max){
                max = count;
                decrypted = decrypt;
                keyLen = i;
            }
        }
        System.out.println("KeyLen with most real words: " + keyLen);


        return decrypted;
    }
}
