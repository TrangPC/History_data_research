package main.app.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import main.app.WindowController;
import main.app.screenID;

public class StartScreenController extends WindowController{
	@FXML
    private Button crawlBtn;

    @FXML
    private Button exitBtn;

    @FXML
    private Button startBtn;
    
    private Stage stage;
    @FXML
    void crawlBtnPressed(ActionEvent event) {

    }

    @FXML
    void exitBtnPressed(ActionEvent event) {
    	stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		stage.close();
    }

    @FXML
    void startBtnPressed(ActionEvent event) throws IOException {
    	switchToScene(event, screenID.MENU);
    }

}
