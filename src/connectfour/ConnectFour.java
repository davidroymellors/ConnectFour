//Code Attribution
//Farrell, J. 2019. Java Programming. 9th edition. 2019. Course Technology, Cengage Learning
package connectfour;

import java.util.InputMismatchException;
import java.util.Scanner;


public class ConnectFour
{
    //declaration of objects of RedPlayer and YellowPlayer sub classes of Player
    public static RedPlayer red = new RedPlayer();
    public static YellowPlayer yellow = new YellowPlayer();
    
    //method that groups all game logic 
    public static void game()
    {
        //booleans used to determine if user would like to re-enter details
        boolean redTryAgain = true;
        boolean yellowTryAgain = true;
        //object input of Scanner class used to record user input
        Scanner input = new Scanner(System.in);
        
        //loop until user is happy with details
        while(redTryAgain)
        {    
            System.out.println("RED PLAYER DETAILS: ");
            //calling of setup method in Player class using instance of RedPlayer red
            red.playerSetup();
            
            System.out.println("Are you happy with these details?"
                    + "\nPlease type \"y\"  for yes or \"n\" for no:");
            String choice = input.nextLine();
            //if user is happy with details loop will end
            if(choice.contains("y"))redTryAgain = false;
        }
        
        //loop until user is happy with details
        while(yellowTryAgain)
        {
            System.out.println("YELLOW PLAYER DETAILS: ");
            //calling of setup method in Player class using instance of YellowPlayer yellow
            yellow.playerSetup();
            System.out.println("Are you happy with these details?"
                        + "\nPlease type \"y\"  for yes or \"n\" for no:");
            String choice = input.nextLine();
            //if user is happy with details loop will end
            if(choice.contains("y"))yellowTryAgain = false;
        }
        
        //boolean used to determine if user would like to play another game
        boolean playAgain = true;
        
        //New games will keep starting until user decides to stop
        while(playAgain)
        {
            //Array used to store table created by createTable() method
            String[][] table = createTable();
            
            //Game will run until a winner is chosen using boolean loop
            boolean loop = true;
            
            //count to count each round
            int count = 0;
            //redWin used to store each time the red player wins
            int redWin = 0;
            //yellow win used to store each time the yellow player wins
            int yellowWin = 0;
            
            //used to store user input if they want to end the game
            String endGame;
            
            //Connect Four table is printed using printTable method
            printTable(table);
            
            //While loop will run until a winner is chosen using boolean loop
            while(loop)
            {
                //asking user if they would like to quit the game
                System.out.print("If you would like to end this game type \"y\","
                        + "\nif not press any key to skip: ");
                endGame = input.nextLine();
                //loop will break if user chooses yes
                if(endGame.contains("y")) break;
                
                //logic used to make red disc play the even rounds and yellow to start on the odd rounds
                if (count % 2 == 0) dropRedDisc(table);//dropRedDisc() method used to place the letter "R" within the correct coloumn and row the user chooses  
                else dropYellowDisc(table);//dropYellowDisc() method used to place the letter "Y" within the correct coloumn and row the user chooses 
                
                //increment count to record that 1 round has been played
                count++;
                //print table after user has chosen where to place their disc
                printTable(table);

               //after each turn the game will check for a winner and if found it will say who won using checkWinner() method
               if (checkWinner(table) != null)
               {
                    if (checkWinner(table) == "R")
                    {
                        //output of red winner name, winner chant and loser expression
                        System.out.println( red.getPlayerAlias() + ", won."
                                + "\n" + red.getPlayerAlias() + ": " + red.getPlayerChant()
                                + "\n" +yellow.getPlayerAlias()+ ": " + yellow.getLosingExpr());
                        redWin++;
                    }

                    else if (checkWinner(table)== "Y")
                    {
                        //output of yellow winner name, winner chant and loser expression
                         System.out.println( yellow.getPlayerAlias() + ", won."
                                + "\n" + yellow.getPlayerAlias() + ": " + yellow.getPlayerChant()
                                + "\n" + red.getPlayerAlias()+ ": " + red.getLosingExpr());
                         yellowWin++;
                    }
                    //passing the current score of each team to the player's relative class
                    red.setScore(redWin);
                    yellow.setScore(yellowWin);
                    
                   //when someone wins the game will end
                   loop = false;
                }
            }
            //creating scoreboard object  to pass red and yellow instances to Scoreboard class
            Scoreboard scoreBoard = new Scoreboard();
            scoreBoard.createScoreboard(red, yellow);
        
            //asks the user if they would like to continue playing
            System.out.print("Would you like to play again?\n"
                            + "Please type \"y\" for yes or \"n\" for no: ");
            String keepPlaying = input.nextLine();
            //if they choose no the program will end
            if(keepPlaying.contains("n")) playAgain = false;
      
        }
    }
    
    //create table method uses an inner and outer loop to populate an array of 7 rows and 15 coloumns
    public static String[][] createTable()
    {
        //2D arrary made up of 7 rows and 15 coloumns because each coloumn will be separted by a |
        //and the final row will be a sequence of ------
        String[][] table = new String[7][15];
        
        //for loop to loop through each row from top to bottom
        for(int i = 0; i < table.length; i++)
        {
            //for loop to loop over each column from left to right
            for(int j = 0; j < table[i].length; j++)
            {
                if(j%2 == 0) table[i][j] = "|";
                else table[i][j] = " ";
                
                if(i == 6) table[i][j] = "-";
            }
        }
        return table;
    }
    
    //printTable method used to print out the entire array.
    public static void printTable(String[][] table)
    {
        for(int i = 0; i < table.length; i++)
        {
            for(int j = 0; j < table[i].length; j++)
            {
                System.out.print(table[i][j]);
            }
            System.out.println();
        }
        
    }
    
    //method used to drop a Red disc in the user selected column
    public static void dropRedDisc(String[][] table)
    {
        //storing red player alias in redAlias string
        String redAlias = red.getPlayerAlias();
        Scanner input = new Scanner(System.in);
        
        //try catch statement within while loop to repeat program until correct input format is met
        while(true)
        { 
            try
            {
                //ask user to choose a column from 0-6 not 0-15 as all the odd coloumns are filled with | to form borders
                System.out.println( redAlias + ", choose a column to drop a red disk (0-6)");
                
                //formula for converting a 1-2-3-4-5-6
                //user column number into a 1-3-5-7-9-11-13
                int choice = 2*(input.nextInt()) + 1;
                
                //for loop to loop over each row from bottom to top
                //till the first empty space is found and then place a "R" there and exit the loop
                for(int i = 5; i > 0; i--)
                {
                    if(table[i][choice] == " ")
                    {
                        table[i][choice] = "R";
                        break;
                    }
                }  
                break;
            }
            catch(InputMismatchException | ArrayIndexOutOfBoundsException e)
            {
                System.out.println("Your input was invalid, please try again: ");
                input.next();
                continue;
            }
        }
    }
    public static void dropYellowDisc(String[][] table)
    {
        
        String yellowAlias = yellow.getPlayerAlias();
        Scanner input = new Scanner(System.in);

        while(true)
        {
            try
            {
                //ask user to choose a column from 0-6 not 0-15 as all the odd coloumns are filled with | to form borders
                System.out.println(yellowAlias + ", choose a column to drop a yellow disk (0-6): ");
                
                //formula for converting a 1-2-3-4-5-6
                //user column number into a 1-3-5-7-9-11-13
                int choice = 2*(input.nextInt()) + 1;
                
                //for loop to loop over each row from bottom to top
                //till the first empty space is found and then place a "R" there and exit the loop
                for(int i = 5; i > 0; i--)
                {
                    if(table[i][choice] == " ")
                    {
                        table[i][choice] = "Y";
                        break;
                    }
                }
                break;
            }
            catch(InputMismatchException | ArrayIndexOutOfBoundsException e)
            {
                System.out.println("Your input was invalid, please try again: ");
                input.next();
                continue;
            }
        }
    }
    

    //There are four patterns of Reds or Yellows that can win the game
    //One pattern is a horizontal line of four Rs or Ys,
    //another is a vertical line, another is a top-left to bottom-right diagonal line, 
    //and the last is bottom-left to top-right diagonal line
    public static String checkWinner(String[][] table)
    {
        //check for first type of win: horizontal line
        for(int i = 0;i < 6; i++)
        {
            for(int j = 0; j < 7; j+=2)
            {
                if((table[i][j+1] != " ")
                && (table[i][j+3] != " ")
                && (table[i][j+5] != " ") 
                && (table[i][j+7] != " ")
                && ((table[i][j+1] == table[i][j+3])
                && (table[i][j+3] == table[i][j+5])
                && (table[i][j+5] == table[i][j+7])))
                    //If we found a same-colored pattern, we'll return
                    //the color's name so that we know who won
                    return table[i][j + 1];

            }
        }
        
        //second type of win: vertical line
        for(int i = 1; i < 15; i+= 2)
        {
            for(int j = 0; j < 3; j++)
            {
                if((table[j][i] != " ")
                && (table[j+1][i] != " ")
                && (table[j+2][i] != " ")   
                && (table[j+3][i] != " ")
                && ((table[j][i] == table[j+1][i])
                && (table[j+1][i] == table[j+2][i])
                && (table[j+2][i] == table[j+3][i])))
                    return table[j][i];
            }
        }
        
        //third type of win: bottom-right to top-left
        for(int i = 0; i < 3; i++)
        {
            for(int j = 1; j < 9; j+= 2)
            {
                if((table[i][j] != " ")
                && (table[i+1][j+2] != " ")
                && (table[i+2][j+4] != " ")
                && (table[i+3][j+6] != " ")
                && ((table[i][j] == table[i+1][j+2])
                && (table[i+1][j+2] == table[i+2][j+4])
                && (table[i+2][j+4] == table[i+3][j+6])))
                    return table[i][j];
                
            }
        }
        
        //fourth tyoe of win: bottom-left to top-right
        for(int i = 0; i < 3; i++)
        {
            for(int j = 7; j < 15; j+=2)
            {
                if((table[i][j] != " ")
               && (table[i+1][j+2] != " ")
               && (table[i+2][j+4] != " ")
               && (table[i+3][j+6] != " ")
               && ((table[i][j] == table[i+1][j+2])
               && (table[i+1][j+2] == table[i+2][j+4])
               && (table[i+2][j+4] == table[i+3][j+6])))
                   return table[i][j];
            }
        }
        //if no winning colour is found
        return null;
    }
    
}
