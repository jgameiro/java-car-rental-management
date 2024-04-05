package pacotes;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class InserirCliente extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldNomeCliente;
	private JTextField textFieldNIFCliente;
	private JTextField textFieldIDCliente;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InserirCliente frame = new InserirCliente();
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
	public InserirCliente() {
		setTitle("Inserir Cliente");
		
		ManipularClientes ProximoID = new ManipularClientes();
		
		JComboBox<String> comboBoxLocalidade = new JComboBox<String>();
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				
				try {
                    // Lógica para obter o próximo ID disponível usando o ManipularClientes
                    int proximoID = ProximoID.obterProximoID();
                    textFieldIDCliente.setText(String.valueOf(proximoID));
                } catch (IOException ex) 
				{
                    ex.printStackTrace();
                }
				
				comboBoxLocalidade.addItem("Escolha uma localidade");
				
				String caminho = BaseDados.perfilUtilizador + "\\Documents\\CinelCars\\Ficheiros\\localidade.txt";
				
				try {
					FileReader ler = new FileReader(caminho);
					BufferedReader dados = new BufferedReader(ler);
					
					String linha;
					//enquanto existir informação no ficheiro, a String linha é carregada com os dados
					while((linha = dados.readLine()) != null)
					{
						comboBoxLocalidade.addItem(linha);
					}
					//Fechamos o acesso ao ficheiro de texto
					ler.close();
				} catch (Exception erro) 
				{
					JOptionPane.showMessageDialog(null, "Algo correu mal! Contacte o Administrador", "ERRO", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JRadioButton radioFeminino = new JRadioButton("Feminino");
		
		JButton btnNewButton = new JButton("Inserir Cliente");
		btnNewButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        
		        String genero = "";
		        
		        if(textFieldNIFCliente.getText().equals("") || textFieldNomeCliente.getText().equals("") || comboBoxLocalidade.getSelectedIndex() == 0 || buttonGroup.isSelected(null)) {
		            JOptionPane.showMessageDialog(null, "Tem de preencher os campos!!" , "ERRO", JOptionPane.ERROR_MESSAGE);
		        } else if (textFieldNIFCliente.getText().length() != 9) {
		            JOptionPane.showMessageDialog(null, "Insira um NIF válido" , "ERRO", JOptionPane.ERROR_MESSAGE);
		        } else {
		            try {
		                int novoNIF = Integer.parseInt(textFieldNIFCliente.getText());
		                
		                // Verificar se o NIF já existe no arquivo clientes.txt
		                String caminhoClientes = BaseDados.perfilUtilizador + "\\Documents\\CinelCars\\Ficheiros\\clientes.txt";
		                FileReader fileReader = new FileReader(caminhoClientes);
		                BufferedReader bufferedReader = new BufferedReader(fileReader);
		                String linha;
		                boolean nifExiste = false;
		                
		                while ((linha = bufferedReader.readLine()) != null) {
		                    String[] dadosCliente = linha.split(";");
		                    int nifExistente = Integer.parseInt(dadosCliente[1]);
		                    if (novoNIF == nifExistente) {
		                        nifExiste = true;
		                        break;
		                    }
		                }
		                fileReader.close();
		                
		                if (nifExiste) {
		                    JOptionPane.showMessageDialog(null, "Este NIF já está associado a um cliente!" , "ERRO", JOptionPane.ERROR_MESSAGE);
		                } else {
		                    try {
		                        int NIF = Integer.parseInt(textFieldNIFCliente.getText());
		                        ManipularClientes guardar = new ManipularClientes();
		                        
		                        if(radioFeminino.isSelected()) {
		                            genero = "Feminino";
		                        } else {
		                            genero = "Masculino";
		                        }
		                        
		                        //Obter próximo ID disponivel
		                        int id = ProximoID.obterProximoID();
		                        
		                        // Obter a data atual do sistema
		                        Date dataAtual = new Date();
		                        
		                        // Inserir a data no formato desejado (dd/MM/yyyy HH:mm:ss)
		                        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		                        String dataRegisto = formatter.format(dataAtual);
		                        
		                        guardar.GuardarCliente(id, NIF, textFieldNomeCliente.getText(), comboBoxLocalidade.getSelectedItem().toString(), genero, dataRegisto);
		                        
		                        // Atualizar o campo ID com o próximo ID disponível
		                        textFieldIDCliente.setText(String.valueOf(id+1));
		                        //Limpar os campos
		                        textFieldNIFCliente.setText("");
		                        textFieldNomeCliente.setText("");
		                        comboBoxLocalidade.setSelectedIndex(0);
		                        buttonGroup.clearSelection();
		                    } catch(Exception erro) {
		                        JOptionPane.showMessageDialog(null, "O NIF não está correto" , "ERRO", JOptionPane.ERROR_MESSAGE);
		                    }
		                }
		            } catch(Exception erro) {
		                JOptionPane.showMessageDialog(null, "O NIF não está correto" , "ERRO", JOptionPane.ERROR_MESSAGE);
		            }
		        }
		    }
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.setBounds(35, 220, 140, 32);
		contentPane.add(btnNewButton);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
			}
		});
		btnVoltar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnVoltar.setBounds(240, 220, 140, 32);
		contentPane.add(btnVoltar);
		
		
		buttonGroup.add(radioFeminino);
		radioFeminino.setFont(new Font("Tahoma", Font.BOLD, 14));
		radioFeminino.setBounds(294, 172, 111, 23);
		contentPane.add(radioFeminino);
		
		JRadioButton radioMasculino = new JRadioButton("Masculino");
		buttonGroup.add(radioMasculino);
		radioMasculino.setFont(new Font("Tahoma", Font.BOLD, 14));
		radioMasculino.setBounds(158, 172, 111, 23);
		contentPane.add(radioMasculino);
		
		
		comboBoxLocalidade.setBounds(158, 132, 220, 22);
		contentPane.add(comboBoxLocalidade);
		
		JLabel lblNewLabel_3 = new JLabel("Género");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_3.setBounds(35, 174, 88, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblLocalidade = new JLabel("Localidade");
		lblLocalidade.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblLocalidade.setBounds(35, 134, 88, 14);
		contentPane.add(lblLocalidade);
		
		JLabel lblNomeCliente = new JLabel("Nome Cliente");
		lblNomeCliente.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNomeCliente.setBounds(35, 102, 96, 14);
		contentPane.add(lblNomeCliente);
		
		textFieldNomeCliente = new JTextField();
		textFieldNomeCliente.setColumns(10);
		textFieldNomeCliente.setBounds(158, 101, 220, 20);
		contentPane.add(textFieldNomeCliente);
		
		textFieldNIFCliente = new JTextField();
		textFieldNIFCliente.setColumns(10);
		textFieldNIFCliente.setBounds(158, 71, 220, 20);
		contentPane.add(textFieldNIFCliente);
		
		JLabel lblNewLabel = new JLabel("NIF Cliente");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(35, 72, 88, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("ID Cliente");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(35, 41, 88, 14);
		contentPane.add(lblNewLabel_1);
		
		textFieldIDCliente = new JTextField();
		textFieldIDCliente.setEditable(false);
		textFieldIDCliente.setColumns(10);
		textFieldIDCliente.setBounds(158, 40, 220, 20);
		contentPane.add(textFieldIDCliente);
	}
}
