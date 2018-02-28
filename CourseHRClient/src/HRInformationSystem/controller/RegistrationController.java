package HRInformationSystem.controller;

import HRInformationSystem.model.*;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RegistrationController {
    private String SecretCode = "IMSUPERMAN";
    @FXML
    TextField EditNickname;
    @FXML
    Button ButtonRegistration;
    @FXML
    PasswordField EditPassword;
    @FXML
    PasswordField EditConfirmPassword;
    @FXML
    PasswordField EditSecretCode;
    @FXML
    TextField EditName;
    @FXML
    TextField EditSurname;
    @FXML
    Label LabelError;
    @FXML
    Label LabelName;
    @FXML
    Label LabelSurname;
    @FXML
    Label LabelCode;
    @FXML
    CheckBox CheckManager;
    @FXML TextField EditAge;
    @FXML Label LabelAge;
    @FXML Label LabelSkills;
    @FXML TextField EditEducation;
    @FXML Label LabelEducation;
    @FXML ChoiceBox ChoiceSkills;
    @FXML TextField EditExperience;
    @FXML Label LabelExperience;
    @FXML ComboBox ComboSkills;
    //@FXML CheckComboBox combp;
    List<Skills> skillsList;
    public ClientConnection connection;
    @FXML
    private void initialize() {
        EditSecretCode.setVisible(false);
        LabelError.setVisible(false);
        LabelCode.setVisible(false);
        skillsList = new ArrayList<>(connection.getSkills());
        for (Skills skill : skillsList) {
            ComboSkills.getItems().add(skill.name);
           // System.out.println(skill.name);
        }
        //ChoiceSkills.setItems(FXCollections.observableList(skillsList));
        //ChoiceSkills.setItems();

    }

    @FXML
    public void onEndRegistration() throws IOException {

        if (EditPassword.getText().equals(""))
            return;
        if (EditNickname.getText().equals(""))
            return;
        if (EditName.getText().equals(""))
            return;
        if (EditSurname.getText().equals(""))
            return;
        String result = "";
        System.out.println(EditPassword.getText());
        ClientConnection coonnection = new ClientConnection();
        if (CheckManager.isSelected()) {
            if (EditSecretCode.getText().equals(SecretCode)) {
                User user = new User(EditNickname.getText(), EditPassword.getText());
                Manager manager = new Manager(EditName.getText(), EditSurname.getText());
                user.role = 1;

                result = coonnection.regUserAdm(user, manager);
                System.out.println(result);
            }else{
                new AlertController("Secret Code ERROR!",
                        "Secret Code Wrong!",
                        "Make sure you type right secret code!",
                        Alert.AlertType.ERROR);
            }
        } else {
            if (EditAge.getText().equals(""))
                return;
            if (EditEducation.getText().equals(""))
                return;
            if (EditExperience.getText().equals(""))
                return;
            User user = new User(EditNickname.getText(), EditPassword.getText());
            Client client = new Client(EditName.getText(), EditSurname.getText(), Integer.parseInt(EditAge.getText()), ComboSkills.getSelectionModel().getSelectedIndex() + 1, EditEducation.getText(), Integer.parseInt(EditExperience.getText()));
            user.role = 0;
            result = coonnection.regUserClient(user, client);
            System.out.println(result);
        }
        if (result.equals("SUCCESS")){
            new AlertController("Registration!",
                    "Registration finished!",
                    "I congratulated you!",
                    Alert.AlertType.CONFIRMATION);
            try {
                Stage newView= new Stage();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/HRInformationSystem/view/AuthorizationView.fxml"));
                newView.setTitle("Authorization!");
                AnchorPane layout = loader.load();
                newView.setScene(new Scene(layout));
                newView.show();
                Stage stage = (Stage) ButtonRegistration.getScene().getWindow();
                stage.close();
            }catch(IOException e){
                e.printStackTrace();

            }



        }else{
            new AlertController("Registration!",
                    "Registration not finished!",
                    "Something went wrong!",
                    Alert.AlertType.ERROR);
            }

    }

    @FXML
    public void onClickedManager() {

        if (CheckManager.isSelected()) {
            Stage stage = (Stage) CheckManager.getScene().getWindow();
            stage.setWidth(448);
            stage.setHeight(255);
            LabelCode.setVisible(true);
            EditSecretCode.setVisible(true);
            EditAge.setVisible(false);
            LabelAge.setVisible(false);
            LabelSkills.setVisible(false);
            EditEducation.setVisible(false);
            LabelEducation.setVisible(false);
            ComboSkills.setVisible(false);
            EditExperience.setVisible(false);
            LabelExperience.setVisible(false);
        }else{
            Stage stage = (Stage) CheckManager.getScene().getWindow();
            stage.setWidth(448);
            stage.setHeight(400);
            LabelCode.setVisible(false);
            EditSecretCode.setVisible(false);
            EditAge.setVisible(true);
            LabelAge.setVisible(true);
            LabelSkills.setVisible(true);
            EditEducation.setVisible(true);
            LabelEducation.setVisible(true);
            ComboSkills.setVisible(true);
            EditExperience.setVisible(true);
            LabelExperience.setVisible(true);
        }

    }
    @FXML public void onFillConfirm(){

    }
    @FXML public void onTypeConfirm(){
        if (EditPassword.getText().equals(EditConfirmPassword.getText())){
            //System.out.println(EditPassword.getText()+'+'+EditConfirmPassword.getText());
            LabelError.setVisible(false);
            ButtonRegistration.setDisable(false);

        } else {
            //System.out.println(EditPassword.getText()+'-'+EditConfirmPassword.getText());
            LabelError.setVisible(true);
            ButtonRegistration.setDisable(true);

        }
    }
    @FXML
    void onBackClicked() {
        try {
            Stage newView= new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/HRInformationSystem/view/AuthorizationView.fxml"));
            newView.setTitle("Authorization!");
            AnchorPane layout = loader.load();
            newView.setScene(new Scene(layout));
            newView.show();
            Stage stage = (Stage) EditSecretCode.getScene().getWindow();
            stage.close();
        }catch(IOException e){
            e.printStackTrace();

        }
    }

}


