package main.main.professor101;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


public class SlideAdapter extends PagerAdapter {

    Context context;
    LayoutInflater inflater;

    public SlideAdapter(Context context){
        this.context=context;
    }

    //Array
    public int[] list_images={

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

    public String[] list_title={

            "곽종욱", "김욱현", "박창현", "서영석", "손영호", "윤종희", "이기동", "조행래", "홍정규", "황도삼", "사공운", "김종근", "안병철"
    };

    public String[] list_description={

            "시스템프로그래밍및보안\n\n운영체제설계\n\n컴퓨터구조\n\n컴퓨터프로그래밍및실습\n\n분산시스템\n\n컴퓨터공학설계\n\n임베디드소프트웨어실습\n\n대학생활설계",
            "대학생활설계\n\n컴퓨터그래픽스\n\n컴퓨터와IT\n\n컴퓨터비전\n\n소프트웨어와컴퓨팅사고\n\n종합설계과제\n\n상업정보논리및논술\n\n종합설계과제(2)",
            "대학생활설계\n\n산학과제공동연구\n\n산업체요구문제연구\n\n이산수학\n\n인공지능\n\n자료구조\n\n알고리즘\n\n산업체수요지향과제\n\n컴퓨터공학세미나\n\n산학공동연구과제",
            "소프트웨어설계\n\n소프트웨어와컴퓨팅사고\n\n소프트웨어와공학\n\n회로이론\n\n종합설계과제\n\n취업설계\n\n자바프로그래밍및실습\n\n대학생활설계\n\n컴퓨터와IT",
            "컴퓨터프로그래밍및실습\n\n컴퓨터와IT\n\n공학입문설계\n\nIOT와프로그램\n\n대학생활설계",
            "프로그래밍언어\n\n소프트웨어와컴퓨팅사고\n\n시스템프로그래밍및보안\n\n모바일프로그래밍및실습\n\n소프트웨어공학\n\n자바프로그래밍및실습\n\n컴파일러\n\n대학생활설계\n\n컴퓨터와IT\n\n종합설계과제",
            "논리회로컴퓨터구조\n\n컴퓨터시스템및어셈블리어공학입문설계\n\n정보보호및암호학\n\nIOT와프로그램\n\n논리회로실험\n\n대학생활설계\n\n컴퓨터프로그래밍및실습\n\n로봇창의설계입문\n\n종합설계과제\n\n인공지능\n\n웹디자인\n\n프로그래밍언어",
            "자료구조\n\n자료구조실습\n\n컴퓨터프로그래밍및실습\n\n데이터베이스\n\n컴퓨터와IT\n\n알고리즘\n\n소프트웨어와컴퓨팅사고\n\n프로그래밍언어실습\n\n대학생활설계\n\n웹프로그래밍및실습\n\n종합설계과제\n\n웹프로그래밍",
            "컴퓨터프로그래밍및실습\n\n임베디드소프트웨어및실습\n\n운영체제설계\n\n논리회로\n\n컴퓨터구조\n\n종합설계과제\n\n마이크로프로세서및실험",
            "프로그래밍언어실습\n\n정보검색\n\n자바프로그래밍및실습\n\n프로그래밍언어\n\n객체지향프로그래밍및실습\n\n상업정보논리및논술",
            "컴퓨터프로그래밍및실습\n\n취업설계\n\n통계학(1)\n\n의사소통기술\n\n컴퓨터와IT\n\n소프트웨어와컴퓨팅사고",
            "실용발명과특허\n\n웹디자인\n\n소프트웨어와컴퓨팅사고\n\n컴퓨터네트워크및실습\n\n데이터통신\n\nICT기술과지식재산권\n\n네트워크보안관리\n\n지식재산권이해와활용",
            "컴퓨터구조\n\n컴퓨터시스템및어셈블리어\n\n임베디드소프트웨어및실습\n\n마이크로프로세서및실험\n\n공학입문설계\n\nS/W융복합시스템\n\n논리회로\n\n논리회로실험\n\n종합설계과제\n\nIOT프로그램\n\n종합설계과제(2)\n\n취업설계\n\n멀티미디어시스템\n\n상업정보논리및논술\n\n소프트웨어와컴퓨팅사고"
    };
    public int[] list_color={

            Color.rgb(255, 255, 255),
            Color.rgb(255, 255, 255),
            Color.rgb(255, 255, 255),
            Color.rgb(255, 255, 255),
            Color.rgb(255, 255, 255),
            Color.rgb(255, 255, 255),
            Color.rgb(255, 255, 255),
            Color.rgb(255, 255, 255),
            Color.rgb(255, 255, 255),
            Color.rgb(255, 255, 255),
            Color.rgb(255, 255, 255),
            Color.rgb(255, 255, 255),
            Color.rgb(255, 255, 255)

    };

    @Override
    public int getCount() {
        return list_title.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object obj) {
        return view==(LinearLayout)obj;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        inflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.slide,container,false);

        LinearLayout linearLayout = (LinearLayout)view.findViewById(R.id.slidelinearlayout);
        ImageView img = (ImageView)view.findViewById(R.id.slideimg);
        TextView txt1 = (TextView) view.findViewById(R.id.slidetitle);
        TextView txt2 = (TextView) view.findViewById(R.id.slidedescription);


        img.setImageResource(list_images[position]);
        txt1.setText(list_title[position]);
        txt2.setText(list_description[position]);
        linearLayout.setBackgroundColor(list_color[position]);

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout)object);
    }
}