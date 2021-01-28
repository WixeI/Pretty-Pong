
public class Perfil {
	private String nome = "";
	private int vitorias = 0;

	public Perfil(){

	}

	public Perfil(String nome){
		this.nome = nome;
	}

	public Perfil(String nome, int vit){
		this.nome = nome;
		this.vitorias = vit;

	}

	// GETTERS E SETTERS

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getVitorias() {
		return vitorias;
	}

	public void setVitorias(int vitorias) {
		this.vitorias = vitorias;
	}

}
