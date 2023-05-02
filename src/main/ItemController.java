package main;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.util.Objects;

public class ItemController {

    @FXML
    private ImageView achievementStars;
    @FXML
    private Label achievementName;
    @FXML
    private Label achievementInfo;
    @FXML
    private Label achievementCompletion;



    public void setAchievementInfo(String name, String info, String completion, int stars) {
        System.out.println(stars);
        File file = null;
        if (Objects.equals(completion, "Completed!")) {
            file = new File("src/images/three_star.png");
        } else {
            file = switch (stars) {
                case 0 -> new File("src/images/no_star.png");
                case 1 -> new File("src/images/one_star.png");
                case 2 -> new File("src/images/two_star.png");
                case 3 -> new File("src/images/three_star.png");
                default -> null;
            };
        }

        assert file != null;
        Image image = new Image(file.toURI().toString());
        achievementStars.setImage(image);

        achievementName.setText(name);
        achievementInfo.setText(info);
        achievementCompletion.setText(completion);

    }
}