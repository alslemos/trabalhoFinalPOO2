/*UFCSPA - Universidade Federal de Ciências da Saúde de Porto Alegre
 *Nomes: Alexandre Lemos Silva e Matheus Castilhos
 *Data: 07/07/2023
 *Disciplina: Programação Orientada a Objetos II
 *Curso: Informática Biomédica*/

package controle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import dao.UsuarioDAO;
import modelo.Usuario;
import visao.JanelaPrincipal;

//Classe Controle do Usuário
public class UsuarioControle implements ActionListener {

	// Declaracao dos atributos
	private JanelaPrincipal jan;
	private Usuario us;
	private UsuarioDAO udao;

	
	//Método Especial (constructor)
	public UsuarioControle(JanelaPrincipal j, Usuario u)
	{
		this.jan=j;
		this.us=u;
		udao = new UsuarioDAO();
		
		//Ação dos botões Autenticar e Cancelar (de todas as telas)
		this.jan.getButtonAutenticar().addActionListener(this);
		this.jan.getButtonCancelar().addActionListener(this);
		this.jan.getButtonCancelarLibAcesso().addActionListener(this);
		this.jan.getButtonCancelarManutSist().addActionListener(this);
		this.jan.getButtonCancelarCriacao().addActionListener(this);
		
	}
	
	//Método de autenticação de usuário
	public void autenticarUsuario() {
		
		us.setUsuario(jan.getFieldUsuario().getText());
		String senha = String.valueOf(jan.getFieldPassword().getPassword());
		us.setSenha(senha);
		
	
		if(udao.autenticaUsuario(us))
		{
			jan.limpaTela(); // limpando a tela
			ImageIcon icon = new ImageIcon("src/imagens/loginOk.png");
			JOptionPane.showMessageDialog(jan.getContentPane(), "Acesso autorizado, seja bem-vindo!", "Mensagem", 0, icon);			
			jan.getMenuSEI().setEnabled(true);
			jan.getMenuPedidosInternos().setEnabled(true);
			
		}
		else
		{
			jan.limpaTela();
			ImageIcon icon = new ImageIcon("src/imagens/loginFale.png");
			JOptionPane.showMessageDialog(jan.getContentPane(), "Falha na autenticação, verifique as informações e tente novamente!", "Mensagem", 0, icon);			

		}
		
		
	}
	
	
	//Método para definir a ação dos botões Cancelar, de todos submenus, e Autenticar apenas do submenu Autenticar
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//Botão Cancelar - retorna ao menu de entrada e limpa a tela
		if(e.getActionCommand().equals("Cancelar"))
		{
			this.jan.getPanelEntrada().setVisible(true);
			this.jan.getPanelConsulta().setVisible(false);
			this.jan.getPanelRemocao().setVisible(false);
			this.jan.getPanelAutenticar().setVisible(false);
			this.jan.getPanelCriacaoUnidades().setVisible(false);
			this.jan.getPanelLiberacaoAcesso().setVisible(false);
			this.jan.getPanelManutencaoSistema().setVisible(false);
			jan.limpaTela();
		}
		//Botão Autenticar - utiliza o método autenticar usuario declarado anteriormente
		else if(e.getActionCommand().equals("Autenticar"))
		{
			autenticarUsuario();
		}
		
	}
}