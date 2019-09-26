import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
		stage.setTitle("Projeto N1");
		stage.setScene(new Scene(root, 1024, 720));
		stage.initStyle(StageStyle.UNDECORATED);
		stage.show();
	}
}
