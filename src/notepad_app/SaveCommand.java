package notepad_app;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class SaveCommand implements Command{

	Stage stage;
	CustomTextArea customTextArea;
	
	public SaveCommand(Stage s, CustomTextArea c) {
		stage = s;
		customTextArea = c;
	}
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		FileChooser fileChooser = new FileChooser();
		fileChooser.setInitialFileName("myfile.txt");
		
		File selectedFile = fileChooser.showSaveDialog(stage);
		if (selectedFile != null) {
			try {
				FileWriter myWriter = new FileWriter(selectedFile);
				myWriter.write(customTextArea.textArea.getText());//write textArea text to file
				myWriter.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}
