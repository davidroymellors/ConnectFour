//Code Attribution
//Farrell, J. 2019. Java Programming. 9th edition. 2019. Course Technology, Cengage Learning
package connectfour;

import java.text.SimpleDateFormat;  
import java.util.Date;   

public class Scoreboard
{
    //method creates a scoreboard using score stored in redPlayer and yellowPlayer instances of RedPlayer and YellowPlayer class
    public static void createScoreboard(RedPlayer redPlayer, YellowPlayer yellowPlayer)
    {
        
        //storing red player score in redScore int
        int redScore = redPlayer.getScore();
        //storing yellow player score in yellowScore int
        int yellowScore = yellowPlayer.getScore();
        
        //calling of SimpleDateFormat class with format "HH:mm:ss" 
        //to display the time of win on the scoreboard
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");  
        Date date = new Date();
        String time = formatter.format(date);
        
        //output of scoreboard will formatting
        System.out.println("----------------\n"
                         + "|-----TIME-----|\n"
                         + "|---" + time +"---|\n"
                         + "|--------------|\n"
                         + "|-RED---YELLOW-|\n"
                         + "|--" + redScore +"--------" + yellowScore +"--|\n"
                         + "----------------");   
                               
    }
}
