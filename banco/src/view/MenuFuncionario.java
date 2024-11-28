package view;

import controller.BancoController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuFuncionario extends JFrame {
    private BancoController bancoController;

    public MenuFuncionario(BancoController bancoController) {
        this.bancoController = bancoController;

        setTitle("Menu Funcionário");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        getContentPane().add(panel);

        JButton btnAbrirConta = new JButton("Abrir Conta");
        JButton btnEncerrarConta = new JButton("Encerrar Conta");
        JButton btnConsultarConta = new JButton("Consultar Conta");
        JButton btnAlterarDados = new JButton("Alterar Dados");
        JButton btnCadastroFuncionario = new JButton("Cadastro Funcionario");
        JButton btnGerarRelatorios = new JButton("Gerar Relatórios");
        JButton btnSair = new JButton("Sair");

        panel.add(btnAbrirConta);
        panel.add(btnEncerrarConta);
        panel.add(btnConsultarConta);
        panel.add(btnAlterarDados);
        panel.add(btnCadastroFuncionario);
        panel.add(btnGerarRelatorios);
        panel.add(btnSair);

        // Abrir Conta
        btnAbrirConta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirConta();
            }
        });

        // Encerrar Conta
        btnEncerrarConta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                encerrarConta();
            }
        });

        // Consultar Conta
        btnConsultarConta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                consultarConta();
            }
        });

        // Alterar Dados
        btnAlterarDados.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                alterarDados();
            }
        });

        // Cadastro Funcionario
        btnCadastroFuncionario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrarFuncionario();
            }
        });

        // Gerar Relatórios
        btnGerarRelatorios.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gerarRelatorios();
            }
        });

        // Sair
        btnSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Fecha o menu de funcionário
            }
        });

        setVisible(true); // Torna a janela visível
    }

    // Métodos que serão chamados pelos botões
    public void abrirConta() {
        new FormularioAbrirConta(bancoController);
    }

    public void encerrarConta() {
        new FormularioEncerrarConta(bancoController);
    }

    public void consultarConta() {
        new FormularioConsultarConta(bancoController);
    }

    public void alterarDados() {
        new FormularioAlterarDados(bancoController);
    }

    public void cadastrarFuncionario() {
        new FormularioCadastroFuncionario(bancoController);
    }

    public void gerarRelatorios() {
        JOptionPane.showMessageDialog(this, "Gerar Relatórios não implementado.");
    }
}
