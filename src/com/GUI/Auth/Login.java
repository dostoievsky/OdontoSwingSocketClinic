package com.GUI.Auth;

import com.GUI.Admin.Menu;
import com.GUI.Principal;
import com.components.Background;

import com.components.JButtons;
import com.components.JLabels;
import com.components.JPanels;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;


public class Login extends JPanel implements ActionListener {

    private final Principal parent;
    private final JTextField UsuarioField = new JTextField();
    private final JTextField SenhaField = new JTextField();
    private final JButtons Entrar = new JButtons(125, 25, "ENTRAR");
    private final JButtons Cadastrar = new JButtons(125, 25, "CADASTRAR");
    private final JButtons Sair = new JButtons(125, 25, "SAIR");


    public Login(Principal principal) {
        super();
        parent = principal;
        createGUI();
    }

    private void createGUI(){
        this.setBounds(0, 0, 1080, 540);
        this.setLayout(null);
        this.add(Container());
        Background bg = new Background(new ImageIcon(Objects.requireNonNull(getClass().getResource("/assets/LoginImage.png"))).getImage());
        bg.setBounds(250, 0, 830, 540);
        this.add(bg);
    }

    private JPanels Container() {
        JPanels container = new JPanels(0, 0, 250, 540);
        container.setLayout(null);
        container.add(Logo());
        container.add(Formulario());
        container.add(ButtonsPanel());
        return container;
    }

    private JPanels Logo(){
        JPanels Logo = new JPanels(75,30,100,100);
        Logo.setBackground(Color.red);

        return Logo;
    }

    private JPanels Formulario(){
        JPanels Formulario = new JPanels(25, 150, 200, 175);
        Formulario.setLayout(null);
        Formulario.add(FSCPanel());
        Formulario.add(UsuarioPanel());
        Formulario.add(UsuarioFieldPanel());
        Formulario.add(SenhaPanel());
        Formulario.add(SenhaFieldPanel());
        return Formulario;
    }

    private JPanels FSCPanel(){
        JPanels FSC = new JPanels(25, 5, 150, 30);
        FSC.add(new JLabels("Faça seu LogIn!", 20, 1));
        return FSC;
    }

    private JPanels ButtonsPanel(){
        JPanels ButtonsLabel = new JPanels(50, 350, 150, 130);
        ButtonsLabel.setLayout(null);
        ButtonsLabel.add(EPanel());
        ButtonsLabel.add(CPanel());
        ButtonsLabel.add(SPanel());
        return ButtonsLabel;
    }

    private JPanels EPanel(){
        JPanels EPanel = new JPanels(10, 5, 125, 30);
        Entrar.addActionListener(this);
        EPanel.add(Entrar);
        return EPanel;
    }

    private JPanels CPanel(){
        JPanels CPanel = new JPanels(10, 40, 125, 30);
        Cadastrar.addActionListener(this);
        CPanel.add(Cadastrar);
        return CPanel;
    }

    private JPanels SPanel(){
        JPanels SPanel = new JPanels(10, 75, 125, 30);
        Sair.addActionListener(this);
        SPanel.add(Sair);
        return SPanel;
    }

    private JPanels UsuarioPanel(){
        JPanels UsuarioPanel = new JPanels(10, 40, 50, 20);
        UsuarioPanel.add(new JLabels("Usuario:", 12, 1));
        return UsuarioPanel;
    }

    private JPanels UsuarioFieldPanel(){
        JPanels UsuarioFieldPanel = new JPanels(10, 63, 150, 30);
        UsuarioFieldPanel.setLayout(null);
        UsuarioFieldPanel.add(UsuarioField());
        return UsuarioFieldPanel;
    }

    private JTextField UsuarioField(){
        UsuarioField.setBounds(0, 0, 150, 30);
        return UsuarioField;
    }

    private JPanels SenhaPanel(){
        JPanels Senha = new JPanels(10, 100, 50, 20);
        Senha.add(new JLabels("Senha:", 12, 1));
        return Senha;
    }

    private JPanels SenhaFieldPanel(){
        JPanels SenhaFieldPanel = new JPanels(10, 125, 150, 30);
        SenhaFieldPanel.setLayout(null);
        SenhaFieldPanel.add(SenhaField());
        return SenhaFieldPanel;
    }

    private JTextField SenhaField(){
        SenhaField.setBounds(0, 0, 150, 30);
        return SenhaField;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==Cadastrar){
            handleCadastro();
        }else if(e.getSource()==Sair) {
            handleSair();
        }else if(e.getSource()==Entrar){
            try {
                handleLogin();
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
        }
    }

    private void handleLogin() throws InterruptedException {
        parent.sendMessage("login");
        Thread.sleep(1000);
        parent.sendMessage(UsuarioField.getText());
        Thread.sleep(1000);
        parent.sendMessage(SenhaField.getText());
        boolean confirmation = parent.getConfirmation();
        if (confirmation) {
            parent.setContentPane(new Menu(parent));
        }
    }

    private void handleCadastro() {
        parent.setContentPane(new Cadastro(parent));
    }

    private void handleSair(){
        parent.sendMessage("close");
        parent.stopConection();
    }

}