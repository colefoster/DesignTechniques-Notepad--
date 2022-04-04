package notepad_app;

import javafx.scene.control.TextArea;

public class ZoomInCommand implements Command {

	TextArea textArea;
	
	public ZoomInCommand(TextArea t) {
		textArea = t;
	}
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		textArea.setStyle("-fx-font-size : 32;");
	}

}
