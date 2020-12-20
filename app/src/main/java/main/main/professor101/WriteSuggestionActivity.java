package main.main.professor101;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class WriteSuggestionActivity extends AppCompatActivity {

    Button btnWrite, btnDel;
    EditText etTitle, etContent;

    @Override
    public void onBackPressed() {
        setResult(RESULT_FIRST_USER);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_suggestion);
        btnWrite = (Button) findViewById(R.id.btnSugWrite);
        btnDel = (Button) findViewById(R.id.btnDel);
        etTitle = (EditText) findViewById(R.id.sugWriteTitle);
        etContent = (EditText) findViewById(R.id.sugWriteContent);

        final Intent intent = getIntent();
        if (intent.getAction().equals("Revise")) {
            btnWrite.setText("수정");
            btnDel.setVisibility(View.VISIBLE);
            int pos = intent.getExtras().getInt("pos");
            etTitle.setText(intent.getExtras().getString("title"));
            etContent.setText(intent.getExtras().getString("content"));
            intent.putExtra("pos", pos);
        }

        btnWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("title", etTitle.getText().toString());
                intent.putExtra("content", etContent.getText().toString());
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_CANCELED, intent);
                finish();
            }
        });

    }
}