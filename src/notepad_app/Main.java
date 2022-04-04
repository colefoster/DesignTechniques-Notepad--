package notepad_app;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javafx.application.Application;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;


public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		try {	
			primaryStage.getIcons().add(new Image(new FileInputStream("images/notes.png")));
			
			
			CustomTextArea customTextArea = new CustomTextArea();
			customTextArea.textArea.setMinSize(400, 400);
			customTextArea.textArea.setOnKeyPressed(e->{
				customTextArea.update();
			});
			
			Caretaker caretaker = new Caretaker();
			WordCounter wordCounter = new WordCounter();
			customTextArea.subscribe(wordCounter);
			
			ZoomInCommand zoomInCommand = new ZoomInCommand(customTextArea.textArea);
			ZoomOutCommand zoomOutCommand = new ZoomOutCommand(customTextArea.textArea);
			
			BorderPane borderPaneLayout = new BorderPane();
			FileChooser fileChooser = new FileChooser();
			
			
			//ToolBar
			ToolBar toolbar = new ToolBar();
			Button snapshotButton = new Button("Take Snapshot");
			SnapshotCommand snapshotCommand = new SnapshotCommand(customTextArea, caretaker);
			snapshotButton.setOnAction(e -> snapshotCommand.execute());
			Button restoreNewestButton = new Button("Restore Most Recent");
			RestoreNewestCommand restoreNewestCommand = new RestoreNewestCommand(customTextArea, caretaker);
			restoreNewestButton.setOnAction(e -> restoreNewestCommand.execute());
			Button restoreOldestButton = new Button("Restore Oldest");
			RestoreOldestCommand restoreOldestCommand = new RestoreOldestCommand(customTextArea, caretaker);
			restoreOldestButton.setOnAction(e-> restoreOldestCommand.execute());
			
			toolbar.getItems().add(snapshotButton);
			toolbar.getItems().add(restoreNewestButton);
			toolbar.getItems().add(restoreOldestButton);
			
			
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
			input = new FileInputStream("images/folder.png");
	        image = new Image(input);
	        imageView = new ImageView(image);
			menuItemOpen.setGraphic(imageView);
			menuItemOpen.setOnAction(e -> {
				fileChooser.getExtensionFilters().addAll(
					     new FileChooser.ExtensionFilter("Text Files", "*.txt")
					);
				File selectedFile = fileChooser.showOpenDialog(primaryStage);
				//prompt to save current doc here
				Scanner myReader;
				if(selectedFile != null) {
					customTextArea.textArea.setText("");	//clears current text
					try {
						myReader = new Scanner(selectedFile);
						String buffer = new String();
						while(myReader.hasNextLine()) {
							buffer += myReader.nextLine() + "\n";
						}
						customTextArea.textArea.setText(buffer);
						myReader.close();
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
				
			});
			menuFile.getItems().add(menuItemOpen);
				
			
			MenuItem menuItemSave = new MenuItem("Save");
			input = new FileInputStream("images/download.png");
	        image = new Image(input);
	        imageView = new ImageView(image);
			menuItemSave.setGraphic(imageView);
			menuItemSave.setOnAction(e -> {
				fileChooser.setInitialFileName("myfile.txt");
				
				File selectedFile = fileChooser.showSaveDialog(primaryStage);
				if (selectedFile != null) {
					try {
						FileWriter myWriter = new FileWriter(selectedFile);
						myWriter.write(customTextArea.textArea.getText());
						myWriter.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
				
				
			});
			menuFile.getItems().add(menuItemSave);
			
			SeparatorMenuItem separator = new SeparatorMenuItem();
			menuFile.getItems().add(separator);
			
			MenuItem menuItemExit = new MenuItem("Exit");
			input = new FileInputStream("images/power-button.png");
	        image = new Image(input);
	        imageView = new ImageView(image);
			menuItemExit.setGraphic(imageView);
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
			input = new FileInputStream("images/return.png");
	        image = new Image(input);
	        imageView = new ImageView(image);
			menuItemUndo.setGraphic(imageView);
			menuEdit.getItems().add(menuItemUndo);
			
			MenuItem menuItemRedo = new MenuItem("Redo");
			input = new FileInputStream("images/forward.png");
	        image = new Image(input);
	        imageView = new ImageView(image);
			menuItemRedo.setGraphic(imageView);			
			menuEdit.getItems().add(menuItemRedo);
			
			menuBar.getMenus().add(menuEdit);
			
			
			
			Menu menuView = new Menu("View");
			FileInputStream streamEye = new FileInputStream("images/eye.png");
	        Image imageEye = new Image(streamEye);
	        ImageView imageViewEye = new ImageView(imageEye);
			menuView.setGraphic(imageViewEye);
			menuView.setStyle("-fx-font-size : 18;");

			MenuItem menuItemZoomIn = new MenuItem("Zoom In");
			input = new FileInputStream("images/zoom-in.png");
	        image = new Image(input);
	        imageView = new ImageView(image);
			menuItemZoomIn.setGraphic(imageView);
			menuItemZoomIn.setOnAction(e->zoomInCommand.execute());
			
			
			MenuItem menuItemZoomOut = new MenuItem("Zoom Out");
			input = new FileInputStream("images/zoom-out.png");
	        image = new Image(input);
	        imageView = new ImageView(image);
			menuItemZoomOut.setGraphic(imageView);
			menuItemZoomOut.setOnAction(e->zoomOutCommand.execute());
			
			menuView.getItems().add(menuItemZoomIn);
			menuView.getItems().add(menuItemZoomOut);
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
			primaryStage.setTitle("Notepad--");
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
