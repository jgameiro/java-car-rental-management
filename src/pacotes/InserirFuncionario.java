package pacotes;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class InserirFuncionario extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldNomeFuncionario;
	private JTextField textFieldID;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InserirFuncionario frame = new InserirFuncionario();
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
	public InserirFuncionario() {
		setTitle("Inserir Funcionários");
		
		ManipularFuncionarios ProximoID = new ManipularFuncionarios();
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				
				try {
                    // Lógica para obter o próximo ID disponível usando o ManipularClientes
                    int proximoID = ProximoID.obterProximoCodigo();
                    textFieldID.setText(String.valueOf(proximoID));
                } catch (IOException ex) 
				{
                    ex.printStackTrace();
                }
				
			
			}
		});
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 448, 285);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("ID Funcionario");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(25, 48, 113, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNomeFuncionario = new JLabel("Nome Funcionario");
		lblNomeFuncionario.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNomeFuncionario.setBounds(25, 109, 131, 14);
		contentPane.add(lblNomeFuncionario);
		
		textFieldNomeFuncionario = new JTextField();
		textFieldNomeFuncionario.setColumns(10);
		textFieldNomeFuncionario.setBounds(166, 108, 220, 20);
		contentPane.add(textFieldNomeFuncionario);
		
		textFieldID = new JTextField();
		textFieldID.setEditable(false);
		textFieldID.setColumns(10);
		textFieldID.setBounds(166, 47, 220, 20);
		contentPane.add(textFieldID);
		
		JButton btnInserirFuncionario = new JButton("Inserir Funcionario");
		btnInserirFuncionario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(textFieldID.getText().equals("") || textFieldNomeFuncionario.getText().equals("")) 
				{
		            JOptionPane.showMessageDialog(null, "Tem de preencher os campos!!" , "ERRO", JOptionPane.ERROR_MESSAGE);
		        }  
				else 
				{
					try {
					//Obter próximo ID disponivel
                    int id = ProximoID.obterProximoCodigo();
                    textFieldID.setText(String.valueOf(id));
					ManipularFuncionarios guardar = new ManipularFuncionarios();
		            guardar.GuardarFuncionarios(textFieldID.getText(), textFieldNomeFuncionario.getText());
		                        
		            // Atualizar o campo ID com o próximo ID disponível
		            textFieldID.setText(String.valueOf(id+1));
		            //Limpar os campos
		            textFieldNomeFuncionario.setText("");            
		             	 
					}catch(Exception erro) {
		                        JOptionPane.showMessageDialog(null, "O NIF não está correto" , "ERRO", JOptionPane.ERROR_MESSAGE);
		                    }
				}
			}
		     
		});
		btnInserirFuncionario.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnInserirFuncionario.setBounds(25, 189, 176, 32);
		contentPane.add(btnInserirFuncionario);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
			}
		});
		btnVoltar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnVoltar.setBounds(232, 189, 176, 32);
		contentPane.add(btnVoltar);
	}
}
