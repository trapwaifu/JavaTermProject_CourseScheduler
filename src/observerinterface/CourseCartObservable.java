package observerinterface;

import course.Course;

public interface CourseCartObservable {
	public void addObserver(CourseCartObserver obs);
	public void notifyObserversAdd(Course course);
	public void notifyObserversRemove(Course course);
	public void notifyObserversReset();
}
