package forsale.dao;

import java.sql.SQLException;
import java.util.List;

public interface GenericDAO<T> {
	public T buscarPorId(Long id);
	public List<T> buscarTodos();
	public void adicionar(T t) throws SQLException;
	public void atualizar(T t);
	public void excluir(T t);
}
