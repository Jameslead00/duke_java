package duke_java.module2.week3.ReadingLogFiles;
import java.util.ArrayList;
import java.util.HashMap;


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

    public HashMap<String, Integer> countVisitsPerIP(){
        HashMap<String, Integer> map = new HashMap<String, Integer>();

        for(LogEntry le : records){
            String ip = le.getIpAddress();
            if(!map.containsKey(ip)){
                map.put(ip, 1);
            } else {
                map.put(ip, map.get(ip) + 1);
            }
        }
        return map;
    }

    public int mostNumberVisitsByIP(HashMap<String, Integer> map){
        int max = 0;

        for(String key : map.keySet()){
            int count = map.get(key);
            if(count > max){
                max = count;
            }
        }
        return max;
    }

    public ArrayList<String> iPsMostvisits(HashMap<String, Integer> map){
        int max = mostNumberVisitsByIP(map);
        ArrayList<String> ipAddr = new ArrayList<String>();

        for(String key : map.keySet()){
            if(map.get(key) == max){
                if(!ipAddr.contains(key)){
                    ipAddr.add(key);
                }
            }
        }
        return ipAddr;
    }

    public HashMap<String, ArrayList<String>> iPsForDays(){
        HashMap<String, ArrayList<String>> ipMap = new HashMap<String, ArrayList<String>>();
        
        for(LogEntry le : records){
            String date = le.getAccessTime().toString().substring(4,10);
            String ipAddress = le.getIpAddress();
            ArrayList<String> ip;

            if(!ipMap.containsKey(date)){
                ip = new ArrayList<String>();
                ip.add(ipAddress);
                ipMap.put(date, ip);
            } else {
                ip = ipMap.get(date);
                ip.add(ipAddress);
                ipMap.put(date, ip);
            }
        }
        return ipMap;
    }

    public String dayWithmostIPVisits(HashMap<String, ArrayList<String>> map){
        String day = "";
        int size = 0;

        for(String key : map.keySet()){
            if(map.get(key).size() > size){
                size = map.get(key).size();
                day = key;
            }
        }
        return day;
    }

    public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> map, String day){
        ArrayList<String> ipWithMostAccess;
        HashMap<String, Integer> ips = new HashMap<String, Integer>();
        for(String key : map.keySet()) {
            if(key.equals(day)) {
                for(int i = 0; i < map.get(key).size(); i++) {
                    String ipAd = map.get(key).get(i);
                    if(!ips.containsKey(ipAd)) {
                        ips.put(ipAd, 1);
                    } else {
                        ips.put(ipAd, ips.get(ipAd) + 1);
                    }
                }
            }
        }
        ipWithMostAccess = iPsMostvisits(ips);
        return ipWithMostAccess;

    }
}