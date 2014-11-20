package QThread;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConsumerProducer {
	public static void main(String[] args) {
		int totalProduce = 1000;
		Store<Integer> store = new Store<Integer>(5, totalProduce);
		Consumer c = new Consumer(store);
		Producer p = new Producer(store);
		c.start();
		p.start();
		
		try {
			c.join();
			p.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			System.out.println();
			System.out.println("producer count: " + store.producerCnt);
			System.out.println("consumer count: " + store.consumerCnt);
		}
	}
	
	static class Store<T> {
		public List<T> store;
		public int capacity;
		public int producerCnt, consumerCnt, totalProduce;
		public Lock lock;  //lock for the entire Store object 
		public Condition notFull, notEmpty;
		
		public Store(int capacity, int totalProduce) {
			this.store = new ArrayList<T>(capacity);
			this.capacity = capacity;
			this.producerCnt = 0;
			this.consumerCnt = 0;
			this.totalProduce = totalProduce;
			this.lock = new ReentrantLock();
			this.notFull = lock.newCondition();
			this.notEmpty = lock.newCondition();
		}
		
		public void add(T item) {
			lock.lock();
			try {
				//store is full, can't add so stop the producer thread
				//must have consumer thread remove some before add
				while(size() == capacity) {
					notFull.await();  //store is full so stop producer thread for now
				}
				
				//have free space in store now
				//add the new item to store
				store.add(item);
				//signal the consumer thread that there is something to remove
				notEmpty.signal();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				++producerCnt;
				lock.unlock();
			}
		}
		
		public T remove() {
			lock.lock();
			try {
				//the store is empty, can't remove so stop the consumer thread 
				//must have producer thread add some before remove
				while(isEmpty()) {
					notEmpty.await();  //store is empty so stop consumer thread for now
				}
				
				//signal the producer thread that there is space to add
				notFull.signal();
				//remove the head of the store
				return store.remove(0);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				++consumerCnt;
				lock.unlock();
			}
			return null;
		}
		
		public boolean isEmpty() {
			return store.isEmpty();
		}
		
		public int size() {
			return store.size();
		}
	}
	
	static class Consumer extends Thread {
		public Store<Integer> store;
		
		public Consumer(Store<Integer> store) {
			this.store = store;
		}
		
		public void consume() {
			if(!store.isEmpty()) {
				//consumer get the store lock here when calling store's remove
				System.out.println("consume: " + store.remove());
			}
		}
		
		@Override
		public void run() {
			while(store.consumerCnt < store.totalProduce) {
				consume();
			}
		}
	}
	
	static class Producer extends Thread {
		public Store<Integer> store;
		public Random rand;

		public Producer(Store<Integer> store) {
			this.store = store;
			this.rand = new Random();
		}
		
		public void produce() {
			int item = 0;
			if(store.size() < store.capacity) {
				item = rand.nextInt(store.capacity);
				System.out.println("produce: " + item);
				
				//producer get the store lock here when calling store's add
				store.add(item);
			}
		}
		
		@Override
		public void run() {
			//this producer thread will only run until for a finite time
			//when all produce are created, it will die
			while(store.producerCnt < store.totalProduce) {
				produce();
			}
		}
	}
}
