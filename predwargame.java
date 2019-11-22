package predwargame;
import java.lang.Math;

public class predwargame {

	public static void main(String[] args) {
		int round = 1;
		int tokensX = 2;
		int tokensY = 5;
		int tokensZ = 8;
		//double betrayPercent = 0;
		double myBetrayPercent;
		double otherBetrayPercent;
		String myPlayer;
		String otherPlayer;
		
		int myTokens = 0;
		int otherTokens = 0;
		
		//x, y, and z must be between 1 and 10; x < y < z
		
		//receive x tokens if my player betrays and other player betrays
		
		//receive y tokens if my player cooperates and other player cooperates
		
		//receive z tokens if my player betrays and other player cooperates
		
		while(round <= 10) {
			myBetrayPercent = Math.random();
			//System.out.print(betrayPercent + " ");
			myPlayer = my_player(tokensX, tokensY, tokensZ, round, myBetrayPercent);
			System.out.print("My player: " + myPlayer + "\t");
			
			otherBetrayPercent = Math.random();
			//System.out.print(betrayPercent + " ");
			otherPlayer = other_player(tokensX, tokensY, tokensZ, round, otherBetrayPercent);
			System.out.println("Other player: " + otherPlayer);
			
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
		}
		
		System.out.println("My tokens: " + myTokens + "\tOther tokens: " + otherTokens);
	}
	
	private static String my_player(int x, int y, int z, int r, double b) {
		if(r <= 10000) { //less than 10000 rounds
			if(b < 0.25) { 
				return "cooperate";
			}
			else if(b > 0.65) {
				return "betray";
			}
			else {
				if(Math.random() < 0.25) {
					return "cooperate";
				}
				else {
					return "betray";
				}
			}
		}
		return "betray";
	}
	
	private static String other_player(int x, int y, int z, int r, double b) { //sample other player strategy used to test my player's strategy
		if(r%2 == 0) { //alternate betray and cooperate every other round
			return "cooperate";
		}
		else {
			return "betray";
		}
		
	}
}
