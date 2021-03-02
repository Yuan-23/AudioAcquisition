package com.example.audioacquisition.Study.bean;

public class OtherBean {
    private String otherimage;
    private String othercontent;
    private String othername;
    private int oid;

    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    public OtherBean(String otherimage, String othercontent, String othername) {
        this.otherimage = otherimage;
        this.othercontent = othercontent;
        this.othername = othername;
    }

    public String getOtherimage() {
        return otherimage;
    }

    public void setOtherimage(String otherimage) {
        this.otherimage = otherimage;
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
