package br.com.sistema.dao;

import br.com.sistema.dao.ConnectionFactory;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioDAO {

    public void cadastrar(String nome, String cpf, Date dataInclusao) {
        String sql = "INSERT INTO tb_funcionario (nome, cpf, data_inclusao) VALUES (?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nome);
            stmt.setString(2, cpf);
            stmt.setDate(3, dataInclusao);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao cadastrar funcionário.", e);
        }
    }

    public List<Object[]> listarTodos() {
        String sql = "SELECT id, nome, cpf, data_inclusao FROM tb_funcionario ORDER BY id";
        List<Object[]> funcionarios = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                funcionarios.add(new Object[]{
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("cpf"),
                    rs.getDate("data_inclusao")
                });
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar funcionários.", e);
        }

        return funcionarios;
    }

    public void atualizar(int id, String nome, String cpf, Date dataInclusao) {
        String sql = "UPDATE tb_funcionario SET nome = ?, cpf = ?, data_inclusao = ? WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nome);
            stmt.setString(2, cpf);
            stmt.setDate(3, dataInclusao);
            stmt.setInt(4, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar funcionário.", e);
        }
    }

    public void excluir(int id) {
        String sql = "DELETE FROM tb_funcionario WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir funcionário.", e);
        }
    }
}
