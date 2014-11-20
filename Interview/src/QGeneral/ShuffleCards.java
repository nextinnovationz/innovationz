package QGeneral;

import java.util.Arrays;

public class ShuffleCards {
	public static void main(String[] args) {
		int[] deck = createDeck();
		shuffle(deck);
		System.out.println(Arrays.toString(deck));
	}
	
	public static int[] createDeck() {
		int[] deck = new int[52];
		for(int i = 0; i < 52; ++i) 
			deck[i] = i;
		return deck;
	}
	
	public static int[] shuffle(int[] deck) {
		int len = deck.length;
		for(int i = 0; i < len; ++i) {
			//swap card i with a random card from i to n-1
			//assuming rand generator produces perfectly random number (equal prob of selecting i to n-1)
			//mod (len-i) brings rand val into a smaller range of len-i new length (let i be index 0)
			//+i makes index i the starting index for selecting a random card [i,n)
			//cards before index i will not be considered for random selection to disable selecting same card multiple times
			swap(deck, i, (int)((Math.random()*100)%(len-i))+i);
		}
		return deck;
	}
	
	public static void swap(int[] arr, int a, int b) {
		int temp = arr[a];
		arr[a] = arr[b];
		arr[b] = temp;
	}
}
