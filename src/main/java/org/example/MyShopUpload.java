package org.example;

import java.sql.*;

public class MyShopUpload {
    private static Connection con;

    public MyShopUpload() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/myshoppee", "root", "javapro2023@");
            System.out.println("Data connected");
            /*
             * jdbc-protocol
             * mysql-sub protocol
             * localhost-address of mysql
             * 3306-port number of mysql
             */
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static Connection getCon() {
        return con;
    }

    public void storeItemDetails(String itemCode, String itemName, double itemPrice, int qoh) {
        if (itemPrice <= 0 || qoh <= 0) {
            System.out.println("Price and QOH must be greater than 0.");
            return;
        }
        try {
            Connection connection = getCon();
            String sql = "INSERT INTO Item VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, itemCode);
            preparedStatement.setString(2, itemName);
            preparedStatement.setDouble(3, itemPrice);
            preparedStatement.setInt(4, qoh);
            preparedStatement.executeUpdate();
            System.out.println("Item details stored successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public void storeCustomerDetails(String customerCode, String customerName, String phoneNumber, String address) {
        try {
            Connection connection = getCon();
            String sql = "INSERT INTO CUSTOMER VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, customerCode);
            preparedStatement.setString(2, customerName);
            preparedStatement.setString(3, phoneNumber);
            preparedStatement.setString(4, address);
            preparedStatement.executeUpdate();
            System.out.println("Item details stored successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void purchaseDetails(String transactionId, String customerCode, String itemCode, Date dateOfPurchase, int quantityPur) {
        try {
            Connection connection = getCon();
            String sql = "INSERT INTO purchase VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, transactionId);
            preparedStatement.setString(2, customerCode);
            preparedStatement.setString(3, itemCode);
            preparedStatement.setDate(4, dateOfPurchase);
            preparedStatement.setInt(5, quantityPur);
            preparedStatement.executeUpdate();
            System.out.println("Item details stored successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
