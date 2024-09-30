package views;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class IniPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel logo;
	private JButton btnGo;
	private JLabel title;

	public IniPage() {

		super("APG");

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

		title = new JLabel("Artificial Intelligence Administration");
		title.setForeground(new Color(74, 149, 0));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 30));
		title.setBounds(0, 190, 482, 77);
		contentPane.add(title);

		Icon apg = new ImageIcon("images/logoBig.png");

		logo = new JLabel(apg);
		logo.setBounds(60, 38, 356, 142);
		contentPane.add(logo);

		Manejador manejador = new Manejador();

		btnGo = new JButton("Let´s go!");
		btnGo.setFocusPainted(false);
		btnGo.addActionListener(manejador);
		btnGo.setToolTipText("Click to enter");
		btnGo.setBorder(null);
		btnGo.setBackground(new Color(255, 255, 255));
		btnGo.setForeground(new Color(74, 149, 0));
		btnGo.setFont(new Font("Arial", Font.BOLD, 13));
		btnGo.setBounds(182, 277, 119, 38);
		contentPane.add(btnGo);
	}

	private class Manejador implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			Object o = e.getSource();

			dispose();

			if (o == btnGo) {
				new Principal().setVisible(true);
			}

		}

	}
}
