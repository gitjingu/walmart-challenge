import java.util.*;
import java.util.concurrent.*;
import java.io.*;
import java.nio.file.*;

public class ReservationMaker
{
    protected ConcurrentHashMap<Character, ArrayList<Integer>> layout;//layout of the whole grid
    protected LinkedHashMap<String, ArrayList<String>> orderedReservations =
            new LinkedHashMap<String, ArrayList<String>>(); //for the final output storing
                                                        //ordered list of reservations
    protected HashMap<String, Integer> reservations =
            new HashMap<String, Integer>(); //map of reservations
                                            // for fast retrieval
    private int seatsPerRow = SeatingChart.columns;
    private int rows=SeatingChart.rows;
    private int totalAvailableSeats = seatsPerRow * rows;


    public ReservationMaker()
    {
        layout = SeatingChart.initializeChart();
    }
    /**
     * tests to see if the inputted line from the file is a valid format for a ResID
     * @param split the resID and the number of people
     * @return false if input is invalid, true if it is okay
     */
    public boolean validateInput(String[] split) throws IllegalArgumentException
    {
        if(split.length!=2)
            return false;
        String resID = split[0].toLowerCase(); //parse Reservation id

        if(resID.charAt(0) != 'r') { //string doesn't begin with R
            return false;
        }
        resID = resID.substring(1); //extract the R part

        if(resID.length() > 4) { //document says format is R#### so if more than 4, definitely a problem
            return false;
        }
        int numPeople=0; int resNum=0;
        //check if second part is integer
        try {
            numPeople = Integer.parseInt(split[1]);
            resNum = Integer.parseInt(resID);
        }
        catch(NumberFormatException e) { //catch if neither of those are integers
            return false;
        }
        if(resNum<1||numPeople<1) { //cant be negatives
            return false;
        }
        return true;
    }

    /**
     * @param name the name of the reservation
     * @param num  the number of seats
     * @return String of the best seats for the user
     * @throws Exception
     */
    public String findBestSeats(String name, int num)
    {
        //num is number of seats needed for user
        String result = "";
        if (num > this.totalAvailableSeats || num > seatsPerRow) {
            result = "For Reservation: "+ name + " ==> Illegal number of seats requested. Try again.";
            return result;
        }

        Character r = closestFit(num); //get the row letter that is the best fit
        if(r=='z')
        {
            return "No seats found for reservation: " + name + "==> Not enough seats in theater. ";
        }
        ArrayList<Integer> available = this.layout.get(r);
        orderedReservations.put(name, printSeats(available, num, r));
        updateSeats(r, num);
        this.totalAvailableSeats = this.totalAvailableSeats-num;//decrement the number of available seats
        return new String(name.concat(orderedReservations.get(name).toString()));
    }

    private Character closestFit(int numSeats)
    {
        int minSize = 1000000; // exit condition
        Character row = 'z';
        for (ConcurrentHashMap.Entry<Character, ArrayList<Integer>> entry : this.layout.entrySet())
        {
            if ((entry.getValue().size() - (numSeats+3)) < minSize && (entry.getValue().size() - (numSeats+3)) >= 0) {
                minSize = entry.getValue().size() - numSeats+3;
                row = entry.getKey();
            }
        }
        return row;
    }

    private void updateSeats(Character r, int numSeats) {
        ArrayList<Integer> availableSeats = this.layout.get(r);
        for (int i = 0; i < numSeats+3; i++)
            try {
                availableSeats.remove(0);
            }
        catch(NullPointerException e)
        {
            System.out.print("Could not remove seats.");
        }
    }

    private ArrayList<String> printSeats(ArrayList<Integer> availableSeats, int n, Character r)
    {
        ArrayList<String> result = new ArrayList<String>();
        for (int i = 0; i < n; i++)
        {
            result.add(Character.toString(r) + availableSeats.get(i));
        }
        return result;
    }
    public String createFile() throws IOException {
        String content = "";
        String newContent = "";
        for (Map.Entry<String, ArrayList<String>> entry : orderedReservations.entrySet()) {
            content += entry.getKey() + " " + entry.getValue() + System.lineSeparator();
            newContent = content.replace("[", "").replace("]", "");
        }
        String path = createOutputFile("output.txt", newContent);
        return path;
    }

    private String createOutputFile(String fileName, String content) throws IOException {
        Files.write(Paths.get(fileName), content.getBytes(), StandardOpenOption.CREATE);
        File f = new File(fileName);
        return f.getCanonicalPath();
    }

}
