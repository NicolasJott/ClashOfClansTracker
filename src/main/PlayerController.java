package main;
import coc.Player.Player;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

import java.net.URL;


public class PlayerController {

    @FXML
    private BorderPane bp;
    @FXML
    private AnchorPane ap;

    @FXML
    private Text playerNameField;

    @FXML
    private Text playerTagField;

    @FXML
    private Text townhallLevel;


    @FXML
    private ImageView townhallImage;
    @FXML
    private ImageView clanImage;

    @FXML
    private ImageView leagueImage;

    @FXML
    private ImageView xpImage;

    @FXML
    private Text playerLeagueField;

    @FXML
    private Text clanNameField;

    @FXML
    private Text levelField;

    @FXML
    private Text trophies;

    @FXML
    private VBox pnItems = null;

    private Player player;

    public PlayerController() throws IOException {

    }

    public void setPlayer(Player player) throws IOException {
        this.player = player;
        playerNameField.setText(player.getPlayerName());
        playerTagField.setText(player.getPlayerTag());
        playerLeagueField.setText(player.getLeagueName());
        clanNameField.setText(player.getClanName());
        levelField.setText("" + player.getExpLevel());

        Image leagueImg = downloadImage(player.getMediumIconUrl());
        leagueImage.setImage(leagueImg);

        Image clanImg = downloadImage(player.getSmallClanBadge());
        clanImage.setImage(clanImg);

        Integer playerTownhallLevel = player.getTownHallLevel();
        townhallLevel.setText("Town Hall " + playerTownhallLevel);

        String thImg = player.getTownHallImage();

        Image image = new Image(thImg);
        townhallImage.setImage(image);

        trophies.setText("Trophies: " + player.getTrophies());

        JSONArray achievementsJsonArray = player.getAchievements();

        pnItems.setPadding(new Insets(10, 50, 50, 50));

        Node[] nodes = new Node[achievementsJsonArray.length()]; // create a node array with the size of your JSON array
        for (int i = 0; i < nodes.length; i++) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("item.fxml"));
                nodes[i] = loader.load();
                JSONObject achievementJson = achievementsJsonArray.getJSONObject(i); // get the corresponding achievement JSON object for this item
                ItemController controller = loader.getController(); // get the controller for the FXML file
                controller.setAchievementInfo(achievementJson.getString("name"), achievementJson.getString("info"), achievementJson.getString("completionInfo"), achievementJson.getInt("stars")); // call a method in the controller to set the achievement information
                pnItems.getChildren().add(nodes[i]);
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
        }




    }



    @FXML
    private void player(MouseEvent event) throws IOException {
        bp.setCenter(ap);
    }



    private Parent clanView;
    @FXML
    private void clan(MouseEvent event) throws IOException {
        if (clanView == null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("clan.fxml"));
            clanView = loader.load();
            ClanController clanController = loader.getController();
            clanController.setClan(player);
        }

        // Show the "clan" view
        bp.setCenter(clanView);

    }

    public static Image downloadImage(String urlString) throws IOException {
        URL url = new URL(urlString);
        try (InputStream stream = url.openStream()) {
            Image image = new Image(stream);
            return image;
        }
    }
}