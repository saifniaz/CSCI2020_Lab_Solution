package sample;

public class StudentRecord {
    private String sid = "";
    private float assignments = 0f;
    private float midterm = 0f;
    private float finalExam = 0f;
    private float finalMark = 0f;
    private String letterGrade = "";

    public StudentRecord(String sid, float assignments, float midterm, float finalExam) {
        this.sid = sid;
        this.assignments = assignments;
        this.midterm = midterm;
        this.finalExam = finalExam;
        this.finalMark = (assignments * 0.2f) + (midterm * 0.3f) + (finalExam * 0.5f);

        if (this.finalMark < 50) {
            this.letterGrade = "F";
        } else if (this.finalMark < 60) {
            this.letterGrade = "D";
        } else if (this.finalMark < 70) {
            this.letterGrade = "C";
        } else if (this.finalMark < 80) {
            this.letterGrade = "B";
        } else {
            this.letterGrade = "A";
        }
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public float getAssignments() {
        return assignments;
    }

    public void setAssignments(float assignments) {
        this.assignments = assignments;
    }

    public float getMidterm() {
        return midterm;
    }

    public void setMidterm(float midterm) {
        this.midterm = midterm;
    }

    public float getFinalExam() {
        return finalExam;
    }

    public void setFinalExam(float finalExam) {
        this.finalExam = finalExam;
    }

    public float getFinalMark() {
        return finalMark;
    }

    public String getLetterGrade() {
        return letterGrade;
    }
}
