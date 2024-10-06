/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.util;

import java.sql.*;

/**
 *
 * @author rubia
 */
public class SequenceManager {
    public static int getValue(String sequenceName) throws SQLException {
        int nextValue = 0;
        String sql = "SELECT NEXT VALUE FOR " + sequenceName + ";";

        try {
            Connection db = ConectorBD.getConnection();
            PreparedStatement ps = db.prepareStatement(sql);
            ResultSet result = ps.executeQuery();

            if (result.next()) {
                return result.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("Erro ao obter sequẽncia: " + e);
        }
        return nextValue;
    }
}
