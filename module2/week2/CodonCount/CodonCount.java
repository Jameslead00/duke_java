package CodonCount;

import edu.duke.FileResource;
import java.util.HashMap;
import java.util.Scanner;

public class CodonCount{
    private HashMap<String, Integer> codonMap;

    public CodonCount(){
        codonMap = new HashMap<>();
    }

    public void buildCodonMap(int start, String dna){
        codonMap.clear();

        for(int i = start; i < dna.length() - 2; i += 3){
            String codon = dna.substring(i, i + 3);
            codon = codon.toUpperCase();
            if(codonMap.containsKey(codon)){
                codonMap.put(codon, codonMap.get(codon) + 1);
            } else {
                codonMap.put(codon, 1);
            }
        }
    }
    public String getMostCommonCodon(){
        int maxCount = 0;
        String mostCommonCodon = "";

        for(String codon : codonMap.keySet()){
            int count = codonMap.get(codon);
            if(count > maxCount){
                maxCount = count;
                mostCommonCodon = codon;
            }
        }
        return mostCommonCodon;
    }

    public void printCodonCounts(int start, int end){
        for(String codon : codonMap.keySet()){
            int count = codonMap.get(codon);
            if(count >= start && count <= end){
                System.out.println(codon + "\t" + count);
            }
        }
    }

    public void tester(){

       /*  Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the filename containing the DNA strand: ");
        String filename = scanner.nextLine();
        scanner.close(); */

        FileResource resource = new FileResource();
        String dna = resource.asString().trim().toUpperCase();

        for(int i = 0; i < 3; i++){
            buildCodonMap(i, dna);

            System.out.println("Reading frame starting with " + i + " results in " + codonMap.size() + " unique codons");
            System.out.println("  and the most common codon is " + getMostCommonCodon() + " with count " + codonMap.get(getMostCommonCodon()));
            
            System.out.println("Counts of codons between 1 and 5 inclusive are:");
            printCodonCounts(1, 5);
        }

    }

    public static void main(String[] args){
        CodonCount codoncount = new CodonCount();
        codoncount.tester();
    }
}