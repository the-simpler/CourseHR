package HRInformationSystem.controller;

import HRInformationSystem.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AdminController {
    @FXML private Button EditButton;
    @FXML TableView<Object> tableView;
    @FXML private TableColumn<Object, Object> Column1;
    @FXML private TableColumn<Object, Object> Column2;
    @FXML private TableColumn<Object, Object> Column3;
    @FXML private TableColumn<Object, Object> Column4;
    @FXML private TableColumn<Object, Object> Column5;
    @FXML private TableColumn<Object, Object> Column6;
    @FXML private TableColumn<Object, Object> Column7;
    @FXML private TableColumn<Object, Object> Column8;
    @FXML private Button EditButton1;
    public static String openTable = "Request";
    List<Object> joinList;
    List<Object> userList;
    List<Object> clientList;
    List<Object> managerList;
    List<Object> skillsList;
    List<Object> positionList;
    public ClientConnection connection;

    @FXML
    void initialize() {   //заполнение таблицы в зависимости от выбранной ранее, либо выбранной по умолчанию
        switch (openTable) {
            case "Client":
                onClickedClient();
                break;
            case "Request":
                onClickedRequest();
                break;
            case "User":
                onClickedUser();
                break;
            case "Manager":
                onClickedManager();
                break;
            case "Skills":
                onClickedSkills();
                break;
            case "Position":
                onClickedPosition();
                break;
        }
    }
    @FXML                       //заполнение таблицы по нажатию на кнопку
    void onClickedRequest(){
        columnForRequest();
        joinList=new ArrayList<>(connection.getRequests());
        tableView.setItems(FXCollections.observableList(joinList));
    }
    @FXML
    void onClickedUser(){
        columnForUser();
        userList=new ArrayList<>(connection.getUsers());
        tableView.setItems(FXCollections.observableList(userList));
    }

    @FXML
    void onClickedClient() {
        columnForClient();
        clientList=new ArrayList<>(connection.getClients());
        tableView.setItems(FXCollections.observableList(clientList));
    }
    @FXML
    void onClickedManager(){
        columnForManager();
        managerList=new ArrayList<>(connection.getManagers());
        tableView.setItems(FXCollections.observableList(managerList));

    }
    @FXML
    void onClickedSkills() {

        columnForSkills();
        skillsList=new ArrayList<>(connection.getSkills());
        tableView.setItems(FXCollections.observableList(skillsList));
    }

    @FXML
    void onClickedPosition() {
        columnForPosition();
        positionList=new ArrayList<>(connection.getPositions());
        tableView.setItems(FXCollections.observableList(positionList));
    }
    @FXML
    void onCancelClicked(){
        Stage stage = (Stage) EditButton.getScene().getWindow();
        stage.close();
    }


    @FXML
    void onClickedEdit() throws IOException {
        Stage newView= new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/HRInformationSystem/view/EditDataView.fxml"));
        newView.setTitle("Edit");
        AnchorPane layout = loader.load();
        newView.setScene(new Scene(layout));
        newView.setResizable(false);
        setEditData(loader);
        EditDataController controller = loader.getController();
        controller.setAdmin();
        //setEditData(newView);
        newView.show();
        Stage stage = (Stage) EditButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void onClickedRefresh() {
        initialize();
    }

    @FXML
    void onClickedAdd() throws IOException {
        Stage newView= new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/HRInformationSystem/view/EditDataView.fxml"));
        newView.setTitle("Add");
        AnchorPane layout = loader.load();
        newView.setScene(new Scene(layout));
        newView.setResizable(false);
        EditDataController controller = loader.getController();
        controller.setData(openTable);
        controller.setAdmin();
        //setEditData(newView);
        newView.show();
        Stage stage = (Stage) EditButton.getScene().getWindow();
        stage.close();
    }

    @FXML                           //удаление записей по нажатию на кнопку
    void onClickedDelete() {
        Object obj;
        switch (openTable) {
            case "Client":
                obj =(Client) tableView.getSelectionModel().getSelectedItem();
                connection.addDeleteEditTable(obj, "DeleteClient");
                break;
            case "Request":
                obj =(Request) tableView.getSelectionModel().getSelectedItem();
                connection.addDeleteEditTable(obj, "DeleteRequest");
                break;
            case "User":
                obj =(User) tableView.getSelectionModel().getSelectedItem();
                connection.addDeleteEditTable(obj, "DeleteUser");
                break;
            case "Manager":
                obj =(Manager) tableView.getSelectionModel().getSelectedItem();
                connection.addDeleteEditTable(obj, "DeleteManager");
                break;
            case "Skills":
                obj =(Skills) tableView.getSelectionModel().getSelectedItem();
                connection.addDeleteEditTable(obj, "DeleteSkills");
                break;
            case "Position":
                obj =(Position) tableView.getSelectionModel().getSelectedItem();
                connection.addDeleteEditTable(obj, "DeletePosition");
                break;
        }
        initialize();



    }
    @FXML                           //возврат на окно авторизации
    void onClickedBack() {
        try {
            Stage newView= new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/HRInformationSystem/view/AuthorizationView.fxml"));
            newView.setTitle("Authorization!");
            AnchorPane layout = loader.load();
            newView.setScene(new Scene(layout));
            newView.show();
            Stage stage = (Stage) tableView.getScene().getWindow();
            stage.close();
        }catch(IOException e){
            e.printStackTrace();

        }
    }
    void columnForRequest(){            //инициализация столбцов таблицы в зависимости от выбранной таблицы
        openTable = "Request";
        Column1.setCellValueFactory(new PropertyValueFactory<>("id"));
        Column1.setVisible(true);
        Column1.setText("id");
        Column2.setCellValueFactory(new PropertyValueFactory<>("description"));
        Column2.setVisible(true);
        Column2.setText("description");
        Column3.setCellValueFactory(new PropertyValueFactory<>("manager"));
        Column3.setVisible(true);
        Column3.setText("manager");
        Column4.setCellValueFactory(new PropertyValueFactory<>("client"));
        Column4.setVisible(true);
        Column4.setText("client");
        Column5.setCellValueFactory(new PropertyValueFactory<>("position"));
        Column5.setVisible(true);
        Column5.setText("position");
        Column6.setCellValueFactory(new PropertyValueFactory<>("status"));
        Column6.setText("status");
        Column6.setVisible(true);
        Column7.setCellValueFactory(new PropertyValueFactory<>(""));
        Column7.setVisible(false);
        Column8.setCellValueFactory(new PropertyValueFactory<>(""));
        Column8.setVisible(false);
    }
    void columnForManager(){
        openTable = "Manager";
        Column1.setCellValueFactory(new PropertyValueFactory<>("id"));
        Column1.setVisible(true);
        Column1.setText("id");
        Column2.setCellValueFactory(new PropertyValueFactory<>("name"));
        Column2.setVisible(true);
        Column2.setText("name");
        Column3.setCellValueFactory(new PropertyValueFactory<>("surname"));
        Column3.setVisible(true);
        Column3.setText("surname");
        Column4.setCellValueFactory(new PropertyValueFactory<>("user_id"));
        Column4.setVisible(true);
        Column4.setText("user_id");
        Column5.setCellValueFactory(new PropertyValueFactory<>(""));
        Column5.setVisible(false);
        Column6.setCellValueFactory(new PropertyValueFactory<>(""));
        Column6.setVisible(false);
        Column7.setCellValueFactory(new PropertyValueFactory<>(""));
        Column7.setVisible(false);
        Column8.setCellValueFactory(new PropertyValueFactory<>(""));
        Column8.setVisible(false);
    }
    void columnForClient(){
        openTable = "Client";
        Column1.setCellValueFactory(new PropertyValueFactory<>("id"));
        Column1.setVisible(true);
        Column1.setText("id");
        Column2.setCellValueFactory(new PropertyValueFactory<>("name"));
        Column2.setVisible(true);
        Column2.setText("name");
        Column3.setCellValueFactory(new PropertyValueFactory<>("surname"));
        Column3.setVisible(true);
        Column3.setText("surname");
        Column4.setCellValueFactory(new PropertyValueFactory<>("age"));
        Column4.setVisible(true);
        Column4.setText("age");
        Column5.setCellValueFactory(new PropertyValueFactory<>("skills"));
        Column5.setVisible(true);
        Column5.setText("skills");
        Column6.setCellValueFactory(new PropertyValueFactory<>("education"));
        Column6.setVisible(true);
        Column6.setText("education");
        Column7.setCellValueFactory(new PropertyValueFactory<>("experience"));
        Column7.setVisible(true);
        Column7.setText("experience");
        Column8.setCellValueFactory(new PropertyValueFactory<>("user_id"));
        Column8.setVisible(true);
        Column8.setText("user_id");
    }
    void columnForUser() {
        openTable = "User";
        Column1.setCellValueFactory(new PropertyValueFactory<>("id"));
        Column1.setVisible(true);
        Column1.setText("id");
        Column2.setCellValueFactory(new PropertyValueFactory<>("login"));
        Column2.setVisible(true);
        Column2.setText("login");
        Column3.setCellValueFactory(new PropertyValueFactory<>("password"));
        Column3.setVisible(true);
        Column3.setText("password");
        Column4.setCellValueFactory(new PropertyValueFactory<>("role"));
        Column4.setVisible(true);
        Column4.setText("role");
        Column5.setCellValueFactory(new PropertyValueFactory<>(""));
        Column5.setVisible(false);
        Column6.setCellValueFactory(new PropertyValueFactory<>(""));
        Column6.setVisible(false);
        Column7.setCellValueFactory(new PropertyValueFactory<>(""));
        Column7.setVisible(false);
        Column8.setCellValueFactory(new PropertyValueFactory<>(""));
        Column8.setVisible(false);
    }
    void columnForSkills(){
        openTable = "Skills";
        Column1.setCellValueFactory(new PropertyValueFactory<>("id"));
        Column1.setVisible(true);
        Column1.setText("id");
        Column2.setCellValueFactory(new PropertyValueFactory<>("name"));
        Column2.setVisible(true);
        Column2.setText("name");
        Column3.setCellValueFactory(new PropertyValueFactory<>(""));
        Column3.setVisible(false);
        Column4.setCellValueFactory(new PropertyValueFactory<>(""));
        Column4.setVisible(false);
        Column5.setCellValueFactory(new PropertyValueFactory<>(""));
        Column5.setVisible(false);
        Column6.setCellValueFactory(new PropertyValueFactory<>(""));
        Column6.setVisible(false);
        Column7.setCellValueFactory(new PropertyValueFactory<>(""));
        Column7.setVisible(false);
        Column8.setCellValueFactory(new PropertyValueFactory<>(""));
        Column8.setVisible(false);
    }
    void columnForPosition(){
        openTable = "Position";
        Column1.setCellValueFactory(new PropertyValueFactory<>("id"));
        Column1.setVisible(true);
        Column1.setText("id");
        Column2.setCellValueFactory(new PropertyValueFactory<>("name"));
        Column2.setVisible(true);
        Column2.setText("name");
        Column3.setCellValueFactory(new PropertyValueFactory<>("hours_per_week"));
        Column3.setVisible(true);
        Column3.setText("hours_per_week");
        Column4.setCellValueFactory(new PropertyValueFactory<>("average_salary"));
        Column4.setVisible(true);
        Column4.setText("average_salary");
        Column5.setCellValueFactory(new PropertyValueFactory<>("schedule"));
        Column5.setText("schedule");
        Column5.setVisible(true);
        Column6.setCellValueFactory(new PropertyValueFactory<>(""));
        Column6.setVisible(false);
        Column7.setCellValueFactory(new PropertyValueFactory<>(""));
        Column7.setVisible(false);
        Column8.setCellValueFactory(new PropertyValueFactory<>(""));
        Column8.setVisible(false);

    }
    public void setEditData(FXMLLoader loader){
        EditDataController controller = loader.getController();
        switch (openTable) {
            case "Client":
                //obj =  tableView.getSelectionModel();
                Client clnt = (Client)tableView.getSelectionModel().getSelectedItem();
                controller.setData(openTable, clnt);
                break;
            case "Request":
                Request rqst = (Request)tableView.getSelectionModel().getSelectedItem();
                controller.setData(openTable, rqst);
                break;
            case "User":
                User usr = (User)tableView.getSelectionModel().getSelectedItem();
                controller.setData(openTable, usr);
                break;
            case "Manager":
                Manager mngr = (Manager)tableView.getSelectionModel().getSelectedItem();
                controller.setData(openTable, mngr);
               // obj = tableView.getSelectionModel();
                break;
            case "Skills":
                Skills sklls = (Skills)tableView.getSelectionModel().getSelectedItem();
                controller.setData(openTable, sklls);
                break;
            case "Position":
                Position pstn = (Position)tableView.getSelectionModel().getSelectedItem();
                controller.setData(openTable, pstn);
                break;
        }
    }
}
