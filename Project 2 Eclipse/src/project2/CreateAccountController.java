package project2;

import project2.StartController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class CreateAccountController {
	@FXML
	TextField username;
	@FXML
	PasswordField password;
	@FXML
	PasswordField confirmPass;
	@FXML
	TextField name;
	@FXML
	DatePicker birthday;
	@FXML
	TextField phone;
	@FXML
	TextField email;
	@FXML
	Button createAccount;
	@FXML
	Button cancel;
	@FXML
	Label prompt;

	StartController start;
	Users users;

	@FXML
	public void initialize(){}

	public void getStart(StartController start){
		this.start = start;
		this.users = start.getUsers();
	}

	@FXML
	public void createAccount(){
		String currentUsername = username.getText();
		if (users.checkUserName(currentUsername)) {
			prompt.setText("This username has already been chosen. Please choose another one.");
		} else {
			users.add(username.getText(), password.getText(), name.getText(), birthday.getValue(),
						phone.getText(), email.getText());
			openTimeline();
		}
	}
	

	@FXML
	public void openTimeline(){
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(GuiMain.class.getResource("Timeline.fxml"));
			Pane root = (Pane) loader.load();

			//SignInController  = (AddActivityController)loader.getController();
			//addActivity.getMain(this);

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