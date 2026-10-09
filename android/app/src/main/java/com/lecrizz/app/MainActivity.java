package com.lecrizz.app;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.content.Context;
import android.widget.*;

public class MainActivity extends Activity {
  LinearLayout root;
  int white=Color.rgb(247,247,250), muted=Color.rgb(194,184,205), purple=Color.rgb(194,92,255);

  TextView text(String s,int sp){ TextView t=new TextView(this); t.setText(s); t.setTextColor(white); t.setTextSize(sp); t.setPadding(0,5,0,5); return t; }
  GradientDrawable bg(int color,float radius){ GradientDrawable g=new GradientDrawable(); g.setColor(color); g.setCornerRadius(radius); return g; }
  TextView pill(String s){ TextView t=text(s,13); t.setGravity(Gravity.CENTER); t.setTextColor(Color.rgb(220,194,235)); t.setPadding(18,8,18,8); t.setBackground(bg(Color.argb(70,255,255,255),50)); return t; }
  Button btn(String s){ Button b=new Button(this); b.setText(s); b.setTextColor(Color.WHITE); b.setTextSize(15); b.setAllCaps(false); b.setPadding(20,4,20,4); b.setBackgroundResource(com.lecrizz.app.R.drawable.primary_button); return b; }

  @Override public void onCreate(Bundle b){super.onCreate(b); showWelcome();}

  void base(){
    root=new LinearLayout(this); root.setOrientation(LinearLayout.VERTICAL); root.setPadding(22,22,22,28); root.setBackgroundColor(Color.rgb(9,7,13));
    ScrollView sc=new ScrollView(this); sc.setFillViewport(true); sc.addView(root); setContentView(sc);
  }

  void showWelcome(){
    base();
    LinearLayout hero=new LinearLayout(this); hero.setOrientation(LinearLayout.VERTICAL); hero.setGravity(Gravity.CENTER_HORIZONTAL); hero.setPadding(20,26,20,25); hero.setBackgroundResource(R.drawable.hero_gradient); root.addView(hero,new LinearLayout.LayoutParams(-1,-2));

    Space top=new Space(this); hero.addView(top,new LinearLayout.LayoutParams(1,4));
    ImageView icon=new ImageView(this); icon.setImageResource(R.drawable.lecrizz_icon); icon.setScaleType(ImageView.ScaleType.CENTER_INSIDE); hero.addView(icon,new LinearLayout.LayoutParams(170,170));

    TextView title=text("LecRizz",34); title.setTypeface(Typeface.DEFAULT,Typeface.BOLD); title.setGravity(Gravity.CENTER); title.setPadding(0,10,0,0); hero.addView(title,new LinearLayout.LayoutParams(-1,-2));
    TextView sub=text("Your AI reply wingman ✨",17); sub.setGravity(Gravity.CENTER); sub.setTextColor(muted); hero.addView(sub);
    TextView features=text("Rizz • Translate • Your Style",14); features.setGravity(Gravity.CENTER); features.setTextColor(Color.rgb(219,190,232)); hero.addView(features);
    TextView langs=pill("150+ languages"); LinearLayout.LayoutParams lp=new LinearLayout.LayoutParams(-2,-2); lp.topMargin=12; hero.addView(langs,lp);
    TextView version=pill("LecRizz v1.0"); LinearLayout.LayoutParams vp=new LinearLayout.LayoutParams(-2,-2); vp.topMargin=10; hero.addView(version,vp);

    Space gap=new Space(this); root.addView(gap,new LinearLayout.LayoutParams(1,16));
    LinearLayout panel=new LinearLayout(this); panel.setOrientation(LinearLayout.VERTICAL); panel.setPadding(20,20,20,20); panel.setBackgroundResource(R.drawable.glass_panel); root.addView(panel,new LinearLayout.LayoutParams(-1,-2));
    TextView h=text("Set up LecRizz",24); h.setTypeface(Typeface.DEFAULT,Typeface.BOLD); panel.addView(h);
    TextView body=text("Use Rizz and Translate directly above your keyboard — without leaving your chat app.",15); body.setTextColor(muted); panel.addView(body);
    Button next=btn("Get started  →"); next.setOnClickListener(v->showGuide(1)); LinearLayout.LayoutParams bp=new LinearLayout.LayoutParams(-1,54); bp.topMargin=18; panel.addView(next,bp);
  }

  void showGuide(int step){
    base();
    TextView brand=text("LecRizz",20); brand.setTextColor(purple); brand.setTypeface(Typeface.DEFAULT,Typeface.BOLD); root.addView(brand);
    Space gap=new Space(this);root.addView(gap,new LinearLayout.LayoutParams(1,25));
    String title="",body=""; if(step==1){title="1  Enable LecRizz";body="Android needs you to enable LecRizz as an available keyboard.";} else if(step==2){title="2  Switch to LecRizz";body="When you're typing, choose LecRizz from the keyboard picker.";} else {title="3  You're ready";body="Rizz and Translate appear above the normal keys. The space bar stays normal.";}
    TextView tt=text(title,28);tt.setTypeface(Typeface.DEFAULT,Typeface.BOLD);root.addView(tt);TextView bb=text(body,16);bb.setTextColor(muted);root.addView(bb);
    Space s=new Space(this);root.addView(s,new LinearLayout.LayoutParams(1,24));
    Button action=btn(step<3?"Open keyboard settings":"Open keyboard picker"); action.setOnClickListener(v->{ if(step<3) startActivity(new Intent("android.settings.INPUT_METHOD_SETTINGS")); else ((InputMethodManager)getSystemService(INPUT_METHOD_SERVICE)).showInputMethodPicker(); }); root.addView(action,new LinearLayout.LayoutParams(-1,54));
    Button next=btn(step<3?"Next  →":"Finish"); next.setOnClickListener(v->{if(step<3)showGuide(step+1);else showDone();}); LinearLayout.LayoutParams np=new LinearLayout.LayoutParams(-1,54);np.topMargin=12;root.addView(next,np);
  }

  void showDone(){base();TextView t=text("LecRizz is ready.",28);t.setTypeface(Typeface.DEFAULT,Typeface.BOLD);root.addView(t);TextView b=text("Open any chat app, switch to LecRizz, and you'll see the action bar above the keyboard.",16);b.setTextColor(muted);root.addView(b);Button x=btn("Open keyboard picker");x.setOnClickListener(v->((InputMethodManager)getSystemService(INPUT_METHOD_SERVICE)).showInputMethodPicker());LinearLayout.LayoutParams p=new LinearLayout.LayoutParams(-1,54);p.topMargin=20;root.addView(x,p);}
}
