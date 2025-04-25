package zad1;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Database {
    private final String url;
    private final TravelData travelData;

    private String dateFormat = "yyyy-MM-dd";
    private String locale = "en_GB";

    private static int index;
    private List<String> locales;

    public Database(String url, TravelData travelData) {
        this.url = url;
        this.travelData = travelData;
        index = 0;
        this.locales = new ArrayList<>();

        Locale[] locales = Locale.getAvailableLocales();
        for (Locale locale : locales) {
            if (!locale.getLanguage().isEmpty() && !locale.getCountry().isEmpty()) {
                this.locales.add(locale.getLanguage() + "_" + locale.getCountry());
            }
        }
    }

    public void create() {
        try(Statement statement = DriverManager.getConnection(this.url + ";create=true").createStatement()){
            statement.executeUpdate("DROP TABLE OFFERS");
            statement.executeUpdate("CREATE TABLE OFFERS (" +
                    "ID INTEGER PRIMARY KEY, " +
                    "LOCALIZATION VARCHAR(30), " +
                    "COUNTRY VARCHAR(30), " +
                    "DEPARTURE_DATE VARCHAR(30), " +
                    "RETURN_DATE VARCHAR(30), " +
                    "PLACE VARCHAR(30), " +
                    "PRICE VARCHAR(30), " +
                    "CURRENCY VARCHAR(30)" +
                    ")"
            );
            for(Offer offer : this.travelData.getOfferList()){
                statement.executeUpdate("INSERT INTO OFFERS(ID, LOCALIZATION, COUNTRY, DEPARTURE_DATE, RETURN_DATE, PLACE, PRICE, CURRENCY) " +
                        offer.getInsertStatement(index++)
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void showGui() {
        SwingUtilities.invokeLater(this::createGui);
    }

    private void createGui(){
//        GUI powinno pozwalac na wybór języka i ustawien regionalnych, w których pokazywane są oferty.
        JFrame frame = new JFrame();
        frame.setTitle("Database");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(1000, 500));
        frame.setLocation(0, 0);
//        frame.setResizable(false);
        frame.setVisible(true);

        JPanel panel = new JPanel();
        panel.setSize(new Dimension(1000, 500));
        panel.setLayout(new BorderLayout());
        frame.add(panel);

        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setSize(new Dimension(1000,  500));
        DefaultTableModel model = new DefaultTableModel(new Object[]{"LOCALIZATION", "COUNTRY", "DEPARTURE_DATE", "RETURN_DATE", "PLACE", "PRICE", "CURRENCY"}, 0);
        JTable table = new JTable();
        table.setSize(new Dimension(1000, 500));
        table.setEnabled(false);
        table.setAutoResizeMode(1);
        table.setModel(model);
        TableColumnModel columnModel = table.getColumnModel();
        for(int i = 0; i < model.getColumnCount(); i++){
            columnModel.getColumn(i).setPreferredWidth(100);

        }
        tablePanel.add(new JScrollPane(table), BorderLayout.CENTER);
        panel.add(tablePanel, BorderLayout.CENTER);
        try(Statement statement = DriverManager.getConnection(this.url).createStatement()){
            ResultSet resultSet = statement.executeQuery("SELECT * FROM OFFERS");
            while(resultSet.next()){
                model.addRow(new Object[]{
                        resultSet.getString("LOCALIZATION"),
                        resultSet.getString("COUNTRY"),
                        resultSet.getString("DEPARTURE_DATE"),
                        resultSet.getString("RETURN_DATE"),
                        resultSet.getString("PLACE"),
                        resultSet.getString("PRICE"),
                        resultSet.getString("CURRENCY"),
                });
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 1));
        panel.add(buttonPanel, BorderLayout.SOUTH);

        JButton changeLanguageButton = new JButton("Change language and location");
        changeLanguageButton.addActionListener(e -> {
            String input = JOptionPane.showInputDialog("Enter location:");

            if(input == null){
                return;
            } else if(locales.contains(input)){
                List<String> rowsToAdd = new ArrayList<>();

                for(int row = 0; row < model.getRowCount(); row++){
                    StringBuilder oldData = new StringBuilder();
                    for(int column = 0 ; column < model.getColumnCount(); column++){
                        oldData.append(model.getValueAt(row, column).toString()).append("\t");
                    }
                    Offer offer = new Offer(oldData.toString(), "\t");
                    String newData = TravelData.getOfferDescription(offer, input, "yyyy-MM-dd", "\t", input);
                    rowsToAdd.add(newData);
                }
                for(int row = model.getRowCount() - 1; row >= 0 ; row--){
                    model.removeRow(row);
                }

                for(String data: rowsToAdd){
                    model.addRow(data.split("\t"));
                }
            }
        });
        buttonPanel.add(changeLanguageButton);

        frame.pack();
    }
}
