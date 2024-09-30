package views;

import java.awt.Color;
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
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import models.AI;
import services.IAService;
import javax.swing.SwingConstants;

public class AIVisual extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnUndo;
	private JLabel lblName;
	private JLabel lblCode;
	private JTextField txtYear;
	private JLabel lblImage;
	private JTextField txtType;
	private JLabel lblType;
	private JLabel lblYearOApparition;
	private JTextField txtCode;
	private JLabel image;
	private List<AI> ias;
	private AI select = new AI();

	public AIVisual(int code) {
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

		Manejador manejador = new Manejador();

		btnUndo = new JButton("");
		btnUndo.addActionListener(manejador);
		btnUndo.setFocusPainted(false);
		btnUndo.setBackground(new Color(201, 232, 179));
		btnUndo.setBorder(null);
		btnUndo.setIcon(new ImageIcon("images/undo.png"));
		btnUndo.setBounds(10, 10, 44, 44);
		contentPane.add(btnUndo);

		// Conexion
		IAService mongo = new IAService("RA4ProyectoAnaPrat");

		// Paso a la lista
		ias = mongo.getAllAis("ArtificialIntelligence");

		for (AI ia : ias) {
			if (ia.getCode() == code) {
				select = ia;
			}
		}

		lblName = new JLabel();
		lblName.setForeground(new Color(255, 255, 255));
		lblName.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 51));
		lblName.setBounds(97, 38, 280, 42);
		lblName.setText(select.getName());
		contentPane.add(lblName);

		lblCode = new JLabel("Code:");
		lblCode.setForeground(Color.WHITE);
		lblCode.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 21));
		lblCode.setBounds(21, 102, 73, 45);
		contentPane.add(lblCode);

		txtYear = new JTextField();
		txtYear.setHorizontalAlignment(SwingConstants.CENTER);
		txtYear.setEditable(false);
		txtYear.setColumns(10);
		txtYear.setBounds(180, 224, 54, 25);
		txtYear.setText(String.valueOf(select.getYearAp()));
		contentPane.add(txtYear);

		txtType = new JTextField();
		txtType.setHorizontalAlignment(SwingConstants.CENTER);
		txtType.setEditable(false);
		txtType.setColumns(10);
		txtType.setBounds(97, 169, 192, 25);
		txtType.setText(select.getType());
		contentPane.add(txtType);

		lblType = new JLabel("Type:");
		lblType.setForeground(Color.WHITE);
		lblType.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 21));
		lblType.setBounds(21, 157, 73, 45);
		contentPane.add(lblType);

		lblYearOApparition = new JLabel("Year of appearance:");
		lblYearOApparition.setForeground(Color.WHITE);
		lblYearOApparition.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 21));
		lblYearOApparition.setBounds(21, 212, 149, 45);
		contentPane.add(lblYearOApparition);

		txtCode = new JTextField();
		txtCode.setHorizontalAlignment(SwingConstants.CENTER);
		txtCode.setEditable(false);
		txtCode.setColumns(10);
		txtCode.setBounds(97, 114, 44, 25);
		txtCode.setText(String.valueOf(select.getCode()));
		contentPane.add(txtCode);

		lblImage = new JLabel("Image: ");
		lblImage.setForeground(Color.WHITE);
		lblImage.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 21));
		lblImage.setBounds(287, 90, 149, 45);
		contentPane.add(lblImage);

		image = new JLabel("");
		image.setHorizontalAlignment(SwingConstants.CENTER);
		image.setBounds(313, 137, 137, 138);
		image.setIcon(new ImageIcon(select.getImage()));
		contentPane.add(image);

	}

	private class Manejador implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			Object o = e.getSource();

			dispose();

			if (o == btnUndo) {
				new VisualizeAI().setVisible(true);
			}

		}

	}
}
