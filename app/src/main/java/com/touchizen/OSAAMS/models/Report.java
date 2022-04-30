package com.touchizen.OSAAMS.models;

public class Report {
    private String consultation_id;
    private String advisor_id;
    private String student_id;
    private String student_fname;
    private String student_lname;
    private String advisor_fname;
    private String advisor_lname;
    private String report;
    private String report_date;

    public String getConsultation_id() {
        return consultation_id;
    }

    public void setConsultation_id(String consultation_id) {
        this.consultation_id = consultation_id;
    }

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

    public String getAdvisor_fname() {
        return advisor_fname;
    }

    public void setAdvisor_fname(String advisor_fname) {
        this.advisor_fname = advisor_fname;
    }

    public String getAdvisor_lname() {
        return advisor_lname;
    }

    public void setAdvisor_lname(String advisor_lname) {
        this.advisor_lname = advisor_lname;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }

    public String getReport_date() {
        return report_date;
    }

    public void setReport_date(String report_date) {
        this.report_date = report_date;
    }
}
