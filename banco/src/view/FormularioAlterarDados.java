package view;

import controller.BancoController;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormularioAlterarDados extends JFrame {
    private BancoController bancoController;

    public FormularioAlterarDados(BancoController bancoController) {
        this.bancoController = bancoController;

        setTitle("Alterar Dados da Conta");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel labelNumeroConta = new JLabel("Número da Conta:");
        JTextField tfNumeroConta = new JTextField(15);
        JButton btnBuscarConta = new JButton("Buscar Conta");

        JLabel labelNome = new JLabel("Novo Nome:");
        JTextField tfNome = new JTextField(15);
        JLabel labelCpf = new JLabel("Novo CPF:");
        JTextField tfCpf = new JTextField(15);

        // Inicialmente desabilita os campos de nome e cpf
        tfNome.setEnabled(false);
        tfCpf.setEnabled(false);

        JButton btnAlterar = new JButton("Alterar Dados");
        btnAlterar.setEnabled(false); // Inicialmente desabilita o botão de alterar

        panel.add(labelNumeroConta);
        panel.add(tfNumeroConta);
        panel.add(btnBuscarConta);
        panel.add(labelNome);
        panel.add(tfNome);
        panel.add(labelCpf);
        panel.add(tfCpf);
        panel.add(btnAlterar);

        getContentPane().add(panel);
        
        // Ação para buscar a conta
        btnBuscarConta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String numeroConta = tfNumeroConta.getText().trim();

                if (numeroConta.isEmpty()) {
                    JOptionPane.showMessageDialog(FormularioAlterarDados.this, "O número da conta deve ser preenchido.");
                    return;
                }

                try {
                    // Tentar consultar a conta (não alterar dados ainda)
                    String resultadoConsulta = bancoController.consultarConta(Integer.parseInt(numeroConta));

                    if (!resultadoConsulta.equals("Conta não encontrada.")) {
                        // Conta encontrada, habilita os campos e o botão de alterar
                        tfNome.setEnabled(true);
                        tfCpf.setEnabled(true);
                        btnAlterar.setEnabled(true);
                        JOptionPane.showMessageDialog(FormularioAlterarDados.this, "Conta encontrada. Pode alterar os dados.");
                    } else {
                        JOptionPane.showMessageDialog(FormularioAlterarDados.this, "Conta não encontrada.");
                    }

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(FormularioAlterarDados.this, "Número da conta inválido.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(FormularioAlterarDados.this, "Erro ao buscar a conta.");
                }
            }
        });

        // Ação para alterar os dados
        btnAlterar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String numeroConta = tfNumeroConta.getText().trim();
                String novoNome = tfNome.getText().trim();
                String novoCpf = tfCpf.getText().trim();

                if (novoNome.isEmpty() || novoCpf.isEmpty()) {
                    JOptionPane.showMessageDialog(FormularioAlterarDados.this, "O nome e o CPF devem ser preenchidos.");
                    return;
                }

                try {
                    // Alterar os dados da conta
                    boolean contaAlterada = bancoController.alterarDados(Integer.parseInt(numeroConta), novoNome, novoCpf);

                    if (contaAlterada) {
                        JOptionPane.showMessageDialog(FormularioAlterarDados.this, "Dados da conta alterados com sucesso!");
                        dispose(); // Fecha a janela
                    } else {
                        JOptionPane.showMessageDialog(FormularioAlterarDados.this, "Conta não encontrada.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(FormularioAlterarDados.this, "Número da conta inválido.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(FormularioAlterarDados.this, "Erro ao alterar os dados da conta.");
                }
            }
        });

        setVisible(true); // Torna a janela visível
    }
}
