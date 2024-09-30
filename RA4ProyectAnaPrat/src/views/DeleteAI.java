package views;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import models.AI;
import services.IAService;

public class DeleteAI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnUndo;
	private List<AI> ias;
	private JTable table;
	private JButton btnDelete;
	private JButton btnCancel;
	private JLabel lblSelectAnAi;
	private int selectedRow;
	private AI selectedAI;
	private List<AI> ais;

	public DeleteAI() {
		super("Delete AI");

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

		IAService mongo = new IAService("RA4ProyectoAnaPrat");

		ias = mongo.getAllAis("ArtificialIntelligence");

		mongo.close();

		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Code");
		model.addColumn("Name");
		model.addColumn("Type");
		model.addColumn("Yar of appearance");

		for (AI ia : ias) {
			model.addRow(new Object[] { ia.getCode(), ia.getName(), ia.getType(), ia.getYearAp() });
		}

		table = new JTable(model);
		JScrollPane scroll = new JScrollPane(table);
		scroll.setBounds(31, 86, 427, 195);
		contentPane.add(scroll);

		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(manejador);
		btnDelete.setToolTipText("Click to delete the selected AI");
		btnDelete.setForeground(new Color(74, 149, 0));
		btnDelete.setFont(new Font("Arial", Font.BOLD, 13));
		btnDelete.setBorder(null);
		btnDelete.setBackground(Color.WHITE);
		btnDelete.setBounds(302, 303, 82, 34);
		contentPane.add(btnDelete);

		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(manejador);
		btnCancel.setToolTipText("Click to cancel");
		btnCancel.setForeground(new Color(74, 149, 0));
		btnCancel.setFont(new Font("Arial", Font.BOLD, 13));
		btnCancel.setBorder(null);
		btnCancel.setBackground(Color.WHITE);
		btnCancel.setBounds(116, 303, 82, 34);
		contentPane.add(btnCancel);

		lblSelectAnAi = new JLabel("Select an AI and press 'delete':");
		lblSelectAnAi.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelectAnAi.setForeground(Color.WHITE);
		lblSelectAnAi.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 21));
		lblSelectAnAi.setBounds(100, 31, 284, 45);
		contentPane.add(lblSelectAnAi);

	}

	private class Manejador implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			selectedRow = table.getSelectedRow();

			IAService mongo = new IAService("RA4ProyectoAnaPrat");

			ais = new ArrayList<>();

			ais = mongo.getAllAis("ArtificialIntelligence");

			for (AI ai : ais) {
				if (ai.getCode() - 1 == selectedRow) {
					selectedAI = ai;
				}
			}
			Object o = e.getSource();

			if (o == btnUndo || o == btnCancel) {
				dispose();
				new Principal().setVisible(true);
			} else if (o == btnDelete) {
				int opcion = JOptionPane.showConfirmDialog(null,
						"Are you sure you want to delete " + selectedAI.getName() + "?", "", JOptionPane.YES_NO_OPTION);
				if (opcion == JOptionPane.YES_OPTION) {
					mongo.deleteAi("ArtificialIntelligence", selectedRow + 1);
					mongo.close();
					JOptionPane.showMessageDialog(null, selectedAI.getName() + " has been deleted");
					dispose();
					new Principal().setVisible(true);

				} else {
					dispose();
					new Principal().setVisible(true);
				}

			}

		}

	}
}
