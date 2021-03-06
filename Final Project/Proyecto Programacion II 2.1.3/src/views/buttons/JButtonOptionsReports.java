package views.buttons;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import views.ConstantsGUI;

public class JButtonOptionsReports extends JButton{

	private static final long serialVersionUID = 1L;
	public static final int WIDHT_AND_HEIGHT_LOGO = 70;

	public JButtonOptionsReports(String text, char logo) {
//		setPreferredSize(new Dimension(50,50));
		setText(ConstantsGUI.HTML_TAG_CENTER+text);
		setIconButton(logo);
		setBorder(BorderFactory.createEmptyBorder(20, 20, 5, 20));
		setBackground(Color.WHITE);
		setFont(new Font("Roboto", Font.BOLD, 20));
		setForeground(ConstantsGUI.COLOR_PRESENTATION);
		setHorizontalTextPosition(SwingConstants.CENTER);
		setVerticalTextPosition(SwingConstants.BOTTOM);
		setBorderPainted(false);
		setOpaque(false);
		setFocusable(false);
		setCursor(new Cursor(Cursor.HAND_CURSOR));
	}
	
	private void setIconButton(char logo) {
		switch (logo) {
		case 'R':
			setIcon(ConstantsGUI.convertToIcon("resources/img/diagramaTorta.png", WIDHT_AND_HEIGHT_LOGO, WIDHT_AND_HEIGHT_LOGO));
			break;
		case 'B':
			setIcon(ConstantsGUI.convertToIcon("resources/img/diagramaBarras.png", WIDHT_AND_HEIGHT_LOGO, WIDHT_AND_HEIGHT_LOGO));
			break;
		case 'T':
			setIcon(ConstantsGUI.convertToIcon("resources/img/tablaBoton.png", WIDHT_AND_HEIGHT_LOGO, WIDHT_AND_HEIGHT_LOGO));
			break;
		default:
			setIcon(ConstantsGUI.convertToIcon("resources/img/diagramaPuntos.png", WIDHT_AND_HEIGHT_LOGO, WIDHT_AND_HEIGHT_LOGO));
			break;
		
		}
	}
	
	public void setNewText(String text) {
		this.setText(ConstantsGUI.HTML_TAG_CENTER+text);
	}
	
	protected void paintComponent(Graphics g) {
		Dimension arcs = new Dimension(20,20);
		int width = getWidth();
		int height = getHeight();
		Graphics2D graphics = (Graphics2D) g;
		graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		graphics.setColor(getBackground());
		graphics.fillRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height);
		graphics.setColor(this.getBackground());
		graphics.drawRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height);
		super.paintComponent(g);
	}
}
