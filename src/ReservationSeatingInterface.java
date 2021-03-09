import java.io.*;
public interface ReservationSeatingInterface
{
    boolean validateInput(String[] split) throws IllegalArgumentException;
    String findBestSeats(String name, int num) throws Exception;
}
