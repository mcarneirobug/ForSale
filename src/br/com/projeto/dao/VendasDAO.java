/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projeto.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.projeto.jdbc.ConnectionFactory;
import br.com.projeto.model.Clientes;
import br.com.projeto.model.Vendas;

public class VendasDAO {

	private Connection con;

	public VendasDAO() {
		this.con = new ConnectionFactory().getConnection();
	}

	// Cadastrar Venda
	public void cadastrarVenda(Vendas obj) {
		try {

			String sql = "insert into tb_vendas (cliente_id, data_venda, total_venda, pendencia_venda, observacoes)"
					+ " values (?,?,?,?,?)";

			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, obj.getCliente().getId());
			stmt.setString(2, obj.getData_venda());
			stmt.setDouble(3, obj.getTotal_venda());
			stmt.setDouble(4, obj.getPendencia_venda());
			stmt.setString(5, obj.getObs());

			stmt.execute();
			stmt.close();

		} catch (Exception erro) {

			JOptionPane.showMessageDialog(null, "Erro : " + erro);

		}

	}

	// Retorna a ultima venda
	public int retornaUltimaVenda() {
		try {
			int idvenda = 0;

			String sql = "select max(id) id from tb_vendas";

			PreparedStatement ps = con.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				Vendas p = new Vendas();

				p.setId(rs.getInt("id"));
				idvenda = p.getId();
			}

			return idvenda;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	// Metodo que filtra Vendas por Datas
	public List<Vendas> listarVendasPorPeriodo(LocalDate data_inicio, LocalDate data_fim, Boolean apenasComPendencias, String nomeCliente) {
		try {
			List<Vendas> lista = new ArrayList<>();
			
			String sql = "select v.id , "
							+ "date_format(v.data_venda,'%d/%m/%Y') as data_formatada, "
							+ "c.nome, "
							+ "v.total_venda, "
							+ "v.pendencia_venda "
						+ "from tb_vendas as v "
						+ "inner join tb_clientes as c on v.cliente_id = c.id "
						+ "where v.data_venda BETWEEN ? AND ?";

			if(apenasComPendencias) {
				sql += " and v.pendencia_venda is not null and v.pendencia_venda > 0";
			}
			
			boolean nomeClienteValido = false;
			Integer idCliente = null;
			
			if(nomeCliente != null && !nomeCliente.equals("")) {
				Clientes cliente = new ClientesDAO().consultaPorNome(nomeCliente);
				idCliente = cliente != null ? cliente.getId() : null;
				
				if(idCliente != null) {
					nomeClienteValido = true;
					sql += " and c.id = ?";
				}
			}
			
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, data_inicio.toString());
			stmt.setString(2, data_fim.toString());
			
			if(nomeClienteValido) {
				stmt.setInt(3, idCliente);
			}
			
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Vendas obj = new Vendas();
				Clientes c = new Clientes();

				obj.setId(rs.getInt("v.id"));
				obj.setData_venda(rs.getString("data_formatada"));
				obj.setTotal_venda(rs.getDouble("v.total_venda"));
				obj.setPendencia_venda(rs.getDouble("v.pendencia_venda"));
				
				c.setNome(rs.getString("c.nome"));
				obj.setCliente(c);

				lista.add(obj);
			}

			return lista;

		} catch (SQLException erro) {
			JOptionPane.showMessageDialog(null, "Erro : " + erro);
			return null;
		}

	}

	public Vendas buscarVendaPorId(Integer id) {
		try {
			String sql = "select v.id as id, "
							+ "date_format(data_venda,'%d/%m/%Y') as data_formatada, "
							+ "c.nome as nome, "
							+ "v.total_venda as total_venda, "
							+ "v.pendencia_venda as pendencia_venda, "
							+ "v.observacoes as observacoes "
					   + "from tb_vendas as v "
					   + "inner join tb_clientes as c on v.cliente_id = c.id "
					   + "where v.id = ?";
			
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);

			ResultSet rs = stmt.executeQuery();

			rs.next();
			Vendas obj = new Vendas();
			Clientes c = new Clientes();

			obj.setId(rs.getInt("id"));
			obj.setData_venda(rs.getString("data_formatada"));
			obj.setTotal_venda(rs.getDouble("total_venda"));
			obj.setPendencia_venda(rs.getDouble("pendencia_venda"));
			obj.setObs(rs.getString("observacoes"));
				
			c.setNome(rs.getString("nome"));
			obj.setCliente(c);

			return obj;

		} catch (SQLException erro) {
			JOptionPane.showMessageDialog(null, "Erro : " + erro);
			return null;
		}

	}
	
	// Metodo que calcula total da venda por data
	public double retornaTotalVendaPorData(LocalDate data_venda) {
		try {
			double totalvenda = 0;

			String sql = "select sum(coalesce(total_venda, 0) - coalesce(pendencia_venda, 0)) as total from tb_vendas where data_venda = ?";

			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, data_venda.toString());

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				totalvenda = rs.getDouble("total");
			}

			return totalvenda;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}