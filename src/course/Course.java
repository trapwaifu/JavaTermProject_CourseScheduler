package course;

import java.util.Arrays;

public class Course {
	public final String code;
	public final String courseName;
	public final String division;
	public final String target;
	public final String misc;
	public final int credit;
	public final int designCredit;
	public final String department;
	public final String professor;
	public final String max_students;
	public final int[] time;
	public Course(String code, String courseName, String division, String target, String misc, int credit,
			int designCredit, String department, String professor, String max_students, int[] time) {
		super();
		this.code = code;
		this.courseName = courseName;
		this.division = division;
		this.target = target;
		this.misc = misc;
		this.credit = credit;
		this.designCredit = designCredit;
		this.department = department;
		this.professor = professor;
		this.max_students = max_students;
		this.time = time;
	}
	@Override
	public String toString() {
		return "Course [code=" + code + ", courseName=" + courseName + ", division=" + division + ", target=" + target
				+ ", misc=" + misc + ", credit=" + credit + ", designCredit=" + designCredit + ", department="
				+ department + ", professor=" + professor + ", max_students=" + max_students + ", time="
				+ Arrays.toString(time) + "]";
	}
	
}
