package views.header;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controllers.Commands;
import general.HandlerLanguage;
import views.ConstantsGUI;
import views.buttons.JButtonsMenuAndDialogs;

public class JPanelLanguage extends JPanel{

	private static final long serialVersionUID = 1L;
	
	private JLabel spanishLabel, englishLabel;
	
	public JPanelLanguage(ActionListener actionListener) {
		setMinimumSize(new Dimension(250,160));
		setOpaque(false);
		setBorder(BorderFactory.createEmptyBorder(10, 5, 0, 5));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		initComponents(actionListener);
	}

	
	private void initComponents(ActionListener actionListener) {
		spanishLabel = createLabel(HandlerLanguage.languageProperties.getProperty(ConstantsGUI.LANGUAGE_SPANISH));
		add(createPanel(this.spanishLabel,"resources/img/BanderaEspa"+ConstantsGUI.LOWERCASE_ACCENTED_N+"a.png",50,30,actionListener,Commands.CHANGE_SPANISH.toString()));
		if(HandlerLanguage.languageProperties.getProperty(ConstantsGUI.LANGUAGE_ENGLISH).equals("Ingl"+ ConstantsGUI.LOWERCASE_ACCENTED_E +"s"))
			englishLabel = createLabel(HandlerLanguage.languageProperties.getProperty(ConstantsGUI.LANGUAGE_ENGLISH) + "   ");

		else
			englishLabel = createLabel(HandlerLanguage.languageProperties.getProperty(ConstantsGUI.LANGUAGE_ENGLISH) + "  ");
		add(createPanel(this.englishLabel,"resources/img/BanderadelReinoUnido.png",50,30,actionListener,Commands.CHANGE_ENGLISH.toString()));
	}
	
	private JPanel createPanel(JLabel label, String routeImage, int widht, int heigth, ActionListener actionListener, String command) {
		FlowLayout layout = new FlowLayout(FlowLayout.CENTER);
		layout.setHgap(50);
		JPanel panel = new JPanel(layout);
		panel.setOpaque(false);
		panel.add(label);
		JButtonsMenuAndDialogs button = new JButtonsMenuAndDialogs(routeImage, widht, heigth);
		button.addActionListener(actionListener);
		button.setActionCommand(command);
		panel.add(button);
		return panel;
	}
	
	private JLabel createLabel(String text) {
		JLabel label = new JLabel(text);
		label.setFont(new Font("Roboto", Font.BOLD, 14));
		label.setForeground(Color.WHITE);
		return label;
	}
	
	public void changeLanguage() {
		spanishLabel.setText(HandlerLanguage.languageProperties.getProperty(ConstantsGUI.LANGUAGE_SPANISH));
		if(HandlerLanguage.languageProperties.getProperty(ConstantsGUI.LANGUAGE_ENGLISH).equals("Ingl"+ ConstantsGUI.LOWERCASE_ACCENTED_E +"s"))
			englishLabel.setText(HandlerLanguage.languageProperties.getProperty(ConstantsGUI.LANGUAGE_ENGLISH) + "   ");
		else
			englishLabel.setText(HandlerLanguage.languageProperties.getProperty(ConstantsGUI.LANGUAGE_ENGLISH) + "  ");
			
	}

}
