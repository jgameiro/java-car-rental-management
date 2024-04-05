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
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RemoverVeiculo extends JFrame {

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
					RemoverVeiculo frame = new RemoverVeiculo();
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
	public RemoverVeiculo() {
		setTitle("Remover Veículo");
		JComboBox<String> comboBoxMatricula = new JComboBox<String>();
		JComboBox<String> comboBoxCodigo = new JComboBox<String>();
		JScrollPane scrollPane = new JScrollPane();
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnRemover = new JButton("Remover");
		btnRemover.setEnabled(false);
		contentPane.add(btnRemover);
	    btnRemover.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnRemover.setBounds(151, 387, 182, 36);
		

		
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
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 728, 471);
		
		scrollPane.setBounds(84, 134, 527, 239);
		contentPane.add(scrollPane);
		
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
                            btnRemover.setEnabled(true);
                            break; // Como encontramos o veículo, podemos sair do loop
                           
                        }
                        else if (colunas[1].equals(matriculaSelecionada))
                        {
                        	tabela.addRow(colunas);
                        	comboBoxCodigo.setSelectedIndex(0);
                            comboBoxMatricula.setSelectedIndex(0);
                            btnRemover.setEnabled(true);
                            break; // Como encontramos o veículo, podemos sair do loop
                        }
                        
                    }
                    
                    
                } catch (Exception erro) {
                    JOptionPane.showMessageDialog(null, "Algo correu mal! Contacte o Administrador", "ERRO", JOptionPane.ERROR_MESSAGE);
                }
            }
           
        });
        
       
        
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.setBounds(387, 60, 182, 36);
		contentPane.add(btnNewButton);
		
		JLabel lblMatricula = new JLabel("Matricula");
		lblMatricula.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMatricula.setBounds(84, 85, 80, 26);
		contentPane.add(lblMatricula);
		
		
		comboBoxMatricula.setBounds(174, 85, 182, 22);
		contentPane.add(comboBoxMatricula);
		
		
		comboBoxCodigo.setBounds(177, 50, 179, 22);
		contentPane.add(comboBoxCodigo);
		
		JLabel lblNewLabel_1_1 = new JLabel("Código");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(84, 46, 95, 26);
		contentPane.add(lblNewLabel_1_1);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
			}
			
		});
		btnVoltar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnVoltar.setBounds(372, 387, 182, 36);
		contentPane.add(btnVoltar);
		
		
		
		btnRemover.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        // Permite acessar à linha selecionada
		        int linha = table.getSelectedRow();

		        if (linha != -1) 
		        { // Verifica se alguma linha está selecionada
		            // StringBuilder para armazenar os dados do veículo selecionado
		            StringBuilder dadosCompletos = new StringBuilder();

		            // Vou percorrer as colunas da linha selecionada
		            for (int indiceColuna = 0; indiceColuna < 5; indiceColuna++) 
		            {
		                // Acesso ao conteúdo de cada coluna
		                dadosCompletos.append(table.getValueAt(linha, indiceColuna));
		                if (indiceColuna != 5) 
		                {
		                    dadosCompletos.append(";");
		                }
		            }

		            int resposta = JOptionPane.showConfirmDialog(null, dadosCompletos.toString(),"Tem a certeza que pretende eliminar?", JOptionPane.YES_NO_OPTION);

			            if (resposta == 0) 
			            {
			                // Remove a linha da tabela
			            	tabela.removeRow(linha);
							dadosCompletos.delete(0, 0);
			                String caminho = BaseDados.perfilUtilizador + "\\Documents\\CinelCars\\Ficheiros\\veiculos.txt";
			                try {
								
								FileWriter escrever = new FileWriter(caminho);
								BufferedWriter dados = new BufferedWriter (escrever);
								StringBuilder dadosCompletos2 = new StringBuilder();
								//Escrever a informação recebida no ficheiro
								for(int i=0; i<tabela.getRowCount(); i++)
								{
									for(int indiceColuna = 0; indiceColuna < 6; indiceColuna ++)
									{
										//acedo ao conteudo de cada coluna
										dadosCompletos2.append(tabela.getValueAt(i, indiceColuna));
										if(indiceColuna != 5)
											dadosCompletos2.append(";");
									}
									//São carregados os dados guarfdados na BaseDados para o ficheiro de texto
									//dados.write(BaseDados.dadosClientes.get(i));
									//dados.newLine();
									dadosCompletos.append("\n");
								}
								dados.write(dadosCompletos2.toString());
								dados.close();
								
								JOptionPane.showMessageDialog(null, "Informação atualizada com sucesso", "AVISO", JOptionPane.INFORMATION_MESSAGE);
								
							}catch(Exception erro)
							{
								JOptionPane.showMessageDialog(null, "Algo correu mal! Contacte o Administrador", "ERRO", JOptionPane.ERROR_MESSAGE);
							}
					
			        }
		        }
		    }
		    
		    
		});

	}
}
