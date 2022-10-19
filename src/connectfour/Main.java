//Code Attribution
//Farrell, J. 2019. Java Programming. 9th edition. 2019. Course Technology, Cengage Learning
package connectfour;

import java.util.Scanner;


public class Main
{
    public static void main(String[] args)
    {

        //Creating object of ConnectFour class named connect4
        System.out.println("Welcome to Connect Four");
        ConnectFour connect4 = new ConnectFour();
        connect4.game();
    }
}
