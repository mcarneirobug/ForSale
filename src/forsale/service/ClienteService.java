package forsale.service;

import java.sql.SQLException;

import forsale.dao.ClienteDAO;
import forsale.model.Cliente;

public class ClienteService {
	private static final ClienteService instance = new ClienteService();
	
	private ClienteService() {}
	
	public static ClienteService getInstance() {
		return instance;
	}
	
	public Cliente carregarCliente(Long id) {
		return ClienteDAO.getInstance().buscarPorId(id);
	}
	
	public void salvarCliente(Cliente cliente) throws Exception {
		if(cliente.getNome() == null || cliente.getNome().equals("")
				|| cliente.getCpf() == null || cliente.getCpf().equals("")) {
			throw new Exception("Dados inválidos");
		}
		
		ClienteDAO.getInstance().adicionar(cliente);
	}
	
}
