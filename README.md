# Projeto CadastroBD

## Visão Geral do Projeto

O projeto **CadastroBD** é uma aplicação Java projetada para gerenciar o cadastro de pessoas, tanto físicas (Pessoa Física) quanto jurídicas (Pessoa Jurídica), utilizando um banco de dados SQL Server. 

O projeto segue o padrão DAO (Data Access Object) para realizar operações no banco de dados e se conecta ao SQL Server utilizando JDBC.

O [relatório de práticas](https://github.com/user-attachments/files/17271256/projeto3mundo3.pdf) foi confeccionado em formato PDF e produzido em conjunto com o desenvolvimento do projeto.

### Funcionalidades
- Cadastro de pessoas físicas e jurídicas.
- Suporte para operações no banco de dados: inserção, atualização, exclusão e consulta de registros.
- Conexão com o banco de dados SQL Server via driver JDBC.
- Interface de linha de comando para interação.

## Estrutura do Projeto

O projeto está organizado nos seguintes pacotes:
- `cadastrobd`: Contém a classe principal main.
- `cadastrobd.model`: Contém as classes de entidade principais, classes DAO responsáveis pelas operações no banco de dados e classe principal para testes e interação com o sistema.
- `cadastrobd.model.util`: Contém classes utilitárias para a conectividade com o banco de dados.

## Instruções de Configuração

### 1. Criação do Projeto

1. Abra o **NetBeans** e crie um novo projeto:
   - Nome do Projeto: `CadastroBD`
   - Tipo de Projeto: Aplicação Java Padrão (baseado em Ant)

2. Adicione o **driver JDBC** para SQL Server:
   - Clique com o botão direito em **Bibliotecas** e selecione `Adicionar JAR/Pasta`.
   - Selecione o arquivo `mssql-jdbc-12.2.0.jre11.jar` (faça o download em [Microsoft JDBC Driver para SQL Server](https://learn.microsoft.com/pt-br/sql/connect/jdbc/download-microsoft-jdbc-driver-for-sql-server?view=sql-server-ver16)).

### 2. Configuração do Acesso ao Banco de Dados

1. Na aba `Serviços` do NetBeans, expanda a seção `Banco de Dados`.
2. Clique com o botão direito em `Drivers` e selecione `Novo Driver`.
3. Na janela que abrir, clique em `Adicionar` e selecione o arquivo `.jar` do driver JDBC que foi adicionado anteriormente.
4. Após o reconhecimento do driver, clique com o botão direito nele e selecione `Conectar Utilizando...`.
5. Preencha os campos conforme abaixo:
   - **Banco de Dados**: `loja`
   - **Usuário**: `seu_usuário`
   - **Senha**: `sua_senha`
   - **JDBC URL**: `jdbc:sqlserver://localhost:1433;databaseName=loja;encrypt=true;trustServerCertificate=true;`
6. Teste a conexão clicando em `Testar Conexão`. Se estiver tudo correto, finalize a conexão.

### 3. Estrutura de Classes

No pacote `cadastrobd.model`:
- **Classe Pessoa**: Com os campos `id`, `nome`, `logradouro`, `cidade`, `estado`, `telefone`, `email`. Possui um construtor padrão e um completo, além do método `exibir` para impressão dos dados no console.
- **Classe PessoaFisica**: Herda de `Pessoa`, adicionando o campo `cpf`. Reescreve os construtores e utiliza polimorfismo no método `exibir`.
- **Classe PessoaJuridica**: Herda de `Pessoa`, adicionando o campo `cnpj`. Reescreve os construtores e utiliza polimorfismo no método `exibir`.

No pacote `cadastrobd.model.util`:
- **Classe ConectorBD**: Possui métodos para gerenciar conexões com o banco de dados: `getConnection`, `getPrepared` e `getSelect`. Também contém métodos sobrecarregados `close` para fechar `Statement`, `ResultSet` e `Connection`.
- **Classe SequenceManager**: Gerencia sequências no banco de dados com o método `getValue`, que recebe o nome da sequência e retorna o próximo valor.

No pacote `cadastrobd.dao`:
- **Classe PessoaFisicaDAO**: Contém os métodos para gerenciar pessoas físicas no banco de dados: `getPessoa`, `getPessoas`, `incluir`, `alterar`, e `excluir`.
- **Classe PessoaJuridicaDAO**: Similar à classe anterior, mas para gerenciar pessoas jurídicas.

### 4. Classe Principal para Testes

Crie a classe principal `CadastroBDTeste` no pacote `cadastrobd.test` para realizar os testes das operações. No método `main`, execute as operações seguintes:
- Inserir, alterar, consultar e excluir pessoas físicas e jurídicas no banco de dados.
- Exibir todos os registros de pessoas físicas e jurídicas no console.

### 5. Cadastro via Interface de Texto

Modifique o método `main` para implementar um cadastro via texto, apresentando as seguintes opções:
- **1**: Incluir
- **2**: Alterar
- **3**: Excluir
- **4**: Exibir por ID
- **5**: Exibir todos
- **0**: Finalizar

O programa deve perguntar ao usuário se deseja cadastrar pessoa física ou jurídica, e realizar as operações no banco de dados conforme a opção escolhida. As exceções devem ser tratadas adequadamente.

### 6. Testes

Testar as funcionalidades do sistema, realizando operações como inclusão, alteração, exclusão e consulta de dados no SQL Server. Verificar os resultados utilizando a aba `Banco de Dados` do NetBeans ou o SQL Server Management Studio.
