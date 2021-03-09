import java.nio.Buffer;
import java.util.*;
import java.io.*;

public class Main {
    //does File Handling and testing
    public static void main(String[] args) throws IOException {

        //Reading the file in and converts all lines from file into a string
        BufferedReader in;
        String line="";
        if(args.length>0)
        {
            File file = new File(args[0]);
            in = new BufferedReader(new FileReader(file));
        }
        else {
            System.out.println("File input not found!");
            throw new FileNotFoundException();
        }
        StringBuilder linesFromFile = new StringBuilder();
        while((line = in.readLine()) != null)
        {
            linesFromFile.append(line);
            linesFromFile.append(System.lineSeparator());
        }

        //Outputs to file the seating arrangement
        in.close();
        FileWriter out = new FileWriter("seatings.txt");
        out.write(linesFromFile.toString());
        System.out.println("Successfully wrote to the file:);
        out.close();
    }
}
