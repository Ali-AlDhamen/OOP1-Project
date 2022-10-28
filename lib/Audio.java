package lib;

import java.io.File;

import javax.sound.sampled.*;

public class Audio
{

    public static void playAudio(String fileName)
    {
        File file = new File(fileName);

        try
        {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        }
        catch (Exception e)
        {
            System.out.println("Couldn't Find The File");
        }

    }

}