package course;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CourseData {
	private static CourseData instance; 
	private ArrayList<Course> data = null;
	private ArrayList<Course> workingData = null;
	private CourseData() {
		try {
			readData();
		}
		catch(Exception e) {
			System.out.println("cannot read data");
			e.printStackTrace();
		}
	}
	public int size() {
		return workingData.size();
	}
	public static CourseData getInstance() {
		if(instance == null)
			instance = new CourseData();
		return instance;
	}
	public ArrayList<Course> getAllData() {
		return (ArrayList<Course>) data.clone();
	}
	public ArrayList<Course> getData() {
		return workingData;
	}
	public Course getCourse(int i) {
		return workingData.get(i);
	}
	public void reset() {
		workingData = (ArrayList<Course>) data.clone();
	}
	public void update(ArrayList<Course> trimed) {
		workingData = trimed;
	}
	private void readData() throws Exception {
		data = new ArrayList<Course>();
		File file = new File("data/data.txt");
		Scanner sc = new Scanner(file);
		while(sc.hasNextLine()) {
			String line = sc.nextLine();
			
			Pattern p = Pattern.compile("\"([^\"]*)\""); 	// https://stackoverflow.com/questions/1473155/how-to-get-data-between-quotes-in-java
			Matcher m = p.matcher(line);
			String[] split = new String[10];
			for(int i = 0; i < 10; ++i) {
				m.find();
				split[i] = m.group(1);
			}
			line = line.substring(1, line.length() - 2); // get rid of outer square brackets( "[]" )

			int[] time; // store time as an array
			if(line.indexOf("[") + 1 < line.indexOf("]") - 1) {
				String time_extract = line.substring(line.indexOf("[") + 1, line.indexOf("]") - 1);
				time = Arrays.asList(time_extract.split(",")).stream()
						.map(String::trim)
						.mapToInt(Integer::parseInt).toArray();
			}
			else {
				time = new int[0];
			}

			// Change all parameters to string
			Course c = 	new Course(split[0], split[1], split[2], split[3], split[4], Integer.parseInt(split[5]),
					Integer.parseInt(split[6]), split[7], split[8], split[9], time);
			data.add(c);
		}
		sc.close();
		workingData = (ArrayList<Course>) data.clone();
	}
}
