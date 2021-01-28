
public class Itens {

	public boolean itemAtivo = false;
	public int frames = 0;
	public int vXInicialBola = 0;

	public Itens(Bola bola, Barreira bar, Barreira bar2){
		vXInicialBola = bola.getvX();

	}

	public void update(){

	}

	public void draw(){

	}

	public void spawn(){

	}



	// -------------------------------------------------------- ITENS -----------------------------------------------

	public void acelerador(Bola bola){

		if (itemAtivo == true){
			bola.setvX(2);
			frames++;

			if (frames == 300){
				frames = 0;
				itemAtivo = false;
				bola.setvX(vXInicialBola);
			}

		}

	}

	public void bigRaco(){

	}

	public void congelador(){

	}

	public void superMira(){


	}
}
