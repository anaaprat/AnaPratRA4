package views;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import models.AI;
import services.IAService;

public class AddAI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblCode;
	private JLabel lblName;
	private JLabel lblType;
	private JLabel lblYear;
	private JButton btnUndo;
	private JLabel lblImage;
	private JTextField txtCode;
	private JTextField txtName;
	private JTextField txtType;
	private JButton btnImagen;
	private String ruta;
	private String[] arrayYears;
	private JComboBox comboBoxYear;
	private JButton btnCancel;
	private JButton btnAdd;

	public AddAI(int code) {
		super("Add new Artificial Intelligence");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 496, 395);

		setIconImage(Toolkit.getDefaultToolkit().getImage("images/logoPetit.png"));
		setForeground(new Color(170, 218, 135));
		setBackground(new Color(255, 255, 255));

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
		btnUndo.setBounds(0, 10, 42, 34);
		contentPane.add(btnUndo);

		lblCode = new JLabel("AI Code:");
		lblCode.setForeground(Color.WHITE);
		lblCode.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 21));
		lblCode.setBounds(21, 54, 73, 45);
		contentPane.add(lblCode);

		lblName = new JLabel("AI Name: ");
		lblName.setForeground(Color.WHITE);
		lblName.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 21));
		lblName.setBounds(21, 101, 73, 45);
		contentPane.add(lblName);

		lblType = new JLabel("Type:");
		lblType.setForeground(Color.WHITE);
		lblType.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 21));
		lblType.setBounds(21, 148, 73, 45);
		contentPane.add(lblType);

		lblYear = new JLabel("Year of appearance:");
		lblYear.setForeground(Color.WHITE);
		lblYear.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 21));
		lblYear.setBounds(21, 195, 149, 45);
		contentPane.add(lblYear);

		lblImage = new JLabel("Image: ");
		lblImage.setForeground(Color.WHITE);
		lblImage.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 21));
		lblImage.setBounds(287, 27, 149, 45);
		contentPane.add(lblImage);

		txtCode = new JTextField();
		txtCode.setEditable(false);
		txtCode.setText(String.valueOf(code + 1));
		txtCode.setBounds(104, 66, 128, 25);
		contentPane.add(txtCode);
		txtCode.setColumns(10);

		txtName = new JTextField();
		txtName.setColumns(10);
		txtName.setBounds(104, 113, 128, 25);
		contentPane.add(txtName);

		txtType = new JTextField();
		txtType.setColumns(10);
		txtType.setBounds(71, 160, 128, 25);
		contentPane.add(txtType);

		btnImagen = new JButton("");
		btnImagen.addActionListener(manejador);
		btnImagen.setIcon(new ImageIcon("images/add.png"));
		btnImagen.setBackground(Color.WHITE);
		btnImagen.setBounds(287, 68, 139, 139);
		contentPane.add(btnImagen);

		// La primera Integligencia se creó en 1943, por lo que los años posibles están
		// en el rango 1943-2024

		arrayYears = new String[82];

		int firstY = 1943;

		for (int i = 0; i < arrayYears.length; i++) {
			arrayYears[i] = String.valueOf(firstY++);
		}

		arrayYears.toString();
		comboBoxYear = new JComboBox(arrayYears);
		comboBoxYear.setEditable(true);
		comboBoxYear.setMaximumRowCount(5);
		comboBoxYear.setBounds(185, 209, 82, 21);
		contentPane.add(comboBoxYear);

		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(manejador);
		btnCancel.setToolTipText("Click to cancel");
		btnCancel.setForeground(new Color(74, 149, 0));
		btnCancel.setFont(new Font("Arial", Font.BOLD, 13));
		btnCancel.setBorder(null);
		btnCancel.setBackground(Color.WHITE);
		btnCancel.setBounds(113, 271, 86, 45);
		contentPane.add(btnCancel);

		btnAdd = new JButton("Add");
		btnAdd.addActionListener(manejador);
		btnAdd.setToolTipText("Click to add the AI to de DataBase");
		btnAdd.setForeground(new Color(74, 149, 0));
		btnAdd.setFont(new Font("Arial", Font.BOLD, 13));
		btnAdd.setBorder(null);
		btnAdd.setBackground(Color.WHITE);
		btnAdd.setBounds(301, 271, 82, 45);
		contentPane.add(btnAdd);

	}

	private class Manejador implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			Object o = e.getSource();

			if (o == btnUndo || o == btnCancel) {
				int opcion = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?", "",
						JOptionPane.YES_NO_OPTION);
				if (opcion == JOptionPane.YES_OPTION) {
					dispose();
					new Principal().setVisible(true);

				}
			} else if (o == btnImagen) {
				traerImagenFileChooser();
			} else if (o == btnAdd) {
				int code = Integer.parseInt(txtCode.getText());
				String name = txtName.getText();
				String type = txtType.getText();
				int year = Integer.parseInt(String.valueOf(comboBoxYear.getSelectedItem()));
				String img = ruta;

				AI nueva = new AI(code, name, type, year, img);

				IAService mongo = new IAService("RA4ProyectoAnaPrat");

				mongo.addAi("ArtificialIntelligence", nueva);
				mongo.close();

				JOptionPane.showMessageDialog(null, nueva.getName() + " has been added");

				dispose();
				new Principal().setVisible(true);

			}

		}

	}

	private void traerImagenFileChooser() {
		JFileChooser fc = new JFileChooser();

		fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		FileNameExtensionFilter imgFilter = new FileNameExtensionFilter("Imagenes JPG y GIF", "JPG", "GIF", "PNG");
		fc.setFileFilter(imgFilter);
		int result = fc.showOpenDialog(null);

		File file = fc.getSelectedFile();

		if (result != JFileChooser.CANCEL_OPTION) {

			if (file == null || file.getName().equalsIgnoreCase("")) {
				JOptionPane.showMessageDialog(null, "Escoge una imagen");
			} else {

				String Imagendestino = "fotos" + file.getName();
				Path destino = Path.of(Imagendestino);
				btnImagen.setIcon(new ImageIcon(Imagendestino));
				ruta = Imagendestino;
				try {
					Files.copy(file.toPath(), destino, StandardCopyOption.REPLACE_EXISTING);

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
