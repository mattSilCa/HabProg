package GUI;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class HabitNoteDemo extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HabitNoteDemo frame = new HabitNoteDemo();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	//Atributos
	JPanel panel_1, panel;
	private JLayeredPane layeredPane;
	
	public HabitNoteDemo() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 965, 608);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		CardLayout card = new CardLayout();
		contentPane.setLayout(card);
		
		panel = new JPanel();
		contentPane.add(panel, "name_101055902318571");
		panel.setLayout(new BorderLayout(0, 0));
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//card.next(contentPane);
				//card.addLayoutComponent(contentPane, "panel_1");
				card.show(contentPane, "zezão");
				
				// Usar upcasting para mudar o card layout
				//CardLayout c1 = (CardLayout)contentPane.getLayout();
				//c1.next(contentPane);
				}
		});
		panel.add(btnNewButton, BorderLayout.CENTER);
		
		panel_1 = new JPanel();
		contentPane.add(panel_1, "zezão");
		panel_1.setLayout(new BorderLayout(0, 0));
		
		layeredPane = new JLayeredPane();
		panel_1.add(layeredPane, BorderLayout.CENTER);
	}
}
