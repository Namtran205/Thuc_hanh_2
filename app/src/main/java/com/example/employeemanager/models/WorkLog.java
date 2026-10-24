package com.example.employeemanager.models;

public class WorkLog {
    private int id;
    private int employeeId;
    private String workDate;      // "yyyy-MM-dd"
    private String checkInTime;   // "HH:mm:ss"
    private String checkOutTime;  // "HH:mm:ss", null nếu chưa check-out
    private double totalHours;
    private String status;        // Constants.WL_STATUS_*

    public WorkLog() {
    }

    public WorkLog(int id, int employeeId, String workDate, String checkInTime,
                   String checkOutTime, double totalHours, String status) {
        this.id = id;
        this.employeeId = employeeId;
        this.workDate = workDate;
        this.checkInTime = checkInTime;
        this.checkOutTime = checkOutTime;
        this.totalHours = totalHours;
        this.status = status;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getEmployeeId() { return employeeId; }
    public void setEmployeeId(int employeeId) { this.employeeId = employeeId; }

    public String getWorkDate() { return workDate; }
    public void setWorkDate(String workDate) { this.workDate = workDate; }

    public String getCheckInTime() { return checkInTime; }
    public void setCheckInTime(String checkInTime) { this.checkInTime = checkInTime; }

    public String getCheckOutTime() { return checkOutTime; }
    public void setCheckOutTime(String checkOutTime) { this.checkOutTime = checkOutTime; }

    public double getTotalHours() { return totalHours; }
    public void setTotalHours(double totalHours) { this.totalHours = totalHours; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
