package pacotes;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class Main extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
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
	public Main() {
		setTitle("CinelCars");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 699, 555);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Ficheiro");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Sair");
		mntmNewMenuItem.setIcon(new ImageIcon("C:\\Users\\USER\\Documents\\CinelCars\\Imagens\\exit.png"));
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int resultado = JOptionPane.showConfirmDialog(null, "Tem a certeza que pretende sair?", "AVISO", JOptionPane.YES_NO_OPTION);
				
				if (resultado == 0)
				{
					System.exit(0);
				}
			}
		});
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenu mnNewMenu_1 = new JMenu("Clientes");
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Inserir Cliente");
		mntmNewMenuItem_1.setIcon(new ImageIcon("C:\\Users\\USER\\Documents\\CinelCars\\Imagens\\client.png"));
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				InserirCliente abrir = new InserirCliente();
				abrir.setVisible(true);
				
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Procurar Cliente");
		mntmNewMenuItem_2.setIcon(new ImageIcon("C:\\Users\\USER\\Documents\\CinelCars\\Imagens\\procurarcliente.png"));
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ProcurarCliente abrir = new ProcurarCliente();
				abrir.setVisible(true);
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_2);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Atualizar Cliente");
		mntmNewMenuItem_3.setIcon(new ImageIcon("C:\\Users\\USER\\Documents\\CinelCars\\Imagens\\updatepeople.png"));
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				AtualizarCliente abrir = new AtualizarCliente();
				abrir.setVisible(true);
			}

		});
		mnNewMenu_1.add(mntmNewMenuItem_3);
		
		JMenu mnNewMenu_2 = new JMenu("Veículos");
		menuBar.add(mnNewMenu_2);
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Inserir Veículos");
		mntmNewMenuItem_4.setIcon(new ImageIcon("C:\\Users\\USER\\Documents\\CinelCars\\Imagens\\new-car.png"));
		mntmNewMenuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				InserirVeiculo abrir = new InserirVeiculo();
				abrir.setVisible(true);
			}
		});
		mnNewMenu_2.add(mntmNewMenuItem_4);
		
		JMenuItem mntmNewMenuItem_5 = new JMenuItem("Procurar Veículo");
		mntmNewMenuItem_5.setIcon(new ImageIcon("C:\\Users\\USER\\Documents\\CinelCars\\Imagens\\searchingcar.png"));
		mntmNewMenuItem_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ProcurarVeiculo abrir = new ProcurarVeiculo();
				abrir.setVisible(true);
			}
		});
		mnNewMenu_2.add(mntmNewMenuItem_5);
		
		JMenuItem mntmNewMenuItem_10 = new JMenuItem("Listar Frota");
		mntmNewMenuItem_10.setIcon(new ImageIcon("C:\\Users\\USER\\Documents\\CinelCars\\Imagens\\list.png"));
		mntmNewMenuItem_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ListarVeiculo abrir = new ListarVeiculo();
				abrir.setVisible(true);
			}
		});
		mnNewMenu_2.add(mntmNewMenuItem_10);
		
		JMenu mnNewMenu_3_1 = new JMenu("Funcionarios");
		menuBar.add(mnNewMenu_3_1);
		
		JMenuItem mntmNewMenuItem_7_1 = new JMenuItem("Novo Funcionário");
		mntmNewMenuItem_7_1.setIcon(new ImageIcon("C:\\Users\\USER\\Documents\\CinelCars\\Imagens\\division.png"));
		mntmNewMenuItem_7_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InserirFuncionario abrir = new InserirFuncionario();
				abrir.setVisible(true);
				
			}
		});
		mnNewMenu_3_1.add(mntmNewMenuItem_7_1);
		
		JMenu mnNewMenu_3 = new JMenu("Aluguer");
		menuBar.add(mnNewMenu_3);
		
		JMenuItem mntmNewMenuItem_7 = new JMenuItem("Registar Aluger");
		mntmNewMenuItem_7.setIcon(new ImageIcon("C:\\Users\\USER\\Documents\\CinelCars\\Imagens\\shopping.png"));
		mntmNewMenuItem_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegistarAluguer abrir = new RegistarAluguer();
				abrir.setVisible(true);
				
				
			}
		});
		mnNewMenu_3.add(mntmNewMenuItem_7);
		
		JMenuItem mntmNewMenuItem_8 = new JMenuItem("Listar Alugueres");
		mntmNewMenuItem_8.setIcon(new ImageIcon("C:\\Users\\USER\\Documents\\CinelCars\\Imagens\\list.png"));
		mntmNewMenuItem_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListarAluguer abrir = new ListarAluguer();
				abrir.setVisible(true);
				
			}
		});
		mnNewMenu_3.add(mntmNewMenuItem_8);
		
		JMenuItem mntmNewMenuItem_9 = new JMenuItem("Devolução de Veiculo");
		mntmNewMenuItem_9.setIcon(new ImageIcon("C:\\Users\\USER\\Documents\\CinelCars\\Imagens\\garage.png"));
		mntmNewMenuItem_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				DevolucaoVeiculo abrir = new DevolucaoVeiculo();
				abrir.setVisible(true);
			}
		});
		mnNewMenu_3.add(mntmNewMenuItem_9);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\USER\\Documents\\CinelCars\\Imagens\\car-rent.png"));
		lblNewLabel.setBounds(10, 11, 665, 474);
		contentPane.add(lblNewLabel);
	}
}
