package main;
import coc.Player.Player;
import coc.Tokens.Tokens;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class MainWindowController {


    @FXML
    private Text welcomeBtn;

    @FXML
    private TextField playerTagTextField;

    @FXML
    private void submitPlayerTag() {
        String playerTag = playerTagTextField.getText().replace("#","");
        System.out.println("Player tag submitted: " + playerTag);
        Player player = getPlayer(playerTag);
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("mainView.fxml"));
            Parent root = loader.load();
            PlayerController playerController = loader.getController();
            playerController.setPlayer(player);
            Scene scene = new Scene(root);
            Stage stage = (Stage) playerTagTextField.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private Player getPlayer(String playerTag) {
        try {
            String PLAYER_TAG = playerTag ;
            String API_KEY = Tokens.getAPI_KEY();
            return new Player(API_KEY, PLAYER_TAG);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}

