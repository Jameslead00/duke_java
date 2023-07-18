package duke_java.module2.week3.ReadingLogFiles;
import java.util.ArrayList;

import edu.duke.*;



public class LogAnalyzer{
    private ArrayList<LogEntry> records;
    
    public LogAnalyzer(){
        records = new ArrayList<>();
    }

    public void readFile(String filename){
        FileResource fr = new FileResource(filename);
        
        for(String line : fr.lines()){
            LogEntry le = WebLogParser.parseEntry(line);
            records.add(le);
        }
    }

    public void printAll(){
        for(LogEntry le : records){
            System.out.println(le);
        }
    }

    public int countUniqueIPs(){
        ArrayList<String> uniqueIPs = new ArrayList<String>();

        for(LogEntry le : records){
            String IPAddr = le.getIpAddress();
            if(!uniqueIPs.contains(IPAddr)){
                uniqueIPs.add(IPAddr);
            }
        }
        return uniqueIPs.size();
    }

    public void printAllHigherThanNum(int num){
        for(LogEntry le : records){
            int statuscode = le.getStatusCode();
            if(statuscode > num){
                System.out.println(le);
            }
        }
    }

    public ArrayList<String> uniqueIPVisitsOnDay(String someday){
        ArrayList<String> result = new ArrayList<String>();

        for(LogEntry le : records){
            String date = le.getAccessTime().toString();
            String ipAddr = le.getIpAddress();

            if(date.indexOf(someday) != -1 && !result.contains(ipAddr)){
                result.add(ipAddr);
            }
        }
        return result;
    }

    public int countUniqueIPsInRange(int low, int high){
        ArrayList<String> result = new ArrayList<String>();
        for(LogEntry le : records){
            int code = le.getStatusCode();
            String ipAddr = le.getIpAddress();
            if(code >= low && code <= high && !result.contains(ipAddr)){
                result.add(ipAddr);
            }
        }
        return result.size();
    }
}