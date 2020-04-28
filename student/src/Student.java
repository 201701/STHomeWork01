public class Student {
    public int ID;
    public String name;
    public String birDate;
    public int gender;
    public Student() {

        // TODO Auto-generated constructor stub
    }
    public Student(int no, String name, String birth, int gend) {
        this.ID = no;
        this.name = name;
        this.birDate = birth;
        this.gender = gend;
        //System.out.println("here:"+this.ID+this.name+this.birDate+this.gender);
    }

    public Student(String name, String birth, int gend) {
        this.name = name;
        this.birDate = birth;
        this.gender = gend;
    }

    public Student(int no, String name, int gend) {
        this.ID = no;
        this.name = name;
        //this.birDate = birth;
        this.gender = gend;
    }

    public Student(int no, String name) {
        this.ID = no;
        this.name = name;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirDate() {
        return birDate;
    }

    public void setBirDate(String birDate) {
        this.birDate = birDate;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }
}
