import java.io.IOException;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * 						PAREI O CÓDIGO NA FUNÇÃO "COLISÃO" DA CLASSE "BOLA". TAVA VENDO COMO FAZER COLISÃO ENTRE ELA E AS BARREIRAS.
 *
 */

public class Jogo extends Application {

	public GameStateManager gsm = new GameStateManager();

	public static void main(String args[]){

		launch(args);

	}

	@Override
	public void start(Stage janela) throws Exception {
		//                                                     FUNCIONAMENTO DA JANELA
		Pane raiz = new Pane();
		Scene cena = new Scene(raiz);
		Canvas canvas = new Canvas (Central.COMPRIMENTO, Central.ALTURA);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		raiz.getChildren().add(canvas);

		janela.setTitle("Pong Ultimate");
		janela.setWidth(Central.COMPRIMENTO);
		janela.setHeight(Central.ALTURA + 30);
		janela.setResizable(false);
		janela.show();
		janela.setScene(cena);

		Handler handler = new Handler(cena);

		try {
			Central.leitor("src/perfil.txt");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("ihh azedou");
		}

		//                              	                      LOOP DE ANIMAÇÃO

	    final long startNanoTime = System.nanoTime();
	    //RODA A 60 FRAMES POR SEGUNDO
	    new AnimationTimer()
	    {

	        public void handle(long currentNanoTime)
	        {

	        	gc.clearRect(0, 0, 800, 500);

	        	gsm.update();
	        	gsm.draw(gc);

	        	// LIDA COM TECLAS PRESSIONADAS
	        	if (Handler.press == true)
	        		Handler.press = false;

	            // USADO PARA CONTAR OS FRAMES
	            Central.frame++;
	            if (Central.frame == 61) Central.frame = 0;
	        }
	    }.start();

	}


}
