public interface Dao {
    //增加
    public void add(Student stu);

    //修改
    public void update(Student stu);

    //删除
    public void delete(int id);

    //查找
    public Student get(int id);



}
