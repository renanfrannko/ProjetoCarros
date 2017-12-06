package br.com.projeto.bean;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.model.ListDataModel;

import br.com.projeto.dao.ProdutoDAO;
import br.com.projeto.domain.Produto;
import br.com.projeto.util.JSFUtil;

public class ProdutoBean {
	public Produto produto;
	private ListDataModel<Produto> produtos;

	public ListDataModel<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(ListDataModel<Produto> produtos) {
		this.produtos = produtos;
	}

	@PostConstruct
	public void prepararPesquisa() {

		try {
			ProdutoDAO dao = new ProdutoDAO();
			ArrayList<Produto> lista = dao.listar();
			produtos = new ListDataModel<Produto>(lista);

		} catch (Exception e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

	}

	public void prepararNovo() {
		produto = new Produto();

	}

	public void novo() {

		ProdutoDAO dao = new ProdutoDAO();
		dao.salvar(produto);

		ArrayList<Produto> lista = dao.listar();
		produtos = new ListDataModel<Produto>(lista);
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public void prepararExcluir() {
		produto = produtos.getRowData();

	}

	public void excluir() {
		ProdutoDAO dao = new ProdutoDAO();
		dao.excluir(produto);

		ArrayList<Produto> lista = dao.listar();
		produtos = new ListDataModel<Produto>(lista);
	}

	public void prepararEditar() {
		produto = produtos.getRowData();
	}

	public void editar() {
		ProdutoDAO dao = new ProdutoDAO();
		dao.editar(produto);

		ArrayList<Produto> lista = dao.listar();
		produtos = new ListDataModel<Produto>(lista);
	}
}
