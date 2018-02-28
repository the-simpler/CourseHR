package HRInformationSystem.controller;

import HRInformationSystem.model.*;
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
import java.util.Random;

import static java.lang.Math.random;

public class ClientController {
    @FXML
    private Label welcomeLabel;

    @FXML
    private Label surnameLabel;

    @FXML
    private Label educationLabel;

    @FXML
    private Label ageLabel;

    @FXML
    private Label experienceLabel;

    @FXML
    private Label nameLabel;

    @FXML
    private Label skillLabel;

    @FXML
    private ComboBox<Object> positionCombo;

    @FXML
    private TextArea descriptionText;

    @FXML
    private TableView<RequestJoin> requestTable;

    @FXML
    private Button requestButton;

    @FXML
    private TableColumn<RequestJoin, String> positionColumn;
    @FXML
    private TableColumn<RequestJoin, String> statusColumn;
    @FXML
    private TableColumn<RequestJoin, String> descriptionColumn;

    ClientConnection connection;
    public static User currentUser;
    public static Client currentClient;
    public  List<RequestJoin> joinList;
    public List<Position> positionList;
    @FXML private void initialize() {
        positionColumn.setCellValueFactory(new PropertyValueFactory<>("position"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        //surnameLabel.setText(currentUser.login);
        positionList = new ArrayList<>(connection.getPositions());
        for (Position position : positionList) {
            positionCombo.getItems().add(position.name);
            // System.out.println(skill.name);
        }
        positionCombo.setValue(positionList.get(1).name);

    }

    public void welcome(){
        welcomeLabel.setText("Welcome client \""+currentUser.login+"\"");
    }
    public void setCurrentUser(User user){
        currentUser = user;
    }
    public void labelInit() throws IOException, ClassNotFoundException {
        connection = new ClientConnection();
        currentClient = connection.getClientByID(currentUser.id);
        nameLabel.setText(currentClient.name);
        surnameLabel.setText(currentClient.surname);
        educationLabel.setText(currentClient.education);
        ageLabel.setText(""+currentClient.age);
        experienceLabel.setText(""+currentClient.experience);
        List<Skills> skillsList;
        skillsList = new ArrayList<>(connection.getSkills());
        skillLabel.setText(skillsList.get(currentClient.skills).name);
    }

    @FXML
    void onClickedRequest() {
        joinList=new ArrayList<>(connection.getRequestsJoinByClient(currentClient.id));
        //System.out.println(joinList.get(0).client+joinList.get(0).position+joinList.get(0).manager);
        requestTable.setItems(FXCollections.observableList(joinList));
        requestTable.setRowFactory((TableView<RequestJoin> paramP) -> new TableRow<RequestJoin>() {
            @Override
            protected void updateItem(RequestJoin row, boolean paramBoolean) {
                if (row != null) {
                    switch (row.status) {
                        case "accept":
                            setStyle("-fx-background-color: LIGHTGREEN; -fx-text-background-color: black;");
                            int a;
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
    List<Manager> managerList;
    @FXML
    void onClickedSend() {
        Request request = new Request();
        request.description = descriptionText.getText();
        request.client = currentClient.id;
        managerList=new ArrayList<>(connection.getManagers());
        System.out.println(managerList.size());
        request.manager =managerList.get(new Random().nextInt(managerList.size())).id;
        request.position =positionCombo.getSelectionModel().getSelectedIndex() + 1;
        request.status = "waiting";
        String result = connection.addDeleteEditTable(request, "AddRequest");
        System.out.println(result +" add "+" Request");


    }

    @FXML
    void onClickedBack() {
        try {
            Stage newView= new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/HRInformationSystem/view/AuthorizationView.fxml"));
            newView.setTitle("Authorization!");
            Stage newone;
            AnchorPane layout = loader.load();
            newView.setScene(new Scene(layout));
            newView.show();
            Stage stage = (Stage) requestButton.getScene().getWindow();
            stage.close();
        }catch(IOException e){
            e.printStackTrace();

        }
    }

    @FXML
    void onClickedEdit() throws IOException {

        Stage newView= new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/HRInformationSystem/view/EditDataView.fxml"));
        newView.setTitle("Edit");
        AnchorPane layout = loader.load();
        newView.setScene(new Scene(layout));
        newView.setResizable(false);
        Client clnt = currentClient;
        EditDataController controller = loader.getController();
        controller.setClient();
        controller.setData("Client", clnt);
        //setEditData(newView);
        newView.show();
        Stage stage = (Stage) ageLabel.getScene().getWindow();
        stage.close();
    }
}
