package com.example.phoneapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {


    //Var
    List<Operateur> operateurs = Operateur.getOperateurs();
    String codeRechargePrefix = "";
    int codeLength = 0;


    //widgets
    private TextView usernameTV;
    //..
    private EditText numTelEt;
    private TextView numTelTypeTV;
    //..
    private TextView codeRechargeTV;
    private EditText codeRechargeET;
    //..
    private EditText rechargerLigneET;
    private Button rechargerLigneBtn;
    //..
    private EditText consulterSoldeET;
    private Button consulterSoldeBtn;


    //Life Cycle
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setupUI(findViewById(R.id.homeParent));

        //init widgets
        usernameTV = findViewById(R.id.usernameTV);

        //labels
        numTelTypeTV = findViewById(R.id.numTelTypeTV);
        codeRechargeTV = findViewById(R.id.codeRechargeTV);

        //edit texts
        numTelEt = findViewById(R.id.numTelET);
        codeRechargeET = findViewById(R.id.codeRechargeET);
        rechargerLigneET = findViewById(R.id.rechargeLigneET);
        consulterSoldeET = findViewById(R.id.ConsulterET);

        //Buttons
        rechargerLigneBtn = findViewById(R.id.rechargerAppel);
        consulterSoldeBtn = findViewById(R.id.ConsulterAppel);

        //init username
        Intent intent = getIntent();
        usernameTV.setText(intent.getStringExtra("username"));

        //Listeners
        numTelEt.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                if (s.length() != 0) {
                    checkOperateur(s.toString());
                }

            }
        });

        //..

        codeRechargeET.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                if (s.length() != 0){
                    rechargerLigneET.setText(codeRechargePrefix + s +"#");
                }

            }
        });

        //..

        rechargerLigneBtn.setOnClickListener(v -> {
            if (numTelEt.getText().length() == 8 && codeRechargeET.getText().length() == codeLength) {
                String phone = rechargerLigneET.getText().toString();
                Intent rechqrgeIntent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
                startActivity(rechqrgeIntent);
            } else {
                Toast.makeText(this, "Veuillez verifier votre numero de telephone ou le code de recharge", Toast.LENGTH_SHORT).show();
            }
        });

        //..

        consulterSoldeBtn.setOnClickListener(v -> {

                String phone = consulterSoldeET.getText().toString();
                Intent soldeIntent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
                startActivity(soldeIntent);

        });
    }


    //Actions
    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        if(inputMethodManager.isAcceptingText()){
            inputMethodManager.hideSoftInputFromWindow(
                    activity.getCurrentFocus().getWindowToken(),
                    0
            );
        }
    }

    public void setupUI(View view) {

        // Set up touch listener for non-text box views to hide keyboard.
        if (!(view instanceof EditText)) {
            view.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    hideSoftKeyboard(HomeActivity.this);
                    return false;
                }
            });
        }

        //If a layout container, iterate over children and seed recursion.
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                View innerView = ((ViewGroup) view).getChildAt(i);
                setupUI(innerView);
            }
        }
    }

    //..

    @SuppressLint("SetTextI18n")
    void checkOperateur(String s) {
        if (s.substring(0).equals("5")) {
            numTelTypeTV.setText("Votre Ligne est " + operateurs.get(0).getName());
            numTelTypeTV.setTextColor(getResources().getColor(operateurs.get(0).getColor()));
            rechargerLigneET.setBackgroundColor((getResources().getColor(operateurs.get(0).getColor())));
            codeRechargePrefix = operateurs.get(0).getPrefixRecharge();
            rechargerLigneET.setText(codeRechargePrefix + codeRechargeET.getText().toString() + "#");
            consulterSoldeET.setBackgroundColor(getResources().getColor(operateurs.get(0).getColor()));
            consulterSoldeET.setText(operateurs.get(0).getSoldeConsulterCode());
            codeRechargeTV.setText("Entrer votre code de recharge (" + operateurs.get(0).getCodeLenght() + " chiffres)");
            codeLength = operateurs.get(0).getCodeLenght();

        } else if (s.substring(0).equals("3")) {

            numTelTypeTV.setText("Votre Ligne est " + operateurs.get(0).getName());
            numTelTypeTV.setTextColor(getResources().getColor(operateurs.get(0).getColor()));
            rechargerLigneET.setBackgroundColor((getResources().getColor(operateurs.get(0).getColor())));
            codeRechargePrefix = operateurs.get(0).getPrefixRecharge();
            rechargerLigneET.setText(codeRechargePrefix + codeRechargeET.getText().toString() + "#");
            consulterSoldeET.setBackgroundColor(getResources().getColor(operateurs.get(0).getColor()));
            consulterSoldeET.setText(operateurs.get(0).getSoldeConsulterCode());
            codeRechargeTV.setText("Entrer votre code de recharge (" + operateurs.get(0).getCodeLenght() + " chiffres)");
            codeLength = operateurs.get(0).getCodeLenght();

        } else if (s.substring(0).equals("2")) {

            numTelTypeTV.setText("Votre Ligne est " + operateurs.get(1).getName());
            numTelTypeTV.setTextColor(getResources().getColor(operateurs.get(1).getColor()));
            rechargerLigneET.setBackgroundColor((getResources().getColor(operateurs.get(1).getColor())));
            codeRechargePrefix = operateurs.get(1).getPrefixRecharge();
            rechargerLigneET.setText(codeRechargePrefix + codeRechargeET.getText().toString() + "#");
            consulterSoldeET.setBackgroundColor(getResources().getColor(operateurs.get(1).getColor()));
            consulterSoldeET.setText(operateurs.get(1).getSoldeConsulterCode());
            codeRechargeTV.setText("Entrer votre code de recharge (" + operateurs.get(1).getCodeLenght() + " chiffres)");
            codeLength = operateurs.get(1).getCodeLenght();

        } else if (s.substring(0).equals("9")) {

            numTelTypeTV.setText("Votre Ligne est " + operateurs.get(2).getName());
            numTelTypeTV.setTextColor(getResources().getColor(operateurs.get(2).getColor()));
            rechargerLigneET.setBackgroundColor((getResources().getColor(operateurs.get(2).getColor())));
            codeRechargePrefix = operateurs.get(2).getPrefixRecharge();
            rechargerLigneET.setText(codeRechargePrefix + codeRechargeET.getText().toString() + "#");
            consulterSoldeET.setBackgroundColor((getResources().getColor(operateurs.get(2).getColor())));
            consulterSoldeET.setText(operateurs.get(2).getSoldeConsulterCode());
            codeRechargeTV.setText("Entrer votre code de recharge (" + operateurs.get(2).getCodeLenght() + " chiffres)");
            codeLength = operateurs.get(2).getCodeLenght();

        }
    }


}