package HRInformationSystem.controller;

import HRInformationSystem.model.Manager;
import HRInformationSystem.model.Request;

import HRInformationSystem.model.RequestJoin;
import HRInformationSystem.model.User;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class ManagerController {

    @FXML
    TableView<Request> requestTableView;
    @FXML
    Button acceptButton;
    @FXML
    Button editButton;
    @FXML
    Button declineButton;
    @FXML
    Button refreshButton;
    @FXML
    TableColumn<RequestJoin, Integer> columnRequestID;
    @FXML
    TableColumn<RequestJoin, String> columnDescription;
    @FXML
    TableColumn<RequestJoin, String> columnClient;
    @FXML
    TableColumn<RequestJoin, String> columnManager;
    @FXML
    TableColumn<RequestJoin, String> columnPosition;
    @FXML
    TableColumn<RequestJoin, String> columnStatus;
    @FXML
    Label welcomeLabel;
    @FXML
    private TextField descriptionField;
    @FXML
    private TextField managerField;
    @FXML
    private TextField statusField;
    public static User currentUser;
    public static Manager currentManager;
    List<Request> joinList;
    public ClientConnection connection;

    @FXML
    private void initialize() {
        columnRequestID.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        columnClient.setCellValueFactory(new PropertyValueFactory<>("client"));
        columnManager.setCellValueFactory(new PropertyValueFactory<>("manager"));
        columnPosition.setCellValueFactory(new PropertyValueFactory<>("position"));
        columnStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        joinList = new ArrayList<>(connection.getRequests());
        //System.out.println(joinList.get(0).client+joinList.get(0).position+joinList.get(0).manager);
        requestTableView.setItems(FXCollections.observableList(joinList));
        //requestTableView.setRowFactory();
        setstyle();

    }

    public void welcome() {
        welcomeLabel.setText("Welcome manager \"" + currentUser.login + "\", your ID " + currentManager.id);
    }

    public void setCurrentUser(User user) {
        currentUser = user;
    }

    List<Manager> managerList;
    public void setCurrentManager() {
        managerList=new ArrayList<>(connection.getManagers());
        for (Manager mngr : managerList) {
            if (currentUser.id == mngr.user_id){
                currentManager = mngr;
            }
        }
    }

    @FXML
    void onClickedAccept() {
        try {
            Request rqst = (Request) requestTableView.getSelectionModel().getSelectedItem();
            rqst.status = "accept";
            String result = connection.addDeleteEditTable(rqst, "EditRequest");
            System.out.println(result + " accept " + "request");
            initialize();
        } catch (NullPointerException ex) {
            new AlertController().selectionError();
        }
    }

    @FXML
    void onClickedBack() {
        try {
            Stage newView = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/HRInformationSystem/view/AuthorizationView.fxml"));
            newView.setTitle("Authorization!");
            AnchorPane newone;
            AnchorPane layout = loader.load();
            newView.setScene(new Scene(layout));
            newView.show();
            Stage stage = (Stage) refreshButton.getScene().getWindow();
            stage.close();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    @FXML
    void onClickedEdit() throws IOException {
        try {
            Stage newView = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/HRInformationSystem/view/EditDataView.fxml"));
            newView.setTitle("Edit");
            AnchorPane layout = loader.load();
            newView.setScene(new Scene(layout));
            newView.setResizable(false);
            EditDataController controller = loader.getController();
            Request rqst = (Request) requestTableView.getSelectionModel().getSelectedItem();
            controller.setData("Request", rqst);
            controller.setManager();
            newView.show();
            Stage stage = (Stage) editButton.getScene().getWindow();
            stage.close();
        } catch (NullPointerException ex) {
            new AlertController().selectionError();
        }
    }

    @FXML
    void onClickedDecline() {
        try {
            Request rqst = (Request) requestTableView.getSelectionModel().getSelectedItem();
            rqst.status = "decline";
            String result = connection.addDeleteEditTable(rqst, "EditRequest");
            System.out.println(result + " decline " + "request");
            initialize();
        } catch (NullPointerException ex) {
            new AlertController().selectionError();
        }
    }

    @FXML
    void onClickedRefresh() {
        initialize();
    }

    @FXML
    void onClickedChart() {
        try {
            Stage newView = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/HRInformationSystem/view/ChartView.fxml"));
            newView.setTitle("Chart");
            AnchorPane layout = loader.load();
            newView.setScene(new Scene(layout));
            newView.setResizable(false);
            ChartController controller = loader.getController();
            controller.initChart(joinList);
            newView.show();
            Stage stage = (Stage) editButton.getScene().getWindow();
            stage.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    void onKeyPressedManager() {
        //managerList=new ArrayList<>(connection.getManagers());
        if (managerField.getText().equals("")){
            initialize();
            return;
        }
        List<Request> searchList;
        searchList = new ArrayList<>();
        for (Request rqst : joinList) {
            if (Integer.parseInt(managerField.getText()) == rqst.manager){
                searchList.add(rqst);
            }
        }

        requestTableView.setItems(FXCollections.observableList(searchList));
        setstyle();

    }

    @FXML
    void onKeyPressedStatus() {
        if (statusField.getText().equals("")){
            initialize();
            return;
        }
        List<Request> searchList;
        searchList = new ArrayList<>();
        for (Request rqst : joinList) {
            if (statusField.getText().equals(rqst.status)){
                searchList.add(rqst);
            }
        }
        requestTableView.setItems(FXCollections.observableList(searchList));
        setstyle();
    }

    @FXML
    void onKeyPressedDescription(){
        if (descriptionField.getText().equals("")){
            initialize();
            return;
        }
        List<Request> searchList;
        searchList = new ArrayList<>();
        for (Request rqst : joinList) {
            if (rqst.description.contains(descriptionField.getText())){
                searchList.add(rqst);
            }
        }
        requestTableView.setItems(FXCollections.observableList(searchList));
        setstyle();
    }

    void setstyle(){
        requestTableView.setRowFactory((TableView<Request> paramP) -> new TableRow<Request>() {
            @Override
            protected void updateItem(Request row, boolean paramBoolean) {
                if (row != null) {
                    switch (row.status) {
                        case "accept":
                            setStyle("-fx-background-color: LIGHTGREEN; -fx-text-background-color: black;");
                            String b;
                            break;
                        case "decline":
                            setStyle("-fx-background-color: LIGHTCORAL; -fx-text-background-color: black;");
                            break;
                        default:
                            setStyle(null);
                    }
                } else {
                    setStyle(null);
                }
                super.updateItem(row, paramBoolean);
            }
        });
    }
    List<RequestJoin> requestJoin;
    @FXML
    void onClickedReport() throws IOException {
        requestJoin = new ArrayList<>(connection.getRequestsJoin());
        FileWriter file = new FileWriter("Report about Requests.txt", false);
        int allCount = 0;
        int acceptCount = 0;
        int declineCount = 0;
        int waitingCount=0;
        for (RequestJoin rqst : requestJoin) {
            file.write("\r\n"+"Request ID: "+rqst.id+"\r\n"+"Request Description: "+rqst.description+"\r\n"+"Request Manager: "+rqst.manager+"\r\n"+"Request Client: "+rqst.client+"\r\n"+"Request Position: "+rqst.position+"\r\n"+"Request Status: "+rqst.status+"\r\n"+"\r\n");
            if (rqst.status.equals("accept")) acceptCount++;
            else if (rqst.status.equals("decline")) declineCount++;
            else if (rqst.status.equals("waiting")) waitingCount++;
            allCount++;
        }
        file.write("\r\n"+"\r\n"+"Number of accepted requests: "+acceptCount+"\r\n"+"Number of declined requests: "+declineCount+"\r\n"+"Number of ALL requests: "+allCount+"\r\n"+"Number of expected requests: "+waitingCount);
        new AlertController("Success!","Success!","Report has been created!", Alert.AlertType.INFORMATION);
        file.close();
    }
}
