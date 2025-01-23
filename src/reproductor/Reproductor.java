package reproductor;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.File;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Reproductor extends Application {
    
    Controller c = new Controller();
    String audioPath;
    String videoPath;
    private int index;
    
    Media audioMedia;
    Media videoMedia;
    
    MediaPlayer audioPlayer;
    MediaPlayer videoPlayer;
    
    MediaView mView;
    
    double vol;

    @Override
    public void start(Stage stage) {
        
        
        Label name = new Label();
        name.setText(Controller.songs.get(index).getName() + " - " + Controller.songs.get(index).getAuthor());
        name.setFont(Font.font(STYLESHEET_CASPIAN, FontWeight.BOLD, 20));
        
        HBox hbox = new HBox();
        hbox.setLayoutX(160);
        hbox.setLayoutY(350);
        hbox.setPrefWidth(450);
        hbox.setAlignment(Pos.CENTER);
        hbox.getChildren().addAll(name);

        try{
            audioPath =  Controller.songs.get(index).getAudioPath();
            videoPath = Controller.songs.get(index).getVideoPath();

            audioMedia = new Media(new File(audioPath).toURI().toString());       
            audioPlayer = new MediaPlayer(audioMedia);
        
            videoMedia = new Media(new File(videoPath).toURI().toString());
            videoPlayer = new MediaPlayer(videoMedia);
            mView = new MediaView(videoPlayer);
         
            audioPlayer.setAutoPlay(true);
            videoPlayer.setAutoPlay(true);
            audioPlayer.setVolume(0.2);
        
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Files could not be loaded");
        }
        
        mView.setLayoutX(165);
        mView.setLayoutY(30);
        mView.setFitWidth(475);
        mView.setFitHeight(425);

        audioPlayer.setOnEndOfMedia(new Runnable(){
            @Override
            public void run() {
                changeSong(index, name);
            }
            
        });
        
        //Play-Pause button
        Button playPause = new Button();
        playPause.setText("Pause");
        playPause.setPrefSize(75, 35);
        playPause.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                
                if(audioPlayer.getStatus() == MediaPlayer.Status.PLAYING){
                    Controller.pauseBtn(audioPlayer, videoPlayer);
                    playPause.setText("Play");
                    
                }else{
                    /*Com que els videos tarden més en carregar que els audios, si li dones a play/pause es crea un desfàs on el video va un poc més 
                    *més atrasat que l'audio, que es fa més gran a mesura que més pitjes es botó
                    *Per evitar-ho, la classe duration ens permet agafar els segons exactes a n'es que s'ha aturat la cançó (en aquest cas d'audio perquè
                    *és el que va per davant), i amb el mètode seek podem posar el video als segons exactes que s'han agafat prèviament*/
                    Duration duration = audioPlayer.getCurrentTime();
                    videoPlayer.seek(duration);
                    Controller.playBtn(audioPlayer, videoPlayer);
                    playPause.setText("Pause");
                }
            }
        });
        
        //Stop button
        
        Button stop = new Button();
        stop.setText("Stop");
        stop.setPrefSize(75, 35);
        stop.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                audioPlayer.stop();
                videoPlayer.stop();
                audioPlayer.play();
                videoPlayer.play();
            }
        });
        

        //Mute button
        
        Button mute = new Button();
        mute.setText("Mute");
        mute.setPrefSize(75, 35);
        mute.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                if(!audioPlayer.isMute()){
                    audioPlayer.setMute(true);
                    mute.setText("Unmute");
                }else{
                    audioPlayer.setMute(false);
                    mute.setText("Mute");
                }
            }
        });
        
        //Button forward
        
        Button forward = new Button();
        forward.setText("Forward");
        forward.setPrefSize(75, 35);
        forward.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                if(index < Controller.songs.size() -1){
                    index++;
                    changeSong(index, name);
                } else{
                    index = 0;
                    changeSong(index, name);
                }
            }
        });
        
        //Button backwards

        Button backward = new Button();
        backward.setText("Backward");
        backward.setPrefSize(75, 35);
        backward.setOnAction(new EventHandler<ActionEvent>(){
            @Override 
            public void handle(ActionEvent event) {
                if(index > 0){
                    index--;
                    changeSong(index, name);
                }else{
                    index = Controller.songs.size() -1;
                    changeSong(index, name);
                }
            }
        });
        
        //Button shuffle
        
        Button shuffle = new Button();
        shuffle.setText("Shuffle");
        shuffle.setPrefSize(75, 35);
        shuffle.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                
                int numRandom = (int)(Math.random() * Controller.songs.size()-1);
                
                while(numRandom == index){
                    numRandom = (int)(Math.random() * Controller.songs.size()-1);
                }
                index = numRandom;
                changeSong(index, name);
            }
        });
        
        //Button upVolume
        Button upVolume = new Button();
        upVolume.setText("Vol +");
        upVolume.setPrefSize(75, 35);
        upVolume.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                audioPlayer.setVolume(vol + 0.1);
                vol = audioPlayer.getVolume();
                if(vol > 1){
                    audioPlayer.setVolume(1.0);
                }
            }
        });
        
        Button downVolume = new Button();
        downVolume.setText("Vol -");
        downVolume.setPrefSize(75, 35);
        downVolume.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                audioPlayer.setVolume(vol - 0.1);
                vol = audioPlayer.getVolume();
                if(vol < 0.0){
                    audioPlayer.setVolume(0.0);
                }
            }
        });
        
        Slider timer = new Slider();
        timer.setLayoutX(170);
        timer.setLayoutY(410);
        timer.setPrefWidth(450);
        
        
        audioPlayer.setOnReady(new Runnable(){
            @Override
            public void run() {
                Duration duration = audioMedia.getDuration();
                timer.setMax(duration.toSeconds());
            }
        });
        audioPlayer.currentTimeProperty().addListener(new ChangeListener<Duration>(){
            @Override
            public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) {
                timer.setValue(newValue.toSeconds());
            }
        });
        
        timer.setOnMouseDragged(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                audioPlayer.seek(Duration.seconds(timer.getValue()));
                videoPlayer.seek(Duration.seconds(timer.getValue()));
            }
        });
             
        VBox buttonBox = new VBox(20);
        buttonBox.setLayoutX(40);
        buttonBox.setLayoutY(30);
        buttonBox.getChildren().addAll(playPause, mute, stop, backward, forward, shuffle, downVolume, upVolume);

        Group root = new Group();
        root.getChildren().addAll(mView, buttonBox, timer, hbox);
        
        Scene scene = new Scene(root, 700, 500);
        
        stage.setTitle("Media Player");
        stage.setScene(scene);
        stage.show();
    }
    
    private void changeSong(int index, Label label){
        audioPlayer.stop();
        videoPlayer.stop();
                    
        audioMedia = new Media(new File(Controller.songs.get(index).getAudioPath()).toURI().toString());
        audioPlayer = new MediaPlayer(audioMedia);
                    
        videoMedia = new Media(new File(Controller.songs.get(index).getVideoPath()).toURI().toString());
        videoPlayer = new MediaPlayer(videoMedia);
                    
        mView.setMediaPlayer(videoPlayer);
                                        
        audioPlayer.setAutoPlay(true);
        videoPlayer.setAutoPlay(true);
        audioPlayer.setVolume(0.2);
                    
        label.setText(Controller.songs.get(index).getName() + " - " + Controller.songs.get(index).getAuthor());
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}


