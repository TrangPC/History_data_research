package main.app.controller;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import main.app.AppWindow;
import main.app.WindowController;
import main.app.screenID;
import main.subject.Subject;
import main.subject.dynasty.Dynasty;
import main.subject.event.Event;
import main.subject.festival.Festival;
import main.subject.person.King;
import main.subject.person.People;
import main.subject.place.Capital;
import main.subject.place.Place;

public class MainScreenController extends WindowController implements Initializable {

    @FXML
    private TextField searchBar;

    @FXML
    private TreeView<Subject> treeView;

    @FXML
    private Pane detailPane;
    
    @FXML
    private VBox infoVBox;

    @FXML
    private Pane leftPane;
    
    @FXML
    private void BackPressed(ActionEvent event) throws IOException {
    	switchToScene(event, screenID.START);
    }
    
    @FXML
    void search(KeyEvent event) {
    	if(event.getCode().equals(KeyCode.ENTER)) {
    		try {
    			FXMLLoader loader = new FXMLLoader();
    			loader.setLocation(AppWindow.class.getResource("asset/SearchResultList.fxml"));
				Pane searchPane = loader.load();
				ResultSearchListController searchController = loader.getController();
				searchController.getCloseButton().setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						leftPane.getChildren().remove(1);
					}
				});
				
				ListView<Subject> selectField = searchController.getSelectField();
				
				selectField.setOnMouseClicked(new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						Subject item = selectField.getSelectionModel().getSelectedItem();
				    	if(item!=null) {
				    		if(item instanceof Dynasty || 
				    			item instanceof Event ||
				    			item instanceof Festival || 
				    			item instanceof People||
				    			item instanceof Place)
				    		setPane(item);
				    	}
					}
				});
				
				String searchKey = searchBar.getText();
				
				for(int i=0; i<treeView.getRoot().getChildren().size(); i++) {
					TreeItem<Subject> branch = treeView.getRoot().getChildren().get(i);
					for(int j=0; j<branch.getChildren().size(); j++) {
						TreeItem<Subject> leaf = branch.getChildren().get(j);
						if(leaf.getValue().getName().indexOf(searchKey)!=-1) {
							selectField.getItems().add(leaf.getValue());
						}
					}
				}
				
	        	leftPane.getChildren().add(searchPane);
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}    	
    }
    
    private void setPane(Subject target) {
    	infoVBox.getChildren().clear();
    	try {
    		List<String> keyInfo = target.getAllKeyNames();
    		List<String> valueInfo = target.getAllValues();
    		for(int i = 0; i<keyInfo.size(); i++) {
    			FXMLLoader loader = new FXMLLoader();
        		loader.setLocation(AppWindow.class.getResource("asset/Info.fxml"));
    			HBox hBox = loader.load();
        		InfoController infoController = loader.getController();
        		infoController.setData(keyInfo.get(i), valueInfo.get(i));
        		infoVBox.getChildren().add(hBox);
    		}
    		
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
    
    @FXML
    void selectItem(MouseEvent event) {
    	TreeItem<Subject> item = treeView.getSelectionModel().getSelectedItem();
    	if(item!=null) {
    		Subject itemSelected = item.getValue();
    		if(itemSelected instanceof Dynasty || 
    				itemSelected instanceof Event ||
    				itemSelected instanceof Festival || 
    				itemSelected instanceof People||
    				itemSelected instanceof Place)
    		setPane(item.getValue());
    	}
    }
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		/*TreeItem<Subject> rootItem = new TreeItem<>();
		TreeItem<Subject> branchDynasty = new TreeItem<>(new Subject("Dynasty"));
		TreeItem<Subject> branchEvent = new TreeItem<>(new Subject("Event"));
		TreeItem<Subject> branchPerson = new TreeItem<>(new Subject("Person"));
		TreeItem<Subject> branchPlace = new TreeItem<>(new Subject("Place"));
		TreeItem<Subject> branchFestival = new TreeItem<>(new Subject("Festival"));

		
		branchDynasty.getChildren().add(new TreeItem<Subject>(new Dynasty("Ly", 1000, 2000, null)));
		branchPerson.getChildren().add(new TreeItem<Subject>(new People("Nguyen Van A", 1864, 1900)));
		branchDynasty.getChildren().add(new TreeItem<Subject>(new Dynasty("Tran", 1000, 1211, null)));
		branchPerson.getChildren().add(new TreeItem<Subject>(new People("Nguyen Van B", 1840, 1900)));
		branchPerson.getChildren().add(new TreeItem<Subject>(new 
				King("King1", 1840, 1900, "1860", "1870", new Capital("Ha Noi") , "A", "B")));
		branchPlace.getChildren().add(new TreeItem<Subject>(new Place("Bac Ninh")));
		branchPlace.getChildren().add(new TreeItem<Subject>(new Place("Ha Noi")));
		branchEvent.getChildren().add(new TreeItem<Subject>(new Event("Example Event", new Place("Da Nang"), "Example", 1868, 1867)));
		branchFestival.getChildren().add(new TreeItem<>(new Festival("Le hoi chua bai dinh", "Example Content")));
		rootItem.getChildren().addAll(branchDynasty, branchEvent, branchPerson, branchPlace, branchFestival);
		
		
		treeView.setShowRoot(false);
		treeView.setRoot(rootItem);*/
	}

}