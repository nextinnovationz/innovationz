package QJava;

public class Static {
	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		Count c1 = new Count();
		c1.count = 13;  //since its static, should be Count.count instead of using instantiation of Count
		c1.unique = 2;
		Count c2 = new Count();
		c2.count = 15;  //since its static, should be Count.count instead of using instantiation of Count
		c2.unique = 3;
		Count.count = 100;  //static access of static variable (without instantiation)
		System.out.println(c1.count);
		System.out.println(c1.unique);
		System.out.println(c2.count);
		System.out.println(c2.unique);
	}
	
	static class Count {
		public static int count;  //all instantiations of this class share this same variable
		public int unique;  //each instantiation of this class will have a different variable
		
		@SuppressWarnings("static-access")
		public Count() {
			this.count = 0;
			this.unique = 0;
		}
	}
}
