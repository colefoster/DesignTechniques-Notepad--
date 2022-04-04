package notepad_app;

import java.io.FileInputStream;
import javafx.application.Application;
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
			//Singleton pattern
			CustomTextArea customTextArea = CustomTextArea.getInstance();
			customTextArea.textArea.setMinSize(400, 400);
			customTextArea.textArea.setOnKeyPressed(e->customTextArea.update());
			
			//Observer pattern
			Caretaker caretaker = new Caretaker();
			WordCounter wordCounter = new WordCounter(customTextArea.textArea);
			customTextArea.subscribe(wordCounter);
			LetterCounter letterCounter = new LetterCounter(customTextArea.textArea);
			customTextArea.subscribe(letterCounter);
			
			
			//Button ToolBar
			ToolBar toolbar = new ToolBar();
			Button snapshotButton = new Button("Take Snapshot");
			SnapshotCommand snapshotCommand = new SnapshotCommand(customTextArea, caretaker);
			snapshotButton.setOnAction(e -> snapshotCommand.execute());
			toolbar.getItems().add(snapshotButton);
			
			Button restoreNewestButton = new Button("Restore Most Recent");
			RestoreNewestCommand restoreNewestCommand = new RestoreNewestCommand(customTextArea, caretaker);
			restoreNewestButton.setOnAction(e -> restoreNewestCommand.execute());
			toolbar.getItems().add(restoreNewestButton);
			
			Button restoreOldestButton = new Button("Restore Oldest");
			RestoreOldestCommand restoreOldestCommand = new RestoreOldestCommand(customTextArea, caretaker);
			restoreOldestButton.setOnAction(e-> restoreOldestCommand.execute());
			toolbar.getItems().add(restoreOldestButton);
			
	
			MenuBar menuBar = new MenuBar();				//MenuBar			
			Menu menuFile = new Menu("File");					//File
			FileInputStream input = new FileInputStream("images/file.png");
	        Image image = new Image(input);
	        ImageView imageView = new ImageView(image);
			menuFile.setGraphic(imageView);
			menuFile.setStyle("-fx-font-size : 18;");
			menuBar.getMenus().add(menuFile);
			
			MenuItem menuItemOpen = new MenuItem("Open...");		//File->Open...
			OpenCommand openCommand = new OpenCommand(primaryStage, CustomTextArea.getInstance());
			input = new FileInputStream("images/folder.png");
	        image = new Image(input);
	        imageView = new ImageView(image);
			menuItemOpen.setGraphic(imageView);
			menuItemOpen.setOnAction(e -> openCommand.execute());
			menuFile.getItems().add(menuItemOpen);
				
			
			MenuItem menuItemSave = new MenuItem("Save");			//File->Save
			SaveCommand saveCommand = new SaveCommand(primaryStage, CustomTextArea.getInstance());
			input = new FileInputStream("images/download.png");
	        image = new Image(input);
	        imageView = new ImageView(image);
			menuItemSave.setGraphic(imageView);
			menuItemSave.setOnAction(e -> saveCommand.execute());
			menuFile.getItems().add(menuItemSave);
			
			SeparatorMenuItem separator = new SeparatorMenuItem();
			menuFile.getItems().add(separator);
			
			MenuItem menuItemExit = new MenuItem("Exit");			//File->Exit
			input = new FileInputStream("images/power-button.png");
	        image = new Image(input);
	        imageView = new ImageView(image);
			menuItemExit.setGraphic(imageView);
			menuItemExit.setOnAction(e -> primaryStage.close());
			menuFile.getItems().add(menuItemExit);

			
			Menu menuEdit = new Menu("Edit");					//Edit
			FileInputStream input2 = new FileInputStream("images/edit.png");
	        Image image2 = new Image(input2);
	        ImageView imageView2 = new ImageView(image2);
			menuEdit.setGraphic(imageView2);
			menuEdit.setStyle("-fx-font-size : 18;");
			menuBar.getMenus().add(menuEdit);
			
			MenuItem menuItemUndoLast = new MenuItem("Undo Last");	//Edit->Undo Last
			menuItemUndoLast.setOnAction(e->restoreNewestCommand.execute());
			input = new FileInputStream("images/return.png");
	        image = new Image(input);
	        imageView = new ImageView(image);
			menuItemUndoLast.setGraphic(imageView);
			menuEdit.getItems().add(menuItemUndoLast);
			
			MenuItem menuItemUndoAll = new MenuItem("Undo All");	//Edit->Undo All
			menuItemUndoAll.setOnAction(e->restoreOldestCommand.execute());
			input = new FileInputStream("images/forward.png");
	        image = new Image(input);
	        imageView = new ImageView(image);
			menuItemUndoAll.setGraphic(imageView);			
			menuEdit.getItems().add(menuItemUndoAll);
			
			
			
			Menu menuView = new Menu("View");					//View
			FileInputStream streamEye = new FileInputStream("images/eye.png");
	        Image imageEye = new Image(streamEye);
	        ImageView imageViewEye = new ImageView(imageEye);
			menuView.setGraphic(imageViewEye);
			menuView.setStyle("-fx-font-size : 18;");
			menuBar.getMenus().add(menuView);

			MenuItem menuItemZoomIn = new MenuItem("Zoom In");		//View->Zoom In
			ZoomInCommand zoomInCommand = new ZoomInCommand(customTextArea.textArea);
			input = new FileInputStream("images/zoom-in.png");
	        image = new Image(input);
	        imageView = new ImageView(image);
			menuItemZoomIn.setGraphic(imageView);
			menuItemZoomIn.setOnAction(e->zoomInCommand.execute());
			menuView.getItems().add(menuItemZoomIn);
			
			MenuItem menuItemZoomOut = new MenuItem("Zoom Out");	//View->Zoom Out
			ZoomOutCommand zoomOutCommand = new ZoomOutCommand(customTextArea.textArea);
			input = new FileInputStream("images/zoom-out.png");
	        image = new Image(input);
	        imageView = new ImageView(image);
			menuItemZoomOut.setGraphic(imageView);
			menuItemZoomOut.setOnAction(e->zoomOutCommand.execute());
			menuView.getItems().add(menuItemZoomOut);


			//Word and character counters labels
			Label wordCountDescription = new Label("\t\tWord Count: ");
			HBox wordCountLabels = new HBox(wordCountDescription ,wordCounter.label);
			
			Label letterCountDescription = new Label("\t\tCharacter Count: ");
			HBox letterCountLabels = new HBox(letterCountDescription ,letterCounter.label);
			VBox allCountLabels = new VBox(wordCountLabels,letterCountLabels);
			
			VBox menuBarAndToolbar = new VBox(menuBar,toolbar);
			
			//Add all nodes to screen layout
			BorderPane borderPaneLayout = new BorderPane();
			borderPaneLayout.setTop(menuBarAndToolbar);
		    borderPaneLayout.setCenter(customTextArea.textArea);
		    borderPaneLayout.setBottom(allCountLabels);
		    borderPaneLayout.setLeft(new ToolBar());
		    borderPaneLayout.setRight(new ToolBar());
			borderPaneLayout.setPrefSize(400,400);
			//Add layout to scene, set size
			Scene scene = new Scene(borderPaneLayout, 600, 600);
			
			primaryStage.setScene(scene);
			
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//HOW TO CONFIGURE AND LAUNCH JAVAFX PROJECT IN ECLIPSE
	// 1. Install e(fx)clipse 3.7.0 plugin from eclipse marketplace, Restart
	// 2. Download JavaFX Windows SDK (https://gluonhq.com/products/javafx/), Extract, remember location
	// 3. Window->Preferences->Java->Build Path->User Libraries->New..., Name JavaFX
	// 4. Select above JavaFX user library, click Add External JARs..., Navigate to extracted SDK, Open all JARs, Apply and Close
	// 5. Right click JavaFX project->Build Path->Configure Build Path...->Libraries Tab->Select Classpath->Add Library...
	// -> UserLibrary -> Add JavaFX user library -> Finish -> Apply and Close
	// 6. Run -> Run Configurations -> select main function under Java header in left panel -> arguments -> VM arguments
	// -> add: --module-path "[YOUR PATH OF EXTRACTED SDKs]" --add-modules javafx.controls,javafx.fxml
	// 			eg. --module-path "C:\javafx\javafx-sdk-15.0.1\lib" --add-modules javafx.controls,javafx.fxml
	
	public static void main(String[] args) {
		launch(args);
	}
}
