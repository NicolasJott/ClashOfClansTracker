package main;
import coc.Player.Player;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

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

    private Player player;

    public PlayerController() {
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

        File file = switch (playerTownhallLevel) {
            case 1 -> new File("src/images/town_hall_level1.png");
            case 2 -> new File("src/images/town_hall_level2.png");
            case 3 -> new File("src/images/town_hall_level3.png");
            case 4 -> new File("src/images/town_hall_level4.png");
            case 5 -> new File("src/images/town_hall_level5.png");
            case 6 -> new File("src/images/town_hall_level6.png");
            case 7 -> new File("src/images/town_hall_level7.png");
            case 8 -> new File("src/images/town_hall_level8.png");
            case 9 -> new File("src/images/town_hall_level9.png");
            case 10 -> new File("src/images/town_hall_level10.png");
            case 11 -> new File("src/images/town_hall_level11.png");
            case 12 -> new File("src/images/Town_Hall12.png");
            case 13 -> new File("src/images/Town_Hall13.png");
            case 14 -> new File("src/images/town_hall_level_14.png");
            case 15 -> new File("src/images/town_hall_level_15.png");
            default -> null;
        };

        Image image = new Image(file.toURI().toString());
        townhallImage.setImage(image);

        trophies.setText("Trophies: " + player.getTrophies());



    }



    @FXML
    private void player(MouseEvent event) throws IOException {
        bp.setCenter(ap);
    }
    @FXML
    private void clan(MouseEvent event) throws IOException {
        loadPage("clan");

    }
    @FXML
    private void events(MouseEvent event) throws IOException {
        loadPage("events");

    }

    private void loadPage(String page) throws IOException {
        Parent root = null;

        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(page + ".fxml")));

        bp.setCenter(root);
    }

    public static Image downloadImage(String urlString) throws IOException {
        URL url = new URL(urlString);
        try (InputStream stream = url.openStream()) {
            Image image = new Image(stream);
            return image;
        }
    }
}