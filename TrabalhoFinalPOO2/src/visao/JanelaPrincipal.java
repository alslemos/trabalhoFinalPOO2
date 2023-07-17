/*UFCSPA - Universidade Federal de Ciências da Saúde de Porto Alegre
 *Nomes: Alexandre Lemos Silva e Matheus Castilhos
 *Data: 06/07/2023
 *Disciplina: Programação Orientada a Objetos II
 *Curso: Informática Biomédica*/

package visao;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.CardLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;


//Classe da Janela Principal do programa, onde estão definidas todas as suas propriedades visuais.
public class JanelaPrincipal extends JFrame implements ActionListener{

	//Declaração das variáveis, atributos e containers.
	private JPanel contentPane, panelLiberacaoAcesso, panelManutencaoSistema, panelCriacaoUnidades, 
	panelAutenticar, panelEntrada, panelConsulta, panelRemocao;
	private JMenu menuEngenharia, menuASCOM, menuPROPLAN, menuSEI, menuPedidosInternos;
	private JTextField fieldUsuario, fieldNomeUnidadeLibAcesso, fieldInfoRamal, fieldNomeUnidadeCriacao, 
	fieldSiglaUnidadeCriacao, fieldEmailUnidadeCriacao, fieldDigPedidoRemove, fieldIDigitPedidoPesquisa;
	private JPasswordField fieldPassword;
	private JButton buttonAutenticar, buttonCancelar, buttonEnviarLibAcesso, buttonCancelarLibAcesso, 
	buttonEnviarManutSist, buttonCancelarManutSist, buttonEnviarCriacao, buttonCancelarCriacao, buttonListaTodos,
	buttonBuscaPesquisa, buttonConsultRemove, buttonApagar, buttonLimparPesquisa, buttonLimpaRemove;;
	private JRadioButton radiobuttonLiberacaoAcesso, radiobuttonExclusaoAcesso, radiobuttonTrocaUsuario, 
	radiobuttonImpossibilidadeAcesso, radiobuttonErro, radiobuttonOutro;
	private JTextArea textAreaMembroAcao, textAreaDescSolic, textAreaMembrosCriacao, textAreaPesquisa, textAreaRemove;
	private ButtonGroup bgLiber = new ButtonGroup(), bgManun = new ButtonGroup();
	private JScrollPane scrollPaneRemove;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JanelaPrincipal frame = new JanelaPrincipal();
					frame.setVisible(true);
					frame.setResizable(false);
					frame.setLocationRelativeTo(null);
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	

	/**
	 * Create the frame.
	 */
	public JanelaPrincipal() {
		setTitle("Sistema de Pedidos Internos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 665, 658);
		
		//Declaração da barra de menu
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		//Declaração do menu Principal, submenus Autenticar e Sair e suas respectivas ações.
		JMenu menuPrincipal = new JMenu("Principal");
		menuPrincipal.setIcon(new ImageIcon(JanelaPrincipal.class.getResource("/imagens/1_Principal.png")));
		menuBar.add(menuPrincipal);
		
		JMenuItem menuitemautenticar = new JMenuItem("Autenticar");
		menuitemautenticar.setIcon(new ImageIcon(JanelaPrincipal.class.getResource("/imagens/1_1_Autenticar.png")));
		menuitemautenticar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelAutenticar.setVisible(true);
				panelConsulta.setVisible(false);
				panelRemocao.setVisible(false);
				panelCriacaoUnidades.setVisible(false);
				panelLiberacaoAcesso.setVisible(false);
				panelManutencaoSistema.setVisible(false);
				panelEntrada.setVisible(false);
			}
		});
		menuPrincipal.add(menuitemautenticar);
		
		JMenuItem menuitemsair = new JMenuItem("Sair");
		menuitemsair.setIcon(new ImageIcon(JanelaPrincipal.class.getResource("/imagens/1_2_Sair.png")));
		menuitemsair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ImageIcon icon = new ImageIcon("src/imagens/confirmacao.png");
			    int button = JOptionPane.showConfirmDialog(null, "Tens certeza que desejas sair?", "Mensagem do sistema", JOptionPane.OK_CANCEL_OPTION, JOptionPane.NO_OPTION, icon);
				
			    if (button == JOptionPane.OK_OPTION) {
			       System.exit(0);
			    } 
			}
		});
		menuPrincipal.add(menuitemsair);
		
		//Declaração dos outros menus
		menuASCOM = new JMenu("ASCOM");
		menuASCOM.setIcon(new ImageIcon(JanelaPrincipal.class.getResource("/imagens/2_ASCOM.png")));
		menuBar.add(menuASCOM);
		menuASCOM.setEnabled(false);
		
		menuEngenharia = new JMenu("Engenharia");
		menuEngenharia.setIcon(new ImageIcon(JanelaPrincipal.class.getResource("/imagens/3_Engenharia.png")));
		menuBar.add(menuEngenharia);
		menuEngenharia.setEnabled(false);
		
		menuPROPLAN = new JMenu("PROPLAN");
		menuPROPLAN.setIcon(new ImageIcon(JanelaPrincipal.class.getResource("/imagens/4_PROPLAN.png")));
		menuBar.add(menuPROPLAN);
		menuPROPLAN.setEnabled(false);
		
		menuSEI = new JMenu("SEI");
		menuSEI.setIcon(new ImageIcon(JanelaPrincipal.class.getResource("/imagens/5_SEI.png")));
		menuBar.add(menuSEI);
		menuSEI.setEnabled(false);
		
		//Declaração dos submenus do menu SEI e suas ações.
		JMenuItem menuitemacessounidade = new JMenuItem("Liberação de acesso em unidades");
		menuitemacessounidade.setIcon(new ImageIcon(JanelaPrincipal.class.getResource("/imagens/5_3_Liberacao.png")));
		menuitemacessounidade.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelAutenticar.setVisible(false);
				panelConsulta.setVisible(false);
				panelRemocao.setVisible(false);
				panelCriacaoUnidades.setVisible(false);
				panelLiberacaoAcesso.setVisible(true);
				panelManutencaoSistema.setVisible(false);
				panelEntrada.setVisible(false);
			}
		});
		
		JMenuItem menuitemcriacaounidade = new JMenuItem("Criação de Unidades no SEI");
		menuitemcriacaounidade.setIcon(new ImageIcon(JanelaPrincipal.class.getResource("/imagens/5_2_Criacao.png")));
		menuitemcriacaounidade.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelAutenticar.setVisible(false);
				panelConsulta.setVisible(false);
				panelRemocao.setVisible(false);
				panelCriacaoUnidades.setVisible(true);
				panelLiberacaoAcesso.setVisible(false);
				panelManutencaoSistema.setVisible(false);
				panelEntrada.setVisible(false);
			}
		});
		menuSEI.add(menuitemcriacaounidade);
		menuSEI.add(menuitemacessounidade);

		JMenuItem menuitemmanutencaosistema = new JMenuItem("Manutenção do Sistema");
		menuitemmanutencaosistema.setIcon(new ImageIcon(JanelaPrincipal.class.getResource("/imagens/5_1_Manutencao.png")));
		menuitemmanutencaosistema.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelAutenticar.setVisible(false);
				panelConsulta.setVisible(false);
				panelRemocao.setVisible(false);
				panelCriacaoUnidades.setVisible(false);
				panelLiberacaoAcesso.setVisible(false);
				panelManutencaoSistema.setVisible(true);
				panelEntrada.setVisible(false);
			}
		});
		menuSEI.add(menuitemmanutencaosistema);
		
		//Criação do menu Pedidos Internos, seus submenus e respectivas ações.
		menuPedidosInternos = new JMenu("Pedidos Internos");
		menuPedidosInternos.setIcon(new ImageIcon(JanelaPrincipal.class.getResource("/imagens/6_Sistema.png")));
		menuBar.add(menuPedidosInternos);
		menuPedidosInternos.setEnabled(false);
		
		
		JMenuItem menuitemconsulta = new JMenuItem("Consultar");
		menuitemconsulta.setIcon(new ImageIcon(JanelaPrincipal.class.getResource("/imagens/6_1_Consultar.png")));
		menuPedidosInternos.add(menuitemconsulta);
		menuitemconsulta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelAutenticar.setVisible(false);
				panelConsulta.setVisible(true);
				panelRemocao.setVisible(false);
				panelCriacaoUnidades.setVisible(false);
				panelLiberacaoAcesso.setVisible(false);
				panelManutencaoSistema.setVisible(false);
				panelEntrada.setVisible(false);
			}
		});
		
		JMenuItem menuitemremover = new JMenuItem("Remover");
		menuitemremover.setIcon(new ImageIcon(JanelaPrincipal.class.getResource("/imagens/6_2_Remover.png")));
		menuPedidosInternos.add(menuitemremover);
		menuitemremover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelAutenticar.setVisible(false);
				panelConsulta.setVisible(false);
				panelRemocao.setVisible(true);
				panelCriacaoUnidades.setVisible(false);
				panelLiberacaoAcesso.setVisible(false);
				panelManutencaoSistema.setVisible(false);
				panelEntrada.setVisible(false);
			}
		});
		
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		
		
		//Declaração do panel inicial
		panelEntrada = new JPanel();
		contentPane.add(panelEntrada, "name_5713235248648");
		
		
		//Declaração do panel Autenticar e seus respectivos containers 
		panelAutenticar = new JPanel();
		contentPane.add(panelAutenticar, "name_23786049927572");
		panelAutenticar.setLayout(new MigLayout("", "[grow]", "[130px][40px][20px][][40px][20px][][40px]"));
		
		
		JLabel labelUsuario = new JLabel("Usuário");
		labelUsuario.setFont(new Font("Dialog", Font.BOLD, 16));
		panelAutenticar.add(labelUsuario, "cell 0 1,alignx center,aligny center");
		
		fieldUsuario = new JTextField();
		fieldUsuario.setFont(new Font("Dialog", Font.PLAIN, 16));
		panelAutenticar.add(fieldUsuario, "cell 0 2,growx");
		fieldUsuario.setColumns(10);
		
		JLabel labelSenha = new JLabel("Senha");
		labelSenha.setFont(new Font("Dialog", Font.BOLD, 16));
		panelAutenticar.add(labelSenha, "cell 0 4,alignx center,aligny center");
		
		fieldPassword = new JPasswordField();
		fieldPassword.setFont(new Font("Dialog", Font.PLAIN, 16));
		panelAutenticar.add(fieldPassword, "cell 0 5,growx");
		
		buttonAutenticar = new JButton("Autenticar");
		buttonAutenticar.setIcon(new ImageIcon(JanelaPrincipal.class.getResource("/imagens/positivo.png")));
		buttonAutenticar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		buttonAutenticar.setFont(new Font("Dialog", Font.BOLD, 14));
		panelAutenticar.add(buttonAutenticar, "flowx,cell 0 9,alignx center");
		
		buttonCancelar = new JButton("Cancelar");
		buttonCancelar.setIcon(new ImageIcon(JanelaPrincipal.class.getResource("/imagens/negativo.png")));
		buttonCancelar.setFont(new Font("Dialog", Font.BOLD, 14));
		panelAutenticar.add(buttonCancelar, "cell 0 9,alignx center");
		
		//Declaração do panel Liberação de Acesso e seus respectivos containers 
		panelLiberacaoAcesso = new JPanel();
		contentPane.add(panelLiberacaoAcesso, "name_18998090489115");
		panelLiberacaoAcesso.setLayout(new MigLayout("", "[::630]", "[][40px][][10px:n][][][][][][10px:n][][][10px:n][][][100px][40px]"));
				
		JLabel labelLiberacaoAcesso = new JLabel("Liberação de acesso em unidades");
		labelLiberacaoAcesso.setHorizontalAlignment(SwingConstants.CENTER);
		labelLiberacaoAcesso.setFont(new Font("Dialog", Font.BOLD, 20));
		panelLiberacaoAcesso.add(labelLiberacaoAcesso, "cell 0 0,growx");
		
		JLabel labelPedidosInternos = new JLabel("");
		labelPedidosInternos.setHorizontalAlignment(SwingConstants.CENTER);
		labelPedidosInternos.setOpaque(true);
		labelPedidosInternos.setBackground(new Color(17, 63, 89));
		labelPedidosInternos.setIcon(new ImageIcon(JanelaPrincipal.class.getResource("/imagens/pedidosinternos.png")));
		labelPedidosInternos.setFont(new Font("Dialog", Font.BOLD, 20));
		panelLiberacaoAcesso.add(labelPedidosInternos, "cell 0 1,growx");
		
		JLabel labelTexto1 = new JLabel("<html><b>Atenção:</b> Somente deverá ser encaminhado PI para criação de unidades se a criação desta não for oriunda de fluxo de algum processo administrativo. Caso seja, encaminhar o pedido para o Comitê Gestor do SEI conforme o fluxo do processo que ensejou a criação desta (por e-mail ou por envio do processo para a unidade CGSEI).</html>");
		labelTexto1.setFont(new Font("Dialog", Font.PLAIN, 12));
		panelLiberacaoAcesso.add(labelTexto1, "cell 0 2");
		
		JLabel labelSolicitacao = new JLabel("Solicitação");
		labelSolicitacao.setForeground(new Color(255, 255, 255));
		labelSolicitacao.setBackground(new Color(27, 47, 98));
		labelSolicitacao.setOpaque(true);
		labelSolicitacao.setHorizontalAlignment(SwingConstants.CENTER);
		labelSolicitacao.setFont(new Font("Dialog", Font.BOLD, 18));
		panelLiberacaoAcesso.add(labelSolicitacao, "cell 0 4,growx");
		
		JLabel labelTipoSolicitacao = new JLabel("<html>Tipo de solicitação <FONT COLOR = red> *</html>");
		labelTipoSolicitacao.setFont(new Font("Dialog", Font.BOLD, 14));
		panelLiberacaoAcesso.add(labelTipoSolicitacao, "cell 0 5");
		
		radiobuttonLiberacaoAcesso = new JRadioButton("Liberação de acesso em unidades");
		radiobuttonLiberacaoAcesso.setFont(new Font("Dialog", Font.PLAIN, 12));
		panelLiberacaoAcesso.add(radiobuttonLiberacaoAcesso, "cell 0 6");
		radiobuttonLiberacaoAcesso.setActionCommand("Liberação de acesso em unidades");
		
		radiobuttonExclusaoAcesso = new JRadioButton("Exclusão de acesso em unidades");
		radiobuttonExclusaoAcesso.setFont(new Font("Dialog", Font.PLAIN, 12));
		panelLiberacaoAcesso.add(radiobuttonExclusaoAcesso, "cell 0 7");
		radiobuttonExclusaoAcesso.setActionCommand("Exclusão de acesso em unidades");
		
		radiobuttonTrocaUsuario = new JRadioButton("Troca de usuário em unidade");
		radiobuttonTrocaUsuario.setFont(new Font("Dialog", Font.PLAIN, 12));
		panelLiberacaoAcesso.add(radiobuttonTrocaUsuario, "cell 0 8");
		radiobuttonTrocaUsuario.setActionCommand("Troca de usuário em unidade");
		
		bgLiber.add(radiobuttonLiberacaoAcesso);
		bgLiber.add(radiobuttonExclusaoAcesso);
		bgLiber.add(radiobuttonTrocaUsuario);
		
		JLabel labelNomeUnidadeLibAcesso = new JLabel("<html>Nome da unidade <FONT COLOR = red> *</html>");
		labelNomeUnidadeLibAcesso.setFont(new Font("Dialog", Font.BOLD, 14));
		panelLiberacaoAcesso.add(labelNomeUnidadeLibAcesso, "cell 0 10");
		
		fieldNomeUnidadeLibAcesso = new JTextField();
		fieldNomeUnidadeLibAcesso.setFont(new Font("Dialog", Font.PLAIN, 14));
		panelLiberacaoAcesso.add(fieldNomeUnidadeLibAcesso, "cell 0 11,growx");
		fieldNomeUnidadeLibAcesso.setColumns(10);
		
		JLabel labelMembroAcao = new JLabel("<html>Membro a ser efetuada a ação <FONT COLOR = red> *</html>");
		labelMembroAcao.setFont(new Font("Dialog", Font.BOLD, 14));
		panelLiberacaoAcesso.add(labelMembroAcao, "cell 0 13");
		
		JLabel labelTextoMembroAcao = new JLabel("Incluir os nomes dos componentes separados por ; ou -. Em caso de troca preencher com: usuário X por usuário Y.");
		labelTextoMembroAcao.setHorizontalAlignment(SwingConstants.CENTER);
		labelTextoMembroAcao.setFont(new Font("Dialog", Font.BOLD, 10));
		panelLiberacaoAcesso.add(labelTextoMembroAcao, "cell 0 14");
		
		textAreaMembroAcao = new JTextArea();
		textAreaMembroAcao.setFont(new Font("Dialog", Font.PLAIN, 12));
		panelLiberacaoAcesso.add(textAreaMembroAcao, "cell 0 15,grow");
		
		buttonEnviarLibAcesso = new JButton("Enviar");
		buttonEnviarLibAcesso.setIcon(new ImageIcon(JanelaPrincipal.class.getResource("/imagens/positivo.png")));
		buttonEnviarLibAcesso.setFont(new Font("Dialog", Font.BOLD, 12));
		panelLiberacaoAcesso.add(buttonEnviarLibAcesso, "flowx,cell 0 16,alignx center");
		buttonEnviarLibAcesso.setActionCommand("Enviar Liberacao");
		
		buttonCancelarLibAcesso = new JButton("Cancelar");
		buttonCancelarLibAcesso.setIcon(new ImageIcon(JanelaPrincipal.class.getResource("/imagens/negativo.png")));
		buttonCancelarLibAcesso.setFont(new Font("Dialog", Font.BOLD, 12));
		panelLiberacaoAcesso.add(buttonCancelarLibAcesso, "cell 0 16");
		
		
		//Declaração do panel Criação de Unidades e seus respectivos containers 
		panelCriacaoUnidades = new JPanel();
		contentPane.add(panelCriacaoUnidades, "name_11263636154700");
		panelCriacaoUnidades.setLayout(new MigLayout("", "[::630px,grow]", "[][40px][][10px:n][][][][][][][][][][100px,grow][]"));
		
		JLabel labelCriacaoUnidadesSEI = new JLabel("Criação de Unidades no SEI");
		labelCriacaoUnidadesSEI.setFont(new Font("Dialog", Font.BOLD, 20));
		panelCriacaoUnidades.add(labelCriacaoUnidadesSEI, "cell 0 0,alignx center");
		
		JLabel labelPedidosInternos2 = new JLabel("");
		labelPedidosInternos2.setHorizontalAlignment(SwingConstants.CENTER);
		labelPedidosInternos2.setOpaque(true);
		labelPedidosInternos2.setBackground(new Color(17, 63, 89));
		labelPedidosInternos2.setIcon(new ImageIcon(JanelaPrincipal.class.getResource("/imagens/pedidosinternos.png")));
		panelCriacaoUnidades.add(labelPedidosInternos2, "cell 0 1,growx");
		
		JLabel labelTexto2 = new JLabel("<html><b>Atenção:</b> Somente deverá ser encaminhado PI para criação de unidades se a criação desta não for oriunda de fluxo de algum processo administrativo. Caso seja, encaminhar o pedido para o Comitê Gestor do SEI conforme o fluxo do processo que ensejou a criação desta (por e-mail ou por envio do processo para a unidade CGSEI).</html>");
		labelTexto2.setFont(new Font("Dialog", Font.PLAIN, 12));
		panelCriacaoUnidades.add(labelTexto2, "cell 0 2");
		
		JLabel labelSolicitacao2 = new JLabel("Solicitação");
		labelSolicitacao2.setOpaque(true);
		labelSolicitacao2.setForeground(new Color(255, 255, 255));
		labelSolicitacao2.setBackground(new Color(27, 47, 98));
		labelSolicitacao2.setHorizontalAlignment(SwingConstants.CENTER);
		labelSolicitacao2.setFont(new Font("Dialog", Font.BOLD, 18));
		panelCriacaoUnidades.add(labelSolicitacao2, "cell 0 4,growx");
		
		JLabel labelNomeUnidadeCriacao = new JLabel("<html>Nome da unidade <FONT COLOR = red> *</html>");
		labelNomeUnidadeCriacao.setFont(new Font("Dialog", Font.BOLD, 14));
		panelCriacaoUnidades.add(labelNomeUnidadeCriacao, "cell 0 5");
		
		fieldNomeUnidadeCriacao = new JTextField();
		panelCriacaoUnidades.add(fieldNomeUnidadeCriacao, "cell 0 6,growx");
		fieldNomeUnidadeCriacao.setColumns(10);
		
		JLabel labelSiglaUnidadeCriacao = new JLabel("<html>Sigla da unidade <FONT COLOR = red> *</html>");
		labelSiglaUnidadeCriacao.setFont(new Font("Dialog", Font.BOLD, 14));
		panelCriacaoUnidades.add(labelSiglaUnidadeCriacao, "cell 0 7");
		
		fieldSiglaUnidadeCriacao = new JTextField();
		panelCriacaoUnidades.add(fieldSiglaUnidadeCriacao, "cell 0 8,growx");
		fieldSiglaUnidadeCriacao.setColumns(10);
		
		JLabel labelEmailUnidadeCriacao = new JLabel("<html>E-mail vinculado a unidade <FONT COLOR = red> *</html>");
		labelEmailUnidadeCriacao.setFont(new Font("Dialog", Font.BOLD, 14));
		panelCriacaoUnidades.add(labelEmailUnidadeCriacao, "cell 0 9");
		
		fieldEmailUnidadeCriacao = new JTextField();
		panelCriacaoUnidades.add(fieldEmailUnidadeCriacao, "cell 0 10,growx");
		fieldEmailUnidadeCriacao.setColumns(10);
		
		JLabel labelMembrosUnidadeCriacao = new JLabel("<html>Membros da unidade <FONT COLOR = red> *</html>");
		labelMembrosUnidadeCriacao.setFont(new Font("Dialog", Font.BOLD, 14));
		panelCriacaoUnidades.add(labelMembrosUnidadeCriacao, "cell 0 11");
		
		JLabel labelMembrosSubTextCriacao = new JLabel("Incluir os nomes dos componentes separados por ; ou -");
		labelMembrosSubTextCriacao.setFont(new Font("Dialog", Font.BOLD, 10));
		panelCriacaoUnidades.add(labelMembrosSubTextCriacao, "cell 0 12");
		
		textAreaMembrosCriacao = new JTextArea();
		panelCriacaoUnidades.add(textAreaMembrosCriacao, "cell 0 13,grow");
		
		buttonEnviarCriacao = new JButton("Enviar");
		buttonEnviarCriacao.setIcon(new ImageIcon(JanelaPrincipal.class.getResource("/imagens/positivo.png")));
		buttonEnviarCriacao.setFont(new Font("Dialog", Font.BOLD, 12));
		panelCriacaoUnidades.add(buttonEnviarCriacao, "flowx,cell 0 14,alignx center");
		buttonEnviarCriacao.setActionCommand("Enviar Criacao");
		
		buttonCancelarCriacao = new JButton("Cancelar");
		buttonCancelarCriacao.setIcon(new ImageIcon(JanelaPrincipal.class.getResource("/imagens/negativo.png")));
		buttonCancelarCriacao.setFont(new Font("Dialog", Font.BOLD, 12));
		panelCriacaoUnidades.add(buttonCancelarCriacao, "cell 0 14,alignx center");
		
		//Declaração do panel Manutenção do Sistema e seus respectivos containers
		panelManutencaoSistema = new JPanel();
		contentPane.add(panelManutencaoSistema, "name_23021440760758");
		panelManutencaoSistema.setLayout(new MigLayout("", "[::630,grow]", "[][40px][][][::10px][][][][][][][:100.00:100px,grow][][][][40px]"));
		
		
		
		JLabel labelManutencaoSistemaSEI = new JLabel("Manutenção do Sistema SEI");
		labelManutencaoSistemaSEI.setFont(new Font("Dialog", Font.BOLD, 20));
		panelManutencaoSistema.add(labelManutencaoSistemaSEI, "cell 0 0,alignx center");
		
		JLabel labelPedidosInternos3 = new JLabel("");
		labelPedidosInternos3.setHorizontalAlignment(SwingConstants.CENTER);
		labelPedidosInternos3.setOpaque(true);
		labelPedidosInternos3.setBackground(new Color(17, 63, 89));
		labelPedidosInternos3.setIcon(new ImageIcon(JanelaPrincipal.class.getResource("/imagens/pedidosinternos.png")));
		panelManutencaoSistema.add(labelPedidosInternos3, "cell 0 1,growx");
		
		JLabel labelSetorEncarregado = new JLabel("<html><b>Setor encarregado:</b> NTI - Desenvolvimento</html>");
		labelSetorEncarregado.setFont(new Font("Dialog", Font.PLAIN, 12));
		panelManutencaoSistema.add(labelSetorEncarregado, "cell 0 2");
		
		JLabel labelQuemPodeSolicitar = new JLabel("<html><b>Quem pode solicitar:</b> Professores/Técnicos</html>");
		labelQuemPodeSolicitar.setFont(new Font("Dialog", Font.PLAIN, 12));
		panelManutencaoSistema.add(labelQuemPodeSolicitar, "cell 0 3");
		
		JLabel labelSolicitacao3 = new JLabel("Solicitação");
		labelSolicitacao3.setOpaque(true);
		labelSolicitacao3.setForeground(new Color(255, 255, 255));
		labelSolicitacao3.setBackground(new Color(27, 47, 98));
		labelSolicitacao3.setHorizontalAlignment(SwingConstants.CENTER);
		labelSolicitacao3.setFont(new Font("Dialog", Font.BOLD, 18));
		panelManutencaoSistema.add(labelSolicitacao3, "cell 0 5,growx");
		
		JLabel labelTipoSolicitacao2 = new JLabel("<html>Tipo de soliticação <FONT COLOR = red> *</html>");
		labelTipoSolicitacao2.setFont(new Font("Dialog", Font.BOLD, 14));
		panelManutencaoSistema.add(labelTipoSolicitacao2, "cell 0 6");
		
		radiobuttonImpossibilidadeAcesso = new JRadioButton("Impossibilidade de acesso no sistema");
		radiobuttonImpossibilidadeAcesso.setFont(new Font("Dialog", Font.PLAIN, 12));
		panelManutencaoSistema.add(radiobuttonImpossibilidadeAcesso, "cell 0 7");
		radiobuttonImpossibilidadeAcesso.setActionCommand("Impossibilidade de acesso no sistema");
		
		
		radiobuttonErro = new JRadioButton("Erro");
		radiobuttonErro.setFont(new Font("Dialog", Font.PLAIN, 12));
		panelManutencaoSistema.add(radiobuttonErro, "cell 0 8");
		radiobuttonErro.setActionCommand("Erro");
		
		radiobuttonOutro = new JRadioButton("Outro");
		radiobuttonOutro.setFont(new Font("Dialog", Font.PLAIN, 12));
		panelManutencaoSistema.add(radiobuttonOutro, "cell 0 9");
		radiobuttonOutro.setActionCommand("Outro");
		
		bgManun.add(radiobuttonImpossibilidadeAcesso);
		bgManun.add(radiobuttonErro);
		bgManun.add(radiobuttonOutro);
		
		JLabel labelDescricaoSolicitacao = new JLabel("<html>Descrição da solicitação <FONT COLOR = red> *</html>");
		labelDescricaoSolicitacao.setFont(new Font("Dialog", Font.BOLD, 14));
		panelManutencaoSistema.add(labelDescricaoSolicitacao, "cell 0 10");
		
		textAreaDescSolic = new JTextArea();
		panelManutencaoSistema.add(textAreaDescSolic, "cell 0 11,grow");
		
		JLabel labelInforComp = new JLabel("Informações complementares");
		labelInforComp.setHorizontalAlignment(SwingConstants.CENTER);
		labelInforComp.setOpaque(true);
		labelInforComp.setForeground(new Color(255, 255, 255));
		labelInforComp.setBackground(new Color(27, 47, 98));
		labelInforComp.setFont(new Font("Dialog", Font.BOLD, 18));
		panelManutencaoSistema.add(labelInforComp, "cell 0 12,growx");
		
		JLabel labelInforRamal = new JLabel("Informe seu ramal UFCSPA ou telefone para facilitar o atendimento!");
		labelInforRamal.setFont(new Font("Dialog", Font.BOLD, 12));
		panelManutencaoSistema.add(labelInforRamal, "cell 0 13");
		
		fieldInfoRamal = new JTextField();
		fieldInfoRamal.setFont(new Font("Dialog", Font.PLAIN, 12));
		panelManutencaoSistema.add(fieldInfoRamal, "cell 0 14,growx");
		fieldInfoRamal.setColumns(10);
		
		buttonEnviarManutSist = new JButton("Enviar");
		buttonEnviarManutSist.setIcon(new ImageIcon(JanelaPrincipal.class.getResource("/imagens/positivo.png")));
		buttonEnviarManutSist.setFont(new Font("Dialog", Font.BOLD, 12));
		panelManutencaoSistema.add(buttonEnviarManutSist, "flowx,cell 0 15,alignx center");
		buttonEnviarManutSist.setActionCommand("Enviar Manutencao");
		
		buttonCancelarManutSist = new JButton("Cancelar");
		buttonCancelarManutSist.setIcon(new ImageIcon(JanelaPrincipal.class.getResource("/imagens/negativo.png")));
		buttonCancelarManutSist.setFont(new Font("Dialog", Font.BOLD, 12));
		panelManutencaoSistema.add(buttonCancelarManutSist, "cell 0 15");
		
		panelConsulta = new JPanel();
		contentPane.add(panelConsulta, "name_142121229791500");
		panelConsulta.setLayout(new MigLayout("", "[::630,grow]", "[][][15px:n][][20px:n][320px:n:320px,grow][20px:n][]"));
		
		JLabel labelConsulta = new JLabel("Consulta");
		labelConsulta.setHorizontalAlignment(SwingConstants.CENTER);
		labelConsulta.setFont(new Font("Dialog", Font.BOLD, 20));
		panelConsulta.add(labelConsulta, "cell 0 0,growx");
		
		JLabel labelPedidosInternos4 = new JLabel("");
		labelPedidosInternos4.setHorizontalAlignment(SwingConstants.CENTER);
		labelPedidosInternos4.setOpaque(true);
		labelPedidosInternos4.setBackground(new Color(17, 63, 89));
		labelPedidosInternos4.setIcon(new ImageIcon(JanelaPrincipal.class.getResource("/imagens/pedidosinternos.png")));
		panelConsulta.add(labelPedidosInternos4, "cell 0 1,growx");
		
		JLabel labelDigConsult = new JLabel("Digite o número de identificação do Pedido Interno:");
		labelDigConsult.setFont(new Font("Dialog", Font.PLAIN, 12));
		panelConsulta.add(labelDigConsult, "flowx,cell 0 3");
		
		fieldIDigitPedidoPesquisa = new JTextField();
		panelConsulta.add(fieldIDigitPedidoPesquisa, "flowx,cell 0 4,growx");
		fieldIDigitPedidoPesquisa.setColumns(10);
		
		buttonBuscaPesquisa = new JButton("Pesquisar");
		buttonBuscaPesquisa.setIcon(new ImageIcon(JanelaPrincipal.class.getResource("/imagens/pesquisaicon.png")));
		buttonBuscaPesquisa.setFont(new Font("Dialog", Font.BOLD, 12));
		panelConsulta.add(buttonBuscaPesquisa, "cell 0 4,alignx center");
		buttonBuscaPesquisa.setActionCommand("Busca Pesquisa");
		
		JScrollPane scrollPanePesquisa = new JScrollPane();
		panelConsulta.add(scrollPanePesquisa, "cell 0 5,grow");
		
		textAreaPesquisa = new JTextArea();
		textAreaPesquisa.setEditable(false);
		scrollPanePesquisa.setViewportView(textAreaPesquisa);
		
		
		buttonListaTodos = new JButton("Listar Todos os Meus Processos");
		buttonListaTodos.setIcon(new ImageIcon(JanelaPrincipal.class.getResource("/imagens/listaricon.png")));
		buttonListaTodos.setFont(new Font("Dialog", Font.BOLD, 12));
		panelConsulta.add(buttonListaTodos, "flowx,cell 0 7");
		buttonListaTodos.setActionCommand("Listar Todos");
		
		buttonLimparPesquisa = new JButton("Limpar");
		buttonLimparPesquisa.setFont(new Font("Dialog", Font.BOLD, 12));
		buttonLimparPesquisa.setIcon(new ImageIcon(JanelaPrincipal.class.getResource("/imagens/limparicon.png")));
		panelConsulta.add(buttonLimparPesquisa, "cell 0 7");
		buttonLimparPesquisa.setActionCommand("Limpar Pesquisa");
		
		panelRemocao = new JPanel();
		contentPane.add(panelRemocao, "name_142143149259500");
		panelRemocao.setLayout(new MigLayout("", "[::630,grow]", "[][][15px:n][][][320px:n:320px,grow][20px:n][]"));
		
		JLabel lblNewLabel = new JLabel("Remoção");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panelRemocao.add(lblNewLabel, "cell 0 0,growx");
		
		JLabel labelPedidosInternos5 = new JLabel("");
		labelPedidosInternos5.setHorizontalAlignment(SwingConstants.CENTER);
		labelPedidosInternos5.setOpaque(true);
		labelPedidosInternos5.setBackground(new Color(17, 63, 89));
		labelPedidosInternos5.setIcon(new ImageIcon(JanelaPrincipal.class.getResource("/imagens/pedidosinternos.png")));
		panelRemocao.add(labelPedidosInternos5, "cell 0 1,growx");
		
		JLabel labelDigRemove = new JLabel("Consultar o número de identificação do processo que deseja excluir");
		labelDigRemove.setFont(new Font("Dialog", Font.BOLD, 12));
		panelRemocao.add(labelDigRemove, "cell 0 3");
		
		fieldDigPedidoRemove = new JTextField();
		panelRemocao.add(fieldDigPedidoRemove, "flowx,cell 0 4,growx");
		fieldDigPedidoRemove.setColumns(10);
		
		buttonConsultRemove = new JButton("Consultar");
		buttonConsultRemove.setIcon(new ImageIcon(JanelaPrincipal.class.getResource("/imagens/pesquisaicon.png")));
		buttonConsultRemove.setFont(new Font("Dialog", Font.BOLD, 12));
		panelRemocao.add(buttonConsultRemove, "cell 0 4");
		
			
		scrollPaneRemove = new JScrollPane();
		panelRemocao.add(scrollPaneRemove, "cell 0 5,grow");
		
		textAreaRemove = new JTextArea();
		textAreaRemove.setEditable(false);
		scrollPaneRemove.setViewportView(textAreaRemove);
		
		buttonApagar = new JButton("Apagar");
		buttonApagar.setIcon(new ImageIcon(JanelaPrincipal.class.getResource("/imagens/deletaricon.png")));
		buttonApagar.setFont(new Font("Dialog", Font.BOLD, 12));
		buttonApagar.setEnabled(false);
		panelRemocao.add(buttonApagar, "flowx,cell 0 7");
		
		buttonLimpaRemove = new JButton("Limpar");
		buttonLimpaRemove.setIcon(new ImageIcon(JanelaPrincipal.class.getResource("/imagens/limparicon.png")));
		buttonLimpaRemove.setFont(new Font("Dialog", Font.BOLD, 12));
		buttonLimpaRemove.setActionCommand("Limpa Remove");
		panelRemocao.add(buttonLimpaRemove, "cell 0 7");
	}
	
	
	
	//Métodos Especiais (getters e setters)
	public JMenu getMenuSEI() {
		return menuSEI;
	}



	public JTextField getFieldNomeUnidadeCriacao() {
		return fieldNomeUnidadeCriacao;
	}



	public void setFieldNomeUnidadeCriacao(JTextField fieldNomeUnidadeCriacao) {
		this.fieldNomeUnidadeCriacao = fieldNomeUnidadeCriacao;
	}



	public JTextField getFieldSiglaUnidadeCriacao() {
		return fieldSiglaUnidadeCriacao;
	}



	public void setFieldSiglaUnidadeCriacao(JTextField fieldSiglaUnidadeCriacao) {
		this.fieldSiglaUnidadeCriacao = fieldSiglaUnidadeCriacao;
	}



	public JTextField getFieldEmailUnidadeCriacao() {
		return fieldEmailUnidadeCriacao;
	}



	public void setFieldEmailUnidadeCriacao(JTextField fieldEmailUnidadeCriacao) {
		this.fieldEmailUnidadeCriacao = fieldEmailUnidadeCriacao;
	}



	public JTextArea getAreaMembrosCriacao() {
		return textAreaMembrosCriacao;
	}



	public void setAreaMembrosCriacao(JTextArea textAreaMembrosCriacao) {
		this.textAreaMembrosCriacao = textAreaMembrosCriacao;
	}



	public JMenu getMenuASCOM() {
		return menuASCOM;
	}



	public void setMenuASCOM(JMenu menuASCOM) {
		this.menuASCOM = menuASCOM;
	}



	public JMenu getMenuPROPLAN() {
		return menuPROPLAN;
	}



	public void setMenuPROPLAN(JMenu menuPROPLAN) {
		this.menuPROPLAN = menuPROPLAN;
	}



	public ButtonGroup getBgLiber() {
		return bgLiber;
	}



	public void setBgLiber(ButtonGroup bgLiber) {
		this.bgLiber = bgLiber;
	}



	public ButtonGroup getBgManun() {
		return bgManun;
	}



	public void setBgManun(ButtonGroup bgManun) {
		this.bgManun = bgManun;
	}



	public void setMenuSEI(JMenu menuSEI) {
		this.menuSEI = menuSEI;
	}



	public JPanel getPanelLiberacaoAcesso() {
		return panelLiberacaoAcesso;
	}

	public void setPanelLiberacaoAcesso(JPanel panelLiberacaoAcesso) {
		this.panelLiberacaoAcesso = panelLiberacaoAcesso;
	}



	public JPanel getPanelCriacaoUnidades() {
		return panelCriacaoUnidades;
	}



	public void setPanelCriacaoUnidades(JPanel panelCriacaoUnidades) {
		this.panelCriacaoUnidades = panelCriacaoUnidades;
	}



	public JPanel getPanelManutencaoSistema() {
		return panelManutencaoSistema;
	}

	public void setPanelManutencaoSistema(JPanel panelManutencaoSistema) {
		this.panelManutencaoSistema = panelManutencaoSistema;
	}

	public JPanel getPanelAutenticar() {
		return panelAutenticar;
	}

	public void setPanelAutenticar(JPanel panelAutenticar) {
		this.panelAutenticar = panelAutenticar;
	}

	public JPanel getPanelEntrada() {
		return panelEntrada;
	}

	public void setPanelEntrada(JPanel panelEntrada) {
		this.panelEntrada = panelEntrada;
	}

	public JMenu getMenuEngenharia() {
		return menuEngenharia;
	}

	public void setMenuEngenharia(JMenu menuEngenharia) {
		this.menuEngenharia = menuEngenharia;
	}

	public JTextField getFieldUsuario() {
		return fieldUsuario;
	}

	public void setFieldUsuario(JTextField fieldUsuario) {
		this.fieldUsuario = fieldUsuario;
	}

	public JPasswordField getFieldPassword() {
		return fieldPassword;
	}

	public void setFieldPassword(JPasswordField fieldPassword) {
		this.fieldPassword = fieldPassword;
	}

	public JTextField getFieldNomeUnidadeLibAcesso() {
		return fieldNomeUnidadeLibAcesso;
	}

	public void setFieldNomeUnidadeLibAcesso(JTextField fieldNomeUnidadeLibAcesso) {
		this.fieldNomeUnidadeLibAcesso = fieldNomeUnidadeLibAcesso;
	}

	

	public JButton getButtonAutenticar() {
		return buttonAutenticar;
	}

	public void setButtonAutenticar(JButton buttonAutenticar) {
		this.buttonAutenticar = buttonAutenticar;
	}

	public JButton getButtonCancelar() {
		return buttonCancelar;
	}

	public void setButtonCancelar(JButton buttonCancelar) {
		this.buttonCancelar = buttonCancelar;
	}

	public JButton getButtonEnviarLibAcesso() {
		return buttonEnviarLibAcesso;
	}

	public void setButtonEnviarLibAcesso(JButton buttonEnviarLibAcesso) {
		this.buttonEnviarLibAcesso = buttonEnviarLibAcesso;
	}

	public JButton getButtonCancelarLibAcesso() {
		return buttonCancelarLibAcesso;
	}

	public void setButtonCancelarLibAcesso(JButton buttonCancelarLibAcesso) {
		this.buttonCancelarLibAcesso = buttonCancelarLibAcesso;
	}


	public JButton getButtonEnviarManutSist() {
		return buttonEnviarManutSist;
	}

	public void setButtonEnviarManutSist(JButton buttonEnviarManutSist) {
		this.buttonEnviarManutSist = buttonEnviarManutSist;
	}

	public JButton getButtonCancelarManutSist() {
		return buttonCancelarManutSist;
	}

	public void setButtonCancelarManutSist(JButton buttonCancelarManutSist) {
		this.buttonCancelarManutSist = buttonCancelarManutSist;
	}


	public JRadioButton getRadiobuttonLiberacaoAcesso() {
		return radiobuttonLiberacaoAcesso;
	}

	public void setRadiobuttonLiberacaoAcesso(JRadioButton radiobuttonLiberacaoAcesso) {
		this.radiobuttonLiberacaoAcesso = radiobuttonLiberacaoAcesso;
	}

	public JRadioButton getRadiobuttonExclusaoAcesso() {
		return radiobuttonExclusaoAcesso;
	}

	public void setRadiobuttonExclusaoAcesso(JRadioButton radiobuttonExclusaoAcesso) {
		this.radiobuttonExclusaoAcesso = radiobuttonExclusaoAcesso;
	}

	public JRadioButton getRadiobuttonTrocaUsuario() {
		return radiobuttonTrocaUsuario;
	}

	public void setRadiobuttonTrocaUsuario(JRadioButton radiobuttonTrocaUsuario) {
		this.radiobuttonTrocaUsuario = radiobuttonTrocaUsuario;
	}

	public JRadioButton getRadiobuttonImpossibilidadeAcesso() {
		return radiobuttonImpossibilidadeAcesso;
	}

	public void setRadiobuttonImpossibilidadeAcesso(JRadioButton radiobuttonImpossibilidadeAcesso) {
		this.radiobuttonImpossibilidadeAcesso = radiobuttonImpossibilidadeAcesso;
	}

	public JRadioButton getRadiobuttonErro() {
		return radiobuttonErro;
	}

	public void setRadiobuttonErro(JRadioButton radiobuttonErro) {
		this.radiobuttonErro = radiobuttonErro;
	}

	public JRadioButton getRadiobuttonOutro() {
		return radiobuttonOutro;
	}

	public void setRadiobuttonOutro(JRadioButton radiobuttonOutro) {
		this.radiobuttonOutro = radiobuttonOutro;
	}

	public JTextField getFieldInfoRamal() {
		return fieldInfoRamal;
	}

	public void setFieldInfoRamal(JTextField fieldInfoRamal) {
		this.fieldInfoRamal = fieldInfoRamal;
	}

	public JTextArea getTextAreaMembroAcao() {
		return textAreaMembroAcao;
	}

	public void setTextAreaMembroAcao(JTextArea textAreaMembroAcao) {
		this.textAreaMembroAcao = textAreaMembroAcao;
	}

	

	public JTextArea getTextAreaDescSolic() {
		return textAreaDescSolic;
	}

	public void setTextAreaDescSolic(JTextArea textAreaDescSolic) {
		this.textAreaDescSolic = textAreaDescSolic;
	}
	
	
	
	
	public JMenu getMenuPedidosInternos() {
		return menuPedidosInternos;
	}



	public void setMenuPedidosInternos(JMenu menuPedidosInternos) {
		this.menuPedidosInternos = menuPedidosInternos;
	}



	public JButton getButtonEnviarCriacao() {
		return buttonEnviarCriacao;
	}



	public void setButtonEnviarCriacao(JButton buttonEnviarCriacao) {
		this.buttonEnviarCriacao = buttonEnviarCriacao;
	}



	public JButton getButtonCancelarCriacao() {
		return buttonCancelarCriacao;
	}



	public void setButtonCancelarCriacao(JButton buttonCancelarCriacao) {
		this.buttonCancelarCriacao = buttonCancelarCriacao;
	}

	

	public JPanel getPanelConsulta() {
		return panelConsulta;
	}



	public void setPanelConsulta(JPanel panelConsulta) {
		this.panelConsulta = panelConsulta;
	}



	public JPanel getPanelRemocao() {
		return panelRemocao;
	}



	public void setPanelRemocao(JPanel panelRemocao) {
		this.panelRemocao = panelRemocao;
	}



	public JTextArea getTextAreaMembrosCriacao() {
		return textAreaMembrosCriacao;
	}



	public void setTextAreaMembrosCriacao(JTextArea textAreaMembrosCriacao) {
		this.textAreaMembrosCriacao = textAreaMembrosCriacao;
	}
	
	



	public JTextField getFieldDigPedidoRemove() {
		return fieldDigPedidoRemove;
	}



	public void setFieldDigPedidoRemove(JTextField fieldDigPedidoRemove) {
		this.fieldDigPedidoRemove = fieldDigPedidoRemove;
	}



	public JTextField getFieldIDigitPedidoPesquisa() {
		return fieldIDigitPedidoPesquisa;
	}



	public void setFieldIDigitPedidoPesquisa(JTextField fieldIDigitPedidoPesquisa) {
		this.fieldIDigitPedidoPesquisa = fieldIDigitPedidoPesquisa;
	}



	public JTextArea getTextAreaPesquisa() {
		return textAreaPesquisa;
	}



	public void setTextAreaPesquisa(JTextArea textAreaPesquisa) {
		this.textAreaPesquisa = textAreaPesquisa;
	}



	public JTextArea getTextAreaRemove() {
		return textAreaRemove;
	}



	public void setTextAreaRemove(JTextArea textAreaRemove) {
		this.textAreaRemove = textAreaRemove;
	}
	
	



	public JButton getButtonLimpaRemove() {
		return buttonLimpaRemove;
	}



	public void setButtonLimpaRemove(JButton buttonLimpaRemove) {
		this.buttonLimpaRemove = buttonLimpaRemove;
	}



	public JScrollPane getScrollPaneRemove() {
		return scrollPaneRemove;
	}



	public void setScrollPaneRemove(JScrollPane scrollPaneRemove) {
		this.scrollPaneRemove = scrollPaneRemove;
	}



	public JButton getButtonListaTodos() {
		return buttonListaTodos;
	}



	public void setButtonListaTodos(JButton buttonListaTodos) {
		this.buttonListaTodos = buttonListaTodos;
	}



	public JButton getButtonBuscaPesquisa() {
		return buttonBuscaPesquisa;
	}



	public void setButtonBuscaPesquisa(JButton buttonBuscaPesquisa) {
		this.buttonBuscaPesquisa = buttonBuscaPesquisa;
	}



	public JButton getButtonConsultRemove() {
		return buttonConsultRemove;
	}



	public void setButtonConsultRemove(JButton buttonConsultRemove) {
		this.buttonConsultRemove = buttonConsultRemove;
	}



	public JButton getButtonApagar() {
		return buttonApagar;
	}



	public void setButtonApagar(JButton buttonApagar) {
		this.buttonApagar = buttonApagar;
	}



	public JButton getButtonLimparPesquisa() {
		return buttonLimparPesquisa;
	}



	public void setButtonLimparPesquisa(JButton buttonLimparPesquisa) {
		this.buttonLimparPesquisa = buttonLimparPesquisa;
	}



	//Método para limpar os campos e as escolhas de botões
	public void limpaTela()
	{
		fieldUsuario.setText("");
		fieldPassword.setText("");
		fieldNomeUnidadeCriacao.setText("");
		fieldSiglaUnidadeCriacao.setText("");
		fieldEmailUnidadeCriacao.setText("");
		textAreaMembrosCriacao.setText("");
		bgLiber.clearSelection();
		fieldNomeUnidadeLibAcesso.setText("");
		textAreaMembroAcao.setText("");
		bgManun.clearSelection();
		textAreaDescSolic.setText("");
		fieldInfoRamal.setText("");
		fieldDigPedidoRemove.setText("");
		fieldIDigitPedidoPesquisa.setText("");
		textAreaPesquisa.setText("");
		textAreaRemove.setText("");
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
