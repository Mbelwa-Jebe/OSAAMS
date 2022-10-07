package com.mbelwa.OSAAMS.models;

public class Advisor_threads {
    private String student_fname;
    private String student_lname;
    private String content;
    private String student_id;
    private String advisor_id;

    public String getAdvisor_id() {
        return advisor_id;
    }

    public void setAdvisor_id(String advisor_id) {
        this.advisor_id = advisor_id;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getStudent_fname() {
        return student_fname;
    }

    public void setStudent_fname(String student_fname) {
        this.student_fname = student_fname;
    }

    public String getStudent_lname() {
        return student_lname;
    }

    public void setStudent_lname(String student_lname) {
        this.student_lname = student_lname;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
