package project2;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class SignInController {
	@FXML
	Button signIn;
	@FXML
	Button cancel;

	StartController start;

	@FXML
	public void initialize(){}

	@FXML
	public void signIn(){

	}
	@FXML
	public void openTimeline(){
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(GuiMain.class.getResource("Timeline.fxml"));
			Pane root = (Pane) loader.load();

			TimelineController timeline = (TimelineController)loader.getController();
			timeline.getStart(this);

			Stage secondStage = new Stage();
			Scene scene = new Scene(root);
			secondStage.setScene(scene);
			secondStage.show();
		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}

	@FXML
	void close() {
		cancel.getScene().getWindow().hide();
	}
}
