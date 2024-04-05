package pacotes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class ManipularVeiculos {

	
	public void GuardarVeiculos(String codigo, String  matricula, String marca, String modelo, String km)
	{
		String caminho = BaseDados.perfilUtilizador + "\\Documents\\CinelCars\\Ficheiros\\veiculos.txt";
		
		String linha = codigo + ";" + matricula + ";" + marca + ";" + modelo + ";" + km;
		
		int aux = 0;
		try {
			
			FileReader ler = new FileReader(caminho);
			
			BufferedReader procura = new BufferedReader(ler);
			
			String linha2;
			while((linha2 = procura.readLine()) != null)
			{
				String coluna2[] = linha2.split(";");
				
				if(coluna2[0].equals(String.valueOf(codigo)))
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
		    JOptionPane.showMessageDialog(null, "O veiculo já existe", "Veículo Existente", JOptionPane.ERROR_MESSAGE);

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
	
	public int obterProximoCodigo() throws IOException {
        int proximoCodigo = 1; // Valor padrão se o arquivo estiver vazio
        try (BufferedReader reader = new BufferedReader(new FileReader(BaseDados.perfilUtilizador + "\\Documents\\CinelCars\\Ficheiros\\veiculos.txt"))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                // Dividir a linha em partes usando o delimitador ";"
                String[] partes = linha.split(";");
                // A primeira parte é o ID
                int id = Integer.parseInt(partes[0]);
                // Se o ID atual for maior que o próximo ID, atualize o próximo ID
                if (id >= proximoCodigo) {
                	proximoCodigo = id + 1;
                }
            }
        }
        return proximoCodigo;
	}
	
	public void InserirCodigoVeiculo(JComboBox<String> comboBox)
	{
		
		String caminho = BaseDados.perfilUtilizador + "\\Documents\\CinelCars\\Ficheiros\\veiculos.txt";
		
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
	
	public void ProcurarVeiculos(int codigo, JTextField textFieldMatricula, JTextField textFieldMarca, JTextField textFieldModelo, JTextField textFieldKm)
	{
		
		String caminho = BaseDados.perfilUtilizador + "\\Documents\\CinelCars\\Ficheiros\\veiculos.txt";
		int aux = 0;
		try {
			FileReader ler = new FileReader(caminho);
			BufferedReader dados = new BufferedReader(ler);
			
			String linha;
			//enquanto existir informação no ficheiro, a String linha é carregada com os dados
			while((linha = dados.readLine()) != null)
			{
				String[] coluna = linha.split(";");
				if(coluna[0].equals(String.valueOf(codigo)))
				{
					JOptionPane.showMessageDialog(null, "Veiculo Encontrado", "AVISO", JOptionPane.INFORMATION_MESSAGE);
					aux = 1;
					textFieldMatricula.setText(coluna[1]);
					textFieldMarca.setText(coluna[2]);
					textFieldModelo.setText(coluna[3]);
					textFieldKm.setText(coluna[4]);	
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
