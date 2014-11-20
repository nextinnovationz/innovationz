package QDesign;

public class ModelViewController {
	public static void main(String[] args) {
		StudentModel sm = new StudentModel("Jay", 23, 711);
		StudentView sv = new StudentView(sm);
		StudentController sc = new StudentController(sm, sv);
		sc.displayView();
	}
	
	static class StudentModel {
		public String name;
		public int age;
		public long id;
		
		public StudentModel(String name, int age, int id) {
			this.name = name;
			this.age = age;
			this.id = id;
		}
	}
	
	static class StudentView {
		public StudentModel model;
		
		public StudentView(StudentModel model) {
			this.model = model;
		}
		
		public void printStudentInfo() {
			System.out.println("student name: " + model.name);
			System.out.println("student age: " + model.age);
			System.out.println("student id: " + model.id);
		}
	}
	
	static class StudentController {
		public StudentModel model;
		public StudentView view;
		
		public StudentController(StudentModel model, StudentView view) {
			this.model = model;
			this.view = view;
		}
		
		public String getName() {
			return model.name;
		}
		
		public void setName(String name) {
			model.name = name;
		}
		
		public int getAge() {
			return model.age;
		}
		
		public void setAge(int age) {
			model.age = age;
		}
		
		public long getId() {
			return model.id;
		}
		
		public void setId(long id) {
			model.id = id;
		}
		
		public void updateView(StudentModel model) {
			view.model = model;
			view.printStudentInfo();
		}
		
		public void displayView() {
			view.printStudentInfo();
		}
	}
}
