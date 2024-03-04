
/**
 * Write a description of LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer {
    private ArrayList<LogEntry> records;
    
    public LogAnalyzer() {
        records = new ArrayList<LogEntry>();
    }
    
    public void readFile(String filename) {
        FileResource fr = new FileResource(filename);
        // Iterate over its lines for each one
        for (String line : fr.lines()) {
            // Use WebLogParser.parseEntry
            LogEntry le = WebLogParser.parseEntry(line);
            // Add the resulting LogEntry to records
            records.add(le);
        }
    }
    
    public int countUniqueIPs() {
        // uniqueIPs starts as an empty list
        ArrayList<String> uniqueIPs = new ArrayList<String>();
        // for each element le in records
        for (LogEntry le : records) {
            // ipAddr is le's ipAddress
            String ipAddr = le.getIpAddress();
            // ipAddr is not in uniqueIPs
            if (!uniqueIPs.contains(ipAddr)) {
                // add ipAddr to uniqueIPs
                uniqueIPs.add(ipAddr);
            }
        }
        return uniqueIPs.size();
    }
    
    public void printAll()  {
        for (LogEntry le : records) {
            System.out.println(le);
        }
    }
    
    public void printAllHigherThanNum(int num) {
        for (LogEntry le : records) {
            int status = le.getStatusCode();
            if (status > num) {
                System.out.println(le);
            }
        }
        
    }
    
    public ArrayList<String> uniqueIPVisitsOnDay(String someday) {
        ArrayList<String> dayVisitors = new ArrayList<String>();
        for (LogEntry le : records) {
            String date = le.getAccessTime().toString().substring(4,10);
            String ipAddr = le.getIpAddress();
            if (date.equals(someday) && !dayVisitors.contains(ipAddr)) {
                dayVisitors.add(ipAddr);
            };
        }
        return dayVisitors;
    }
    
    public int countUniqueIPsInRange(int low, int high) {
        ArrayList<String> uniqueIPs = new ArrayList<String>();
        for (LogEntry le : records) {
            int status = le.getStatusCode();
            String ipAddr = le.getIpAddress();
            if (status >= low && status <= high && !uniqueIPs.contains(ipAddr)) {
                uniqueIPs.add(ipAddr);
            }
        }
        return uniqueIPs.size();
    }
    
    public HashMap<String, Integer> countVisitsPerIP() {
        HashMap<String, Integer> counts = new HashMap<String, Integer>();
        for (LogEntry le : records) {
            String ipAddr = le.getIpAddress();
            if (!counts.containsKey(ipAddr)) {
                counts.put(ipAddr, 1);
            } else {
                counts.put(ipAddr, counts.get(ipAddr) + 1);
            }
        }
        return counts;
    }
    
    public int mostNumberVisitsByIP(HashMap<String, Integer> counts) {
        int max = 0;
        for (String ip : counts.keySet()) {
            if (counts.get(ip) > max) {
                max = counts.get(ip);
            }
        }
        return max;
    }
    
    public ArrayList<String> iPsMostVisits(HashMap<String, Integer> counts) {
        ArrayList<String> iPs = new ArrayList<String>();
        int max = mostNumberVisitsByIP(counts);
        for (String ip : counts.keySet()) {
            if (counts.get(ip) == max) {
                iPs.add(ip);
            }
        }
        return iPs;
    }
    
    public HashMap<String, ArrayList<String>> iPsForDays() {
        HashMap<String, ArrayList<String>> iPsByDay = new HashMap<String, ArrayList<String>>();
        for (LogEntry le : records) {
            String date = le.getAccessTime().toString().substring(4,10);
            String ipAddr = le.getIpAddress();
            if (!iPsByDay.containsKey(date)) {
                ArrayList<String> dates = new ArrayList<String>();
                dates.add(ipAddr);
                iPsByDay.put(date, dates);
            } else {
                iPsByDay.get(date).add(ipAddr);
            }
        }
        return iPsByDay;
    }
    
    public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> iPsByDay) {
        int mostVisits = 0;
        String day = null;
        for (String currDate : iPsByDay.keySet()) {
            int currVisits = iPsByDay.get(currDate).size();
            if (currVisits > mostVisits) {
                mostVisits = currVisits;
                day = currDate;
            }
        }
        return day;
    }
    
    public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> iPsByDay,
                                                    String day) {
        ArrayList<String> visits = iPsByDay.get(day);
        HashMap<String, Integer> visitCounts = new HashMap<String, Integer>();
        for (String v : visits) {
            if (!visitCounts.containsKey(v)) {
                visitCounts.put(v, 1);
            } else {
                visitCounts.put(v, visitCounts.get(v) + 1);
            }
        } 
        return iPsMostVisits(visitCounts);
    }
}
