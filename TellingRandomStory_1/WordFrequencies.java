package TellingRandomStory_1;
import java.util.ArrayList;

import edu.duke.FileResource;

public class WordFrequencies{
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;

    public WordFrequencies(){
        myWords = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }

    public void findUnique(){
        myWords.clear();
        myFreqs.clear();

        FileResource resource = new FileResource();

        for(String word : resource.words()){
            word = word.toLowerCase();
            if(!myWords.contains(word)){
                myWords.add(word);
                myFreqs.add(1);
            }
            else {
                int idx = myWords.indexOf(word);
                int freq = myFreqs.get(idx);
                myFreqs.set(idx, freq + 1);
            }
        }
    }

    public void tester(){
        findUnique();

        System.out.println("Number of unique words: " + myWords.size());
        System.out.println("Word Frequencies:");
        for(int i = 0; i < myWords.size(); ++i){
            //System.out.println(myFreqs.get(i) + "\t" + myWords.get(i));
        }
        System.out.println("The word that occurs most often and its count are: " + myWords.get(findIndexOfMax()) + " " + myFreqs.get(findIndexOfMax()));
    }

    public int findIndexOfMax(){
        int maxIndex = 0;
        int maxValue = 0;

        for(int i = 0; i < myFreqs.size(); ++i){
            if(myFreqs.get(i) > maxValue){
                maxIndex = i;
                maxValue = myFreqs.get(i);
            }
        }
        return maxIndex;
    }

    
}
