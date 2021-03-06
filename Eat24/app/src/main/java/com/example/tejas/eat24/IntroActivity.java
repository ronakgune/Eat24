package com.example.tejas.eat24;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class IntroActivity extends Activity {

    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;
    private LinearLayout dotsLayout;
    private TextView[] dots;
    private int[] layouts;
    private Button btnSkip, btnNext;
    RelativeLayout myLayout;
    AnimationDrawable animationDrawable;
    LinearLayout l1,l2,l3,l4,l5,l6,ld1,ld2,ld3,ld4,ld5,ld6;
    Animation uptodown,downtoup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_intro);

//        uptodown = AnimationUtils.loadAnimation(this,R.anim.uptodown);
//        downtoup = AnimationUtils.loadAnimation(this, R.anim.downtoup);
//        l1.setAnimation(uptodown);
//        ld1.setAnimation(downtoup);
//        l2.setAnimation(uptodown);
//        ld2.setAnimation(downtoup);
//        l3.setAnimation(uptodown);
//        ld3.setAnimation(downtoup);
//        l4.setAnimation(uptodown);
//        ld4.setAnimation(downtoup);
//        l5.setAnimation(uptodown);
//        ld5.setAnimation(downtoup);
//        l6.setAnimation(uptodown);
//        ld6.setAnimation(downtoup);

       // myLayout = (RelativeLayout) findViewById(R.id.rel_intro);
       // animationDrawable = (AnimationDrawable) myLayout.getBackground();
        // animationDrawable.setEnterFadeDuration(1500);
        // animationDrawable.setExitFadeDuration(1500);
        // animationDrawable.start();

        // Onboarding screen to disappear after first launch------
        SharedPreferences share = getSharedPreferences("PREFS",MODE_PRIVATE);
        if(share.getInt("INTRO",0)==1)
        {
            startActivity(new Intent(IntroActivity.this,MainActivity.class));
            finish();
        }
//-----------------------------------------------------------------------------
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        dotsLayout = (LinearLayout) findViewById(R.id.layoutDots);
        btnSkip = (Button) findViewById(R.id.btn_skip);
        btnNext = (Button) findViewById(R.id.btn_next);

        layouts = new int[]{
                R.layout.onboarding_slide1,
                R.layout.onboarding_slide2,
                R.layout.onboarding_slide3,
                R.layout.onboarding_slide4,
                R.layout.onboarding_slide5,
                R.layout.onboarding_slide6};

        // adding bottom dots
        addBottomDots(0);

        viewPagerAdapter = new ViewPagerAdapter();
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.addOnPageChangeListener(viewPagerPageChangeListener);

         }

    public  void btnSkipClick(View v)
    {
        launchHomeScreen();
    }

    public  void btnNextClick(View v)
    {
        // checking for last page
        // if last page home screen will be launched
        int current = getItem(1);
        if (current < layouts.length) {
            // move to next screen
            viewPager.setCurrentItem(current);
        } else {
            launchHomeScreen();
        }
    }


    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(int position) {
            addBottomDots(position);

            // changing the next button text 'NEXT' / 'GOT IT'
            if (position == layouts.length - 1) {
                // last page. make button text to GOT IT
                btnNext.setText(getString(R.string.start));
                btnSkip.setVisibility(View.GONE);
            } else {
                // still pages are left
                btnNext.setText(getString(R.string.next));
                btnSkip.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }
    };

    private void addBottomDots(int currentPage) {
        dots = new TextView[layouts.length];

        dotsLayout.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(getResources().getColor(R.color.dot_inactive));
            dotsLayout.addView(dots[i]);
        }

        if (dots.length > 0)
            dots[currentPage].setTextColor(getResources().getColor(R.color.dot_active));
    }



    private int getItem(int i) {
        return viewPager.getCurrentItem() + i;
    }

    private void launchHomeScreen() {
 // Onboarding screen to disappear after first launch----
        SharedPreferences share = getSharedPreferences("PREFS",MODE_PRIVATE);
        SharedPreferences.Editor editor;

        editor=share.edit();
        editor.putInt("INTRO",1);
        editor.apply();
// --------------------------------------------------------------------

        startActivity(new Intent(this, MainActivity.class));
        finish();
    }


    public class ViewPagerAdapter extends PagerAdapter {
        private LayoutInflater layoutInflater;


        public ViewPagerAdapter() {

        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View view = layoutInflater.inflate(layouts[position], container, false);
            container.addView(view);

            return view;
        }

        @Override
        public int getCount() {
            return layouts.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View) object;
            container.removeView(view);
        }
    }

}
