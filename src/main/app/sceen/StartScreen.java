package main.app.sceen;

import java.io.IOException;

import javax.swing.JFrame;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import main.app.controller.StartScreenController;

public class StartScreen extends JFrame {
	public StartScreen() {
		super();
		JFXPanel fxPanel = new JFXPanel();
		this.add(fxPanel);

		this.setTitle("Lich su Viet Nam");
		this.setVisible(true);
		this.setResizable(false);
		this.setSize(1000, 600);

		// this.setLocationRelativeTo(null);
		Platform.runLater(new Runnable() {
			public void run() {
				try {
					FXMLLoader loader = new FXMLLoader(getClass().getResource("Startscreen.fxml"));
					StartScreenController controller = new StartScreenController();
					loader.setController(controller);
					Parent root = loader.load();
					fxPanel.setScene(new Scene(root));

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});

	}
}