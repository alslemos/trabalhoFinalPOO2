/*UFCSPA - Universidade Federal de Ciências da Saúde de Porto Alegre
 *Nomes: Alexandre Lemos Silva e Matheus Castilhos
 *Data: 07/07/2023
 *Disciplina: Programação Orientada a Objetos II
 *Curso: Informática Biomédica*/

package modelo;


//Classe Modelo do Usuário com suas variáveis e Construtores.
public class Usuario {
	//Declaração dos atributos
	private String usuario, senha;
	private int id;
	
	//Métodos especiais (constructor, getters e setters)
	public Usuario(int id, String usuario, String senha) {
		super();
		this.id = id;
		this.usuario = usuario;
		this.senha = senha;
	}
	
	public Usuario() {
		
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String cs) {
		this.senha = cs;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

		
}
