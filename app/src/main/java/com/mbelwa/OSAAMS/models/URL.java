package com.mbelwa.OSAAMS.models;

public class URL {
    private static final String ROOT_URL = "http://169.254.147.149:88/AcademicAdvisor/";
  //  private static final String ROOT_URL = "http://192.168.43.64:88/AcademicAdvisor/";
    public static final String LOGIN_URL = ROOT_URL + "login2.php";
    public static final String GET_AP_URL = ROOT_URL + "get_appointments.php";
    public static final String GET_REPORT_URL = ROOT_URL + "get_reports.php";
    public static final String ADD_AP_STUDENT_URL = ROOT_URL + "insertap1.php";
    public static final String ADD_AP_ADVISOR_URL = ROOT_URL + "insertap2.php";
    public static final String ADD_REPORT_STUDENT_URL = ROOT_URL + "insert_reports_student.php";
    public static final String ADD_REPORT_ADVISOR_URL = ROOT_URL + "insert_reports.php";
    public static final String DELETE_REPORT_URL = ROOT_URL + "delete_report.php";
    public static final String CONFIRM_AP_URL = ROOT_URL + "accept_appointment.php";
    public static final String REJECT_AP_URL = ROOT_URL + "reject_appointment.php";
    public static final String DELETE_AP_URL = ROOT_URL + "delete_appointment.php";
    public static final String GET_STUDENT_URL = ROOT_URL + "get_students.php";
    public static final String GET_ADVISOR_URL = ROOT_URL + "get_advisors.php";
    public static final String GET_FAQS_URL = ROOT_URL + "get_faqs.php";
  public static final String UPDATE_REPORT_URL = ROOT_URL + "update_report.php";
  public static final String STUDENT_DASHBOARD_URL = ROOT_URL + "student_dashboard.php";
  public static final String ADVISOR_DASHBOARD_URL = ROOT_URL + "advisor_dashboard.php";
  public static final String INSERT_MESSAGES_STUDENT_URL = ROOT_URL + "insert_messages_student.php";
  public static final String INSERT_MESSAGES_ADVISOR_URL = ROOT_URL + "insert_messages_advisor.php";
  public static final String GET_MESSAGES_URL = ROOT_URL + "get_messages.php";
  public static final String RESET_PASS_STUDENT = ROOT_URL + "reset_password_student.php";
  public static final String RESET_PASS_ADVISOR = ROOT_URL + "reset_password_advisor.php";

}
