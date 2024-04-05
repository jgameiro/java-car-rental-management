package pacotes;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DevolucaoVeiculo extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldKm;
	private JTextField textFieldNIFCliente;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DevolucaoVeiculo frame = new DevolucaoVeiculo();
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
	public DevolucaoVeiculo() {
		
		ManipularAluguer receber = new ManipularAluguer();
		JComboBox<String> comboBoxVeiculo = new JComboBox<String>();
		
		comboBoxVeiculo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			    if(comboBoxVeiculo.getSelectedIndex() > 0)    
			    	receber.ProcurarClientes(comboBoxVeiculo.getSelectedItem().toString(), textFieldNIFCliente);
			    
			}
		});
		setTitle("Receber Veículo");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				
				comboBoxVeiculo.addItem("Escolha um Veiculo");
				
				String caminho1 = BaseDados.perfilUtilizador + "\\Documents\\CinelCars\\Ficheiros\\aluguer.txt";
				
				try {
					FileReader ler = new FileReader(caminho1);
					BufferedReader procura = new BufferedReader(ler);
					
	                    String linha;
	                    while ((linha = procura.readLine()) != null) 
	                    {
	                        String[] colunas = linha.split(";");
	                        String matricula = colunas[0]; // Considerando que a matrícula está na segunda coluna
	                        comboBoxVeiculo.addItem(matricula);
	                    }
	                } 
				catch (Exception erro) 
					{
	                    JOptionPane.showMessageDialog(null, "Algo correu mal! Contacte o Administrador", "ERRO", JOptionPane.ERROR_MESSAGE);
	                    erro.printStackTrace();
	                }
	            }
	
		});
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 353);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblVeiculo = new JLabel("Veículo");
		lblVeiculo.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblVeiculo.setBounds(10, 11, 121, 22);
		contentPane.add(lblVeiculo);
		
		
		comboBoxVeiculo.setBounds(187, 11, 220, 22);
		contentPane.add(comboBoxVeiculo);
		
		JLabel lblCliente = new JLabel("NIF Cliente");
		lblCliente.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCliente.setBounds(10, 59, 121, 22);
		contentPane.add(lblCliente);
		
		JLabel lblQuilmetrosRealizados = new JLabel("Quilómetros Realizados");
		lblQuilmetrosRealizados.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblQuilmetrosRealizados.setBounds(10, 112, 167, 22);
		contentPane.add(lblQuilmetrosRealizados);
		
		textFieldKm = new JTextField();
		textFieldKm.setColumns(10);
		textFieldKm.setBounds(187, 115, 220, 20);
		contentPane.add(textFieldKm);
		
		JButton btnReceber = new JButton("Receber");
		btnReceber.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				if(comboBoxVeiculo.getSelectedIndex() == 0 || textFieldKm.getText().equals("")) 
				{
		            JOptionPane.showMessageDialog(null, "Tem de preencher os campos!!" , "ERRO", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					receber.AtualizarDadosVeiculo(comboBoxVeiculo.getSelectedItem().toString(), textFieldKm);
					//receber.receberVeiculo(comboBoxVeiculo.getSelectedItem().toString(), textFieldNIFCliente.getText(), textFieldKm.getText());
					receber.AtualizarDadosAluguer(comboBoxVeiculo.getSelectedItem().toString());;
					//Limpar os campos
					comboBoxVeiculo.setSelectedIndex(0);
					textFieldKm.setText("");
					textFieldNIFCliente.setText("");
					
                    // Limpar e recarregar a comboBoxVeiculo
                    comboBoxVeiculo.removeAllItems();
                    comboBoxVeiculo.addItem("Escolha um Veiculo");
                    
                    
                    String caminho1 = BaseDados.perfilUtilizador + "\\Documents\\CinelCars\\Ficheiros\\aluguer.txt";

                    try {
                        FileReader ler = new FileReader(caminho1);
                        BufferedReader procura = new BufferedReader(ler);

                        String linha;
                        while ((linha = procura.readLine()) != null) {
                            String[] colunas = linha.split(";");
                            String matricula = colunas[0]; // Considerando que a matrícula está na segunda coluna
                            comboBoxVeiculo.addItem(matricula);
                        }
                        procura.close();
                    } catch (Exception erro) {
                        JOptionPane.showMessageDialog(null, "Algo correu mal! Contacte o Administrador", "ERRO", JOptionPane.ERROR_MESSAGE);
                        erro.printStackTrace();
                    }
                }
            }
		});
		btnReceber.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnReceber.setBounds(107, 188, 197, 48);
		contentPane.add(btnReceber);
		
		textFieldNIFCliente = new JTextField();
		textFieldNIFCliente.setEnabled(false);
		textFieldNIFCliente.setColumns(10);
		textFieldNIFCliente.setBounds(187, 62, 220, 20);
		contentPane.add(textFieldNIFCliente);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
			}
		});
		btnVoltar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnVoltar.setBounds(107, 257, 197, 25);
		contentPane.add(btnVoltar);
	}

}
