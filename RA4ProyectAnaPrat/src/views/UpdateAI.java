package views;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;

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

public class UpdateAI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnUndo;
	private JLabel lblAiCode;
	private JTextField txtCode;
	private JLabel lblAiName;
	private JTextField txtName;
	private JLabel lblType;
	private JTextField txtType;
	private JLabel lblYearOApparition;
	private JLabel lblImage;
	private JButton btnCancel;
	private JButton btnUpdate;
	private List<AI> ias;
	private AI selectedAI;
	private int codeUp;
	private String[] arrayYears;
	private JComboBox comboBoxYear;
	private JButton btnImagen;
	private String ruta;

	public UpdateAI(int code) {
		super("Update AI");

		codeUp = code;

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

		IAService mongo = new IAService("RA4ProyectoAnaPrat");

		ias = mongo.getAllAis("ArtificialIntelligence");

		for (AI ia : ias) {
			if (ia.getCode() == codeUp) {
				selectedAI = ia;
			}
		}

		mongo.close();

		Manejador manejador = new Manejador();

		btnUndo = new JButton("");
		btnUndo.addActionListener(manejador);
		btnUndo.setFocusPainted(false);
		btnUndo.setBackground(new Color(201, 232, 179));
		btnUndo.setBorder(null);
		btnUndo.setIcon(new ImageIcon("images/undo.png"));
		btnUndo.setBounds(10, 10, 44, 44);
		contentPane.add(btnUndo);

		lblAiCode = new JLabel("AI Code:");
		lblAiCode.setForeground(Color.WHITE);
		lblAiCode.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 21));
		lblAiCode.setBounds(34, 62, 73, 45);
		contentPane.add(lblAiCode);

		txtCode = new JTextField();
		txtCode.setEditable(false);
		txtCode.setText(String.valueOf(selectedAI.getCode()));
		txtCode.setColumns(10);
		txtCode.setBounds(117, 74, 128, 25);
		contentPane.add(txtCode);

		lblAiName = new JLabel("AI Name: ");
		lblAiName.setForeground(Color.WHITE);
		lblAiName.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 21));
		lblAiName.setBounds(34, 109, 73, 45);
		contentPane.add(lblAiName);

		txtName = new JTextField();
		txtName.setText(selectedAI.getName());
		txtName.setColumns(10);
		txtName.setBounds(117, 121, 128, 25);
		contentPane.add(txtName);

		lblType = new JLabel("Type:");
		lblType.setForeground(Color.WHITE);
		lblType.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 21));
		lblType.setBounds(34, 156, 73, 45);
		contentPane.add(lblType);

		txtType = new JTextField();
		txtType.setText(selectedAI.getType());
		txtType.setColumns(10);
		txtType.setBounds(84, 168, 128, 25);
		contentPane.add(txtType);

		lblYearOApparition = new JLabel("Year of appearance:");
		lblYearOApparition.setForeground(Color.WHITE);
		lblYearOApparition.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 21));
		lblYearOApparition.setBounds(34, 203, 149, 45);
		contentPane.add(lblYearOApparition);

		arrayYears = new String[82];

		int firstY = 1943;
		String yea = String.valueOf(selectedAI.getYearAp());

		for (int i = 0; i < arrayYears.length; i++) {
			arrayYears[i] = String.valueOf(firstY++);
		}

		arrayYears.toString();
		comboBoxYear = new JComboBox(arrayYears);

		for (int i = 0; i < arrayYears.length; i++) {
			if (arrayYears[i].equals(yea))
				comboBoxYear.setSelectedIndex(i);
		}

		comboBoxYear.setEditable(true);
		comboBoxYear.setMaximumRowCount(5);
		comboBoxYear.setBounds(185, 209, 82, 21);
		contentPane.add(comboBoxYear);

		lblImage = new JLabel("Image: ");
		lblImage.setForeground(Color.WHITE);
		lblImage.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 21));
		lblImage.setBounds(300, 35, 149, 45);
		contentPane.add(lblImage);

		btnImagen = new JButton("");
		btnImagen.addActionListener(manejador);
		btnImagen.setIcon(new ImageIcon(selectedAI.getImage()));
		btnImagen.setBackground(Color.WHITE);
		btnImagen.setBounds(287, 68, 139, 139);
		contentPane.add(btnImagen);

		btnCancel = new JButton("Cancel");
		btnCancel.setToolTipText("Click to cancel");
		btnCancel.setForeground(new Color(74, 149, 0));
		btnCancel.setFont(new Font("Arial", Font.BOLD, 13));
		btnCancel.setBorder(null);
		btnCancel.setBackground(Color.WHITE);
		btnCancel.setBounds(126, 279, 86, 45);
		contentPane.add(btnCancel);

		btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(manejador);
		btnUpdate.setToolTipText("Click to add the AI to de DataBase");
		btnUpdate.setForeground(new Color(74, 149, 0));
		btnUpdate.setFont(new Font("Arial", Font.BOLD, 13));
		btnUpdate.setBorder(null);
		btnUpdate.setBackground(Color.WHITE);
		btnUpdate.setBounds(314, 279, 82, 45);
		contentPane.add(btnUpdate);

	}

	private class Manejador implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			Object o = e.getSource();

			if (o == btnUndo) {
				dispose();
				new UpdateAISelect().setVisible(true);
			} else if (o == btnImagen) {
				traerImagenFileChooser();
			} else if (o == btnUpdate) {
				dispose();
				// Conexion
				IAService mongo = new IAService("RA4ProyectoAnaPrat");

				// Paso a la lista
				ias = mongo.getAllAis("ArtificialIntelligence");

				int code = Integer.parseInt(txtCode.getText());
				String name = txtName.getText();
				String type = txtType.getText();
				int year = Integer.parseInt(String.valueOf(comboBoxYear.getSelectedItem()));
				String img = ruta;

				AI nueva = new AI(code, name, type, year, img);

				mongo.updateAi("ArtificialIntelligence", codeUp, nueva);
				mongo.close();

				JOptionPane.showMessageDialog(null, selectedAI.getName() + " has been updated");
				new Principal().setVisible(true);
				dispose();
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