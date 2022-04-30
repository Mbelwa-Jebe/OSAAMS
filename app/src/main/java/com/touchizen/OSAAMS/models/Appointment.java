package com.touchizen.OSAAMS.models;

public class Appointment {

    private String request_info,appointment_id;
    private String response;
    private String student_fname;
    private String student_lname;
    private String advisor_fname,student_id;
    private String advisor_lname,advisor_id;
    private String timestamp;

    public String getRequest_info() {
        return request_info;
    }

    public void setRequest_info(String request_info) {
        this.request_info = request_info;
    }

    public String getAppointment_id() {
        return appointment_id;
    }

    public void setAppointment_id(String appointment_id) {
        this.appointment_id = appointment_id;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
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

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getAdvisor_lname() {
        return advisor_lname;
    }

    public void setAdvisor_lname(String advisor_lname) {
        this.advisor_lname = advisor_lname;
    }

    public String getAdvisor_id() {
        return advisor_id;
    }

    public void setAdvisor_id(String advisor_id) {
        this.advisor_id = advisor_id;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}


