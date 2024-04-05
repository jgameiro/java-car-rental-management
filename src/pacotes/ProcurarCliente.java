package pacotes;

import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.FileReader;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ProcurarCliente extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldNIFCliente;
	private JTextField textFieldNomeCliente;
	private JTextField textFieldLocalidade;
	private JTextField textFieldGenero;
	private JTextField textFieldDataRegisto;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProcurarCliente frame = new ProcurarCliente();
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
	public ProcurarCliente() {
		setTitle("Procurar Cliente");
		JComboBox comboBoxIDCliente = new JComboBox();
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				
				comboBoxIDCliente.addItem("Escolha um ID");
				ManipularClientes atualizarComboBoxID = new ManipularClientes();
				atualizarComboBoxID.InserirIDCliente(comboBoxIDCliente);
			}
		});
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 586, 435);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("NIF Cliente");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(102, 95, 113, 20);
		contentPane.add(lblNewLabel);
		
		textFieldNIFCliente = new JTextField();
		textFieldNIFCliente.setColumns(10);
		textFieldNIFCliente.setBounds(225, 97, 249, 20);
		contentPane.add(textFieldNIFCliente);
		
		JLabel lblNomeCliente = new JLabel("Nome Cliente");
		lblNomeCliente.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNomeCliente.setBounds(102, 131, 113, 20);
		contentPane.add(lblNomeCliente);
		
		textFieldNomeCliente = new JTextField();
		textFieldNomeCliente.setEnabled(false);
		textFieldNomeCliente.setColumns(10);
		textFieldNomeCliente.setBounds(225, 131, 249, 20);
		contentPane.add(textFieldNomeCliente);
		
		JLabel lblLocalidade = new JLabel("Localidade");
		lblLocalidade.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblLocalidade.setBounds(102, 172, 113, 19);
		contentPane.add(lblLocalidade);
		
		textFieldLocalidade = new JTextField();
		textFieldLocalidade.setEnabled(false);
		textFieldLocalidade.setColumns(10);
		textFieldLocalidade.setBounds(225, 171, 249, 20);
		contentPane.add(textFieldLocalidade);
		
		JLabel lblNewLabel_3 = new JLabel("Género");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_3.setBounds(102, 210, 113, 19);
		contentPane.add(lblNewLabel_3);
		
		textFieldGenero = new JTextField();
		textFieldGenero.setEnabled(false);
		textFieldGenero.setColumns(10);
		textFieldGenero.setBounds(225, 209, 249, 20);
		contentPane.add(textFieldGenero);
		
		JLabel lblDataDeRegisto = new JLabel("Data de Registo");
		lblDataDeRegisto.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDataDeRegisto.setBounds(102, 245, 113, 19);
		contentPane.add(lblDataDeRegisto);
		
		textFieldDataRegisto = new JTextField();
		textFieldDataRegisto.setEnabled(false);
		textFieldDataRegisto.setColumns(10);
		textFieldDataRegisto.setBounds(225, 244, 249, 20);
		contentPane.add(textFieldDataRegisto);
		
		JButton btnNewButton = new JButton("Procurar Cliente");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(comboBoxIDCliente.getSelectedIndex() == 0)
				{
					JOptionPane.showMessageDialog(null, "Deve escolher um ID", "ERRO", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					int id = Integer.parseInt(comboBoxIDCliente.getSelectedItem().toString());
					ManipularClientes procurar = new ManipularClientes();
					procurar.ProcurarClientes(id, textFieldNIFCliente, textFieldNomeCliente, textFieldLocalidade, textFieldGenero, textFieldDataRegisto);
					
					textFieldNIFCliente.setEnabled(true);
					textFieldNomeCliente.setEnabled(true);
					textFieldLocalidade.setEnabled(true);
					textFieldGenero.setEnabled(true);
					//btnAtualizarCliente.setEnabled(true);
				}
				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.setBounds(387, 26, 175, 42);
		contentPane.add(btnNewButton);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
			}
		});
		btnVoltar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnVoltar.setBounds(197, 315, 175, 42);
		contentPane.add(btnVoltar);
		
		JLabel lblId = new JLabel("ID Cliente");
		lblId.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblId.setBounds(26, 37, 113, 20);
		contentPane.add(lblId);
		
		
		comboBoxIDCliente.setBounds(123, 38, 249, 22);
		contentPane.add(comboBoxIDCliente);
	}
	
	public void exibirJanela(int NIF) {
	    // Código para exibir a janela ProcurarCliente
	    setVisible(true); // Este é um exemplo, você precisa adaptar conforme a estrutura da sua classe
	    
		String caminho = BaseDados.perfilUtilizador + "\\Documents\\CinelCars\\Ficheiros\\clientes.txt";
		int aux = 0;
		try {
			FileReader ler = new FileReader(caminho);
			BufferedReader dados = new BufferedReader(ler);
			
			String linha;
			//enquanto existir informação no ficheiro, a String linha é carregada com os dados
			while((linha = dados.readLine()) != null)
			{
				String[] coluna = linha.split(";");
				if(coluna[1].equals(String.valueOf(NIF)))
				{
					aux = 1;
					
					textFieldNIFCliente.setText(coluna[1]);
					textFieldNomeCliente.setText(coluna[2]);
					textFieldLocalidade.setText(coluna[3]);
					textFieldGenero.setText(coluna[4]);
					textFieldDataRegisto.setText(coluna[5]);
				}
				
			}
			//Fechamos o acesso ao ficheiro de texto
			ler.close();
		} catch (Exception erro) 
		{
			JOptionPane.showMessageDialog(null, "Algo correu mal! Contacte o Administrador", "ERRO", JOptionPane.ERROR_MESSAGE);
		}
		
	}
}
