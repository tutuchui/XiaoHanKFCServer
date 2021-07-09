package com.conehanor.kfcserver.entity;

public class NamePair {
    private String eName;
    private String pName;

    public String geteName() {
        return eName;
    }

    public void seteName(String eName) {
        this.eName = eName;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public NamePair(String eName, String pName) {
        this.eName = eName;
        this.pName = pName;
    }
}
