
// Compiler:      IntelliJ
package Tickets;


import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Auditorium
{
    public TheaterSeat first;//holds head of list
    //setter for Firts
    public void setFirst(TheaterSeat first) {
        this.first = first;
    }//end of setter function
    //getter for getFirst function
    public TheaterSeat getFirst() {
        return first;
    }//end of getter function

Auditorium()
{
}//end of defailt constructtor

    //constructor with all the parameters
    Auditorium(char[][] seats, int col, int row, char[] label)
    {
        boolean res = true;//holds reservation flag
        TheaterSeat currentTwo;//holds the next seat
        //setting first and making sure it isnt reserved
        if (seats[0][0] == '.')
        {
            res = false;
        }//end of if statement
        //creating first
        first = new TheaterSeat(1, 'A', res, seats[0][0]);
        first.up = null;
        first.left = null;
        TheaterSeat current = first;
        res = true;
        //connecting list down from first if there is more than one row
        if (row > 1)
        {
            current = first;
            int count = 1;//counter
            //checking to see if the seat is reserved or not
            if (seats[count][0] == '.')
            {
                res = false;
            }//end of if statement
            //setting the next nodes info
            first.down = new TheaterSeat((count + 1), 'A', res, seats[count][0]);
            res = true;
            current = first.down;
            count++;//updating counter
            //loop runs until all the rows nodes have been created and connected to first
            while (count < row)
            {
                if ((count ==row))
                {
                    current.down = null;
                    current.left = null;
                    break;
                }//end if if statement
                //setting the nodes based on their characteristics
                else
                    {
                    if (seats[count][0] == '.')
                    {
                        res = false;
                    }//end of if statement
                    current.down = new TheaterSeat((count + 1), 'A', res, seats[count][0]);
                    current.left = null;//setting left to null as they are edge nodes
                    current = current.down;
                    count++;
                    res = true;
                }//end of else statement
            }//end of while loop
        }//end of if statment
        current = first;

        //connecting list to the right
        for (int i = 0; i < row; i++)
        {
            int counter = i;//counter
            //moving down to start the next row
            while (counter != 0)
            {
                current = current.down;
                counter--;
            }//end of while loop
            //for loop that runs until until all the columns in a row have been initialized
            for (int k = 1; k < col; k++)
            {
                //checking if the seat is reserved or not
                if (seats[i][k] == '.')
                {
                    res = false;
                }//end of if statement
                //initalizing the new nodes
                current.right = new TheaterSeat((i + 1), label[i + 1], res, seats[i][k]);
                //if in row 1 we set all up nodes to null as they are edge nodes
                if (i == 0)
                {
                    current.up = null;
                }//end of if statement
                //updating current
                current = current.right;
                res = true;
                //setting teh edge of the columns right to null
                if (k + 1 == col)
                {
                    current.right = null;
                }//end of if statement
            }//end of for loop
            current = first;
        }//end of for loop

        //connecting the nodes to the left
        current = first;
        for (int i = 0; i < row; i++)
        {
            int counter = i;//counter
            //moving down to the right row after the prevoius row is done
            while (counter != 0)
            {
                current = current.down;
                counter--;
            }//end of while loop
            currentTwo = current;//swapper
            //for loop for all the column memebers of a row
            for (int k = 0; k < col; k++)
            {
                //if it is the first column of a row left is null
                if (k == 0)
                {
                    current.left = null;
                }//end of if statement
                //else we store the old value in the currents left
                else
                {
                    current.left = currentTwo;
                }//end of else statement
                //updating the holders
                currentTwo = current;
                current = current.right;
            }//end of for loop
            current = first;
        }//end of for loop

        //connecting the nodes downward
        currentTwo = first;
        for (int i = 1; i < col; i++)
        {
            int counter = i;//counter
            //making sure we start from the column
            while (counter != 0)
            {
                current = first.right;
                counter--;
            }//end of while loop
            //for loop for all the columns in a row
            for (int k = 0; k < row; k++)
            {
                int count = i;//counter
                int counterTwo = k + 1;//counter #2
                //making sure we start from the right row
                while (counterTwo != 0)
                {
                    currentTwo = currentTwo.down;
                    counterTwo--;
                }//end of while loop
                while (count != 0)
                {
                    //updating a holder if need be
                    if(k+1!=row)
                    {
                        currentTwo = currentTwo.right;
                    }//end of if statement
                    count--;
                }//end of while loop
                //updating holders
                current.down = currentTwo;
                current = currentTwo;
                currentTwo = first;
            }//end of for loop
            current = first;
        }//end of for loop

        //connecting the nodes upward
        currentTwo = first;
        for (int i = 0; i < col; i++)
        {
            int counter = i;//counter
            //making sure we start from the right column
            while (counter != 0)
            {
                current = first.right;
                counter--;
            }//end of while loop
            //for loop runs through all the nodes vertically
            for (int k = 0; k < (row-1); k++)
            {
                int count = i;//counyer
                int counterTwo = k + 1;//counter #2
                //making sure we start from the right row
                while (counterTwo != 0)
                {
                    current = current.down;
                    counterTwo--;
                }//end of while loop
                //making sure we start from the right column
                while (count != 0)
                {
                    currentTwo = currentTwo.right;
                    count--;
                }//end of while loop
                //updating holders
                current.up = currentTwo;
                currentTwo=current;
                current=first;
                currentTwo=first;
            }//end of for loop
        }//end of for loop
        }//end of constructor

        //function displays the auditorium to the user
        public void display(Auditorium seat,int row,int col)
        {
            TheaterSeat current=first;//holds nodes
            int counterR=1;//counter for row display
            //for loop to display all the elements
            for (int i=0;i <row;i++)
            {
                //if we are at the start we dsiplay the labels
                if(i==0)
                {
                    System.out.print("  ");
                    //displaying the label for the columns
                    for (int l=0;l<col;l++)
                    {
                        System.out.print((char)(l+65));
                    }//end of for loop
                    System.out.println();
                }//end of if statement
                //displaying the rows label
                System.out.print(counterR+" ");
                counterR++;
                int counter=i;//counter
                //making sure we are dsiplaying the right row
                while(counter!=0)
                {
                    current=current.down;
                    counter--;
                }//end of while loop
                //runs until all the columns in the row are shown
             while(current!=null)
             {
                 //if reserved we display a '#'
                 if(current.getReserved())
                 {
                    System.out.print("#");
                 }//end of if statement
                 //else we display the '.'
                 else
                 {
                     System.out.print(current.getTicketType());
                 }//end of else statement
                 current=current.right;
             }//end of for loop
                System.out.println();
                current=first;
            }//end of for loop

        }//end of display function

        //checkPlace function to check and see if the original choice of the user is open or not
        public boolean checkPlace(Auditorium seat,int colNum,int rowNum,int tickets,int adult,int child,int senior)
        {
            boolean done=false;//falg that tells if the reservation was made or not
            boolean check=true;//flag for checkong space
            TheaterSeat current=first;//holds the first node
            TheaterSeat starting;//holds starting position
            //getting to the right row
            for(int i=1;i<rowNum;i++)
            {
            current=current.down;
            }//end of for loop
            //getting to the right column
            for (int k=1;k<colNum;k++)
            {
            current=current.right;
            }//end of for loop

            starting=current;//holding starting point
            //loop runs until space is confirmed or not available
            while(current!=null && tickets>0&&check!=false)
            {
                //if open we reduce the need of space
                if(!current.getReserved())
                {
                    tickets--;
                }//end of if statement
                //else we set flag to false
                else
                {
                    check=false;
                }//end of else statement
                current=current.right;
            }//end of while loop
            //if all seats were taken care of we reserve them
            if (tickets==0)
            {
                done=true;
                //setting all the adult tickers
                while(adult>0)
                {
                    starting.setTicketType('A');
                    starting.setReserved(true);
                    starting=starting.right;
                    adult--;
                }//end of while loop
                //setting all the child tickers
                while(child>0)
                {
                    starting.setTicketType('C');
                    starting.setReserved(true);
                    starting=starting.right;
                    child--;
                }//end of while loop
                //setting all the senior tickers
                while(senior>0)
                {
                    starting.setTicketType('S');
                    starting.setReserved(true);
                    starting=starting.right;
                    senior--;
                }//end of while loop
            }//end of if statement
            return done;
        }//end of function




    //function to find an alternative place for reserveation if the users original space is taken. Nothing is returned
    public void altPlace(Auditorium seat, int col, int row, int child, int adult, int senior, int tickets, Scanner sc)
    {
        double midx;//holds midpoint x
        double midy;//holds midpoint y
        double startx;//holds starting x value
        double starty;//holds starting y value
        double currDistance=100000;//holds current distance
        double distance=0;//holds distance
        boolean altExist=false;//flag to see if new spot is available
        int colCounter=0;//column counter
        double holdx=0;//holds current x
        double holdy=0;//holds current y
        char response='0';//holds Y/N response
        boolean space=true;//holds space flag
        int spaceCounter=0;//space counter
        int counter;//counter

        TheaterSeat current=first;//holds head
        TheaterSeat end=current;//holds end

            //calculating midpoint of y and making adjustments if its a odd number
            midy=row/2.0;
            if(row%2==1)
            {
                midy+=.5;
            }//end of if statement
        //calculating midpoint of y and making adjustments if its a odd number
            midx=col/2.0;
            if(col%2==1)
            {
                midx+=.5;
            }//end of if statement
            //making an offsett to match the middle of the reservation
            if(tickets<=col && col%2==1)
            {
            midx-=(tickets/2);
            }//end of if statement
            //making an offsett to match the middle of the reservation
            else
            {
            midx-=(tickets/2)-1;
            }//end of else statement
            //nested for loops to traerse the whole grid
            for(int i=1;i<=row;i++)
            {
             current=first;
             counter=i;//counter
             starty=i;//holds starting y
             //getting to the right row
             while(counter!=1)
             {
               current=current.down;
               counter--;
             }//end of whileloop
             //runs until row is completed
            while(current!=null)
            {
                TheaterSeat temp=current;//acts as variable to check elements after current to see if there is space or not
                colCounter++;
                //if it is not reserved we count it as open space
             if(!temp.getReserved())
             {
                 spaceCounter++;

                 int count=colCounter;//counter
                 //while loop to see if there is enought consecutive open spaces for reservations
                 while((spaceCounter != tickets) && (temp.right != null) && space && (count < col))
                 {
                     temp=temp.right;
                     //if the space is open we count it
                  if(!temp.getReserved())
                  {
                      spaceCounter++;
                  }//end of if statement

                  //else we set flag to false
                  else
                  {
                      space=false;
                  }//end of else statement
                    count++;
                    //making sure that there are not enough spaces left and updating flag
                     if(count==col && spaceCounter!=tickets)
                     {
                         space=false;
                     }//end of if statement
                 }//end of while loop
                    if(spaceCounter<tickets)
                    {
                        space=false;
                    }//end of if statement
                 //if there is space we check to see if its closest to the best spot
                 if(space)
                 {

                     altExist=true;//updating flag
                     startx=colCounter;
                     //calculating the distance between current point and midpoitn
                     distance=Math.sqrt((starty-midy)*(starty-midy)+(startx-midx)*(startx-midx));
                     //if its less than previous distance we set this as the best seats for the user
                     if(distance<currDistance)
                     {
                         end=temp;
                         currDistance=distance;
                         holdx=startx;
                         holdy=starty;
                     }//end of if statement
                     //else if the distance is the same and row is smaller than previous but in the best row we set it as
                     //the best
                     else if((distance==currDistance)&&(starty==midy))
                     {
                         currDistance=distance;
                         holdx=startx;
                         holdy=starty;
                     }//end of if statement
                 }//end of if statement
             }//end of if statement
                //resetting flags and counters for next run
                space=true;
                current=current.right;
                spaceCounter=0;

            }//end of while loop
                colCounter=0;
            }//end of for loop
            //if alt exists we tell the user and ask if they are ok with the new seats
            if(altExist)
            {
                System.out.println("We found alternative seats since the original ones are not available");
                System.out.println("They are seats " + holdy + (char) (holdx + 64) + " to " + holdy+ (char)((holdx+tickets-1)+64));
                //getting the users response
                while (response != 'Y' && response != 'N')
                {
                    sc.nextLine();
                    //validating user input
                    try
                    {
                        System.out.println("Do you accept? (Y/N)");
                        response = sc.next().charAt(0);
                    }//end of try
                    catch (Exception e)
                    {
                        sc.nextLine();
                        System.out.println("Do you accept? (Y/N)");
                    }//end of catch
                }//end of while loop
            }//end of if statement
        //if they agree we reserve the seats and let them know that it was done
            if (response=='Y')
            {
                System.out.println("Your New Reservation Has Been Completed!");
                current=first;
             //for loop to get to the right row
            for (int l=1;l<holdy;l++)
            {
             current=current.down;
            }//end of for loop
                //for loop to get to the right column
                for (int j=1;j<holdx;j++)
                {
                    current=current.right;
                }//end of for loop
                //placing all the adult tickets
                while(adult>0)
                {
                    current.setTicketType('A');
                    current.setReserved(true);
                    current=current.right;
                    adult--;
                }//end of while loop
                //placing all the child tickets
                while(child>0)
                {
                    current.setTicketType('C');
                    current.setReserved(true);
                    current=current.right;
                    child--;
                }//end of while loop
                //placing all the senior tickets
                while(senior>0)
                {
                    current.setTicketType('S');
                    current.setReserved(true);
                    current=current.right;
                    senior--;
                }//end of while loop
            }//end of if statement
            //else we tell them that there are no open spaces for their needs
            else if (response!='Y'&&response!='N')
            {
                System.out.println("We are unable to accommodate your request at this time.");
            }//end of else statement
    }//end of altPlace function


    //function that shows the final display of the auditorium before terminating
    public void finalDisplay(Auditorium seat,int row,int col)
    {
        TheaterSeat current=first;//holds seats
        int totalTickets=0;//holds total tickets
        int adultT=0;//holds adult tickets
        int childT=0;//holds child tickets
        int seniorT=0;//holds senior tickets
        double totalSales=0;//holds sales
        int counterR=1;//holds counter
        //for loop to display whole auditorium
        for (int i=0;i <row;i++)
        {
            //if its 0 we display teh column headers
            if(i==0)
            {
                System.out.print("  ");
                //displaying column headers
                for (int l=0;l<col;l++)
                {
                    System.out.print((char)(l+65));
                }//end of for loop

                System.out.println();
            }//end of if statement

            System.out.print(counterR+" ");
            counterR++;
            int counter=i;//counter
            //starting from the right row
            while(counter!=0)
            {
                current=current.down;
                counter--;
            }//end of while loop
            //runs until row has ended
            while(current!=null)
            {
                //if seat is reserved we check to see what type and ouput a #
                if(current.getReserved())
                {   //incrementing total tickets
                    totalTickets++;
                    //updating child counter if its C
                    if(current.getTicketType()=='C')
                    {
                        childT++;
                    }//end of if statement
                    //incrementing adult counter if its A
                    else if (current.getTicketType()=='A')
                    {
                        adultT++;
                    }//end of else if statement
                    //incrementing senior counter if its S
                    else if (current.getTicketType()=='S')
                    {
                        seniorT++;
                    }//end of else if statement
                    System.out.print("#");
                }//end of if statement
                else
                {
                    System.out.print(current.getTicketType());
                }//end of else statement
                current=current.right;
            }//end of for loop
            System.out.println();
            current=first;
        }//end of for loop
        //displaying the stats of the auditorium to the user
        System.out.println("Total Seats in Auditorium: " +(row*col));
        System.out.println("Total Tickets Sold:        " +totalTickets);
        System.out.println("Adult Tickets Sold:        " + adultT);
        System.out.println("Child Tickets Sold:        " + childT);
        System.out.println("Senior Tickets Sold:       "+ seniorT);
        totalSales=(adultT*10)+(childT*5)+(seniorT*7.50);
        System.out.println("Total Ticket Sales:       $" +String.format("%.2f",totalSales));
    }//end of display function

//function to update the auditorium file and rewrite it
public void reWrite(Auditorium seats,int col,int row) throws IOException
{
    FileWriter edit;//editor
    TheaterSeat current=first;//acts as holder for seats
    edit=new FileWriter("A1.txt");
    //loop that runs through the whole grid
    for (int i=0;i<row;i++)
    {
        current=first;
        int counter=i;//counter
        //making sure we start from the right row
        while(counter!=0)
        {
            current=current.down;
            counter--;
        }//end of while loop
        //runs until the end of the row
        while (current!=null)
        {
            //rewriting the file
         edit.write(current.getTicketType());
         current=current.right;
        }//end of while loop
        edit.write("\n");
    }//end of for loop
    //closing the editor
        edit.close();
}//end of reWrite function

}//end of auditorium class

