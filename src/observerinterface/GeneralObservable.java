package observerinterface;

public interface GeneralObservable {
	abstract public void notifyObservers();
	abstract public void addObserver(GeneralObserver o);
}
