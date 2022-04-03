module NotepadTest1 {
	requires javafx.controls;
	requires javafx.graphics;
	
	opens notepad_app to javafx.graphics, javafx.fxml;
}
