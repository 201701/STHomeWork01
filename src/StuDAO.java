
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class StuDAO implements Dao {//问题 3.5
    public StuDAO() throws SQLException {//问题 3.5
        try {//问题 3.5
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch(ClassNotFoundException e) {//问题 3.5
            e.printStackTrace();
        }


    }
    private static final String URL="jdbc:mysql://127.0.0.1:3306/stu?characterEncoding=UTF-8";
    private static final String USER="root";
    private static final String PASSWORD="root101";
    public static Connection getConnection() throws SQLException {
        System.out.println("链接成功");
//        return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/stu?characterEncoding=UTF-8", "root",
//                "root101");
        //问题 3.6
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }



    @Override //增加学生 问题 2.5
    public  void add(Student stu) {
        String sql = "insert into student values(null,?,?,?)";


        try(Connection c = getConnection();PreparedStatement ps = c.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);){
            ps.setString(1,stu.name);
            ps.setString(2,stu.birDate);
            ps.setInt(3,stu.gender);
            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();//问题2.1
            if(rs.next()) {//问题 3.5
                int id = rs.getInt(1);
                stu.ID = id;
            }

        }catch (SQLException e) {

            e.printStackTrace();
        }

    }

    @Override //修改学生信息 问题 2.5
    public void update(Student stu) {
        String sql = "update student set name =?, birDate=?, gender=? where ID =?";
        try(Connection c = getConnection();PreparedStatement ps = c.prepareStatement(sql);){

            ps.setString(1,stu.getName());
            ps.setString(2,stu.getBirDate());
            ps.setInt(3,stu.getGender());
            ps.setInt(4,stu.getID());
            //ps.execute(); 问题2.1
            ps.executeUpdate();

        }catch (SQLException e) {

            e.printStackTrace();
        }
    }

    @Override //删除学生 问题 2.5
    public void delete(int id) {
        String sql = "delete from  student where id = "+ id;
        try(Connection c = getConnection();PreparedStatement ps = c.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);){

            ps.execute(sql);

        }catch (SQLException e) {

            e.printStackTrace();
        }
    }



    @Override //查找学生 问题 2.5
    public Student get(int id) {//查找
        Student s1  = null;
        //问题：7.4 7.5
//        try {//问题 3.5
//            Class.forName("com.mysql.cj.jdbc.Driver");
//
//        }
//        catch(ClassNotFoundException e) {//问题 3.5
//            e.printStackTrace();
//        }
        try (Connection c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/stu?characterEncoding=UTF-8","root", "root101");
             Statement s = c.createStatement();) {//问题 3.5
            //System.out.println("get it!"); 问题2.1
            String sql = "select * from student where id = " + id;
            ResultSet rs = s.executeQuery(sql);
            if(rs.next()) {//问题 3.5
                s1 = new Student();

                String name = rs.getString(2);
                //System.out.println(name); 问题2.1
               String birDate = rs.getString(3);
                //System.out.println(birDate); 问题2.1
                int gender = rs.getInt(4);
                //System.out.println(gender); 问题2.1
                //获取属性值 并赋值给对象
                s1.name = name;
                s1.birDate = birDate;
                s1.gender = gender;

            }
        }catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return s1;//返回对象

    }


}
