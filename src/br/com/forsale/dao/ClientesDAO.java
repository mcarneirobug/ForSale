package br.com.forsale.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import br.com.forsale.jdbc.ConnectionFactory;
import br.com.forsale.model.Clientes;

public class ClientesDAO {
	
	private Connection con;
	
	public ClientesDAO() {
		this.con = new ConnectionFactory().getConnection();
	}
	
	public void cadastrarCliente(Clientes obj) {
		
		try {
			
			String sql = "INSERT INTO tb_clientes (nome, rg, cpf, email, telefone, celular, cep, endereco, numero, complemento, bairro, cidade, estado)"
					   + " values(?,?,?,?,?,?,?,?,?,?,?,?,?)"; 
			
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, obj.getNome());
			stmt.setString(2, obj.getRg());
			stmt.setString(3, obj.getCpf());
			stmt.setString(4, obj.getEmail());
			stmt.setString(5, obj.getTelefone());
			stmt.setString(6, obj.getCelular());
			stmt.setString(7, obj.getCep());
			stmt.setString(8, obj.getEndereco());
			stmt.setString(9, obj.getComplemento());
			stmt.setString(10, obj.getBairro());
			stmt.setString(11, obj.getCidade());
			stmt.setString(12, obj.getUf());
			
			stmt.execute();
			stmt.close();
			
			JOptionPane.showMessageDialog(null, "Cadastrado com sucesso!");
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro: " + e);
		}
		
	}
	
	public void alterarCliente() {
		
	}
	
	public void excluirCliente() {
		
	}
	
}