package holdem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map.Entry;


public class Hand {
	private String initial;
	private int[] handInNumbers;
	private int[] handInNumbersAceBefore;
	private int score;
	private ArrayList<Integer> handsList;
	private ArrayList<Integer> handsListAceBefore;
	private ArrayList<Integer> handsListNoSuits;
	private ArrayList<Integer> handsListNoSuitsAceBefore;
	private HashMap<Integer, Integer> valuesHashMap;
	private HashMap<Integer, Integer> suitsHashMap;
	
	//Initializing Hand object
	public Hand(String initial) {
		this.initial = initial;
		handInNumbers = new int[initial.length()/2];
		handInNumbersAceBefore = new int[handInNumbers.length];
		score = 0;
		handsList = new ArrayList<>();
		handsListAceBefore = new ArrayList<>();
		handsListNoSuits = new ArrayList<>();
		handsListNoSuitsAceBefore = new ArrayList<>();
		valuesHashMap = new HashMap<>();
		suitsHashMap = new HashMap<>();
		
		
		//Passing values to array
		int pointer = 0;
		for(int i = 0; i < initial.length(); i += 2) {
			handInNumbers[pointer] = twoCharsToNum(initial.substring(i,i+2));
			pointer++;
		}
		
		//Passing values to array with Ace before 2
		for(int i = 0; i < handInNumbersAceBefore.length; i++) {
			if(handInNumbers[i] / 10 == 14) {
				handInNumbersAceBefore[i] = handInNumbers[i] - 130;
			} else {
				handInNumbersAceBefore[i] = handInNumbers[i];
			}
		}
		
		//HashMap with values of the cards
		for(int i = 0; i < handInNumbers.length; i++) {
			if(valuesHashMap.containsKey(handInNumbers[i]/10)) {
				valuesHashMap.replace(handInNumbers[i]/10, valuesHashMap.get(handInNumbers[i]/10)+1);
			} else {
				valuesHashMap.put(handInNumbers[i]/10, 1);
			}
		}
		
		//HashMap with suits of the cards
		for(int i = 0; i < handInNumbers.length; i++) {
			if(suitsHashMap.containsKey(handInNumbers[i]%10)) {
				suitsHashMap.replace(handInNumbers[i]%10, suitsHashMap.get(handInNumbers[i]%10)+1);
			} else {
				suitsHashMap.put(handInNumbers[i]%10, 1);
			}
		}
		
		//Adding number to the Lists
		for(int i = 0; i < handInNumbers.length; i++) {
			handsList.add(handInNumbers[i]);
			handsListAceBefore.add(handInNumbersAceBefore[i]);
			handsListNoSuits.add(handInNumbers[i]/10);
			handsListNoSuitsAceBefore.add(handInNumbersAceBefore[i]/10);
		}
		
		
		Arrays.sort(handInNumbers);
		Arrays.sort(handInNumbersAceBefore);
		Collections.sort(handsList);	
		Collections.sort(handsListAceBefore);
		
		//Checking if list has values with same suits from 10 to Ace
		if(hasRoyalFlush()) {
			score = 1300;
			if(handsList.contains(101) && handsList.contains(111) && handsList.contains(121) && handsList.contains(131) && handsList.contains(141)){
				handsList.remove(handsList.indexOf(101));
				handsList.remove(handsList.indexOf(111));
				handsList.remove(handsList.indexOf(121));
				handsList.remove(handsList.indexOf(131));
				handsList.remove(handsList.indexOf(141));			
			}
			if(handsList.contains(102) && handsList.contains(112) && handsList.contains(122) && handsList.contains(132) && handsList.contains(142)){
				handsList.remove(handsList.indexOf(102));
				handsList.remove(handsList.indexOf(112));
				handsList.remove(handsList.indexOf(122));
				handsList.remove(handsList.indexOf(132));
				handsList.remove(handsList.indexOf(142));	
			}
			if(handsList.contains(103) && handsList.contains(113) && handsList.contains(123) && handsList.contains(133) && handsList.contains(143)) {
				handsList.remove(handsList.indexOf(103));
				handsList.remove(handsList.indexOf(113));
				handsList.remove(handsList.indexOf(123));
				handsList.remove(handsList.indexOf(133));
				handsList.remove(handsList.indexOf(143));	
			}
			if(handsList.contains(104) && handsList.contains(114) && handsList.contains(124) && handsList.contains(134) && handsList.contains(144)) {
				handsList.remove(handsList.indexOf(104));
				handsList.remove(handsList.indexOf(114));
				handsList.remove(handsList.indexOf(124));
				handsList.remove(handsList.indexOf(134));
				handsList.remove(handsList.indexOf(144));	
			}
		//Checking if list has sequenced values with same suit
		} else if (hasStraightFlush()) {
			score = 1200;
			for(int i = 0; i < handsList.size(); i++) {
				if(handsList.contains(handsList.get(i)+10) && handsList.contains(handsList.get(i)+20) && handsList.contains(handsList.get(i)+30)  &&
						handsList.contains(handsList.get(i)+40)) {
					score += handsList.get(i)/10;
					score += handsList.get(handsList.indexOf(handsList.get(i)+10))/10;
					score += handsList.get(handsList.indexOf(handsList.get(i)+20))/10;
					score += handsList.get(handsList.indexOf(handsList.get(i)+30))/10;
					score += handsList.get(handsList.indexOf(handsList.get(i)+40))/10;
					int temp = handsList.get(i);
					handsList.remove(handsList.indexOf(temp+40));
					handsList.remove(handsList.indexOf(temp+30));
					handsList.remove(handsList.indexOf(temp+20));
					handsList.remove(handsList.indexOf(temp+10));
					handsList.remove(handsList.indexOf(temp));
					break;
				}
			}
		} else if (hasStraightFlushAceBefore()) {
			score = 1100;
			for(int i = 0; i < handsList.size(); i++) {
				if(handsListAceBefore.contains(handsListAceBefore.get(i)+10) && handsListAceBefore.contains(handsListAceBefore.get(i)+20) && handsListAceBefore.contains(handsListAceBefore.get(i)+30)  &&
						handsListAceBefore.contains(handsListAceBefore.get(i)+40)) {
					score += handsListAceBefore.get(i)/10;
					score += handsListAceBefore.get(handsListAceBefore.indexOf(handsListAceBefore.get(i)+10))/10;
					score += handsListAceBefore.get(handsListAceBefore.indexOf(handsListAceBefore.get(i)+20))/10;
					score += handsListAceBefore.get(handsListAceBefore.indexOf(handsListAceBefore.get(i)+30))/10;
					score += handsListAceBefore.get(handsListAceBefore.indexOf(handsListAceBefore.get(i)+40))/10;
					int temp = handsListAceBefore.get(i);
					handsList.remove(handsList.indexOf(temp+130));
					handsList.remove(handsList.indexOf(temp+40));
					handsList.remove(handsList.indexOf(temp+10));
					handsList.remove(handsList.indexOf(temp+20));
					handsList.remove(handsList.indexOf(temp+30));
				}
			}
		} else if (hasFourOfKind()) {
			score = 1000;
			int times = 0;
			int value = 0;
			for (Entry<Integer, Integer> entry : valuesHashMap.entrySet()) {
	            if (entry.getValue().equals(4) || entry.getValue().equals(6) ||entry.getValue().equals(5) || entry.getValue().equals(7)) {
	                value = entry.getKey();
	                times = 4;
	                break;
	            }
	        }
			//Temporary array to track values that has to be removed/added
			ArrayList<Integer> tempArrayList = new ArrayList<>();
			for(int i = 0; i < handsList.size(); i++) {
				if(handsList.get(i)/10 == value) {
					tempArrayList.add(handsList.get(i));
				}
			}
			
			for(int i = 0; i < times; i++) {
				score += handsList.get(handsList.indexOf(tempArrayList.get(0)))/10;
				handsList.remove(handsList.indexOf(tempArrayList.get(0)));
				tempArrayList.remove(0);
			}
			
		} else if(hasFullHouse()) {
			score = 700;
			int valueTriple = 0;
			int valueDouble = 0;
			for (Entry<Integer, Integer> entry : valuesHashMap.entrySet()) {
	            if (entry.getValue().equals(3)) {
	                valueTriple = entry.getKey();
	            }
	            if (entry.getValue().equals(2)) {
	                valueDouble = entry.getKey();
	            }
			}
			
			score = score + (valueTriple*14) + valueDouble;
			
			ArrayList<Integer> tempArrayListTriple = new ArrayList<>();
			for(int i = 0; i < handsList.size(); i++) {
				if(handsList.get(i)/10 == valueTriple) {
					tempArrayListTriple.add(handsList.get(i));
				}
			}
			ArrayList<Integer> tempArrayListDouble = new ArrayList<>();
			for(int i = 0; i < handsList.size(); i++) {
				if(handsList.get(i)/10 == valueDouble) {
					tempArrayListDouble.add(handsList.get(i));
				}
			}
			
			for(int i = 0; i<3; i++) {
				handsList.remove(handsList.indexOf(tempArrayListTriple.get(0)));
				tempArrayListTriple.remove(0);
			}
			for(int i = 0; i<2; i++) {
				handsList.remove(handsList.indexOf(tempArrayListDouble.get(0)));
				tempArrayListDouble.remove(0);
			}
			
		} else if(hasFlush()) {
			score = 600;
			int suit = 0;
			int suitCount = 0;
			//Finding which suit has flush
			for (Entry<Integer, Integer> entry : suitsHashMap.entrySet()) {
	            if (entry.getValue().equals(5) || entry.getValue().equals(6) || entry.getValue().equals(7)) {
	                suit = entry.getKey();
	                suitCount = 5;
	            }
			}
			//Adding cards to the score and removing it from the list
			int count = 0;
			for(int i = handsList.size()-1; i>=0; i--) {
				if(handsList.get(i)%10 == suit) {
					score += handsList.get(i)/10;
					handsList.remove(i);
					count++;
				}
				if(count == 5) {
					break;
				}
			}
			//6 cards left in the list. Sort cards in such way: left side non-suits, right card suits
			int index = handsList.size()-1;
			for(int i = handsList.size()-1; i >= 0; i--) {
				if(handsList.get(index)%10 != suit) {
					for(int j = index; j>0; j--) {
						int temp = handsList.get(j);
						handsList.set(j, handsList.get(j-1));
						handsList.set(j-1, temp);
					}
					index++;
				}
				index--;
			}
		} else if (hasStraight()) {
			score = 500;
			for(int i = 0; i < handsListNoSuits.size(); i++) {
				if(handsListNoSuits.contains(handsListNoSuits.get(i)+1) && 
						handsListNoSuits.contains(handsListNoSuits.get(i)+2) &&
						handsListNoSuits.contains(handsListNoSuits.get(i)+3)  &&
						handsListNoSuits.contains(handsListNoSuits.get(i)+4)) {
					score += handsListNoSuits.get(i);
					score += handsListNoSuits.get(handsListNoSuits.indexOf(handsListNoSuits.get(i)+1));
					score += handsListNoSuits.get(handsListNoSuits.indexOf(handsListNoSuits.get(i)+2));
					score += handsListNoSuits.get(handsListNoSuits.indexOf(handsListNoSuits.get(i)+3));
					score += handsListNoSuits.get(handsListNoSuits.indexOf(handsListNoSuits.get(i)+4));
					int temp = handsListNoSuits.get(i);
					handsListNoSuits.remove(handsListNoSuits.indexOf(temp+4));
					handsListNoSuits.remove(handsListNoSuits.indexOf(temp+3));
					handsListNoSuits.remove(handsListNoSuits.indexOf(temp+2));
					handsListNoSuits.remove(handsListNoSuits.indexOf(temp+1));
					handsListNoSuits.remove(handsListNoSuits.indexOf(temp));
					handsList.clear();
					for(int j = 0; i < handsListNoSuits.size(); i++) {
						handsList.add(handsListNoSuits.get(i)*10);
					}
					break;
				}
			}
		} else if (hasStraightAceBefore()) {
			score = 400;
			for(int i = 0; i < handsListNoSuitsAceBefore.size(); i++) {
				if(handsListNoSuitsAceBefore.contains(handsListNoSuitsAceBefore.get(i)+1) && 
						handsListNoSuitsAceBefore.contains(handsListNoSuitsAceBefore.get(i)+2) &&
						handsListNoSuitsAceBefore.contains(handsListNoSuitsAceBefore.get(i)+3)  &&
						handsListNoSuitsAceBefore.contains(handsListNoSuitsAceBefore.get(i)+4)) {
					score += handsListNoSuitsAceBefore.get(i);
					score += handsListNoSuitsAceBefore.get(handsListNoSuitsAceBefore.indexOf(handsListNoSuitsAceBefore.get(i)+1));
					score += handsListNoSuitsAceBefore.get(handsListNoSuitsAceBefore.indexOf(handsListNoSuitsAceBefore.get(i)+2));
					score += handsListNoSuitsAceBefore.get(handsListNoSuitsAceBefore.indexOf(handsListNoSuitsAceBefore.get(i)+3));
					score += handsListNoSuitsAceBefore.get(handsListNoSuitsAceBefore.indexOf(handsListNoSuitsAceBefore.get(i)+4));
					int temp = handsListNoSuitsAceBefore.get(i);
					handsListNoSuitsAceBefore.remove(handsListNoSuitsAceBefore.indexOf(temp+4));
					handsListNoSuitsAceBefore.remove(handsListNoSuitsAceBefore.indexOf(temp+3));
					handsListNoSuitsAceBefore.remove(handsListNoSuitsAceBefore.indexOf(temp+2));
					handsListNoSuitsAceBefore.remove(handsListNoSuitsAceBefore.indexOf(temp+1));
					handsListNoSuitsAceBefore.remove(handsListNoSuitsAceBefore.indexOf(temp));
					handsList.clear();
					for(int j = 0; i < handsListNoSuitsAceBefore.size(); i++) {
						if(handsListNoSuitsAceBefore.get(i) == 1) {
							handsList.add(140);
						} else {
						handsList.add(handsListNoSuitsAceBefore.get(i)*10);
						}
					}
					break;
				}
			}
			
		} else if (hasThreeOfKind()) {
			score = 300;
			int times = 3;
			int value = 0;
			for (Entry<Integer, Integer> entry : valuesHashMap.entrySet()) {
	            if (entry.getValue().equals(3)) {
	                value = entry.getKey();
	                break;
	            }
	        }
			//Temporary array to track values that has to be removed/added
			ArrayList<Integer> tempArrayList = new ArrayList<>();
			for(int i = 0; i < handsList.size(); i++) {
				if(handsList.get(i)/10 == value) {
					tempArrayList.add(handsList.get(i));
				}
			}
			
			for(int i = 0; i < times; i++) {
				score += handsList.get(handsList.indexOf(tempArrayList.get(0)))/10;
				handsList.remove(handsList.indexOf(tempArrayList.get(0)));
				tempArrayList.remove(0);
			}
		} else if(hasTwoPairs()) {
			score = 200;
			int firstPairValue = 0;
			int secondPairValue = 0;
			for (Entry<Integer, Integer> entry : valuesHashMap.entrySet()) {
	            if (entry.getValue().equals(2)) {
	                firstPairValue = entry.getKey();
	                valuesHashMap.remove(firstPairValue);
	                break;
	            }
	        }
			for (Entry<Integer, Integer> entry : valuesHashMap.entrySet()) {
	            if (entry.getValue().equals(2)) {
	                secondPairValue = entry.getKey();
	                valuesHashMap.remove(secondPairValue);
	                break;
	            }
	        }
			ArrayList<Integer> tempArrayList = new ArrayList<>();
			for(int i = 0; i < handsList.size(); i++) {
				if(handsList.get(i)/10 == firstPairValue) {
					tempArrayList.add(handsList.get(i));
				}
				if(handsList.get(i)/10 == secondPairValue) {
					tempArrayList.add(handsList.get(i));
				}
			}
			for(int i = 0; i < 4; i++) {
				score += handsList.get(handsList.indexOf(tempArrayList.get(0)))/10;
				handsList.remove(handsList.indexOf(tempArrayList.get(0)));
				tempArrayList.remove(0);
			}
			
		} else if (hasPair()){
			score = 100;
			int value = 0;
			for (Entry<Integer, Integer> entry : valuesHashMap.entrySet()) {
	            if (entry.getValue().equals(2)) {
	                value = entry.getKey();
	                break;
	            }
	        }
			ArrayList<Integer> tempArrayList = new ArrayList<>();
			for(int i = 0; i < handsList.size(); i++) {
				if(handsList.get(i)/10 == value) {
					tempArrayList.add(handsList.get(i));
				}
			}
			for(int i = 0; i < 2; i++) {
				score += handsList.get(handsList.indexOf(tempArrayList.get(0)))/10;
				handsList.remove(handsList.indexOf(tempArrayList.get(0)));
				tempArrayList.remove(0);
			}
		} else {
			score = 0;
			score += handsList.get(handsList.size()-1)/10;
			handsList.remove(handsList.size()-1);
		}
		
		//
		for(int i = 0; i < handsList.size(); i++) {
			handsList.set(i, handsList.get(i)/10);
		}
		Collections.sort(handsList);
	}
	
	
	private boolean hasRoyalFlush() {
		if((handsList.contains(101) && handsList.contains(111) && handsList.contains(121) && handsList.contains(131) && handsList.contains(141)) || 
				(handsList.contains(102) && handsList.contains(112) && handsList.contains(122) && handsList.contains(132) && handsList.contains(142)) ||
				(handsList.contains(103) && handsList.contains(113) && handsList.contains(123) && handsList.contains(133) && handsList.contains(143)) ||
				(handsList.contains(104) && handsList.contains(114) && handsList.contains(124) && handsList.contains(134) && handsList.contains(144))) {
			return true;
		} else {
			return false;
		}
	}
	
	private boolean hasStraightFlush() {
		for(int i = 0; i < handsList.size(); i++) {
			if(handsList.contains(handsList.get(i)+10) && handsList.contains(handsList.get(i)+20) && handsList.contains(handsList.get(i)+30)  &&
					handsList.contains(handsList.get(i)+40)) {
				return true;
			}
		}
		return false;
	}
	
	private boolean hasStraightFlushAceBefore() {
		for(int i = 0; i < handsList.size(); i++) {
			if(handsListAceBefore.contains(handsListAceBefore.get(i)+10) && handsListAceBefore.contains(handsListAceBefore.get(i)+20) && handsListAceBefore.contains(handsListAceBefore.get(i)+30)  &&
					handsListAceBefore.contains(handsListAceBefore.get(i)+40)) {
				return true;
			}
		}
		return false;
	}
	
	private boolean hasFourOfKind() {
		if(valuesHashMap.containsValue(4) || valuesHashMap.containsValue(5) || valuesHashMap.containsValue(6) || valuesHashMap.containsValue(7)) {
			return true;
		} else {
			return false;
		}
	}
	
	private boolean hasFullHouse() {
		if(valuesHashMap.containsValue(3) && valuesHashMap.containsValue(2)) {
			return true;
		} else {
			return false;
		}
	}
	
	private boolean hasFlush() {
		if(suitsHashMap.containsValue(5) || suitsHashMap.containsValue(6) || suitsHashMap.containsValue(7)) {
			return true;
		} else {
			return false;
		}
	}
	
	private boolean hasStraight() {
		for(int i = 0; i < handsListNoSuits.size(); i++) {
			if(handsListNoSuits.contains(handsListNoSuits.get(i)+1) && handsListNoSuits.contains(handsListNoSuits.get(i)+2) && handsListNoSuits.contains(handsListNoSuits.get(i)+3)  &&
					handsListNoSuits.contains(handsListNoSuits.get(i)+4)) {
				return true;
			}
		}
		return false;
	}
	
	private boolean hasStraightAceBefore() {
		for(int i = 0; i < handsListNoSuitsAceBefore.size(); i++) {
			if(handsListNoSuitsAceBefore.contains(handsListNoSuitsAceBefore.get(i)+1) && handsListNoSuitsAceBefore.contains(handsListNoSuitsAceBefore.get(i)+2) && handsListNoSuitsAceBefore.contains(handsListNoSuitsAceBefore.get(i)+3)  &&
					handsListNoSuitsAceBefore.contains(handsListNoSuitsAceBefore.get(i)+4)) {
				return true;
			}
		}
		return false;
	}
	
	private boolean hasThreeOfKind() {
		if(valuesHashMap.containsValue(3)) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean hasTwoPairs() {
		boolean firstPair = false;
		boolean secondPair = false;
		ArrayList<Integer> tempArrayList = new ArrayList<>(handsListNoSuits);
		outerloop:
		for(int i = 0; i < tempArrayList.size(); i++) {
			for (int j = 0; j < tempArrayList.size(); j++) {
				if(i == j) {
					continue;
				}
				if(tempArrayList.get(i) == tempArrayList.get(j)) {
					firstPair = true;
					int firstRemoved = tempArrayList.get(i);
					int secondRemoved = tempArrayList.get(j);
					tempArrayList.remove(tempArrayList.indexOf(firstRemoved));
					tempArrayList.remove(tempArrayList.indexOf(secondRemoved));
					break outerloop;
				}
			}
		}
		outerloop:
			for(int i = 0; i < tempArrayList.size(); i++) {
				for (int j = 0; j < tempArrayList.size(); j++) {
					if(i == j) {
						continue;
					}
					if(tempArrayList.get(i) == tempArrayList.get(j)) {
						secondPair = true;
						break outerloop;
					}
				}
			}
		if(firstPair && secondPair) {
			return true;
		} else {
			return false;
		}
	}
	
	private boolean hasPair() {
		if(valuesHashMap.containsValue(2)) {
			return true;
		} else {
			return false;
		}
	}
	
	public String getSubstring() {
		return initial.substring(10);
	}
	
	public int getScore() {
		return score;	
	}
	
	public void addNextValue() {
		if(!handsList.isEmpty()) {
			score += handsList.get(handsList.size()-1);
			handsList.remove(handsList.size()-1);
		}
	}
	
	public boolean isEmpty() {
		if(handsList.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}
	
	//Representing each card in the hand as value
		private  int twoCharsToNum(String twoChars) {
			int number = 0;
			
			//Checking first character in the card
			if(twoChars.charAt(0) == '2') {
				number = 20;
			} else if(twoChars.charAt(0) == '3') {
				number = 30;
			} else if(twoChars.charAt(0) == '4') {
				number = 40;
			} else if(twoChars.charAt(0) == '5') {
				number = 50;
			} else if(twoChars.charAt(0) == '6') {
				number = 60;
			} else if(twoChars.charAt(0) == '7') {
				number = 70;
			} else if(twoChars.charAt(0) == '8') {
				number = 80;
			} else if(twoChars.charAt(0) == '9') {
				number = 90;
			} else if(twoChars.charAt(0) == 'T') {
				number = 100;
			} else if(twoChars.charAt(0) == 'J') {
				number = 110;
			} else if(twoChars.charAt(0) == 'Q') {
				number = 120;
			} else if(twoChars.charAt(0) == 'K') {
				number = 130;
			} else {
				number = 140;
			}
			
			//Checking second character in the card
			if(twoChars.charAt(1) == 'c') {
				number += 1;
			} else if(twoChars.charAt(1) == 'd') {
				number += 2;
			} else if(twoChars.charAt(1) == 'h') {
				number += 3;
			} else if(twoChars.charAt(1) == 's') {
				number += 4;
			}
			
			return number;
		}
}
