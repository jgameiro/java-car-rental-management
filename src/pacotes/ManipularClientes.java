package pacotes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class ManipularClientes {
	
	public void GuardarCliente(int id, int NIF, String nome, String localidade, String genero, String dataRegisto)
	{
		String caminho = BaseDados.perfilUtilizador + "\\Documents\\CinelCars\\Ficheiros\\clientes.txt";
		
		String linha = id + ";" + NIF + ";" + nome + ";" + localidade + ";" + genero + ";" + dataRegisto;
		
		int aux = 0;
		try {
			
			FileReader ler = new FileReader(caminho);
			
			BufferedReader procura = new BufferedReader(ler);
			
			String linha2;
			while((linha2 = procura.readLine()) != null)
			{
				String coluna2[] = linha2.split(";");
				
				if(coluna2[0].equals(String.valueOf(NIF)))
				{
					aux = 1;
				}
			}
			
		}catch(Exception erro)
		{
			JOptionPane.showMessageDialog(null, "Algo correu mal! Contacte o Administrador", "ERRO", JOptionPane.ERROR_MESSAGE);
		}
		
		if (aux == 1)
		{
			//JOptionPane.showMessageDialog(null, "O cliente já existe", "ERRO", JOptionPane.ERROR_MESSAGE);
		    int resposta = JOptionPane.showConfirmDialog(null, "O cliente já existe. Deseja verificar os dados do cliente?", "Cliente Existente", JOptionPane.YES_NO_OPTION);

		    if (resposta == 0) {
		        // Invocar um método para abrir a janela de Procurar Cliente e preencher os com os dados existentes
		        ProcurarCliente obterCliente = new ProcurarCliente(); // Instancia a janela ProcurarCliente
		        obterCliente.exibirJanela(NIF); // Método para exibir a janela, você precisa ter um método assim em sua classe ProcurarCliente
		    }
		}
		
		else
		{
		
			try {
				
				FileWriter escrever = new FileWriter(caminho, true);
				BufferedWriter dados = new BufferedWriter (escrever);
				
				//Escrever a informação recebida no ficheiro
				dados.write(linha);
				dados.newLine();
				dados.close();
				
				JOptionPane.showMessageDialog(null, "Informação guardada com sucesso", "AVISO", JOptionPane.INFORMATION_MESSAGE);
				
			}catch(Exception erro)
			{
				JOptionPane.showMessageDialog(null, "Algo correu mal! Contacte o Administrador", "ERRO", JOptionPane.ERROR_MESSAGE);
			}
		
		}
		
	}
	
	public void ProcurarClientes(int id, JTextField textFieldNIF, JTextField textFieldNome, JTextField textFieldLocalidade, JTextField textFieldGenero, JTextField textFieldData)
	{
		
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
				if(coluna[0].equals(String.valueOf(id)))
				{
					JOptionPane.showMessageDialog(null, "Cliente Encontrado", "AVISO", JOptionPane.INFORMATION_MESSAGE);
					aux = 1;
					textFieldNIF.setText(coluna[1]);
					textFieldNome.setText(coluna[2]);
					textFieldLocalidade.setText(coluna[3]);
					textFieldGenero.setText(coluna[4]);
					textFieldData.setText(coluna[5]);				}
				
			}
			//Fechamos o acesso ao ficheiro de texto
			ler.close();
			if(aux == 0)
			{
				JOptionPane.showMessageDialog(null, "O cliente não existe", "ERRO", JOptionPane.ERROR_MESSAGE);
				
				textFieldNome.setText("");
				textFieldLocalidade.setText("");
				textFieldGenero.setText("");
			}
		} catch (Exception erro) 
		{
			JOptionPane.showMessageDialog(null, "Algo correu mal! Contacte o Administrador", "ERRO", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public int obterProximoID() throws IOException {
        int proximoID = 1; // Valor padrão se o arquivo estiver vazio
        try (BufferedReader reader = new BufferedReader(new FileReader(BaseDados.perfilUtilizador + "\\Documents\\CinelCars\\Ficheiros\\clientes.txt"))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                // Dividir a linha em partes usando o delimitador ";"
                String[] partes = linha.split(";");
                // A primeira parte é o ID
                int id = Integer.parseInt(partes[0]);
                // Se o ID atual for maior que o próximo ID, atualize o próximo ID
                if (id >= proximoID) {
                    proximoID = id + 1;
                }
            }
        }
        return proximoID;
	}
	
	public void InserirIDCliente(JComboBox<String> comboBox)
	{
		
		String caminho = BaseDados.perfilUtilizador + "\\Documents\\CinelCars\\Ficheiros\\clientes.txt";
		
		try {
			FileReader ler = new FileReader(caminho);
			BufferedReader dados = new BufferedReader(ler);
			
			String linha;
			//enquanto existir informação no ficheiro, a String linha é carregada com os dados
			while((linha = dados.readLine()) != null)
			{
				String[] coluna = linha.split(";");
				comboBox.addItem(coluna[0]);	
			}

		} catch (Exception erro) 
		{
			JOptionPane.showMessageDialog(null, "Algo correu mal! Contacte o Administrador", "ERRO", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	
	public void CarregarListaDados()
	{
		//Garantir que apenas temos os dados existentes no ficheiro de texto
		BaseDados.dadosClientes.clear();
		String caminho = BaseDados.perfilUtilizador + "\\Documents\\CinelCars\\Ficheiros\\clientes.txt";
	
		try {
			FileReader ler = new FileReader(caminho);
			BufferedReader dados = new BufferedReader(ler);
			
			String linha;
			//enquanto existir informação no ficheiro, a String linha é carregada com os dados //Lê linha a linha e carrega-as no ArrayList
			while((linha = dados.readLine()) != null)
			{
				BaseDados.dadosClientes.add(linha);
			}
			//Fechamos o acesso ao ficheiro de texto
			ler.close();
			
		} catch (Exception erro) 
		{
			JOptionPane.showMessageDialog(null, "Algo correu mal! Contacte o Administrador", "ERRO", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void AtualizarFicheiro()
	{
		String caminho = BaseDados.perfilUtilizador + "\\Documents\\CinelCars\\Ficheiros\\clientes.txt";
		try {
			
			FileWriter escrever = new FileWriter(caminho);
			BufferedWriter dados = new BufferedWriter (escrever);
			
			//Escrever a informação recebida no ficheiro
			for(int i=0; i<BaseDados.dadosClientes.size(); i++)
			{
				//São carregados os dados guarfdados na BaseDados para o ficheiro de texto
				dados.write(BaseDados.dadosClientes.get(i));
				dados.newLine();
			}
			dados.close();
			
			JOptionPane.showMessageDialog(null, "Informação guardada com sucesso", "AVISO", JOptionPane.INFORMATION_MESSAGE);
			
		}catch(Exception erro)
		{
			JOptionPane.showMessageDialog(null, "Algo correu mal! Contacte o Administrador", "ERRO", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void AtualizarDadosCliente(String id, String NIF, String nome, String localidade, String genero, String data)
	{
		//Chamar método que permite carregar a lista de dados
		CarregarListaDados();
		
		for(int i=0; i<BaseDados.dadosClientes.size(); i++)
		{
			String[] coluna = BaseDados.dadosClientes.get(i).split(";");
			
			if(coluna[0].equals(id))
			{
				//Criação da linha de dados com a informação atualizada
				String linha = id + ";" + NIF + ";" + nome + ";" + localidade + ";" + genero + ";" + data;
				//Removo os dados antigos
				BaseDados.dadosClientes.remove(i);
				//Insiro os novos dados na mesma posição que foram removidos
				BaseDados.dadosClientes.add(i, linha);
			}
		}
		//Reposição da informação completa e atualizada no ficheiro de texto
		
		AtualizarFicheiro();
		
	}
	
}
	

