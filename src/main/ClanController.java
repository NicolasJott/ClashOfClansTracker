package main;

import coc.Clan.Clan;
import coc.Events.GoldPass;
import coc.Player.Player;
import coc.Tokens.Tokens;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class ClanController {

    @FXML
    private ImageView clanImage;
    @FXML
    private Label clanName;
    @FXML
    private Label clanTag;
    @FXML
    private Label clanDescription;

    @FXML
    private Label clanLeague;

    @FXML
    private Label clanPoints;

    @FXML
    private Label clanVersusPoints;

    @FXML
    private Label clanLocation;

    @FXML
    private Label clanLanguage;

    @FXML
    private Label requiredTrophies;
    @FXML
    private Label requiredVersusTrophies;

    @FXML
    private Label requiredTownhallLevel;

    @FXML
    private Label numMembers;

    @FXML
    private VBox clanMembers = null;

    public void setClan(Player player) throws IOException {
        String CLAN_TAG = player.getClanTag().replace("#", "");
        String API_TOKEN = Tokens.getAPI_KEY();
        Clan clan = new Clan(API_TOKEN, CLAN_TAG);

        Image clanBanner = new Image(player.getMediumClanBadge());
        clanImage.setImage(clanBanner);

        clanName.setText(clan.getName());
        clanTag.setText(clan.getTag());
        clanDescription.setText(clan.getDescription());
        clanLeague.setText(clan.getClanWarLeague());
        clanPoints.setText(clan.getClanPoints().toString());
        clanVersusPoints.setText(clan.getClanVersusPoints().toString());
        clanLocation.setText(clan.getLocationName());
        clanLanguage.setText(clan.getClanLanguage());
        requiredTrophies.setText(clan.getRequiredTrophies().toString());
        requiredVersusTrophies.setText(clan.getRequiredVersusTrophies().toString());
        requiredTownhallLevel.setText(clan.getRequiredTownhallLevel().toString());
        numMembers.setText("Members " + clan.getNumberMembers() + "/50");

        JSONArray clanMembersArray = clan.getClanMembers();

        Node[] nodes = new Node[clanMembersArray.length()]; // create a node array with the size of your JSON array
        for (int i = 0; i < nodes.length; i++) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("clanMember.fxml"));
                nodes[i] = loader.load();
                JSONObject member = clanMembersArray.getJSONObject(i); // get the corresponding achievement JSON object for this item
                ClanMemberController controller = loader.getController(); // get the controller for the FXML file
                controller.setMemberInfo(member); // call a method in the controller to set the achievement information
                clanMembers.getChildren().add(nodes[i]);
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
        }

    }

}
