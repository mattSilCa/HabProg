package GUI;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import Objeto.Habito;
import pacoteDesenhos.Sequencia3;

public class HabNote extends JFrame implements ItemListener {

	private JPanel contentPane;
	private JTextField textNome;
	private JTextField textNDeletar;

	ArrayList<Habito> lista = new ArrayList<Habito>();
	Habito novoHab;
	private JTextArea textNomeDel;
	private JTextArea textAtividade;
	private JTextField textNomeR;
	private int iDes;
	private JTextField textHora;
	private JTextField textMin;
	private JTextArea textEscDes;
	private JComboBox<String> comboBox;
	private JTextArea textMostrar;
	private JProgressBar progressBar;
	private JLabel lblProgress;
	private JButton bCont;
	private JButton btnNewButton_6;
	Path path;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				// Path path;
				try {
					HabNote frame = new HabNote();
					frame.setVisible(true);
					// path = Paths.get(URI.create(getClass().getResource("Dados.txt").toString()));
				} catch (Exception e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, e1.getStackTrace());
				}

			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws IOException
	 */

	
	FileSystem fs ;
	private JLabel lblNS;
	private JLabel lblNI;
	private Path pathB;
	
	
	public HabNote() throws Exception {
		setTitle("Hebert");
		ImageIcon pic = new ImageIcon(HabNote.class.getResource("/GUI/Figuras/icone.png") );
		setIconImage((pic.getImage())); 
		
		// Verificar se há arquivo de registro
		

		path = Paths.get(System.getProperty("user.dir").toString()+ "/Dados.txt");
		pathB = Paths.get(System.getProperty("user.dir").toString()+ "/DadosBackup.txt");
		//FileSystem fs = FileSystems.newFileSystem(path);
		//ZipFile zip = new ZipFile(null);
		
		try {
		// Carregar dados de hábitos registrados em arquivo
		setHabitos();
		}catch(Exception eLoad) {
			endsTxt();
			setHabitos();
		}

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 993, 599);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		CardLayout card = new CardLayout();
		contentPane.setLayout(card);

		JPanel pInicio = new JPanel();
		contentPane.add(pInicio, "pInicio");
		pInicio.setLayout(new BorderLayout(0, 0));

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 5));
		ImageIcon icone = new ImageIcon(HabNote.class.getResource("/GUI/Figuras/habitos.jpg"));
		Image imagem = icone.getImage();

		lblNewLabel.setIcon(new ImageIcon(
				imagem.getScaledInstance(icone.getIconWidth() / 2, icone.getIconHeight() / 2, MAXIMIZED_BOTH)));
		pInicio.add(lblNewLabel, BorderLayout.EAST);

		JPanel panel_1 = new JPanel();
		pInicio.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new GridLayout(4, 0, 0, 0));

		JButton bCadastrar = new JButton("Cadastrar novo h\u00E1bito");
		bCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(contentPane, "pCadastro");
			}
		});
		bCadastrar.setFont(new Font("Tahoma", Font.BOLD, 24));
		panel_1.add(bCadastrar);

		JButton bDeletar = new JButton("Deletar h\u00E1bito");
		bDeletar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(contentPane, "pDeletar");
				String s = "\tLista de hábitos\n\n";
				int i = 1;
				for (Habito h : lista) {
					s += "\t\t< " + i + " > \n"+ h.showHab() + "\n";
					i++;
				}

				textNomeDel.setText(s);

			}
		});
		bDeletar.setFont(new Font("Tahoma", Font.BOLD, 24));
		panel_1.add(bDeletar);

		JButton bRegistrar = new JButton("Registrar atividade");
		bRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				card.show(contentPane, "pAtividade");
				String s = "\tLista de hábitos\n\n";
				int i = 0;
				for (i = 0; i < lista.size(); i++) {
					s += "\t\t< " + (i+1) + " > \n"+lista.get(i).showHab() + "\n";

				}

				textAtividade.setText(s);
			}
		});
		bRegistrar.setFont(new Font("Tahoma", Font.BOLD, 24));
		panel_1.add(bRegistrar);

		JButton btnNewButton_3 = new JButton("Mostrar h\u00E1bitos");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (Habito h : lista) {
					comboBox.addItem(h.getName());
				}
				card.show(contentPane, "pMostrar");
			}
		});
		btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 24));
		panel_1.add(btnNewButton_3);

		JPanel pCadastro = new JPanel();
		contentPane.add(pCadastro, "pCadastro");
		pCadastro.setLayout(new GridLayout(3, 1, 25, 25));

		JLabel lblNewLabel_1 = new JLabel("Digite o nome do h\u00E1bito");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 50));
		pCadastro.add(lblNewLabel_1);

		textNome = new JTextField();
		textNome.setHorizontalAlignment(SwingConstants.CENTER);
		textNome.setFont(new Font("Tahoma", Font.BOLD, 53));
		pCadastro.add(textNome);
		textNome.setColumns(10);

		JPanel panel_6 = new JPanel();
		pCadastro.add(panel_6);
		FlowLayout fl_panel_6 = new FlowLayout(FlowLayout.CENTER, 50, 50);
		panel_6.setLayout(fl_panel_6);

		JButton bRegistrarHab = new JButton("Registrar");
		bRegistrarHab.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				novoHab = new Habito(textNome.getText());
				lista.add(novoHab);
				card.show(contentPane, "pInicio");
				textNome.setText("");

				// Registrar informações no arquivo dados.txt
				try {
					saveData();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		bRegistrarHab.setFont(new Font("Tahoma", Font.PLAIN, 35));
		panel_6.add(bRegistrarHab);

		JButton bCancelarCad = new JButton("Cancelar");
		bCancelarCad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(contentPane, "pInicio");
				textNome.setText("");

			}
		});
		bCancelarCad.setFont(new Font("Tahoma", Font.PLAIN, 35));
		panel_6.add(bCancelarCad);

		JPanel pDeletar = new JPanel();
		contentPane.add(pDeletar, "pDeletar");
		pDeletar.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		pDeletar.add(scrollPane, BorderLayout.CENTER);

		textNomeDel = new JTextArea();
		textNomeDel.setLineWrap(true);
		textNomeDel.setFont(new Font("Times New Roman", Font.BOLD, 20));
		textNomeDel.setWrapStyleWord(true);
		textNomeDel.setEditable(false);
		scrollPane.setViewportView(textNomeDel);

		JPanel panel_2 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_2.getLayout();
		flowLayout.setHgap(50);
		pDeletar.add(panel_2, BorderLayout.SOUTH);

		JLabel lblNewLabel_2 = new JLabel("Digite o n\u00FAmero do h\u00E1bito para deletar.      ");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel_2.add(lblNewLabel_2);

		textNDeletar = new JTextField();
		textNDeletar.setText("");
		textNDeletar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel_2.add(textNDeletar);
		textNDeletar.setColumns(10);

		JButton bDeletarHab = new JButton("Deletar");
		bDeletarHab.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int i = Integer.parseInt(textNDeletar.getText());
					if (i < 1 || i > lista.size()) {
						throw new Exception();

					}

					lista.remove(i - 1);
					card.show(contentPane, "pInicio");
					textNDeletar.setText("");
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Entrada inválida");
				}

				try {
					saveData();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		bDeletarHab.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel_2.add(bDeletarHab);

		JButton bCalcDel = new JButton("Cancelar");
		bCalcDel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textNDeletar.setText("");
				card.show(contentPane, "pInicio");
			}
		});
		bCalcDel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel_2.add(bCalcDel);

		CardLayout card2 = new CardLayout();
		JPanel pAtividade = new JPanel();
		contentPane.add(pAtividade, "pAtividade");
		pAtividade.setLayout(card2);

		JPanel pEscolher = new JPanel();
		pAtividade.add(pEscolher, "pEscolher");
		pEscolher.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane_1 = new JScrollPane();
		pEscolher.add(scrollPane_1, BorderLayout.CENTER);

		textAtividade = new JTextArea();
		textAtividade.setEditable(false);
		textAtividade.setFont(new Font("Times New Roman", Font.BOLD, 20));
		textAtividade.setWrapStyleWord(true);
		textAtividade.setLineWrap(true);
		scrollPane_1.setViewportView(textAtividade);

		JPanel panel_3 = new JPanel();
		pEscolher.add(panel_3, BorderLayout.SOUTH);
		panel_3.setLayout(new BoxLayout(panel_3, BoxLayout.X_AXIS));

		JLabel lblNewLabel_3 = new JLabel("  Digite o nome do h\u00E1bito.");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panel_3.add(lblNewLabel_3);

		textNomeR = new JTextField();
		panel_3.add(textNomeR);
		textNomeR.setColumns(10);

		JButton btnNewButton_5 = new JButton("Pr\u00F3ximo");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String s = textNomeR.getText();
					boolean b = false;
					iDes = 0;
					for (Habito h : lista) {
						if (s.equals(h.getName())) {
							card2.show(pAtividade, "pEscreverD");
							b = false;
							break;
						} else
							b = true;
						iDes++;
					}
					if (b) {
						throw new Exception();
					}

				} catch (Exception e3) {
					JOptionPane.showMessageDialog(null, "Nome inválido");

				}

			}
		});
		panel_3.add(btnNewButton_5);

		JButton bCancAtiv = new JButton("Cancelar");
		bCancAtiv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(contentPane, "pInicio");
				textNomeR.setText("");
			}
		});
		panel_3.add(bCancAtiv);

		JPanel pEscreverD = new JPanel();
		pAtividade.add(pEscreverD, "pEscreverD");
		pEscreverD.setLayout(new BorderLayout(0, 0));

		JLabel lblNewLabel_4 = new JLabel("Descri\u00E7\u00E3o");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 23));
		lblNewLabel_4.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		pEscreverD.add(lblNewLabel_4, BorderLayout.NORTH);

		JScrollPane scrollPane_2 = new JScrollPane();
		pEscreverD.add(scrollPane_2, BorderLayout.CENTER);

		textEscDes = new JTextArea();
		textEscDes.setFont(new Font("Times New Roman", Font.BOLD, 20));
		textEscDes.setWrapStyleWord(true);
		textEscDes.setLineWrap(true);
		textEscDes.setToolTipText("Descreva sua atividade\r\n");
		scrollPane_2.setViewportView(textEscDes);

		JPanel panel_4 = new JPanel();
		pEscreverD.add(panel_4, BorderLayout.SOUTH);

		JLabel lblNewLabel_5 = new JLabel("Digite o tempo dedicado.");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_4.add(lblNewLabel_5);

		textHora = new JTextField();
		textHora.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_4.add(textHora);
		textHora.setColumns(10);

		JLabel lblNewLabel_5_1 = new JLabel(":");
		lblNewLabel_5_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_4.add(lblNewLabel_5_1);

		textMin = new JTextField();
		textMin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textMin.setColumns(10);
		panel_4.add(textMin);

		JButton bRegDes = new JButton("Registrar");
		bRegDes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int h = 0;
				int m = 0;

				try {
					h = Integer.parseInt(textHora.getText());
					m = Integer.parseInt(textMin.getText());

					if (h < 0 || m < 0 || m > 60) {
						throw new Exception();
					} else if (m == 0 && h == 0)
						throw new Exception();

					lista.get(iDes).makeDesc(textEscDes.getText(), h, m);

					textHora.setText("");
					textMin.setText("");
					textNomeR.setText("");
					textEscDes.setText("");
					
					card2.show(pAtividade, "pEscolher");
					card.show(contentPane, "pInicio");

					try {
						saveData();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				} catch (Exception e4) {
					JOptionPane.showMessageDialog(null, "Tempo inserido é inválido");
					e4.printStackTrace();
				}

			}
		});
		bRegDes.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel_4.add(bRegDes);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textEscDes.setText("");
				textHora.setText("");
				textMin.setText("");
				card2.show(pAtividade, "pEscolher");
			}
		});
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel_4.add(btnCancelar);

		JPanel pMostrar = new JPanel();
		contentPane.add(pMostrar, "pMostrar");
		pMostrar.setLayout(new BorderLayout(0, 0));

		comboBox = new JComboBox<String>();
		comboBox.addItemListener(this);
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pMostrar.add(comboBox, BorderLayout.SOUTH);

		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBackground(Color.WHITE);
		pMostrar.add(desktopPane, BorderLayout.CENTER);

		progressBar = new JProgressBar();
		progressBar.setValue(0);
		progressBar.setBounds(633, 72, 227, 17);
		desktopPane.add(progressBar);

		bCont = new JButton("Continuidade");
		bCont.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				novoHab = null;
				Sequencia3 sq = null;
				novoHab = new Habito();
				novoHab = lista.get(comboBox.getSelectedIndex());
				sq = new Sequencia3(novoHab.getAL());
				sq.graf();
				}catch(Exception eCont) {
				}
			}
		});
		bCont.setFont(new Font("Tahoma", Font.PLAIN, 17));
		bCont.setBounds(664, 252, 152, 47);
		desktopPane.add(bCont);

		btnNewButton_6 = new JButton("Voltar");
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				progressBar.setValue(0);
				textMostrar.setText("");
				comboBox.removeAllItems();
				lblNI.setText("");
				lblNS.setText("");
				card.show(contentPane, "pInicio");
			}
		});
		btnNewButton_6.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnNewButton_6.setBounds(664, 346, 152, 47);
		desktopPane.add(btnNewButton_6);

		lblProgress = new JLabel("Progresso");
		lblProgress.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblProgress.setHorizontalAlignment(SwingConstants.CENTER);
		lblProgress.setBounds(633, 29, 227, 32);
		desktopPane.add(lblProgress);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(41, 29, 487, 451);
		desktopPane.add(panel);
		panel.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane_3 = new JScrollPane();
		panel.add(scrollPane_3, BorderLayout.CENTER);

		textMostrar = new JTextArea();
		textMostrar.setLineWrap(true);
		textMostrar.setFont(new Font("Arial", Font.BOLD, 15));
		textMostrar.setWrapStyleWord(true);
		scrollPane_3.setViewportView(textMostrar);
		
		lblNI = new JLabel("0");
		lblNI.setBounds(608, 72, 15, 13);
		desktopPane.add(lblNI);
		
		lblNS = new JLabel("0");
		lblNS.setBounds(870, 72, 22, 13);
		desktopPane.add(lblNS);

	}

	// Métodos
	
	private void endsTxt() throws Exception {
		
			path = Paths.get(System.getProperty("user.dir").toString()+"/Dados.txt");
	
			File arquivo = new File(System.getProperty("user.dir").toString()+"/Dados.txt");
			String s = "Fim";
			byte[] strToBytes = s.getBytes();
			Files.write(Paths.get(arquivo.getAbsolutePath()), strToBytes);
			
		
		
		
	}

	public void saveData() throws IOException {
		String s = "";
		for (Habito h : lista) {
			s += h.registrar();
			
		}
		s += "Fim";

		try {
			byte[] strToBytes = s.getBytes();
			Files.write(path, strToBytes);
			Files.write(pathB, strToBytes);
		} catch (Exception eSave) {
		}

	}

	public String getData() throws IOException {
		String retorno = new String(Files.readAllBytes(path));
		return retorno;
	}

	public void setHabitos() throws IOException {
		
		String[] s = getData().split("Mud4r");
		String[] cons;	
		for (int i = 0; i < (s.length - 1); i++) {
			
			cons = s[i].split("/ - /");
			
			// String nome, String splitD, int splitTHab, int dia, int mes, int ano
			int dia = Integer.parseInt(cons[3]);
			int mes = Integer.parseInt(cons[4]);
			int ano = Integer.parseInt(cons[5]);
			String cont = "";
			try {cont = cons[6];}
			catch(Exception eCont)	{cont = "";}
			novoHab = new Habito(cons[0], cons[1], cons[2], dia, mes, ano,cont);
			lista.add(novoHab);
			
		}
		
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
			novoHab = new Habito();
			novoHab = lista.get(comboBox.getSelectedIndex());
			textMostrar.setText(novoHab.showHab()+"\n"+ novoHab.showDesc());

			lblProgress.setText("Progresso: " + (int)novoHab.progresso + "%");
		
			
			String s1 = String.valueOf(novoHab.calculaNivel());
			String s2 = String.valueOf(novoHab.calculaNivel()+1);
			
			lblNI.setText(s1);
			lblNS.setText(s2);

			progressBar.setValue((int) novoHab.progresso);
		}
	}
}
