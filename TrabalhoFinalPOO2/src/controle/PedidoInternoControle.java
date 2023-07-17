/*UFCSPA - Universidade Federal de Ciências da Saúde de Porto Alegre
 *Nomes: Alexandre Lemos Silva e Matheus Castilhos
 *Data: 07/07/2023
 *Disciplina: Programação Orientada a Objetos II
 *Curso: Informática Biomédica*/

package controle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import dao.PedidoInternoDAO;
import modelo.PedidoInterno;
import visao.JanelaPrincipal;
import modelo.Usuario;

//Classe controle dos Pedidos Internos
public class PedidoInternoControle implements ActionListener {
	
	//Declaração dos atributos
	private JanelaPrincipal jan;
	private PedidoInterno pedInt;
	private PedidoInternoDAO pedIntDAO;
	private Usuario usu;
	
	//Método especial (construtor)
	public PedidoInternoControle (JanelaPrincipal j, PedidoInterno p, Usuario u) {
		this.jan = j;
		this.pedInt = p;
		this.usu = u; 
		
		pedIntDAO = new PedidoInternoDAO(); // nova instancia da classe
		
		this.jan.getButtonEnviarCriacao().addActionListener(this);
		this.jan.getButtonEnviarLibAcesso().addActionListener(this);
		this.jan.getButtonEnviarManutSist().addActionListener(this);
		this.jan.getButtonListaTodos().addActionListener(this);
		this.jan.getButtonLimparPesquisa().addActionListener(this);
		this.jan.getButtonBuscaPesquisa().addActionListener(this);
		this.jan.getButtonConsultRemove().addActionListener(this);
		this.jan.getButtonApagar().addActionListener(this);
		this.jan.getButtonLimpaRemove().addActionListener(this);
		
	}
	
	//Método de Cadastro do Pedido Interno: Criação de Unidades
	public void enviaPedidoCriacao() {
			
		int tipo = 1;

		String tipoSolic = "";
		if(jan.getBgLiber().getSelection() != null) {
			tipoSolic = jan.getBgLiber().getSelection().getActionCommand();
		}
			
		String t1 = jan.getFieldNomeUnidadeCriacao().getText(); // NOME DA UNIDADE
		String t2 = jan.getFieldSiglaUnidadeCriacao().getText();// SIGLA DA UNIDADE
		String t3 = jan.getFieldEmailUnidadeCriacao().getText();// EMAIL DA UNIDADE
		String t4 = jan.getTextAreaMembrosCriacao().getText();// MEMBROS DA UNIDADE
			
		pedInt.setTipodePedido(tipo); // SET TIPO DE PEDIDO
		pedInt.setTipoSolicitacao(tipoSolic); // TIPO DE SOLICITACAO (UMA DAS TRES)
		pedInt.setTexto1(t1); // NOME DA UNIDADE
		pedInt.setTexto2(t2); // SIGLA DA UNIDADE
		pedInt.setTexto3(t3); // EMAIL DA UNIDADE
		pedInt.setTexto4(t4); // MEMBROS DA UNIDADE
			
		if(pedInt.validaCamposCriacao().size()> 0)
		{
			ImageIcon icon = new ImageIcon("src/imagens/notificacaoAlerta.png");
			JOptionPane.showMessageDialog(jan.getContentPane(), "Termine de preencher os campos!" , "Mensagem", 0, icon);			
		}
		else
		{
			if(pedIntDAO.cadastraPedido(pedInt, usu))
			{
				int numPedido = pedIntDAO.consultaID(pedInt, usu);
				ImageIcon icon = new ImageIcon("src/imagens/notificacaoOK.png");
				JOptionPane.showMessageDialog(jan.getContentPane(), "Processo encaminhado! Número do pedido: " + numPedido + " ." , "Mensagem", 0, icon);			
				jan.limpaTela();
			}
			
			else
			{
				ImageIcon icon = new ImageIcon("src/imagens/notificacaoErro.png");
				JOptionPane.showMessageDialog(jan.getContentPane(), "Erro temporário detectado, seu pedido Liberação de Acesso não foi enviado. Tente novamente mais tarde" , "Mensagem", 0, icon);			
				System.exit(0);
				
			}
				
		}
		
	}

	//Método de Cadastro do Pedido Interno: Liberação de Acesso
	public void enviaPedidoLiber() {
		
		int tipo = 2;

		String tipoSolic = "";
		if(jan.getBgLiber().getSelection() != null) {
			tipoSolic = jan.getBgLiber().getSelection().getActionCommand();
		}
		
		String t1 = jan.getFieldNomeUnidadeLibAcesso().getText(); // NOME DA UNIDADE
		String t2 = jan.getTextAreaMembroAcao().getText(); // MEMBRO A SER EFETUADO
		
		pedInt.setTipodePedido(tipo); // SET TIPO 
		pedInt.setTipoSolicitacao(tipoSolic); // TIPO DE SOLICITACAO (UMA DAS TRES)
		pedInt.setTexto1(t1); // NOME DA UNIDADE
		pedInt.setTexto2(t2); // MEMBRO A SER EFETUADO
		
		if(pedInt.validaCamposLiberacao().size()> 0)
		{
			ImageIcon icon = new ImageIcon("src/imagens/notificacaoAlerta.png");
			JOptionPane.showMessageDialog(jan.getContentPane(), "Termine de preencher os campos!" , "Mensagem", 0, icon);			
		}
		else
		{
			if(pedIntDAO.cadastraPedido(pedInt, usu))
			{
				int numPedido = pedIntDAO.consultaID(pedInt, usu);
				ImageIcon icon = new ImageIcon("src/imagens/notificacaoOK.png");
				JOptionPane.showMessageDialog(jan.getContentPane(), "Processo encaminhado! Número do pedido: " + numPedido + " ." , "Mensagem", 0, icon);			
				jan.limpaTela();
			}
				
			else
			{
				ImageIcon icon = new ImageIcon("src/imagens/notificacaoErro.png");
				JOptionPane.showMessageDialog(jan.getContentPane(), "Erro temporário detectado, seu pedido Liberação de Acesso não foi enviado. Tente novamente mais tarde" , "Mensagem", 0, icon);			
				System.exit(0);
				
			}
				
		}
		
	}
		
	//Método de Cadastro do Pedido Interno: Manutenção do Sistema
	public void enviaPedidoManun() {
		
		int tipo = 3;
		
		String tipoSolic = "";
		if(jan.getBgManun().getSelection() != null) {
			tipoSolic = jan.getBgManun().getSelection().getActionCommand();
		}
		String t1 = jan.getTextAreaDescSolic().getText();
		String t2 = jan.getFieldInfoRamal().getText();
		

		pedInt.setTipodePedido(tipo);
		pedInt.setTipoSolicitacao(tipoSolic);
		pedInt.setTexto1(t1);
		pedInt.setTexto2(t2);
		t2 = t2.replaceAll(" ", "");
		
		if(pedInt.validaCamposManutencao().size()> 0)
		{
			
			if(t2.equals("") && pedInt.validaCamposManutencao().size() == 1) {
				
				int ramal = JOptionPane.showConfirmDialog(jan, "Deseja prosseguir sem informar seu ramal/contato?", "Falta de Ramal", 0);
				
				if(ramal == 0) {
					pedInt.setTexto2("Não informou contato");
					
					if(pedIntDAO.cadastraPedido(pedInt, usu))
					{	
						int numPedido = pedIntDAO.consultaID(pedInt, usu);
						ImageIcon icon = new ImageIcon("src/imagens/notificacaoOK.png");
						JOptionPane.showMessageDialog(jan.getContentPane(), "Processo encaminhado! Número do pedido: " + numPedido + " ." , "Mensagem", 0, icon);			
						jan.limpaTela();
					}
					else
					{
						ImageIcon icon = new ImageIcon("src/imagens/notificacaoErro.png");
						JOptionPane.showMessageDialog(jan.getContentPane(), "Erro temporário detectado, seu pedido de Manutenção do Sistema não foi enviado. Tente novamente mais tarde" , "Mensagem", 0, icon);			
						System.exit(0);
					}
					
				}
				
				
			} else {
			
				ImageIcon icon = new ImageIcon("src/imagens/notificacaoAlerta.png");
				JOptionPane.showMessageDialog(jan.getContentPane(), "Termine de preencher os campos!" , "Mensagem", 0, icon);			
			}
		}
		else
		{
			if(pedIntDAO.cadastraPedido(pedInt, usu))
			{
				
				int numPedido = pedIntDAO.consultaID(pedInt, usu);
				ImageIcon icon = new ImageIcon("src/imagens/notificacaoOK.png");
				JOptionPane.showMessageDialog(jan.getContentPane(), "Processo encaminhado! Número do pedido: " + numPedido + " ." , "Mensagem", 0, icon);			
				jan.limpaTela();
		
			}
				
			else
			{

				ImageIcon icon = new ImageIcon("src/imagens/notificacaoErro.png");
				JOptionPane.showMessageDialog(jan.getContentPane(), "Erro temporário detectado, seu pedido de Manutenção do Sistema não foi enviado. Tente novamente mais tarde" , "Mensagem", 0, icon);			
				System.exit(0);
				
			}
				
		}
		
	}
	
	//Método de Busca de Pedido no Banco de Dados com base no seu código ID único
	public void buscaID() {
		
		String aux = this.jan.getFieldIDigitPedidoPesquisa().getText();
		
		if(validaBuscadorComoInteger(aux) == true) {
			int pesquisaID = Integer.parseInt(aux);
			pedInt = pedIntDAO.listaPedidoUnico(usu, pesquisaID);
			
			switch (pedInt.getTipodePedido()) {
			case 1:
				jan.getTextAreaPesquisa().setText("");
				jan.getTextAreaPesquisa().append("Pedido: " + pedInt.getPedidoID());
				jan.getTextAreaPesquisa().append("\n---------- Tipo de Pedido: Criação de Unidades");
				jan.getTextAreaPesquisa().append("\n---------- Nome da Unidade: " + pedInt.getTexto1());
				jan.getTextAreaPesquisa().append("\n---------- Sigla da Unidade: " + pedInt.getTexto2());
				jan.getTextAreaPesquisa().append("\n---------- E-mail da Unidade: " + pedInt.getTexto3());
				jan.getTextAreaPesquisa().append("\n---------- Membros da Unidade: " + pedInt.getTexto4());
				jan.getTextAreaPesquisa().append("\n----------------------- \n\n");
				break;
			case 2:
				jan.getTextAreaPesquisa().setText("");
				jan.getTextAreaPesquisa().append("Pedido: " + pedInt.getPedidoID());
				jan.getTextAreaPesquisa().append("\n---------- Tipo de Pedido: Liberação de Acessos");
				jan.getTextAreaPesquisa().append("\n-----------Tipo de Solicitação: " + pedInt.getTipoSolicitacao());
				jan.getTextAreaPesquisa().append("\n---------- Nome da Unidade: " + pedInt.getTexto1());
				jan.getTextAreaPesquisa().append("\n---------- Membros Afetados da Unidade: " + pedInt.getTexto2());
				jan.getTextAreaPesquisa().append("\n----------------------- \n\n");
				break;
				
			case 3:
				jan.getTextAreaPesquisa().setText("");
				jan.getTextAreaPesquisa().append("Pedido: " + pedInt.getPedidoID());
				jan.getTextAreaPesquisa().append("\n---------- Tipo de Pedido: Manutenção de Sistemas");
				jan.getTextAreaPesquisa().append("\n-----------Tipo de Solicitação: " + pedInt.getTipoSolicitacao());
				jan.getTextAreaPesquisa().append("\n---------- Descrição da Solicitação: " + pedInt.getTexto1());
				jan.getTextAreaPesquisa().append("\n---------- Ramal/Contato: " + pedInt.getTexto2());
				jan.getTextAreaPesquisa().append("\n----------------------- \n\n");
				break;

			default:
				jan.getTextAreaPesquisa().setText("Pedido " + this.jan.getFieldIDigitPedidoPesquisa().getText() + " não foi encontrado no Banco de Dados.");
				break;
			}
			
			
			
			
		}else {
			jan.getTextAreaPesquisa().setText("Erro: Número de pedido inválido");
		}
		
		
	}
	
	//Método de Conferir de o que está na busca é uma código válido, ou seja, um inteiro
	private boolean validaBuscadorComoInteger(String aux) {
		// TODO Auto-generated method stub
		return aux != null && aux.matches("[0-9]*");
	}

	//Método que Lista todos os Pedidos do Usuário autenticado
	public void listaTodosPedidosInternos() {
		
		ArrayList<PedidoInterno> vetorDePedidos = pedIntDAO.listaPedidoPorUsuario(usu);
		
		if (vetorDePedidos.size() > 0) {
			
			for (int i = 0; i < vetorDePedidos.size(); i++) {
				
				int tipoPedido = vetorDePedidos.get(i).getTipodePedido();
				
				switch (tipoPedido) {
				case 1:
					jan.getTextAreaPesquisa().append("Pedido: " + vetorDePedidos.get(i).getPedidoID());
					jan.getTextAreaPesquisa().append("\n---------- Tipo de Pedido: Criação de Unidades");
					jan.getTextAreaPesquisa().append("\n---------- Nome da Unidade: " + vetorDePedidos.get(i).getTexto1());
					jan.getTextAreaPesquisa().append("\n---------- Sigla da Unidade: " + vetorDePedidos.get(i).getTexto2());
					jan.getTextAreaPesquisa().append("\n---------- E-mail da Unidade: " + vetorDePedidos.get(i).getTexto3());
					jan.getTextAreaPesquisa().append("\n---------- Membros da Unidade: " + vetorDePedidos.get(i).getTexto4());
					jan.getTextAreaPesquisa().append("\n----------------------- \n\n");
					break;
				
				case 2:
					jan.getTextAreaPesquisa().append("Pedido: " + vetorDePedidos.get(i).getPedidoID());
					jan.getTextAreaPesquisa().append("\n---------- Tipo de Pedido: Liberação de Acessos");
					jan.getTextAreaPesquisa().append("\n-----------Tipo de Solicitação: " + vetorDePedidos.get(i).getTipoSolicitacao());
					jan.getTextAreaPesquisa().append("\n---------- Nome da Unidade: " + vetorDePedidos.get(i).getTexto1());
					jan.getTextAreaPesquisa().append("\n---------- Membros Afetados da Unidade: " + vetorDePedidos.get(i).getTexto2());
					jan.getTextAreaPesquisa().append("\n----------------------- \n\n");
					break;
					
				case 3:
					jan.getTextAreaPesquisa().append("Pedido: " + vetorDePedidos.get(i).getPedidoID());
					jan.getTextAreaPesquisa().append("\n---------- Tipo de Pedido: Manutenção de Sistemas");
					jan.getTextAreaPesquisa().append("\n-----------Tipo de Solicitação: " + vetorDePedidos.get(i).getTipoSolicitacao());
					jan.getTextAreaPesquisa().append("\n---------- Descrição da Solicitação: " + vetorDePedidos.get(i).getTexto1());
					jan.getTextAreaPesquisa().append("\n---------- Ramal/Contato: " + vetorDePedidos.get(i).getTexto2());
					jan.getTextAreaPesquisa().append("\n----------------------- \n\n");
					break;

				default:
					break;
				}
				
			}
			
			
			
		} else {
			ImageIcon icon = new ImageIcon("src/imagens/notificacaoErro.png");
			JOptionPane.showMessageDialog(jan.getContentPane(), "Nenhum processo encontrado." , "Mensagem", 0, icon);			
			jan.getMenuSEI().setEnabled(true);	
		}
		
		
	}
	
	//Método de Busca para conferir se o pedido está disponível no banco de dados
	// para remoção com base em sua ID e no usuário autenticado.
	public void buscaIdParaRemover() {
		
		String aux = this.jan.getFieldDigPedidoRemove().getText();
		
		if(validaBuscadorComoInteger(aux) == true) {
			int pesquisaID = Integer.parseInt(aux);
			pedInt = pedIntDAO.listaPedidoUnico(usu, pesquisaID);
			
			switch (pedInt.getTipodePedido()) {
			case 1:
				jan.getTextAreaRemove().setText("");
				jan.getTextAreaRemove().append("Pedido: " + pedInt.getPedidoID());
				jan.getTextAreaRemove().append("\n---------- Tipo de Pedido: Criação de Unidades");
				jan.getTextAreaRemove().append("\n---------- Nome da Unidade: " + pedInt.getTexto1());
				jan.getTextAreaRemove().append("\n---------- Sigla da Unidade: " + pedInt.getTexto2());
				jan.getTextAreaRemove().append("\n---------- E-mail da Unidade: " + pedInt.getTexto3());
				jan.getTextAreaRemove().append("\n---------- Membros da Unidade: " + pedInt.getTexto4());
				jan.getTextAreaRemove().append("\n----------------------- \n\n");
				jan.getFieldDigPedidoRemove().setEditable(false);
				jan.getButtonApagar().setEnabled(true);
				break;
			case 2:
				jan.getTextAreaRemove().setText("");
				jan.getTextAreaRemove().append("Pedido: " + pedInt.getPedidoID());
				jan.getTextAreaRemove().append("\n---------- Tipo de Pedido: Liberação de Acessos");
				jan.getTextAreaRemove().append("\n-----------Tipo de Solicitação: " + pedInt.getTipoSolicitacao());
				jan.getTextAreaRemove().append("\n---------- Nome da Unidade: " + pedInt.getTexto1());
				jan.getTextAreaRemove().append("\n---------- Membros Afetados da Unidade: " + pedInt.getTexto2());
				jan.getTextAreaRemove().append("\n----------------------- \n\n");
				jan.getFieldDigPedidoRemove().setEditable(false);
				jan.getButtonApagar().setEnabled(true);
				break;
				
			case 3:
				jan.getTextAreaRemove().setText("");
				jan.getTextAreaRemove().append("Pedido: " + pedInt.getPedidoID());
				jan.getTextAreaRemove().append("\n---------- Tipo de Pedido: Manutenção de Sistemas");
				jan.getTextAreaRemove().append("\n-----------Tipo de Solicitação: " + pedInt.getTipoSolicitacao());
				jan.getTextAreaRemove().append("\n---------- Descrição da Solicitação: " + pedInt.getTexto1());
				jan.getTextAreaRemove().append("\n---------- Ramal/Contato: " + pedInt.getTexto2());
				jan.getTextAreaRemove().append("\n----------------------- \n\n");
				jan.getFieldDigPedidoRemove().setEditable(false);
				jan.getButtonApagar().setEnabled(true);
				break;

			default:
				jan.getTextAreaRemove().setText("Pedido " + this.jan.getFieldDigPedidoRemove().getText() + " não foi encontrado no Banco de Dados.");
				jan.getFieldDigPedidoRemove().setText("");
				break;
			}
			
			
			
			
		}else {
			jan.getTextAreaRemove().setText("Erro: Número de pedido inválido");
			jan.getFieldDigPedidoRemove().setText("");
		}
		
		
	}		
	
	//Método para Apagar determinado pedido.
	public void removePedido() {
		
		String aux = this.jan.getFieldDigPedidoRemove().getText();
		
		int pesquisaID = Integer.parseInt(aux);
		pedInt = pedIntDAO.listaPedidoUnico(usu, pesquisaID);
		
		if(pedIntDAO.apagaPedido(pedInt)){
			jan.getButtonApagar().setEnabled(false);
			ImageIcon icon = new ImageIcon("src/imagens/notificacaoOK.png");
			JOptionPane.showMessageDialog(jan.getContentPane(), "Processo excluído com sucesso." , "Mensagem", 0, icon);
			
			
		}else {
			jan.getButtonApagar().setEnabled(false);
			ImageIcon icon = new ImageIcon("src/imagens/notificacaoErro.png");
			JOptionPane.showMessageDialog(jan.getContentPane(), "Erro detectado no momento de apagar. Tente novamente mais tarde" , "Mensagem", 0, icon);		
		}
	}
	
	
	//Método que define a ação dos botões por meio do ActionEvent.
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getActionCommand().equals("Enviar Criacao"))
		{
			enviaPedidoCriacao();
		}
		
		else if(e.getActionCommand().equals("Enviar Liberacao"))
		{
			enviaPedidoLiber();
		}
		else if(e.getActionCommand().equals("Enviar Manutencao"))
		{
			enviaPedidoManun();
		}
		else if(e.getActionCommand().equals("Listar Todos"))
		{
			this.jan.getTextAreaPesquisa().setText("");
			listaTodosPedidosInternos();
		}
		else if(e.getActionCommand().equals("Limpar Pesquisa"))
		{
			this.jan.getTextAreaPesquisa().setText("");
			this.jan.getButtonListaTodos().setEnabled(true);
		}
		else if(e.getActionCommand().equals("Busca Pesquisa")) {
			
			if(this.jan.getFieldIDigitPedidoPesquisa().getText().equals(""))
			{
				this.jan.getFieldIDigitPedidoPesquisa().setText("");
			}else {
			buscaID();
			this.jan.getFieldIDigitPedidoPesquisa().setText("");
			}
		}
		else if(e.getActionCommand().equals("Consultar"))
		{
			buscaIdParaRemover();
		}
		
		else if(e.getActionCommand().equals("Apagar"))
		{
			removePedido();
			this.jan.getTextAreaRemove().setText("");
			jan.getFieldDigPedidoRemove().setText("");
			jan.getFieldDigPedidoRemove().setEditable(true);
		}
		else if(e.getActionCommand().equals("Limpa Remove"))
		{
			this.jan.getTextAreaRemove().setText("");
			jan.getFieldDigPedidoRemove().setText("");
			jan.getFieldDigPedidoRemove().setEditable(true);
			this.jan.getButtonApagar().setEnabled(false);
		}
		

		
	}

}