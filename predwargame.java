package predwargame;
import java.lang.Math;

public class predwargame {

	public static void main(String[] args) {
		//initializing necessary variables
		int round = 1;
		int tokensX = 1;
		int tokensY = 5;
		int tokensZ = 8;
		double myBetrayPercent;
		double otherBetrayPercent;
		String myPlayer;
		String otherPlayer;
		int myTokens = 0;
		int otherTokens = 0;
		
		//test game with 10 rounds
		while(round <= 100) { 
			myBetrayPercent = Math.random();
			//System.out.print(betrayPercent + " ");
			myPlayer = my_player(tokensX, tokensY, tokensZ, round, myBetrayPercent);
			
			otherBetrayPercent = Math.random();
			//System.out.print(betrayPercent + " ");
			otherPlayer = other_player(tokensX, tokensY, tokensZ, round, otherBetrayPercent);
			
			
			if(myPlayer == otherPlayer) { 
				if(myPlayer == "cooperate") { //if both players choose to cooperate
					myTokens += tokensY;
					otherTokens += tokensY;
				}
				else{ //if both players choose to betray
					myTokens += tokensX;
					otherTokens += tokensX;
				}
			}
			else if(myPlayer == "cooperate" && otherPlayer == "betray") { //if other player betrays and my player cooperates
				otherTokens += tokensZ;
			}
			else { //if my player betrays and other cooperates
				myTokens += tokensZ;
			}
			
			round++;
			
			System.out.println("My player: " + myPlayer + "\tOther player: " + otherPlayer + "\t\tMy tokens: " + myTokens + "\tOther tokens: " + otherTokens);
		}
		
		if(myTokens > otherTokens) {
			System.out.println("My player wins!");
		}
		else if(myTokens < otherTokens) {
			System.out.println("Your player wins!");
		}
		else {
			System.out.println("It's a tie.");
		}
		
	}
	
	/**
	 * my_player
	 * @param int x
	 * @param int y
	 * @param int z
	 * @param in t r
	 * @param double b
	 * @return String
	 * 
	 * Will take into account the affect on token amount in choice to betray or cooperate
	 * My player will betray 65% of the time, and cooperate 35% of the time
	 * Whether or not the player betrays or cooperates is not based on the round number, that may be implemented later
	 *
	 */
	private static String my_player(int x, int y, int z, int r, double b) {
		//x, y, and z must be between 1 and 10; x < y < z
		//receive x tokens if my player betrays and other player betrays
		//receive y tokens if my player cooperates and other player cooperates
		//receive z tokens if my player betrays and other player cooperates
		
		//if tokens for betraying is significantly more than other options or the reward for both players cooperating is not greater than 2, betray
		if (z - y > 4 || y - x <= 2) { 
			return "betray";
		}
		
		if(r <= 10000) { //less than 10000 rounds
			if(b < 0.25) { //cooperate 25% of time
				return "cooperate";
			}
			else if(b > 0.65) { //betray 35% of time
				return "betray";
			}
			else {
				if(Math.random() < 0.25) { //cooperate 25% of 40% of time = 10%
					return "cooperate";
				}
				else { // betray 75% of 40% of time = 30%
					return "betray";
				}
			}
		}
		return "betray";
	}
	
	/**
	 * other_player
	 * @param int x
	 * @param int y
	 * @param int z
	 * @param in t r
	 * @param double b
	 * @return String
	 * 
	 * strategy changes and is used to play against my_player
	 *
	 */
	private static String other_player(int x, int y, int z, int r, double b) { //sample other player strategy used to test my player's strategy
		if(r%2 == 0) { //alternate betray and cooperate every other round
			return "cooperate";
		}
		else {
			return "betray";
		}
	}
}
