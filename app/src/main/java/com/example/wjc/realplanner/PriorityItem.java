package com.example.wjc.realplanner;

/**
 * Created by WJC on 2017-07-24.
 */

public class PriorityItem {
    int prioritynum;
    String date;
    String memo;

    public PriorityItem(int prioritynum, String date, String memo) {
        this.prioritynum = prioritynum;
        this.date = date;
        this.memo = memo;
    }

    public int getPrioritynum() {
        return prioritynum;
    }

    public void setPrioritynum(int prioritynum) {
        this.prioritynum = prioritynum;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}
