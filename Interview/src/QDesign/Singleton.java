package QDesign;

public class Singleton {
	
	static class SingletonNotThreadSafe {
		//initialize the instance only one (before its even needed)
		//could be wasteful of instantiation if its never used
		private final static Object obj = new Object();
		
		//outside cannot access the constructor to instantiate a new instance of this class
		private SingletonNotThreadSafe() {
			
		}
		
		//outside must use this method to get the single instance of this class
		//its not thread safe so multiple threads can call this method at the same time to create multiple instances
		public Object getInstance() {
			return obj;
		}
	}
	
	static class SingletonThreadSafe {
		//don't initialize the instance yet in case its never needed
		private final static Object obj = null;
		
		//outside cannot access the constructor to instantiate a new instance of this class
		private SingletonThreadSafe() {
			
		}
		
		//outside must use this method to get the single instance of this class
		//initialize it if its not already done (deferred initialization/ lazy initialization)
		//its not thread safe so multiple threads can call this method at the same time to create multiple instances
		public Object getInstance() {
			if(obj == null)
				return new Object();
			else
				return obj;
		}
	}
	
	static class SingletonThreadSafeSlow {
		//don't initialize the instance yet in case its never needed
		private final static Object obj = null;
		
		//outside cannot access the constructor to instantiate a new instance of this class
		private SingletonThreadSafeSlow() {
			
		}
		
		//outside must use this method to get the single instance of this class
		//initialize it if its not already done (deferred initialization/ lazy initialization)
		//also synchronized it so its thread safe and only one thread will be able to initialize it
		public synchronized Object getInstance() {
			if(obj == null)
				return new Object();
			else
				return obj;
		}
	}
	
	static class SingletonThreadSafeFast {
		//don't initialize the instance yet in case its never needed
		private final static Object obj = null;
		
		//outside cannot access the constructor to instantiate a new instance of this class
		private SingletonThreadSafeFast() {
			
		}
		
		//outside must use this method to get the single instance of this class
		//instead of using synchronized on the method which has expensive overhead (especially for frequent calls)
		//wrap the object in a static class so that its guaranteed to be protected by the classloader
		public Object getInstance() {
			if(obj == null)
				return ProtectedObject.obj;
			else
				return obj;
		}
		
		static class ProtectedObject {
			public static final Object obj = new Object();
		}
	}
}
