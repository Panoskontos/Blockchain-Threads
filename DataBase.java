import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DataBase {

    private Connection conn = this.connect();
    public void createNewTable(String newname) {
        String url = "jdbc:sqlite:src/sample.db";

        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS " +newname+ " (\n"
                + " id integer PRIMARY KEY,\n"
                + " previousHash text NOT NULL,\n"
                + " AA real,\n"
                + " code text NOT NULL,\n"
                + " title text NOT NULL,\n"
                + " timestamp long, \n"
                + " price real, \n"
                + " description text, \n"
                + " category text, \n"
                + " previousAA real, \n"
                + " hash text \n"
                + ");";

        try{
            Connection conn = this.conn;
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    private Connection connect() {

        String url = "jdbc:sqlite:src/sample.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
    public void insert(String tablename,
                       String previousHash,
                       int AA,
                       String code,
                       String title,
                       long timestamp,
                       int price,
                       String description,
                       String category,
                       int previousAA,
                       String hash
                       ) {
        String sql = "INSERT INTO "+tablename+"(previousHash,AA,code,title,timestamp,price,description,category,previousAA,hash) VALUES(?,?,?,?,?,?,?,?,?,?)";

        try{
            Connection conn = this.conn;
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, previousHash);
            pstmt.setInt(2, AA);
            pstmt.setString(3, code);
            pstmt.setString(4, title);
            pstmt.setLong(5, timestamp);
            pstmt.setInt(6, price);
            pstmt.setString(7, description);
            pstmt.setString(8, category);
            pstmt.setInt(9, previousAA);
            pstmt.setString(10,hash);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void selectAll(String newname){
        String sql = "SELECT * FROM "+newname+";";

        try {
            Connection conn = this.conn;
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);

            // loop through the result set
            while (rs.next()) {
                System.out.println(rs.getInt("id")+"\t");}
//                        +  "\t" +
//                        rs.getString("name") + "\t" +
//                        rs.getDouble("capacity"));

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public int countAll(String newname){
//        String sql = "SELECT COUNT(*) FROM "+newname+") AS count" +";";

        try {
            Connection conn = this.conn;
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery("SELECT COUNT(*) AS count FROM block ");
            System.out.println("Table blocks has:"+rs.getInt("count")+" rows");
            return rs.getInt("count");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }
}
