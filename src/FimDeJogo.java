import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class FimDeJogo implements State {
	private boolean teste = false;

	private int opcao = 0;
	private Image fundo = new Image("file:C:src/imagens/fimDeJogo/Fundo.png");
	public FimDeJogo(){

	}

	@Override
	public void update() {
		if (teste == false){
			Musica.playMusic();
			teste = true;
		}
		controlador();
	}

	@Override
	public void draw(GraphicsContext gc) {
		gc.drawImage(fundo, 0, 0);
		this.drawFimDeJogo(gc);

	}

	// -------------------------------------------------------------------------------------------------------------------------------------

	//            												 REFERENTES AO UPDATE
	//				REFERENTE ÀS TECLAS
	private void controlador(){
		if (Handler.input.contains("UP") && Handler.press == true){
			if (opcao == 0){
				//DAR A VOLTA
				opcao = 2;
			}
			else
				opcao = opcao - 1;


		}

		if (Handler.input.contains("DOWN") && Handler.press == true){
			if (opcao == 2){
				//DAR A VOLTA
				opcao = 0;
			}
			else
				opcao = opcao + 1;
		}

		if (Handler.input.contains("Z") && Handler.press == true){
			// CONFIRMA
			confirma();
		}

		if (Handler.input.contains("X") && Handler.press == true){
			// NEGA/RETORNA
			nega();
		}


	}

	public void confirma(){
		if (opcao == 0){
			// TEM GAMBIARRA AQUI. TU TEM QUE DAR UM JEITO NO CONTADOR DE OBJETOS CRIADOS DAS RAQUETES/BARREIRAS
			// ONDE A GAMBIARRA APARECE: MENU, MULTIPLAYER E FIMDEJOGO
			// NEW MULTIPLAYER SERVE PRA RESETAR O ESTADO. SE NÃO USAR ELE VOLTA PRA COMO ESTAVA. USAR E NÃO USAR, CADA UM TEM SUA UTILIDADE
			Barreira.objetosCriados = 0;
			GameStateManager.states[1] = new Multiplayer(SelectScreen.player1Perfil, SelectScreen.player2Perfil);
			GameStateManager.stateAtual = 1;
		}
		else if (opcao == 1){
			GameStateManager.states[4] = new SelectScreen();
			GameStateManager.stateAtual = 4;
		}
		else if (opcao == 2){
			GameStateManager.states[0] = new Menu();
			GameStateManager.stateAtual = 0;
		}
	}

	public void nega(){


	}

	//            												 REFERENTES AO DESENHO

	private void drawFimDeJogo(GraphicsContext gc) {
		gc.setFill(Color.ROYALBLUE);
		gc.setFont(Font.loadFont(Central.padrao2Font, 60));
		gc.fillText("WINNER" , 290, 100);

		if (Central.score1 > Central.score2)
			gc.fillText("PLAYER 1" , 260, 160);
		else
			gc.fillText("PLAYER 2" , 260, 160);

		gc.setFont(Font.loadFont(Central.padrao2Font, 30));

		if (opcao == 0)
			gc.setFill(Color.SEAGREEN);
		else
			gc.setFill(Color.ROYALBLUE);

		gc.fillText("PLAY AGAIN", 310, 270);

		if (opcao == 1)
			gc.setFill(Color.SEAGREEN);
		else
			gc.setFill(Color.ROYALBLUE);
		gc.fillText("PLAYER SELECT", 285, 320);

		if (opcao == 2)
			gc.setFill(Color.SEAGREEN);
		else
			gc.setFill(Color.ROYALBLUE);
		gc.fillText("MAIN MENU", 310, 370);

	}


}
