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
import javax.swing.JTable;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ListarAluguer extends JFrame {

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
					ListarAluguer frame = new ListarAluguer();
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
	public ListarAluguer() {
		JComboBox<String> comboBoxFuncionario = new JComboBox<String>();
		JComboBox<String> comboBoxMatricula = new JComboBox<String>();
		
		JScrollPane scrollPane = new JScrollPane();
		table = new JTable();
		DefaultTableModel tabela = new DefaultTableModel();
		String[] colunas = {"Matrícula", "Marca", "ID Funcionário", "Nome Funcionário", "Alugado", "Valor"};
		tabela.setColumnIdentifiers(colunas);
		table.setModel(tabela);
		scrollPane.setViewportView(table);
		
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {

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
				
                comboBoxMatricula.addItem("Escolha uma Matricula");
                String caminhoVeiculos = BaseDados.perfilUtilizador + "\\Documents\\CinelCars\\Ficheiros\\veiculos.txt";
        		
        		try {
        			FileReader ler = new FileReader(caminhoVeiculos);
        			BufferedReader dados = new BufferedReader(ler);
        			
        			String linha;
        			//enquanto existir informação no ficheiro, a String linha é carregada com os dados
        			while((linha = dados.readLine()) != null)
        			{
        				String[] coluna = linha.split(";");
        				comboBoxMatricula.addItem(coluna[1]);	
        			}

        		} catch (Exception erro) 
        		{
        			JOptionPane.showMessageDialog(null, "Algo correu mal! Contacte o Administrador", "ERRO", JOptionPane.ERROR_MESSAGE);
        		}
        		

                String caminho = BaseDados.perfilUtilizador + "\\Documents\\CinelCars\\Ficheiros\\aluguer.txt";

                // Limpar a tabela
                tabela.setRowCount(0);

                try (FileReader ler = new FileReader(caminho);
                     BufferedReader procura = new BufferedReader(ler)) {

                    String linha;
                    while ((linha = procura.readLine()) != null) {
                        // Dividir a linha em partes usando ";" como delimitador
                        String[] colunas = linha.split(";");

                        // Criar um array para armazenar os dados da linha
                        String[] dados = new String[6];
                        
                        // Atribuir os valores aos campos correspondentes
                        dados[0] = colunas[0]; // Matricula
                        dados[1] = colunas[1]; // Marca
                        dados[2] = colunas[3]; // ID Funcionario
                        dados[3] = colunas[4]; // Nome Funcionario
                        dados[4] = "Sim"; // Campo Alugado
                        dados[5] = colunas[8]; // Campo Valor
                        // Adicionar a linha à tabela
                        tabela.addRow(dados);
                    }
                } catch (Exception erro) {
                    erro.printStackTrace(); // Se ocorrer um erro durante a leitura do arquivo
                }
            }
        });
		setTitle("Listar Aluguer");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 622, 430);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		scrollPane.setBounds(40, 143, 527, 239);
		contentPane.add(scrollPane);
		

		
        JButton btnNewButton = new JButton("Pesquisar");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Ação do botão de pesquisa
                // Limpar o conteúdo da tabela
                tabela.setRowCount(0);

                String caminho = BaseDados.perfilUtilizador + "\\Documents\\CinelCars\\Ficheiros\\aluguer.txt";
                String funcionarioSelecionado = (String) comboBoxFuncionario.getSelectedItem();
                String matriculaSelecionada = (String) comboBoxMatricula.getSelectedItem();
                try (FileReader ler = new FileReader(caminho);
                     BufferedReader procura = new BufferedReader(ler)) {

                    String linha;
                    while ((linha = procura.readLine()) != null) {
                        String[] colunas = linha.split(";");
                        if (colunas[3].equals(funcionarioSelecionado)) {
                            // Criar um array para armazenar os dados da linha
                            // Criar um array para armazenar os dados da linha
                            String[] dados = new String[6];
                            
                            // Atribuir os valores aos campos correspondentes
                            dados[0] = colunas[0]; // Matricula
                            dados[1] = colunas[1]; // Marca
                            dados[2] = colunas[3]; // ID Funcionario
                            dados[3] = colunas[4]; // Nome Funcionario
                            dados[4] = "Sim"; // Campo Alugado
                            dados[5] = colunas[8]; // Campo Valor
                            // Adicionar a linha à tabela
                            tabela.addRow(dados);
                        	comboBoxFuncionario.setSelectedIndex(0);
                            comboBoxMatricula.setSelectedIndex(0);
                            break; // Como encontramos o veículo, podemos sair do loop
                           
                        }
                        else if (colunas[0].equals(matriculaSelecionada))
                        {
                            // Criar um array para armazenar os dados da linha
                            // Criar um array para armazenar os dados da linha
                            String[] dados = new String[6];
                            
                            // Atribuir os valores aos campos correspondentes
                            dados[0] = colunas[0]; // Matricula
                            dados[1] = colunas[1]; // Marca
                            dados[2] = colunas[3]; // ID Funcionario
                            dados[3] = colunas[4]; // Nome Funcionario
                            dados[4] = "Sim"; // Campo Alugado
                            dados[5] = colunas[8]; // Campo Valor
                            // Adicionar a linha à tabela
                            tabela.addRow(dados);
                        	comboBoxFuncionario.setSelectedIndex(0);
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
		btnNewButton.setBounds(363, 49, 182, 27);
		contentPane.add(btnNewButton);
		
		JLabel lblMatrcula = new JLabel("Matrícula");
		lblMatrcula.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMatrcula.setBounds(40, 84, 80, 22);
		contentPane.add(lblMatrcula);
		
		
		comboBoxMatricula.setBounds(158, 84, 182, 22);
		contentPane.add(comboBoxMatricula);
		
		
		comboBoxFuncionario.setBounds(158, 49, 182, 22);
		contentPane.add(comboBoxFuncionario);
		
		JLabel lblNewLabel_1_1 = new JLabel("ID Funcionário");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(40, 49, 103, 20);
		contentPane.add(lblNewLabel_1_1);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
			}
		});
		btnVoltar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnVoltar.setBounds(363, 84, 182, 27);
		contentPane.add(btnVoltar);
	}

}
