package pacotes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

public class ManipularFuncionarios {

	public void GuardarFuncionarios(String id, String nomeFuncionario) {
		// TODO Apêndice de método gerado automaticamente
		String caminho = BaseDados.perfilUtilizador + "\\Documents\\CinelCars\\Ficheiros\\funcionarios.txt";
		
		String linha = id + ";" + nomeFuncionario;
		try
		{
			FileWriter escrever = new FileWriter(caminho, true);
			BufferedWriter dados = new BufferedWriter (escrever);
			
			//Escrever a informação recebida no ficheiro
			dados.write(linha);
			dados.newLine();
			dados.close();
			
			JOptionPane.showMessageDialog(null, "Informação guardada com sucesso", "AVISO", JOptionPane.INFORMATION_MESSAGE);
		}
		catch(Exception erro)
		{
			JOptionPane.showMessageDialog(null, "Algo correu mal! Contacte o Administrador", "ERRO", JOptionPane.ERROR_MESSAGE);
		}
				
	}
	
	
	public int obterProximoCodigo() throws IOException {
        int proximoCodigo = 1; // Valor padrão se o arquivo estiver vazio
        try (BufferedReader reader = new BufferedReader(new FileReader(BaseDados.perfilUtilizador + "\\Documents\\CinelCars\\Ficheiros\\funcionarios.txt"))) {
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

}
