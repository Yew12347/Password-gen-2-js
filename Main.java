import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Random;

public class PasswordGenerator extends Application {

    // ... Other existing code ...

    private ProgressIndicator progressIndicator;

    @Override
    public void start(Stage primaryStage) {
        // ... Existing code ...

        // Create the progress indicator for animation
        progressIndicator = new ProgressIndicator(0);
        progressIndicator.setVisible(false);

        // Add the progress indicator to the layout
        root.getChildren().addAll(lengthLabel, lengthField, includeLowercase, includeUppercase,
                includeDigits, generateButton, progressIndicator, resultLabel, resultField);

        // ... Existing code ...
    }

    private void generatePassword() {
        int length = Integer.parseInt(lengthField.getText());
        String characters = "";

        if (includeLowercase.isSelected())
            characters += "abcdefghijklmnopqrstuvwxyz";

        if (includeUppercase.isSelected())
            characters += "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        if (includeDigits.isSelected())
            characters += "0123456789";

        if (characters.isEmpty()) {
            resultField.setText("Please select at least one option.");
            return;
        }

        // Disable the generate button during password generation
        generateButton.setDisable(true);
        progressIndicator.setVisible(true);

        // Add a slight delay for the loading animation
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1000), new KeyValue(progressIndicator.progressProperty(), 0)));
        timeline.setOnFinished(e -> {
            StringBuilder password = new StringBuilder();
            Random random = new Random();
            for (int i = 0; i < length; i++) {
                int index = random.nextInt(characters.length());
                password.append(characters.charAt(index));
            }

            // Simulate a delay of 1 second for password generation
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }

            // Update the resultField with the generated password
            resultField.setText(password.toString());

            // Re-enable the generate button and hide the progress indicator
            generateButton.setDisable(false);
            progressIndicator.setVisible(false);
        });

        timeline.play();
    }

    // ... Other existing code ...
}
