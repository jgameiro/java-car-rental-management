package pacotes;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ProcurarVeiculo extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
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
					ProcurarVeiculo frame = new ProcurarVeiculo();
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
	public ProcurarVeiculo() {
		setTitle("Procurar Veiculos");
		JComboBox comboBoxCodigo = new JComboBox();
		addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowOpened(WindowEvent e) {
				
				comboBoxCodigo.addItem("Escolha um código");
				ManipularVeiculos atualizarComboBoxCodigo = new ManipularVeiculos();
				atualizarComboBoxCodigo.InserirCodigoVeiculo(comboBoxCodigo);
			}
		});
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 658, 451);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCodigo = new JLabel("Codigo");
		lblCodigo.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCodigo.setBounds(51, 39, 113, 20);
		contentPane.add(lblCodigo);
		
		
		comboBoxCodigo.setBounds(148, 40, 249, 22);
		contentPane.add(comboBoxCodigo);
		
		JButton btnNewButton = new JButton("Procurar Veículo");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(comboBoxCodigo.getSelectedIndex() == 0)
				{
					JOptionPane.showMessageDialog(null, "Deve escolher um código", "ERRO", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					int codigo = Integer.parseInt(comboBoxCodigo.getSelectedItem().toString());
					ManipularVeiculos procurar = new ManipularVeiculos();
					procurar.ProcurarVeiculos(codigo, textFieldMatricula, textFieldMarca, textFieldModelo, textFieldKm);
					
					textFieldMatricula.setEnabled(true);
					textFieldMarca.setEnabled(true);
					textFieldModelo.setEnabled(true);
					textFieldKm.setEnabled(true);
					//btnAtualizarCliente.setEnabled(true);
				}
				
			}
			
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.setBounds(412, 28, 175, 42);
		contentPane.add(btnNewButton);
		
		JLabel lblMarca = new JLabel("Matricula");
		lblMarca.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMarca.setBounds(127, 97, 113, 20);
		contentPane.add(lblMarca);
		
		textFieldMatricula = new JTextField();
		textFieldMatricula.setEnabled(false);
		textFieldMatricula.setColumns(10);
		textFieldMatricula.setBounds(250, 99, 249, 20);
		contentPane.add(textFieldMatricula);
		
		JLabel lblMarca_1 = new JLabel("Marca");
		lblMarca_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMarca_1.setBounds(127, 133, 113, 20);
		contentPane.add(lblMarca_1);
		
		textFieldMarca = new JTextField();
		textFieldMarca.setEnabled(false);
		textFieldMarca.setColumns(10);
		textFieldMarca.setBounds(250, 133, 249, 20);
		contentPane.add(textFieldMarca);
		
		JLabel lblModelo = new JLabel("Modelo");
		lblModelo.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblModelo.setBounds(127, 174, 113, 19);
		contentPane.add(lblModelo);
		
		textFieldModelo = new JTextField();
		textFieldModelo.setEnabled(false);
		textFieldModelo.setColumns(10);
		textFieldModelo.setBounds(250, 173, 249, 20);
		contentPane.add(textFieldModelo);
		
		JLabel lblNewLabel_3 = new JLabel("Quilómetros");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_3.setBounds(127, 212, 113, 19);
		contentPane.add(lblNewLabel_3);
		
		textFieldKm = new JTextField();
		textFieldKm.setEnabled(false);
		textFieldKm.setColumns(10);
		textFieldKm.setBounds(250, 211, 249, 20);
		contentPane.add(textFieldKm);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
			}
		});
		btnVoltar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnVoltar.setBounds(222, 317, 175, 42);
		contentPane.add(btnVoltar);
	}
}
