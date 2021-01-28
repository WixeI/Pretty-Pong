import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;


public class Multiplayer implements State {
	private boolean teste = false;


	private Barreira player1 = new Barreira();
	private Barreira player2 = new Barreira();
	private Bola bola = new Bola();

	private Itens itens = new Itens(bola, player1, player2);
	public Image fundo = new Image("file:C:src/imagens/inGame/Fundo.png");


	public Multiplayer(int perfil, int perfil2){
		player1.perfil = perfil;
		player2.perfil = perfil2;

		Central.score1 = 0;
		Central.score2 = 0;
	}

	@Override
	public void update(){
		itens.acelerador(bola);

		if (teste == false){
			Musica.playMusic();
			teste = true;
		}
    	//COMPORTAMENTO DA BOLA
    	bola.update(player1, player2);
    	updatePontos();

    	//CONTROLA OS JOGADORES
    	player1.update();
    	player2.update();

	}
	@Override
	public void draw(GraphicsContext gc){
		
		gc.drawImage(fundo, 0, 0);

		drawPontos(gc);

		player2.draw(gc);
		player1.draw(gc);

		gc.drawImage(bola.getSprite(), bola.getHitbox().getCenterX() - bola.getHitbox().getRadius(), bola.getHitbox().getCenterY() - bola.getHitbox().getRadius());
	}

	// -------------------------------------------------------------------------------------------------------------------------------------

	private void updatePontos(){
		if (Central.score1 >= 5 || Central.score2 >= 5){
			if (Central.score1 > Central.score2)
				Central.getLp().get(SelectScreen.player1Perfil).setVitorias(Central.getLp().get(SelectScreen.player1Perfil).getVitorias() + 1);
			else
				Central.getLp().get(SelectScreen.player2Perfil).setVitorias(Central.getLp().get(SelectScreen.player2Perfil).getVitorias() + 1);

			// TEM GAMBIARRA AQUI. TU TEM QUE DAR UM JEITO NO CONTADOR DE OBJETOS CRIADOS DAS RAQUETES/BARREIRAS
			// ONDE A GAMBIARRA APARECE: MENU, MULTIPLAYER E FIMDEJOGO
			// NEW MULTIPLAYER SERVE PRA RESETAR O ESTADO. SE NÃO USAR ELE VOLTA PRA COMO ESTAVA. USAR E NÃO USAR, CADA UM TEM SUA UTILIDADE
			Barreira.objetosCriados = 0;
			GameStateManager.states[2] = new FimDeJogo();
			GameStateManager.stateAtual = 2;

		} else {
			if (bola.getHitbox().getCenterX() + bola.getHitbox().getRadius() > Central.COMPRIMENTO){
				Central.score1++;

				bola.spawn();


			} else if (bola.getHitbox().getCenterX() - bola.getHitbox().getRadius() < 0){
				Central.score2++;

				bola.spawn();


			}

		}

	}

	public void drawPontos(GraphicsContext gc){
		gc.setFill(Color.ROYALBLUE);
		gc.setFont(Font.font("Verdana", 40));

		gc.fillText(String.valueOf(Central.score1), 200, 40);
		gc.fillText(String.valueOf(Central.score2), 600, 40);
	}


}
