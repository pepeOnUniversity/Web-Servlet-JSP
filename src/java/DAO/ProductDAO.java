/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.*;
import java.util.*;
import model.Product;
import util.DBUtil;

/**
 *
 * @author ADMIN
 */
public class ProductDAO {

    public List<Product> getAll() throws SQLException {
        //create listProducts to store list products fromDB
        List<Product> listProducts = new ArrayList();
        //sql
        String sql = "SELECT * FROM Products";
        //try connect and get DB
        try (Connection conn = DBUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            //get DB into listProducts
            while (rs.next()) {
                //add DB into list
                listProducts.add(new Product(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getString("description")));
            }
        }
        return listProducts;
    }

    //add a new product into DB
    public void add(Product product) throws SQLException {
        //sql
        String sql = "INSERT INTO Products(name, price, description)"
                + "VALUES (?, ?, ?)";
        //try connection
        try (Connection conn = DBUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            //set data
            ps.setString(1, product.getName());
            ps.setDouble(2, product.getPrice());
            ps.setString(3, product.getDescription());
            //execute
            ps.executeUpdate();
        }
    }

    //delete a product by id
    public void delete(int id) throws SQLException {
        //sql
        String sql = "DELETE FROM Products WHERE id = ?";
        //try connection
        try (Connection conn = DBUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            //set id
            ps.setInt(1, id);
            //execute
            ps.executeUpdate();
        }
    }

    //update a product
    public void update(Product product) throws SQLException {
        //sql
        String sql = "UPDATE Products SET name=?, price=?, description=?"
                + "WHERE id = ?";
        //try connection
        try (Connection conn = DBUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            //set parameters
            ps.setString(1, product.getName());
            ps.setDouble(2, product.getPrice());
            ps.setString(3, product.getDescription());
            ps.setInt(4, product.getId());
            //execute
            ps.executeUpdate();
        }
    }

    public Product getById(int id) throws SQLException {
        String sql = "SELECT * FROM Products WHERE id = ?";
        try (Connection conn = DBUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Product(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getDouble("price"),
                            rs.getString("description")
                    );
                }
            }
        }
        return null;
    }
}
