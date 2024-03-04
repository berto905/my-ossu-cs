
/**
 * Write a description of Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

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
        la.readFile("data/short-test_log.txt");
        la.printAll();
    }
    
    public void testUniqueIP() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("data/weblog2_log.txt");
        System.out.println("There are " + la.countUniqueIPs() + " unique IP addresses");
    }
    
    public void testPrintAllHigherThanNum() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("data/weblog1_log.txt");
        la.printAllHigherThanNum(400);
    }
    
    public void testUniqueIPVisitsOnDay() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("data/weblog2_log.txt");
        ArrayList<String> dayVisitors = la.uniqueIPVisitsOnDay("Sep 24");
        for (String ip : dayVisitors) {
            System.out.println(ip);
        }
        System.out.println(dayVisitors.size());
    }
    
    public void testCountUniqueIPsInRange() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("data/weblog2_log.txt");
        System.out.println(la.countUniqueIPsInRange(400,499));
    }
    
    public void testCountVisitsPerIP() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("data/short-test_log.txt");
        HashMap<String, Integer> counts = la.countVisitsPerIP();
        for (String ip : counts.keySet()) {
            System.out.println(ip + " : " + counts.get(ip));
        }
    }
    
    public void testMostNumberVisitsByIP() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("data/weblog2_log.txt");
        HashMap<String, Integer> counts = la.countVisitsPerIP();
        System.out.println("Most number of visits by an IP is " + la.mostNumberVisitsByIP(counts));
    }
    
    public void testIPsMostVisits() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("data/weblog2_log.txt");
        HashMap<String, Integer> counts = la.countVisitsPerIP();
        ArrayList<String> ips = la.iPsMostVisits(counts);
        System.out.println("IPs with most number of visits:");
        for (String ip : ips) {
            System.out.println(ip);
        }
    }
    
    public void testIPsForDays() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("data/weblog3-short_log.txt");
        System.out.println("IPs for particular days:");
        HashMap<String, ArrayList<String>> iPsByDay = la.iPsForDays();
        for (String ip : iPsByDay.keySet()) {
            System.out.println(ip + " : " + iPsByDay.get(ip));
        }
    }
    
    public void testDayWithMostIPVisits() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("data/weblog2_log.txt");
        HashMap<String, ArrayList<String>> iPsByDay = la.iPsForDays();
        System.out.println("Day with most IP visits is " + la.dayWithMostIPVisits(iPsByDay));
    }
    
    public void testIPsWithMostVisitsOnDay() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("data/weblog2_log.txt");
        HashMap<String, ArrayList<String>> iPsByDay = la.iPsForDays();
        String day = "Sep 29";
        System.out.println("IP with most visits on " + day + " is " + 
                           la.iPsWithMostVisitsOnDay(iPsByDay, day));
    }
}
