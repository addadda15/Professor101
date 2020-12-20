package main.main.professor101;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

import com.google.android.material.navigation.NavigationView;

public class Mainmenu extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    RelativeLayout btn101, btnRank, btnEvaluate, btnPom;
    Intent intent;
    String Sid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainmenu);
        intent = getIntent();
        Sid = intent.getExtras().getString("studentID");
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);

        //Tool Bar
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        //Navigation Drawer Menu
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        navigationView.setCheckedItem(R.id.nav_home);

        btn101 = findViewById(R.id.go101);
        btnRank = findViewById(R.id.goRank);
        btnEvaluate = findViewById(R.id.go_Evaluate);
        btnPom = findViewById(R.id.go_MOP);

        btn101.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Produce101Activity.class);
                startActivity(intent);
            }
        });

        btnEvaluate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Dashboard.class);
                intent.putExtra("studentID",Sid);//교수들 화면으로 넘어가고
                startActivity(intent);
            }
        });

        btnRank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RankingActivity.class);
                startActivity(intent);
            }
        });

        btnPom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MonthOfProfessorActivity.class); //이달의 교수수
               startActivity(intent);
            }
        });


    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else{
            super.onBackPressed();
        }

    }




    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()){
            case R.id.nav_home:
                break;
            case R.id.it_101:
                Intent intent = new Intent(Mainmenu.this, Nav_101.class);
                startActivity(intent);
                break;

            case R.id.it_evaluate:
                Intent intent2 = new Intent(Mainmenu.this, Nav_evaluate.class);
                startActivity(intent2);
                break;
            case R.id.it_rank:
                Intent intent3 = new Intent(Mainmenu.this, Nav_rank.class);
                startActivity(intent3);
                break;
            case R.id.it_pom:
                Intent intent4 = new Intent(Mainmenu.this, Nav_pom.class);
                startActivity(intent4);
                break;

        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}