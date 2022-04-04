package notepad_app;

import javafx.scene.control.TextArea;

public class ZoomOutCommand implements Command {

	TextArea textArea;
	
	public ZoomOutCommand(TextArea t) {
		textArea = t;
	}
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		textArea.setStyle("-fx-font-size : 10;");
	}

}
