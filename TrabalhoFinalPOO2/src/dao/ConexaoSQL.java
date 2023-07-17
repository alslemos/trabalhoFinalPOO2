/*UFCSPA - Universidade Federal de Ciências da Saúde de Porto Alegre
 *Nomes: Alexandre Lemos Silva e Matheus Castilhos
 *Data: 07/07/2023
 *Disciplina: Programação Orientada a Objetos II
 *Curso: Informática Biomédica*/

package dao;

// Classes necessárias para o uso do Banco de Dados
import java.sql.Connection; 
import java.sql.DriverManager;
import javax.swing.JOptionPane;

// Incio da classe de conexao
public class ConexaoSQL {
	private static String status = "Nao conectou";
	private static Connection connection = null;
	
	// método construtor da classe
	public ConexaoSQL() {}

	// getters e setters
	public static String getStatus() {
		return status;
	}

	public static void setStatus(String status) {
		ConexaoSQL.status = status;
	}

	public static Connection getConnection() {
		return connection;
	}

	public static void setConnection(Connection connection) {
		ConexaoSQL.connection = connection;
	}

	
	// abre a conexao
	public static Connection abrirConexaoMySQL() {
		
		connection = null;
		
		try {
			String serverName = "localhost"; // caminho do servidor
			String mydatabase = "TrabalhoPOO"; // nome do banco de dados
			String username = "root";  //nome do usuario
			String password = "usuario145236";  // senha de acesso
			String url = "jdbc:mysql://"+serverName+":3306/"+mydatabase;   
			
			connection = DriverManager.getConnection(url, username, password);
			
			//Testando a conexao
			if(connection != null) {
				status = "Conectado com sucesso";
			} else {
				status = "Não foi possível realizar a conexão";
			}
			
			return connection;
			
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao tentar conexão com Banco de Dados", "Erro", 0);
			System.exit(0);
			return null;
		}
	}
	
	// retorna o status da conexao
	public static void obterStatusConexao() {
		System.out.println(status);
	}
	
	// fecha a conexao
	public static boolean fecharConexao() {
		try {
			connection.close();
			return true;
		}catch (Exception e) {
			return false;
		}
	}
	
	// método que fecha e abre novamente a conexao
	public static Connection reiniciarConexao() {
		fecharConexao();
		return ConexaoSQL.abrirConexaoMySQL();
		
	}
	
}
