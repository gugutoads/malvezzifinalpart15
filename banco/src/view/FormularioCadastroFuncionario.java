package view;

import controller.BancoController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormularioCadastroFuncionario extends JFrame {
    private BancoController bancoController;

    public FormularioCadastroFuncionario(BancoController bancoController) {
        this.bancoController = bancoController;

        setTitle("Cadastro de Funcionário");
        setSize(400, 250);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        getContentPane().add(panel);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel labelUsuario = new JLabel("Usuário:");
        JTextField tfUsuario = new JTextField(15);
        JLabel labelSenha = new JLabel("Senha:");
        JPasswordField pfSenha = new JPasswordField(15);

        panel.add(labelUsuario);
        panel.add(tfUsuario);
        panel.add(labelSenha);
        panel.add(pfSenha);

        JButton btnCadastrar = new JButton("Cadastrar");
        panel.add(btnCadastrar);

        // Ação para cadastrar um novo funcionário
        btnCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuario = tfUsuario.getText().trim();
                String senha = new String(pfSenha.getPassword()).trim();

                if (usuario.isEmpty() || senha.isEmpty()) {
                    JOptionPane.showMessageDialog(FormularioCadastroFuncionario.this, "Usuário e Senha devem ser preenchidos.");
                    return;
                }

                try {
                    // Chama o controlador para cadastrar o funcionário
                    bancoController.cadastrarFuncionario(usuario, senha);

                    JOptionPane.showMessageDialog(FormularioCadastroFuncionario.this, "Funcionário cadastrado com sucesso!");
                    dispose(); // Fecha a janela de cadastro
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(FormularioCadastroFuncionario.this, ex.getMessage());
                }
            }
        });

        setVisible(true); // Torna a janela visível
    }
}
