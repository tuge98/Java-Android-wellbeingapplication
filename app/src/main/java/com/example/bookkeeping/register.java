package com.example.bookkeeping;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.security.NoSuchAlgorithmException;

public class register extends AppCompatActivity {

    EditText username1, password1, userid;
    //EditText password1;
    Button register_button;
    Button switchbacktologin;

    public String salasana;
    public String kayttaja;
    javaHashing hashingClass = new javaHashing();

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.s2_register);

        username1 = findViewById(R.id.username1);
        password1 = findViewById(R.id.password1);
        userid = findViewById(R.id.userid);
        register_button = findViewById(R.id.register_button);
        switchbacktologin= findViewById(R.id.switchbacktologin);;


//Registers the user to database
        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userdetails userdetails = new userdetails();
                //userdetails.setuserIDRIVI(userid.getText().toString());
                //userdetails.setPassword(password1.getText().toString());
                userdetails.setUsername(username1.getText().toString());
                try {
                    byte[] salt = hashingClass.getSalt();
                    String password1_ = hashingClass.getSecurePassword(password1.getText().toString(), salt);
                    System.out.println(password1_);
                    System.out.println("suola rekisteri; "+salt);
                    userdetails.setuserIDRIVI(salt);
                    //String password_1;
                    userdetails.setPassword(password1_);
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
                if (validateInput(userdetails)){
                    userdatabase userdatabase = com.example.bookkeeping.userdatabase.getuserDatabase(getApplicationContext());
                    final dbinterface dbinterface = userdatabase.dbinterface();
                    //Runnable is replaced with lambda
                    new Thread(() -> {
                        dbinterface.registerUser(userdetails);

                        runOnUiThread(new Runnable(){
                            @Override
                            public void run(){
                                Toast.makeText(getApplicationContext(),"User registered",Toast.LENGTH_SHORT).show();

                            }
                        });

                    }).start();
                }else{
                    Toast.makeText(getApplicationContext(),"Remember to fill all fields!", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
    //Validating inputs
        private Boolean validateInput(userdetails userdetails){

            if (userdetails.getuserIDRIVI() == null ||
            userdetails.getPassword().isEmpty() ||
            userdetails.getUsername().isEmpty()){
                return false;
    }
        return true;

}

//Function to return back to login
public void switchbacktoLogin(View w){
    startActivity(new Intent(register.this, login.class));
}



}