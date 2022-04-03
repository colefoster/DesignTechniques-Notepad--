package notepad_app;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javafx.application.Application;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


public class Main extends Application {
	public void undo() {
		
	}
	public void redo() {
		
	}
	public void save() {
		
	}
	
	@Override
	public void start(Stage primaryStage) {
		try {	
			primaryStage.getIcons().add(new Image(new FileInputStream("images/notes.png")));
			
			BorderPane borderPaneLayout = new BorderPane();
			FileChooser fileChooser = new FileChooser();
			CustomTextArea customTextArea = new CustomTextArea();
			customTextArea.textArea.setMinSize(400, 400);
			customTextArea.textArea.setOnKeyPressed(e->{
				customTextArea.update();
			});
			WordCounter wordCounter = new WordCounter();
			customTextArea.subscribe(wordCounter);
			
			//ToolBar
			ToolBar toolbar = new ToolBar();
			Button openButton = new Button("Open...");
			Button undoButton = new Button("Undo");
			Button redoButton = new Button("Redo");

			toolbar.getItems().add(openButton);
			toolbar.getItems().add(undoButton);
			toolbar.getItems().add(redoButton);
			
			
			//MenuBar	    
			MenuBar menuBar = new MenuBar();
			Menu menuFile = new Menu("File");
			FileInputStream input = new FileInputStream("images/file.png");
	        Image image = new Image(input);
	        ImageView imageView = new ImageView(image);
			menuFile.setGraphic(imageView);
			menuFile.setStyle("-fx-font-size : 18;");
			menuBar.getMenus().add(menuFile);
			
			MenuItem menuItemOpen = new MenuItem("Open...");
			menuItemOpen.setOnAction(e -> {
				fileChooser.getExtensionFilters().addAll(
					     new FileChooser.ExtensionFilter("Text Files", "*.txt")
					);
				File selectedFile = fileChooser.showOpenDialog(primaryStage);
				//prompt to save current doc here
				Scanner myReader;
				try {
					myReader = new Scanner(selectedFile);
					while(myReader.hasNextLine()) {
						customTextArea.textArea.setText(customTextArea.textArea.getText() + myReader.nextLine());
					}
					myReader.close();
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}catch(NullPointerException e2) {
					e2.printStackTrace();
				}
			});
			menuFile.getItems().add(menuItemOpen);
			
			MenuItem menuItemNew = new MenuItem("New");
			menuFile.getItems().add(menuItemNew);
			
			MenuItem menuItemSave = new MenuItem("Save");
			menuItemSave.setOnAction(e -> {
				fileChooser.setInitialFileName("myfile.txt");
				
				File selectedFile = fileChooser.showSaveDialog(primaryStage);
				
				
			});
			menuFile.getItems().add(menuItemSave);
			
			SeparatorMenuItem separator = new SeparatorMenuItem();
			menuFile.getItems().add(separator);
			
			MenuItem menuItemExit = new MenuItem("Exit");
			menuItemExit.setOnAction(e -> {
				primaryStage.close();
				});
			menuFile.getItems().add(menuItemExit);

			
			
			
			Menu menuEdit = new Menu("Edit");
			FileInputStream input2 = new FileInputStream("images/edit.png");
	        Image image2 = new Image(input2);
	        ImageView imageView2 = new ImageView(image2);
			menuEdit.setGraphic(imageView2);
			menuEdit.setStyle("-fx-font-size : 18;");
			
			MenuItem menuItemUndo = new MenuItem("Undo");
			menuItemUndo.setOnAction(e -> undo());
			menuEdit.getItems().add(menuItemUndo);
			
			MenuItem menuItemRedo = new MenuItem("Redo");
			menuItemRedo.setOnAction(e -> redo());
			menuEdit.getItems().add(menuItemRedo);
			
			menuBar.getMenus().add(menuEdit);
			
			
			
			Menu menuView = new Menu("View");
			FileInputStream streamEye = new FileInputStream("images/eye.png");
	        Image imageEye = new Image(streamEye);
	        ImageView imageViewEye = new ImageView(imageEye);
			menuView.setGraphic(imageViewEye);
			menuView.setStyle("-fx-font-size : 18;");

			menuBar.getMenus().add(menuView);
			
			VBox vbox1 = new VBox(menuBar,toolbar);
			ToolBar toolbar2 = new ToolBar();
			ToolBar toolbar3 = new ToolBar();
			
			Label wordCountDescription = new Label("Word Count: ");
			HBox hBox = new HBox(wordCountDescription ,wordCounter.label);
			
			borderPaneLayout.setTop(vbox1);
		    borderPaneLayout.setCenter(customTextArea.textArea);
		    borderPaneLayout.setBottom(hBox);
		    borderPaneLayout.setLeft(toolbar2);
		    borderPaneLayout.setRight(toolbar3);
			borderPaneLayout.setPrefSize(400,400);
			Scene scene = new Scene(borderPaneLayout, 600, 600);
			
			primaryStage.setScene(scene);
			primaryStage.setTitle("Notepad__");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//Package Explorer -> main(String[]) -> run configurations -> arguments -> VM arguments
	//add: --module-path "B:\JavaFX\javafx_sdk-18\lib" --add-modules javafx.controls,javafx.fxml
	public static void main(String[] args) {
		launch(args);
	}
}
