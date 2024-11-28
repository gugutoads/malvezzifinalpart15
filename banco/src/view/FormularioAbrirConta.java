package view;

import controller.BancoController;

import javax.swing.*;

public class FormularioAbrirConta extends JFrame {
    private BancoController bancoController;

    public FormularioAbrirConta(BancoController bancoController) {
        this.bancoController = bancoController;

        setTitle("Abrir Conta");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        getContentPane().add(panel);

        JLabel labelNumeroConta = new JLabel("Número da Conta:");
        JTextField tfNumeroConta = new JTextField(15);
        JLabel labelNome = new JLabel("Nome:");
        JTextField tfNome = new JTextField(15);
        JLabel labelCpf = new JLabel("CPF:");
        JTextField tfCpf = new JTextField(15);
        JLabel labelTipoConta = new JLabel("Tipo de Conta:");
        JComboBox<String> comboTipoConta = new JComboBox<>(new String[]{"Corrente", "Poupança"});

        JButton btnAbrir = new JButton("Abrir Conta");

        panel.add(labelNumeroConta);
        panel.add(tfNumeroConta);
        panel.add(labelNome);
        panel.add(tfNome);
        panel.add(labelCpf);
        panel.add(tfCpf);
        panel.add(labelTipoConta);
        panel.add(comboTipoConta);
        panel.add(btnAbrir);

        btnAbrir.addActionListener(e -> {
            try {
                int numeroConta = Integer.parseInt(tfNumeroConta.getText()); // Converte o número da conta para inteiro
                String nome = tfNome.getText();
                String cpf = tfCpf.getText();
                String tipoConta = (String) comboTipoConta.getSelectedItem();

                bancoController.criarConta(numeroConta, nome, cpf, tipoConta);

                JOptionPane.showMessageDialog(this, "Conta criada com sucesso!");
                dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Número da conta deve ser um número válido.");
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        });

        setVisible(true);
    }
}
