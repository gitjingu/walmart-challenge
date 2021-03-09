import java.util.*;
import java.util.concurrent.*;
import java.io.*;

public class ReservationMaker implements ReservationSeatingInterface
{
    protected ConcurrentHashMap<Character, ArrayList<Integer>> layout; //layout of the whole grid
    protected LinkedHashMap<String, ArrayList<String>> orderedReservations; //ordered list of reservations
    protected HashMap<String, Integer> reservations; //map of reservations for fast retrieval
    private int availableSeats, seatsPerRow, rows;

    public ReservationMaker()
    {
        layout = SeatingChart.initializeChart();
        reservations = new HashMap<String, Integer>();
        orderedReservations = new LinkedHashMap<String, ArrayList<String>>(); //for the final output storing
        seatsPerRow = SeatingChart.columns;
        rows = SeatingChart.rows;
        availableSeats = seatsPerRow * rows;
    }
    /**
     * tests to see if the inputted line from the file is a valid format for a ResID
     * @param split the resID and the number of people
     * @return false if input is invalid, true if it is okay
     */
    @Override
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

    @Override
    public String findBestSeats(String name, int num) throws Exception
    {
        //num is number of seats needed for user
        String result = "";
        if (num > availableSeats || num > seatsPerRow) {
            result = "For Reservation: "+ name + " ==> Illegal number of seats requested. Try again.";
            return result;
        }
//        Character row = closestFit(num);
//        ArrayList<Integer> availableSeats = theaterLayout.get(row);
//        reservationDetails.put(name, printSeats(availableSeats, numSeats, row));
//        updateSeats(row, numSeats);
//        countAvailableSeats -= numSeats;
//        return new String(name.concat(reservationDetails.get(name).toString()));
        return name;
    }
}
