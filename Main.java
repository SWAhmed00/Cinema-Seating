
// Compiler:      IntelliJ
//  In preparation for the release of a new movie next summer, you have been hired by the owner of a very small movie theater
//  to develop the backend for an online ticket reservation system.  The program should display the current
//  seating arrangement and allow the patron to select seats.  A report should be generated at the end of the program to
//  specify for each individual auditorium and overall for all auditoriums how many seats were sold/unsold and
//  how much money was earned.
//nodes and lnked list should be used for this project


package Tickets;

import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException
    {
        int rowNumb = 0;//holds row number
        boolean validInput=false;//flag for valid input
        int adult=0;//holds adult tickets
        int child=0;//holds children tickets
        int totalTickets=0;//holds total tickets
        int senior=0;//holds senior tickets
        int coll=0;//holds column
        int userInput=0;//holds user input
        char[] colLabel = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};//holds column labels
        int rowLength = 0;//holds size of columns
        int rowNum = 0;//holds number of rows
        Auditorium aud=new Auditorium();//holds auditorium
        String temp = "";//holds row temporarily
        Scanner sc = new Scanner(System.in);

        File seating = new File("A1.txt");//holds auditorium file
        //if file exists we carry on with processing
        if (seating.exists())
        {
            //scanner for the file
            Scanner input = new Scanner(seating);
            //while loop runs until whole file is read
            while (input.hasNextLine())
            {
                temp += input.nextLine();
                //getting length of row only once
                if (rowLength == 0)
                {
                    rowLength = temp.length();
                }//end of if statement
                rowNum++;
            }//end of while loop

            char[][] seats = new char[rowNum][rowLength];//array holds the seats until they are converted to nodes

            int offset;//offsett for accessing the elements

            //for loop to run through all the seats
            for (int i = 0; i < rowNum; i++)
            {
                //for loop to comvert string elements into a array
                for (int k = 0; k < rowLength; k++)
                {

                    offset = i * (rowLength);
                    seats[i][k] = temp.charAt(offset + k);
                }//end of for loop

            }//end of for loop
            //creating a new auditorium obkect with nodes and a grid of the auditorium
            aud = new Auditorium(seats, rowLength, rowNum, colLabel);
            //do while runs until the user exits the program
            do
                {
                //do while loop to display menu and take in valid input
                do
                {
                    System.out.println("1. Reserve Seats");
                    System.out.println("2. Exit");
                    //validating user input
                    try
                    {
                        userInput = sc.nextInt();
                        validInput=true;
                    }//end of try
                    catch (Exception e)
                    {
                        System.out.println("Invalid Choice");
                        sc.nextLine();
                    }//end of catch
                }//end of do while loop
                while (userInput != 1 && userInput != 2 && !validInput);

                //resetting the flag
                validInput=false;
                //if they wamt to reserve a seat we offer them a chance
                if (userInput == 1)
                {
                    //calling display function display the auditorium
                    aud.display(aud, rowNum, rowLength);

                    //do while to take in valid row number
                    do
                    {
                        System.out.print("Please enter the row that you would like to reserve a seat in:");
                        //validating input
                        try
                        {
                            rowNumb = sc.nextInt();
                            validInput = true;
                        }//end of try statement
                        catch (Exception e)
                        {
                            sc.nextLine();
                            System.out.println("Invalid Row Number!");
                        }//end of catch statement
                        //invalid input eror message
                        if (rowNumb < 0 || rowNumb > rowNum && validInput)
                        {
                            System.out.println("Invalid Row Number!");
                        }//end of if statement

                    }//end of do while loop
                    while (rowNumb < 0 || rowNumb > rowNum || !validInput);
                    //restting flag
                    validInput = false;
                    sc.nextLine();
                    char colNumb = 0;//holds column
                    //dow hile to take in valid column
                    do
                    {
                        System.out.print("Please enter the column that you would like to reserve a seat in:");
                        //validating input
                        try
                        {
                            String test="";
                            test = sc.nextLine();
                            if(test.length()==1)
                            {
                                colNumb=test.charAt(0);
                                colNumb = (char) (colNumb - 64);
                                coll = colNumb;
                                validInput=true;
                            }//end of if statement
                            else
                            {
                                System.out.println("Invalid Column!");
                                colNumb='0';
                            }//end of else statement
                        }//end of try statement
                        catch (Exception e)
                        {
                            if(!validInput)
                            {
                                System.out.println("Invalid Column!");
                            }//end of if statement
                        }//end of catch stateemnt
                        //making sure input is within a certain range
                        if (colNumb < 0 || colNumb > rowLength && validInput)
                        {
                            System.out.println("Invalid Column!");

                        }//end of if statement
                    }//end of do while loop
                    while (colNumb < 0 || colNumb > rowLength);
                    validInput = false;
                    //do while loop to get valid adult tickets
                    do
                    {
                        System.out.print("Please enter the number of adult tickets you want: ");
                        //validating input
                        try
                        {
                            adult = sc.nextInt();
                            validInput = true;
                        }//end of try
                        catch (Exception e)
                        {
                            sc.nextLine();
                            System.out.println("Invalid number!");
                        }//end of catch
                        //making sure number is not negative
                        if (adult < 0)
                        {
                            System.out.println("Invalid number!");
                        }//end of if statement

                    }//end of do while loop
                    while (adult < 0 || !validInput);
                    totalTickets+=adult;
                    validInput = false;
                    //do while to get valid children ticketa
                    do
                    {
                        System.out.print("Please enter the number of children tickets you want: ");
                        //validating user input
                        try
                        {
                            child = sc.nextInt();
                            validInput = true;
                        }//end of try statement
                        catch (Exception e)
                        {
                            sc.nextLine();
                            System.out.println("Invalid number!");
                        }//end of catch statement
                        if (child < 0 && validInput)
                        {
                            System.out.println("Invalid number!");
                        }//end of if statement
                    }//end of do while loop
                    while (child < 0 || !validInput);
                    totalTickets+=child;
                    validInput = false;
                    //do while loop to validate number of senior tickets
                    do
                    {
                        System.out.print("Please enter the number of senior tickets you want: ");
                        //validating user input
                        try
                        {
                            senior = sc.nextInt();
                            validInput = true;
                        }//end of try
                        catch (Exception e)
                        {
                            sc.nextLine();
                            System.out.println("Invalid number!");
                        }//end of catch
                        //making sure that number is greater than 0
                        if (senior < 0 && validInput)
                        {
                            System.out.println("Invalid number!");
                        }//end of if statement
                    } while (senior < 0 || !validInput);

                    totalTickets+=senior;
                    validInput = false;
                    //we check to see if their original seats are open and the reservation is made
                    boolean finished=aud.checkPlace(aud,coll,rowNumb,totalTickets,adult,child,senior);
                    //if not we call the alternative function
                    if(!finished)
                    {
                        aud.altPlace(aud,rowLength,rowNum,child,adult,senior,totalTickets,sc);
                    }//end of if statement
                    //resetting the counters
                    totalTickets=0;
                    adult=0;
                    child=0;
                    senior=0;
                }//end of if statement
            }//end of do while loop
            while(userInput!=2);
        }//end of if statement
        //displaying that a file does not exists if that is the case
        else
            {
                System.out.println("File does not exist");
            }//end of else statement
            //if the file did exist we display the stats
            if(seating.exists())
            {
                aud.finalDisplay(aud,rowNum,rowLength);
                aud.reWrite(aud,rowLength,rowNum);
            }//end of if statement

    }//end of main function

}//end of public class



