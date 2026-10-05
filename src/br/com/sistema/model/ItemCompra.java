package br.com.sistema.model;

public class ItemCompra {
    private int id;
    private int quantidade;
    private double valorUnit;
    private int produtoId;
    private int compraId;

    public ItemCompra() {}

    public ItemCompra(int id, int quantidade, double valorUnit, int produtoId, int compraId) {
        this.id = id;
        this.quantidade = quantidade;
        this.valorUnit = valorUnit;
        this.produtoId = produtoId;
        this.compraId = compraId;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getQuantidade() { return quantidade; }
    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }

    public double getValorUnit() { return valorUnit; }
    public void setValorUnit(double valorUnit) { this.valorUnit = valorUnit; }

    public int getProdutoId() { return produtoId; }
    public void setProdutoId(int produtoId) { this.produtoId = produtoId; }

    public int getCompraId() { return compraId; }
    public void setCompraId(int compraId) { this.compraId = compraId; }

    public double getSubtotal() {
        return quantidade * valorUnit;
    }
}
