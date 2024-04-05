package pacotes;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class InserirVeiculo extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldCodigo;
	private JTextField textFieldMatricula;
	private JTextField textFieldMarca;
	private JTextField textFieldModelo;
	private JTextField textFieldKm;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InserirVeiculo frame = new InserirVeiculo();
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
	public InserirVeiculo() {
		ManipularVeiculos ProximoCodigo = new ManipularVeiculos();
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				
				try {
                    // Lógica para obter o próximo ID disponível usando o ManipularClientes
                    int codigo = ProximoCodigo.obterProximoCodigo();
                    textFieldCodigo.setText(String.valueOf(codigo));
                } catch (IOException ex) 
				{
                    ex.printStackTrace();
                }
			}
		});
		setTitle("Inserir Veículo");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnInserirVeculo = new JButton("Inserir Veículo");
		btnInserirVeculo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String caminho = BaseDados.perfilUtilizador + "\\Documents\\CinelCars\\Ficheiros\\veiculos.txt";
				int aux = 0;
				
				if(textFieldCodigo.getText().equals("") || textFieldMatricula.getText().equals("") || textFieldMarca.getText().equals("") || textFieldModelo.getText().equals("") || textFieldKm.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Tem de preencher os campos!!" , "ERRO", JOptionPane.ERROR_MESSAGE);
				}
				else {
				
					try {
						FileReader ler = new FileReader(caminho);
						BufferedReader dados = new BufferedReader(ler);
						
						String linha;
						//enquanto existir informação no ficheiro, a String linha é carregada com os dados
						while((linha = dados.readLine()) != null)
						{
							String[] coluna = linha.split(";");
							if(coluna[1].equalsIgnoreCase(textFieldMatricula.getText()))
							{
								JOptionPane.showMessageDialog(null, "Matrícula já registada", "AVISO", JOptionPane.INFORMATION_MESSAGE);
								aux = 1;
								//Limpar os campos
								textFieldMatricula.setText("");
								textFieldMarca.setText("");
								textFieldModelo.setText("");
								textFieldKm.setText("");
							}
						}
						// Fecha os recursos do leitor
		                dados.close();
		                ler.close();
					
						if(aux == 0)
						{
							ManipularVeiculos guardar = new ManipularVeiculos();
							guardar.GuardarVeiculos(textFieldCodigo.getText(), textFieldMatricula.getText(), textFieldMarca.getText(), textFieldModelo.getText(), textFieldKm.getText());
							// Atualizar o campo ID com o próximo ID disponível
							int valorAtual = Integer.parseInt(textFieldCodigo.getText());
							textFieldCodigo.setText(String.valueOf(valorAtual + 1));
							//Limpar os campos
							textFieldMatricula.setText("");
							textFieldMarca.setText("");
							textFieldModelo.setText("");
							textFieldKm.setText("");
						}
					
					}catch(Exception erro)
					{
						JOptionPane.showMessageDialog(null, "Algo correu mal! Contacte o Administrador", "ERRO", JOptionPane.ERROR_MESSAGE);
					}
					
				}
			}
							
		});
		btnInserirVeculo.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnInserirVeculo.setBounds(35, 205, 140, 32);
		contentPane.add(btnInserirVeculo);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
			}
		});
		btnVoltar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnVoltar.setBounds(240, 205, 140, 32);
		contentPane.add(btnVoltar);
		
		JLabel lblNewLabel_1 = new JLabel("Código");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(35, 26, 88, 14);
		contentPane.add(lblNewLabel_1);
		
		textFieldCodigo = new JTextField();
		textFieldCodigo.setColumns(10);
		textFieldCodigo.setBounds(158, 25, 220, 20);
		contentPane.add(textFieldCodigo);
		
		JLabel lblMatrcula = new JLabel("Matrícula");
		lblMatrcula.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMatrcula.setBounds(35, 57, 88, 14);
		contentPane.add(lblMatrcula);
		
		textFieldMatricula = new JTextField();
		textFieldMatricula.setColumns(10);
		textFieldMatricula.setBounds(158, 56, 220, 20);
		contentPane.add(textFieldMatricula);
		
		JLabel lblMarca = new JLabel("Marca");
		lblMarca.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMarca.setBounds(35, 87, 96, 14);
		contentPane.add(lblMarca);
		
		textFieldMarca = new JTextField();
		textFieldMarca.setColumns(10);
		textFieldMarca.setBounds(158, 86, 220, 20);
		contentPane.add(textFieldMarca);
		
		JLabel lblModelo = new JLabel("Modelo");
		lblModelo.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblModelo.setBounds(35, 119, 88, 19);
		contentPane.add(lblModelo);
		
		textFieldModelo = new JTextField();
		textFieldModelo.setColumns(10);
		textFieldModelo.setBounds(158, 118, 220, 20);
		contentPane.add(textFieldModelo);
		
		textFieldKm = new JTextField();
		textFieldKm.setColumns(10);
		textFieldKm.setBounds(158, 149, 220, 20);
		contentPane.add(textFieldKm);
		
		JLabel lblQuilmetros = new JLabel("Quilómetros");
		lblQuilmetros.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblQuilmetros.setBounds(35, 150, 88, 19);
		contentPane.add(lblQuilmetros);
	}

}
