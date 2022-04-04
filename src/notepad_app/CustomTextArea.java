package notepad_app;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.TextArea;

public class CustomTextArea {

	TextArea textArea = new TextArea("");
	
	//Singleton Pattern Implementation
	private static CustomTextArea instance = null;	//private static instance
	private CustomTextArea() {//private constructor
		observerList = new ArrayList<Observer>();
	}
	public static CustomTextArea getInstance() {//static method to get instance
		if(instance == null) {
			instance = new CustomTextArea();
		}
		return instance;
	}
	
	
	//Observer Pattern Implementation
	private List<Observer> observerList;
	void subscribe(Observer o) {
		observerList.add(o);
	}
	void unsubscribe(Observer o) {
		observerList.remove(o);
	}
	void update() {
		
		for (Observer observer : observerList) {

			observer.update();
		}
	}
	
	
	//Memento Pattern Implementation
	public Memento takeSnapshot() {
		return new Memento(this.textArea.getText());
	}
	
	public void restore(Memento memento) {
		this.textArea.setText(memento.getData()); 
	}
	
}


