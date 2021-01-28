import java.util.Random;
import javafx.scene.image.Image;
import javafx.scene.shape.Circle;


public class Bola {
	public static int objetosCriados = 0;
	private int numeroDoObjeto, vY, vX = 5;
	private Image sprite;
	private Circle hitbox = new Circle();

	public Bola(){

		// DEFINE O SPRITE
		try{
		sprite = new Image("file:C:src/imagens/inGame/Bola_1.png");
		} catch (Exception e){
			System.out.println("Não carregou a imagem da barreira meu broder");
		};

		hitbox.setRadius(8);
		this.spawn();

	}

	public void spawn(){
		// VELOCIDADE VERTICAL MÁXIMA: 20
		int quadrante = 1;
		hitbox.setCenterX(400);
		hitbox.setCenterY(250);
		Random rand = new Random();

		// DEFINE SE A BOLA COMEÇA INDO PRA CIMA OU PRA BAIXO
		if (rand.nextInt(2) == 0)
			quadrante = -1;
		else
			quadrante = 1;

		// DEFINE A ANGULAÇÃO DA BOLA
		vY = 6*quadrante;

		// DEFINE SE A BOLA COMEÇA PARA A ESQUERDA OU PARA A DIREITA
		if (rand.nextInt(2) == 0)
			quadrante = -1;
		else
			quadrante = 1;

		vX = vX *(quadrante);


	}

	public void update(Barreira bar, Barreira bar2){
		updateDir();

		colisaoComBarreiras(bar);
		colisaoComBarreiras(bar2);

		// DETECÇÃO DE PAREDE
		if (hitbox.getCenterY() + hitbox.getRadius() > Central.ALTURA || hitbox.getCenterY() - hitbox.getRadius() < 0)
			vY = vY *(-1);

		hitbox.setCenterX(hitbox.getCenterX() + vX);
		hitbox.setCenterY(hitbox.getCenterY() + vY);

	}

	private void colisaoComBarreiras(Barreira bar){
		// COLISÃO COM BARREIRAS

			// ESTE IF CHECA SE A BOLA ESTÁ EM ALGUMA POSIÇÃO Y CORRESPONDENTE À DA BARREIRA
			if (hitbox.getCenterY() + hitbox.getRadius() > bar.getHitbox().getY() &&
				hitbox.getCenterY() - hitbox.getRadius() < bar.getHitbox().getY() + bar.getHitbox().getHeight())
			{	// ESTE IF DEFINE QUAL FACE DA BARREIRA SERÁ USADA PARA CHECAGEM DE COLISÃO
				if (bar.getNumeroDoObjeto() == 1){
					// ESTE IF CHECA SE A BOLA ESTÁ NO MESMO "X" DA BARREIRA. SE SIM, INVERTE-SE O VETOR VELOCIDADE
					if (hitbox.getCenterX() - hitbox.getRadius() < bar.getHitbox().getX() + bar.getHitbox().getWidth())
					{
						bar.hit = true;
						bar.hitSound.play();
						vX = vX *(-1);

					}
				} else if (bar.getNumeroDoObjeto() == 2){
					// ESTE IF CHECA SE A BOLA ESTÁ NO MESMO "X" DA BARREIRA. SE SIM, INVERTE-SE O VETOR VELOCIDADE
					if (hitbox.getCenterX() + hitbox.getRadius() > bar.getHitbox().getX())
					{
						bar.hit = true;
						bar.hitSound.play();
						vX = vX *(-1);

					}

				}

			}

	}
	public void updateDir(){
		if (hitbox.getCenterY() < Central.ALTURA / 3)
			Central.bolaDir = 0;
		else if (hitbox.getCenterY() > 2 * Central.ALTURA / 3)
			Central.bolaDir = 2;
		else
			Central.bolaDir = 1;
	}

	// GETTERS E SETTERS

	public int getNumeroDoObjeto() {
		return numeroDoObjeto;
	}

	public void setNumeroDoObjeto(int numeroDoObjeto) {
		this.numeroDoObjeto = numeroDoObjeto;
	}

	public Image getSprite() {
		return sprite;
	}

	public void setSprite(Image sprite) {
		this.sprite = sprite;
	}

	public int getvY() {
		return vY;
	}

	public int getvX() {
		return vX;
	}

	public void setvX(int vX) {
		this.vX = vX;
	}

	public Circle getHitbox() {
		return hitbox;
	}
}
