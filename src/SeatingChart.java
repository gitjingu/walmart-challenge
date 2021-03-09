import java.util.concurrent.*;
import java.util.ArrayList;

/**
 * SeatingChart class is used for populating the seat chart at the theater into a hashmap
 */
public class SeatingChart
{
    /**
     * Generate a seating chart as a Hashmap
     * @return ConcurrentHashMap<Character, ArrayList<Integer>>
     * O(n) runtime method
     */
    public static ConcurrentHashMap<Character, ArrayList<Integer>> initializeChart(int rows, int columns)
    {
        ConcurrentHashMap<Character, ArrayList<Integer>> chart =
                new ConcurrentHashMap<Character, ArrayList<Integer>>();
        //temp pointers to traverse through
        char row = 'A';
        int col = 0;
        ArrayList<Integer> seatList = new ArrayList<Integer>(); //used for List<Integer> of hashmap

        /* populate column list: O(n) runtime */
        for(col=1; col<=columns; col++)
            seatList.add(col);

        /* populate rows: (O(n) runtime */
        for(int i=0; i<rows; i++)
        {
            chart.put(row, seatList);
            row++; //increments char to next value a-->b-->c etc.
        }
        /** this solves the problem of having to traverse to do n^2 loops through a 2-d array **/
        return chart;
    }
}
