package br.com.sistema.dao;

import br.com.sistema.dao.ConnectionFactory;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


 // @author erick
 
public class ComprasDAO {

    public int cadastrar(double valorTotal, Date dataCompra, int clienteId, int funcionarioId) {
        String sql = "INSERT INTO tb_compra (valor_total, data_compra, cliente_id, funcionario_id) VALUES (?, ?, ?, ?) RETURNING id";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, valorTotal);
            stmt.setDate(2, dataCompra);
            stmt.setInt(3, clienteId);
            stmt.setInt(4, funcionarioId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id");
                }
                throw new RuntimeException("Não foi possível recuperar o ID da compra cadastrada.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao cadastrar compra.", e);
        }
    }

    public List<Object[]> listarTodas() {
        String sql = "SELECT * FROM tb_compra ORDER BY id";
        List<Object[]> compras = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Object[] compra = new Object[5];
                compra[0] = rs.getInt("id");
                compra[1] = rs.getDouble("valor_total");
                compra[2] = rs.getDate("data_compra");
                compra[3] = rs.getInt("cliente_id");
                compra[4] = rs.getInt("funcionario_id");
                compras.add(compra);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar compras.", e);
        }
        return compras;
    }

    public List<Object[]> listarPorCliente(int clienteId) {
        String sql = "SELECT * FROM tb_compra WHERE cliente_id = ? ORDER BY id";
        List<Object[]> compras = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, clienteId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Object[] compra = new Object[5];
                    compra[0] = rs.getInt("id");
                    compra[1] = rs.getDouble("valor_total");
                    compra[2] = rs.getDate("data_compra");
                    compra[3] = rs.getInt("cliente_id");
                    compra[4] = rs.getInt("funcionario_id");
                    compras.add(compra);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar compras do cliente.", e);
        }
        return compras;
    }

    public Object[] buscarPorId(int id) {
        String sql = "SELECT * FROM tb_compra WHERE id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Object[] compra = new Object[5];
                    compra[0] = rs.getInt("id");
                    compra[1] = rs.getDouble("valor_total");
                    compra[2] = rs.getDate("data_compra");
                    compra[3] = rs.getInt("cliente_id");
                    compra[4] = rs.getInt("funcionario_id");
                    return compra;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar compra.", e);
        }
        return null;
    }

    public void atualizarValorTotal(int id, double valorTotal) {
        String sql = "UPDATE tb_compra SET valor_total = ? WHERE id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, valorTotal);
            stmt.setInt(2, id);
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar valor da compra.", e);
        }
    }

    public void excluir(int id) {
        String sql = "DELETE FROM tb_compra WHERE id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir compra.", e);
        }
    }
}
