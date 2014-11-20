package QMath;

import java.util.HashMap;
import java.util.Random;

public class LoadBalanceServers {
	public static void main(String[] args) {
		String[] servers = {"S1", "S2", "S3", "S4", "S5"};
		int[] probs = {5,15,20,30,40};
		int count = 100;
		handleRequests(count, servers, probs);
	}
	
	public static void handleRequests(int count, String[] servers, int[] probs) {
		HashMap<String, Integer> log = new HashMap<String, Integer>();
		String handler;
		for(int i = 0; i < count; ++i) {
			handler = selectServer(servers, probs);
			if(log.containsKey(handler)) {
				log.put(handler, log.get(handler)+1);
			} else {
				log.put(handler, 1);
			}
			System.out.println("assigning request " + i + ": " + handler);
		}
		
		System.out.println();
		for(String s : log.keySet()) {
			System.out.println(s + ": " + log.get(s));
		}
	}
	
	public static String selectServer(String[] servers, int[] probs) {
		int sumProbs = sum(probs);  //sum of all probabilities (shared denominator)
		Random randgen = new Random();
		int rand = randgen.nextInt(sumProbs);  //generate rand int [0,sumProbs-1]
		
		int len = probs.length, start = 0, end = 0;
		for(int i = 0; i < len; ++i) {  //just need to check each server's prob to define bounds
			start = end;  //update start range for current server (previous server's end)
			end = start + probs[i];  //update end range for current server 
			if(rand >= start && rand < end) {  //check if rand is within [start, end) for each server
				return servers[i];
			}
		}
		
		return "Cannot assign request to a server!!!";
	}
	
	public static int sum(int[] arr) {
		if(arr == null || arr.length == 0)
			return 0;
		
		int sum = 0;
		for(int i : arr) {
			sum += i;
		}
		return sum;
	}
}
