package main.main.professor101;

    import androidx.annotation.NonNull;
    import androidx.appcompat.app.AppCompatActivity;

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
    import java.util.Collections;
    import java.util.Comparator;

public class RankingActivity extends AppCompatActivity {

    ImageView rankImg1, rankImg2, rankImg3;
    TextView rankName1, rankName2, rankName3, rankScore1, rankScore2, rankScore3;
    FirebaseDatabase database;
    DatabaseReference databaseReference;
    ArrayList<Score> scoreList;
    int[] list_images;
    String[] list_title;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);

        list_images = new int[]{
                R.drawable.professor1,
                R.drawable.professor2,
                R.drawable.professor3,
                R.drawable.professor4,
                R.drawable.professor5,
                R.drawable.professor6,
                R.drawable.professor7,
                R.drawable.professor8,
                R.drawable.professor9,
                R.drawable.professor10,
                R.drawable.professor11,
                R.drawable.professor12,
                R.drawable.professor13
        };
        list_title = new String[]{
                "곽종욱", "김욱현", "박창현", "서영석", "손영호", "윤종희", "이기동", "조행래", "홍정규", "황도삼", "사공운", "김종근", "안병철"
        };

        rankImg1 = (ImageView) findViewById(R.id.rankImg1);
        rankImg2 = (ImageView) findViewById(R.id.rankImg2);
        rankImg3 = (ImageView) findViewById(R.id.rankImg3);
        rankName1 = (TextView) findViewById(R.id.rankName1);
        rankName2 = (TextView) findViewById(R.id.rankName2);
        rankName3 = (TextView) findViewById(R.id.rankName3);
        rankScore1 = (TextView) findViewById(R.id.rankScore1);
        rankScore2 = (TextView) findViewById(R.id.rankScore2);
        rankScore3 = (TextView) findViewById(R.id.rankScore3);

        scoreList = new ArrayList<>();
        scoreList.clear();
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("Ranking");

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Score sc = snapshot.getValue(Score.class);
                    scoreList.add(sc);
                }
                output();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("RankinActivity", String.valueOf(error.toException()));
            }
        });
    }

    private void output() {

        Collections.sort(scoreList, new Comparator<Score>() {
            @Override
            public int compare(Score score, Score t1) {
                return Double.compare(t1.score, score.score);
            }
        });

        rankImg1.setImageResource(list_images[scoreList.get(0).pid]);
        rankImg2.setImageResource(list_images[scoreList.get(1).pid]);
        rankImg3.setImageResource(list_images[scoreList.get(2).pid]);

        rankName1.setText(list_title[scoreList.get(0).pid]);
        rankName2.setText(list_title[scoreList.get(1).pid]);
        rankName3.setText(list_title[scoreList.get(2).pid]);

        String strScore1 = "평균평점 : " + scoreList.get(0).score;
        String strScore2 = "평균평점 : " + scoreList.get(1).score;
        String strScore3 = "평균평점 : " + scoreList.get(2).score;

        rankScore1.setText(strScore1);
        rankScore2.setText(strScore2);
        rankScore3.setText(strScore3);
    }
}