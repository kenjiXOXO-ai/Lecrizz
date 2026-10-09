package com.lecrizz.app;

import android.inputmethodservice.InputMethodService;
import android.view.*;
import android.view.inputmethod.InputConnection;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.widget.*;
import java.util.Locale;

public class LecRizzImeService extends InputMethodService {
  LinearLayout root, keys;
  int purple=Color.rgb(168,140,255);
  GradientDrawable bg(int c,float r){GradientDrawable g=new GradientDrawable();g.setColor(c);g.setCornerRadius(r);return g;}
  TextView key(String s){TextView t=new TextView(this);t.setText(s);t.setTextColor(Color.WHITE);t.setTextSize(17);t.setGravity(Gravity.CENTER);t.setBackground(bg(Color.rgb(28,30,40),18));t.setOnClickListener(v->{InputConnection ic=getCurrentInputConnection();if(ic!=null)ic.commitText(s,1);});return t;}
  @Override public View onCreateInputView(){
    root=new LinearLayout(this);root.setOrientation(LinearLayout.VERTICAL);root.setPadding(7,7,7,7);root.setBackgroundColor(Color.rgb(9,10,15));
    LinearLayout actions=new LinearLayout(this);actions.setOrientation(LinearLayout.HORIZONTAL);
    TextView r=key("✦  Rizz");r.setTextColor(Color.WHITE);r.setBackground(bg(purple,22));r.setOnClickListener(v->insertRizz());
    TextView tr=key("⇄  Translate");tr.setOnClickListener(v->insertTranslation());
    actions.addView(r,new LinearLayout.LayoutParams(0,52,1));actions.addView(tr,new LinearLayout.LayoutParams(0,52,1));root.addView(actions);
    keys=new LinearLayout(this);keys.setOrientation(LinearLayout.VERTICAL);String[] rows={"QWERTYUIOP","ASDFGHJKL","ZXCVBNM"};for(String row:rows){LinearLayout line=new LinearLayout(this);for(char c:row.toCharArray()){TextView k=key(String.valueOf(c));line.addView(k,new LinearLayout.LayoutParams(0,55,1));}root.addView(line);}TextView space=key("space");space.setOnClickListener(v->{InputConnection ic=getCurrentInputConnection();if(ic!=null)ic.commitText(" ",1);});root.addView(space,new LinearLayout.LayoutParams(-1,55));return root;
  }
  void insertRizz(){InputConnection ic=getCurrentInputConnection();if(ic==null)return;CharSequence before=ic.getTextBeforeCursor(160,0);String in=before==null?"":before.toString().trim();String out=in.isEmpty()?"what you doing rn? 😭":"okayyy I see you 😏 "+in;ic.commitText(out,1);}
  void insertTranslation(){InputConnection ic=getCurrentInputConnection();if(ic==null)return;CharSequence before=ic.getTextBeforeCursor(160,0);String in=before==null?"":before.toString().trim();String out=in.isEmpty()?"Type something to translate":"Translation: "+in;ic.commitText(out,1);}
}
