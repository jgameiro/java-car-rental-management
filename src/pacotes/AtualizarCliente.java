package pacotes;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AtualizarCliente extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField textFieldDataRegisto;
	private JTextField textFieldGenero;
	private JTextField textFieldLocalidade;
	private JTextField textFieldNomeCliente;
	private JTextField textFieldNIFCliente;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AtualizarCliente frame = new AtualizarCliente();
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
	public AtualizarCliente() {
		setTitle("Atualizar Cliente");
		JComboBox<String> comboBox = new JComboBox<String>();
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				
				comboBox.addItem("Escolha um ID");
				ManipularClientes atualizarComboBoxID = new ManipularClientes();
				atualizarComboBoxID.InserirIDCliente(comboBox);
			}
		});
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 596, 346);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnAtualizarCliente = new JButton("Atualizar Cliente");
		btnAtualizarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(comboBox.getSelectedIndex() == 0)
				{
					JOptionPane.showMessageDialog(null, "Deve identificar o ID do cliente!", "ERRO", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					ManipularClientes enviarDados = new ManipularClientes();
					enviarDados.AtualizarDadosCliente(comboBox.getSelectedItem().toString(), textFieldNIFCliente.getText(), textFieldNomeCliente.getText(), textFieldLocalidade.getText(), textFieldGenero.getText(), textFieldDataRegisto.getText());
				
					textFieldNIFCliente.setText("");
					textFieldNomeCliente.setText("");
					textFieldLocalidade.setText("");
					textFieldGenero.setText("");
					textFieldDataRegisto.setText("");
					comboBox.setSelectedIndex(0);
				}
			}
		});
		btnAtualizarCliente.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnAtualizarCliente.setEnabled(false);
		btnAtualizarCliente.setBounds(226, 260, 156, 24);
		contentPane.add(btnAtualizarCliente);
		
		JButton btnNewButtonVoltar = new JButton("Voltar");
		btnNewButtonVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
			}
		});
		btnNewButtonVoltar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButtonVoltar.setBounds(428, 260, 138, 24);
		contentPane.add(btnNewButtonVoltar);
		
		textFieldDataRegisto = new JTextField();
		textFieldDataRegisto.setEnabled(false);
		textFieldDataRegisto.setColumns(10);
		textFieldDataRegisto.setBounds(226, 218, 148, 20);
		contentPane.add(textFieldDataRegisto);
		
		JLabel lblDataRegisto = new JLabel("Data Registo");
		lblDataRegisto.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDataRegisto.setBounds(94, 215, 106, 22);
		contentPane.add(lblDataRegisto);
		
		JLabel lblGnero = new JLabel("Género");
		lblGnero.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblGnero.setBounds(94, 182, 106, 22);
		contentPane.add(lblGnero);
		
		textFieldGenero = new JTextField();
		textFieldGenero.setEnabled(false);
		textFieldGenero.setColumns(10);
		textFieldGenero.setBounds(226, 185, 148, 20);
		contentPane.add(textFieldGenero);
		
		JLabel lblLocalidade = new JLabel("Localidade");
		lblLocalidade.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblLocalidade.setBounds(94, 149, 106, 22);
		contentPane.add(lblLocalidade);
		
		textFieldLocalidade = new JTextField();
		textFieldLocalidade.setEnabled(false);
		textFieldLocalidade.setColumns(10);
		textFieldLocalidade.setBounds(226, 148, 148, 20);
		contentPane.add(textFieldLocalidade);
		
		JLabel lblNomeCliente = new JLabel("Nome Cliente");
		lblNomeCliente.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNomeCliente.setBounds(94, 115, 106, 22);
		contentPane.add(lblNomeCliente);
		
		textFieldNomeCliente = new JTextField();
		textFieldNomeCliente.setEnabled(false);
		textFieldNomeCliente.setColumns(10);
		textFieldNomeCliente.setBounds(226, 117, 148, 20);
		contentPane.add(textFieldNomeCliente);
		
		JLabel lblNewLabel = new JLabel("NIF Cliente");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(94, 82, 106, 22);
		contentPane.add(lblNewLabel);
		
		
		comboBox.setFont(new Font("Tahoma", Font.BOLD, 14));
		comboBox.setBounds(226, 41, 148, 22);
		contentPane.add(comboBox);
		
		JButton btnNewButton = new JButton("Procurar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(comboBox.getSelectedIndex() == 0)
				{
					JOptionPane.showMessageDialog(null, "Deve escolher um ID", "ERRO", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					int id = Integer.parseInt(comboBox.getSelectedItem().toString());
					ManipularClientes procurar = new ManipularClientes();
					procurar.ProcurarClientes(id, textFieldNIFCliente, textFieldNomeCliente, textFieldLocalidade, textFieldGenero, textFieldDataRegisto);
					
					textFieldNIFCliente.setEnabled(true);
					textFieldNomeCliente.setEnabled(true);
					textFieldLocalidade.setEnabled(true);
					textFieldGenero.setEnabled(true);
					btnAtualizarCliente.setEnabled(true);
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.setBounds(399, 40, 116, 24);
		contentPane.add(btnNewButton);
		
		textFieldNIFCliente = new JTextField();
		textFieldNIFCliente.setEnabled(false);
		textFieldNIFCliente.setColumns(10);
		textFieldNIFCliente.setBounds(226, 86, 148, 20);
		contentPane.add(textFieldNIFCliente);
		
		JLabel lblIdCliente = new JLabel("ID Cliente");
		lblIdCliente.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblIdCliente.setBounds(94, 41, 106, 22);
		contentPane.add(lblIdCliente);
	}

}
