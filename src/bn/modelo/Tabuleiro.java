package bn.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class Tabuleiro  implements CampoObservadores{

	private final int qtdLinhas;
	private final int qtdColunas;
	private final int qtdSubmarinos;
	
	private final List<Campo> campos = new ArrayList<Campo>();
	private final List<Consumer<ResultadoEvento>> observadores = new ArrayList<>();
	
	public Tabuleiro(int qtdLinhas, int qtdColunas, int qtdSubmarinos) {
		this.qtdLinhas = qtdLinhas;
		this.qtdColunas = qtdColunas;
		this.qtdSubmarinos = qtdSubmarinos;
		
		gerarCampo();
		adicionarVizinhos();
		sortearSubmarinos();
	}
	

	public void paraCada(Consumer<Campo> funcao) {
		campos.forEach(funcao);
	}
	public void registrarObservador(Consumer<ResultadoEvento> observador) {
		observadores.add(observador);
	}
	
	public void notificarObservador(boolean resultado) {
		observadores.stream().forEach(o -> o.accept(new ResultadoEvento(resultado)));
	}
	
	private void gerarCampo() {
		for (int l = 0; l < qtdLinhas; l++) {
			for (int c = 0; c < qtdColunas; c++) {
				Campo campo = new Campo(l, c);
				campo.registrarObservador(this);
				campos.add(campo);
			}

		}

	}
	private void adicionarVizinhos() {
		

			for (Campo campo1 : campos) {
				for (Campo campo2 : campos) {
					campo1.adicionarVizinho(campo2);

				}
			}

		}
	
	public int getQtdLinhas() {
		return qtdLinhas;
	}
	public int getQtdColunas() {
		return qtdColunas;
	}
		
	private void sortearSubmarinos() {
		long submarinosAlocados = 0;
		
		Predicate<Campo> nSub = c -> c.isOcupado();

		do {

			int randomNum = (int) (Math.random() * campos.size());
			campos.get(randomNum).ocupar();
			submarinosAlocados = campos.stream().filter(nSub).count();

		} while (submarinosAlocados < qtdSubmarinos);
	
}

	
	public boolean objetivAlcancado() {
		
		return campos.stream().allMatch(c -> c.objetivoAlcancado());
		
	}
	
	public void reiniciarJogo() {
		
		campos.stream().forEach(c -> c.reiniciar());
		sortearSubmarinos();
		
	}
	
	

	@Override
	public void eventoOcorreu(Campo campo, CampoEvento evento) {
		if(evento== CampoEvento.ABRIR) {
			if(objetivAlcancado()) {
				notificarObservador(true);
			}
			
			
		}
		
	}
}
