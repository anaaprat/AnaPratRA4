package views;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import models.AI;
import services.IAService;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class Principal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnUndo;
	private JButton btnAdd;
	private JLabel lblWelcome;
	private JButton btnUpdate;
	private JButton btnVisualize;
	private JButton btnDelete;
	private List<AI> ias;

	public Principal() {

		super("Artificial Intelligence");

		setIconImage(Toolkit.getDefaultToolkit().getImage("images/logoPetit.png"));
		setForeground(new Color(170, 218, 135));
		setBackground(new Color(255, 255, 255));

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 496, 395);
		setLocationRelativeTo(null);

		contentPane = new JPanel();
		contentPane.setBackground(new Color(201, 232, 179));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		Manejador manejador = new Manejador();

		btnUndo = new JButton("");
		btnUndo.addActionListener(manejador);
		btnUndo.setFocusPainted(false);
		btnUndo.setBackground(new Color(201, 232, 179));
		btnUndo.setBorder(null);
		btnUndo.setIcon(new ImageIcon("images/undo.png"));
		btnUndo.setBounds(10, 10, 44, 44);
		contentPane.add(btnUndo);

		lblWelcome = new JLabel("Welcome! What do you need?");
		lblWelcome.setForeground(new Color(255, 255, 255));
		lblWelcome.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 21));
		lblWelcome.setBounds(54, 64, 284, 45);
		contentPane.add(lblWelcome);

		btnAdd = new JButton("Add new AI");
		btnAdd.addActionListener(manejador);
		btnAdd.setBounds(88, 125, 104, 59);
		btnAdd.setToolTipText("Click to add a new AI to the DateBase");
		btnAdd.setBorder(null);
		btnAdd.setBackground(new Color(255, 255, 255));
		btnAdd.setForeground(new Color(74, 149, 0));
		btnAdd.setFont(new Font("Arial", Font.BOLD, 13));
		contentPane.add(btnAdd);

		btnUpdate = new JButton("Update AI");
		btnUpdate.addActionListener(manejador);
		btnUpdate.setToolTipText("Click to update an existing AI");
		btnUpdate.setForeground(new Color(74, 149, 0));
		btnUpdate.setFont(new Font("Arial", Font.BOLD, 13));
		btnUpdate.setBorder(null);
		btnUpdate.setBackground(Color.WHITE);
		btnUpdate.setBounds(285, 125, 104, 59);
		contentPane.add(btnUpdate);

		btnVisualize = new JButton("Visualize AI");
		btnVisualize.addActionListener(manejador);
		btnVisualize.setToolTipText("Click to get info about an AI");
		btnVisualize.setForeground(new Color(74, 149, 0));
		btnVisualize.setFont(new Font("Arial", Font.BOLD, 13));
		btnVisualize.setBorder(null);
		btnVisualize.setBackground(Color.WHITE);
		btnVisualize.setBounds(88, 226, 104, 59);
		contentPane.add(btnVisualize);

		btnDelete = new JButton("Delete AI");
		btnDelete.addActionListener(manejador);
		btnDelete.setToolTipText("Click to delete an AI");
		btnDelete.setForeground(new Color(74, 149, 0));
		btnDelete.setFont(new Font("Arial", Font.BOLD, 13));
		btnDelete.setBorder(null);
		btnDelete.setBackground(Color.WHITE);
		btnDelete.setBounds(285, 226, 104, 59);
		contentPane.add(btnDelete);

	}

	private class Manejador implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			Object o = e.getSource();
			dispose();

			if (o == btnUndo) {
				new IniPage().setVisible(true);
			} else if (o == btnAdd) {

				IAService mongo = new IAService("RA4ProyectoAnaPrat");

				ias = mongo.getAllAis("ArtificialIntelligence");

				mongo.close();

				int lastCode = ias.getLast().getCode();

				new AddAI(lastCode).setVisible(true);

			} else if (o == btnUpdate) {
				new UpdateAISelect().setVisible(true);

			} else if (o == btnVisualize) {
				new VisualizeAI().setVisible(true);

			} else if (o == btnDelete) {
				new DeleteAI().setVisible(true);

			}
		}
	}
}
