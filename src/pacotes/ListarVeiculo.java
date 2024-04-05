package pacotes;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ListarVeiculo extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListarVeiculo frame = new ListarVeiculo();
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
	public ListarVeiculo() {
		setTitle("Listar Veículos");
		JComboBox<String> comboBoxMatricula = new JComboBox<String>();
		JComboBox<String> comboBoxCodigo = new JComboBox<String>();
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 609, 433);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(25, 115, 527, 239);
		contentPane.add(scrollPane);

		table = new JTable();
		DefaultTableModel tabela = new DefaultTableModel();
		String[] colunas = {"Código", "Matricula", "Marca", "Modelo", "Quilómetros"};
		tabela.setColumnIdentifiers(colunas);
		table.setModel(tabela);
		scrollPane.setViewportView(table);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				comboBoxCodigo.addItem("Escolha um código");
				comboBoxMatricula.addItem("Escolha uma Matricula");
				
				String caminho = BaseDados.perfilUtilizador + "\\Documents\\CinelCars\\Ficheiros\\veiculos.txt";
				try (FileReader ler = new FileReader(caminho);
					 BufferedReader procura = new BufferedReader(ler)) {

					String linha;
					while ((linha = procura.readLine()) != null) {
						String[] colunas = linha.split(";");
						tabela.addRow(colunas);
					}

				} catch (Exception erro) {
					JOptionPane.showMessageDialog(null, "Algo correu mal! Contacte o Administrador", "ERRO", JOptionPane.ERROR_MESSAGE);
				}
				
				try {
					FileReader ler = new FileReader(caminho);
					BufferedReader dados = new BufferedReader(ler);
					
					String linha;
					//enquanto existir informação no ficheiro, a String linha é carregada com os dados
					while((linha = dados.readLine()) != null)
					{
						String[] coluna = linha.split(";");
						comboBoxCodigo.addItem(coluna[0]);
					}
					//Fechamos o acesso ao ficheiro de texto
					ler.close();

				} catch (Exception erro) 
				{
					JOptionPane.showMessageDialog(null, "Algo correu mal! Contacte o Administrador", "ERRO", JOptionPane.ERROR_MESSAGE);
				}
				try {
					FileReader ler = new FileReader(caminho);
					BufferedReader dados = new BufferedReader(ler);
					
					String linha;
					//enquanto existir informação no ficheiro, a String linha é carregada com os dados
					while((linha = dados.readLine()) != null)
					{
						String[] coluna = linha.split(";");
						comboBoxMatricula.addItem(coluna[1]);
					}
					//Fechamos o acesso ao ficheiro de texto
					ler.close();

				} catch (Exception erro) 
				{
					JOptionPane.showMessageDialog(null, "Algo correu mal! Contacte o Administrador", "ERRO", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

        JButton btnNewButton = new JButton("Pesquisar");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Ação do botão de pesquisa
                // Limpar o conteúdo da tabela
                tabela.setRowCount(0);

                String caminho = BaseDados.perfilUtilizador + "\\Documents\\CinelCars\\Ficheiros\\veiculos.txt";
                String codigoSelecionado = (String) comboBoxCodigo.getSelectedItem();
                String matriculaSelecionada = (String) comboBoxMatricula.getSelectedItem();
                try (FileReader ler = new FileReader(caminho);
                     BufferedReader procura = new BufferedReader(ler)) {

                    String linha;
                    while ((linha = procura.readLine()) != null) {
                        String[] colunas = linha.split(";");
                        if (colunas[0].equals(codigoSelecionado)) {
                            tabela.addRow(colunas);
                            comboBoxCodigo.setSelectedIndex(0);
                            comboBoxMatricula.setSelectedIndex(0);
                            break; // Como encontramos o veículo, podemos sair do loop
                           
                        }
                        else if (colunas[1].equals(matriculaSelecionada))
                        {
                        	tabela.addRow(colunas);
                        	comboBoxCodigo.setSelectedIndex(0);
                            comboBoxMatricula.setSelectedIndex(0);
                            break; // Como encontramos o veículo, podemos sair do loop
                        }
                    }
                } catch (Exception erro) {
                    JOptionPane.showMessageDialog(null, "Algo correu mal! Contacte o Administrador", "ERRO", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.setBounds(327, 31, 182, 22);
		contentPane.add(btnNewButton);

		JLabel lblMatricula = new JLabel("Matricula");
		lblMatricula.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMatricula.setBounds(25, 66, 80, 26);
		contentPane.add(lblMatricula);

		
		comboBoxMatricula.setBounds(115, 66, 182, 22);
		contentPane.add(comboBoxMatricula);

		
		comboBoxCodigo.setBounds(118, 31, 179, 22);
		contentPane.add(comboBoxCodigo);

		JLabel lblNewLabel_1_1 = new JLabel("Código");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(25, 27, 95, 26);
		contentPane.add(lblNewLabel_1_1);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
			}
		});
		btnVoltar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnVoltar.setBounds(327, 64, 182, 22);
		contentPane.add(btnVoltar);
	}
}

