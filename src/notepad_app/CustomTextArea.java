package notepad_app;

import javafx.scene.control.TextArea;

public class CustomTextArea {

	TextArea textArea = new TextArea("");
	
	Observer wordCounter;
	
	void subscribe(Observer o) {
		wordCounter = o;
	}
	
	void unsubscribe() {
		wordCounter = null;
	}
	
	void update() {
		
		int words = 0;
		if(textArea.getText() == null || textArea.getText().isEmpty()) {
			words = 0;
		}
		else {
			String[] text = textArea.getText().split("\\s+");
			words = text.length;
		}
		wordCounter.update(words);
	}
	
	public Memento takeSnapshot() {
		return new Memento(this.textArea.getText());
	}
	
	public void restore(Memento memento) {
		this.textArea.setText(memento.getData()); 
	}
	
}


