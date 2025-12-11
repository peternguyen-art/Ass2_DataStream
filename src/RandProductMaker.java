import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

import static java.lang.System.exit;

public class RandProductMaker extends JFrame {
    JPanel mainPnl;
    JPanel titlePnl;
    JPanel contentPnl;
    JPanel buttonPnl;

    JLabel titleLbl;
    JLabel nameLbl;
    JLabel desLbl;
    JLabel idLbl;
    JLabel costLbl;
    JLabel countLbl;

    JTextField nameField;
    JTextField desField;
    JTextField idField;
    JTextField costField;
    JTextField countField;

    JButton addBtn;
    JButton quitBtn;

    public RandProductMaker() {
        mainPnl = new JPanel();
        mainPnl.setLayout(new BorderLayout(5, 5));

        createTitlePnl();
        mainPnl.add(titlePnl, BorderLayout.NORTH);

        createContentPnl();
        mainPnl.add(contentPnl, BorderLayout.CENTER);

        createButtonPnl();
        mainPnl.add(buttonPnl, BorderLayout.SOUTH);

        add(mainPnl);
        setSize(400,400);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void createTitlePnl(){
        titlePnl = new JPanel();
        titleLbl = new JLabel("Enter your product here");
        titlePnl.add(titleLbl);
    }

    private void createContentPnl(){
        contentPnl = new JPanel();
        SpringLayout layout = new SpringLayout();
        contentPnl.setLayout(layout);

        JLabel nameLbl = new JLabel("Name:");
        JLabel desLbl = new JLabel("Description:");
        JLabel idLbl = new JLabel("ID:");
        JLabel costLbl = new JLabel("Cost:");
        JLabel countLbl = new JLabel("Count:");

        nameField = new JTextField(15);
        desField = new JTextField(15);
        idField = new JTextField(15);
        costField = new JTextField(15);
        countField = new JTextField(15);


        layout.putConstraint(SpringLayout.WEST, nameLbl, 20, SpringLayout.WEST, contentPnl);
        layout.putConstraint(SpringLayout.NORTH, nameLbl, 10, SpringLayout.NORTH, contentPnl);
        layout.putConstraint(SpringLayout.WEST, nameField, 20, SpringLayout.EAST, desLbl);
        layout.putConstraint(SpringLayout.NORTH, nameField, 0, SpringLayout.NORTH, nameLbl);

        layout.putConstraint(SpringLayout.WEST, desLbl, 20, SpringLayout.WEST, contentPnl);
        layout.putConstraint(SpringLayout.NORTH, desLbl, 60, SpringLayout.NORTH, contentPnl);
        layout.putConstraint(SpringLayout.WEST, desField, 20, SpringLayout.EAST, desLbl);
        layout.putConstraint(SpringLayout.NORTH, desField, 0, SpringLayout.NORTH, desLbl);

        layout.putConstraint(SpringLayout.WEST, idLbl, 20, SpringLayout.WEST, contentPnl);
        layout.putConstraint(SpringLayout.NORTH, idLbl, 110, SpringLayout.NORTH, contentPnl);
        layout.putConstraint(SpringLayout.WEST, idField, 20, SpringLayout.EAST, desLbl);
        layout.putConstraint(SpringLayout.NORTH, idField, 0, SpringLayout.NORTH, idLbl);

        layout.putConstraint(SpringLayout.WEST, costLbl, 20, SpringLayout.WEST, contentPnl);
        layout.putConstraint(SpringLayout.NORTH, costLbl, 160, SpringLayout.NORTH, contentPnl);
        layout.putConstraint(SpringLayout.WEST, costField, 20, SpringLayout.EAST, desLbl);
        layout.putConstraint(SpringLayout.NORTH, costField, 0, SpringLayout.NORTH, costLbl);

        layout.putConstraint(SpringLayout.WEST, countLbl, 20, SpringLayout.WEST, contentPnl);
        layout.putConstraint(SpringLayout.NORTH, countLbl, 210, SpringLayout.NORTH, contentPnl);
        layout.putConstraint(SpringLayout.WEST, countField, 20, SpringLayout.EAST, desLbl);
        layout.putConstraint(SpringLayout.NORTH, countField, 0, SpringLayout.NORTH, countLbl);

        contentPnl.add(nameLbl);
        contentPnl.add(nameField);
        contentPnl.add(desLbl);
        contentPnl.add(desField);
        contentPnl.add(idLbl);
        contentPnl.add(idField);
        contentPnl.add(costLbl);
        contentPnl.add(costField);
        contentPnl.add(countLbl);
        contentPnl.add(countField);
    }

    private void createButtonPnl(){
        buttonPnl = new JPanel();
        addBtn = new JButton("Add");
        quitBtn = new JButton("Quit");

        quitBtn.addActionListener((ActionEvent e)->exit(0));
        buttonPnl.add(addBtn);
        buttonPnl.add(quitBtn);
    }
}
