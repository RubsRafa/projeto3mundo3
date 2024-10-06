/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package cadastrobd;

import java.util.List;
import java.util.Scanner;

import model.PessoaFisica;
import model.PessoaFisicaDAO;
import model.PessoaJuridica;
import model.PessoaJuridicaDAO;

/**
 *
 * @author rubia
 */
public class CadastroBD {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {
            System.out.println("===================");
            System.out.println("1 - Incluir Pessoa");
            System.out.println("2 - Alterar Pessoa");
            System.out.println("3 - Excluir Pessoa");
            System.out.println("4 - Buscar pelo ID");
            System.out.println("5 - Exibir Todos");
            System.out.println("0 - Finalizar Programa");
            System.out.println("===================");
            System.out.println("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.println("Escolha o tipo: 1 - Física | 2 - Jurídica");
                    int tipoInclusao = scanner.nextInt();
                    scanner.nextLine();

                    if (tipoInclusao == 1) {
                        incluirPessoaFisica(scanner);
                    } else if (tipoInclusao == 2) {
                        incluirPessoaJuridica(scanner);
                    } else {
                        System.out.println("Tipo inválido.");
                    }
                    break;

                case 2:
                    System.out.println("Escolha o tipo: 1 - Física | 2 - Jurídica");
                    int tipoAlteracao = scanner.nextInt();
                    scanner.nextLine();

                    if (tipoAlteracao == 1) {
                        alterarPessoaFisica(scanner);
                    } else if (tipoAlteracao == 2) {
                        alterarPessoaJuridica(scanner);
                    } else {
                        System.out.println("Tipo inválido.");
                    }
                    break;

                case 3:
                    System.out.println("Escolha o tipo: 1 - Física | 2 - Jurídica");
                    int tipoExcluir = scanner.nextInt();
                    scanner.nextLine();

                    if (tipoExcluir == 1) {
                        excluirPessoaFisica(scanner);
                    } else if (tipoExcluir == 2) {
                        excluirPessoaJuridica(scanner);
                    } else {
                        System.out.println("Tipo inválido.");
                    }
                    break;

                case 4:
                    System.out.println("Escolha o tipo: 1 - Física | 2 - Jurídica");
                    int tipoExibirID = scanner.nextInt();
                    scanner.nextLine();

                    if (tipoExibirID == 1) {
                        exibirPessoaFisica(scanner);
                    } else if (tipoExibirID == 2) {
                        exibirPessoaJuridica(scanner);
                    } else {
                        System.out.println("Tipo inválido.");
                    }
                    break;

                case 5:
                    System.out.println("Escolha o tipo: 1 - Física | 2 - Jurídica");
                    int tipoExibirTodos = scanner.nextInt();
                    scanner.nextLine();

                    if (tipoExibirTodos == 1) {
                        exibirTodosPessoaFisica(scanner);
                    } else if (tipoExibirTodos == 2) {
                        exibirTodosPessoaJuridica(scanner);
                    } else {
                        System.out.println("Tipo inválido.");
                    }
                    break;

                case 0:
                    continuar = false;
                    break;

                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        }
    }

    private static void incluirPessoaFisica(Scanner scanner) {
        try {
            System.out.print("Nome: ");
            String nome = scanner.nextLine();

            System.out.print("Logradouro: ");
            String logradouro = scanner.nextLine();

            System.out.print("Cidade: ");
            String cidade = scanner.nextLine();

            System.out.print("Estado: ");
            String estado = scanner.nextLine();

            System.out.print("Telefone: ");
            String telefone = scanner.nextLine();

            System.out.print("Email: ");
            String email = scanner.nextLine();

            System.out.print("CPF: ");
            String cpf = scanner.nextLine();

            PessoaFisica pessoaFisica = new PessoaFisica(
                    0,
                    nome,
                    logradouro,
                    cidade,
                    estado,
                    telefone,
                    email,
                    cpf);
            PessoaFisicaDAO pessoaFisicaDAO = new PessoaFisicaDAO();
            pessoaFisicaDAO.incluir(pessoaFisica);
            System.out.println("Pessoa física incluída com sucesso.");

        } catch (Exception e) {
            System.out.println("Erro ao incluir pessoa física: " + e.getMessage());
        }
    }

    private static void incluirPessoaJuridica(Scanner scanner) {
        try {
            System.out.print("Nome: ");
            String nome = scanner.nextLine();

            System.out.print("Logradouro: ");
            String logradouro = scanner.nextLine();

            System.out.print("Cidade: ");
            String cidade = scanner.nextLine();

            System.out.print("Estado: ");
            String estado = scanner.nextLine();

            System.out.print("Telefone: ");
            String telefone = scanner.nextLine();

            System.out.print("Email: ");
            String email = scanner.nextLine();

            System.out.print("CNPJ: ");
            String cnpj = scanner.nextLine();

            PessoaJuridica pessoaJuridica = new PessoaJuridica(
                    0,
                    nome,
                    logradouro,
                    cidade,
                    estado,
                    telefone,
                    email,
                    cnpj);
            PessoaJuridicaDAO pessoaJuridicaDAO = new PessoaJuridicaDAO();
            pessoaJuridicaDAO.incluir(pessoaJuridica);
            System.out.println("Pessoa jurídica incluída com sucesso.");

        } catch (Exception e) {
            System.out.println("Erro ao incluir pessoa jurídica: " + e.getMessage());
        }
    }

    private static void alterarPessoaFisica(Scanner scanner) {
        try {
            System.out.println("Digite o ID: ");
            int idAlterar = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Nome: ");
            String nome = scanner.nextLine();

            System.out.print("Logradouro: ");
            String logradouro = scanner.nextLine();

            System.out.print("Cidade: ");
            String cidade = scanner.nextLine();

            System.out.print("Estado: ");
            String estado = scanner.nextLine();

            System.out.print("Telefone: ");
            String telefone = scanner.nextLine();

            System.out.print("Email: ");
            String email = scanner.nextLine();

            System.out.print("CPF: ");
            String cpf = scanner.nextLine();

            PessoaFisica pessoaFisica = new PessoaFisica(
                    idAlterar,
                    nome,
                    logradouro,
                    cidade,
                    estado,
                    telefone,
                    email,
                    cpf);
            PessoaFisicaDAO pessoaFisicaDAO = new PessoaFisicaDAO();
            pessoaFisicaDAO.alterar(pessoaFisica);
            System.out.println("Pessoa física alterada com sucesso.");

        } catch (Exception e) {
            System.out.println("Erro ao alterar pessoa física: " + e.getMessage());
        }
    }

    private static void alterarPessoaJuridica(Scanner scanner) {
        try {
            System.out.println("Digite o ID: ");
            int idAlterar = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Nome: ");
            String nome = scanner.nextLine();

            System.out.print("Logradouro: ");
            String logradouro = scanner.nextLine();

            System.out.print("Cidade: ");
            String cidade = scanner.nextLine();

            System.out.print("Estado: ");
            String estado = scanner.nextLine();

            System.out.print("Telefone: ");
            String telefone = scanner.nextLine();

            System.out.print("Email: ");
            String email = scanner.nextLine();

            System.out.print("CNPJ: ");
            String cnpj = scanner.nextLine();

            PessoaJuridica pessoaJuridica = new PessoaJuridica(
                    idAlterar,
                    nome,
                    logradouro,
                    cidade,
                    estado,
                    telefone,
                    email,
                    cnpj);
            PessoaJuridicaDAO pessoaJuridicaDAO = new PessoaJuridicaDAO();
            pessoaJuridicaDAO.alterar(pessoaJuridica);
            System.out.println("Pessoa jurídica alterada com sucesso.");

        } catch (Exception e) {
            System.out.println("Erro ao alterar pessoa jurídica: " + e.getMessage());
        }
    }

    private static void excluirPessoaFisica(Scanner scanner) {
        try {
            System.out.println("Digite o ID: ");
            int idPessoa = scanner.nextInt();
            scanner.nextLine();

            PessoaFisicaDAO pessoaFisicaDAO = new PessoaFisicaDAO();
            pessoaFisicaDAO.excluir(idPessoa);
            System.out.println("Pessoa física excluída com sucesso.");

        } catch (Exception e) {
            System.out.println("Erro ao excluir pessoa física: " + e.getMessage());
        }
    }

    private static void excluirPessoaJuridica(Scanner scanner) {
        try {
            System.out.println("Digite o ID: ");
            int idPessoa = scanner.nextInt();
            scanner.nextLine();

            PessoaJuridicaDAO pessoaJuridicaDAO = new PessoaJuridicaDAO();
            pessoaJuridicaDAO.excluir(idPessoa);
            System.out.println("Pessoa jurídica excluída com sucesso.");

        } catch (Exception e) {
            System.out.println("Erro ao excluir pessoa jurídica: " + e.getMessage());
        }
    }

    private static void exibirPessoaFisica(Scanner scanner) {
        try {
            System.out.println("Digite o ID: ");
            int idPessoa = scanner.nextInt();
            scanner.nextLine();

            PessoaFisicaDAO pessoaFisicaDAO = new PessoaFisicaDAO();
            PessoaFisica pessoaFisica = pessoaFisicaDAO.getPessoa(idPessoa);
            if (pessoaFisica == null) {
                System.out.print("Nenhuma pessoa física encontrada");
            } else {
                pessoaFisica.exibir();
            }

        } catch (Exception e) {
            System.out.println("Erro ao obter pessoa física: " + e.getMessage());
        }
    }

    private static void exibirPessoaJuridica(Scanner scanner) {
        try {
            System.out.println("Digite o ID: ");
            int idPessoa = scanner.nextInt();
            scanner.nextLine();

            PessoaJuridicaDAO pessoaJuridicaDAO = new PessoaJuridicaDAO();
            PessoaJuridica pessoaJuridica = pessoaJuridicaDAO.getPessoa(idPessoa);
            if (pessoaJuridica == null) {
                System.out.print("Nenhuma pessoa jurídica encontrada");
            } else {
                pessoaJuridica.exibir();
            }

        } catch (Exception e) {
            System.out.println("Erro ao obter pessoa jurídica: " + e.getMessage());
        }
    }

    private static void exibirTodosPessoaFisica(Scanner scanner) {
        try {
            PessoaFisicaDAO pessoaFisicaDAO = new PessoaFisicaDAO();

            List<PessoaFisica> pessoasFisicas = pessoaFisicaDAO.getPessoas();
            System.out.println("\nListando todas as pessoas físicas:");
            if (pessoasFisicas.isEmpty()) {
                System.out.print("Nenhuma pessoa física encontrada");
            } else {
                for (PessoaFisica pf : pessoasFisicas) {
                    System.out.println("ID: " + pf.getId() + ", Nome: " + pf.getNome() + ", CPF:" + pf.getCpf());
                }
            }

        } catch (Exception e) {
            System.out.println("Erro ao obter pessoas físicas: " + e.getMessage());
        }
    }

    private static void exibirTodosPessoaJuridica(Scanner scanner) {
        try {
            PessoaJuridicaDAO pessoaJuridicaDAO = new PessoaJuridicaDAO();

            List<PessoaJuridica> pessoasJuridicas = pessoaJuridicaDAO.getPessoas();
            System.out.println("\nListando todas as pessoas jurídicas:");
            if (pessoasJuridicas.isEmpty()) {
                System.out.print("Nenhuma pessoa jurídica encontrada");
            } else {
                for (PessoaJuridica pj : pessoasJuridicas) {
                    System.out.println("ID: " + pj.getId() + ", Nome: " + pj.getNome() + ", CNPJ:" + pj.getCnpj());
                }
            }

        } catch (Exception e) {
            System.out.println("Erro ao obter pessoas jurídicas: " + e.getMessage());
        }
    }
}