package TellingRandomStory_2;

import java.util.ArrayList;
import edu.duke.FileResource;


public class CharactersInPlay{
    private ArrayList<String> characters;
    private ArrayList<Integer> characterCounts;

    public CharactersInPlay() {
        characters = new ArrayList<String>();
        characterCounts = new ArrayList<Integer>();
    }

    public void update(String person){
        int index = characters.indexOf(person);
        if(index == -1){
            characters.add(person);
            characterCounts.add(1);
        } else{
            int count = characterCounts.get(index);
            characterCounts.set(index, count + 1);
        }
    }

    public void findAllCharacters(){
        characterCounts.clear();
        characters.clear();

        FileResource resource = new FileResource();

        for(String line : resource.lines()){
            int idx = line.indexOf(".");

            if(idx != -1 /*&& idx < line.length() -1*/){
                String person = line.substring(0, idx).trim();
                update(person);
            }
        }
    }

    public void tester(){
        findAllCharacters();
        int num1 = 10;
        int num2 = 15;

        System.out.println("Main characters with " + num1 + " or more speaking parts: ");

        for(int i = 0; i < characters.size(); i++){
            int count = characterCounts.get(i);

            if(count >= num1){
                System.out.println(characters.get(i) + "\t" + count);
            }
        }
        System.out.println("\nCharacters with speaking parts between "+ num1 + " and " + num2 + " inclusive:");
        charactersWithNumParts(num1, num2);
    }

    public void charactersWithNumParts(int num1, int num2){
        for(int i = 0; i < characters.size(); i++){
            int count = characterCounts.get(i);
        
            if(count > num1 && count <= num2){
                System.out.println(characters.get(i) + "\t" + count);
            }
        }
    }
}