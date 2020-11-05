package test;

public class CH13_13 {

	
	public static void main(String[] args) {
		Course a = new Course("Mathematical Analysis");
		a.addStudent("chenxling");
		a.addStudent("caocan");
		a.addStudent("jinxue");
		Course b  = a.clone();	
		System.out.println(a.getStudents());
		System.out.println(b.getStudents());
	}

}


class Course implements Cloneable {
	private String courseName;
	private String[] students = new String[100];
	private int numberOfStudents =0;
	
	public Course (String courseName) {
		this.courseName = courseName;
	}
	
	public void addStudent ( String student ) {
		students[numberOfStudents] = student;
		numberOfStudents++;	
	}
	
	public String[] getStudents() {
		return students;
	}
	
	public Course clone() {
		Course ss = new Course(this.courseName);	
		for(int i = 0; i <100; i++) {
			ss.addStudent( this.students[i] );
		}
		return ss;
		
	}
	
}
