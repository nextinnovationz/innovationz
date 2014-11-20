package QThread;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DiningPhilosophers {
	public static void main(String[] args) {
		Dining d = new Dining(5);
		d.dine();
	}
	
	static class Dining {
		public int philCnt;
		public ArrayList<Philosopher> phils;
		public ArrayList<Fork> forks;
		public static int[] stats;
		
		public Dining(int philCnt) {
			this.philCnt = philCnt;
			if(stats == null)
				Dining.stats = new int[philCnt];
			setupForks();
			setupPhilosophers();
		}
		
		public void setupForks() {
			this.forks = new ArrayList<Fork>();
			for(int i = 0; i < philCnt; ++i) {
				forks.add(new Fork(i));
			}
		}
		
		public void setupPhilosophers() {
			//create new philosopher threads
			this.phils = new ArrayList<Philosopher>();
			
			//create philosophers 
			for(int i = 0; i < philCnt; ++i) {
				phils.add(new Philosopher(i, null, null));
			}
			
			//assign each philosopher a left and right fork 
			//not dining yet, just assignment
			int curr = 0, prev = philCnt-1;;
			Philosopher p = null;
			while(curr < philCnt) {
				p = phils.get(curr);
				p.left = forks.get(prev);
				p.right = forks.get(curr);
				prev = curr;
				++curr;
			}
		}
		
		public void dine() {
			//start each philosopher thread, call each run() inside it
			//infinite loop for infinite eating
			//a thread once started cannot be restart after it ends, need to create need thread
			int i = 0, r = 0, rounds = 100;
			while(r < rounds) {
				phils.get(i).start();
				++i;
				if(i == philCnt) {  //all current threads ran, need to create new threads
					//first use join() so each thread dies 
					for(Philosopher p : phils) {
						try {
							p.join();  //wait for this philosopher thread to die here, before moving on
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						//at this point, p is dead from join()
						System.out.println("\t" + p.key + " - " + p.getState() + ", " + p.isAlive());
					}
					System.out.println();
					
					i = 0;
					++r;
					
					//create new philosopher threads for next round
					setupPhilosophers();
				}
			}
			
			printStats();
		}
		
		//multiple philosophers can modify the stats array whenever they eat
		//need a monitor on stats so that only one thread can modifiy it at a time
		public static synchronized void updateStats(int key) {
			++Dining.stats[key];
		}
		
		public void printStats() {
			for(int i = 0; i < philCnt; ++i) {
				System.out.println("P" + i + ": " + stats[i]);
			}
		}
	}
	
	static class Fork {
		public Lock lock;
		public int key;
		
		public Fork(int key) {
			this.key = key;
			this.lock = new ReentrantLock();
		}
		
		public boolean pickUp() {
			return lock.tryLock();
		}
		
		public void putDown() {
			lock.unlock();
		}
	}
	
	static class Philosopher extends Thread {
		public Fork left, right;
		public int key;
		
		public Philosopher(int key, Fork left, Fork right) {
			this.key = key;
			this.left = left;
			this.right = right;
		}
		
		@Override
		public void run() {
			//try to eat once during the execution of this thread
			//this run() will be called automatically when this thread calls start()
			if(pickUp()) {
				eat();
				putDown();
			}
		}
		
		public boolean pickUp() {
			if(!left.pickUp()) {
				return false;
			} else {
				if(!right.pickUp()) {
					left.putDown();
					return false;
				}
			}
			return true;
		}
		
		public void putDown() {
			left.putDown();
			right.putDown();
		}
		
		public void eat() {
			try {
				Dining.updateStats(key);
				System.out.println("P" + key + " is eating with (F" + left.key + ",F" + right.key + ")");
				sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
