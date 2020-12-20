package main.main.professor101;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager.widget.ViewPager.OnPageChangeListener;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;

import android.widget.LinearLayout;
import android.widget.TextView;

public class Dashboard extends AppCompatActivity {

    private ViewPager viewpager;
    private LinearLayout liner;
    private SlideAdapter myadapter;

    private TextView[] mdots;
    private Button next, back;

    private int mCurrentPage;
    private int pos;
    Button btnComment, btnSuggest;
    Intent getIntent;
    private String Uid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        getIntent = getIntent();
        Uid = getIntent.getExtras().getString("studentID");

        pos = 0;
        viewpager = findViewById(R.id.viewpager);
        liner = findViewById(R.id.dots);

        next = findViewById(R.id.nextBtn);
        back = findViewById(R.id.backBtn);
        btnComment = (Button) findViewById(R.id.goComment);
        btnSuggest = (Button) findViewById(R.id.goSuggest);



        myadapter = new SlideAdapter(this);
        viewpager.setAdapter(myadapter);
        addDots(0);

        viewpager.addOnPageChangeListener(viewListener);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewpager.setCurrentItem(mCurrentPage + 1);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewpager.setCurrentItem(mCurrentPage - 1);
            }
        });
        btnComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CommentActivity.class);
                intent.putExtra("professorNum", pos);
                intent.putExtra("Uid", Uid);
                startActivity(intent);
            }
        });
        btnSuggest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SuggestionActivity.class);
                intent.putExtra("professorNum", pos);
                intent.putExtra("Uid", Uid);
                startActivity(intent);
            }
        });



    }

    public void addDots(int i) {

        mdots = new TextView[13];
        liner.removeAllViews();

        for (int x = 0; x < mdots.length; x++) {

            mdots[x] = new TextView(this);
            mdots[x].setText(Html.fromHtml("&#8226;"));
            mdots[x].setTextSize(35);
            mdots[x].setTextColor(getResources().getColor(R.color.colorAccent));

            liner.addView(mdots[x]);
        }
        if (mdots.length > 0) {

            mdots[i].setTextColor(getResources().getColor(R.color.white));

        }

    }

    OnPageChangeListener viewListener = new OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            pos = position;
            addDots(position);
            mCurrentPage = position;

            if (position == 0) {
                next.setEnabled(true);
                back.setEnabled(false);
                back.setVisibility(View.INVISIBLE);

                next.setText("NEXT");
                back.setText("");
            } else if (position == mdots.length - 1) {

                next.setEnabled(true);
                back.setEnabled(true);
                back.setVisibility(View.VISIBLE);

                next.setText("FINISH");
                back.setText("BACK");

            } else {
                next.setEnabled(true);
                back.setEnabled(true);
                back.setVisibility(View.VISIBLE);

                next.setText("NEXT");
                back.setText("BACK");
            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }


    };
}