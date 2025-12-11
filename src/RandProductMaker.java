import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.RandomAccessFile;

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

    RandomAccessFile raf;
    final String FILE_NAME = "product.dat";
    long recordCount = 0;

    public RandProductMaker() {
        mainPnl = new JPanel();
        mainPnl.setLayout(new BorderLayout(5, 5));

        createTitlePnl();
        mainPnl.add(titlePnl, BorderLayout.NORTH);

        createContentPnl();
        mainPnl.add(contentPnl, BorderLayout.CENTER);

        createButtonPnl();
        mainPnl.add(buttonPnl, BorderLayout.SOUTH);

        setupFile();

        add(mainPnl);
        setTitle("Random Product Maker");
        setSize(500,350);
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

        nameLbl = new JLabel("Name:");
        desLbl = new JLabel("Description:");
        idLbl = new JLabel("ID:");
        costLbl = new JLabel("Cost:");
        countLbl = new JLabel("Count:");

        nameField = new JTextField(15);
        desField = new JTextField(15);
        idField = new JTextField(15);
        costField = new JTextField(15);
        countField = new JTextField(15);
        countField.setEditable(false);


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

        addBtn.addActionListener(this::addRecord);
        quitBtn.addActionListener((ActionEvent e)->exit(0));

        buttonPnl.add(addBtn);
        buttonPnl.add(quitBtn);
    }


    private void addRecord(ActionEvent e) {
        if (!validateFields()) {
            return;
        }

        try {
            String name = nameField.getText();
            String des = desField.getText();
            String id = idField.getText();
            double cost = Double.parseDouble(costField.getText());

            Product newProduct = new Product(name, des, id, cost);
            long bytePosition = recordCount * Product.RECORD_SIZE;
            raf.seek(bytePosition);

            newProduct.writeToFile(raf);

            recordCount++;
            countField.setText(String.valueOf(recordCount));
            resetFields();

            JOptionPane.showMessageDialog(this, "Record added successfully at position: " + recordCount);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Cost must be a valid number.", "Validation Error", JOptionPane.ERROR_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error writing to file: " + ex.getMessage(), "File Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    private boolean validateFields() {
        String costStr = costField.getText().trim();

        if(nameField.getText().isEmpty() || desField.getText().isEmpty() || idField.getText().isEmpty() || costField.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Please fill all the fields!", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (nameField.getText().length() > Product.NAME_LENGTH) {
            JOptionPane.showMessageDialog(this,
                    "Product Name is too long. It will be truncated to " + Product.NAME_LENGTH + " characters.",
                    "Warning: Length",
                    JOptionPane.WARNING_MESSAGE);
        }

        if (desField.getText().length() > Product.DESCRIPTION_LENGTH) {
            JOptionPane.showMessageDialog(this,
                    "Product Description is too long. It will be truncated to " + Product.DESCRIPTION_LENGTH + " characters.",
                    "Warning: Length",
                    JOptionPane.WARNING_MESSAGE);
        }

        if (idField.getText().length() > Product.ID_LENGTH) {
            JOptionPane.showMessageDialog(this,
                    "Product ID is too long. It will be truncated to " + Product.ID_LENGTH + " characters.",
                    "Warning: Length",
                    JOptionPane.WARNING_MESSAGE);
        }

        try {
            double cost = Double.parseDouble(costStr);

            if (cost < 0) {
                JOptionPane.showMessageDialog(this,
                        "Cost cannot be negative.",
                        "Validation Error",
                        JOptionPane.ERROR_MESSAGE);
                return false;
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "Cost must be a valid numeric value.",
                    "Validation Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    private void resetFields(){
        nameField.setText("");
        desField.setText("");
        idField.setText("");
        costField.setText("");
    }

    private void setupFile(){
        try{
            raf = new RandomAccessFile(FILE_NAME, "rw");
            recordCount = raf.length();
            countField.setText(String.valueOf(recordCount));
        }catch(IOException e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            System.exit(0);
        }
    }
}

