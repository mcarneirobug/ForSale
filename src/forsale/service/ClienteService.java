package forsale.service;

import java.sql.SQLException;
import java.util.List;

import forsale.dao.ClienteDAO;
import forsale.filter.ClienteFiltro;
import forsale.model.Cliente;

public class ClienteService {
	private static final ClienteService instance = new ClienteService();
	
	private ClienteService() {}
	
	public static ClienteService getInstance() {
		return instance;
	}
	
	public Cliente buscarPorId(Long id) {
		return ClienteDAO.getInstance().buscarPorId(id);
	}
	
	public void adicionar(Cliente cliente) throws Exception {
		ClienteDAO.getInstance().adicionar(cliente);
	}
	
	public List<Cliente> buscarTodos() {
		return ClienteDAO.getInstance().buscarTodos();
	}
	
	public void atualizar(Cliente cliente) throws SQLException {
		ClienteDAO.getInstance().atualizar(cliente);
	}
	
	public List<Cliente> buscarTodosPorFiltro(ClienteFiltro filtro) {
		return ClienteDAO.getInstance().buscarTodosPorFiltro(filtro);
	}
	
	public void excluir(Cliente cliente) {
		ClienteDAO.getInstance().excluir(cliente);
	}
}
