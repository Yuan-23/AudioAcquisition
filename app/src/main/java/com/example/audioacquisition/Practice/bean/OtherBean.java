package com.example.audioacquisition.Practice.bean;

public class OtherBean {
    private String othervideo;
    private String othercontent;
    private String othername;

    public OtherBean(String othervideo, String othercontent, String othername) {
        this.othervideo = othervideo;
        this.othercontent = othercontent;
        this.othername = othername;
    }

    public OtherBean() {
    }

    public String getOthervideo() {
        return othervideo;
    }

    public void setOthervideo(String othervideo) {
        this.othervideo = othervideo;
    }

    public String getOthercontent() {
        return othercontent;
    }

    public void setOthercontent(String othercontent) {
        this.othercontent = othercontent;
    }

    public String getOthername() {
        return othername;
    }

    public void setOthername(String othername) {
        this.othername = othername;
    }
}
