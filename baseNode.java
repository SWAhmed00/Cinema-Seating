
// Compiler:      IntelliJ
package Tickets;

public abstract class baseNode
{
public int row;//holds row
public char seat;//holds seat
public boolean reserved;//holds reserved
public char ticketType;//holds ticketType
baseNode()
{

}//end of defualt constructor


    //getter function for row
    public int getRow()
    {
        return row;
    }//end of getRow function

    //getter function for seat
    public char getSeat()
    {
        return seat;
    }//end getSeat function

    //getter function for reserced
    public boolean getReserved()
    {
        return reserved;
    }//end of getreserved function

    //getter function for ticket type
    public char getTicketType()
    {
        return ticketType;
    }//end of getTicketType function

    //setter function for row
    public void setRow(int row)
    {
        this.row=row;
    }//end of setRow function

    //setter function for seat
    public void setSeat(char seat)
    {
        this.seat=seat;
    }//end setSeat function

    //setter function for reserved
    public void setReserved(boolean reserved)
    {
        this.reserved=reserved;
    }//end of setreserved function

    //setter function for ticket type
    public void setTicketType(char ticketType)
    {
       this.ticketType=ticketType;
    }//end of setTicketType function

}//end of baseNode class
