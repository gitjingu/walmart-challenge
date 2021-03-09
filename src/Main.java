import java.nio.Buffer;
import java.util.*;
import java.io.*;

public class Main {
    //does File Handling and testing
    public static void main(String[] args) throws IOException, Exception {

        //Reading the file in and converts all lines from file into a string
        BufferedReader in;
        String line;
        int numRows = SeatingChart.rows;
        int numCols = SeatingChart.columns;
        int matrixSize = numRows*numCols;
        String finalSeatings = new String(); //keep track of finalSeatings

        if(args.length>0)
        {
            File file = new File(args[0]);
            in = new BufferedReader(new FileReader(file));
        }
        else {
            System.out.println("File input not found! Please try with a valid file.");
            throw new FileNotFoundException();
        }
        StringBuilder linesFromFile = new StringBuilder();

        //validate inputs
        int lineCount=1; //keep track of what line we are at
        ReservationMaker theaterSeats = new ReservationMaker();
        while((line = in.readLine()) != null)
        {
            String[] split = line.split(" ");
            boolean check = theaterSeats.validateInput(split); //validates inputs
            if(check) {
                theaterSeats.findBestSeats(split[0], Integer.parseInt(split[1]));
            }
            else
                System.out.println("At line: "+lineCount+"==> invalid input received. Reservation not created.");
            lineCount++;
        }

        //Outputs to file the seating arrangement
        in.close();
        System.out.println(theaterSeats.createFile());
//        FileWriter out = new FileWriter("seatings.txt");
//        System.out.print
//        out.write(finalSeatings.toString());
//        System.out.println("\nReservations updated at '/walmart-challenge/src/seatings.txt' ");
//        out.close();
    }
}
