package reproductor;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Label;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

public class Controller {
    
    static List<Song> songs = new ArrayList<>();

    public Controller() {
        
        songs.add(new Song(1, "Dark Necessities", "Red Hot Chilli Peppers", ".\\src\\media\\Audio\\RedHotChilliPeppers_DarkNecessities.mp3", ".\\src\\media\\Video\\RedHotChilliPeppers_DarkNecessities_Final.mp4"));
        songs.add(new Song(2, "Un minut estroboscopica", "Antonia Font", ".\\src\\media\\Audio\\AntoniaFont_UnMinutEstroboscopica.wav", ".\\src\\media\\Video\\AntoniaFont_UnMinutEstroboscopica_Final.mp4"));
        songs.add(new Song(3, "Fake Plastic Trees", "Radiohead", ".\\src\\media\\Audio\\Radiohead_FakePlasticTrees.mp3", ".\\src\\media\\Video\\Radiohead_FakePlasticTrees_Final.mp4"));
        songs.add(new Song(4, "Bullets", "Archive", ".\\src\\media\\Audio\\Archive_Bullets.aiff", ".\\src\\media\\Video\\Archive_Bullets_Final.mp4"));  
    }
    
        public static void playBtn(MediaPlayer audioPlayer, MediaPlayer videoPlayer){
            audioPlayer.play();
            videoPlayer.play();
        
        }
    
        public static void pauseBtn(MediaPlayer audioPlayer, MediaPlayer videoPlayer){
            audioPlayer.pause();
            videoPlayer.pause();
        
        }

}

