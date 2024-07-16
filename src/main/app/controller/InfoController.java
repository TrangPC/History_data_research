package main.app.controller;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class InfoController {

    @FXML
    private Text infoKey;

    @FXML
    private Text infoValue;
    
    public void setData(String key, String value) {
    	infoKey.setText(key);
    	infoValue.setText(value);
    }
}
