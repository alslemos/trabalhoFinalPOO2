/*UFCSPA - Universidade Federal de Ciências da Saúde de Porto Alegre
 *Nomes: Alexandre Lemos Silva e Matheus Castilhos
 *Data: 07/07/2023
 *Disciplina: Programação Orientada a Objetos II
 *Curso: Informática Biomédica*/

package modelo;


import java.util.ArrayList;
import java.util.List;

//Classe Modelo do Pedido Interno, com todas suas variáveis e diferentes construtores.
public class PedidoInterno {
	
	int tipodePedido, pedidoID;
	String texto1, texto2, texto3, texto4, tipoSolicitacao;
	
	//Métodos Especiais (constructor, getters e setters)
	public PedidoInterno(int tipodePedido, int pedidoID, String texto1,
			String texto2, String texto3, String texto4) {
		super();
		this.tipodePedido = tipodePedido;
		this.pedidoID = pedidoID;
		this.texto1 = texto1;
		this.texto2 = texto2;
		this.texto3 = texto3;
		this.texto4 = texto4;
	}
	
	public PedidoInterno(int tipodePedido, int pedidoID, String tipoSolicitacao, String texto1,
			String texto2) {
		super();
		this.tipodePedido = tipodePedido;
		this.pedidoID = pedidoID;
		this.tipoSolicitacao = tipoSolicitacao;
		this.texto1 = texto1;
		this.texto2 = texto2;
	}
	
	
	public PedidoInterno() {
		
	}





	public int getTipodePedido() {
		return tipodePedido;
	}

	public void setTipodePedido(int tipodePedido) {
		this.tipodePedido = tipodePedido;
	}

	public int getPedidoID() {
		return pedidoID;
	}

	public void setPedidoID(int pedidoID) {
		this.pedidoID = pedidoID;
	}

	public String getTipoSolicitacao() {
		return tipoSolicitacao;
	}


	public void setTipoSolicitacao(String tipoSolicitacao) {
		this.tipoSolicitacao = tipoSolicitacao;
	}


	public String getTexto1() {
		return texto1;
	}


	public void setTexto1(String texto1) {
		this.texto1 = texto1;
	}


	public String getTexto2() {
		return texto2;
	}


	public void setTexto2(String texto2) {
		this.texto2 = texto2;
	}


	
	public String getTexto3() {
		return texto3;
	}


	public void setTexto3(String texto3) {
		this.texto3 = texto3;
	}


	public String getTexto4() {
		return texto4;
	}


	public void setTexto4(String texto4) {
		this.texto4 = texto4;
	}

	//Método para conferir se algum campo da solicitação do usuário está em branco e retorno um Array com a quantidade.
	public List<String> validaCamposCriacao() {
		ArrayList<String> listaCampos= new ArrayList<String>();
		
		String t1 = texto1;
		String t2 = texto2;
		String t3 = texto3;
		String t4 = texto4;
		
		t1 = t1.replaceAll(" ", "");
		t2 = t2.replaceAll(" ", "");
		t3 = t3.replaceAll(" ", "");
		t4 = t4.replaceAll(" ", "");
		
		if(t1.equals(""))
			listaCampos.add("Nome da Unidade");
		if(t2.equals(""))
			listaCampos.add("Sigla da Unidade");
		if(t3.equals(""))
			listaCampos.add("Email da Unidade");
		if(t4.equals(""))
			listaCampos.add("Membros da Unidade");

		
		return listaCampos;
	}
	
	//Método para conferir se algum campo da solicitação do usuário está em branco e retorno um Array com a quantidade.
	public List<String> validaCamposLiberacao() {
		ArrayList<String> listaCampos= new ArrayList<String>();
		
		String t1 = texto1;
		String t2 = texto2;
		
		t1 = t1.replaceAll(" ", "");
		t2 = t2.replaceAll(" ", "");

		if(tipoSolicitacao == "")
			listaCampos.add("Tipo de Soliticação");
		if(t1.equals(""))
			listaCampos.add("Nome da Unidade");
		if(t2.equals(""))
			listaCampos.add("Membro a ser efetuada a ação");
		
		return listaCampos;
	}
	
	
	//Método para conferir se algum campo da solicitação do usuário está em branco e retorno um Array com a quantidade.
	public List<String> validaCamposManutencao() {
		ArrayList<String> listaCampos= new ArrayList<String>();
		
		String t1 = texto1;
		String t2 = texto2;
		
		t1 = t1.replaceAll(" ", "");
		t2 = t2.replaceAll(" ", "");
	
		if(tipoSolicitacao == "")
			listaCampos.add("Tipo de Soliticação");
		if(t1.equals(""))
			listaCampos.add("Descrição da Solicitação");
		if(t2.equals(""))
			listaCampos.add("RAFAL/Telefone");
		
		return listaCampos;
	}

}
