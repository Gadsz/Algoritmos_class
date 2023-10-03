import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class Musica {
    public static void main(String[] args) {
        try {
            File arquivoAudio = new File("C:\\Users\\aluno\\SALVE_AQUI\\Overworld_2.wav");
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(arquivoAudio);

            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();

            Thread.sleep(clip.getMicrosecondLength() / 1000);

            clip.close();
            audioInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}