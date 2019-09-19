package forsale.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import forsale.jdbc.connection.ConnectionFactory;
import forsale.model.Cliente;

public class ClienteDAO implements GenericDAO<Cliente> {
	private static final ClienteDAO instance = new ClienteDAO();
	
	private ClienteDAO() {}
	
	public static ClienteDAO getInstance() {
		return instance;
	}
	
	private Cliente extrairClienteDoResultSet(ResultSet rs) throws SQLException {
		Cliente cliente = new Cliente();
        
        cliente.setCliente_id(rs.getLong("cliente_id"));
    	cliente.setNome(rs.getString("nome"));
    	cliente.setTelefone(rs.getString("telefone"));
    	cliente.setRg(rs.getString("rg"));
    	cliente.setCpf(rs.getString("cpf"));
    	cliente.setRua(rs.getString("rua"));
    	cliente.setNumero(rs.getInt("numero"));
    	cliente.setComplemento(rs.getString("complemento"));
    	cliente.setCep(rs.getString("cep"));
    	cliente.setBairro(rs.getString("bairro"));
    	cliente.setCidade(rs.getString("cidade"));
    	cliente.setUf(rs.getString("uf"));
        
        return cliente;
	}
	
	@Override
	public Cliente buscarPorId(Long id) {
		Connection connection = ConnectionFactory.getConnection();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM cliente WHERE cliente_id = " + id);
            
            Cliente cliente = null;
            
            if(rs.next()) {
                cliente = extrairClienteDoResultSet(rs);
            }
            
            return cliente;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
	}

	@Override
	public List<Cliente> buscarTodos() {
		Connection connection = ConnectionFactory.getConnection();
	    try {
	        Statement stmt = connection.createStatement();
	        ResultSet rs = stmt.executeQuery("SELECT * FROM cliente");
	        
	        List<Cliente> listaClientes = new ArrayList<Cliente>();
	        while(rs.next()) {
	            Cliente cliente = extrairClienteDoResultSet(rs);
	            listaClientes.add(cliente);
	        }
	        
	        return listaClientes;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return null;
	    }
	}

	@Override
	public void adicionar(Cliente cliente) throws SQLException {
		Connection connection = ConnectionFactory.getConnection();
	        PreparedStatement ps = connection.prepareStatement("INSERT INTO cliente VALUES " + 
	        		"(NULL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
	        
	        ps.setString(1, cliente.getNome());
	        ps.setString(2, cliente.getTelefone());
	        ps.setString(3, cliente.getRg());
	        ps.setString(4, cliente.getCpf());
	        ps.setString(5, cliente.getRua());
	        ps.setInt(6, cliente.getNumero());
	        ps.setString(7, cliente.getComplemento());
	        ps.setString(8, cliente.getCep());
	        ps.setString(9, cliente.getBairro());
	        ps.setString(10, cliente.getCidade());
	        ps.setString(11, cliente.getUf());
	        
	        ps.executeUpdate();
	}

	@Override
	public void atualizar(Cliente cliente) {
		Connection connection = ConnectionFactory.getConnection();
	    try {
	        PreparedStatement ps = connection.prepareStatement("UPDATE cliente SET " + 
	        		"nome = ?, telefone = ?, rg = ?, cpf = ?, rua = ?, numero = ?, " +
	        		"complemento = ?, cep = ?, bairro = ?, cidade = ?, uf = ? " +
	        		"WHERE cliente_id = ?");
	        
	        ps.setString(1, cliente.getNome());
	        ps.setString(2, cliente.getTelefone());
	        ps.setString(3, cliente.getRg());
	        ps.setString(4, cliente.getCpf());
	        ps.setString(5, cliente.getRua());
	        ps.setInt(6, cliente.getNumero());
	        ps.setString(7, cliente.getComplemento());
	        ps.setString(8, cliente.getCep());
	        ps.setString(9, cliente.getBairro());
	        ps.setString(10, cliente.getCidade());
	        ps.setString(11, cliente.getUf());
	        
	        ps.setLong(12, cliente.getCliente_id());
	        
	        ps.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	@Override
	public void excluir(Cliente cliente) {
	    Connection connection = ConnectionFactory.getConnection();
	    try {
	        Statement stmt = connection.createStatement();
	        stmt.executeUpdate("DELETE FROM cliente WHERE cliente_id = " + cliente.getCliente_id());
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
}
