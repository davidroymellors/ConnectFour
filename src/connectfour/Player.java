//Code Attribution
//Farrell, J. 2019. Java Programming. 9th edition. 2019. Course Technology, Cengage Learning
package connectfour;

import java.util.Scanner;


public class Player
{
    //variables used to store player details privately
    private String playerColour;
    private String playerAlias;
    private String playerChant;
    private String playerLosingExpr;
    private int score;
    
    //Super Player constructer with String colour as parameter
    public Player(String colour)
    {  
    }
    
    //all set methods of player class extended to subclasses
    public void setPlayerAlias(String playerAlias)
    {
        this.playerAlias = playerAlias;
    }
    
    public void setPlayerChant(String chant)
    {
        this.playerChant = chant;
    }
    
    public void setLosingExpr(String losingExpr)
    {
        this.playerLosingExpr = losingExpr;
    }
    
    public void setScore(int score)
    {
        this.score = score;
    }
    
    //all get methods of player class extended to sub classes
    public int getScore()
    {
        return this.score;
    }
    
    public String getPlayerAlias()
    {
        return this.playerAlias;
    }
    
    public String getPlayerChant()
    {
        return this.playerChant;
    }
    
    public String getLosingExpr()
    {
        return this.playerLosingExpr;
    }
    
    
    //method used to record player details
    public void playerSetup()
    {   
        Scanner input = new Scanner(System.in);
        //set red player alias
        System.out.println("Please set the player's alias: ");
        this.playerAlias = input.nextLine();

        //set red player winning chant
        System.out.println("Now that you have set an alias,\n"
                + playerAlias + ", please enter a chant for when you win: ");
        this.playerChant = input.nextLine();


        //set red player losing expression
        System.out.println("This is your winning chant: " + playerChant + "\n"
                + "Now set your losing expression: ");
        this.playerLosingExpr = input.nextLine();
    }
    
    //method used to print all player details
    public String printPlayerDetails()
    {
        String playerDetails = ("Player alias: " + playerAlias
                        + "\nRed player winning chant: " + playerChant
                        + "\nRed player losing expression: " + playerLosingExpr);
        return playerDetails;
    }
}
