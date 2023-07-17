/*UFCSPA - Universidade Federal de Ciências da Saúde de Porto Alegre
 *Nomes: Alexandre Lemos Silva e Matheus Castilhos
 *Data: 07/07/2023
 *Disciplina: Programação Orientada a Objetos II
 *Curso: Informática Biomédica*/

package controle;

//Importação das classes
import modelo.PedidoInterno;
import modelo.Usuario;
import visao.JanelaPrincipal;

public class App {
	
	//Método main.
	public static void main(String[] args) {
		
		JanelaPrincipal jan = new JanelaPrincipal();
		jan.setResizable(false);
		jan.setLocationRelativeTo(null);
		jan.setVisible(true);
		Usuario us= new Usuario();
		PedidoInterno pd = new PedidoInterno();
		
		UsuarioControle ucon= new UsuarioControle(jan, us);
		PedidoInternoControle pdcon = new PedidoInternoControle(jan, pd, us);
	}

}
