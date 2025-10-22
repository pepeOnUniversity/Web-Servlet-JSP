/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import model.User;
import java.sql.*;
import util.DBUtil;
/**
 *
 * @author ADMIN
 */
public class UserDAO {
    public User findByUserName(String username) throws SQLException {
        String sql = "SELECT * FROM USERS WHERE username=?";
        try(Connection conn = DBUtil.getConnection();
                //ps: prepare SQL compile line "sql = ... "
                PreparedStatement ps = conn.prepareStatement(sql)){
            //set the first parameter "?" of sql == username
            ps.setString(1, username);
            //excute code sql 
            ResultSet rs = ps.executeQuery();
            
            //rs return a dimension array 
            //when init, cursor of array at before first element -> need code: rs.next()
            if(rs.next()){
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getString("role"));
                //return user -> LoginServlet receive
                return user;
            }
        }
        return null;
    }
}
