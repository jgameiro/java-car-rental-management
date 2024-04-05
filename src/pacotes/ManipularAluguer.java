package pacotes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class ManipularAluguer {

    public void GuardarAluguer(String veiculo, String marca, String modelo, String funcionario, String nomeFuncionario, String cliente, String nomeCliente, String estado, String valor, String descricao, String data) {
        String caminho = BaseDados.perfilUtilizador + "\\Documents\\CinelCars\\Ficheiros\\aluguer.txt";
        String linha = veiculo + ";" + marca + ";" + modelo + ";" + funcionario + ";" + nomeFuncionario + ";" + cliente + ";" + nomeCliente + ";" + estado + ";" + valor + ";" + descricao + ";" + data;
        
        try {
            FileReader ler = new FileReader(caminho);
            BufferedReader procura = new BufferedReader(ler);

            String linha2;
            while ((linha2 = procura.readLine()) != null) {
                String coluna2[] = linha2.split(";");
                if (coluna2[0].equals(veiculo)) {
                    JOptionPane.showMessageDialog(null, "O veiculo já se encontra alugado", "Veículo Existente", JOptionPane.ERROR_MESSAGE);
                    return; // Se o veículo já existir, interrompe a execução
                }
            }
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Algo correu mal! Contacte o Administrador", "ERRO", JOptionPane.ERROR_MESSAGE);
        }

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
    
   /* public void receberVeiculo(String veiculo, String Cliente, String km) {
        String caminho1 = BaseDados.perfilUtilizador + "\\Documents\\CinelCars\\Ficheiros\\aluguer.txt";

        try {
            FileReader ler = new FileReader(caminho1);
            BufferedReader procura = new BufferedReader(ler);

            String linha;
            while ((linha = procura.readLine()) != null) {
                String[] colunas = linha.split(";");
                if (!veiculo.equals(colunas[0])) {
                    // Se a linha não corresponder ao veículo, a adicionamos à variável de linhas
                    linhas += linha + "\n";
                }
            }
 
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Algo correu mal! Contacte o Administrador", "ERRO", JOptionPane.ERROR_MESSAGE);
            erro.printStackTrace();
        }
    }*/
    
	public void ProcurarClientes(String matricula, JTextField textFieldNIFCliente)
	{
		
		String caminho = BaseDados.perfilUtilizador + "\\Documents\\CinelCars\\Ficheiros\\aluguer.txt";
		try {
			FileReader ler = new FileReader(caminho);
			BufferedReader dados = new BufferedReader(ler);
			
			String linha;
			//enquanto existir informação no ficheiro, a String linha é carregada com os dados
			while((linha = dados.readLine()) != null)
			{
				String[] coluna = linha.split(";");
				if(coluna[0].equals(matricula))
				{
					textFieldNIFCliente.setText(coluna[5]);				
				}
				
			}
			//Fechamos o acesso ao ficheiro de texto
			ler.close();
			
		} catch (Exception erro) 
		{
			JOptionPane.showMessageDialog(null, "Algo correu mal! Contacte o Administrador", "ERRO", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void ProcurarMatricula(String matricula, JTextField textFieldMarca, JTextField textFieldModelo)
	{
		
		String caminho = BaseDados.perfilUtilizador + "\\Documents\\CinelCars\\Ficheiros\\veiculos.txt";
		try {
			FileReader ler = new FileReader(caminho);
			BufferedReader dados = new BufferedReader(ler);
			
			String linha = matricula + textFieldMarca + textFieldModelo;
			//enquanto existir informação no ficheiro, a String linha é carregada com os dados
			while((linha = dados.readLine()) != null)
			{
				String[] coluna = linha.split(";");
				if(coluna[1].equals(matricula))
				{
					textFieldMarca.setText(coluna[2]);	
					textFieldModelo.setText(coluna[3]);
				}
				
			}
			//Fechamos o acesso ao ficheiro de texto
			ler.close();
			
		} catch (Exception erro) 
		{
			JOptionPane.showMessageDialog(null, "Algo correu mal! Contacte o Administrador", "ERRO", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void ProcurarNomeCliente(String nif, JTextField textFieldNome)
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
				if(coluna[1].equals(nif))
				{
					textFieldNome.setText(coluna[2]);				
				}
				
			}
			//Fechamos o acesso ao ficheiro de texto
			ler.close();
			
		} catch (Exception erro) 
		{
			JOptionPane.showMessageDialog(null, "Algo correu mal! Contacte o Administrador", "ERRO", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void ProcurarNomeFuncionario(String id, JTextField textFieldNome)
	{
		
		String caminho = BaseDados.perfilUtilizador + "\\Documents\\CinelCars\\Ficheiros\\funcionarios.txt";
		try {
			FileReader ler = new FileReader(caminho);
			BufferedReader dados = new BufferedReader(ler);
			
			String linha;
			//enquanto existir informação no ficheiro, a String linha é carregada com os dados
			while((linha = dados.readLine()) != null)
			{
				String[] coluna = linha.split(";");
				if(coluna[0].equals(id))
				{
					textFieldNome.setText(coluna[1]);				
				}
				
			}
			//Fechamos o acesso ao ficheiro de texto
			ler.close();
			
		} catch (Exception erro) 
		{
			JOptionPane.showMessageDialog(null, "Algo correu mal! Contacte o Administrador", "ERRO", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void CarregarListaDados()
	{
		//Garantir que apenas temos os dados existentes no ficheiro de texto
		BaseDados.dadosVeículos.clear();
		String caminho = BaseDados.perfilUtilizador + "\\Documents\\CinelCars\\Ficheiros\\veiculos.txt";
	
		try {
			FileReader ler = new FileReader(caminho);
			BufferedReader dados = new BufferedReader(ler);
			
			String linha;
			//enquanto existir informação no ficheiro, a String linha é carregada com os dados //Lê linha a linha e carrega-as no ArrayList
			while((linha = dados.readLine()) != null)
			{
				BaseDados.dadosVeículos.add(linha);
			}
			//Fechamos o acesso ao ficheiro de texto
			ler.close();
			
		} catch (Exception erro) 
		{
			JOptionPane.showMessageDialog(null, "Algo correu mal! Contacte o Administrador", "ERRO", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void AtualizarDadosVeiculo(String matricula, JTextField textFieldKm)
	{
		//Chamar método que permite carregar a lista de dados
		CarregarListaDados();
		
		for(int i=0; i<BaseDados.dadosVeículos.size(); i++)
		{
			String[] coluna = BaseDados.dadosVeículos.get(i).split(";");
			
			if(coluna[1].equals(matricula))
			{
				String id = coluna[0];
				String marca = coluna[2];
				String modelo = coluna[3];
	            int kmAtual = Integer.parseInt(coluna[4]); // Converter para inteiro
	            int novoKm = Integer.parseInt(textFieldKm.getText()); // Obter o novo valor de km do JTextField e converter para inteiro
	            int kmTotal = kmAtual + novoKm; // Calcular o novo total de km
	            textFieldKm.setText(String.valueOf(kmTotal)); // Definir o novo valor de km no JTextField
	            // Criação da linha de dados com a informação atualizada
	            String linha = id + ";" + matricula + ";" + marca + ";" + modelo + ";" + kmTotal;
				//Removo os dados antigos
				BaseDados.dadosVeículos.remove(i);
				//Insiro os novos dados na mesma posição que foram removidos
				BaseDados.dadosVeículos.add(i, linha);
			}
		}
		//Reposição da informação completa e atualizada no ficheiro de texto
		AtualizarFicheiro();
		
	}
	
	public void AtualizarFicheiro()
	{
		String caminho = BaseDados.perfilUtilizador + "\\Documents\\CinelCars\\Ficheiros\\veiculos.txt";
		try {
			
			FileWriter escrever = new FileWriter(caminho);
			BufferedWriter dados = new BufferedWriter (escrever);
			
			//Escrever a informação recebida no ficheiro
			for(int i=0; i<BaseDados.dadosVeículos.size(); i++)
			{
				//São carregados os dados guarfdados na BaseDados para o ficheiro de texto
				dados.write(BaseDados.dadosVeículos.get(i));
				dados.newLine();
			}
			dados.close();
			
			//JOptionPane.showMessageDialog(null, "Informação guardada com sucesso", "AVISO", JOptionPane.INFORMATION_MESSAGE);
			
		}catch(Exception erro)
		{
			JOptionPane.showMessageDialog(null, "Algo correu mal! Contacte o Administrador", "ERRO", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void AtualizarDadosAluguer(String matricula)
	{
		//Chamar método que permite carregar a lista de dados
		CarregarListaDadosAluguer();
		
		for(int i=0; i<BaseDados.dadosAluguer.size(); i++)
		{
			String[] coluna = BaseDados.dadosAluguer.get(i).split(";");
			
			if(coluna[0].equals(matricula))
			{
				String marca = coluna[1];
				String modelo = coluna[2];
				String idFuncionario = coluna[3];
				String nomeFuncionario = coluna[4];
				String nifCliente = coluna[5];
				String nomeCliente = coluna[6];
				String estado = coluna[7];
				String valor = coluna[8];
				String descricao = coluna[9];
				String dataRegisto = coluna[10];
	            // Criação da linha de dados com a informação atualizada
	            String linha = matricula + ";" + marca + ";" + modelo + ";" + idFuncionario + ";" + nomeFuncionario + ";" + nifCliente + ";" + 
	            		nomeCliente + ";" + estado + ";" + valor + ";" + descricao + ";" + dataRegisto;
				//Removo os dados antigos
				BaseDados.dadosAluguer.remove(i);
				//Insiro os novos dados na mesma posição que foram removidos
				//BaseDados.dadosAluguer.add(i, linha);
			}
		}
		//Reposição da informação completa e atualizada no ficheiro de texto
		AtualizarFicheiroAluguer();
		
	}
	
	public void CarregarListaDadosAluguer()
	{
		//Garantir que apenas temos os dados existentes no ficheiro de texto
		BaseDados.dadosAluguer.clear();
		String caminho = BaseDados.perfilUtilizador + "\\Documents\\CinelCars\\Ficheiros\\aluguer.txt";
	
		try {
			FileReader ler = new FileReader(caminho);
			BufferedReader dados = new BufferedReader(ler);
			
			String linha;
			//enquanto existir informação no ficheiro, a String linha é carregada com os dados //Lê linha a linha e carrega-as no ArrayList
			while((linha = dados.readLine()) != null)
			{
				BaseDados.dadosAluguer.add(linha);
			}
			//Fechamos o acesso ao ficheiro de texto
			ler.close();
			
		} catch (Exception erro) 
		{
			JOptionPane.showMessageDialog(null, "Algo correu mal! Contacte o Administrador", "ERRO", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void AtualizarFicheiroAluguer()
	{
		String caminho = BaseDados.perfilUtilizador + "\\Documents\\CinelCars\\Ficheiros\\aluguer.txt";
		try {
			
			FileWriter escrever = new FileWriter(caminho);
			BufferedWriter dados = new BufferedWriter (escrever);
			
			//Escrever a informação recebida no ficheiro
			for(int i=0; i<BaseDados.dadosAluguer.size(); i++)
			{
				//São carregados os dados guarfdados na BaseDados para o ficheiro de texto
				dados.write(BaseDados.dadosAluguer.get(i));
				dados.newLine();
			}
			dados.close();
			
			JOptionPane.showMessageDialog(null, "Informação guardada com sucesso", "AVISO", JOptionPane.INFORMATION_MESSAGE);
			
		}catch(Exception erro)
		{
			JOptionPane.showMessageDialog(null, "Algo correu mal! Contacte o Administrador", "ERRO", JOptionPane.ERROR_MESSAGE);
		}
	}
}
