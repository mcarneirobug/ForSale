/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projeto.dao;

import br.com.projeto.jdbc.ConnectionFactory;
import br.com.projeto.model.Clientes;

import br.com.projeto.model.Fornecedores;
import br.com.projeto.model.Utilitarios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class FornecedoresDAO {

    private Connection con;

    public FornecedoresDAO() {
        this.con = new ConnectionFactory().getConnection();
    }

    //Metodo cadastrarFornecedores
    public void cadastrarFornecedores(Fornecedores obj) {
        try {

            //1 passo  - criar o comando sql
            String sql = "insert into tb_fornecedores (nome,cnpj,email,telefone,celular,cep,endereco,numero,complemento,bairro,cidade,estado) "
                    + " values (?,?,?,?,?,?,?,?,?,?,?,?)";

            //2 passo - conectar o banco de dados e organizar o comando sql
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, obj.getNome());
            stmt.setString(2, Utilitarios.retornaNuloSeVazio(obj.getCnpj()));
            stmt.setString(3, obj.getEmail());
            stmt.setString(4, Utilitarios.retornaNuloSeVazio(obj.getTelefone()));
            stmt.setString(5, Utilitarios.retornaNuloSeVazio(obj.getCelular()));
            stmt.setString(6, Utilitarios.retornaNuloSeVazio(obj.getCep()));
            stmt.setString(7, obj.getEndereco());
            
            if(obj.getNumero() != null) {
            	stmt.setInt(8, obj.getNumero());
            } else {
            	stmt.setNull(8, Types.INTEGER);
            }
            
            stmt.setString(9, obj.getComplemento());
            stmt.setString(10, obj.getBairro());
            stmt.setString(11, obj.getCidade());
            stmt.setString(12, obj.getUf());

            //3 passo - executar o comando sql
            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Cadastrado com Sucesso!");

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro: " + erro);

        }

    }

    //metodo excluir fornecedor
    public void excluirFornecedor(Fornecedores obj) {
        try {
            String sql = "delete from tb_fornecedores where id = ?";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, obj.getId());

            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Excluido com Sucesso!");

        } catch(SQLIntegrityConstraintViolationException e) { 
        	e.printStackTrace();
        	JOptionPane.showMessageDialog(null, "Não é possível excluir esse registro porque ele possui referências em outros cadastros do sistema.");
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro: " + erro +". Contate o administrador do sistema.");
        }

    }

    //metodo alterarFornecedor
    public void alterarFornecedor(Fornecedores obj) {
        try {
            String sql = "update tb_fornecedores set  nome=?, cnpj=?, email=?, telefone=?, celular=?, cep=?, "
                    + "endereco=?, numero=?,complemento=?,bairro=?,cidade=?, estado=?  where id =?";

            PreparedStatement stmt = con.prepareStatement(sql);
        
            stmt.setString(1, obj.getNome());
            stmt.setString(2, Utilitarios.retornaNuloSeVazio(obj.getCnpj()));
            stmt.setString(3, obj.getEmail());
            stmt.setString(4, Utilitarios.retornaNuloSeVazio(obj.getTelefone()));
            stmt.setString(5, Utilitarios.retornaNuloSeVazio(obj.getCelular()));
            stmt.setString(6, Utilitarios.retornaNuloSeVazio(obj.getCep()));
            stmt.setString(7, obj.getEndereco());
            stmt.setInt(8, obj.getNumero());
            stmt.setString(9, obj.getComplemento());
            stmt.setString(10, obj.getBairro());
            stmt.setString(11, obj.getCidade());
            stmt.setString(12, obj.getUf());

            stmt.setInt(13, obj.getId());

            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Alterado com Sucesso!");

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro: " + erro);

        }
    }
    
    //Metodo listarFornecedores
     public List<Fornecedores> listarFornecedores() {
        try {
            List<Fornecedores> lista = new ArrayList<>();

            String sql = "select * from tb_fornecedores";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Fornecedores obj = new Fornecedores();

                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));             
                obj.setCnpj(rs.getString("cnpj"));
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

                lista.add(obj);
            }

            return lista;

        } catch (SQLException erro) {

            JOptionPane.showMessageDialog(null, "Erro :" + erro);
            return null;
        }

    }
     
     public Fornecedores consultarPorId(Integer id) {
         try {
             String sql = "select * from tb_fornecedores where id = ?";
             PreparedStatement stmt = con.prepareStatement(sql);
             stmt.setInt(1, id);
             
             ResultSet rs = stmt.executeQuery();
             rs.next();
             
             Fornecedores obj = new Fornecedores();

             obj.setId(rs.getInt("id"));
             obj.setNome(rs.getString("nome"));             
             obj.setCnpj(rs.getString("cnpj"));
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

             return obj;

         } catch (SQLException erro) {

             JOptionPane.showMessageDialog(null, "Erro :" + erro);
             return null;
         }

     }
    
    //Metodo listarFornecedores por nome
     public List<Fornecedores> listarFornecedoresPorNome(String nome) {
        try {
            List<Fornecedores> lista = new ArrayList<>();

            String sql = "select * from tb_fornecedores where nome like ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Fornecedores obj = new Fornecedores();

                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));             
                obj.setCnpj(rs.getString("cnpj"));
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

                lista.add(obj);
            }

            return lista;

        } catch (SQLException erro) {

            JOptionPane.showMessageDialog(null, "Erro :" + erro);
            return null;
        }

    }
     
     //metodo consultaFornecedoresPornome
      public Fornecedores consultaPorNome(String nome) {
        try {
            String sql = "select * from tb_fornecedores where nome = ?";
            
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nome);

            ResultSet rs = stmt.executeQuery();
            Fornecedores obj = new Fornecedores();

            if (rs.next()) {
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));             
                obj.setCnpj(rs.getString("cnpj"));
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
            JOptionPane.showMessageDialog(null, "Fornecedor nÃ£o encontrado!");
            return null;
        }
    }
}