package healthApp.controller;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;

public class TestController {

    @FXML
    private GridPane gridpane;

    @FXML
    private Button eventBtn;
    @FXML
    private ListView<Button> listview;
 
    
    @FXML
    void addStuff(ActionEvent event) {
   
    	for(int i=0;i<3;i++) {
    		Button newButton = new Button("Button ABCs");
    		newButton.minWidth(300);
    		listview.getChildrenUnmodifiable().add(newButton);
    		newButton.setOnAction(e->{
    			System.out.println("You click on a button " );
    		});
    	}
    }
    
    

}
