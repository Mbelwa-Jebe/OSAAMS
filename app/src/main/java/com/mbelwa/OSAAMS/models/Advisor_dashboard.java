package com.mbelwa.OSAAMS.models;

public class Advisor_dashboard {
    public String student_count;
    public String appointment_count;
    public String report_count;
    public String advisor_id;


    public String getAdvisor_id() {
        return advisor_id;
    }

    public void setAdvisor_id(String advisor_id) {
        this.advisor_id = advisor_id;
    }

    public String getStudent_count() {
        return student_count;
    }

    public void setStudent_count(String student_count) {
        this.student_count = student_count;
    }

    public String getAppointment_count() {
        return appointment_count;
    }

    public void setAppointment_count(String appointment_count) {
        this.appointment_count = appointment_count;
    }

    public String getReport_count() {
        return report_count;
    }

    public void setReport_count(String report_count) {
        this.report_count = report_count;
    }
}
