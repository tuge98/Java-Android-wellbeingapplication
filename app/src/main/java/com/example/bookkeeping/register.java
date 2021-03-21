package com.example.bookkeeping;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class register extends AppCompatActivity {

    EditText username1, password1, userid;
    //EditText password1;
    Button register_button;

    public String salasana;
    public String kayttaja;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.s2_register);

        username1 = findViewById(R.id.username1);
        password1 = findViewById(R.id.password1);
        userid = findViewById(R.id.userid);
        register_button = findViewById(R.id.register_button);

        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userdetails userdetails = new userdetails();
                userdetails.setuserIDRIVI(userid.getText().toString());
                userdetails.setPassword(password1.getText().toString());
                userdetails.setUsername(username1.getText().toString());
                if (validateInput(userdetails)){
                    userdatabase userdatabase = com.example.bookkeeping.userdatabase.getuserDatabase(getApplicationContext());
                    final dbinterface dbinterface = userdatabase.dbinterface();
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

        private Boolean validateInput(userdetails userdetails){

            if (userdetails.getuserIDRIVI().isEmpty() ||
            userdetails.getPassword().isEmpty() ||
            userdetails.getUsername().isEmpty()){
                return false;
    }
        return true;

}



}