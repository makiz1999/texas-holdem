package holdem;
import java.io.*;
import java.util.*;

public class TexasHoldem {

	public static void main(String[] args) throws IOException{
		//Scanner input = new Scanner(System.in);
		BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in)); 
		ArrayList<String> stringList = new ArrayList<>();
		String lineString = stdin.readLine();
		while(lineString.length() != 0  && lineString != null) {
			stringList.add(lineString);
			lineString = stdin.readLine();
		}
		stdin.close();
		
		for(int element = 0; element < stringList.size(); element++) {
			String[] initialArr = stringList.get(element).split(" ");
			String[] concatinatedInputs = new String[initialArr.length-1];
			for(int i = 0; i < concatinatedInputs.length; i++) {
				concatinatedInputs[i] = initialArr[0] + initialArr[i+1];
			}
			
			//Initializing hands
			Hand[] hands = new Hand[concatinatedInputs.length];
			for(int i = 0; i < hands.length; i++) {
				hands[i] = new Hand(concatinatedInputs[i]);
			}
			
			//Sorting scores in ascending order
			for(int i = 0; i < hands.length; i++) {
				for(int j = 0; j < hands.length-1; j++) {
					if(hands[j].getScore() > hands[j+1].getScore()) {
						Hand tempHand = hands[j];
						hands[j] = hands[j+1];
						hands[j+1] = tempHand;
					}
				}
			}
			
			//Algorithm finding if hands have same value and adding next biggest value to the score
			for(int i = 0; i < hands.length; i++) {
				for(int j = hands.length-1; j > i; j--) {
					//We know that hands from i to j will have same values, so the amount of their rest cards will be the same
					if(hands[i].getScore() == hands[j].getScore() && !hands[i].isEmpty()) {
						for(int z = i; z <= j; z++) {
							hands[z].addNextValue();
						}
						//Sort particular part
						for(int start = i; start <= j; start++) {
							for(int second = i; second < j; second++) {
								if(hands[second].getScore() > hands[second+1].getScore()) {
									Hand tempHand = hands[second];
									hands[second] = hands[second+1];
									hands[second+1] = tempHand;
								}
							}
						}
						j++;
					}
				}
			}
			
			//Output values
			for(int i = 0; i < hands.length-1; i++) {
				if(hands[i].getScore() == hands[i+1].getScore()) {
					System.out.print(hands[i].getSubstring() + "=");
				} else {
					System.out.print(hands[i].getSubstring() + " ");
				}
			}
			System.out.print(hands[hands.length-1].getSubstring());
			System.out.println();
		}
	}
}
