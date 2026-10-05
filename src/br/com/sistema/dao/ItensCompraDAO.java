package br.com.sistema.dao;

import br.com.sistema.dao.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/*
 * @author erick
 */
public class ItensCompraDAO {

  
    public int cadastrar(int quantidade, double valorUnit, int produtoId, int compraId) {
        String sql = "INSERT INTO tb_itemCompra (quantidade, valor_unit, produto_id, compra_id) VALUES (?, ?, ?, ?) RETURNING id";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, quantidade);
            stmt.setDouble(2, valorUnit);
            stmt.setInt(3, produtoId);
            stmt.setInt(4, compraId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id");
                }
                throw new RuntimeException("Não foi possível recuperar o ID do item cadastrado.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao cadastrar item de compra.", e);
        }
    }


    public List<Object[]> listarPorCompra(int compraId) {
        String sql = "SELECT * FROM tb_itemCompra WHERE compra_id = ? ORDER BY id";
        List<Object[]> itens = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, compraId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Object[] item = new Object[5];
                    item[0] = rs.getInt("id");
                    item[1] = rs.getInt("quantidade");
                    item[2] = rs.getDouble("valor_unit");
                    item[3] = rs.getInt("produto_id");
                    item[4] = rs.getInt("compra_id");
                    itens.add(item);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar itens da compra.", e);
        }
        return itens;
    }
    public Object[] buscarPorCompraEProduto(int compraId, int produtoId) {
        String sql = "SELECT * FROM tb_itemCompra WHERE compra_id = ? AND produto_id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, compraId);
            stmt.setInt(2, produtoId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Object[] item = new Object[5];
                    item[0] = rs.getInt("id");
                    item[1] = rs.getInt("quantidade");
                    item[2] = rs.getDouble("valor_unit");
                    item[3] = rs.getInt("produto_id");
                    item[4] = rs.getInt("compra_id");
                    return item;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar item de compra.", e);
        }
        return null;
    }

    public void somarQuantidade(int idItem, int quantidadeAdicional) {
        String sql = "UPDATE tb_itemCompra SET quantidade = quantidade + ? WHERE id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, quantidadeAdicional);
            stmt.setInt(2, idItem);
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao somar quantidade do item.", e);
        }
    }

    public void excluir(int id) {
        String sql = "DELETE FROM tb_itemCompra WHERE id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir item de compra.", e);
        }
    }
    public void excluirPorCompra(int compraId) {
        String sql = "DELETE FROM tb_itemCompra WHERE compra_id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, compraId);
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir itens da compra.", e);
        }
    }
}