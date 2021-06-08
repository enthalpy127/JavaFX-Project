package Application;

public class Student {
    private int no;
    private String name;
    private int id;
    private int myanmar;
    private int english;
    private int mathematics;
    private int physics;
    private int chemistry;
    private int biology;
    private int totalmarks;
    private int rollno;

    public Student(){

    }

    public Student(int no, String name, int id, int myanmar, int english, int mathematics, int physics, int chemistry, int biology, int totalmarks, int rollno) {
        this.no = no;
        this.name = name;
        this.id = id;
        this.myanmar = myanmar;
        this.english = english;
        this.mathematics = mathematics;
        this.physics = physics;
        this.chemistry = chemistry;
        this.biology = biology;
        this.totalmarks = totalmarks;
        this.rollno = rollno;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMyanmar(int myanmar) {
        this.myanmar = myanmar;
    }

    public void setEnglish(int english) {
        this.english = english;
    }

    public void setMathematics(int mathematics) {
        this.mathematics = mathematics;
    }

    public void setPhysics(int physics) {
        this.physics = physics;
    }

    public void setChemistry(int chemistry) {
        this.chemistry = chemistry;
    }

    public void setBiology(int biology) {
        this.biology = biology;
    }

    public void setTotalmarks(int totalmarks) {
        this.totalmarks = totalmarks;
    }

    public void setRollno(int rollno) {
        this.rollno = rollno;
    }

    public int getNo() {
        return no;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int getMyanmar() {
        return myanmar;
    }

    public int getEnglish() {
        return english;
    }

    public int getMathematics() {
        return mathematics;
    }

    public int getPhysics() {
        return physics;
    }

    public int getChemistry() {
        return chemistry;
    }

    public int getBiology() {
        return biology;
    }

    public int getTotalmarks() {
        return totalmarks;
    }

    public int getRollno() {
        return rollno;
    }


}
