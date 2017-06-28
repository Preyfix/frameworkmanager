package com.mycompany.framework_manager;

import java.sql.*;

/**
 * The model. Called from the controller and notifies the view about any changes.
 *
 * @author Martin
 */
public class Model extends java.util.Observable {

    private Connection conn;
    private PreparedStatement stmt;

    private ResultSet rs;
    private String table;

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/fm";

    static final String USERNAME = "admin";
    static final String PASSWORD = "admin";

    /**
     * Registers JDBC driver and opens a connection to the database.
     */
    public Model() {

        conn = null;
        stmt = null;

        try {

            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void translateResultSet() {
        StringBuilder sb = new StringBuilder();

        try {
            sb.append("ID - Name - Type - Language - Intrusive - Open Source\n");
            while (rs.next()) {

                int id = rs.getInt("id");
                String name = rs.getString("name");
                String type = rs.getString("type");
                String language = rs.getString("language");
                String intrusive = rs.getString("intrusive");
                String openSource = rs.getString("open_source");

                sb.append(id + " - " + name + " - " + type + " - " + language + " - " + intrusive + " - " + openSource + "\n");
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    /**
     * Fetches the table sorted on row ID.
     */
    public void read() {
        try {
            stmt = conn.prepareStatement("SELECT * FROM fm ORDER BY id");

            ResultSet rs = stmt.executeQuery();

        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        translateResultSet();

        setChanged();
        notifyObservers(table);
    }

    /**
     * Fetches the table sorted on 'fieldName'.
     *
     * @param fieldName Name of the field in the table.
     */
    public void sort(String fieldName) {
        try {
            stmt = conn.prepareStatement("SELECT * FROM fm ORDER BY ?");
            stmt.setString(1, fieldName);

            ResultSet rs = stmt.executeQuery();

        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        translateResultSet();

        setChanged();
        notifyObservers(table);
    }

    /**
     * Inserts a new row based on information stored in object 'e'.
     *
     * @param o Object with information to be put in all fields in the new row.
     */
    public void add(FrameworkTemplate fmt) { // Fundera på hur nya raden som kommer ska vara formaterad. Någon typ av lista eller ett eget objekt?
        try {
            stmt = conn.prepareStatement("INSERT INTO fm(id,name,type,language,intrusive,open_source) VALUES(?, ?, ?, ?, ?, ?)");
            stmt.setString(1, fmt.getId());
            stmt.setString(2, fmt.getName());
            stmt.setString(3, fmt.getType());
            stmt.setString(4, fmt.getLanguage());
            stmt.setString(5, fmt.getIntrusive());
            stmt.setString(6, fmt.getOpenSource());
            stmt.executeUpdate();

            stmt = conn.prepareStatement("SELECT * FROM fm ORDER BY id");
            ResultSet rs = stmt.executeQuery();

        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        translateResultSet();

        setChanged();
        notifyObservers(table);
    }

    /**
     * Deletes the row where id = 'id'.
     *
     * @param id Row ID.
     */
    public void remove(String id) {
        try {
            stmt = conn.prepareStatement("DELETE FROM fm WHERE id='?'");
            stmt.setString(1, id);
            stmt.executeUpdate();

            stmt = conn.prepareStatement("SELECT * FROM fm ORDER BY id");
            ResultSet rs = stmt.executeQuery();

        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        translateResultSet();

        setChanged();
        notifyObservers(table);
        translateResultSet();

        setChanged();
        notifyObservers(table);
    }

    /**
     * Puts 'data' in the field 'fieldName' where row ID = 'id'.
     *
     * @param fieldName Name of the field to update.
     * @param data New information to store in the field.
     * @param id Row ID.
     */
    public void update(String fieldName, String data, String id) {
        try {
            stmt = conn.prepareStatement("UPDATE fm SET ? = '?' WHERE id = ?");
            stmt.setString(1, fieldName);
            stmt.setString(2, data);
            stmt.setString(3, id);
            stmt.executeUpdate();

            stmt = conn.prepareStatement("SELECT * FROM fm ORDER BY id");
            ResultSet rs = stmt.executeQuery();

        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        translateResultSet();

        setChanged();
        notifyObservers(table);
    }

    /**
     * Get a recent ResultSet of the table in the database.
     */
    public void setTable() {
        try {
            stmt = conn.prepareStatement("SELECT * FROM fm ORDER BY id");

            ResultSet rs = stmt.executeQuery();

        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        translateResultSet();
    }

    /**
     * Closes the connection to the database.
     */
    public void closeJDBC() {
        try {
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
}
