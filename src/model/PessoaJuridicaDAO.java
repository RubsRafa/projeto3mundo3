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
public class PessoaJuridicaDAO {
    public PessoaJuridica getPessoa(int idPessoa) throws SQLException {
        String sql = "SELECT * FROM dbo.pessoa p INNER JOIN dbo.pessoa_juridica pj ON p.idPessoa=pj.idPessoa WHERE p.idPessoa=?";
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
                String cnpj = rs.getString("cnpj");

                return new PessoaJuridica(idPessoa, nome, logradouro, cidade, estado, telefone, email, cnpj);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao obter pessoa jurídica: " + e.getMessage());
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

    public List<PessoaJuridica> getPessoas() throws SQLException {
        String sql = "SELECT * FROM dbo.pessoa p INNER JOIN dbo.pessoa_juridica pj ON p.idPessoa=pj.idPessoa";
        List<PessoaJuridica> pessoas = new ArrayList<>();

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
                String cnpj = rs.getString("cnpj");

                PessoaJuridica pj = new PessoaJuridica(idPessoa, nome, logradouro, cidade, estado, telefone, email,
                        cnpj);
                pessoas.add(pj);
            }

            return pessoas;

        } catch (Exception e) {
            System.out.printf("Erro ao obter lista de  pessoas jurídicas: ", e);
        }
        return pessoas;
    }

    public int incluir(PessoaJuridica pessoa) throws SQLException {
        String sqlPessoa = "INSERT INTO dbo.pessoa (idPessoa, nome, logradouro, cidade, estado, telefone, email) VALUES (?, ?, ?, ?, ?, ?, ?)";
        String sqlPessoaJuridica = "INSERT INTO dbo.pessoa_juridica (idPessoa, cnpj) VALUES (?, ?)";

        Connection conn = null;
        PreparedStatement psPessoa = null;
        PreparedStatement psPessoaJuridica = null;
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

            psPessoaJuridica = conn.prepareStatement(sqlPessoaJuridica);
            psPessoaJuridica.setInt(1, idPessoa);
            psPessoaJuridica.setString(2, pessoa.getCnpj());
            psPessoaJuridica.executeUpdate();

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
            if (psPessoaJuridica != null)
                psPessoaJuridica.close();
            if (conn != null)
                conn.close();
        }
        return 0;
    }

    public void alterar(PessoaJuridica pessoa) throws SQLException {
        String sqlPessoa = "UPDATE dbo.pessoa SET nome = ?, logradouro = ?, cidade = ?, estado = ?, telefone = ?, email = ? WHERE idPessoa = ?";
        String sqlPessoaJuridica = "UPDATE dbo.pessoa_juridica SET cnpj = ? WHERE idPessoa = ?";

        Connection conn = null;
        PreparedStatement psPessoa = null;
        PreparedStatement psPessoaJuridica = null;

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

            psPessoaJuridica = conn.prepareStatement(sqlPessoaJuridica);
            psPessoaJuridica.setString(1, pessoa.getCnpj());
            psPessoaJuridica.setInt(2, pessoa.getId());
            psPessoaJuridica.executeUpdate();

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
                if (psPessoaJuridica != null)
                    psPessoaJuridica.close();
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
        String sqlPessoaJuridica = "DELETE FROM dbo.pessoa_juridica WHERE idPessoa = ?";

        Connection conn = null;
        PreparedStatement psPessoa = null;
        PreparedStatement psPessoaJuridica = null;

        try {
            conn = ConectorBD.getConnection();
            conn.setAutoCommit(false);

            psPessoaJuridica = conn.prepareStatement(sqlPessoaJuridica);
            psPessoaJuridica.setInt(1, idPessoa);
            psPessoaJuridica.executeUpdate();

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
            if (psPessoaJuridica != null)
                psPessoaJuridica.close();
            if (conn != null)
                conn.close();
        }
    }
}
