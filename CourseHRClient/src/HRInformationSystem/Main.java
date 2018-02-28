package HRInformationSystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.io.IOException;

public class Main extends Application {
    private Stage primaryStage;


    @Override

    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Authorization");
        ShowAuthorization();

    }
    public void ShowAuthorization() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("view/AuthorizationView.fxml"));
           // VBox layout = loader.load();
            //authorizationcontroller controller = loader.getController();
           // AuthorizationViewController controller = loader.getController();
            //controller.setMainApp(this);
            // Отображаем сцену, содержащую корневой макет.
            primaryStage.setResizable(false);
            primaryStage.setScene(new Scene(loader.load()));
            //controller.setFocus();
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}