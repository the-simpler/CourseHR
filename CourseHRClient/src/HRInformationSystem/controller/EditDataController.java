package HRInformationSystem.controller;

import HRInformationSystem.model.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EditDataController {
    @FXML private TextField TextField4;
    @FXML private TextField TextField3;
    @FXML private TextField TextField2;
    @FXML private TextField TextField1;
    @FXML private TextField TextField6;
    @FXML private Label Label1;
    @FXML private TextField TextField8;
    @FXML private TextField TextField7;
    @FXML private Label Label3;
    @FXML private Label Label2;
    @FXML private TextField TextField5;
    @FXML private ComboBox<Object> ComboBox6;
    @FXML private Label Label8;
    @FXML private Label Label5;
    @FXML private Label Label4;
    @FXML private Label Label7;
    @FXML private Label Label6;
    @FXML private Button buttonSave;

    public ClientConnection connection;
    private String openTable;
    private String action;
    public static int role=3;
    @FXML
    void onClickedBack() throws IOException, ClassNotFoundException {  //возврат на предыдущую страницу
        Stage newView;
        FXMLLoader loader;
        if (role == 0){
            loader = new FXMLLoader(getClass().getResource("/HRInformationSystem/view/ClientView.fxml"));
            newView = new Stage();
            newView.setTitle("Client window");
            AnchorPane layout = loader.load();
            ClientController controller = loader.getController();
            controller.welcome();
            controller.labelInit();
            newView.setScene(new Scene(layout));
        }
        else  if (role == 1){
            loader = new FXMLLoader(getClass().getResource("/HRInformationSystem/view/ManagerView.fxml"));
            newView = new Stage();
            newView.setTitle("Manager window");
            AnchorPane layout = loader.load();
            ManagerController controller = loader.getController();
            controller.welcome();
            newView.setScene(new Scene(layout));
        }else {
            loader = new FXMLLoader(getClass().getResource("/HRInformationSystem/view/AdminPanelView.fxml"));
            newView = new Stage();
            newView.setTitle("Admin window");
            AnchorPane layout = loader.load();
            newView.setScene(new Scene(layout));

        }
        newView.setResizable(false);
        newView.show();
        Stage stage = (Stage) Label1.getScene().getWindow();
        stage.close();
    }

    @FXML
    void onClickedSave() {  //сохранение или измененеие, в зависимости от выбранной таблицы
        String result= "";
        switch (openTable){
            case "Client":
                Client client = new Client();
                client.name = TextField2.getText();
                client.surname = TextField3.getText();
                client.education =  TextField4.getText();
                client.user_id =Integer.parseInt(TextField5.getText());
                client.skills = ComboBox6.getSelectionModel().getSelectedIndex() + 1;
                client.experience = Integer.parseInt(TextField7.getText());
                client.age = Integer.parseInt(TextField8.getText());
                if (action.equals("Add")) {
                    result = connection.addDeleteEditTable(client, "AddClient");
                    System.out.println(result +" add "+openTable);
                }
                if (action.equals("Edit")) {
                    client.id = Integer.parseInt(TextField1.getText());
                    result = connection.addDeleteEditTable(client, "EditClient");
                    System.out.println(result +" edit "+openTable);
                }
                break;
            case "Manager":
                Manager manager = new Manager();
                manager.name = TextField2.getText();
                manager.surname = TextField3.getText();
                manager.user_id = Integer.parseInt(TextField4.getText());
                if (action.equals("Add")) {
                    result = connection.addDeleteEditTable(manager, "AddManager");
                    System.out.println(result +" add "+openTable);
                }
                if (action.equals("Edit")) {
                    manager.id = Integer.parseInt(TextField1.getText());
                    result = connection.addDeleteEditTable(manager, "EditManager");
                    System.out.println(result +" edit "+openTable);
                }
                break;
            case "Request":
                Request request = new Request();
                request.description = TextField2.getText();
                request.client = Integer.parseInt(TextField3.getText());
                request.manager =Integer.parseInt(TextField4.getText());
                request.position =Integer.parseInt(TextField5.getText());
                request.status = TextField6.getText();
                if (action.equals("Add")) {
                    result = connection.addDeleteEditTable(request, "AddRequest");
                    System.out.println(result +" add "+openTable);
                }
                if (action.equals("Edit")) {
                    request.id = Integer.parseInt(TextField1.getText());
                    result = connection.addDeleteEditTable(request, "EditRequest");
                    System.out.println(result +" edit "+openTable);
                }
                break;
            case "User":
                User user = new User(TextField2.getText(), TextField3.getText());
                user.setRole(Integer.parseInt(TextField4.getText()));
                if (action.equals("Add")) {
                    result = connection.addDeleteEditTable(user, "AddUser");
                    System.out.println(result +" add "+openTable);
                }
                if (action.equals("Edit")) {
                    user.setId(Integer.parseInt(TextField1.getText()));
                    result = connection.addDeleteEditTable(user,"EditUser");
                    System.out.println(result +" edit "+openTable);
                }
                break;
            case "Position":
                Position position = new Position();
                position.name = TextField2.getText();
                position.schedule = TextField3.getText();
                position.hours_per_week =Integer.parseInt(TextField4.getText());
                position.average_salary =Integer.parseInt(TextField5.getText());
                if (action.equals("Add")) {
                    result = connection.addDeleteEditTable(position, "AddPosition");
                    System.out.println(result +" add "+openTable);
                }
                if (action.equals("Edit")) {
                    position.id = Integer.parseInt(TextField1.getText());
                    result = connection.addDeleteEditTable(position, "EditPosition");
                    System.out.println(result +" edit "+openTable);
                }
                break;
            case "Skills":
                Skills skills = new Skills();
                skills.name = TextField2.getText();
                if (action.equals("Add")) {
                    result = connection.addDeleteEditTable(skills, "AddSkills");
                    System.out.println(result +" add "+openTable);
                }
                if (action.equals("Edit")) {
                    skills.id = Integer.parseInt(TextField1.getText());
                    result = connection.addDeleteEditTable(skills, "EditSkills");
                    System.out.println(result +" edit "+openTable);
                }
                break;
        }
    }
    @FXML
    void initialize() {  //включить видимость для всех элементов на форме
        TextField1.setDisable(true);
        TextField1.setVisible(true);
        TextField2.setVisible(true);
        TextField3.setVisible(true);
        TextField4.setVisible(true);
        TextField5.setVisible(true);
        ComboBox6.setVisible(true);
        TextField7.setVisible(true);
        TextField8.setVisible(true);
        Label1.setVisible(true);
        Label2.setVisible(true);
        Label3.setVisible(true);
        Label4.setVisible(true);
        Label5.setVisible(true);
        Label6.setVisible(true);
        Label7.setVisible(true);
        Label8.setVisible(true);
    }

    void setData(String table, Object model){  //отображение данных на форме в зависимости от того, какую таблицу редактировать
        action = "Edit";
        openTable=table;
        switch (table) {
            case "Client":
                List<Skills> skillsList;
                Client clnt = (Client) model;
                TextField1.setText(""+clnt.id);
                TextField2.setText(""+clnt.name);
                TextField3.setText(""+clnt.surname);
                TextField4.setText(""+clnt.education);
                TextField5.setText(""+clnt.user_id);
                skillsList = new ArrayList<>(connection.getSkills());
                for (Skills skill : skillsList) {
                    ComboBox6.getItems().add(skill.name);
                    // System.out.println(skill.name);
                }
                if (role == 0) {
                TextField5.setDisable(true);
                }
                ComboBox6.setValue(skillsList.get(clnt.skills).name);
                TextField7.setText(""+clnt.experience);
                TextField8.setText(""+clnt.age);
                Label1.setText("ID");
                Label2.setText("Name");
                Label3.setText("Surname");
                Label4.setText("Education");
                Label5.setText("User ID");
                Label6.setText("Skills");
                Label7.setText("Experience");
                Label8.setText("Age");
                TextField6.setVisible(false);
                break;
            case "Manager":
                Manager mngr = (Manager) model;
                TextField1.setText(""+mngr.id);
                TextField2.setText(""+mngr.name);
                TextField3.setText(""+mngr.surname);
                TextField4.setText(""+mngr.user_id);
                Label1.setText("ID");
                Label2.setText("Name");
                Label3.setText("Surname");
                Label4.setText("User ID");
                TextField6.setVisible(false);
                ComboBox6.setVisible(false);
                Label5.setVisible(false);
                Label6.setVisible(false);
                Label7.setVisible(false);
                Label8.setVisible(false);
                TextField5.setVisible(false);
                TextField7.setVisible(false);
                TextField8.setVisible(false);
                break;
            case "Request":
                Request rqst = (Request) model;
                TextField1.setText(""+rqst.id);
                TextField2.setText(""+rqst.description);
                TextField3.setText(""+rqst.client);
                TextField4.setText(""+rqst.manager);
                TextField5.setText(""+rqst.position);
                TextField6.setText(""+rqst.status);
                Label1.setText("ID");
                Label2.setText("Description");
                Label3.setText("Client");
                Label4.setText("Manager");
                Label5.setText("Position");
                Label6.setText("Status");
                ComboBox6.setVisible(false);
                TextField7.setVisible(false);
                TextField8.setVisible(false);

                Label7.setVisible(false);
                Label8.setVisible(false);
                break;
            case "User":
                User usr = (User) model;
                TextField1.setText(""+usr.id);
                TextField2.setText(""+usr.login);
                TextField3.setText(""+usr.password);
                TextField4.setText(""+usr.role);
                Label1.setText("ID");
                Label2.setText("Login");
                Label3.setText("Password");
                Label4.setText("Role");
                TextField6.setVisible(false);
                TextField5.setVisible(false);
                ComboBox6.setVisible(false);
                TextField7.setVisible(false);
                TextField8.setVisible(false);
                Label5.setVisible(false);
                Label6.setVisible(false);
                Label7.setVisible(false);
                Label8.setVisible(false);
            break;
            case "Position":
                Position pstn = (Position) model;
                TextField1.setText(""+pstn.id);
                TextField2.setText(""+pstn.name);
                TextField3.setText(""+pstn.schedule);
                TextField4.setText(""+pstn.hours_per_week);
                TextField5.setText(""+pstn.average_salary);
                Label1.setText("ID");
                Label2.setText("Name");
                Label3.setText("Schedule");
                Label4.setText("Hours per week");
                Label5.setText("Average salary");
                TextField6.setVisible(false);
                ComboBox6.setVisible(false);
                TextField7.setVisible(false);
                TextField8.setVisible(false);
                Label6.setVisible(false);
                Label7.setVisible(false);
                Label8.setVisible(false);
                break;
            case "Skills":
                Skills sklls = (Skills) model;
                TextField1.setText(""+sklls.id);
                TextField2.setText(""+sklls.name);
                Label1.setText("ID");
                Label2.setText("Name");
                TextField3.setVisible(false);
                TextField4.setVisible(false);
                TextField5.setVisible(false);
                ComboBox6.setVisible(false);
                TextField7.setVisible(false);
                TextField8.setVisible(false);
                TextField6.setVisible(false);
                Label3.setVisible(false);
                Label4.setVisible(false);
                Label5.setVisible(false);
                Label6.setVisible(false);
                Label7.setVisible(false);
                Label8.setVisible(false);
                break;

        }

    }

    void setData(String table){  //отображение данных на форме в зависимости от того, какую таблицу добавлять
        action = "Add";
        openTable=table;
        switch (table) {
            case "Client":
                List<Skills> skillsList;
                skillsList = new ArrayList<>(connection.getSkills());
                for (Skills skill : skillsList) {
                    ComboBox6.getItems().add(skill.name);
                    // System.out.println(skill.name);
                }
                Label1.setText("ID");
                Label2.setText("Name");
                Label3.setText("Surname");
                Label4.setText("Education");
                Label5.setText("User ID");
                Label6.setText("Skills");
                Label7.setText("Experience");
                Label8.setText("Age");
                TextField6.setVisible(false);
                break;
            case "Manager":
                Label1.setText("ID");
                Label2.setText("Name");
                Label3.setText("Surname");
                Label4.setText("User ID");
                ComboBox6.setVisible(false);
                Label5.setVisible(false);
                Label6.setVisible(false);
                Label7.setVisible(false);
                Label8.setVisible(false);
                TextField6.setVisible(false);
                TextField5.setVisible(false);
                TextField7.setVisible(false);
                TextField8.setVisible(false);
                break;
            case "Request":
                Label1.setText("ID");
                Label2.setText("Description");
                Label3.setText("Client");
                Label4.setText("Manager");
                Label5.setText("Position");
                Label6.setText("Status");
                ComboBox6.setVisible(false);
                TextField7.setVisible(false);
                TextField8.setVisible(false);

                Label7.setVisible(false);
                Label8.setVisible(false);
                break;
            case "User":
                Label1.setText("ID");
                Label2.setText("Login");
                Label3.setText("Password");
                Label4.setText("Role");
                TextField6.setVisible(false);
                TextField5.setVisible(false);
                ComboBox6.setVisible(false);
                TextField7.setVisible(false);
                TextField8.setVisible(false);
                Label5.setVisible(false);
                Label6.setVisible(false);
                Label7.setVisible(false);
                Label8.setVisible(false);
                break;
            case "Position":
                Label1.setText("ID");
                Label2.setText("Name");
                Label3.setText("Schedule");
                Label4.setText("Hours per week");
                Label5.setText("Average salary");
                TextField6.setVisible(false);
                ComboBox6.setVisible(false);
                TextField7.setVisible(false);
                TextField8.setVisible(false);
                Label6.setVisible(false);
                Label7.setVisible(false);
                Label8.setVisible(false);
                break;
            case "Skills":

                Label1.setText("ID");
                Label2.setText("Name");
                TextField6.setVisible(false);
                TextField3.setVisible(false);
                TextField4.setVisible(false);
                TextField5.setVisible(false);
                ComboBox6.setVisible(false);
                TextField7.setVisible(false);
                TextField8.setVisible(false);
                Label3.setVisible(false);
                Label4.setVisible(false);
                Label5.setVisible(false);
                Label6.setVisible(false);
                Label7.setVisible(false);
                Label8.setVisible(false);
                break;

        }

    }
    public void setManager(){
        role = 1;
    }

    public void setClient(){
        role = 0;
    }

    public void setAdmin(){
        role = 2;
    }


    @FXML
    void onTypedField2() {
        switch (openTable) {
            case "Client":
                if(!TextField2.getText().matches("[a-z A-Z]+")){
                    buttonSave.setDisable(true);
                }else{
                    buttonSave.setDisable(false);
                }
                break;
            case "Manager":

                break;
            case "Request":
                if(!TextField2.getText().matches("[a-z A-Z]+")){
                    buttonSave.setDisable(true);
                }else{
                    buttonSave.setDisable(false);
                }

                break;
            case "User":

                break;
            case "Position":

                break;
            case "Skills":

                break;

        }

    }
    @FXML
    void onTypedField1() {}
    @FXML
    void onTypedField3() {
        switch (openTable) {
            case "Client":
                if(!TextField3.getText().matches("[a-z A-Z]+")){
                    buttonSave.setDisable(true);
                }else{
                    buttonSave.setDisable(false);
                }

                break;
            case "Manager":

                break;
            case "Request":
                if(!TextField3.getText().matches("[-+]?\\d+"))
                {
                    buttonSave.setDisable(true);
                }else{
                    buttonSave.setDisable(false);
                }
                break;
            case "User":

                break;
            case "Position":

                break;
            case "Skills":

                break;

        }

    }
    @FXML
    void onTypedField4() {
        switch (openTable) {
            case "Client":
                if(!TextField4.getText().matches("[a-z A-Z]+")){
                    buttonSave.setDisable(true);
                }else{
                    buttonSave.setDisable(false);
                }

                break;
            case "Manager":

                break;
            case "Request":
                if(!TextField4.getText().matches("[-+]?\\d+"))
                {
                    buttonSave.setDisable(true);
                }else{
                    buttonSave.setDisable(false);
                }
                break;
            case "User":

                break;
            case "Position":

                break;
            case "Skills":

                break;

        }

    }

    @FXML
    void onTypedField5() {
        switch (openTable) {
            case "Client":
                if(!TextField5.getText().matches("[-+]?\\d+"))
                {
                    buttonSave.setDisable(true);
                }else{
                    buttonSave.setDisable(false);
                }
                break;
            case "Manager":

                break;
            case "Request":
                if(!TextField5.getText().matches("[-+]?\\d+"))
                {
                    buttonSave.setDisable(true);
                }else{
                    buttonSave.setDisable(false);
                }
                break;
            case "User":

                break;
            case "Position":

                break;
            case "Skills":

                break;

        }
    }
    @FXML
    void onTypedField6() {
        switch (openTable) {
            case "Client":

                break;
            case "Manager":

                break;
            case "Request":
                if(!TextField6.getText().matches("[a-z A-Z]+"))
                {
                    buttonSave.setDisable(true);
                }else{
                    buttonSave.setDisable(false);
                }
                break;
            case "User":

                break;
            case "Position":

                break;
            case "Skills":

                break;

        }

    }
    @FXML
    void onTypedField7() {
        switch (openTable) {
            case "Client":
                if(!TextField7.getText().matches("[-+]?\\d+"))
                {
                    buttonSave.setDisable(true);
                }else{
                    buttonSave.setDisable(false);
                }

                break;
            case "Manager":

                break;
            case "Request":

                break;
            case "User":

                break;
            case "Position":

                break;
            case "Skills":

                break;

        }
    }
    @FXML
    void onTypedField8() {
        switch (openTable) {
            case "Client":
                if(!TextField8.getText().matches("[-+]?\\d+"))
                {
                    buttonSave.setDisable(true);
                }else{
                    buttonSave.setDisable(false);
                }

                break;
            case "Manager":

                break;
            case "Request":

                break;
            case "User":

                break;
            case "Position":

                break;
            case "Skills":

                break;

        }
    }
}
