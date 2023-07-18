package duke_java.module2.week3.ReadingLogFiles;
import java.util.*;


public class Tester {
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("short-test_log.txt");
        la.printAll();
    }

    public void testUniqueIP() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2testing.txt");
        int uniqueIPs = la.countUniqueIPs();
        System.out.println("There are " + uniqueIPs + " IPs");
    }

    public void testPrintAllHigherThanNum(int num) {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog1.txt");
        la.printAllHigherThanNum(num);
    }

    public void testUniqueIPVisitsOnDay(String someday) {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2testing.txt");
        ArrayList<String> IPs = la.uniqueIPVisitsOnDay(someday);
        for(String ip : IPs) {
            System.out.println(ip);
        }
        System.out.println("Has " + IPs.size() + " IPs");
    }

    public void testCountUniqueIPsInRange(int low, int high){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2testing.txt");
        int count = la.countUniqueIPsInRange(low, high);
        System.out.println("There are " + count + " unique IPs in the range.");
    }

    public void testCountVisitsPerIP(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("short-test_log.txt");
        HashMap<String, Integer> map = la.countVisitsPerIP();
        System.out.println(map);
    }
    public void testMostNumberVisitsByIP(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2testing.txt");
        HashMap<String, Integer> counts = la.countVisitsPerIP();
        System.out.println(la.mostNumberVisitsByIP(counts));
    }

    public void testiPsMostVisits(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2testing.txt");
        HashMap<String, Integer> counts = la.countVisitsPerIP();
        ArrayList<String> IPs = la.iPsMostvisits(counts);
        for(int i = 0; i < IPs.size(); i++){
            System.out.println(IPs.get(i));
        }      
    }

    public void testIpsForDays() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog3.txt");
        HashMap<String, ArrayList<String>> ip = la.iPsForDays();
        for(String key : ip.keySet()) {
            System.out.println(key + " " + ip.get(key).size() +" IPs");
        }
    }

    public void testDaywithMostIPVisits(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2testing.txt");

        System.out.println(la.dayWithmostIPVisits(la.iPsForDays()));
    }

    public void testIpsWithMostVisitsOnDay() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2testing.txt");
        HashMap<String, ArrayList<String>> ip = la.iPsForDays();
        ArrayList<String> abc = la.iPsWithMostVisitsOnDay(ip, "Sep 29");
        for(int i = 0; i < abc.size(); i++) {
            System.out.println(abc.get(i));
        }
    }


}