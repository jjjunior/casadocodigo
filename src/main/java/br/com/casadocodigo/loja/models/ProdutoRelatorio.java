package br.com.casadocodigo.loja.models;

import java.util.Calendar;
import java.util.List;

public class ProdutoRelatorio {

    private Calendar dataGeracao;
    private Integer quantidade;
    List<Produto> produtos;

    public ProdutoRelatorio(Calendar dataGeracao, Integer quantidade, List<Produto> produtos) {
        this.dataGeracao = dataGeracao;
        this.quantidade = quantidade;
        this.produtos = produtos;
    }

    public Calendar getDataGeracao() {
        return dataGeracao;
    }

    public void setDataGeracao(Calendar dataGeracao) {
        this.dataGeracao = dataGeracao;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }
}
