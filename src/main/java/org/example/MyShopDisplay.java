package org.example;

import java.sql.*;

public class MyShopDisplay {
    private static Connection con;

    public MyShopDisplay() {
        con = MyShopUpload.getCon();
    }

    public void customerDetails(String customer_id) {
        try {
            String sql = "SELECT customer_name, phone_number, address FROM CUSTOMER WHERE customer_code = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, customer_id);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String customerName = rs.getString("customer_name");
                String phoneNumber = rs.getString("phone_number");
                String address = rs.getString("address");
                System.out.println("Customer Name: " + customerName);
                System.out.println("Phone Number: " + phoneNumber);
                System.out.println("Address: " + address);
            } else {
                System.out.println("Customer not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void displayItems() {
        try {
            String sql = "SELECT item_code, item_name, item_price FROM ITEM";
            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery(sql);

            if (!rs.isBeforeFirst()) {
                System.out.println("No items found.");
                return;
            }

            while (rs.next()) {
                String itemCode = rs.getString("item_code");
                String itemName = rs.getString("item_name");
                double itemPrice = rs.getDouble("item_price");
                System.out.println("Item Code: " + itemCode);
                System.out.println("Item Name: " + itemName);
                System.out.println("Item Price: " + itemPrice);
                System.out.println("-----");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void purchaseDetails(String customer_id) {
        try {
            String sql = "SELECT c.customer_name, i.item_name, p.quantity_pur " +
                    "FROM PURCHASE p " +
                    "JOIN CUSTOMER c ON p.customer_code = c.customer_code " +
                    "JOIN ITEM i ON p.item_code = i.item_code " +
                    "WHERE c.customer_code = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, customer_id);

            ResultSet rs = pstmt.executeQuery();

            if (!rs.isBeforeFirst()) {
                System.out.println("No purchase details found for the customer.");
                return;
            }

            while (rs.next()) {
                String customerName = rs.getString("customer_name");
                String itemName = rs.getString("item_name");
                int quantityPur = rs.getInt("quantity_pur");
                System.out.println("Customer Name: " + customerName);
                System.out.println("Item Name: " + itemName);
                System.out.println("Quantity Purchased: " + quantityPur);
                System.out.println("-----");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
