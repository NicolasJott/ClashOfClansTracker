package main;

import coc.Player.Player;
import coc.Tokens.Tokens;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

public class ClanMemberController {
    @FXML
    private ImageView leagueImage;

    @FXML
    private ImageView townhallImage;

    @FXML
    private Label memberName;

    @FXML
    private Label memberRole;

    @FXML
    private Label troopsDonated;

    @FXML
    private Label troopsReceived;

    @FXML
    private Label trophies;

    public void setMemberInfo(JSONObject member) throws IOException {

        try {
            String PLAYER_TAG = member.getString("tag").replace("#","");
            String API_KEY = Tokens.getAPI_KEY();
            Player clanMember = new Player(API_KEY, PLAYER_TAG);

            Image leagueImg = new Image(member.getString("leagueIcon"));
            leagueImage.setImage(leagueImg);

            Image thImg = new Image(clanMember.getTownHallImage());
            townhallImage.setImage(thImg);

            memberName.setText(clanMember.getPlayerName());
            memberRole.setText(clanMember.getRole());
            troopsDonated.setText(member.getString("donations"));
            troopsReceived.setText(member.getString("donationsReceived"));
            trophies.setText(member.getString("trophies"));
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
