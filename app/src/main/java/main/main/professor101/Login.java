package main.main.professor101;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {

    Button callSignUp,login_btn;
    ImageView image;
    TextView logoText, sloganText;
    TextInputLayout studentID, password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);



        //Hooks
        image = findViewById(R.id.logo_image);  //변수 불러옴
        callSignUp = findViewById(R.id.signup_screen);
        logoText = findViewById(R.id.logo_name);
        sloganText = findViewById(R.id.slogan_name);
        studentID = findViewById(R.id.st_id);
        password = findViewById(R.id.pswd);
        login_btn = findViewById(R.id.login_btn);




        login_btn.setOnClickListener(new View.OnClickListener() { //Go 버튼 클릭됫을때
            @Override
            public void onClick(View view) {
                loginUser(view); //loginUser함수 실행
            }
        });

        callSignUp.setOnClickListener(new View.OnClickListener() { //callSignUp 버튼이 선택되었을때
            @Override
            public void onClick(View view) { //SignUp버튼 클릭됫을때
                Intent intent = new Intent(Login.this, SignUp.class);

                Pair[] pairs = new Pair[7];

                pairs[0] = new Pair<View, String>(image,"logo_image");  // 애니메이션 변환 하나씩 transition같게해서 변환
                pairs[1] = new Pair<View, String>(logoText,"logo_text");
                pairs[2] = new Pair<View, String>(sloganText,"logo_desc");
                pairs[3] = new Pair<View, String>(studentID,"username_tran");
                pairs[4] = new Pair<View, String>(password,"password_tran");
                pairs[5] = new Pair<View, String>(login_btn,"button_tran");
                pairs[6] = new Pair<View, String>(callSignUp,"login_signup_tran");

                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Login.this, pairs); // pairs배열에있는거 하나씩 변환한다.
                startActivity(intent, options.toBundle());

            }
        });
    }

    private Boolean validateStudentID(){  // 학번이 빈칸으로 입력될때 에러 함수
        String val = studentID.getEditText().getText().toString();  // 학번

        if(val.isEmpty()){
            studentID.setError("Field cannot be empty");
            return false;
        }else if(val.length() > 8){  // 학번이 8글자 넘어갈때
            studentID.setError("Username too long");
            return false;
        }
        else{
            studentID.setError(null);
            return true;
        }
    }

    private Boolean validatePassword(){  // 비밀번호가 빈칸으로 입력될때 에러 함수
        String val = password.getEditText().getText().toString();


        if(val.isEmpty()){
            password.setError("Field cannot be empty");
            return false;
        }
        else{
            password.setError(null);
            password.setErrorEnabled(false);
            return true;
        }
    }

    public void loginUser(View view){
        if(!validateStudentID() | !validatePassword()){  // 틀린경우
            return;
        }
        else{
            isUser();  //파이어베이스에 있는지 없는지 체크하는 함수 //같으면 Dashboard화면으로 넘어가
        }
    }

    private void isUser() {

        final String userEnteredStudentID = studentID.getEditText().getText().toString().trim();
        final String userEnteredPassword = password.getEditText().getText().toString().trim();

        //firebase reference만들어야함.
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");  //users에서 참조한다.

        Query checkUser = reference.orderByChild("studentID").equalTo(userEnteredStudentID);
        //studentID가 userEnteredPassword와 매치되면 로그인 스크린안에 user에 의해 들어가진다.
        //이 큐리는 모든 유저에서 패스워드 맞는애 매치시킴.

        //우리가 이 studentID있다면 이 이벤트 추가 시킬수있다.
       checkUser.addListenerForSingleValueEvent(new ValueEventListener() { //user가 있다면 dataSnapshot안에 value를 얻을수 있다.
           @Override
           public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

               if(dataSnapshot.exists()){ // dataSnapshot이 있는지 체크

                   studentID.setError(null);
                   studentID.setErrorEnabled(false);

                   // dataSnapshot이 있다면 구체적인 유저의 이studentID가 가지고있는 그데이터를 얻는다.
                   String passwordFromDB = dataSnapshot.child(userEnteredStudentID).child("password").getValue(String.class); // password확인

                   if(passwordFromDB.equals(userEnteredPassword)) {  //데이터베이스에 잇는 패스오ㅝ드랑 같으면 우린 로그인할수있고 모든 데이터 공유됨

                       studentID.setError(null);
                       studentID.setErrorEnabled(false);

                       String nameFromDB = dataSnapshot.child(userEnteredStudentID).child("name").getValue(String.class);
                       String studentIDFromDB = dataSnapshot.child(userEnteredStudentID).child("studentID").getValue(String.class); //passwordcheck는 같은거라서 할필요없어서 안함.

                       Intent intent = new Intent(getApplicationContext(), Mainmenu.class);  //대쉬보드 클래스로 넘어감

                       intent.putExtra("name",nameFromDB);  // 액티비티 이동과 동시에 액티비티에서 이동하는 액티비티로 어떤값 넘기고싶을때
                       intent.putExtra("studentID",studentIDFromDB);
                       intent.putExtra("password",passwordFromDB);


                       startActivity(intent);


                   }
                   else{
                       password.setError("Wrong Password"); //없으면
                       password.requestFocus(); //키보드 자동으로 올리게
                   }
               }
               else{
                   studentID.setError("No such Student exist"); // 학번 db랑 비교해서 없으면
                   studentID.requestFocus(); //자동으로 키보드 올리게?
               }
           }

           @Override
           public void onCancelled(@NonNull DatabaseError databaseError) {

           }
       });

    }

    public void callSignUpScreen(View view){

    }


}