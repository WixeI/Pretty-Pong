

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class CriarPerfil implements State {
	private boolean teste = false;

	private int opcao = 0;
	private Image fundo = new Image("file:C:src/imagens/fimDeJogo/Fundo.png");
	public CriarPerfil(){
		sortHighscore();
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
			criarPerfil();
		}
		else if (opcao == 1){
			apagarPerfil();
		}
		else if (opcao == 2){
			GameStateManager.states[0] = new Menu();
			GameStateManager.stateAtual = 0;
		}
	}

	public void nega(){
		GameStateManager.states[0] = new Menu();
		GameStateManager.stateAtual = 0;
	}

	public void criarPerfil(){
		String nome = "";
		Scanner scan = new Scanner(System.in);
		nome = scan.nextLine();

		for (int cont = 0; cont < Central.getLp().size(); cont++){
			if (Central.getLp().get(cont).getNome().contains(nome)){
				System.out.println("O nome já existe!");
				return;
			}
		}

		//SE DER OUTOFBOUNDS EXCEPTION, FOI POR CAUSA DO '/n'. DAÍ É SÓ SUBIR O '4' PRA '5'
		if (nome != null && nome.length() < 4){
			Central.getLp().add(new Perfil(nome));
			System.out.println("Perfil criado com sucesso!");
		}
		else if (nome.length() >= 4){
			System.out.println("O nome deve ter apenas 3 letras!");
		}

	}

	public void apagarPerfil(){
		if (Central.getLp().size() < 3){
			System.out.println("Você deve ter pelo menos 2 perfis!");
			return;
		}

		String nome = "";
		Scanner scan = new Scanner(System.in);
		nome = scan.nextLine();

		try{
		for (int cont = 0; cont < Central.getLp().size(); cont++){
			if (Central.getLp().get(cont).getNome().contains(nome)){
				Central.getLp().remove(cont);
				System.out.println("Perfil apagado com sucesso!");
				break;
			}
		}
		} catch(NoSuchElementException e){
			System.out.println("O perfil não existe!");
		}

		/*scan.nextLine();
		scan.close();*/
	}


	//            												 REFERENTES AO DESENHO

	private void drawFontes(GraphicsContext gc) {

		gc.setFill(Color.ROYALBLUE);

		gc.setFont(Font.loadFont(Central.padraoFont, 20));

		if (opcao == 0)
			gc.setFill(Color.SEAGREEN);
		else
			gc.setFill(Color.ROYALBLUE);

		gc.fillText("CREATE PROFILE", 60, 200);

		if (opcao == 1)
			gc.setFill(Color.SEAGREEN);
		else
			gc.setFill(Color.ROYALBLUE);
		gc.fillText("DELETE PROFILE", 60, 255);

		if (opcao == 2)
			gc.setFill(Color.SEAGREEN);
		else
			gc.setFill(Color.ROYALBLUE);
		gc.fillText("MAIN MENU", 60, 310);

		drawHighscores(gc);

	}

	public void drawHighscores(GraphicsContext gc){

		gc.setFont(Font.loadFont(Central.highscoreFont, 40));
		gc.setFill(Color.ROYALBLUE);

		gc.fillText("PROFILES", 500, 70);

		gc.setFont(Font.loadFont(Central.padraoFont, 20));
		gc.setFill(Color.ROYALBLUE);

	    for(int cont = 0; cont < Central.getLp().size(); cont++){
	    	gc.fillText(Central.getLp().get(cont).getNome(), 550, 120 + 50*cont);
	    	gc.fillText(Integer.toString(Central.getLp().get(cont).getVitorias()), 700, 120 + 50*cont);
	    }


	}

	public void sortHighscore(){
		ArrayList<Perfil> tempLP= new ArrayList<Perfil>();

		int contMaior = 0;


		for(int cont2 = 0; cont2 < Central.getLp().size(); cont2++){
			for(int cont = 1; cont < Central.getLp().size(); cont++){
				if ( Central.getLp().get(contMaior).getVitorias() < Central.getLp().get(cont).getVitorias()){

					contMaior = cont;
				}

			}

			tempLP.add(Central.getLp().get(contMaior));
			Central.getLp().remove(contMaior);
			contMaior = 0;
		}

		for(int cont = 0; cont < tempLP.size(); cont++){
			Central.getLp().add(tempLP.get(cont));
		}

	}


}
