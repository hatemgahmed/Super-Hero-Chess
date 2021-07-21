package view;


import java.io.File;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

public class Video extends Application{

StackPane stack = new StackPane();

static int x;
static String win;

public void start(Stage primaryStage) throws Exception {
	if(x==1) {
		final File file = new File("Team1.mp4");
		final String MEDIA_URL = file.toURI().toString();
		Media media = new Media(MEDIA_URL);

		MediaPlayer player = new MediaPlayer(media);


		primaryStage.setScene(new Scene(new Group(new MediaView(player)), 640, 360));
		primaryStage.setTitle(win +" wins");
		primaryStage.show();

		player.play();
		}
	else {
		final File file = new File("Team21.mp4");
		final String MEDIA_URL = file.toURI().toString();
		Media media = new Media(MEDIA_URL);

		MediaPlayer player = new MediaPlayer(media);


		primaryStage.setScene(new Scene(new Group(new MediaView(player)), 640, 360));
		primaryStage.setTitle(win +" wins");
		primaryStage.show();

		player.play();
	}
}

public static void startVideo(int i,String s){
	x=i;
	win = s;
    Application.launch();
}

}