package healthApp.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.io.File;
import java.io.FileWriter;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class ChangeImageController implements Initializable{
	@FXML
	private GridPane logosGridPane;
	@FXML
	private AnchorPane anchorPane;
	@FXML
	private ImageView imagePane;

	
	// Event Listener on Button.onAction
	@FXML
	public void confirmButton(ActionEvent event) {
		//change the imageURL in temp.txt
		try {
			Path p = FileSystems.getDefault().getPath("data","temp.txt");
			File f = new File(p.toUri());
			Scanner sc = new Scanner(f);
			String[] values = sc.nextLine().split("::");
			String str = String.format("%s::%s::%s::%s::%s::%s",values[0], values[1], values[2], values[3], imagePane.getImage().getUrl(),values[5]);
			
			//			String str = String.format("%s::%s::%s::%s::%s::%s",values[0], values[1], values[2], values[3], imagePane.getImage().impl_getUrl(),values[5]);

			FileWriter fw = new FileWriter(f,false);
			fw.write(str);
			fw.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		//go back to adding reminder.
		goToAddReminder();
	}
	
	public void goToAddReminder() {
		try {
			Parent newRoot = FXMLLoader.load(getClass().getResource("/healthApp/view/AddReminder.fxml"));
			anchorPane.getScene().setRoot(newRoot);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		//check for all logos inside resources to put the logos in the gridpane with event....
		File logos = new File("resources/logo");
		int rowPos= 0;
		
		//grid pane can only have 3 in a row, but scroll up to many columns
		for(File file : logos.listFiles()) {
			
			StackPane p = new StackPane();
			ImageView iv = new ImageView(file.toURI().toString());
			iv.setFitWidth(70);
			iv.setFitHeight(70);
			
			//Event listener (set the other thing to the image)
			p.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
				public void handle(MouseEvent e) {
					ImageView n = (ImageView)((Pane) e.getSource()).getChildren().get(0);
					imagePane.setImage(n.getImage());
				}
			});
			
			p.getChildren().add(iv);
			int columnPos = (int)Math.floor((double)rowPos/3);
			logosGridPane.add(p, rowPos % 3, columnPos); //3columns, so rowPos / 4 = columnPos
			p.setLayoutX(18); //each grid is 106 wide. (106-80) / 2 = 13
			rowPos++;
		}
	}
}
