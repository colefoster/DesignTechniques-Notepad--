package notepad_app;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class OpenCommand implements Command{

	Stage stage;
	CustomTextArea customTextArea;
	
	public OpenCommand(Stage s, CustomTextArea c) {
		stage = s;
		customTextArea = c;
	}
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().addAll(
			     new FileChooser.ExtensionFilter("Text Files", "*.txt")
			);
		File selectedFile = fileChooser.showOpenDialog(stage);
		
		Scanner myReader;
		if(selectedFile != null) {
			customTextArea.textArea.setText("");	//clears current text
			try {
				myReader = new Scanner(selectedFile);
				String buffer = new String();	
				while(myReader.hasNextLine()) {		//read from selected file
					buffer += myReader.nextLine() + "\n";	//add each line to buffer
				}
				customTextArea.textArea.setText(buffer);//set textArea text to buffer
				myReader.close();
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}		
	}
}
