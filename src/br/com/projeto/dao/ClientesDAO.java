/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projeto.dao;

import br.com.projeto.jdbc.ConnectionFactory;
import br.com.projeto.model.Clientes;
import br.com.projeto.model.Utilitarios;
import br.com.projeto.model.WebServiceCep;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class ClientesDAO {

    private Connection con;

    public ClientesDAO() {
        this.con = new ConnectionFactory().getConnection();
    }

    //Metodo cadastrarCliente
    public void cadastrarCliente(Clientes obj) {
        try {

            //1 passo  - criar o comando sql
            String sql = "insert into tb_clientes (nome,rg,cpf,email,telefone,celular,cep,endereco,numero,complemento,bairro,cidade,estado) "
                    + " values (?,?,?,?,?,?,?,?,?,?,?,?,?)";

            //2 passo - conectar o banco de dados e organizar o comando sql
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, obj.getNome());
            stmt.setString(2, Utilitarios.retornaNuloSeVazio(obj.getRg()));
            stmt.setString(3, Utilitarios.retornaNuloSeVazio(obj.getCpf()));
            stmt.setString(4, obj.getEmail());
            stmt.setString(5, Utilitarios.retornaNuloSeVazio(obj.getTelefone()));
            stmt.setString(6, Utilitarios.retornaNuloSeVazio(obj.getCelular()));
            stmt.setString(7, Utilitarios.retornaNuloSeVazio(obj.getCep()));
            stmt.setString(8, obj.getEndereco());
            
            if(obj.getNumero() != null) {
            	stmt.setInt(9, obj.getNumero());
            } else {
            	stmt.setNull(9, Types.INTEGER);
            }
            
            stmt.setString(10, obj.getComplemento());
            stmt.setString(11, obj.getBairro());
            stmt.setString(12, obj.getCidade());
            stmt.setString(13, obj.getUf());

            //3 passo - executar o comando sql
            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Cadastrado com Sucesso!");

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro: " + erro);

        }

    }

    //Metodo AlterarCliente
    public void alterarCliente(Clientes obj) {
        try {

            //1 passo  - criar o comando sql
            String sql = "update tb_clientes set  nome=?, rg=?, cpf=?, email=?, telefone=?, celular=?, cep=?, "
                    + "endereco=?, numero=?,complemento=?,bairro=?,cidade=?, estado=?  where id =?";

            //2 passo - conectar o banco de dados e organizar o comando sql
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, obj.getNome());
            stmt.setString(2, Utilitarios.retornaNuloSeVazio(obj.getRg()));
            stmt.setString(3, Utilitarios.retornaNuloSeVazio(obj.getCpf()));
            stmt.setString(4, obj.getEmail());
            stmt.setString(5, Utilitarios.retornaNuloSeVazio(obj.getTelefone()));
            stmt.setString(6, Utilitarios.retornaNuloSeVazio(obj.getCelular()));
            stmt.setString(7, Utilitarios.retornaNuloSeVazio(obj.getCep()));
            stmt.setString(8, obj.getEndereco());

            if(obj.getNumero() != null) {
            	stmt.setInt(9, obj.getNumero());
            } else {
            	stmt.setNull(9, Types.INTEGER);
            }
                        
            stmt.setString(10, obj.getComplemento());
            stmt.setString(11, obj.getBairro());
            stmt.setString(12, obj.getCidade());
            stmt.setString(13, obj.getUf());

            stmt.setInt(14, obj.getId());

            //3 passo - executar o comando sql
            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Alterado com Sucesso!");

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro: " + erro);

        }
    }

    //Metodo ExcluirCliente
    public void excluirCliente(Clientes obj) {
        try {

            //1 passo  - criar o comando sql
            String sql = "delete from tb_clientes where id = ?";

            //2 passo - conectar o banco de dados e organizar o comando sql
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, obj.getId());

            //3 passo - executar o comando sql
            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Excluido com Sucesso!");

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro: " + erro);

        }

    }

    //Metodo Listar Todos Clientes
    public List<Clientes> listarClientes() {
        try {
            List<Clientes> lista = new ArrayList<>();

            String sql = "select "
            				+ "c.id as id, "
            				+ "c.nome as nome, "
            				+ "c.cpf as cpf, "
            				+ "exists(select "
            							+ "v.id "
            					   + "from "
            					   		+ "tb_vendas as v "
            					   + "where v.cliente_id = c.id "
            					   		+ "and v.pendencia_venda is not null "
            					   		+ "and v.pendencia_venda > 0"
            				+ ") as inadimplente "
            		   + "from tb_clientes as c";
            
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Clientes obj = new Clientes();

                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setCpf(rs.getString("cpf"));
                obj.setInadimplente(rs.getBoolean("inadimplente"));

                lista.add(obj);
            }

            return lista;

        } catch (SQLException erro) {

            JOptionPane.showMessageDialog(null, "Erro :" + erro);
            return null;
        }

    }

    //metodo consultaCliente por Nome
    public Clientes consultaPorNome(String nome) {
        try {
            //1 passo - criar o sql , organizar e executar.
            String sql = "select * from tb_clientes where nome = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nome);

            ResultSet rs = stmt.executeQuery();
            Clientes obj = new Clientes();

            if (rs.next()) {

                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setRg(rs.getString("rg"));
                obj.setCpf(rs.getString("cpf"));
                obj.setEmail(rs.getString("email"));
                obj.setTelefone(rs.getString("telefone"));
                obj.setCelular(rs.getString("celular"));
                obj.setCep(rs.getString("cep"));
                obj.setEndereco(rs.getString("endereco"));
                obj.setNumero(rs.getInt("numero"));
                obj.setComplemento(rs.getString("complemento"));
                obj.setBairro(rs.getString("bairro"));
                obj.setCidade(rs.getString("cidade"));
                obj.setUf(rs.getString("estado"));
            }

            return obj;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Cliente não encontrado!");
            return null;
        }
    }

  //metodo consultaCliente por Nome
    public Clientes consultaPorId(Integer id) {
        try {
            String sql = "select * from tb_clientes where id = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();
            Clientes obj = new Clientes();

            if (rs.next()) {
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setRg(rs.getString("rg"));
                obj.setCpf(rs.getString("cpf"));
                obj.setEmail(rs.getString("email"));
                obj.setTelefone(rs.getString("telefone"));
                obj.setCelular(rs.getString("celular"));
                obj.setCep(rs.getString("cep"));
                obj.setEndereco(rs.getString("endereco"));
                obj.setNumero(rs.getInt("numero"));
                obj.setComplemento(rs.getString("complemento"));
                obj.setBairro(rs.getString("bairro"));
                obj.setCidade(rs.getString("cidade"));
                obj.setUf(rs.getString("estado"));
            }

            return obj;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Cliente não encontrado!");
            return null;
        }
    }
    
    
    //metodo busca Cliente  por Cpf
    public Clientes buscaporcpf(String cpf) {
        try {
            //1 passo - criar o sql , organizar e executar.
            String sql = "select * from tb_clientes where cpf = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, cpf);

            ResultSet rs = stmt.executeQuery();
            Clientes obj = new Clientes();

            if (rs.next()) {

                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setRg(rs.getString("rg"));
                obj.setCpf(rs.getString("cpf"));
                obj.setEmail(rs.getString("email"));
                obj.setTelefone(rs.getString("telefone"));
                obj.setCelular(rs.getString("celular"));
                obj.setCep(rs.getString("cep"));
                obj.setEndereco(rs.getString("endereco"));
                obj.setNumero(rs.getInt("numero"));
                obj.setComplemento(rs.getString("complemento"));
                obj.setBairro(rs.getString("bairro"));
                obj.setCidade(rs.getString("cidade"));
                obj.setUf(rs.getString("estado"));
            }

            return obj;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Cliente não encontrado!");
            return null;
        }
    }
    
    //Metodo buscarclientePorNome - retorna uma lista
    public List<Clientes> buscaClientePorNome(String nome, Boolean apenasInadimplentes) {
        try {

            //1 passo criar a lista
            List<Clientes> lista = new ArrayList<>();

            //2 passo - criar o sql , organizar e executar.
            String sql = "select "
    				+ "c.id as id, "
    				+ "c.nome as nome, "
    				+ "c.cpf as cpf, "
    				+ "exists(select "
    							+ "v.id "
    					   + "from "
    					   		+ "tb_vendas as v "
    					   + "where v.cliente_id = c.id "
    					   		+ "and v.pendencia_venda is not null "
    					   		+ "and v.pendencia_venda > 0"
    				+ ") as inadimplente "
    		   + "from tb_clientes as c "
    		   + "where c.nome like ?";
            
            if(apenasInadimplentes) {
            	sql += " and exists(select v.id from tb_vendas as v "
            				+ "where v.cliente_id = c.id "
            				+ "and v.pendencia_venda is not null "
            				+ "and v.pendencia_venda > 0);";
            }
            
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nome);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Clientes obj = new Clientes();

                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setCpf(rs.getString("cpf"));
                obj.setInadimplente(rs.getBoolean("inadimplente"));

                lista.add(obj);
            }

            return lista;

        } catch (SQLException erro) {

            JOptionPane.showMessageDialog(null, "Erro :" + erro);
            return null;
        }
    }

    //Busca Cep
    
    public Clientes buscaCep(String cep) {
       
        WebServiceCep webServiceCep = WebServiceCep.searchCep(cep);
       

        Clientes obj = new Clientes();

        if (webServiceCep.wasSuccessful()) {
            obj.setEndereco(webServiceCep.getLogradouroFull());
            obj.setCidade(webServiceCep.getCidade());
            obj.setBairro(webServiceCep.getBairro());
            obj.setUf(webServiceCep.getUf());
            
            return obj;
        } else {
            JOptionPane.showMessageDialog(null, "Erro numero: " + webServiceCep.getResulCode());
            JOptionPane.showMessageDialog(null, "Descrição do erro: " + webServiceCep.getResultText());
            return null;
        }

    }
    
    public List<String> buscaClientesParaAutocomplete() {
    	try {
            List<String> lista = new ArrayList<>();

            String sql = "select id, nome from tb_clientes limit 20";
            PreparedStatement stmt = con.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                lista.add(rs.getString("nome"));
            }

            return lista;

        } catch (SQLException erro) {
        	erro.printStackTrace();
        	
            JOptionPane.showMessageDialog(null, "Erro :" + erro);
            return null;
        }
    }
}