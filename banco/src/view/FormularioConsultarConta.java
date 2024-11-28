package view;

import javax.swing.*;
import java.awt.*;
import controller.BancoController;

public class FormularioConsultarConta extends JFrame {
    private JTextField tfNumeroConta;
    private JTextArea taResultadoConsulta;
    private BancoController bancoController; // Referência para o controlador

    public FormularioConsultarConta(BancoController bancoController) {
        this.bancoController = bancoController; // Recebe o controlador

        setTitle("Consultar Conta");
        setSize(400, 300);
        setLocationRelativeTo(null);

        // Altere para DISPOSE_ON_CLOSE para fechar apenas esta janela
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1));

        JLabel lblNumeroConta = new JLabel("Número da Conta:");
        tfNumeroConta = new JTextField();
        JButton btnConsultar = new JButton("Consultar");
        taResultadoConsulta = new JTextArea(6, 30);
        taResultadoConsulta.setEditable(false);

        panel.add(lblNumeroConta);
        panel.add(tfNumeroConta);
        panel.add(btnConsultar);
        panel.add(new JScrollPane(taResultadoConsulta));

        add(panel);

        // Ação para consultar a conta
        btnConsultar.addActionListener(e -> {
            String numeroConta = tfNumeroConta.getText().trim();

            // Verifica se o número da conta está vazio
            if (numeroConta.isEmpty()) {
                JOptionPane.showMessageDialog(this, "O número da conta deve ser preenchido.");
                return; // Sai do método se o campo estiver vazio
            }

            try {
                int numeroContaInt = Integer.parseInt(numeroConta); // Converte o número da conta para inteiro

                // Chama o método de consulta no controlador
                String resultadoConsulta = bancoController.consultarConta(numeroContaInt);

                taResultadoConsulta.setText(resultadoConsulta); // Exibe o resultado da consulta

            } catch (NumberFormatException e1) {
                JOptionPane.showMessageDialog(this, "Número da conta deve ser um número válido.");
            }
        });

        setVisible(true);
    }
}
