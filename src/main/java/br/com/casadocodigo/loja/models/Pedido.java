package br.com.casadocodigo.loja.models;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Pedido implements Serializable {

    private Integer id;
    private BigDecimal valor;
    @DateTimeFormat
    private Calendar data;
    private ArrayList<Produto> produtos = new ArrayList<>();


    public Pedido(Integer id, BigDecimal valor, Calendar data, Produto produto) {
        this.id = id;
        this.valor = valor;
        this.data = data;
        this.produtos.add(produto);
    }

    public Pedido(Integer id, BigDecimal valor, Calendar data, ArrayList<Produto> produtos) {
        this.id = id;
        this.valor = valor;
        this.data = data;
        this.produtos = produtos;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Calendar getData() {
        return data;
    }

    public void setData(Calendar data) {
        this.data = data;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(ArrayList<Produto> produtos) {
        this.produtos = produtos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pedido)) return false;

        Pedido pedido = (Pedido) o;

        return getId() != null ? getId().equals(pedido.getId()) : pedido.getId() == null;
    }

    @Override
    public int hashCode() {
        return getId() != null ? getId().hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", valor=" + valor +
                ", data=" + data +
                ", produtos=" + produtos +
                '}';
    }
}
