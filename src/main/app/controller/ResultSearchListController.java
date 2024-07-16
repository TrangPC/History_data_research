package main.app.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import main.subject.Subject;

public class ResultSearchListController {

    @FXML
    private Button closeBtn;

    @FXML
    private ListView<Subject> searchListField;

    @FXML
    void selectItem(MouseEvent event) {
    	
    }
    @FXML
    void onCloseBtnClicked(MouseEvent event) {

    }
    Button getCloseButton() {
    	return closeBtn;
    }
    ListView<Subject> getSelectField(){
    	return searchListField;
    }
    

}
