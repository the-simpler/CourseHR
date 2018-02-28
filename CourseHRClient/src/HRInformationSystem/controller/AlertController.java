package HRInformationSystem.controller;

import javafx.scene.control.Alert;

public class AlertController {
    public AlertController(String title, String header, String content, Alert.AlertType type){
        //type = Alert.AlertType.INFORMATION;
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public AlertController() {
    }

    public void AlertSocket(){
        AlertController alert = new AlertController("Socket ERROR!",
                "Socket Error!",
                "Make sure you turn on the server!",
                Alert.AlertType.INFORMATION);
    }
    public void selectionError(){
        new AlertController("Selection error!",
                "Selection error!",
                "Select anything!",
                Alert.AlertType.ERROR);
    }
}
