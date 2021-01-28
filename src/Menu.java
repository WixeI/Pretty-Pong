import java.io.IOException;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Menu implements State{
	private boolean teste = false;

	private int opcao = 0;
	private Image fundo = new Image("file:C:src/imagens/menu/Fundo.png");

	public Menu(){

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
		this.drawMenuPrincipal(gc);

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
			GameStateManager.states[4] = new SelectScreen();
			GameStateManager.stateAtual = 4;
		}
		else if (opcao == 1){
			GameStateManager.states[3] = new CriarPerfil();
			GameStateManager.stateAtual = 3;
		}
		else if (opcao == 2){
			try {
				Central.escritor("src/perfil.txt");
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("ihh azedou");
			}
			System.exit(0);
		}
	}

	public void nega(){


	}

	//			REFERENTE AOS UPDATES DE CADA MENU
	public void updateHighscores(){

	}

	public void updateCriarPerfil(){

	}

	public void updateSelecionarPerfil(){

	}


	//           											  REFERENTES AO DESENHO
	public void drawMenuPrincipal(GraphicsContext gc){
		gc.setFill(Color.ROYALBLUE);
		gc.setFont(Font.loadFont(Central.padraoFont, 20));

		if (opcao == 0)
			gc.setFill(Color.SEAGREEN);
		else
			gc.setFill(Color.ROYALBLUE);

		gc.fillText("MULTIPLAYER", 290, 260);

		if (opcao == 1)
			gc.setFill(Color.SEAGREEN);
		else
			gc.setFill(Color.ROYALBLUE);
		gc.fillText("LEADERBOARDS", 280, 325);

		if (opcao == 2)
			gc.setFill(Color.SEAGREEN);
		else
			gc.setFill(Color.ROYALBLUE);
		gc.fillText("QUIT", 360, 390);
	}

	public void drawHighscores(){

	}

	public void drawCriarPerfil(){

	}

	public void drawSelecionarPerfil(){

	}

}
