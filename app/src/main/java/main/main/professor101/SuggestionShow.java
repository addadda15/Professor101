package main.main.professor101;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import org.w3c.dom.Text;

public class SuggestionShow extends AppCompatActivity {
    TextView title,content;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggestion_show);

        Intent intent = getIntent();
        title = (TextView) findViewById(R.id.sugShowTitle);
        content = (TextView) findViewById(R.id.sugShowContent);

        content.setMovementMethod(new ScrollingMovementMethod());

        title.setText(intent.getStringExtra("title"));
        content.setText(intent.getStringExtra("content"));

    }
}