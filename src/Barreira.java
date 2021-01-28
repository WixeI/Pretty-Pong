import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;
import javafx.scene.shape.Rectangle;

public class Barreira {
	public static int objetosCriados = 0;
	private int numeroDoObjeto, velocidade = 8, aceleracao = 1;

	private Image[] sprite = new Image[5];
	private Image[] olhos = new Image[5];
	private Rectangle hitbox = new Rectangle();

	public AudioClip hitSound = new AudioClip("file:src/audios/sfx/oof.mp3");
	public boolean hit = false;
	public int frames = 0;

	public int perfil = 0;

	public Barreira(){
		// 	DEFINE QUAL OBJETO ELE É NA ORDEM DE CRIAÇÃO
		objetosCriados++;
		numeroDoObjeto = objetosCriados;

		// DEFINE O SPRITE
		for(int cont = 0; cont < 1; cont++){
			try{
				sprite[cont] = new Image("file:src/imagens/inGame/Barreira_" + cont + ".png");
			} catch (Exception e){
				System.out.println("Não carregou a imagem da barreira meu broder");
			};
		}

		for(int cont = 0; cont < 4; cont++){
			try{
				olhos[cont] = new Image("file:src/imagens/inGame/Olhos_" + cont + ".png");
			} catch (Exception e){
				System.out.println("Não carregou a imagem da barreira meu broder");
			};
		}

		// DEFINE O TAMANHO (hardcode hihihi)
		hitbox.setHeight(70);
		hitbox.setWidth(20);

		// DEFINE ONDE SERÁ CRIADO NA TELA
		if (numeroDoObjeto == 1){
			// Sim eu estou ciente do crime deste hardcode. Em minha defesa, este código inteiro foi feito em 2 dias.
			hitbox.setX(0);
			hitbox.setY(10);
		}
		else {
			// ....Aqui também..
			hitbox.setX(800-35);
			hitbox.setY(10);
		}

		///DEFINE O VOLUME DE SFX
		hitSound.setVolume(0.2);

	}

	public void update(){
		controlador();
	}

	public void draw(GraphicsContext gc){
		drawCorpo(gc);
		drawEyes(gc);
		closingEyes(gc);
	}


	public void NPC(){

	}

	public void controlador(){
		if (numeroDoObjeto == 1){
			if (Handler.input.contains("W")){
				if (hitbox.getY() > 0)
					hitbox.setY(hitbox.getY() - velocidade);
			}
			else if (Handler.input.contains("S"))
				if (hitbox.getY() + hitbox.getHeight() < Central.ALTURA)
					hitbox.setY(hitbox.getY() + velocidade);

		}

		else if (numeroDoObjeto == 2){
			if (Handler.input.contains("UP")){
				if (hitbox.getY() > 0)
					hitbox.setY(hitbox.getY() - velocidade);
			}
			else if (Handler.input.contains("DOWN")){
					if (hitbox.getY() + hitbox.getHeight() < Central.ALTURA)
						hitbox.setY(hitbox.getY() + velocidade);
			}
		}

	}

	public void drawEyes(GraphicsContext gc){
		if (this.numeroDoObjeto == 1)
			gc.drawImage(olhos[Central.bolaDir], hitbox.getX(), hitbox.getY());
		if (this.numeroDoObjeto == 2)
			gc.drawImage(olhos[Central.bolaDir], getHitbox().getX() - 3 + olhos[0].getWidth(), getHitbox().getY(), -olhos[0].getWidth(), olhos[0].getHeight());
	}

	public void closingEyes(GraphicsContext gc){
		if (hit == true){

			if (this.numeroDoObjeto == 1)
				gc.drawImage(olhos[3], hitbox.getX(), hitbox.getY());
			else{/// O "- 3" É PORQUÊ A HITBOX É 3 PIXELS MENOR QUE A IMAGEM.
				gc.drawImage(olhos[3], getHitbox().getX() - 3 + olhos[0].getWidth(), getHitbox().getY(), -olhos[0].getWidth(), olhos[0].getHeight());
			}

			frames++;

			if (frames == 20){
				frames = 0;
				hit = false;
			}
		}


	}

	public void drawCorpo(GraphicsContext gc){
		if (this.numeroDoObjeto == 1)
			gc.drawImage(sprite[0], hitbox.getX(), hitbox.getY());
		else{/// O "- 3" É PORQUÊ A HITBOX É 3 PIXELS MENOR QUE A IMAGEM.
			gc.drawImage(sprite[0], getHitbox().getX() - 3 + sprite[0].getWidth(), getHitbox().getY(), -sprite[0].getWidth(), sprite[0].getHeight());
		}
	}

	// GETTERS E SETTERS

	public void setSprite(String string, int pos){
		sprite[pos] = new Image(string);
	}

	public Image getSprite(int pos){
		return sprite[pos];
	}

	public int getNumeroDoObjeto() {
		return numeroDoObjeto;
	}

	public Rectangle getHitbox() {
		return hitbox;
	}

	public void setHitbox(Rectangle hitbox) {
		this.hitbox = hitbox;
	}



}
