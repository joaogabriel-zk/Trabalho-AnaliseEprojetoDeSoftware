package br.com.sistema.model;

import java.sql.Date;

public class Compra {
    private int id;
    private double valorTotal;
    private Date dataCompra;
    private int clienteId;
    private int funcionarioId;

    public Compra() {}

    public Compra(int id, double valorTotal, Date dataCompra, int clienteId, int funcionarioId) {
        this.id = id;
        this.valorTotal = valorTotal;
        this.dataCompra = dataCompra;
        this.clienteId = clienteId;
        this.funcionarioId = funcionarioId;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public double getValorTotal() { return valorTotal; }
    public void setValorTotal(double valorTotal) { this.valorTotal = valorTotal; }

    public Date getDataCompra() { return dataCompra; }
    public void setDataCompra(Date dataCompra) { this.dataCompra = dataCompra; }

    public int getClienteId() { return clienteId; }
    public void setClienteId(int clienteId) { this.clienteId = clienteId; }

    public int getFuncionarioId() { return funcionarioId; }
    public void setFuncionarioId(int funcionarioId) { this.funcionarioId = funcionarioId; }
}
