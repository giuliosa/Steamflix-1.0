package br.aeso.Steamflix.main;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import br.aeso.Steamflix.Cadastro.Cadastro;
import br.aeso.Steamflix.Cadastro.CampoVazioException;
import br.aeso.Steamflix.Cadastro.CamposNulosCadastro;
import br.aeso.Steamflix.Cliente.Cliente;
import br.aeso.Steamflix.Fachada.Fachada;

public class TelaLogin extends JFrame {

	private JPanel contentPane;
	private JTextField loginField;
	private JPasswordField senhaField;
	private JButton entrarBotao;
	private JLabel lblNoCadastrado;
	private JLabel lblLogo;
	private TelaCadastro telaCadastro;
	private TelaCliente telaCliente;
	Fachada fachada = Fachada.getInstance();
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaLogin frame = new TelaLogin();
					frame.setVisible(true);
					frame.setResizable(false);
					frame.setTitle("SteamFlix - Login");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaLogin() {

		start();
	}

	public void start() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 368, 435);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel loginLabel = new JLabel("Login");
		loginLabel.setBounds(161, 139, 39, 15);
		contentPane.add(loginLabel);

		loginField = new JTextField();
		loginField.setBounds(124, 168, 114, 19);
		contentPane.add(loginField);
		loginField.setColumns(10);

		JLabel senhaLabel = new JLabel("Senha");
		senhaLabel.setBounds(158, 199, 45, 15);
		contentPane.add(senhaLabel);

		senhaField = new JPasswordField();
		senhaField.setColumns(10);
		senhaField.setBounds(124, 228, 114, 19);
		contentPane.add(senhaField);

		entrarBotao = new JButton("Entrar");
		entrarBotao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					entrar();
				} catch (CampoVazioException e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
				}				
			}
		});
		entrarBotao.setBounds(122, 259, 117, 25);
		contentPane.add(entrarBotao);

		lblNoCadastrado = new JLabel("Não é cadastrado? Cadastre-se");
		lblNoCadastrado.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				telaCadastro = new TelaCadastro();
				telaCadastro.setVisible(true);
				telaCadastro
						.setTitle("SteamFlix - Cadastro Cliente/Fornecedor");
			}
		});
		lblNoCadastrado.setBounds(69, 313, 224, 15);
		contentPane.add(lblNoCadastrado);

		lblLogo = new JLabel("LOGO");
		lblLogo.setBounds(161, 53, 40, 15);
		contentPane.add(lblLogo);
	}

	private void entrar() throws CampoVazioException{
		//camposNulos = new CamposNulosCadastro();
		String senha = new String(senhaField.getPassword());
		String login = loginField.getText();
		Cadastro cadastro = fachada.retornaCadastro(login, senha);
		
		

		if (cadastro.getCliente().getCPF() != null) {
			Cliente cliente = fachada.procuraCliente(cadastro.getCliente()
					.getCPF());
			entrarCliente(cliente);
		}
	}

	public void entrarCliente(Cliente cliente) {
		telaCliente = new TelaCliente();
		telaCliente.setCliente(cliente);
		telaCliente.setVisible(true);
		telaCliente.setTitle("SteamFlix - Cliente");
	}

}
