package pacotes;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RegistarAluguer extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldDescricao;
	private JTextField textFieldValor;
	private JTextField textFieldMarca;
	private JTextField textFieldModelo;
	private JTextField textFieldNome;
	private JTextField textFieldNomeFuncionario;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegistarAluguer frame = new RegistarAluguer();
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
	public RegistarAluguer() {
		
		ManipularAluguer guardar = new ManipularAluguer();
		JComboBox<String> comboBoxVeiculo = new JComboBox<String>();
		comboBoxVeiculo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				guardar.ProcurarMatricula(comboBoxVeiculo.getSelectedItem().toString(), textFieldMarca, textFieldModelo);
			}
		});

		JComboBox<String> comboBoxFuncionario = new JComboBox<String>();
		comboBoxFuncionario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				guardar.ProcurarNomeFuncionario(comboBoxFuncionario.getSelectedItem().toString(), textFieldNomeFuncionario);
			}
		});
		JComboBox<String> comboBoxCliente = new JComboBox<String>();
		comboBoxCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				guardar.ProcurarNomeCliente(comboBoxCliente.getSelectedItem().toString(), textFieldNome);
			}
		});
		JComboBox<String> comboBoxEstadoVeiculo = new JComboBox<String>();
		
			
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				
				
				comboBoxVeiculo.addItem("Escolha um Veiculo");
				
                String caminhoVeiculos = BaseDados.perfilUtilizador + "\\Documents\\CinelCars\\Ficheiros\\veiculos.txt";
        		
        		try {
        			FileReader ler = new FileReader(caminhoVeiculos);
        			BufferedReader dados = new BufferedReader(ler);
        			
        			String linha;
        			//enquanto existir informação no ficheiro, a String linha é carregada com os dados
        			while((linha = dados.readLine()) != null)
        			{
        				String[] coluna = linha.split(";");
        				comboBoxVeiculo.addItem(coluna[1]);	
        			}

        		} catch (Exception erro) 
        		{
        			JOptionPane.showMessageDialog(null, "Algo correu mal! Contacte o Administrador", "ERRO", JOptionPane.ERROR_MESSAGE);
        		}
        		
				comboBoxFuncionario.addItem("Escolha um Funcionário");
				
				String caminhoFuncionario = BaseDados.perfilUtilizador + "\\Documents\\CinelCars\\Ficheiros\\funcionarios.txt";
					try {
						FileReader ler = new FileReader(caminhoFuncionario);
						BufferedReader dados = new BufferedReader(ler);
						
						String linha;
						//enquanto existir informação no ficheiro, a String linha é carregada com os dados
						while((linha = dados.readLine()) != null)
						{
							String[] coluna = linha.split(";");
							comboBoxFuncionario.addItem(coluna[0]);	
						}

					} catch (Exception erro) 
					{
						JOptionPane.showMessageDialog(null, "Algo correu mal! Contacte o Administrador", "ERRO", JOptionPane.ERROR_MESSAGE);
					}
	
				comboBoxCliente.addItem("Escolha um Cliente");
				
				String caminhoCliente = BaseDados.perfilUtilizador + "\\Documents\\CinelCars\\Ficheiros\\clientes.txt";
				
				try {
					FileReader ler = new FileReader(caminhoCliente);
					BufferedReader dados = new BufferedReader(ler);
					
					String linha;
					//enquanto existir informação no ficheiro, a String linha é carregada com os dados
					while((linha = dados.readLine()) != null)
					{
						String[] coluna = linha.split(";");
						comboBoxCliente.addItem(coluna[1]);
					}
					//Fechamos o acesso ao ficheiro de texto
					ler.close();
				} catch (Exception erro) 
				{
					JOptionPane.showMessageDialog(null, "Algo correu mal! Contacte o Administrador", "ERRO", JOptionPane.ERROR_MESSAGE);
				}
				
				comboBoxEstadoVeiculo.addItem("Escolha um estado");
				comboBoxEstadoVeiculo.addItem("Mau");
				comboBoxEstadoVeiculo.addItem("Médio");
				comboBoxEstadoVeiculo.addItem("Bom");
			}
		});
		
		comboBoxEstadoVeiculo.addItemListener(new ItemListener() {
		    public void itemStateChanged(ItemEvent e) {
		        String estadoSelecionado = (String) comboBoxEstadoVeiculo.getSelectedItem();
		        if (estadoSelecionado.equals("Médio") || estadoSelecionado.equals("Mau")) {
		            textFieldDescricao.setEnabled(true);
		        } else {
		            textFieldDescricao.setEnabled(false);
		            textFieldDescricao.setText(""); // Limpa o campo de descrição quando o estado não é "Médio" ou "Mau"
		        }
		    }
		});
			
		setTitle("Aluguer de Veiculo");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 570, 552);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		comboBoxVeiculo.setBounds(221, 11, 220, 22);
		contentPane.add(comboBoxVeiculo);
		
		
		comboBoxFuncionario.setBounds(221, 75, 220, 22);
		contentPane.add(comboBoxFuncionario);
		
		JLabel lblVeiculo = new JLabel("Veículo");
		lblVeiculo.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblVeiculo.setBounds(44, 11, 121, 22);
		contentPane.add(lblVeiculo);
		
		
		comboBoxCliente.setBounds(221, 142, 220, 22);
		contentPane.add(comboBoxCliente);
		
		JLabel lblFuncionrio = new JLabel("ID Funcionário");
		lblFuncionrio.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblFuncionrio.setBounds(44, 75, 121, 22);
		contentPane.add(lblFuncionrio);
		
		JLabel lblCliente = new JLabel("NIF Cliente");
		lblCliente.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCliente.setBounds(44, 142, 121, 22);
		contentPane.add(lblCliente);
		
		JLabel lblEstadoDoVeculo = new JLabel("Estado do Veículo");
		lblEstadoDoVeculo.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblEstadoDoVeculo.setBounds(44, 214, 134, 22);
		contentPane.add(lblEstadoDoVeculo);
		
		
		comboBoxEstadoVeiculo.setBounds(221, 214, 220, 22);
		contentPane.add(comboBoxEstadoVeiculo);
		
		JLabel lblNoCasoDe = new JLabel("No caso de veículo em estado médio ou mau, descreva sumáriamente os danos:");
		lblNoCasoDe.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNoCasoDe.setBounds(44, 290, 502, 22);
		contentPane.add(lblNoCasoDe);
		
		textFieldDescricao = new JTextField();
		textFieldDescricao.setEnabled(false);
		textFieldDescricao.setBounds(44, 326, 447, 60);
		contentPane.add(textFieldDescricao);
		textFieldDescricao.setColumns(10);
		
		JButton btnNewButton = new JButton("Submeter");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				
				if(comboBoxVeiculo.getSelectedIndex() == 0 || comboBoxFuncionario.getSelectedIndex() == 0 || comboBoxCliente.getSelectedIndex() == 0 || comboBoxEstadoVeiculo.getSelectedIndex() == 0 || textFieldValor.getText().equals("")) 
				{
		            JOptionPane.showMessageDialog(null, "Tem de preencher os campos!!" , "ERRO", JOptionPane.ERROR_MESSAGE);
				}
				else if(comboBoxEstadoVeiculo.getSelectedIndex() == 1 && textFieldDescricao.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Tem de preencher campo descrição!!" , "ERRO", JOptionPane.ERROR_MESSAGE);
				}
				else if(comboBoxEstadoVeiculo.getSelectedIndex() == 2 && textFieldDescricao.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Tem de preencher campo descrição!!" , "ERRO", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					// Obter a data atual do sistema
                    Date dataAtual = new Date();
                    
                    // Inserir a data no formato desejado (dd/MM/yyyy HH:mm:ss)
                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                    String dataRegisto = formatter.format(dataAtual);
					guardar.GuardarAluguer(comboBoxVeiculo.getSelectedItem().toString(), textFieldMarca.getText(), textFieldModelo.getText(), comboBoxFuncionario.getSelectedItem().toString(), textFieldNomeFuncionario.getText(),
							comboBoxCliente.getSelectedItem().toString(), textFieldNome.getText(), comboBoxEstadoVeiculo.getSelectedItem().toString(), textFieldValor.getText(), textFieldDescricao.getText(), dataRegisto);
					
					comboBoxVeiculo.setSelectedIndex(0);
					comboBoxFuncionario.setSelectedIndex(0);
					comboBoxCliente.setSelectedIndex(0);
					comboBoxEstadoVeiculo.setSelectedIndex(0);
					textFieldMarca.setText("");
					textFieldModelo.setText("");
					textFieldNome.setText("");
					textFieldNomeFuncionario.setText("");
					textFieldValor.setText("");
					textFieldDescricao.setText("");
					
					}
				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.setBounds(156, 407, 197, 48);
		contentPane.add(btnNewButton);
		
		JLabel lblValorDoAluguer = new JLabel("Valor do Aluguer");
		lblValorDoAluguer.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblValorDoAluguer.setBounds(44, 247, 134, 22);
		contentPane.add(lblValorDoAluguer);
		
		textFieldValor = new JTextField();
		textFieldValor.setBounds(221, 250, 220, 20);
		contentPane.add(textFieldValor);
		textFieldValor.setColumns(10);
		
		textFieldMarca = new JTextField();
		textFieldMarca.setEnabled(false);
		textFieldMarca.setColumns(10);
		textFieldMarca.setBounds(221, 44, 110, 20);
		contentPane.add(textFieldMarca);
		
		textFieldModelo = new JTextField();
		textFieldModelo.setEnabled(false);
		textFieldModelo.setColumns(10);
		textFieldModelo.setBounds(331, 44, 110, 20);
		contentPane.add(textFieldModelo);
		
		textFieldNome = new JTextField();
		textFieldNome.setEnabled(false);
		textFieldNome.setColumns(10);
		textFieldNome.setBounds(221, 175, 220, 20);
		contentPane.add(textFieldNome);
		
		textFieldNomeFuncionario = new JTextField();
		textFieldNomeFuncionario.setEnabled(false);
		textFieldNomeFuncionario.setColumns(10);
		textFieldNomeFuncionario.setBounds(221, 108, 220, 20);
		contentPane.add(textFieldNomeFuncionario);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
			}
		});
		btnVoltar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnVoltar.setBounds(156, 479, 197, 25);
		contentPane.add(btnVoltar);
		
		
	}
}
