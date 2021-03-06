package views.header;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

import controllers.Commands;
import general.HandlerLanguage;
import views.ConstantsGUI;

public class JPanelMenuIcons extends JPanel{
	
	public static final int WIDTH_BUTTONS = 90;
	public static final int HEIGTH_BUTTONS = 70;

	private static final long serialVersionUID = 1L;
	
	private JButtonsMenuAndDialogs add, delete, edit, language, tableCultives;
	
	public JPanelMenuIcons(ActionListener actionListener) {
		FlowLayout layout = new FlowLayout(FlowLayout.CENTER);
		setLayout(layout);
		setOpaque(false);
		initComponents(actionListener);
		setVisible(true);
	}
	
	private void initComponents(ActionListener actionListener) {
		addLogo();
		addButtons(actionListener);
	}
	
	private void addLogo() {
		JLabel labelLogo = new JLabel();
		labelLogo.setIcon(ConstantsGUI.convertToIcon("resources/img/logo.png",120,120));
		this.add(labelLogo);
	}
	
	private void addButtons(ActionListener actionListener) {
		add = new JButtonsMenuAndDialogs(HandlerLanguage.languageProperties.getProperty(ConstantsGUI.T_ADD),"resources/img/add.png",WIDTH_BUTTONS,HEIGTH_BUTTONS,Color.WHITE);
		add(add);
		delete = new JButtonsMenuAndDialogs(HandlerLanguage.languageProperties.getProperty(ConstantsGUI.T_DELETE),"resources/img/delete.png",WIDTH_BUTTONS,HEIGTH_BUTTONS,Color.WHITE);
		add(delete);
		edit = new JButtonsMenuAndDialogs(HandlerLanguage.languageProperties.getProperty(ConstantsGUI.T_EDIT),"resources/img/edit.png",WIDTH_BUTTONS,HEIGTH_BUTTONS,Color.WHITE);
		add(edit);
		language = new JButtonsMenuAndDialogs(HandlerLanguage.languageProperties.getProperty(ConstantsGUI.T_LANGUAGE),"resources/img/language.png",WIDTH_BUTTONS,HEIGTH_BUTTONS,Color.WHITE);
		language.addActionListener(actionListener);
		language.setActionCommand(Commands.OPEN_LENGUAGE_DIALOG.toString());
		add(language);
		tableCultives = new JButtonsMenuAndDialogs(HandlerLanguage.languageProperties.getProperty(ConstantsGUI.T_TABLE_CULTIVES),"resources/img/tablaCultivos.png",WIDTH_BUTTONS,HEIGTH_BUTTONS,Color.WHITE);
		tableCultives.addActionListener(actionListener);
		tableCultives.setActionCommand(Commands.TABLE_CULTIVES.toString());
		add(tableCultives);
	}
	
	public void changeLanguage() {
		add.setText(HandlerLanguage.languageProperties.getProperty(ConstantsGUI.T_ADD));
		delete.setText(HandlerLanguage.languageProperties.getProperty(ConstantsGUI.T_DELETE));
		edit.setText(HandlerLanguage.languageProperties.getProperty(ConstantsGUI.T_EDIT));
		language.setText(HandlerLanguage.languageProperties.getProperty(ConstantsGUI.T_LANGUAGE));
		tableCultives.setText(HandlerLanguage.languageProperties.getProperty(ConstantsGUI.T_TABLE_CULTIVES));
	}
	
}
