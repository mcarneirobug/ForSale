package forsale.teste;

import java.util.List;

import forsale.dao.ClienteDAO;
import forsale.model.Cliente;

public class ClienteDAOTest {
	public static void main(String[] args) {
		Cliente cliente = new Cliente();
		
		cliente.setNome("Richter Belmont");
		cliente.setTelefone("31999998888");
		cliente.setRg("12345678");
		cliente.setCpf("38646718840");
		
		cliente.setRua("Rua Bolanhos");
		cliente.setNumero(125);
		cliente.setComplemento("casa");
		cliente.setCep("34585558");
		cliente.setBairro("Coqueiros");
		cliente.setCidade("Belo Horizonte");
		cliente.setUf("MG");
		
//		ClienteDAO.getInstance().adicionar(cliente);
//		System.out.println("Salvo com sucesso!");
		
//		cliente = ClienteDAO.getInstance().buscarPorId(1L);
//		System.out.println(cliente.getCliente_id() + " - " + cliente.getNome());
		
//		List<Cliente> lista = ClienteDAO.getInstance().buscarTodos();
//		for(Cliente c : lista) {
//			System.out.println(c.getCliente_id() + " - " + c.getNome());
//		}
		
//		cliente = ClienteDAO.getInstance().buscarPorId(2L);
//		cliente.setNome("Alucard");
//		cliente.setRg("222555888");
//		cliente.setRua("Avenida do Contorno");
//		cliente.setNumero(1955);
//		ClienteDAO.getInstance().atualizar(cliente);
//		System.out.println("Atualizado com sucesso!");
//		
//		List<Cliente> lista = ClienteDAO.getInstance().buscarTodos();
//		for(Cliente c : lista) {
//			System.out.println(c.getCliente_id() + " - " + c.getNome());
//		}
		
		cliente = ClienteDAO.getInstance().buscarPorId(1L);
		ClienteDAO.getInstance().excluir(cliente);
		System.out.println("Excluido com sucesso!");
	}
}
