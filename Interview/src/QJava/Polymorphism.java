package QJava;

public class Polymorphism {
	public static void main(String[] args) {
		//virtual method invocation 
		//JVM will call the method defined by the RHS constructor and not the LHS variable type
		
		Animal ani = new Animal("animal", 1);  //call animal
		ani.eat();
		
		Animal cat = new Cat("cat", 2);  //call cat
		cat.eat();
		cat.sound();  
		
		Cat cat2 = new Cat("cat2", 4);
		cat2.eat();
		cat2.sound();
		
		Animal dog = new Dog("dog", 3);  //cat dog
		dog.eat();
		dog.sound();
		
		Dog dog2 = new Dog("dog2", 5);
		dog2.eat();
		dog2.sound(); 
		
		Bird bird = new Bird("bird", 6);
		bird.eat();
		
		//use abstract super class as variable type
		//use subclass for constructor
		Snake snake = new Viper("viper", 7);  
		snake.move();
		snake.eat();
		
		//use interface as variable type
		//use subclass for constructor 
		//any methods not declared in the interface cannot be used (including methods defined in subclass)
		//any class that implements the same interface can use their own constructor with interface as the variable type
		//they must share the same set of methods defined in the interface and anything not shared cannot be used
		Predator viper = new Viper("viper2", 8);
		viper.hunt();
		
		//any methods in subclass can be used since the variable type if the subclass itself
		Viper viper2 = new Viper("viper3", 9);
		viper2.sleep();
		
		//bat cannot use fly() because its type is an interface which does not have the fly()
		Predator bat = new Bat("bat", 10);
		bat.hunt();
		
		//bat2 can use fly() because its type is Bat()
		Bat bat2 = new Bat("bat2", 11);
		bat2.fly();
		
		//dog2 is a subclass Dog of the super class Animal 
		//Dog is-a Animal
		//everything an Animal has exist inside Dog due to inheritance so upward casting is fine
		//every unique method in Dog that is not in Animal will not be casted because the Animal doesn't have them
		//so bark() is not available after the cast
		Animal dog3 = (Animal) dog2;
		dog3.eat();

		//ani is the super class Animal 
		//it cannot be cast to a subclass Dog
		//Animal is-not-a Dog
		//everything a Dog has is may not be inside Animal so downward casting don't work (runtime exception)
		Dog ani2 = (Dog) ani;
		ani2.eat();
	}
	
	static class Animal {
		String name;
		int age;
		
		public Animal() {
			this("", 0);
		}
		
		public Animal(String name, int age) {
			this.name = name;
			this.age = age;
		}
		
		//inherited by all subclasses 
		public void sound() {
			System.out.println(name + " is making a sound");
		}
		
		//inherited by all subclasses
		public void eat() {
			System.out.println("animal eating");
		}
	}
	
	static class Cat extends Animal{
		public Cat() {
			super("", 0);
		}
		
		public Cat(String name, int age) {
			super(name, age);
		}
		
		public void meow() {
			System.out.println("meow");
		}
		
		//override eat() in parent Animal
		public void eat() {
			System.out.println("cat eating");
		}
	}
	
	static class Dog extends Animal {
		public Dog() {
			super("", 0);
		}
		
		public Dog(String name, int age) {
			super(name, age);
		}
		
		public void bark() {
			System.out.println("bark");
		}
		
		//override eat() in parent Animal
		public void eat() {
			System.out.println("dog eating");
		}
	}
	
	static class Bird extends Animal {
		public Bird() {
			super("", 0);
		}
		
		public Bird(String name, int age) {
			super(name, age);
		}
		
		public void chip() {
			System.out.println("chip");
		}
		
		//override eat() in parent Animal
		public void eat() {
			super.eat();  //call eat() from its parent before doing anything else
			System.out.println("bird eating");
		}
	}
	
	//abstract class cannot be instantiated, but it can be extended as a super class
	//abstract class does not have to define any of the interface method bodies
	//it can leave the definitions to the direct subclass that extends it
	//implementing multiple interfaces is like having multiple inheritance
	static abstract class Snake extends Animal implements Attack, Defend {  
		public Snake() {  //abstract class can have constructors used by its subclass only (cannot do new() on abstract class directly)
			super("", 0);
		}
		
		public Snake(String name, int age) {
			super(name, age);
		}
		
		abstract public void move();  //abstract method don't require a body, just define body in direct subclass
		
		abstract public void sleep();
		
		public void eat() {  //override or called by subclass only
			System.out.println(name + " eating");
		}
		
		public void generalAttack() {  //only define 1 method from interface here, leave the rest for subclass
			System.out.println(name + " general attack");
		}
		
		public void generalDefend() {  //only define 1 method from interface here, leave the rest for subclass
			System.out.println(name + " general defend");
		}
	}
	
	//subclass that extends an abstract super class must define the body for all its abstract methods
	//otherwise it also becomes an abstract class 
	//since its abstract super class did not define all the methods from the interface
	//this subclass need to define those that are missing
	static class Viper extends Snake implements Predator {  		
		public Viper() {
			super();
		}
		
		public Viper(String name, int age) {  //call abstract super class's constructor
			super(name, age);
		}
		
		@Override
		public void move() {
			System.out.println("viper moving");
		}

		@Override
		public void sleep() {
			System.out.println("viper sleeping");
		}
		
		@Override
		public void attackFront() {  //define body for method in interface
			System.out.println("viper attack front");
		}

		@Override
		public void attackBack() {  //define body for method in interface
			System.out.println("viper attack back");
		}
		
		@Override
		public void defendFront() {  //define body for method in interface
			System.out.println("viper attack front");
		}
		
		@Override
		public void defendBack() {  //define body for method in interface
			System.out.println("viper attack back");
		}

		@Override
		public void hunt() {
			System.out.println("viper hunting");
		}
	}
	
	static class RareViper extends Viper {
		public RareViper() {
			super();
		}
		
		public RareViper(String name, int age) {
			super(name, age);
		}
		
		@Override
		public void defendFront() {  //override method from super class
			System.out.println("rare viper attack front");
		}
		
		@Override
		public void defendBack() {  //override method from super class
			System.out.println("rare viper attack back");
		}
	}
	
	static class Bat extends Animal implements Predator {
		public Bat() {
			super();
		}
		
		public Bat(String name, int age) {
			super(name, age);
		}
		
		//this method is not available if the instantiation of Bat() uses Predator as the variable type
		//because fly() is not declared in the Predator interface
		public void fly() {
			System.out.println("bat flying");
		}

		@Override
		public void hunt() {
			System.out.println("bat hunting");
		}
		
		//subclass cannot override a final method
		final public void sense() {
			System.out.println("bat sensing");
		}
	}
	
	static interface Attack {
		public void generalAttack();
		public void attackFront();
		public void attackBack();
	}
	
	static interface Defend {
		public void generalDefend();
		public void defendFront();
		public void defendBack();
	}
	
	static interface Predator {
		public void hunt();
	}
}
