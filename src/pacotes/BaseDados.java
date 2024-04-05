package pacotes;

import java.util.ArrayList;

public class BaseDados {
	
	static ArrayList<String> dadosClientes = new ArrayList <String>();
	static ArrayList<String> dadosVeículos = new ArrayList <String>();
	static ArrayList<String> dadosFuncionarios = new ArrayList <String>();
	static ArrayList<String> dadosAluguer = new ArrayList <String>();
	//O USERPROFILE permite descubrir o utilizador ativo
	static String perfilUtilizador = System.getenv("USERPROFILE");
	
}