package com.example.bookkeeping;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteException;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
                String invalidPassword = "Password must be atleast 12 characters, include 1 uppercase letter, 1 lowercase letter, a number, and a special character";
                Snackbar mySnackbar = Snackbar.make(v, invalidPassword, BaseTransientBottomBar.LENGTH_SHORT);
                Boolean validPassword = passwordChecker(password1.getText().toString());


                //Snackbar mySnackbar2 = Snackbar.make(v, "Username is not unique, try another", BaseTransientBottomBar.LENGTH_SHORT);
                if (!validPassword) {
                    System.out.println(invalidPassword);
                    //findViewById(R.id.textView4).setVisibility(View.VISIBLE);
                    mySnackbar.show();
//                } else if (!validUsername) {
//                    System.out.println("Username is not unique, try another");
//                    mySnackbar2.show();
                } else {

                    User user = new User(username1.getText().toString());
                    //userdetails.setuserIDRIVI(userid.getText().toString());
                    //userdetails.setPassword(password1.getText().toString());





                    try {
                        byte[] salt = hashingClass.getSalt();
                        String password1_ = hashingClass.getSecurePassword(password1.getText().toString(), salt);
                        System.out.println(password1_);
                        System.out.println("suola rekisteri; "+salt);
                        user.setSalt(salt);
                        user.setHash(password1_);
                        //String password_1;

                    } catch (NoSuchAlgorithmException e) {
                        e.printStackTrace();
                    }
                    if (validateInput(user)) {

                            UserDao dao = UserDB.getInstance(getApplicationContext()).userDao();
                            Runnable task = () -> {
                                try {
                                    dao.insert(user);
                                } catch (SQLiteException exception) {
                                    System.out.println("Username is already used, try another");
                                    Snackbar usernameUsed = Snackbar.make(v, "Username is already used, try another", BaseTransientBottomBar.LENGTH_SHORT);
                                    usernameUsed.show();
                                }
                            };


                            Thread thread = new Thread(task);
                            thread.start();
                            thread.interrupt();



                        //Runnable is replaced with lambda
                       /* new Thread(() -> {
                            dbinterface.registerUser(userdetails);

                            runOnUiThread(new Runnable(){
                                @Override
                                public void run(){
                                    Toast.makeText(getApplicationContext(),"User registered",Toast.LENGTH_SHORT).show();

                                }
                            });

                        }).start();
                        */

                    }else{
                        Toast.makeText(getApplicationContext(),"Remember to fill all fields!", Toast.LENGTH_SHORT).show();

                    }
                }

            }
        });
    }
    //Validating inputs
        private Boolean validateInput(User user){

            if (user.getUsername() == null ||
            user.getSalt() == null) {
                return false;
        }
        return true;

    }

//Function to return back to login
    public void switchbacktoLogin(View w){
    startActivity(new Intent(register.this, login.class));
}

    public boolean passwordChecker(String password) {
        Pattern p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(password);
        boolean b = m.find();
        return ((password.length() >= 12) & (password.matches(".*\\d.*")) & (b) & (!password.equals(password.toLowerCase())) & (!password.equals(password.toUpperCase())));

    }




}

