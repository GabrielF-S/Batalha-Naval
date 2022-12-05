package bn.visao;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;

import bn.modelo.Campo;
import bn.modelo.CampoEvento;
import bn.modelo.CampoObservadores;



@SuppressWarnings("serial")
public class BotaoCampo extends JButton implements CampoObservadores, MouseListener {
	
	private Campo campo;
	
	
	public BotaoCampo(Campo campo) {
		this.campo = campo;
		setBackground(Color.CYAN);
		setOpaque(true);
		setText("~~~" );
		setBorder(BorderFactory.createBevelBorder(0));
		
		
		addMouseListener(this);
		campo.registrarObservador(this);
	}
	

	@Override
	public void eventoOcorreu(Campo campo, CampoEvento evento) {
		switch (evento) {
		case ABRIR: {
			
			aplicarEstiloAbrir();
			break;
		}
		
//		case ACERTOU: {
//			
//			aplicarEstilExplodir();
//			break;
//		}
		case ERROU:{
			aplicarEstiloErro();
			break;
		}
		default:
			aplicarEstiloPadrao();
		}
	}
		
	private void aplicarEstiloErro() {
		
		setBackground(Color.CYAN);
		setText("X" );
//		setForeground(Color.WHITE);
		setBorder(BorderFactory.createBevelBorder(0));
	}


	private void aplicarEstiloPadrao() {
		
		setBackground(Color.CYAN);
		setOpaque(true);
		setText("~~~" );
		setBorder(BorderFactory.createBevelBorder(0));
	}


	private void aplicarEstilExplodir() {
		setBackground(Color.GREEN);
		setText("O");
//		setForeground(Color.WHITE);
		
		
	}


	private void aplicarEstiloAbrir() {
		setBorder(BorderFactory.createLineBorder(Color.BLUE));
		
		if(campo.isOcupado()) {
//			System.out.println("Acertou");
			aplicarEstilExplodir();
			return;
			
		}else {
		
			aplicarEstiloErro();
		}
		
	}

	


	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getButton()== 1) {
			campo.abrir();
			
		}
		
	}




	
	
	
	public void mouseClicked(MouseEvent e) {}
	
	public void mouseReleased(MouseEvent e) {}
	
	public void mouseEntered(MouseEvent e) {}
	
	public void mouseExited(MouseEvent e) {}
	
	}


