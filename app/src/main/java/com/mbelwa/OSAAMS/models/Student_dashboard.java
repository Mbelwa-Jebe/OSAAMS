package com.mbelwa.OSAAMS.models;

public class Student_dashboard {
    public String dash_advisor_fname;
    public String dash_advisor_lname;
    public String dash_consultations;
    public String dash_student_ID;
    public String dash_appointments_received;

    public String getDash_student_ID() {
        return dash_student_ID;
    }

    public void setDash_student_ID(String dash_student_ID) {
        this.dash_student_ID = dash_student_ID;
    }

    public String getDash_advisor_fname() {
        return dash_advisor_fname;
    }

    public void setDash_advisor_fname(String dash_advisor_fname) {
        this.dash_advisor_fname = dash_advisor_fname;
    }

    public String getDash_advisor_lname() {
        return dash_advisor_lname;
    }

    public void setDash_advisor_lname(String dash_advisor_lname) {
        this.dash_advisor_lname = dash_advisor_lname;
    }

    public String getDash_consultations() {
        return dash_consultations;
    }

    public void setDash_consultations(String dash_consultations) {
        this.dash_consultations = dash_consultations;
    }

    public String getDash_appointments_received() {
        return dash_appointments_received;
    }

    public void setDash_appointments_received(String dash_appointments_received) {
        this.dash_appointments_received = dash_appointments_received;
    }
}
