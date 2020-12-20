package main.main.professor101;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Produce101Activity extends AppCompatActivity {

    LinearLayout linear1, linear2;
    TextView txView, vs;
    ImageView imgView1, imgView2;
    TextView txName1, txName2, txFiled1, txFiled2, txLocation1, txLocation2, txNumber1, txNumber2, txEmail1, txEmail2;
    FirebaseDatabase database;
    DatabaseReference myRef1, myRef2;
    ArrayList<Long> arrayList;

    class Professor {
        String name;
        String filed;
        String location;
        String number;
        String email;
        int id;
        int img;
    }

    Professor professor[] = new Professor[13];
    int count16, count8, count4, count2, win;
    int state; // 0일때 16강, 1일때 8강, 2일때 4강, 3일때 2강
    int pid;
    long score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produce101);

        count16 = 0;
        count8 = 0;
        count4 = 0;
        count2 = 0;
        win = 0;
        state = 0;
        pid = 0;

        linear1 = (LinearLayout) findViewById(R.id.linear1);
        linear2 = (LinearLayout) findViewById(R.id.linear2);

        txView = (TextView) findViewById(R.id.txView);
        txName1 = (TextView) findViewById(R.id.txName1);
        txName2 = (TextView) findViewById(R.id.txName2);
        txFiled1 = (TextView) findViewById(R.id.txField1);
        txFiled2 = (TextView) findViewById(R.id.txField2);
        txLocation1 = (TextView) findViewById(R.id.txLocation1);
        txLocation2 = (TextView) findViewById(R.id.txLocation2);
        txNumber1 = (TextView) findViewById(R.id.txNumber1);
        txNumber2 = (TextView) findViewById(R.id.txNumber2);
        txEmail1 = (TextView) findViewById(R.id.txEmail1);
        txEmail2 = (TextView) findViewById(R.id.txEmail2);

        imgView1 = (ImageView) findViewById(R.id.imgView1);
        imgView2 = (ImageView) findViewById(R.id.imgView2);

        arrayList = new ArrayList<Long>();

        for (int i = 0; i < professor.length; i++) { // 13명의 교수를 배열로 둠.
            professor[i] = new Professor();
        }

        //0
        professor[0].name = "곽종욱";
        professor[0].filed = "컴퓨터구조 및 임베디드시스템";
        professor[0].location = "IT관 217호";
        professor[0].number = "053-810-3533";
        professor[0].email = "kwak@yu.ac.kr";
        professor[0].img = R.drawable.professor1;
        professor[0].id = 0;
        //1
        professor[1].name = "김욱현";
        professor[1].filed = "시각정보처리";
        professor[1].location = "IT관 218호";
        professor[1].number = "053-810-2558";
        professor[1].email = "whkim@yu.ac.kr";
        professor[1].img = R.drawable.professor2;
        professor[1].id = 1;
        //2
        professor[2].name = "박창현";
        professor[2].filed = "인공지능 및 지능정보시스템";
        professor[2].location = "IT관 211호";
        professor[2].number = "053-810-2557";
        professor[2].email = "park@yu.ac.kr";
        professor[2].img = R.drawable.professor3;
        professor[2].id = 3;
        //3
        professor[3].name = "서영석";
        professor[3].filed = "소프트웨어공학";
        professor[3].location = "IT관 225호";
        professor[3].number = "053-810-3534";
        professor[3].email = "ysseo@yu.ac.kr";
        professor[3].img = R.drawable.professor4;
        professor[3].id = 5;
        //4
        professor[4].name = "손영호";
        professor[4].filed = "센서정보학";
        professor[4].location = "IT관 205호";
        professor[4].number = "053-810-2482";
        professor[4].email = "ysohn@ynu.ac.kr";
        professor[4].img = R.drawable.professor5;
        professor[4].id = 6;
        //5
        professor[5].name = "윤종희";
        professor[5].filed = "컴파일러, 소프트웨어 최적화 및 보안";
        professor[5].location = "IT관 231호";
        professor[5].number = "053-810-2552";
        professor[5].email = "youn@yu.ac.kr";
        professor[5].img = R.drawable.professor6;
        professor[5].id = 8;
        //6
        professor[6].name = "이기동";
        professor[6].filed = "제어정보시스템";
        professor[6].location = "IT관 223호";
        professor[6].number = "053-810-2584";
        professor[6].email = "kdrhee@yu.ac.kr";
        professor[6].img = R.drawable.professor7;
        professor[6].id = 9;
        //7
        professor[7].name = "조행래";
        professor[7].filed = "데이터베이스";
        professor[7].location = "IT관 215호";
        professor[7].number = "053-810-2559";
        professor[7].email = "hrcho@yu.ac.kr";
        professor[7].img = R.drawable.professor8;
        professor[7].id = 10;
        //8
        professor[8].name = "홍정규";
        professor[8].filed = "컴퓨팅 및 메모리 시스템";
        professor[8].location = "IT관 236호";
        professor[8].number = "053-810-2553";
        professor[8].email = "jhong@yu.ac.kr";
        professor[8].img = R.drawable.professor9;
        professor[8].id = 11;
        //9
        professor[9].name = "황도삼";
        professor[9].filed = "언어미디어통신";
        professor[9].location = "IT관 204호";
        professor[9].number = "053-810-3515";
        professor[9].email = "dshwang@yu.ac.kr";
        professor[9].img = R.drawable.professor10;
        professor[9].id = 12;
        //10
        professor[10].name = "사공운";
        professor[10].filed = "BioInformatics";
        professor[10].location = "IT관 209호";
        professor[10].number = "053-810-2554";
        professor[10].email = "wsagong@yu.ac.kr";
        professor[10].img = R.drawable.professor11;
        professor[10].id = 4;
        //11
        professor[11].name = "김종근";
        professor[11].filed = "네트워크 및 분산처리";
        professor[11].location = "IT관 207호";
        professor[11].number = "053-810-2555";
        professor[11].email = "cgkim@yu.ac.kr";
        professor[11].img = R.drawable.professor12;
        professor[11].id = 2;
        //12
        professor[12].name = "안병철";
        professor[12].filed = "멀티미디어 시스템";
        professor[12].location = "IT관 216호";
        professor[12].number = "053-810-2556";
        professor[12].email = "b.ahn@yu.ac.kr";
        professor[12].img = R.drawable.professor13;
        professor[12].id = 7;

        int count = 13;
        // 교수 데이터 셔플
        shuffle(count);

        txName1.setText(professor[0].name);
        txFiled1.setText(professor[0].filed);
        txLocation1.setText(professor[0].location);
        txNumber1.setText(professor[0].number);
        txEmail1.setText(professor[0].email);
        imgView1.setImageResource(professor[0].img);

        txName2.setText(professor[8].name);
        txFiled2.setText(professor[8].filed);
        txLocation2.setText(professor[8].location);
        txNumber2.setText(professor[8].number);
        txEmail2.setText(professor[8].email);
        imgView2.setImageResource(professor[8].img);

        state = 0;

        database = FirebaseDatabase.getInstance();
        myRef1 = database.getReference();
        myRef2 = database.getReference("professor101");
        myRef2.addValueEventListener(new ValueEventListener() { //시작하자마자 데이터베이스에서 값을읽어서 배열에 추가
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //arrayList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    arrayList.add((long) dataSnapshot.getValue());
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.linear1:
                        setCount();
                        stateCheck();
                        viewState();
                        break;
                    case R.id.linear2:
                        setState2();
                        setCount();
                        stateCheck();
                        viewState();
                        break;
                }
            }
        };
        linear1.setOnClickListener(clickListener);
        linear2.setOnClickListener(clickListener);
    }

    public void setState2() {
        if (state == 0) {
            professor[count16].name = professor[count16 + 8].name;
            professor[count16].filed = professor[count16 + 8].filed;
            professor[count16].location = professor[count16 + 8].location;
            professor[count16].number = professor[count16 + 8].number;
            professor[count16].email = professor[count16 + 8].email;
            professor[count16].img = professor[count16 + 8].img;
            professor[count16].id = professor[count16 + 8].id;
        } else if (state == 1) {
            professor[count8].name = professor[count8 + 4].name;
            professor[count8].filed = professor[count8 + 4].filed;
            professor[count8].location = professor[count8 + 4].location;
            professor[count8].number = professor[count8 + 4].number;
            professor[count8].email = professor[count8 + 4].email;
            professor[count8].img = professor[count8 + 4].img;
            professor[count8].id = professor[count8 + 4].id;
        } else if (state == 2) {
            professor[count4].name = professor[count4 + 2].name;
            professor[count4].filed = professor[count4 + 2].filed;
            professor[count4].location = professor[count4 + 2].location;
            professor[count4].number = professor[count4 + 2].number;
            professor[count4].email = professor[count4 + 2].email;
            professor[count4].img = professor[count4 + 2].img;
            professor[count4].id = professor[count4 + 2].id;
        } else if (state == 3) {
            professor[count2].name = professor[count2 + 1].name;
            professor[count2].filed = professor[count2 + 1].filed;
            professor[count2].location = professor[count2 + 1].location;
            professor[count2].number = professor[count2 + 1].number;
            professor[count2].email = professor[count2 + 1].email;
            professor[count2].img = professor[count2 + 1].img;
            professor[count2].id = professor[count2 + 1].id;
        }
    }

    public void setCount() {
        if (state == 0) { //16강 10개 5번 매치 (3개 부전승)
            count16++;
        } else if (state == 1) { //8강 4번 매치
            count8++;
        } else if (state == 2) { //4강 2번 매치
            count4++;
        } else if (state == 3) { // 2강 1번 매치
            count2++;
        }
    }

    public void viewState() {
        if (state == 0) {
            txView.setText("Processor 101 16강 " + (count16 + 1) + "/8");
            txName1.setText(professor[count16].name);
            txFiled1.setText(professor[count16].filed);
            txLocation1.setText(professor[count16].location);
            txNumber1.setText(professor[count16].number);
            txEmail1.setText(professor[count16].email);
            imgView1.setImageResource(professor[count16].img);
            txName2.setText(professor[count16 + 8].name);
            txFiled2.setText(professor[count16 + 8].filed);
            txLocation2.setText(professor[count16 + 8].location);
            txNumber2.setText(professor[count16 + 8].number);
            txEmail2.setText(professor[count16 + 8].email);
            imgView2.setImageResource(professor[count16 + 8].img);
        } else if (state == 1) {
            txView.setText("Processor 101 8강 " + (count8 + 1) + "/4");
            txName1.setText(professor[count8].name);
            txFiled1.setText(professor[count8].filed);
            txLocation1.setText(professor[count8].location);
            txNumber1.setText(professor[count8].number);
            txEmail1.setText(professor[count8].email);
            imgView1.setImageResource(professor[count8].img);
            txName2.setText(professor[count8 + 4].name);
            txFiled2.setText(professor[count8 + 4].filed);
            txLocation2.setText(professor[count8 + 4].location);
            txNumber2.setText(professor[count8 + 4].number);
            txEmail2.setText(professor[count8 + 4].email);
            imgView2.setImageResource(professor[count8 + 4].img);
        } else if (state == 2) {
            txView.setText("Processor 101 4강 " + (count4 + 1) + "/2");
            txName1.setText(professor[count4].name);
            txFiled1.setText(professor[count4].filed);
            txLocation1.setText(professor[count4].location);
            txNumber1.setText(professor[count4].number);
            txEmail1.setText(professor[count4].email);
            imgView1.setImageResource(professor[count4].img);
            txName2.setText(professor[count4 + 2].name);
            txFiled2.setText(professor[count4 + 2].filed);
            txLocation2.setText(professor[count4 + 2].location);
            txNumber2.setText(professor[count4 + 2].number);
            txEmail2.setText(professor[count4 + 2].email);
            imgView2.setImageResource(professor[count4 + 2].img);
        } else if (state == 3) {
            txView.setText("Processor 101 결승전");
            txName1.setText(professor[count2].name);
            txFiled1.setText(professor[count2].filed);
            txLocation1.setText(professor[count2].location);
            txNumber1.setText(professor[count2].number);
            txEmail1.setText(professor[count2].email);
            imgView1.setImageResource(professor[count2].img);
            txName2.setText(professor[count2 + 1].name);
            txFiled2.setText(professor[count2 + 1].filed);
            txLocation2.setText(professor[count2 + 1].location);
            txNumber2.setText(professor[count2 + 1].number);
            txEmail2.setText(professor[count2 + 1].email);
            imgView2.setImageResource(professor[count2 + 1].img);
        } else if (state == 4) {
            vs = (TextView) findViewById(R.id.vs);
            linear2 = (LinearLayout) findViewById(R.id.linear2);

            txView.setText("Processor 101 우승");
            txName1.setText(professor[win].name);

            txFiled1.setText(professor[win].filed);
            txLocation1.setText(professor[win].location);
            txNumber1.setText(professor[win].number);
            txEmail1.setText(professor[win].email);
            imgView1.setImageResource(professor[win].img);

            vs.setVisibility(View.INVISIBLE);
            linear2.setVisibility(View.INVISIBLE);

            score = arrayList.get(professor[win].id);
            myRef1.child("professor101").child(professor[win].name).setValue(score + 1);

        }
    }

    public void stateCheck() {
        if (count16 == 5) {
            state = 1; // 8강
            shuffle(8);
            count16 = 0;
        }
        if (count8 == 4) {
            state = 2;
            shuffle(4);
            count8 = 0;
        }
        if (count4 == 2) {
            state = 3;
            shuffle(2);
            count4 = 0;
        }
        if (count2 == 1) {
            state = 4;
            count2 = 0;
        }
    }

    public void shuffle(int count) {
        for (int i = 0; i < count; i++) {
            int randomNum1, randomNum2;

            String tempName1, tempName2, tempFiled1, tempFiled2, tempLocation1, tempLocation2, tempNumber1, tempNumber2, tempEmail1, tempEmail2;
            int tempImg1, tempImg2;
            int tempId1, tempId2;

            randomNum1 = (int) (Math.random() * count);
            tempName1 = professor[randomNum1].name;
            tempFiled1 = professor[randomNum1].filed;
            tempLocation1 = professor[randomNum1].location;
            tempNumber1 = professor[randomNum1].number;
            tempEmail1 = professor[randomNum1].email;
            tempImg1 = professor[randomNum1].img;
            tempId1 = professor[randomNum1].id;

            randomNum2 = (int) (Math.random() * count);
            tempName2 = professor[randomNum2].name;
            tempFiled2 = professor[randomNum2].filed;
            tempLocation2 = professor[randomNum2].location;
            tempNumber2 = professor[randomNum2].number;
            tempEmail2 = professor[randomNum2].email;
            tempImg2 = professor[randomNum2].img;
            tempId2 = professor[randomNum2].id;
            // 다시 넣기
            professor[randomNum1].name = tempName2;
            professor[randomNum1].filed = tempFiled2;
            professor[randomNum1].location = tempLocation2;
            professor[randomNum1].number = tempNumber2;
            professor[randomNum1].email = tempEmail2;
            professor[randomNum1].img = tempImg2;
            professor[randomNum1].id = tempId2;

            professor[randomNum2].name = tempName1;
            professor[randomNum2].filed = tempFiled1;
            professor[randomNum2].location = tempLocation1;
            professor[randomNum2].number = tempNumber1;
            professor[randomNum2].email = tempEmail1;
            professor[randomNum2].img = tempImg1;
            professor[randomNum2].id = tempId1;
        }
    }
}