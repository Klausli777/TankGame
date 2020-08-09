package tankrotationexample.readBGM;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


public class SoundPlayer {
    private AudioInputStream soundStream;
    private String path;
    private Clip clip;

    public SoundPlayer(String path){
        this.path = path;
        try{
            soundStream = AudioSystem.getAudioInputStream(SoundPlayer.class.getClassLoader().getResource(path));
            clip = AudioSystem.getClip();
            clip.open(soundStream);
        }
        catch(Exception e){
            System.out.println(e.getMessage() + "\tNo sound documents are found");
        }
    }
    public void play(){
        clip.start();
    }
    public void stop(){
        clip.stop();
    }
}
