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
	
	public void salvarCliente(Cliente cliente) throws SQLException {
		ClienteDAO.getInstance().adicionar(cliente);
	}
	
}
