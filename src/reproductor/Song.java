/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package reproductor;

import javafx.scene.media.MediaPlayer;

public class Song {
    
    private int id;
    private String name;
    private String author;
    private String AudioPath;
    private String VideoPath;

    public Song(int id, String name, String author, String AudioPath, String VideoPath) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.AudioPath = AudioPath;
        this.VideoPath = VideoPath;
    }

    public Song() {
    }

    public int getId(){
        return id;
    }
    
    public void setId(int id){
        this.id = id;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAudioPath() {
        return AudioPath;
    }

    public void setAudioPath(String AudioPath) {
        this.AudioPath = AudioPath;
    }

    public String getVideoPath() {
        return VideoPath;
    }

    public void setVideoPath(String VideoPath) {
        this.VideoPath = VideoPath;
    }

    @Override
    public String toString() {
        return "Song{" + "name=" + name + ", author=" + author + ", AudioPath=" + AudioPath + ", VideoPath=" + VideoPath + '}';
    }
}
