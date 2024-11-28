package view;

import controller.BancoController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormularioEncerrarConta extends JFrame {
    private BancoController bancoController;

    public FormularioEncerrarConta(BancoController bancoController) {
        this.bancoController = bancoController;

        setTitle("Encerrar Conta");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        getContentPane().add(panel);

        JLabel lblNumeroConta = new JLabel("Número da Conta:");
        JTextField txtNumeroConta = new JTextField(15);
        JButton btnEncerrar = new JButton("Encerrar");

        panel.add(lblNumeroConta);
        panel.add(txtNumeroConta);
        panel.add(btnEncerrar);

        btnEncerrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String numeroContaStr = txtNumeroConta.getText().trim();

                if (numeroContaStr.isEmpty()) {
                    JOptionPane.showMessageDialog(FormularioEncerrarConta.this, "O número da conta deve ser preenchido.");
                    return;
                }

                try {
                    int numeroConta = Integer.parseInt(numeroContaStr);
                    String resultado = bancoController.excluirConta(numeroConta);
                    JOptionPane.showMessageDialog(FormularioEncerrarConta.this, resultado);
                    dispose(); // Fecha a janela após encerrar a conta
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(FormularioEncerrarConta.this, "O número da conta deve ser um valor válido.");
                }
            }
        });

        setVisible(true);
    }
}
