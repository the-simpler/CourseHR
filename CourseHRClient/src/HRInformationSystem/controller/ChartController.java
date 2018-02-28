package HRInformationSystem.controller;

import HRInformationSystem.model.Request;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class ChartController {
    @FXML
    private PieChart pieChart;

    @FXML
    void onClickedBack() throws IOException {
        Stage newView;
        FXMLLoader loader;
        loader = new FXMLLoader(getClass().getResource("/HRInformationSystem/view/ManagerView.fxml"));
        newView = new Stage();
        newView.setTitle("Manager window");
        AnchorPane layout = loader.load();
        ManagerController controller = loader.getController();
        controller.welcome();
        newView.setScene(new Scene(layout));
        newView.setResizable(false);
        newView.show();
        Stage stage = (Stage) pieChart.getScene().getWindow();
        stage.close();

    }
    @FXML
    void initialize() {

    }
    List<Request> RequestList;
    void initChart(List<Request> list){
        RequestList = list;
        double acceptCount = 0;
        double declineCount = 0;
        int waitingCount = 0;
        double allCount = 0;
        for (Request rqst : RequestList) {
           if (rqst.status.equals("accept")){
               acceptCount++;
           }
           if (rqst.status.equals("decline")){
               declineCount++;
           }
           if (rqst.status.equals("waiting")){
               waitingCount++;
           }
           allCount++;
            // System.out.println(skill.name);
        }
        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                        new PieChart.Data("Accepted Request", acceptCount/allCount*100),
                        new PieChart.Data("Declined Request", declineCount/allCount*100));
        pieChart.setTitle("Chart Accept-Decline Request");
        pieChart.setData(pieChartData);
    }
}
