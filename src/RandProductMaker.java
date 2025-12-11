import javax.swing.*;
import java.awt.*;

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

        add(mainPnl);
    }

    private void createTitlePnl(){
        titlePnl = new JPanel();
        titleLbl = new JLabel("Enter your product here");
        titlePnl.add(titleLbl);
    }

    private void createContentPnl(){
        contentPnl = new JPanel();
        contentPnl.setLayout(new BoxLayout(contentPnl, BoxLayout.Y_AXIS));
        JLabel nameLbl = new JLabel("Name:");
        JLabel desLbl = new JLabel("Description:");
        JLabel idLbl = new JLabel("ID:");
        JLabel costLbl = new JLabel("Cost:");
        JLabel countLbl = new JLabel("Count:");
        nameField = new JTextField();
        desField = new JTextField(15);
        idField = new JTextField(15);
        costField = new JTextField(15);
        countField = new JTextField(15);
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
}
