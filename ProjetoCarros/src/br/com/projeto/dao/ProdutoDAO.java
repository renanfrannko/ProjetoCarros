package br.com.projeto.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.fabric.xmlrpc.base.Array;

import br.com.projeto.domain.Produto;
import br.com.projeto.factory.ConexaoFactory;
import br.com.projeto.util.JSFUtil;

public class ProdutoDAO {

	public void salvar(Produto p) {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO Produto ");
		sql.append("(nomeProduto,descricaoProduto,valorProduto) ");
		sql.append("VALUES (?,?,?)");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando;
		try {
			comando = conexao.prepareStatement(sql.toString());
			comando.setString(1, p.getNome());
			comando.setString(2, p.getDescricao());
			comando.setDouble(3, p.getPreco());
			comando.executeUpdate();

			JSFUtil.adicionarMensagemSucesso("Produto adicionado com sucesso");

		} catch (SQLException e) {
			JSFUtil.adicionarMensagemErro(e.getMessage());
		} finally {
			try {
				conexao.close();
				System.out.println("Conexão Fechada");
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
	}

	public void excluir(Produto p) {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM Produto ");
		sql.append("WHERE idProduto = ?");

		Connection conexao = ConexaoFactory.conectar();
		PreparedStatement comando;

		try {
			comando = conexao.prepareStatement(sql.toString());
			comando.setLong(1, p.getCodigo());
			comando.executeUpdate();
			JSFUtil.adicionarMensagemSucesso("Produto removido com sucesso");
		} catch (SQLException e) {
			JSFUtil.adicionarMensagemErro(e.getMessage());
		} finally {
			try {
				conexao.close();
				System.out.println("Conexão Fechada");
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}

	}

	public void editar(Produto p) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE Produto ");
		sql.append("SET nomeProduto = ?,descricaoProduto = ?,valorProduto = ? ");
		sql.append("WHERE idProduto = ?");

		Connection conexao = ConexaoFactory.conectar();
		PreparedStatement comando;

		try {
			comando = conexao.prepareStatement(sql.toString());
			comando.setString(1, p.getNome());
			comando.setString(2, p.getDescricao());
			comando.setDouble(3, p.getPreco());
			comando.setLong(4, p.getCodigo());
			comando.executeUpdate();
			JSFUtil.adicionarMensagemSucesso("Produto editado com sucesso");
		} catch (SQLException e) {
			JSFUtil.adicionarMensagemErro(e.getMessage());
		} finally {
			try {
				conexao.close();
				System.out.println("Conexão Fechada");
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
	}

	public Produto buscarPorCodigo(Produto p) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT idProduto,nomeProduto,descricaoProduto,valorProduto ");
		sql.append("FROM Produto ");
		sql.append("WHERE idProduto = ?");

		Connection conexao = ConexaoFactory.conectar();
		PreparedStatement comando;

		try {
			comando = conexao.prepareStatement(sql.toString());
			comando.setLong(1, p.getCodigo());

			ResultSet resultado = comando.executeQuery();

			Produto produto = new Produto();
			produto.setCodigo(resultado.getLong("idProduto"));
			produto.setNome(resultado.getString("nomeProduto"));
			produto.setDescricao(resultado.getString("descricaoProduto"));
			produto.setPreco(resultado.getDouble("valorProduto"));

			return produto;

		} catch (SQLException e) {
		} finally {
			try {
				conexao.close();
				System.out.println("Conexão Fechada");
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}

		return null;
	}

	public ArrayList<Produto> listar() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT idProduto,nomeProduto,descricaoProduto,valorProduto ");
		sql.append("FROM Produto ");
		sql.append("ORDER BY nomeProduto ASC");

		Connection conexao = ConexaoFactory.conectar();
		PreparedStatement comando;

		try {
			comando = conexao.prepareStatement(sql.toString());

			ResultSet resultado = comando.executeQuery();

			ArrayList<Produto> lista = new ArrayList<Produto>();

			while (resultado.next()) {
				Produto produto = new Produto();
				produto.setCodigo(resultado.getLong("idProduto"));
				produto.setNome(resultado.getString("nomeProduto"));
				produto.setDescricao(resultado.getString("descricaoProduto"));
				produto.setPreco(resultado.getDouble("valorProduto"));

				lista.add(produto);
			}

			return lista;

		} catch (SQLException e) {
		} finally {
			try {
				conexao.close();
				System.out.println("Conexão Fechada");
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}

		return null;
	}

	public static void main(String[] args) {
		Produto p = new Produto();
		p.setNome("Teste");
		p.setDescricao("teste desc");
		p.setPreco(5);

	}

}
