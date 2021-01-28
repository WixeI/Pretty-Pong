import javafx.scene.media.AudioClip;

public class Musica {
	private static AudioClip clip = new AudioClip("file:src/audios/musicas/inGameMusic.mp3");

	public static boolean iniciarMusica = false;
	private static boolean mesmaMusica = false;

	public static void playMusic(){
		if (GameStateManager.stateAtual == 0){
			if (clip.getSource() != "file:src/audios/musicas/menuMusic.mp3"){
				clip.stop();
				clip = new AudioClip("file:src/audios/musicas/menuMusic.mp3");
				mesmaMusica = false;
			}
			else
				mesmaMusica = true;
		} else if (GameStateManager.stateAtual == 1){
			if (clip.getSource() != "file:src/audios/musicas/inGameMusic.mp3"){
				clip.stop();
				clip = new AudioClip("file:src/audios/musicas/inGameMusic.mp3");
				mesmaMusica = false;
			}
			else
				mesmaMusica = true;
		} else if (GameStateManager.stateAtual == 2){
			if (clip.getSource() != "file:src/audios/musicas/gameOverMusic.mp3"){
				clip.stop();
				clip = new AudioClip("file:src/audios/musicas/gameOverMusic.mp3");
				mesmaMusica = false;
			}
			else
				mesmaMusica = true;
		} else if (GameStateManager.stateAtual == 3){
			if (clip.getSource() != "file:src/audios/musicas/menuMusic.mp3"){
				clip.stop();
				clip = new AudioClip("file:src/audios/musicas/menuMusic.mp3");
				mesmaMusica = false;
			}
			else
				mesmaMusica = true;
		} else if (GameStateManager.stateAtual == 4){
			if (clip.getSource() != "file:src/audios/musicas/menuMusic.mp3"){
				clip.stop();
				clip = new AudioClip("file:src/audios/musicas/menuMusic.mp3");
				mesmaMusica = false;
			}
			else
				mesmaMusica = true;
		}

		if (mesmaMusica == false){
			clip.setCycleCount(AudioClip.INDEFINITE);
			clip.setVolume(0.2);
			clip.play();
		}

	}

}
