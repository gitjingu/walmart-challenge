# WalmartChallenge - Vishnu Kumar

This project is a utility that assigns seats in a movie theater to fulfil reservation requests.
## Prerequisites

Before you begin, ensure you have met the following requirements:
* You have installed the latest version of `Java`. I'm using Java 11.0.2.
* You have `Windows/Linux/Mac` OS for command line (input and output).
* You have installed the `JUnit` package and related dependencies. I'm using JUnit 4. 

## Design Requirements

The following assumptions have been made:

* The movie theater has 400 seats organized in a 10x20 array.
````
        [[ SCREEN ]]
     --------------------
A    ssssssssssssssssssss 
B    ssssssssssssssssssss 
C    ssssssssssssssssssss 
D    ssssssssssssssssssss 
E    ssssssssssssssssssss 
F    ssssssssssssssssssss 
G    ssssssssssssssssssss 
H    ssssssssssssssssssss 
I    ssssssssssssssssssss 
J    ssssssssssssssssssss 
     --------------------
     1 ............... 20
````
* A buffer of 3 seats and/or one row is required in between reservation IDs
 
* Input files will be human-readable text. 

Sample command line input:
```
R001 2 
R002 4 
R003 4 
R004 3
```

Sample command line output:
```
R001 I1,I2
R002 F16,F17,F18,F19 
R003 A1,A2,A3,A4 
R004 J4,J5,J6
```

where `R###` is the reservation identifier and `A##-J##` is a seat.

##Running the program

 This program runs on Java. 
* Open your terminal
* `cd 'walmart-challenge'`to go to  directory where the unzipped folder saved. 

* Navigate to the folder `src`.
Run:
  ```
  javac Main.java
   ```

 * Now Run the following command to start the application
 :
   ```
   javac Main.java <filepath>.txt
    ```
## Classes


###Main
  * methods:
    
        '+ main(String[] args) : void'
        
###SeatingChart
  * fields:
    
        `+ rows : int`
        
        `+ columns : int`
        
        `+ availableSeats: int`
    * methods:
    
       `+ initializeChart(): ConcurrentHashMap<Character, ArrayList<Integer>>`
        
        <strike> public String[][] initializeChart </strike> : was an original seating chart initialization method with 2-d array, 
        but I changed this later to HashMap (concurrent, so thread-safe)
        
* ReservationMaker 

## Contributors
The following people who have contributed to this project:

* [@gitjingu](https://github.com/gitjingu) 📖

## Contact

If you want to contact me you can reach me at `vvkumar1@asu.edu`