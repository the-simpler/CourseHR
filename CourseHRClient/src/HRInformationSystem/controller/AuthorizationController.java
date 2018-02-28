package HRInformationSystem.controller;


import HRInformationSystem.model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class AuthorizationController {
    @FXML
    private TextField EditNickname;
    @FXML
    private PasswordField EditPassword;
    @FXML
    private Button ButtonLogin;
    private ClientConnection connection;
      @FXML
    public void onLoginClicked() throws ClassNotFoundException {

          try {
            connection = new ClientConnection();
            Stage newView;
            FXMLLoader loader;

            User user = connection.checkUser(EditNickname.getText(), EditPassword.getText());
            System.out.println(user.getLogin()+' '+user.getRole());
            if (user.getLogin() != null) {
                switch (user.getRole()) {
                    case 0:
                        loader = new FXMLLoader(getClass().getResource("/HRInformationSystem/view/ClientView.fxml"));
                        newView = new Stage();
                        newView.setTitle("Client window");
                        AnchorPane layout = loader.load();
                        ClientController controller = loader.getController();
                        newView.setScene(new Scene(layout));
                        controller.setCurrentUser(user);
                        controller.welcome();
                        controller.labelInit();
                        break;
                    case 1:
                        loader = new FXMLLoader(getClass().getResource("/HRInformationSystem/view/ManagerView.fxml"));
                        newView = new Stage();
                        newView.setTitle("Manager window");
                        AnchorPane layout1 = loader.load();
                        ManagerController controller1 = loader.getController();
                        newView.setScene(new Scene(layout1));
                        controller1.setCurrentUser(user);
                        controller1.setCurrentManager();
                        controller1.welcome();

                        break;
                    case 2:
                        loader = new FXMLLoader(getClass().getResource("/HRInformationSystem/view/AdminPanelView.fxml"));
                        newView = new Stage();
                        newView.setTitle("Admin window");
                        AnchorPane layout3 = loader.load();
                        newView.setScene(new Scene(layout3));
                        break;
                    default:
                        loader = new FXMLLoader(getClass().getResource("/HRInformationSystem/view/ClientPanelView.fxml"));
                        newView = new Stage();
                        newView.setTitle("Client window");
                        AnchorPane layout2 = loader.load();
                        newView.setScene(new Scene(layout2));
                        break;
                }


                newView.setResizable(false);
                newView.show();
                Stage stage = (Stage) ButtonLogin.getScene().getWindow();
                stage.close();
            }else{
               new AlertController("Authorization ERROR!",
                        "Authorization Error!",
                        "Make sure you input right login and password!",
                        Alert.AlertType.ERROR);
            }

        }catch(IOException e){
            System.out.println(e);


        }

    }
    @FXML
    public void onRegistrationClicked(){
        try {
        Stage newView= new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/HRInformationSystem/view/RegistrationView.fxml"));
        newView.setTitle("Registration");
        AnchorPane layout = loader.load();
        newView.setScene(new Scene(layout));
        newView.setResizable(false);
        newView.show();
        Stage stage = (Stage) ButtonLogin.getScene().getWindow();
        stage.close();
        }catch(IOException e){
            e.printStackTrace();

        }
    }
    @FXML
    private void initialize() {
        try {
            ClientConnection.connection();
        }catch(IOException e){
            new AlertController().AlertSocket();
        }
    }





}
