

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class SelectScreen implements State {
	private boolean teste = false;

	private int opcao = 0;
	private int opcao2 = 0;
	private boolean submenu = false;

	public static int player1Perfil = 0;
	public static int player2Perfil = 1;

	private Image fundo = new Image("file:C:src/imagens/fimDeJogo/Fundo.png");
	public SelectScreen(){

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
		drawFontes(gc);
		drawHighscores(gc);

	}

	// -------------------------------------------------------------------------------------------------------------------------------------

	//            												 REFERENTES AO UPDATE
	//				REFERENTE ÀS TECLAS
	private void controlador(){
		if (Handler.input.contains("UP") && Handler.press == true){
			if (submenu == false){
				if (opcao == 0){
					//DAR A VOLTA
					opcao = 2;
				}
				else
					opcao = opcao - 1;
			} else {
				if (opcao2 == 0){
					//DAR A VOLTA
					opcao2 = Central.getLp().size() - 1;
				}
				else
					opcao2 = opcao2 - 1;

			}

		}

		if (Handler.input.contains("DOWN") && Handler.press == true){
			if (submenu == false){
				if (opcao == 2){
					//DAR A VOLTA
					opcao = 0;
				}
				else
					opcao = opcao + 1;
			} else {
				if (opcao2 == Central.getLp().size() - 1){
					//DAR A VOLTA
					opcao2 = 0;
				}
				else
					opcao2 = opcao2 + 1;
			}
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
		if (submenu == false){
			if (opcao == 0){
				submenu = true;
			}
			else if (opcao == 1){
				submenu = true;
			}
			else if (opcao == 2){
				GameStateManager.states[1] = new Multiplayer(player1Perfil, player2Perfil);
				GameStateManager.stateAtual = 1;
			}
		} else {
			if (opcao == 0){
				if (player2Perfil != opcao2)
					player1Perfil = opcao2;
				opcao2 = 0;
				submenu = false;
			}
			else if (opcao == 1){
				if (player1Perfil != opcao2)
					player2Perfil = opcao2;
				opcao2 = 0;
				submenu = false;
			}

		}

	}

	public void nega(){
		if (submenu == false){
			GameStateManager.states[0] = new Menu();
			GameStateManager.stateAtual = 0;
		} else
			submenu = false;
	}

	//            												 REFERENTES AO DESENHO

	private void drawFontes(GraphicsContext gc) {

		gc.setFill(Color.ROYALBLUE);

		gc.setFont(Font.loadFont(Central.padraoFont, 20));

		if (opcao == 0 && submenu == false)
			gc.setFill(Color.SEAGREEN);
		else
			gc.setFill(Color.ROYALBLUE);

		gc.fillText("PLAYER 1", 60, 200);

		if (opcao == 1 && submenu == false)
			gc.setFill(Color.SEAGREEN);
		else
			gc.setFill(Color.ROYALBLUE);
		gc.fillText("PLAYER 2", 60, 255);

		if (opcao == 2 && submenu == false)
			gc.setFill(Color.SEAGREEN);
		else
			gc.setFill(Color.ROYALBLUE);
		gc.fillText("START", 60, 310);

	}

	public void updateHighscores(){

	}

	public void drawHighscores(GraphicsContext gc){

		gc.setFont(Font.loadFont(Central.highscoreFont, 40));
		gc.setFill(Color.ROYALBLUE);

		gc.fillText("PROFILES", 500, 70);


		gc.setFont(Font.loadFont(Central.padraoFont, 20));
		gc.setFill(Color.ROYALBLUE);


	    for(int cont = 0; cont < Central.getLp().size(); cont++){
	    	if (player1Perfil == cont)
	    		gc.setFill(Color.INDIANRED);
	    	if (player2Perfil == cont)
	    		gc.setFill(Color.MEDIUMPURPLE);
	    	if (opcao2 == cont && submenu == true)
	    		gc.setFill(Color.SEAGREEN);

	    	gc.fillText(Central.getLp().get(cont).getNome(), 550, 120 + 50*cont);
	    	gc.fillText(Integer.toString(Central.getLp().get(cont).getVitorias()), 700, 120 + 50*cont);

	    	gc.setFill(Color.DARKRED);
	    }





	}

}
