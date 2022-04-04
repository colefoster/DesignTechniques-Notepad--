package notepad_app;

import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class WordCounter implements Observer{
	
	//Concrete Observer
	Label label = new Label("0");
	
	private TextArea textArea;
	
	public WordCounter(TextArea t) {
		textArea = t;
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		int words = 0;
		if(textArea.getText() == null || textArea.getText().isEmpty()) {
			words = 0;
		}
		else {
			String[] text = textArea.getText().split("\\s+");
			words = text.length;
		}
		label.setText(Integer.toString(words));
	}
	
	

}
