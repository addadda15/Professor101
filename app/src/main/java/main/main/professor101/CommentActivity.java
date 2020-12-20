package main.main.professor101;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CommentActivity extends AppCompatActivity {

    private String Uid;
    int professorNum;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Comment> arrayList;
    private ArrayList<String> keyList;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private DatabaseReference rankDbRef;
    private Button btnComment;
    private View dialogView;
    private EditText dlgComment;
    private RatingBar dlgScore;
    private AlertDialog.Builder dlg;
    private int cnt;
    private double sumScore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        Intent getIntent = getIntent();

        Uid = getIntent.getExtras().getString("Uid");
        professorNum = getIntent.getExtras().getInt("professorNum");

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        arrayList = new ArrayList<>();
        keyList = new ArrayList<>();
        btnComment = (Button) findViewById(R.id.btnComment);
        database = FirebaseDatabase.getInstance();

        rankDbRef = database.getReference("Ranking");
        databaseReference = database.getReference("Comment" + String.valueOf(professorNum));
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                arrayList.clear();
                keyList.clear();
                cnt = 0;
                sumScore = 0;
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Comment comment = dataSnapshot.getValue(Comment.class);
                    arrayList.add(comment);
                    keyList.add(dataSnapshot.getKey());
                    cnt++;
                    sumScore += comment.getRatingScore();
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("CommentActivity", String.valueOf(error.toException()));
            }
        });

        btnComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogView = (View) View.inflate(CommentActivity.this, R.layout.dialog_comment, null);
                AlertDialog.Builder dlg = new AlertDialog.Builder(CommentActivity.this);
                dlg.setTitle("댓글 입력");
                dlg.setView(dialogView);
                dlgComment = (EditText) dialogView.findViewById(R.id.dlgEdt1);
                dlgScore = (RatingBar) dialogView.findViewById(R.id.score);
                dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String str = dlgComment.getText().toString();
                        float rating = dlgScore.getRating();
                        commentWrite(str, rating);
                    }
                });
                dlg.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                dlg.show();
            }
        });

        adapter = new CommentAdapter(arrayList, this);
        ((CommentAdapter) adapter).setOnItemLongClickListener(new CommentAdapter.OnItemLongClickListener() {
            @Override
            public void onItemLongClick(View v, final int position) {
                if (arrayList.get(position).getId().equals(Uid)) {
                    dialogView = (View) View.inflate(CommentActivity.this, R.layout.dialog_comment, null);
                    dlgComment = (EditText) dialogView.findViewById(R.id.dlgEdt1);
                    dlgScore = (RatingBar) dialogView.findViewById(R.id.score);
                    dlg = new AlertDialog.Builder(CommentActivity.this);
                    dlg.setTitle("댓글 입력");
                    dlg.setView(dialogView);
                    dlg.setPositiveButton("수정", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            commentRevise(position, dlgComment.getText().toString(), dlgScore.getRating());
                        }
                    });
                    dlg.setNegativeButton("삭제", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            commentDel(position);
                        }
                    });
                    dlg.show();
                }

            }
        });
        recyclerView.setAdapter(adapter);

    }

    private void commentRevise(int position, String text, float rating) {
        sumScore -= arrayList.get(position).getRatingScore();
        cnt--;
        arrayList.get(position).setComment(text);
        arrayList.get(position).setRatingScore(rating);
        Comment comment = arrayList.get(position);
        databaseReference.child(keyList.get(position)).setValue(comment);
        adapter.notifyDataSetChanged();
        sumScore += arrayList.get(position).getRatingScore();
        cnt++;
        rankDbRef.child(String.valueOf(professorNum)).setValue(sumScore / cnt);
    }


    private void commentDel(int position) {
        double avg;
        sumScore -= arrayList.get(position).getRatingScore();
        cnt--;
        arrayList.remove(position);
        databaseReference.child(keyList.get(position)).removeValue();
        keyList.remove(position);
        adapter.notifyDataSetChanged();

        if (sumScore == 0)
            avg = 0;
        else
            avg = sumScore / cnt;
        Score score = new Score(String.valueOf(String.format("%.3f", avg)), professorNum);
        rankDbRef.child("rank" + String.valueOf(professorNum)).setValue(score);
    }

    private void commentWrite(String str, float rating) {
        String key = databaseReference.push().getKey();
        keyList.add(key);
        Comment comment = new Comment(Uid, str, rating);
        databaseReference.child(key).setValue(comment);
        arrayList.add(comment);
        adapter.notifyDataSetChanged();
        sumScore += comment.getRatingScore();
        cnt++;
        Score score = new Score(String.valueOf(String.format("%.3f", sumScore / cnt)), professorNum);
        rankDbRef.child("rank" + String.valueOf(professorNum)).setValue(score);
    }
}