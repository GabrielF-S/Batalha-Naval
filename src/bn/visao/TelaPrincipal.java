package bn.visao;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

import bn.modelo.Tabuleiro;

@SuppressWarnings("serial")
public class TelaPrincipal extends JFrame{
 public TelaPrincipal() {
	 	
	 	organizarLayout();
	 	setSize(690, 438);
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
}

private void organizarLayout() {
	setLayout(new BorderLayout());
	Tabuleiro tabuleiro = new Tabuleiro(6, 6, 6);
	
	Display display = new Display();
	display.setPreferredSize(new Dimension(233, 60));
	add(display, BorderLayout.NORTH);
	
	TelaTabuleiro telaTabuleiro = new TelaTabuleiro(tabuleiro);
	add(telaTabuleiro, BorderLayout.CENTER);
	
}

public static void main(String[] args) {
	new TelaPrincipal();
}
}
