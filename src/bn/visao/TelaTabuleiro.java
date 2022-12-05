package bn.visao;

import java.awt.GridLayout;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import bn.modelo.Tabuleiro;

@SuppressWarnings("serial")
public class TelaTabuleiro extends JPanel {
	public TelaTabuleiro(Tabuleiro tabuleiro) {
		setLayout(new GridLayout(tabuleiro.getQtdLinhas(), tabuleiro.getQtdColunas()));
		
		tabuleiro.paraCada(c -> add(new BotaoCampo(c)));
		tabuleiro.registrarObservador(e -> {
			
			SwingUtilities.invokeLater(()->{
				if(e.isGanhou()) {
					JOptionPane.showMessageDialog(this, "Gannhou!! :)");
					
				}else {
					JOptionPane.showMessageDialog(this, "Perdeu!! :(");
				}
				tabuleiro.reiniciarJogo();
			});
			
		});
	}

}
