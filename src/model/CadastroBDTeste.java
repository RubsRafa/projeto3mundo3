/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.SQLException;
import java.util.List;
import com.github.javafaker.Faker;

/**
 *
 * @author rubia
 */

public class CadastroBDTeste {

    public static void main(String[] args) {
        try {
            PessoaFisicaDAO pessoaFisicaDAO = new PessoaFisicaDAO();

            Faker faker = new Faker();

            String nomePF = faker.name().firstName();
            String logradouroPF = faker.address().streetName();
            String cidadePF = faker.address().city();
            String estadoPF = faker.address().state();
            String telefonePF = faker.phoneNumber().cellPhone();
            String emailPF = faker.internet().emailAddress();
            String cpf = faker.number().digits(11);

            PessoaFisica pessoaFisica = new PessoaFisica(
                    0,
                    nomePF,
                    logradouroPF,
                    cidadePF,
                    estadoPF,
                    telefonePF,
                    emailPF,
                    cpf);

            int idPessoaPF = pessoaFisicaDAO.incluir(pessoaFisica);
            pessoaFisica.setId(idPessoaPF);

            System.out.println("Pessoa física inserida no banco.");
            pessoaFisica.exibir();

            String nomePFAlterado = faker.name().firstName();
            String cpfAlterado = faker.number().digits(11);

            pessoaFisica.setNome(nomePFAlterado);
            pessoaFisica.setCpf(cpfAlterado);

            pessoaFisicaDAO.alterar(pessoaFisica);
            System.out.println("\nDados da pessoa física alterados para: " +
                    pessoaFisica.getNome() + ", CPF: "
                    + pessoaFisica.getCpf());

            System.out.printf("\nPessoa física alterada.");
            pessoaFisica.exibir();

            List<PessoaFisica> pessoasFisicas = pessoaFisicaDAO.getPessoas();
            System.out.println("\nListando todas as pessoas físicas:");
            if (pessoasFisicas.isEmpty()) {
                System.out.print("Nenhuma pessoa física encontrada");
            } else {
                for (PessoaFisica pf : pessoasFisicas) {
                    System.out.println("ID: " + pf.getId() + ", Nome: " + pf.getNome() + ", CPF:" + pf.getCpf());
                }
            }

            pessoaFisicaDAO.excluir(pessoaFisica.getId());
            System.out.println("\nPessoa física excluída do banco: " +
                    pessoaFisica.getNome());
            System.out.println("\nFinalização dos testes com pessoa física");

            PessoaJuridicaDAO pessoaJuridicaDAO = new PessoaJuridicaDAO();

            String nomePJ = faker.company().name();
            String logradouroPJ = faker.address().streetName();
            String cidadePJ = faker.address().city();
            String estadoPJ = faker.address().state();
            String telefonePJ = faker.phoneNumber().cellPhone();
            String emailPJ = faker.internet().emailAddress();
            String cnpj = faker.number().digits(14);

            PessoaJuridica pessoaJuridica = new PessoaJuridica(
                    1,
                    nomePJ,
                    logradouroPJ,
                    cidadePJ,
                    estadoPJ,
                    telefonePJ,
                    emailPJ,
                    cnpj);

            int idPessoaPJ = pessoaJuridicaDAO.incluir(pessoaJuridica);
            pessoaJuridica.setId(idPessoaPJ);

            System.out.println("Pessoa jurídica inserida no banco.");
            pessoaJuridica.exibir();

            String nomePJAlterado = faker.company().name();
            String cnpjAlterado = faker.number().digits(14);

            pessoaJuridica.setNome(nomePFAlterado);
            pessoaJuridica.setCnpj(cnpj);

            pessoaJuridicaDAO.alterar(pessoaJuridica);
            System.out.println("\nDados da pessoa juridica alterados para: " +
                    pessoaJuridica.getNome() + ", CNPJ: "
                    + pessoaJuridica.getCnpj());

            System.out.printf("\nPessoa juridica alterada.");
            pessoaJuridica.exibir();

            List<PessoaJuridica> pessoasJuridicas = pessoaJuridicaDAO.getPessoas();
            System.out.println("\nListando todas as pessoas jurídicas:");
            if (pessoasJuridicas.isEmpty()) {
                System.out.print("Nenhuma pessoa jurídicas encontrada");
            } else {
                for (PessoaJuridica pj : pessoasJuridicas) {
                    System.out.println("ID: " + pj.getId() + ", Nome: " + pj.getNome() + ", CNPJ:" + pj.getCnpj());
                }
            }

            pessoaJuridicaDAO.excluir(pessoaJuridica.getId());
            System.out.println("\nPessoa juridica excluída do banco: " +
                    pessoaJuridica.getNome());
            System.out.println("\nFinalização dos testes com pessoa juridica");

        } catch (SQLException e) {
            System.out.printf("Erro no teste: %s%n", e);
        }
    }
}
