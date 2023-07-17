/*UFCSPA - Universidade Federal de Ciências da Saúde de Porto Alegre
 *Nomes: Alexandre Lemos Silva e Matheus Castilhos
 *Data: 07/07/2023
 *Disciplina: Programação Orientada a Objetos II
 *Curso: Informática Biomédica*/

package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.Usuario;

//Classe de ControleDAO dos Pedidos Internos
public class UsuarioDAO {
	private Connection con;
			
	public UsuarioDAO() {}
	 
	//Método que autentica e valida a entrada do usuário.
	public boolean autenticaUsuario(Usuario u) {
		PreparedStatement prep;
		ResultSet resSet;
		String sql;
		
		con = ConexaoSQL.abrirConexaoMySQL();
		
		sql = "select * from Usuarios where nome like ? and senha like ? ";
		
	
		try {
			prep = con.prepareStatement(sql);
			prep.setString(2, u.getSenha());
			prep.setString(1, u.getUsuario());
			
			resSet = prep.executeQuery();
			
			if(resSet.next()) {
				u.setId(resSet.getInt("ID"));
				ConexaoSQL.fecharConexao();
				return true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	
		return false;
		
	}
	
}
		
