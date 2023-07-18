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
        la.readFile("short-test_log.txt");
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
        la.readFile("weblog1.txt");
        ArrayList<String> IPs = la.uniqueIPVisitsOnDay(someday);
        for(String ip : IPs) {
            System.out.println(ip);
        }
        System.out.println("Has " + IPs.size() + " IPs");
    }

    public void testCountUniqueIPsInRange(int low, int high){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog1.txt");
        int count = la.countUniqueIPsInRange(low, high);
        System.out.println("There are " + count + " unique IPs in the range.");
    }

}