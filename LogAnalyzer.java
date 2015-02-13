/**
 * Read web server data and analyse
 * hourly access patterns.
 * 
 * @author David J. Barnes and Michael Kölling.
 * @version 2011.07.31
 */
public class LogAnalyzer
{
    // Where to calculate the hourly access counts.
    private int[] hourCounts;
    // Use a LogfileReader to access the data.
    private LogfileReader reader;

    /**
     * Create an object to analyze hourly web accesses.
     */
    public LogAnalyzer()
    { 
        // Create the array object to hold the hourly
        // access counts.
        hourCounts = new int[24];
        // Create the reader to obtain the data.
        reader = new LogfileReader();
    }

    /**
     * 
     */

    public LogAnalyzer(String log)
    {
        hourCounts = new int[24];
        reader = new LogfileReader(log);
    }

    /**
     * Analyze the hourly access data from the log file.
     */
    public void analyzeHourlyData()
    {
        while(reader.hasNext()) {
            LogEntry entry = reader.next();
            int hour = entry.getHour();
            hourCounts[hour]++;
        }
    }
     
    /** 
     * Analyze the hourly accesses in the given date
     *
     * @param day     The given day
     * @param month The given month
     * @param year  The given year
     */         
    public void analyzeHourlyData(int day, int month, int year)
    {
        while(reader.hasNext()) {
            LogEntry entry = reader.next();
            if(day == entry.getDay() && month == entry.getMonth() && year == entry.getYear())
            {
                int hour = entry.getHour();
                hourCounts[hour]++;
            }            
        }
    }
    
    public int busiestHour()
    {
        int mostAccesses = 0;
        int cont = 0;
        for (int i = 0; i < hourCounts.length;i++)
        {
            if (hourCounts[i] > mostAccesses)
            {
                mostAccesses = hourCounts[i];
                cont = i;
            }            
        }
        return cont;
    }

    public int quietestHour()
    {
        int lessAccesses = 0;
        for (int i = 0; i < hourCounts.length;i++)
        {            
            if (hourCounts[i] < hourCounts[lessAccesses])
            {
                lessAccesses = i;
            }            
        }
        return lessAccesses;
    }

    public int busiestPeriod()
    {
        int mostAccesses = 0;
        int cont = 0;
        for (int i = 0; i < (hourCounts.length - 1); i++)
        {  
                if ((hourCounts[i] + hourCounts[i + 1]) > mostAccesses)
                {
                    mostAccesses = hourCounts[i];
                    cont = i;
                }          
        }
        return cont;
    }

    private int numberOfAccesses()
    {
        int accesses = 0;
        for (int i = 0; i < hourCounts.length; i++)
        {
            accesses += hourCounts[i];
        }
        return accesses;
    }

    /**
     * Print the hourly counts.
     * These should have been set with a prior
     * call to analyzeHourlyData.
     */
    public void printHourlyCounts()
    {
        System.out.println("Hr: Count");
        int hour = 0;
        while(hour < hourCounts.length)
        {
            System.out.println(hour + ": " + hourCounts[hour]);
            hour++;
        }
    }

    /**
     * Print the lines of data read by the LogfileReader
     */
    public void printData()
    {
        reader.printData();
    }
}
