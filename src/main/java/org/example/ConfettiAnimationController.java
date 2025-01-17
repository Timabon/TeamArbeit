package org.example;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import javafx.scene.paint.Color;


public class ConfettiAnimationController {

    public void start(Pane root) {

//        if (root == null || root.getWidth() <= 0 || root.getHeight() <= 0) {
//            System.out.println("Confetti animation aborted: Invalid pane dimensions!");
//            return;
//        }

        // Generate 50 confetti particles
        for (int i = 0; i < 350; i++) {
            Circle particle = new Circle(5, getRandomColor());
            particle.setTranslateX(Math.random() * root.getWidth());
            particle.setTranslateY(0); // Start at the top of the scene

            root.getChildren().add(particle);

            // Create falling animation
            Timeline timeline = new Timeline();
            KeyValue kvY = new KeyValue(particle.translateYProperty(), root.getHeight() + 250);
            KeyValue kvOpacity = new KeyValue(particle.opacityProperty(), 0); // Fade out
            KeyFrame kf = new KeyFrame(Duration.seconds(6 + Math.random()), kvY, kvOpacity);
            timeline.getKeyFrames().add(kf);

            timeline.setOnFinished(e -> {
                root.getChildren().remove(particle);
            });
            timeline.play();
        }
    }

    // Generate a random color for each particle
    private Color getRandomColor() {
        return Color.color(Math.random(), Math.random(), Math.random());
    }
}
