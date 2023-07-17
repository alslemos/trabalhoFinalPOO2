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
import java.util.ArrayList;

import modelo.Usuario;
import modelo.PedidoInterno;

//Classe de ControleDAO dos Pedidos Internos
public class PedidoInternoDAO {
	private Connection con;
	public PedidoInternoDAO(){}
	
	//Método para Cadastrar Pedido Interno no Banco de Dados
	public boolean cadastraPedido(PedidoInterno p, Usuario u) {
		PreparedStatement prep;
		ResultSet res;
		String sql;
		int i = 0;


		try {
			con = ConexaoSQL.abrirConexaoMySQL();
			var auxiliar = p.getTipodePedido(); // ou passagem por parametro
			
			sql = "insert into PedidosInternos values (null)";
			prep = con.prepareStatement(sql);
			prep.executeUpdate();
			
			sql = "select * from PedidosInternos where SolicitacaoID = (SELECT MAX(SolicitacaoID) FROM PedidosInternos)";	
			
			prep = con.prepareStatement(sql);
			res = prep.executeQuery();
			
			while(res.next()) {
				i = res.getInt(1);
			}
			
			prep.close();
			
			
	
		switch (auxiliar) {
			case 1: {
				// 1 -> Criação de Unidade
				sql = "insert into CriacaoUnidades values (?,?,?,?,?,?)";		
				
				prep = con.prepareStatement(sql);
				prep.setInt(1, i); //Número do Pedido
				prep.setString(2, p.getTexto1()); // Nome da Unidade/TextField
				prep.setString(3, p.getTexto2()); // Sigla da Unidade/TextField
				prep.setString(4, p.getTexto3()); // Email da Unidade/TextField
				prep.setString(5, p.getTexto4()); //Membros da Unidade/TextArea
				prep.setInt(6, u.getId()); //ID do Usuário/Padrão
				prep.executeUpdate(); // Executa a query
				
				break;
				
				
						
			}
			
			case 2: {		
				// 2 -> Liberação de Acesso
				sql = "Insert into LiberacaoAcessos values(?,?,?,?,?);";
				
				prep = con.prepareStatement(sql);
				prep.setInt(1, i); //Número do Pedido
				prep.setString(2, p.getTipoSolicitacao());  // Tipo de Soliticação/RadioButtons
				prep.setString(3, p.getTexto1()); // Nome da Unidade/TextField
				prep.setString(4, p.getTexto2()); // MembrosUnidade/TextArea
				prep.setInt(5, u.getId()); // ID do Usuário/Padrão
				
				prep.executeUpdate(); // Executa a query
				break;
				
				
				
			}
			
			
			default: {
				// 3 -> Manutenção de Unidades
				sql = "insert into ManutencaoSistemas values (?,?,?,?,?)";
				
				prep = con.prepareStatement(sql);
				prep.setInt(1, i); //Número do Pedido
				prep.setString(2, p.getTipoSolicitacao());  // Tipo de Soliticação/RadioButtons
				prep.setString(3, p.getTexto1()); // Descrição da Soliticação/TextArea
				prep.setString(4, p.getTexto2()); // Ramal/TextField
				prep.setInt(5, u.getId()); // ID do Usuário/Padrão
		
				prep.executeUpdate(); // Executa a query
				break;
			}			
			
		}
		
		return true;
	
		} catch (Exception e) {
			ConexaoSQL.fecharConexao();
			e.printStackTrace();
			
			return false;	
		}
	}
	
	//Método para Consultar os Pedidos Internos no Banco de Dados com base na ID do Pedido e do Usuário autenticado.
	public int consultaID(PedidoInterno p, Usuario u) {
		PreparedStatement prep;
		ResultSet res;
		String sql;
		int i = 0, tipo = p.getTipodePedido();
		String identific = p.getTexto1();
		
		try {
			
			ConexaoSQL.abrirConexaoMySQL();
			con = ConexaoSQL.getConnection();
			
		switch (tipo) {
		case 1: {
			// 1 -> Criação de Unidade
			sql = "select * from CriacaoUnidades where NomeDaUnidade = ?";	
			
			prep = con.prepareStatement(sql);
			prep.setString(1, identific);
			res = prep.executeQuery();
			
			while(res.next()) {
				i = res.getInt(1);
			}
			
			
			break;
			
			
					
		}
		
		case 2: {		
			// 2 -> Liberação de Acesso
			sql = "select * from LiberacaoAcessos where NomeDaUnidade = ?";
			
			prep = con.prepareStatement(sql);
			prep.setString(1, identific);
			res = prep.executeQuery();
			
			while(res.next()) {
				i = res.getInt(1);
			}
			
			break;
			
			
			
		}
		
		
		default: {
			// 3 -> Manutenção de Unidades
			sql = "select * from ManutencaoSistemas where DescricaoSolicitao = ?";
			
			prep = con.prepareStatement(sql);
			prep.setString(1, identific);
			res = prep.executeQuery();
			
			while(res.next()) {
				i = res.getInt(1);
			}
			
			break;
		}
		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
		return i;
		
	}
	
	//Método para Lista um Pedido Interno específico.
	public PedidoInterno listaPedidoUnico(Usuario u, int pesquisaID) {
		PreparedStatement prepS = null;
		
		PedidoInterno pedInt = new PedidoInterno();
		
		int numPedido = 0;
		String tipodeSolicitacao = "";
		String texto1 = "" ;
		String texto2 = "";
		String texto3 = "";
		String texto4 = "";
		
		ResultSet retorno;
		String sql0, sql1, sql2, sql3;
		int aux = 0;
		
		ConexaoSQL.abrirConexaoMySQL();
		con = ConexaoSQL.getConnection();
		
		sql0 = "select * from PedidosInternos where SolicitacaoID = ?;";
		sql1 = "select * from CriacaoUnidades where SolicitacaoID = ?  AND IdUsuarioCriador = ?;"; 
		sql2 = "Select * from LiberacaoAcessos where SolicitacaoID = ?  AND IdUsuarioCriador = ?;"; 
		sql3 = "Select * from ManutencaoSistemas where SolicitacaoID = ?  AND IdUsuarioCriador = ?;"; 
		
		try {
			prepS =  con.prepareStatement(sql0);
			prepS.setInt(1, pesquisaID) ; // passando o int de PesquisaID
			retorno = prepS.executeQuery();
			
			while(retorno.next()) {
				numPedido = retorno.getInt(1);
			}
			prepS.close();
			
			if(numPedido > 0) {
				prepS =  con.prepareStatement(sql1);
				prepS.setInt(1, pesquisaID) ; // passando o int de PesquisaID
				prepS.setInt(2, u.getId()); //passando int do UsuarioID
				retorno = prepS.executeQuery();
				
				while(retorno.next()) {
					numPedido = retorno.getInt(1);
					aux = 1;
				}
				prepS.close();
				
				prepS =  con.prepareStatement(sql2);
				prepS.setInt(1, pesquisaID) ; // passando o int de PesquisaID
				prepS.setInt(2, u.getId()); //passando int do UsuarioID
				retorno = prepS.executeQuery();
				
				while(retorno.next()) {
					numPedido = retorno.getInt(1);
					aux = 2;
				}
				prepS.close();
				
				prepS =  con.prepareStatement(sql3);
				prepS.setInt(1, pesquisaID) ; // passando o int de PesquisaID
				prepS.setInt(2, u.getId()); //passando int do UsuarioID
				retorno = prepS.executeQuery();
				
				while(retorno.next()) {
					numPedido = retorno.getInt(1);
					aux = 3;
				}
				prepS.close();
				
				
				
			}else {
				aux = 0;
			}

		
			
			switch (aux) {
			case 1:
				prepS =  con.prepareStatement(sql1);
				prepS.setInt(1, pesquisaID) ; // passando o int de PesquisaID
				prepS.setInt(2, u.getId()); //passando int do UsuarioID
				retorno = prepS.executeQuery();
	
				while (retorno.next()) {
					numPedido = retorno.getInt(1); // ID do Pedido
					texto1 = retorno.getString(2) ; // Nome da Unidade
					texto2 = retorno.getString(3); // Sigla da Unidade
					texto3 = retorno.getString(4); // Email da Unidade
					texto4 = retorno.getString(5); // Membros da Unidade
					
					pedInt = new PedidoInterno(1, numPedido, texto1, texto2, texto3, texto4);
				}
		
				break;
			case 2:
				prepS =  con.prepareStatement(sql2);
				prepS.setInt(1, pesquisaID) ; // passando o int de PesquisaID
				prepS.setInt(2, u.getId()); //passando int do UsuarioID
				retorno = prepS.executeQuery();
	
				while (retorno.next()) {
					numPedido = retorno.getInt(1); // ID do pedido
					tipodeSolicitacao = retorno.getString(2); //Tipo de Solicitação de Liberação
					texto1 = retorno.getString(3) ; // Nome da Unidade
					texto2 = retorno.getString(4); // Membros afetados
				
					pedInt = new PedidoInterno(2, numPedido, tipodeSolicitacao, texto1, texto2);
				}
				break;
			case 3:
				prepS =  con.prepareStatement(sql3);
				prepS.setInt(1, pesquisaID) ; // passando o int de PesquisaID
				prepS.setInt(2, u.getId()); //passando int do UsuarioID
				retorno = prepS.executeQuery();
				
				while (retorno.next()) {
					numPedido = retorno.getInt(1); // ID do Pedido
					tipodeSolicitacao = retorno.getString(2); //Tipo de Solicitação
					texto1 = retorno.getString(3) ; // Descrição do Erro
					texto2 = retorno.getString(4); // Ramal ou Contato
				
					pedInt = new PedidoInterno(3, numPedido, tipodeSolicitacao, texto1, texto2);
				}
				break;
	

			default:
				
				break;
			}
			
			
			
			
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pedInt;
		
		
	}
	
	//Método que retorna um Array de Pedidos com base na autenticação do usuário.
	 public ArrayList<PedidoInterno> listaPedidoPorUsuario(Usuario u) { 
			PreparedStatement prepS = null;
			
			ArrayList<PedidoInterno> vetorDePedidos = new ArrayList<PedidoInterno>();
			
			int numPedido = 0;
			String tipodeSolicitacao = "";
			String texto1 = "" ;
			String texto2 = "";
			String texto3 = "";
			String texto4 = "";
			
			ResultSet retorno;
			String sql;
			
			ConexaoSQL.abrirConexaoMySQL();
			con = ConexaoSQL.getConnection();
			
			// TIPO DE SOLICITACAO 1 | Criação de Unidade
			sql = "Select * from CriacaoUnidades where IdUsuarioCriador = ?"; 
			
			try {
				prepS =  con.prepareStatement(sql);
				prepS.setInt(1, u.getId()) ; // passando o int de ID
				
				retorno = prepS.executeQuery();
				
				while (retorno.next()) {
					numPedido = retorno.getInt(1); // ID do Pedido
					texto1 = retorno.getString(2) ; // Nome da Unidade
					texto2 = retorno.getString(3); // Sigla da Unidade
					texto3 = retorno.getString(4); // Email da Unidade
					texto4 = retorno.getString(5); // Membros da Unidade
					
					PedidoInterno novoPedido = new PedidoInterno(1, numPedido, texto1, texto2, texto3, texto4);
					vetorDePedidos.add(novoPedido);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				ConexaoSQL.fecharConexao();
				System.out.println("ERRO AO FINAL DA CRIAÇÃO DE UNIDADES");
			}
				
			
			// TIPO DE SOLICITACAO 2 | Liberação de Acessos
			sql = "Select * from LiberacaoAcessos where IdUsuarioCriador = ?"; 
			
			try {
				prepS =  con.prepareStatement(sql);
				prepS.setInt(1, u.getId()) ; // passando o int de ID
				
				retorno = prepS.executeQuery();
				
				while (retorno.next()) {
					numPedido = retorno.getInt(1); // ID do pedido
					tipodeSolicitacao = retorno.getString(2); //Tipo de Solicitação de Liberação
					texto1 = retorno.getString(3) ; // Nome da Unidade
					texto2 = retorno.getString(4); // Membros afetados
					
					PedidoInterno novoPedido = new PedidoInterno(2, numPedido, tipodeSolicitacao, texto1, texto2);
					vetorDePedidos.add(novoPedido);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				ConexaoSQL.fecharConexao();
				System.out.println("ERRO AO FINAL DA LIBERACAO DE ACESSOS");
			}
			

			// TIPO DE SOLICITACAO 3 | Manutencao de Sistemas
			sql = "Select * from ManutencaoSistemas where IdUsuarioCriador = ?"; 
			
			try {
				prepS =  con.prepareStatement(sql);
				prepS.setInt(1, u.getId()) ; // passando o int de ID
				
				retorno = prepS.executeQuery();
				
				while (retorno.next()) {
					numPedido = retorno.getInt(1); // ID do Pedido
					tipodeSolicitacao = retorno.getString(2); //Tipo de Solicitação
					texto1 = retorno.getString(3) ; // Descrição do Erro
					texto2 = retorno.getString(4); // Ramal ou Contato
					
					PedidoInterno novoPedido = new PedidoInterno(3, numPedido, tipodeSolicitacao, texto1, texto2);
					vetorDePedidos.add(novoPedido);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				ConexaoSQL.fecharConexao();
				System.out.println("ERRO AO FINAL DA MANUTENÇÃO DE SISTEMAS");
			}
			
			ConexaoSQL.fecharConexao();
			return vetorDePedidos;
		}
	
	 //Método que apaga determinado pedido do Banco de Dados
	 public boolean apagaPedido(PedidoInterno p) {
		 	
		 PreparedStatement prepS = null;
		 int numPedido = 0;
		
		 ResultSet retorno;
		 int retorn;
		 String sql, sql0, sql1, sql2, sql3;
		 int aux = 0;
			
		 ConexaoSQL.abrirConexaoMySQL();
		 con = ConexaoSQL.getConnection();
			
		 sql0 = "select * from PedidosInternos where SolicitacaoID = ?;";
		 sql1 = "select * from CriacaoUnidades where SolicitacaoID = ?"; 
		 sql2 = "Select * from LiberacaoAcessos where SolicitacaoID = ?"; 
		 sql3 = "Select * from ManutencaoSistemas where SolicitacaoID = ?"; 
			
		 try {
			 prepS =  con.prepareStatement(sql0);
			 prepS.setInt(1, p.getPedidoID()) ; // passando o int de PesquisaID
			 retorno = prepS.executeQuery();
			 
			 while(retorno.next()) {
				 numPedido = retorno.getInt(1);
			 }
			 prepS.close();
				
			 if(numPedido > 0) {
				 prepS =  con.prepareStatement(sql1);
				 prepS.setInt(1, p.getPedidoID()) ; // passando o int de PesquisaID
				 retorno = prepS.executeQuery();
					
				 while(retorno.next()) {
					 numPedido = retorno.getInt(1);
						aux = 1;
					}
					prepS.close();
					
					prepS =  con.prepareStatement(sql2);
					prepS.setInt(1, p.getPedidoID()) ; // passando o int de PesquisaID
					retorno = prepS.executeQuery();
					
					while(retorno.next()) {
						numPedido = retorno.getInt(1);
						aux = 2;
					}
					prepS.close();
					
					prepS =  con.prepareStatement(sql3);
					prepS.setInt(1, p.getPedidoID()) ; // passando o int de PesquisaID
					retorno = prepS.executeQuery();
					
					while(retorno.next()) {
						numPedido = retorno.getInt(1);
						aux = 3;
					}
					prepS.close();
					
					
					
				}else {
					aux = 0;
				}

			
				
				switch (aux) {
				case 1:
					sql = "Delete from CriacaoUnidades where SolicitacaoID like ?";
					prepS =  con.prepareStatement(sql);
					prepS.setInt(1, p.getPedidoID()) ; // passando o int de PesquisaID
					retorn = prepS.executeUpdate();
		
					ConexaoSQL.fecharConexao();
						
					return true;
			
				case 2:
					sql = "Delete from LiberacaoAcessos where SolicitacaoID like ?";
					prepS =  con.prepareStatement(sql);
					prepS.setInt(1, p.getPedidoID()) ; // passando o int de PesquisaID
					retorn = prepS.executeUpdate();
		
					ConexaoSQL.fecharConexao();
						
					return true;
				case 3:
					sql = "Delete from ManutencaoSistemas where SolicitacaoID like ?";
					prepS =  con.prepareStatement(sql);
					prepS.setInt(1, p.getPedidoID()) ; // passando o int de PesquisaID
					retorn = prepS.executeUpdate();
		
					ConexaoSQL.fecharConexao();
						
					return true;
				default:
					
					return false;
				}
				
				
				
				
				
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return false;
	 }
}
