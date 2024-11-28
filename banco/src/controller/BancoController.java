package controller;

import model.Conta;
import model.ContaCorrente;
import model.ContaPoupanca;
import model.Funcionario;
import utils.DataManager;
import view.FormularioConsultarConta;
import view.FormularioEncerrarConta;
import view.FormularioCadastroFuncionario;

import java.util.ArrayList;
import java.util.List;

public class BancoController {
    private List<Conta> contas; // Lista de contas do banco
    private List<Funcionario> funcionarios; // Lista de funcionários do banco

    public BancoController() {
        contas = new ArrayList<>();
        funcionarios = new ArrayList<>();
        carregarDados(); // Carrega os dados salvos
    }

    // Método para criar uma nova conta
    public void criarConta(int numeroConta, String nome, String cpf, String tipoConta) {
        if (nome.isEmpty() || cpf.isEmpty() || tipoConta == null || tipoConta.isEmpty()) {
            throw new IllegalArgumentException("Todos os campos devem ser preenchidos.");
        }

        Conta novaConta;
        if ("Corrente".equals(tipoConta)) {
            double limite = 1000.0; // Exemplo de limite para Conta Corrente
            novaConta = new ContaCorrente(numeroConta, nome, cpf, limite);
        } else if ("Poupança".equals(tipoConta)) {
            novaConta = new ContaPoupanca(numeroConta, nome, cpf);
        } else {
            throw new IllegalArgumentException("Tipo de conta inválido.");
        }

        contas.add(novaConta);
        salvarDados();
    }

    // Método para consultar uma conta
    public String consultarConta(int numeroConta) {
        for (Conta conta : contas) {
            if (conta.getNumero() == numeroConta) {
                // Monta os detalhes da conta encontrados
                StringBuilder detalhesConta = new StringBuilder();
                detalhesConta.append("Número da Conta: ").append(conta.getNumero()).append("\n");
                detalhesConta.append("Nome do Titular: ").append(conta.getNome()).append("\n");
                detalhesConta.append("CPF do Titular: ").append(conta.getCpf()).append("\n");
                detalhesConta.append("Tipo de Conta: ").append(conta.getTipoConta()).append("\n");
                detalhesConta.append("Saldo: ").append(conta.getSaldo()).append("\n");
                detalhesConta.append(conta.consultarDetalhes()); // Detalhes específicos da conta
                return detalhesConta.toString();
            }
        }
        return "Conta não encontrada."; // Retorna mensagem caso não encontre a conta
    }

    // Método para excluir uma conta
    public String excluirConta(int numeroConta) {
        for (int i = 0; i < contas.size(); i++) {
            if (contas.get(i).getNumero() == numeroConta) {
                contas.remove(i); // Remove a conta
                salvarDados(); // Salva a lista atualizada
                return "Conta excluída com sucesso!";
            }
        }
        return "Conta não encontrada.";
    }

    // Método para alterar os dados de uma conta
    public boolean alterarDados(int numeroConta, String novoNome, String novoCpf) {
        for (Conta conta : contas) {
            if (conta.getNumero() == numeroConta) {
                // Alterar os dados da conta
                conta.setNome(novoNome);
                conta.setCpf(novoCpf);
                salvarDados(); // Salva os dados atualizados
                return true; // Retorna verdadeiro se a conta foi encontrada e os dados foram alterados
            }
        }
        return false; // Retorna falso se a conta não foi encontrada
    }

 // Método para cadastrar um novo funcionário
    public void cadastrarFuncionario(String usuario, String senha) {
        if (usuario.isEmpty() || senha.isEmpty()) {
            throw new IllegalArgumentException("Usuário e Senha devem ser preenchidos.");
        }

        Funcionario novoFuncionario = new Funcionario(usuario, senha);
        funcionarios.add(novoFuncionario);
        salvarDados();
    }


    // Outros métodos do controlador
    public void salvarDados() {
        DataManager.salvarContas(contas, "contas.dat");
        DataManager.salvarFuncionarios(funcionarios, "funcionarios.dat");
    }

    public void carregarDados() {
        contas = DataManager.carregarContas("contas.dat");
        funcionarios = DataManager.carregarFuncionarios("funcionarios.dat");
    }
}
