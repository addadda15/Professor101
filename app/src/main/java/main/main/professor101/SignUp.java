package main.main.professor101;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {

    TextInputLayout regName, regStudentID, regPassword, regPasswordCheck;
    Button regBtn, regToLoginBtn;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        regName = findViewById(R.id.reg_name);
        regStudentID = findViewById(R.id.reg_st_id);
        regPassword = findViewById(R.id.reg_password);
        regPasswordCheck = findViewById(R.id.reg_pswd_check);
        regBtn = findViewById(R.id.reg_btn);
        regToLoginBtn = findViewById(R.id.reg_login_btn);

        regBtn.setOnClickListener(new View.OnClickListener() { //로그인버튼 눌럿을때
            @Override
            public void onClick(View view) {
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("users");

                registerUser(view);
//                // get all the values
//                String name = regName.getEditText().getText().toString();
//                String studentid = regStudentID.getEditText().getText().toString();
//                String password = regPassword.getEditText().getText().toString();
//                String passwordcheck = regPasswordCheck.getEditText().getText().toString();
//
//                UserHelperClass helperClass = new UserHelperClass(name, studentid, password, passwordcheck);
//
//                reference.child(studentid).setValue(helperClass); // unique 밸류 입력 구분하려고.

            }
        }); //Register Button

        regToLoginBtn.setOnClickListener(new View.OnClickListener() { // already Have An Account누르면 로그인창으로
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUp.this, Login.class); //Signup에서 login class로
            }
        });

    } //onCreate Button Method End

    private Boolean validateName(){  // 이름이 빈칸으로 입력될때 에러 함수
        String val = regName.getEditText().getText().toString(); // regName이 실명이름
//        String noWhiteSpace = "\\A\\w{4,20}\\z";

        if(val.isEmpty()){
            regName.setError("Field cannot be empty");
            return false;
        } else if(val.length()>5){  // 이름이 5글자 넘어갈때
            regName.setError("Username too long");
            return false;
        }
//        else if(!val.matches(noWhiteSpace)){  // 공백안된다?
//            regName.setError("White Spaces are not allowed");
//            return false;
//        }
        else{
            regName.setError(null);
            regName.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validateStudentID(){  // 학번이 빈칸으로 입력될때 에러 함수
        String val = regStudentID.getEditText().getText().toString();  // 학번

        if(val.isEmpty()){
            regStudentID.setError("Field cannot be empty");
            return false;
        }else if(val.length() > 8){  // 학번이 8글자 넘어갈때
            regStudentID.setError("Username too long");
            return false;
        }
        else{
            regStudentID.setError(null);
            return true;
        }
    }

    private Boolean validatePassword(){  // 비밀번호가 빈칸으로 입력될때 에러 함수
        String val = regPassword.getEditText().getText().toString();
//        String passwordVal = "^" +
//                "(?=.*[a-zA-Z])" +      // any letter
//                "(?=.*[@#$%^&+=])" +    // at least 1 special character
//                "(?=\\S+$)" +           //no white spaces
//                ".{4,}" +               //at least 4characters
//                "$";


        if(val.isEmpty()){
            regPassword.setError("Field cannot be empty");
            return false;
        }
//        else if(!val.matches(passwordVal)){
//            regPassword.setError("Password is too weak"); //비밀번호 보안 ㅈㄴ약하다
//            return false;
//        }
        else{
            regPassword.setError(null);
            return true;
        }
    }

    private Boolean validatePasswordCheck() {  // 비밀번호 체크가  빈칸으로 입력될때 에러 함수
        String val = regPasswordCheck.getEditText().getText().toString();
        String val1 = regPassword.getEditText().getText().toString();  // val이랑 val1 비교하려고 같지않으면 오류메세지

        if(val.isEmpty()){ // 칸이 비어져있으면
            regPasswordCheck.setError("Field cannot be empty");
            return false;
        }
        else if (!val1.equals(val)) { //두문자가 같지않으면  비밀번호 체크하는 구문
            regPasswordCheck.setError("Password isn't same"); // 같지않다고 출력
            return false;
        }
        else{
            regPasswordCheck.setError(null); //에러없다
            return true;
        }
    }





    public void registerUser(View view){
        // get all the values

        if(!validateName() | !validatePassword() | !validatePasswordCheck() | !validateStudentID()) { // |는 or
            return;
        }

        String name = regName.getEditText().getText().toString();
        String studentid = regStudentID.getEditText().getText().toString();
        String password = regPassword.getEditText().getText().toString();
        String passwordcheck = regPasswordCheck.getEditText().getText().toString();

        UserHelperClass helperClass = new UserHelperClass(name, studentid, password, passwordcheck);

        reference.child(studentid).setValue(helperClass); // unique 밸류 입력 구분하려고.
        Toast.makeText(this, "Your Account has been created sucessfully!", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(getApplicationContext(), Login.class);
        startActivity(intent);
        finish();


    }

} //SignUp class End