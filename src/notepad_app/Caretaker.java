package notepad_app;

import java.util.Deque;
import java.util.LinkedList;

public class Caretaker {
	Deque<Memento> mementos;

	public Caretaker(){
		mementos = new LinkedList<Memento>();
	}

	public void addMemento(Memento memento) {

		mementos.addFirst(memento);

	}

	public Memento getFirst() {
		return mementos.getFirst();
	}
	
	public Memento getLast() {
		return mementos.getLast();
	}
	
}


