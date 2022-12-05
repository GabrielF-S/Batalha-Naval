package bn.modelo;

import java.util.ArrayList;
import java.util.List;




public class Campo {
	
	
	private final int coluna;
	private final int linha;
	
	private boolean ocupado = false;
	private boolean aberto = false;
	
	private List<Campo> vizinhos = new ArrayList<Campo>();
	private List<CampoObservadores> observadores = new ArrayList<>();
	
	Campo(int linha, int coluna) {
		this.coluna = coluna;
		this.linha = linha;

	}
	
	
	public void registrarObservador(CampoObservadores observador) {
		observadores.add(observador);
	}
	
	private void notificarObservadores(CampoEvento evento) {
		observadores.stream().forEach( o -> o.eventoOcorreu(this, evento));
	
	}


	boolean adicionarVizinho(Campo vizinho) {
		boolean linhaDiferente = this.linha != vizinho.linha;
		boolean colunaDiferente = this.coluna != vizinho.coluna;
		boolean diagonal = linhaDiferente && colunaDiferente;

		int deltaLinha = Math.abs(this.linha - vizinho.linha);
		int deltaColuna = Math.abs(this.coluna - vizinho.coluna);
		int deltaGeral = deltaColuna + deltaLinha;

		if (deltaGeral == 1 && !diagonal) {
			vizinhos.add(vizinho);
			return true;
		} else if (deltaGeral == 2 && diagonal) {
			vizinhos.add(vizinho);
			return true;
		} else {
			return false;
		}
		
		
	}


	public boolean isOcupado() {
		return ocupado;
	}

	public boolean isAberto() {
		return aberto;
	}


	void ocupar() {
		ocupado = true;
	}

	public void setOcupado(boolean ocupado) {
		this.ocupado = ocupado;
	}
	
	public boolean abrir() {
		if(!aberto) {
			if(ocupado) {
//				notificarObservadores(CampoEvento.ACERTOU);
				setAberto(true);
				return true;
			}
			
			notificarObservadores(CampoEvento.ERROU);
			return false;
			
		}
		return false;
		
	}
	
	
	void setAberto(boolean aberto) {
		this.aberto = aberto;
		
		if (aberto) {
			notificarObservadores(CampoEvento.ABRIR);
		}
	}
	
	
	boolean objetivoAlcancado() {
		boolean afundado = ocupado && aberto;
		boolean agua = !ocupado;
		return afundado || agua ;
	}
	
	void reiniciar() {
		ocupado = false;
		aberto = false;
		notificarObservadores(CampoEvento.REINICIAR);
	}
	

}
