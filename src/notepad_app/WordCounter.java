package notepad_app;

import javafx.scene.control.Label;

public class WordCounter implements Observer{

	int count = 0;
	Label label = new Label("");
	
	@Override
	public void update(int wordCount) {
		// TODO Auto-generated method stub
		label.setText(Integer.toString(wordCount));
	}
	
	

}
