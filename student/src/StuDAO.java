
import java.sql.Connection;
import java.sql.DriverManager;
 import java.sql.PreparedStatement;
 import java.sql.ResultSet;
 import java.sql.SQLException;
 import java.sql.Statement;


public class StuDAO implements Dao{
    public StuDAO(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch(ClassNotFoundException e){
            e.printStackTrace();
        }


    }
    public static Connection getConnection() throws SQLException {
        System.out.println("链接成功");
        return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/stu?characterEncoding=UTF-8", "root",
                "root101");
    }


    @Override
    public  void add(Student stu) {
        String sql = "insert into student values(null,?,?,?)";
        //System.out.println("add");
        try(Connection c = getConnection();PreparedStatement ps = c.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);){
            ps.setString(1,stu.name);
            ps.setString(2,stu.birDate);
            ps.setInt(3,stu.gender);
            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();//id 并返回结果
            if(rs.next()){
                int id = rs.getInt(1);
                stu.ID = id;
            }

        }catch (SQLException e) {

            e.printStackTrace();
        }

    }

    @Override
    public void update(Student stu) {
        String sql = "update student set name =?, birDate=?, gender=? where ID =?";
        try(Connection c = getConnection();PreparedStatement ps = c.prepareStatement(sql);){

            ps.setString(1,stu.getName());
            ps.setString(2,stu.getBirDate());
            ps.setInt(3,stu.getGender());
            ps.setInt(4,stu.getID());
            //ps.execute();
            ps.executeUpdate();

        }catch (SQLException e) {

            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "delete from  student where id = "+ id;
        try(Connection c = getConnection();PreparedStatement ps = c.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);){

            ps.execute(sql);

        }catch (SQLException e) {

            e.printStackTrace();
        }
    }



    @Override
    public Student get(int id) {//查找
        Student s1  = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

        }
        catch(ClassNotFoundException e){
            e.printStackTrace();
        }
        try (Connection c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/stu?characterEncoding=UTF-8","root", "root101");
             Statement s = c.createStatement();){
            //System.out.println("get it!");
            String sql = "select * from student where id = " + id;
            ResultSet rs = s.executeQuery(sql);
            if(rs.next()){
                s1 = new Student();

                String name = rs.getString(2);
                //System.out.println(name);
               String birDate = rs.getString(3);
                //System.out.println(birDate);
                int gender = rs.getInt(4);
                //System.out.println(gender);
                //获取值 并赋值给对象
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
