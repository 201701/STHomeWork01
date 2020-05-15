
import java.sql.SQLException;
import java.util.Scanner;

//学生信息管理系统的菜单选择
public class StudentManager {
    public void  menu() throws SQLException {
                //1.打印菜单
                //2.输入菜单
                //3.switch菜单选择

                //int choose; 问题 3.2
                int choose = 0;
                do {
                    System.out.println("******************************");
                    System.out.println("=======欢迎进入学生信息管理系统=======");
                    System.out.println("1.新增学生");
                    System.out.println("2.查询学生");
                    System.out.println("3.删除学生");
                    System.out.println("4.修改学生");
                    System.out.println("5.退出该系统");
                    System.out.println("请选择（1-5）：");

                    Scanner scanner=new Scanner(System.in);
                    choose=scanner.nextInt();
                    System.out.println("******************************");
                    switch (choose) {
                        case 1:
                            myAdd(); //菜单选择1，是新增学生
                            break;
                        case 2:
                            myList();  //菜单选择2，是查询学生
                            break;
                        case 3:
                            myDel();  //菜单选择3，是删除学生
                            break;
                        case 4:
                            myUpdate();  //菜单选择4，是修改学生

                            break;
                        case 5:     //菜单选择5，是退出该系统
                            System.out.println("您选择了退出系统，确定要退出吗？(y/n)");
                            Scanner scan=new Scanner(System.in);
                            String scanExit=scan.next();
                            if(scanExit.equals("y")) {//问题 3.5
                                System.exit(-1);
                                System.out.println("您已成功退出系统，欢迎您再次使用！");
                            }
                            break;
                        default:
                            break;
                    }
                } while (choose!=5);
            }

     //新增学生信息
    public void myAdd() throws SQLException {//问题 6.7
                //String continute;  问题 1.1
                String conti;
                do {
                    Scanner s=new Scanner(System.in);
                    String name;
                    String birth;
                    int no = 0;//问题 3.2
                    int gend;
                    System.out.println("====新增学生====");
                    System.out.println("学号：");

                    // no= Integer.parseInt(s.next());
                    //System.out.println(no);
                    //问题 ： 6. 2  6.14
                    String noid = s.next();
                    while(noid.length()>10) {//对学号合法性进行判断
                        System.out.println("非法学号,请重新输入");
                        noid = s.next();
                    }

                    no= Integer.parseInt(noid);
                    System.out.println(no);


                    System.out.println("姓名：");
                    name=s.next();
                    System.out.println(name);
                    System.out.println("生日：");
                    birth=s.next();
                    System.out.println(birth);
                    System.out.println("性别：");
                    gend= Integer.parseInt(s.next());
                    System.out.println(gend);

                    Student stu=new Student(no,name,birth,gend);
                    StuDAO dao = new StuDAO();
                    dao.add(stu);
                    System.out.println("保存成功！");
                    System.out.println("是否继续添加(y/n)：");
                    Scanner scanner2=new Scanner(System.in);
                    conti = scanner2.next();
                } while (conti.equals("y"));
    }

    //查询学生信息
    public void myList() throws SQLException {//问题 3.5 //问题 6.7
        Scanner s=new Scanner(System.in);
        int  no;
        System.out.println("请输入要查询的学生学号：");


        no= Integer.parseInt(s.next());

        System.out.println("************************");
        System.out.println("====查询学生====");

        System.out.println("该学生的信息如下：");

        StuDAO stuDao=new StuDAO();
        Student l = stuDao.get(no);
        if(l!=null) {//问题 3.5
            System.out.println(l.getName()+"\t"+l.getBirDate()+"\t"+l.getGender());

        }
        else {
            System.out.println("not Found");

        }

        System.out.println("************************");
    }

    //删除学生信息
    public void myDel() throws SQLException {//问题 3.5 //问题 6.7
        Scanner s=new Scanner(System.in);
        int no;
        System.out.println("====删除学生====");
        System.out.println("请输入要删除的学生学号：");


        no= Integer.parseInt(s.next());

        System.out.println("该学生的信息如下：");

        StuDAO stuDao=new StuDAO();

        System.out.println("学生姓名："+stuDao.get(no).getName());
        System.out.println("学生生日："+stuDao.get(no).getBirDate());
        System.out.println("学生性别："+stuDao.get(no).getGender());

        System.out.println("是否真的删除(y/n)：");
        Scanner scanner3=new Scanner(System.in);
        String x=scanner3.next();
        if (x.equals("y")) {
            Student stu=new Student(no,null,null,0);
            StuDAO dao=new StuDAO();
            dao.delete(no);
            System.out.println("删除成功！");

        }
    }

    //修改学生信息
    public void myUpdate() throws SQLException {//问题 3.5 //问题 6.7
        Scanner s=new Scanner(System.in);
        int no;

        System.out.println("====修改学生====");
        System.out.println("请输入要修改的学生学号：");

        no= Integer.parseInt(s.next());
        System.out.println("该学生的信息如下：");
        StuDAO stuDao=new StuDAO();
        System.out.println("学生姓名："+stuDao.get(no).getName());
        System.out.println("学生生日："+stuDao.get(no).getBirDate());
        System.out.println("学生性别："+stuDao.get(no).getGender());

        System.out.println("请输入新的学生信息：");
        Scanner stuUp=new Scanner(System.in);
        String name0;
        String birth;
        int gend;

        System.out.println("学生姓名：");
        name0=stuUp.next();
        System.out.println("学生生日：");
        birth=stuUp.next();
        System.out.println("学生性别：");
        gend= Integer.parseInt(stuUp.next());

        Student stu=new Student(no,name0,birth,gend);
        StuDAO dao = new StuDAO();

        dao.update(stu);

        System.out.println("保存成功！");

    }

}





