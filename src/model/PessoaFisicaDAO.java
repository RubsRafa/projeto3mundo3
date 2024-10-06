/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import model.util.ConectorBD;
import model.util.SequenceManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rubia
 */
public class PessoaFisicaDAO {
    public PessoaFisica getPessoa(int idPessoa) throws SQLException {
        String sql = "SELECT * FROM dbo.pessoa p INNER JOIN dbo.pessoa_fisica pf ON p.idPessoa=pf.idPessoa WHERE p.idPessoa=?";
        PreparedStatement psPessoa = null;
        ResultSet rs = null;

        try {
            psPessoa = ConectorBD.getPrepared(sql);
            psPessoa.setInt(1, idPessoa);
            rs = psPessoa.executeQuery();

            if (rs.next()) {
                String nome = rs.getString("nome");
                String logradouro = rs.getString("logradouro");
                String cidade = rs.getString("cidade");
                String estado = rs.getString("estado");
                String telefone = rs.getString("telefone");
                String email = rs.getString("email");
                String cpf = rs.getString("cpf");

                return new PessoaFisica(idPessoa, nome, logradouro, cidade, estado, telefone, email, cpf);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao obter pessoa física: " + e.getMessage());
            e.printStackTrace();
            System.out.println(e);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (psPessoa != null) {
                try {
                    psPessoa.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public List<PessoaFisica> getPessoas() throws SQLException {
        String sql = "SELECT * FROM dbo.pessoa p INNER JOIN dbo.pessoa_fisica pf ON p.idPessoa=pf.idPessoa";
        List<PessoaFisica> pessoas = new ArrayList<>();

        try {
            PreparedStatement ps = ConectorBD.getPrepared(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int idPessoa = rs.getInt("idPessoa");
                String nome = rs.getString("nome");
                String logradouro = rs.getString("logradouro");
                String cidade = rs.getString("cidade");
                String estado = rs.getString("estado");
                String telefone = rs.getString("telefone");
                String email = rs.getString("email");
                String cpf = rs.getString("cpf");

                PessoaFisica pf = new PessoaFisica(idPessoa, nome, logradouro, cidade, estado, telefone, email, cpf);
                pessoas.add(pf);
            }

            return pessoas;

        } catch (Exception e) {
            System.out.printf("Erro ao obter lista de  pessoas físicas: ", e);
        }
        return pessoas;
    }

    public int incluir(PessoaFisica pessoa) throws SQLException {
        String sqlPessoa = "INSERT INTO dbo.pessoa (idPessoa, nome, logradouro, cidade, estado, telefone, email) VALUES (?, ?, ?, ?, ?, ?, ?)";
        String sqlPessoaFisica = "INSERT INTO dbo.pessoa_fisica (idPessoa, cpf) VALUES (?, ?)";

        Connection conn = null;
        PreparedStatement psPessoa = null;
        PreparedStatement psPessoaFisica = null;
        Statement stmt = null;

        try {
            conn = ConectorBD.getConnection();
            conn.setAutoCommit(false);

            stmt = conn.createStatement();
            stmt.executeUpdate("SET IDENTITY_INSERT dbo.pessoa ON");

            int idPessoa = SequenceManager.getValue("seq_pessoa_id");

            psPessoa = conn.prepareStatement(sqlPessoa);
            psPessoa.setInt(1, idPessoa);
            psPessoa.setString(2, pessoa.getNome());
            psPessoa.setString(3, pessoa.getLogradouro());
            psPessoa.setString(4, pessoa.getCidade());
            psPessoa.setString(5, pessoa.getEstado());
            psPessoa.setString(6, pessoa.getTelefone());
            psPessoa.setString(7, pessoa.getEmail());
            psPessoa.executeUpdate();

            psPessoaFisica = conn.prepareStatement(sqlPessoaFisica);
            psPessoaFisica.setInt(1, idPessoa);
            psPessoaFisica.setString(2, pessoa.getCpf());
            psPessoaFisica.executeUpdate();

            stmt.executeUpdate("SET IDENTITY_INSERT dbo.pessoa OFF");

            conn.commit();
            return idPessoa;

        } catch (SQLException e) {
            if (conn != null) {
                conn.rollback();
            }
            e.printStackTrace();
        } finally {
            if (stmt != null)
                stmt.close();
            if (psPessoa != null)
                psPessoa.close();
            if (psPessoaFisica != null)
                psPessoaFisica.close();
            if (conn != null)
                conn.close();
        }
        return 0;
    }

    public void alterar(PessoaFisica pessoa) throws SQLException {
        String sqlPessoa = "UPDATE dbo.pessoa SET nome = ?, logradouro = ?, cidade = ?, estado = ?, telefone = ?, email = ? WHERE idPessoa = ?";
        String sqlPessoaFisica = "UPDATE dbo.pessoa_fisica SET cpf = ? WHERE idPessoa = ?";

        Connection conn = null;
        PreparedStatement psPessoa = null;
        PreparedStatement psPessoaFisica = null;

        try {
            conn = ConectorBD.getConnection();
            conn = ConectorBD.getConnection();
            if (conn == null) {
                throw new SQLException("Falha ao obter conexão com o banco de dados.");
            }

            conn.setAutoCommit(false);

            psPessoa = conn.prepareStatement(sqlPessoa);
            psPessoa.setString(1, pessoa.getNome());
            psPessoa.setString(2, pessoa.getLogradouro());
            psPessoa.setString(3, pessoa.getCidade());
            psPessoa.setString(4, pessoa.getEstado());
            psPessoa.setString(5, pessoa.getTelefone());
            psPessoa.setString(6, pessoa.getEmail());
            psPessoa.setInt(7, pessoa.getId());
            psPessoa.executeUpdate();

            psPessoaFisica = conn.prepareStatement(sqlPessoaFisica);
            psPessoaFisica.setString(1, pessoa.getCpf());
            psPessoaFisica.setInt(2, pessoa.getId());
            psPessoaFisica.executeUpdate();

            conn.commit();
            conn.setAutoCommit(true);
        } catch (Exception e) {
            if (conn != null) {
                conn.rollback();
            }
            e.printStackTrace();
        } finally {
            try {
                if (psPessoa != null)
                    psPessoa.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if (psPessoaFisica != null)
                    psPessoaFisica.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                if (conn != null)
                    conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void excluir(int idPessoa) throws SQLException {
        String sqlPessoa = "DELETE FROM dbo.pessoa WHERE idPessoa = ?";
        String sqlPessoaFisica = "DELETE FROM dbo.pessoa_fisica WHERE idPessoa = ?";

        Connection conn = null;
        PreparedStatement psPessoa = null;
        PreparedStatement psPessoaFisica = null;

        try {
            conn = ConectorBD.getConnection();
            conn.setAutoCommit(false);

            psPessoaFisica = conn.prepareStatement(sqlPessoaFisica);
            psPessoaFisica.setInt(1, idPessoa);
            psPessoaFisica.executeUpdate();

            psPessoa = conn.prepareStatement(sqlPessoa);
            psPessoa.setInt(1, idPessoa);
            psPessoa.executeUpdate();

            conn.commit();
        } catch (SQLException e) {
            if (conn != null) {
                conn.rollback();
            }
            e.printStackTrace();
        } finally {
            if (psPessoa != null)
                psPessoa.close();
            if (psPessoaFisica != null)
                psPessoaFisica.close();
            if (conn != null)
                conn.close();
        }
    }
}
