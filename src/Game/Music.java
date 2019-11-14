/*
package Game;

import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.IOException;
import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;

public class Music {

    public void playMusic() {
        AudioPlayer MPlayer = AudioPlayer.player;
        AudioStream BGMusic;
        AudioData MusicData;
        ContinuousAudioDataStream AudioLoop = null;

        try {
            BGMusic = new AudioStream(this.getClass().getResourceAsStream("/resources/background.wav"));
            MusicData = BGMusic.getData();
            AudioLoop = new ContinuousAudioDataStream(MusicData);

        } catch (IOException e) {
            System.out.println("File not found");
        }
        MPlayer.start(AudioLoop);
    }
}*/
