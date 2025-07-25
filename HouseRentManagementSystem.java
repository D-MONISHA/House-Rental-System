package house;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

class House {
    String houseNo, rent, floor, unit, details, about;

    public House(String houseNo, String rent, String floor, String unit, String details, String about) {
        this.houseNo = houseNo;
        this.rent = rent;
        this.floor = floor;
        this.unit = unit;
        this.details = details;
        this.about = about;
    }
}

public class HouseRentManagementSystem {
    private static final ArrayList<House> houseList = new ArrayList<>();

    public static void main(String[] args) {
        // Add some dummy data for demonstration
        houseList.add(new House("101", "5000", "1st", "A", "2 Bed + 1 Kitchen + 1 Bath", "Free"));
        houseList.add(new House("102", "6000", "1st", "B", "3 Bed + 1 Kitchen + 2 Bath", "Occupied"));

        // Home Frame
        JFrame homeFrame = new JFrame("House Rent Management System");
        homeFrame.setSize(800, 600);
        homeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        homeFrame.setLayout(null);
        homeFrame.getContentPane().setBackground(new Color(245, 245, 245));

        JLabel title = new JLabel("House Rent Management System", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 22));
        title.setBounds(150, 50, 500, 40);
        homeFrame.add(title);

        // Admin and User Buttons
        JButton adminButton = createGradientButton("Admin Panel");
        adminButton.setBounds(200, 200, 150, 50);
        homeFrame.add(adminButton);

        JButton userButton = createGradientButton("User Panel");
        userButton.setBounds(450, 200, 150, 50);
        homeFrame.add(userButton);

        // Admin Panel with Password Protection
        adminButton.addActionListener(e -> {
            JPasswordField passwordField = new JPasswordField();
            int option = JOptionPane.showConfirmDialog(homeFrame, passwordField, "Enter Admin Password", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (option == JOptionPane.OK_OPTION) {
                String password = new String(passwordField.getPassword());
                if (password.equals("admin123")) { // Replace with a secure password mechanism
                    showAdminPanel(homeFrame);
                } else {
                    JOptionPane.showMessageDialog(homeFrame, "Incorrect Password!", "Access Denied", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // User Panel
        userButton.addActionListener(e -> showUserPanel(homeFrame));

        homeFrame.setVisible(true);
    }

    private static void showAdminPanel(JFrame homeFrame) {
        JFrame adminFrame = new JFrame("Admin Panel");
        adminFrame.setSize(800, 600);
        adminFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        adminFrame.setLayout(null);
        adminFrame.getContentPane().setBackground(new Color(240, 240, 240));

        JLabel adminTitle = new JLabel("Admin - Add House Details", JLabel.CENTER);
        adminTitle.setFont(new Font("Arial", Font.BOLD, 20));
        adminTitle.setBounds(150, 20, 500, 40);
        adminFrame.add(adminTitle);

        JButton backToHomeFromAdmin = createGradientButton("Back");
        backToHomeFromAdmin.setBounds(50, 500, 100, 40);
        adminFrame.add(backToHomeFromAdmin);

        backToHomeFromAdmin.addActionListener(e -> {
            adminFrame.dispose();
            homeFrame.setVisible(true);
        });

        JButton addHouseButton = createGradientButton("Add House");
        addHouseButton.setBounds(300, 100, 200, 40);
        adminFrame.add(addHouseButton);

        addHouseButton.addActionListener(e -> {
            JDialog dialog = new JDialog(adminFrame, "Add House Details", true);
            dialog.setLayout(null);
            dialog.setSize(400, 400);
            dialog.getContentPane().setBackground(new Color(230, 240, 255));

            JLabel houseNoLabel = new JLabel("House No:");
            houseNoLabel.setBounds(30, 30, 80, 25);
            JTextField houseNoField = new JTextField();
            houseNoField.setBounds(120, 30, 200, 25);

            JLabel rentLabel = new JLabel("Rent (Tk):");
            rentLabel.setBounds(30, 70, 80, 25);
            JTextField rentField = new JTextField();
            rentField.setBounds(120, 70, 200, 25);

            JLabel floorLabel = new JLabel("Floor:");
            floorLabel.setBounds(30, 110, 80, 25);
            JComboBox<String> floorBox = new JComboBox<>(new String[]{"1st", "2nd", "3rd", "4th"});
            floorBox.setBounds(120, 110, 200, 25);

            JLabel unitLabel = new JLabel("Unit:");
            unitLabel.setBounds(30, 150, 80, 25);
            JComboBox<String> unitBox = new JComboBox<>(new String[]{"A", "B", "C"});
            unitBox.setBounds(120, 150, 200, 25);

            JLabel detailsLabel = new JLabel("Details:");
            detailsLabel.setBounds(30, 190, 80, 25);
            JTextField detailsField = new JTextField();
            detailsField.setBounds(120, 190, 200, 25);

            JLabel aboutLabel = new JLabel("Status:");
            aboutLabel.setBounds(30, 230, 80, 25);
            JComboBox<String> aboutBox = new JComboBox<>(new String[]{"Free", "Occupied"});
            aboutBox.setBounds(120, 230, 200, 25);

            JButton saveButton = createGradientButton("Save");
            saveButton.setBounds(150, 300, 100, 40);

            dialog.add(houseNoLabel);
            dialog.add(houseNoField);
            dialog.add(rentLabel);
            dialog.add(rentField);
            dialog.add(floorLabel);
            dialog.add(floorBox);
            dialog.add(unitLabel);
            dialog.add(unitBox);
            dialog.add(detailsLabel);
            dialog.add(detailsField);
            dialog.add(aboutLabel);
            dialog.add(aboutBox);
            dialog.add(saveButton);

            saveButton.addActionListener(ev -> {
                String houseNo = houseNoField.getText();
                String rent = rentField.getText();
                String floor = floorBox.getSelectedItem().toString();
                String unit = unitBox.getSelectedItem().toString();
                String details = detailsField.getText();
                String about = aboutBox.getSelectedItem().toString();

                if (!houseNo.isEmpty() && !rent.isEmpty()) {
                    House house = new House(houseNo, rent, floor, unit, details, about);
                    houseList.add(house);
                    dialog.dispose();
                } else {
                    JOptionPane.showMessageDialog(dialog, "Please fill in all fields!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            });

            dialog.setLocationRelativeTo(adminFrame);
            dialog.setVisible(true);
        });

        adminFrame.setVisible(true);
    }

    private static void showUserPanel(JFrame homeFrame) {
        JFrame userFrame = new JFrame("User Panel");
        userFrame.setSize(800, 600);
        userFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        userFrame.setLayout(null);
        userFrame.getContentPane().setBackground(new Color(255, 255, 255));

        JLabel userTitle = new JLabel("User - Search and Book House", JLabel.CENTER);
        userTitle.setFont(new Font("Arial", Font.BOLD, 20));
        userTitle.setBounds(150, 20, 500, 40);
        userFrame.add(userTitle);

        JButton backToHomeFromUser = createGradientButton("Back");
        backToHomeFromUser.setBounds(50, 500, 100, 40);
        userFrame.add(backToHomeFromUser);

        DefaultTableModel tableModel = new DefaultTableModel(new String[]{"House No", "Rent (Tk)", "Floor", "Unit", "Details", "Status"}, 0);
        JTable userHouseTable = new JTable(tableModel);
        userHouseTable.setRowHeight(30);
        JScrollPane userScrollPane = new JScrollPane(userHouseTable);
        userScrollPane.setBounds(50, 120, 700, 300);
        userFrame.add(userScrollPane);

        JTextField searchField = new JTextField();
        searchField.setBounds(50, 80, 500, 30);
        userFrame.add(searchField);

        JButton searchButton = createGradientButton("Search");
        searchButton.setBounds(570, 80, 100, 30);
        userFrame.add(searchButton);

        // Populate table initially with all houses
        tableModel.setRowCount(0);
        for (House house : houseList) {
            tableModel.addRow(new Object[]{house.houseNo, house.rent, house.floor, house.unit, house.details, house.about});
        }

        // Search functionality
        searchButton.addActionListener(e -> {
            String searchText = searchField.getText().toLowerCase();
            tableModel.setRowCount(0);
            for (House house : houseList) {
                if (house.houseNo.toLowerCase().contains(searchText) ||
                        house.rent.toLowerCase().contains(searchText) ||
                        house.floor.toLowerCase().contains(searchText) ||
                        house.unit.toLowerCase().contains(searchText)) {
                    tableModel.addRow(new Object[]{house.houseNo, house.rent, house.floor, house.unit, house.details, house.about});
                }
            }
        });

        // "Book House" button
        JButton bookHouseButton = createGradientButton("Book House");
        bookHouseButton.setBounds(600, 500, 150, 40);
        userFrame.add(bookHouseButton);

        bookHouseButton.addActionListener(e -> {
            int selectedRow = userHouseTable.getSelectedRow();
            if (selectedRow != -1) {
                String houseNo = (String) tableModel.getValueAt(selectedRow, 0);
                for (House house : houseList) {
                    if (house.houseNo.equals(houseNo)) {
                        if (house.about.equalsIgnoreCase("Free")) {
                            house.about = "Occupied";
                            tableModel.setValueAt("Occupied", selectedRow, 5);
                            JOptionPane.showMessageDialog(userFrame, "House " + houseNo + " has been successfully booked!", "Success", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(userFrame, "House " + houseNo + " is already occupied!", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                        break;
                    }
                }
            } else {
                JOptionPane.showMessageDialog(userFrame, "Please select a house to book!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        backToHomeFromUser.addActionListener(e -> {
            userFrame.dispose();
            homeFrame.setVisible(true);
        });

        userFrame.setVisible(true);
    }

    private static JButton createGradientButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(new Color(100, 149, 237));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        return button;
    }
}