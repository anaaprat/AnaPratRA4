package views;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import models.AI;
import services.IAService;

public class VisualizeAI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblSelect;
	private JButton btnUndo;
	private List<AI> ias;
	private JTable table;
	private JButton btnVisualize;
	private int selected;

	public VisualizeAI() {
		super("Visualize AI");

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

		lblSelect = new JLabel("Select an AI and press 'visualize':");
		lblSelect.setForeground(Color.WHITE);
		lblSelect.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 21));
		lblSelect.setBounds(54, 32, 284, 45);
		contentPane.add(lblSelect);

		Manejador manejador = new Manejador();

		btnUndo = new JButton("");
		btnUndo.addActionListener(manejador);
		btnUndo.setFocusPainted(false);
		btnUndo.setBackground(new Color(201, 232, 179));
		btnUndo.setBorder(null);
		btnUndo.setIcon(new ImageIcon("images/undo.png"));
		btnUndo.setBounds(10, 10, 44, 44);
		contentPane.add(btnUndo);

		IAService mongo = new IAService("RA4ProyectoAnaPrat");

		ias = mongo.getAllAis("ArtificialIntelligence");

		mongo.close();

		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Code");
		model.addColumn("Name");

		for (AI ia : ias) {
			model.addRow(new Object[] { ia.getCode(), ia.getName() });
		}

		table = new JTable(model);
		JScrollPane scroll = new JScrollPane(table);
		scroll.setBounds(30, 87, 427, 195);
		contentPane.add(scroll);

		btnVisualize = new JButton("Visualize");
		btnVisualize.addActionListener(manejador);
		btnVisualize.setToolTipText("Click to visualize the select AI");
		btnVisualize.setBorder(null);
		btnVisualize.setBackground(new Color(255, 255, 255));
		btnVisualize.setForeground(new Color(74, 149, 0));
		btnVisualize.setFont(new Font("Arial", Font.BOLD, 13));
		btnVisualize.setBounds(372, 303, 85, 21);
		contentPane.add(btnVisualize);
	}

	private class Manejador implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			Object o = e.getSource();

			dispose();

			if (o == btnUndo) {
				new Principal().setVisible(true);
			} else if (o == btnVisualize) {
				selected = table.getSelectedRow();
				new AIVisual(selected + 1).setVisible(true);
			}

		}

	}
}
