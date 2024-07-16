package main.app;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.app.controller.*;

public class WindowController {
	private Stage stage;
	private Scene scene;
	private Parent root;
	private FXMLLoader loader;
	private AppWindow temp = new AppWindow();
	public void switchToScene(ActionEvent event, screenID id) throws IOException  {
		switch(id) {
			case START:
				loader = new FXMLLoader(temp.getClass().getResource("asset/StartScreen.fxml"));
				loader.setController(new StartScreenController());
				root = loader.load();
				break;
			case MENU:
				loader = new FXMLLoader(temp.getClass().getResource("asset/MainScreen.fxml"));
				loader.setController(new MainScreenController());
				root = loader.load();
				break;
		}
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
}