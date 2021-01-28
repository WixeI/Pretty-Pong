import javafx.scene.canvas.GraphicsContext;

public class GameStateManager {
	public static int stateAtual = 0;
	public static State states[] = new State[10];

	public GameStateManager(){
		states[0] = new Menu();
		states[1] = new Multiplayer(0,1);
		states[2] = new FimDeJogo();
		states[3] = new CriarPerfil();
		states[4] = new SelectScreen();
	}

	public void update(){
		states[stateAtual].update();
	}

	public void draw(GraphicsContext gc){
		states[stateAtual].draw(gc);
	}

}
