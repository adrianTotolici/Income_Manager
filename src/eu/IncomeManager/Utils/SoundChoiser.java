package eu.IncomeManager.Utils;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

/**
 * Created by adrian on 26.05.2014.
 */
public class SoundChoiser {

    private static SoundChoiser instance;

    public static SoundChoiser getInstace(){
        if (instance==null){
            instance=new SoundChoiser();
        }
        return instance;
    }

    public  void playSound() {
        URL soundURL = getClass().getResource("/resources/sounds/cash_register_purchase.wav");
        try {
            AudioInputStream audio = AudioSystem.getAudioInputStream(soundURL);
            Clip clip = AudioSystem.getClip();
            clip.open(audio);
            clip.start();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void purchaseItem(){
        URL soundURL = getClass().getResource("/resources/sounds/coin.wav");
        try {
            AudioInputStream audio = AudioSystem.getAudioInputStream(soundURL);
            Clip clip = AudioSystem.getClip();
            clip.open(audio);
            clip.start();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
