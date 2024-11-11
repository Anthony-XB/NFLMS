//package com.CEN30241.nflms.Controllers;
//
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Node;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.stage.Stage;
//import javafx.application.Application;
//import com.CEN30241.nflms.Controllers.Menu;
//
//import java.io.IOException;
//import java.util.Objects;
//
//public class menuController {
//
//    Menu menu = new Menu();
//
//    private Stage stage;
//    private Scene scene;
//    private Parent root;
//    @FXML
//    private Button enterButton;
//
//    public void switchToScene1(ActionEvent event) throws IOException {
//        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("menuFX.fxml")));
//        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
//        scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();
//    }
//
//    public void switchToScene2(ActionEvent event) throws IOException{
//        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("menuFX2.fxml")));
//        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
//        scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();
//    }
//
//    @FXML
//    private void EnterButtonClick(){
//        Stage stage = (Stage)enterButton.getScene().getWindow();
//        menu.showMainMenu(stage);
//
//    }
//
//
//
//}
