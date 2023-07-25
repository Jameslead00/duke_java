package duke_java.module2.week4.vigenereProgram;

import edu.duke.FileResource;

public class Tester{

    public void testCaesarCipher(){
        CaesarCipher cc = new CaesarCipher(7);
        FileResource fr = new FileResource("data/titus-small.txt");
        String msg = fr.asString();
        String encr = cc.encrypt(msg);
        System.out.println("Engrypted: " + cc.encrypt(msg));
        System.out.println("Decrypted: " + cc.decrypt(encr));
    }

    public void testCaesarCracker(){
        CaesarCracker cc = new CaesarCracker('a');
        FileResource fr = new FileResource("data/oslusiadas_key17.txt");
        String msg = fr.asString();
        int key = cc.getKey(msg);
        String decrypted = cc.decrypt(msg);
        System.out.println("Key: " + key + "\n Message: " + decrypted);

    }

    public void testVigenereCipher(){
        int[] key = {17, 14, 12, 4}; //rome
        VigenereCipher vc = new VigenereCipher(key);
        FileResource fr = new FileResource("data/titus-small.txt");
        String msg = fr.asString();
        String encr = vc.encrypt(msg);
        System.out.println("Encrypted: " + encr);
        System.out.println("Decrypted: " + vc.decrypt(encr));
    }
    
}