package main.main.professor101;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class MonthOfProfessorActivity extends AppCompatActivity {

    ImageView professor101Img;
    TextView professor101Name, professor101Win;
    FirebaseDatabase database;
    DatabaseReference myRef1, myRef2;
    ArrayList<Long> arrayList;

    class Professor {
        String name;
        int id;
        int img;
    }

    Professor professor[] = new Professor[13];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_month_of_professor);

        professor101Img = (ImageView)findViewById(R.id.professor101Img);
        professor101Name = (TextView)findViewById(R.id.professor101Name);
        professor101Win = (TextView)findViewById(R.id.professor101Win);

        arrayList = new ArrayList<Long>();

        for (int i = 0; i < professor.length; i++) { // 13명의 교수를 배열로 둠.
            professor[i] = new Professor();
        }

        //0
        professor[0].name = "곽종욱";
        professor[0].img = R.drawable.professor1;
        professor[0].id = 0;
        //1
        professor[1].name = "김욱현";
        professor[1].img = R.drawable.professor2;
        professor[1].id = 1;
        //2
        professor[2].name = "박창현";
        professor[2].img = R.drawable.professor3;
        professor[2].id = 3;
        //3
        professor[3].name = "서영석";
        professor[3].img = R.drawable.professor4;
        professor[3].id = 5;
        //4
        professor[4].name = "손영호";
        professor[4].img = R.drawable.professor5;
        professor[4].id = 6;
        //5
        professor[5].name = "윤종희";
        professor[5].img = R.drawable.professor6;
        professor[5].id = 8;
        //6
        professor[6].name = "이기동";
        professor[6].img = R.drawable.professor7;
        professor[6].id = 9;
        //7
        professor[7].name = "조행래";
        professor[7].img = R.drawable.professor8;
        professor[7].id = 10;
        //8
        professor[8].name = "홍정규";
        professor[8].img = R.drawable.professor9;
        professor[8].id = 11;
        //9
        professor[9].name = "황도삼";
        professor[9].img = R.drawable.professor10;
        professor[9].id = 12;
        //10
        professor[10].name = "사공운";
        professor[10].img = R.drawable.professor11;
        professor[10].id = 4;
        //11
        professor[11].name = "김종근";
        professor[11].img = R.drawable.professor12;
        professor[11].id = 2;
        //12
        professor[12].name = "안병철";
        professor[12].img = R.drawable.professor13;
        professor[12].id = 7;

        database = FirebaseDatabase.getInstance();
        myRef1 = database.getReference();
        myRef2 = database.getReference("professor101");
        myRef2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    arrayList.add((long) dataSnapshot.getValue());
                }
                output();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    private void output() {
        long score = 0;
        int index = 0;
        for(int i = 0; i < arrayList.size(); i++){
            if(arrayList.get(i) > score) {
                index = i;
                score = arrayList.get(i);
            }
        }
        int id = 0;

        for(int i=0;i<professor.length; i++) {
            if(professor[i].id==index) id = i;
        }

        professor101Img.setImageResource(professor[id].img);
        professor101Name.setText(professor[id].name);
        professor101Win.setText("최종 우승 횟수 "+score+"회");
    }


}