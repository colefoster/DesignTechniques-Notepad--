package notepad_app;

import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class LetterCounter implements Observer{
	
	//Concrete Observer
	Label label = new Label("0");
	TextArea textArea;
	
	public LetterCounter(TextArea t) {
		textArea = t;
	}
	@Override
	public void update() {
		// TODO Auto-generated method stub
		System.out.println(Integer.toString(textArea.getText().length()));
		
		label.setText(Integer.toString(textArea.getText().length() + 1));
		
	}
	
	

}