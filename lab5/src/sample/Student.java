package sample;

/**
 * Created by Saif Niaz on 2016-03-13.
 */
public class Student {
    private String sid;
    private float assignment;
    private float midterm;
    private float finalMarks;
    private float finalExam;
    private char letterGrade;

    public Student(String sid, float assignment, float midterm, float finalExam) {
        this.sid = sid;
        this.assignment = assignment;
        this.midterm = midterm;
        this.finalExam = finalExam;
    }

    public String getSid() { return this.sid; }
    public float getAssignment() { return this.assignment; }
    public float getMidterm() { return this.midterm; }
    public float getFinalExam() { return this.finalExam; }

    public float getFinalMarks(){
        this.finalMarks = (float)(0.2f * assignment) +(0.3f * midterm) +(0.5f * finalExam);
        return this.finalMarks;
    }

    public char getLetterGrade() {
        float temp = this.finalMarks;
        if(temp < 50){letterGrade = 'F';
        }else if(temp < 60 && temp >= 50){
            letterGrade = 'D';
        }else if(temp < 70 && temp >= 60){
            letterGrade = 'C';
        }else if(temp < 800 && temp >= 70){
            letterGrade = 'B';
        }else if(temp <= 100 && temp >= 80){
            letterGrade = 'A';
        }

        return letterGrade;
    }


/*public void setSid(int sid) { this.sid = sid; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setGpa(double gpa) { this.gpa = gpa; }*/
}