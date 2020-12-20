package main.main.professor101;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SuggestionActivity extends AppCompatActivity {
    private String Uid;
    private int professorNum;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Suggestion> arrayList;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private Button btnSuggestion;
    private ArrayList<String> keyList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggestion);


        Intent getIntent = getIntent();

        Uid = getIntent.getExtras().getString("Uid");
        professorNum = getIntent.getExtras().getInt("professorNum");

        recyclerView = (RecyclerView) findViewById(R.id.sgRecyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        arrayList = new ArrayList<>();
        keyList = new ArrayList<>();
        btnSuggestion = (Button) findViewById(R.id.btnSug);


        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("Suggestion" + professorNum);
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                arrayList.clear();
                keyList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Suggestion suggestion = dataSnapshot.getValue(Suggestion.class);
                    arrayList.add(suggestion);
                    keyList.add(dataSnapshot.getKey());
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("SuggestionActivity", String.valueOf(error.toException()));
            }
        });
        btnSuggestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), WriteSuggestionActivity.class);
                intent.setAction("Write");
                startActivityForResult(intent, 1);
            }
        });
        adapter = new SuggestionAdapter(arrayList, this);
        ((SuggestionAdapter) adapter).setOnItemLongClickListener(new SuggestionAdapter.OnItemLongClickListener() {
            @Override
            public void onItemLongClick(View v, int position) {
                if (arrayList.get(position).getId().equals(Uid)) {
                    Intent intent = new Intent(getApplicationContext(), WriteSuggestionActivity.class);
                    intent.setAction("Revise");
                    intent.putExtra("pos", position);
                    intent.putExtra("title", arrayList.get(position).title);
                    intent.putExtra("content", arrayList.get(position).content);
                    startActivityForResult(intent, 2);
                }
            }
        });
        ((SuggestionAdapter) adapter).setOnItemClickListener(new SuggestionAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Intent intent = new Intent(getApplicationContext(), SuggestionShow.class);
                intent.putExtra("title",arrayList.get(position).title);
                intent.putExtra("content", arrayList.get(position).content);
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String title = "";
        String content = "";
        int pos = 0;

        if (resultCode == RESULT_OK) {
            title = data.getExtras().getString("title");
            content = data.getExtras().getString("content");
            pos = data.getExtras().getInt("pos");
        } else if (resultCode == RESULT_CANCELED) {
            pos = data.getExtras().getInt("pos");
        }

        if (requestCode == 2) {
            if (resultCode == RESULT_OK) {
                suggestionRevise(pos, title, content);
            } else if (resultCode == RESULT_CANCELED) {
                pos = data.getExtras().getInt("pos");
                suggestionDel(pos);
            }
        } else {
            if (resultCode == RESULT_OK) {
                suggestionWrite(title, content);
            }
        }
    }

    private void suggestionRevise(int pos, String title, String content) {
        arrayList.get(pos).setTitle(title);
        arrayList.get(pos).setContent(content);
        Suggestion suggestion = arrayList.get(pos);
        databaseReference.child(keyList.get(pos)).setValue(suggestion);
        adapter.notifyDataSetChanged();
    }

    private void suggestionDel(int pos) {
        arrayList.remove(pos);
        databaseReference.child(keyList.get(pos)).removeValue();
        keyList.remove(pos);
        adapter.notifyDataSetChanged();
    }

    private void suggestionWrite(String title, String content) {
        String key = databaseReference.push().getKey();
        keyList.add(key);
        Suggestion suggestion = new Suggestion(Uid, title, content);
        databaseReference.child(key).setValue(suggestion);
        arrayList.add(suggestion);
        adapter.notifyDataSetChanged();
    }
}