package com.example.phoneapplication;

import android.graphics.Color;

import java.util.ArrayList;
import java.util.List;

public class Operateur {

    //var
    private String name;
    private int codeLenght;
    private String prefixRecharge;
    private String soldeConsulterCode;
    private int color;

    public Operateur(String name, int codeLenght, String prefixRecharge, String soldeConsulterCode, int color) {
        this.name = name;
        this.codeLenght = codeLenght;
        this.prefixRecharge = prefixRecharge;
        this.soldeConsulterCode = soldeConsulterCode;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCodeLenght() {
        return codeLenght;
    }

    public void setCodeLenght(int codeLenght) {
        this.codeLenght = codeLenght;
    }

    public String getPrefixRecharge() {
        return prefixRecharge;
    }

    public void setPrefixRecharge(String prefixRecharge) {
        this.prefixRecharge = prefixRecharge;
    }

    public String getSoldeConsulterCode() {
        return soldeConsulterCode;
    }

    public void setSoldeConsulterCode(String soldeConsulterCode) {
        this.soldeConsulterCode = soldeConsulterCode;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

//################################################################
    //################################################################

    static List<Operateur> getOperateurs(){
        List<Operateur> operateurs = new ArrayList<>();

        operateurs.add(new Operateur("Orange", 14, "*100*", "*111#", R.color.orange));
        operateurs.add(new Operateur("Ooredoo", 14, "*101*", "*100#", R.color.red));
        operateurs.add(new Operateur("Tunisie Telecom", 13, "*123*", "*122#", R.color.blue));

        return operateurs;
    }

}
