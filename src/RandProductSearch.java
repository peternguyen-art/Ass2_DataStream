import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.RandomAccessFile;
import java.io.IOException;

public class RandProductSearch extends JFrame {
    private RandomAccessFile raf;

    JPanel mainPnl;
    JPanel searchPnl;
    JPanel resultsPnl;

    JTextField searchField;
    JButton searchBtn;
    JTextArea resultsArea;

    public RandProductSearch() {
        mainPnl = new JPanel(new BorderLayout(10, 10));
        mainPnl.setBorder(new EmptyBorder(10, 10, 10, 10));

        setupFile();
        createSearchPnl();
        createResultsPnl();

        mainPnl.add(searchPnl, BorderLayout.NORTH);
        mainPnl.add(resultsPnl, BorderLayout.CENTER);

        add(mainPnl);
        setTitle("Random Product Search");
        setSize(700, 500);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void setupFile() {
        try {
            String FILE_NAME = System.getProperty("user.dir") + "\\src\\product.dat";
            raf = new RandomAccessFile(FILE_NAME, "r");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error opening file: " + e.getMessage(), "File Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            System.exit(1);
        }
    }

    private void createSearchPnl() {
        searchPnl = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        searchField = new JTextField(25);
        searchBtn = new JButton("Search by Name");

        searchBtn.addActionListener(e -> searchRecords());

        searchPnl.add(new JLabel("Partial Product Name:"));
        searchPnl.add(searchField);
        searchPnl.add(searchBtn);
    }

    private void createResultsPnl() {
        resultsPnl = new JPanel(new BorderLayout());
        resultsArea = new JTextArea();
        resultsArea.setEditable(false);
        resultsArea.setFont(new Font("Monospaced", Font.PLAIN, 12));

        JScrollPane scrollPane = new JScrollPane(resultsArea);
        resultsPnl.add(scrollPane, BorderLayout.CENTER);

        resultsArea.setText("Enter a partial name and click 'Search'.");
    }

    private void searchRecords() {
        String query = searchField.getText().trim().toLowerCase();
        resultsArea.setText("");

        if (query.isEmpty()) {
            resultsArea.setText("Please enter a search query.");
            return;
        }

        long matches = 0;

        try {
            long numRecords = raf.length() / Product.RECORD_SIZE;

            resultsArea.append(String.format("%-10s %-35s %-35s %s\n",
                    "Record #", "Name", "Description", "Cost"));
            resultsArea.append("-------------------------------------------------------------------------------------------------\n");


            for (long i = 0; i < numRecords; i++) {
                raf.seek(i * Product.RECORD_SIZE);

                String rawName = readString(raf, Product.NAME_LENGTH);

                if (rawName.trim().toLowerCase().contains(query)) {
                    matches++;

                    String des = readString(raf, Product.DESCRIPTION_LENGTH);
                    String id = readString(raf, Product.ID_LENGTH);
                    double cost = raf.readDouble();

                    resultsArea.append(String.format("%-10s %-35s %-35s $%,.2f\n",
                            id,
                            rawName.trim(),
                            des.trim(),
                            cost));
                }
            }

            if (matches == 0) {
                resultsArea.append("\nNo products found matching \"" + query + "\"");
            } else {
                resultsArea.append("\nFound " + matches + " matching record(s).");
            }

        } catch (IOException ex) {
            resultsArea.append("\nError reading file: " + ex.getMessage());
        }
    }

    private String readString(RandomAccessFile raf, int length) throws IOException {
        char[] chars = new char[length];
        for (int i = 0; i < length; i++) {
            chars[i] = raf.readChar();
        }
        return new String(chars);
    }

    @Override
    public void dispose() {
        super.dispose();
        try {
            if (raf != null) {
                raf.close();
                System.out.println("RandomAccessFile closed.");
            }
        } catch (IOException e) {
            System.err.println("Error closing file: " + e.getMessage());
        }
    }
}