import view.BeginFrame;
import view.MusicStuff;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {

            new BeginFrame();

            String filepath = "海德薇变奏曲.wav";
            MusicStuff musicObject = new MusicStuff();
            musicObject.playMusic(filepath);

        });
    }
}
