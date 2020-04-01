package com.fendy.covid19.model;

import java.io.Serializable;

public class CovidIndonesia implements Serializable {

    private String FID;
    private String codeProv;
    private String prov;
    private String casePositive;
    private String caseGetWell;
    private String caseDead;

    public String getFID() {
        return FID;
    }

    public void setFID(String FID) {
        this.FID = FID;
    }

    public String getCodeProv() {
        return codeProv;
    }

    public void setCodeProv(String codeProv) {
        this.codeProv = codeProv;
    }

    public String getProv() {
        return prov;
    }

    public void setProv(String prov) {
        this.prov = prov;
    }

    public String getCasePositive() {
        return casePositive;
    }

    public void setCasePositive(String casePositive) {
        this.casePositive = casePositive;
    }

    public String getCaseGetWell() {
        return caseGetWell;
    }

    public void setCaseGetWell(String caseGetWell) {
        this.caseGetWell = caseGetWell;
    }

    public String getCaseDead() {
        return caseDead;
    }

    public void setCaseDead(String caseDead) {
        this.caseDead = caseDead;
    }
}
