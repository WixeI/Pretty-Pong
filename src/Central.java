import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javafx.scene.text.Font;

public class Central {

	public static String highscoreFont = "file:src/fontes/Pixel-Grafiti.ttf";
	public static String padraoFont = "file:src/fontes/Pixeled.ttf";
	public static String padrao2Font = "file:src/fontes/slkscr.ttf";
	
	public static final int ALTURA = 500;
	public static final int COMPRIMENTO = 800;

	public static int score1 = 0, score2 = 0;
	public static int frame = 1;
	public static int segundos = 0;

	private static ArrayList<Perfil> lp = new ArrayList<Perfil>();

	// 0 PARA 1/3 DE CIMA DA TELA. 1 PARA MEIO DA TELA. 2 PARA 1/3 DE BAIXO DA TELA
	public static int bolaDir = 1;

	// MANIPULAÇÃO DE ARQUIVOS

	public static void leitor(String path) throws IOException {

        BufferedReader buffRead = new BufferedReader(new FileReader(path));
        String linha = buffRead.readLine();
        String vitorias = "";
        String nome = "";

        while (true) {
        	if (linha != null) {
        		if (linha.contains("Perfil")){
        			nome = buffRead.readLine();
        			vitorias = buffRead.readLine();
        			lp.add(new Perfil(nome, Integer.parseInt(vitorias)));

        		}


            } else
                break;

        	linha = buffRead.readLine();

        }

        buffRead.close();

	}


    public static void escritor(String path) throws IOException {
        BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path));
        for(int cont = 0; cont < lp.size(); cont++){
        	buffWrite.append("Perfil:" + "\n");
        	buffWrite.append(lp.get(cont).getNome() + "\n");
        	buffWrite.append((lp.get(cont).getVitorias() + "\n"));

        }

        buffWrite.close();
    }

    // GETTERS E SETTERS

    public static ArrayList<Perfil> getLp(){
    	return lp;
    }


}
