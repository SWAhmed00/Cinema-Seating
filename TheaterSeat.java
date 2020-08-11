// Author:        Syed Wajih Ahmed (SWA170000)
// Course:        CS2336.503
// Date Due:      11/3/2019
// Assignment:    Project 3
// Compiler:      IntelliJ
package Tickets;

public class TheaterSeat extends baseNode
{

    public TheaterSeat up;//up node
    public TheaterSeat down;//down node
    public TheaterSeat right;//right node
    public TheaterSeat left;//left node

    //constructor with all the parameters
    TheaterSeat(int row,char seat,boolean reserved,char ticketType)
    {
        //setting the object based on input parameters
    this.row=row;
    this.seat=seat;
    this.reserved=reserved;
    this.ticketType=ticketType;

    }//end of constructor
    //getter function for up
    public TheaterSeat getUp()
    {
        return up;
    }//end of getter function

    //setter function for up
    public void setUp(TheaterSeat up) {
        this.up = up;
    }//end of setter function

    //setter function for down
    public void setDown(TheaterSeat down) {
        this.down = down;
    }//end of setter function

    //setter function for right
    public void setRight(TheaterSeat right) {
        this.right = right;
    }//end of setter function

    //setter function for left
    public void setLeft(TheaterSeat left) {
        this.left = left;
    }//end of setter function

    //getter function for down
    public TheaterSeat getDown() {
        return down;
    }//end of getter function

    //getter function for right
    public TheaterSeat getRight() {
        return right;
    }//end of getter function

    //getter function for left
    public TheaterSeat getLeft() {
        return left;
    }//end of getter function

}//end of TheaterSeat class
