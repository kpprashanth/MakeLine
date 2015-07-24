package com.example.prashanth.theline;

import android.content.ClipData;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.SoundPool;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsoluteLayout;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.view.View.OnDragListener;
import android.view.View.OnLongClickListener;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;


public class MainActivity extends ActionBarActivity /*implements GestureDetector.OnGestureListener*/ {

    ImageView circle1,circle2,circle3,circle4,circle5,circle6,circle7,circle8,circle9;
    ImageView circles[] = new ImageView[9];
    ImageView yellowcrl,browncrl,emptycrl,redcircle;
    Button refresh,singleplayer,multiplayer,setting,help;
    float x1,x2,y1,y2;
    private static int player=0,total=0;
    public static int yellowdrawableId=0,browndrawableId=0;
    GestureDetector detector;
    public static String dragtag;
    public static boolean canplace=false,won=false;
    private static String IMAGEVIEW_TAG = "Android Logo",playername1,playername2;
    public TextView result,nextplayer;
    public static String yellowImgName="newyellow",brownImgName="newbrown";
    LinearLayout parentpanel;
    RelativeLayout gamelayout;
    static SoundPool sp;
   static  int soundIds[];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         sp = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
         soundIds = new int[10];
        soundIds[0] = sp.load(getApplicationContext(), R.raw.hangouts_message, 1);
        soundIds[1] = sp.load(getApplicationContext(), R.raw.clapping, 1);

        parentpanel=(LinearLayout) findViewById(R.id.parentpanel);
        gamelayout=(RelativeLayout) findViewById(R.id.gamelayout);

       yellowcrl=(ImageView) findViewById(R.id.yelllowhided);
        browncrl=(ImageView) findViewById(R.id.brownhided);
        emptycrl=(ImageView) findViewById(R.id.emptycircle);
        redcircle=(ImageView) findViewById(R.id.redcircle);

        result=(TextView)    findViewById(R.id.result);
        nextplayer=(TextView)    findViewById(R.id.nextplayer);


        circles[0]=(ImageView) findViewById(R.id.circle1);
        circles[1]=(ImageView) findViewById(R.id.circle2);
        circles[2]=(ImageView) findViewById(R.id.circle3);
        circles[3]=(ImageView) findViewById(R.id.circle4);
        circles[4]=(ImageView) findViewById(R.id.circle5);
        circles[5]=(ImageView) findViewById(R.id.circle6);
        circles[6]=(ImageView) findViewById(R.id.circle7);
        circles[7]=(ImageView) findViewById(R.id.circle8);
        circles[8]=(ImageView) findViewById(R.id.circle9);

        circle1=(ImageView) findViewById(R.id.circle1);
        circle2=(ImageView) findViewById(R.id.circle2);
        circle3=(ImageView) findViewById(R.id.circle3);
        circle4=(ImageView) findViewById(R.id.circle4);
        circle5=(ImageView) findViewById(R.id.circle5);
        circle6=(ImageView) findViewById(R.id.circle6);
        circle7=(ImageView) findViewById(R.id.circle7);
        circle8=(ImageView) findViewById(R.id.circle8);
        circle9=(ImageView) findViewById(R.id.circle9);


        circle1.setTag("crl1");
        circle2.setTag("crl2");
        circle3.setTag("crl3");
        circle4.setTag("crl4");
        circle5.setTag("crl5");
        circle6.setTag("crl6");
        circle7.setTag("crl7");
        circle8.setTag("crl8");
        circle9.setTag("crl9");

        Log.v("Arrayyyyyyyyy-------------------dsfrerg", "------------------Tag= " + circles[0].getTag());

        circle1.setOnLongClickListener(longListener);
        circle2.setOnLongClickListener(longListener);
        circle3.setOnLongClickListener(longListener);
        circle4.setOnLongClickListener(longListener);
        circle5.setOnLongClickListener(longListener);
        circle6.setOnLongClickListener(longListener);
        circle7.setOnLongClickListener(longListener);
        circle8.setOnLongClickListener(longListener);
        circle9.setOnLongClickListener(longListener);


        circle5.setOnDragListener(dragListener);
        circle1.setOnDragListener(dragListener);
        circle2.setOnDragListener(dragListener);
        circle3.setOnDragListener(dragListener);
        circle4.setOnDragListener(dragListener);
        circle6.setOnDragListener(dragListener);
        circle7.setOnDragListener(dragListener);
        circle8.setOnDragListener(dragListener);
        circle9.setOnDragListener(dragListener);
        //circle4.setOnDragListener(dragListener);



        yellowcrl.setImageResource(R.drawable.newyellow);
       browncrl.setImageResource(R.drawable.newbrown);
        result.setTextColor(Color.GREEN);
        yellowdrawableId = getResources().getIdentifier(yellowImgName, "drawable", getPackageName());
        browndrawableId = getResources().getIdentifier(brownImgName, "drawable", getPackageName());

        refresh=(Button) findViewById(R.id.refresh);
        multiplayer=(Button) findViewById(R.id.multiplayer);
        setting=(Button) findViewById(R.id.setting);
        help=(Button) findViewById(R.id.help);

        refresh.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                circle1.setImageResource(R.drawable.circleimg);
                circle2.setImageResource(R.drawable.circleimg);
                circle3.setImageResource(R.drawable.circleimg);
                circle4.setImageResource(R.drawable.circleimg);
                circle5.setImageResource(R.drawable.circleimg);
                circle6.setImageResource(R.drawable.circleimg);
                circle7.setImageResource(R.drawable.circleimg);
                circle8.setImageResource(R.drawable.circleimg);
                circle9.setImageResource(R.drawable.circleimg);
                total=0;player=0;
                nextplayer.setText(playername1+ "'s turn");
                result.setVisibility(TextView.INVISIBLE);
                nextplayer.setVisibility(TextView.VISIBLE);
                won=false;
                if("SOUND ON".equals(setting.getText().toString())){
                    soundIds[0] = sp.load(getApplicationContext(), R.raw.hangouts_message, 1);

                }
                else {
                   // soundIds[0] = sp.load(getApplicationContext(), R.raw.MUTE, 1);
                }

            }
        });

       multiplayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText  play1=(EditText) findViewById(R.id.player1);
                EditText  play2=(EditText) findViewById(R.id.player2);
                TextView namecheck=(TextView) findViewById(R.id.namecheck);
                playername1=play1.getText().toString();
                playername2=play2.getText().toString();

                if(playername1.equals("") || playername2.equals("")){
                    namecheck.setVisibility(TextView.VISIBLE);
                }
                else {
                    parentpanel.setVisibility(LinearLayout.INVISIBLE);
                    gamelayout.setVisibility(LinearLayout.VISIBLE);
                    namecheck.setVisibility(TextView.INVISIBLE);
                    nextplayer.setText(playername1+ "'s turn");

                }

            }
        });

        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String on=setting.getText().toString();
                if(on.equals("SOUND ON")){
                    setting.setText("SOUND OFF");
                   // soundIds[0] = sp.load(getApplicationContext(), R.raw.MUTE, 1);

                }
                else{
                    setting.setText("SOUND ON");
                    soundIds[0] = sp.load(getApplicationContext(), R.raw.hangouts_message, 1);
                }

            }
        });

        circle1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if( !(yellowcrl.getDrawable().getConstantState().equals(circle1.getDrawable().getConstantState()) ||
                        (browncrl.getDrawable().getConstantState().equals(circle1.getDrawable().getConstantState())) )  && total<6 ) {

                    if(player%2==0) {
                        circle1.setImageResource(R.drawable.newyellow);
                        nextplayer.setText(playername2 + "'s turn");
                        total++;
                        if("SOUND ON".equals(setting.getText().toString())) {
                            sp.play(soundIds[0], 1, 1, 1, 0, 1);
                        }
                    }

                    else{
                        circle1.setImageResource(R.drawable.newbrown);
                        total++;
                        nextplayer.setText(playername1+"'s turn");
                        if("SOUND ON".equals(setting.getText().toString())) {
                            sp.play(soundIds[0], 1, 1, 1, 0, 1);
                        }
                    }

                    linecheck((String)circle1.getTag(),player);
                    player++;

                }




            }

        });





        //hcircle2.setOnDragListener();

        circle2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if( !(yellowcrl.getDrawable().getConstantState().equals(circle2.getDrawable().getConstantState()) ||
                        (browncrl.getDrawable().getConstantState().equals(circle2.getDrawable().getConstantState())) ) && total<6 && won==false) {

                    if(player%2==0) {
                        circle2.setImageResource(R.drawable.newyellow);
                        nextplayer.setText(playername2+"'s turn");
                        total++;
                        if("SOUND ON".equals(setting.getText().toString())) {
                            sp.play(soundIds[0], 1, 1, 1, 0, 1);
                        }
                    }

                    else{
                        total++;
                        circle2.setImageResource(R.drawable.newbrown);
                        nextplayer.setText(playername1+"'s turn");
                        if("SOUND ON".equals(setting.getText().toString())) {
                            sp.play(soundIds[0], 1, 1, 1, 0, 1);
                        }
                    }

                    linecheck((String)circle2.getTag(),player);
                    player++;

                }


            }

        });


        circle3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                if( !(yellowcrl.getDrawable().getConstantState().equals(circle3.getDrawable().getConstantState()) ||
                        (browncrl.getDrawable().getConstantState().equals(circle3.getDrawable().getConstantState())) ) && total<6 && won==false) {

                    if(player%2==0) {
                        circle3.setImageResource(R.drawable.newyellow);
                        nextplayer.setText(playername2 + "'s turn");
                        total++;
                        if("SOUND ON".equals(setting.getText().toString())) {
                            sp.play(soundIds[0], 1, 1, 1, 0, 1);
                        }
                    }

                    else{
                        circle3.setImageResource(R.drawable.newbrown);
                        total++;
                        nextplayer.setText(playername1+"'s turn");
                        if("SOUND ON".equals(setting.getText().toString())) {
                            sp.play(soundIds[0], 1, 1, 1, 0, 1);
                        }
                    }
                    linecheck((String)circle3.getTag(),player);
                    player++;
                }


            }




        });


        circle4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                if( !(yellowcrl.getDrawable().getConstantState().equals(circle4.getDrawable().getConstantState()) ||
                        (browncrl.getDrawable().getConstantState().equals(circle4.getDrawable().getConstantState())) ) && total<6 && won==false) {

                    if(player%2==0) {
                        circle4.setImageResource(R.drawable.newyellow);
                        total++;
                        nextplayer.setText(playername2+"'s turn");
                        if("SOUND ON".equals(setting.getText().toString())) {
                            sp.play(soundIds[0], 1, 1, 1, 0, 1);
                        }
                    }

                    else{
                        circle4.setImageResource(R.drawable.newbrown);
                        total++;
                        nextplayer.setText(playername1+"'s turn");
                        if("SOUND ON".equals(setting.getText().toString())) {
                            sp.play(soundIds[0], 1, 1, 1, 0, 1);
                        }
                    }
                    linecheck((String)circle4.getTag(),player);
                    player++;
                }




            }

        });


        circle5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if( !(yellowcrl.getDrawable().getConstantState().equals(circle5.getDrawable().getConstantState()) ||
                        (browncrl.getDrawable().getConstantState().equals(circle5.getDrawable().getConstantState())) ) && total<6 && total>1 && won==false) {

                    if(player%2==0) {
                        total++;
                        circle5.setImageResource(R.drawable.newyellow);
                        nextplayer.setText(playername2+"'s turn");
                        if("SOUND ON".equals(setting.getText().toString())) {
                            sp.play(soundIds[0], 1, 1, 1, 0, 1);
                        }
                    }

                    else{
                        total++;
                        circle5.setImageResource(R.drawable.newbrown);
                        nextplayer.setText(playername1+"'s turn");
                        if("SOUND ON".equals(setting.getText().toString())) {
                            sp.play(soundIds[0], 1, 1, 1, 0, 1);
                        }
                    }
                    linecheck((String)circle5.getTag(),player);
                    player++;

                }


            }

        });

        circle6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if( !(yellowcrl.getDrawable().getConstantState().equals(circle6.getDrawable().getConstantState()) ||
                        (browncrl.getDrawable().getConstantState().equals(circle6.getDrawable().getConstantState())) )  && total<6 && won==false) {

                    if(player%2==0) {
                        circle6.setImageResource(R.drawable.newyellow);
                        total++;
                        nextplayer.setText(playername2+"'s turn");
                        if("SOUND ON".equals(setting.getText().toString())) {
                            sp.play(soundIds[0], 1, 1, 1, 0, 1);
                        }
                    }

                    else{
                        circle6.setImageResource(R.drawable.newbrown);
                        total++;
                        nextplayer.setText(playername1+"'s turn");
                        if("SOUND ON".equals(setting.getText().toString())) {
                            sp.play(soundIds[0], 1, 1, 1, 0, 1);
                        }
                    }
                    linecheck((String)circle6.getTag(),player);
                    player++;

                }


            }

        });


        circle7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                if( !(yellowcrl.getDrawable().getConstantState().equals(circle7.getDrawable().getConstantState()) ||
                        (browncrl.getDrawable().getConstantState().equals(circle7.getDrawable().getConstantState())) ) && total<6 && won==false) {

                    if(player%2==0) {
                        circle7.setImageResource(R.drawable.newyellow);
                        total++;
                        nextplayer.setText(playername2+"'s turn");
                        if("SOUND ON".equals(setting.getText().toString())) {
                            sp.play(soundIds[0], 1, 1, 1, 0, 1);
                        }

                    }

                    else{
                        total++;
                        circle7.setImageResource(R.drawable.newbrown);
                        nextplayer.setText(playername1+"'s turn");
                        if("SOUND ON".equals(setting.getText().toString())) {
                            sp.play(soundIds[0], 1, 1, 1, 0, 1);
                        }
                    }
                    linecheck((String)circle7.getTag(),player);
                    player++;

                }


            }

        });


        circle8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                if( !(yellowcrl.getDrawable().getConstantState().equals(circle8.getDrawable().getConstantState()) ||
                        (browncrl.getDrawable().getConstantState().equals(circle8.getDrawable().getConstantState())) ) && total<6 && won==false) {

                    if(player%2==0) {
                        circle8.setImageResource(R.drawable.newyellow);
                        total++;
                        nextplayer.setText(playername2+"'s turn");
                        if("SOUND ON".equals(setting.getText().toString())) {
                            sp.play(soundIds[0], 1, 1, 1, 0, 1);
                        }
                    }

                    else{
                        circle8.setImageResource(R.drawable.newbrown);
                        total++;
                        nextplayer.setText(playername1+"'s turn");
                        if("SOUND ON".equals(setting.getText().toString())) {
                            sp.play(soundIds[0], 1, 1, 1, 0, 1);
                        }
                    }
                    linecheck((String)circle8.getTag(),player);

                    player++;
                }


            }

        });

        circle9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                if( !(yellowcrl.getDrawable().getConstantState().equals(circle9.getDrawable().getConstantState()) ||
                        (browncrl.getDrawable().getConstantState().equals(circle9.getDrawable().getConstantState())) )  && total<6 && won==false) {


                    if(player%2==0) {
                        circle9.setImageResource(R.drawable.newyellow);
                        total++;
                        nextplayer.setText(playername2+"'s turn");
                        if("SOUND ON".equals(setting.getText().toString())) {
                            sp.play(soundIds[0], 1, 1, 1, 0, 1);
                        }
                    }

                    else{
                        circle9.setImageResource(R.drawable.newbrown);
                        total++;
                        nextplayer.setText(playername1+"'s turn");
                        if("SOUND ON".equals(setting.getText().toString())) {
                            sp.play(soundIds[0], 1, 1, 1, 0, 1);
                        }
                    }
                    linecheck((String)circle9.getTag(),player);
                    player++;
                    //total++;
                }


            }

        });

    }

    OnLongClickListener longListener = new OnLongClickListener(){
        @Override
        public boolean onLongClick(View v) {
            if (total >= 6 && won==false) {
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder myShadow = new View.DragShadowBuilder(v);

                v.startDrag(data, myShadow, v, 0);

            }
            return true;
        }
    };

    OnDragListener dragListener = new OnDragListener()
    {
        @Override
        public boolean onDrag(View v, DragEvent event)
        {
            int dragEvent = event.getAction();
            String selected="";
            String msg="hello";
            canplace=false;
            switch(dragEvent)
            {

                case DragEvent.ACTION_DRAG_STARTED:
                    ImageView draggedstartText = (ImageView)event.getLocalState();
                    selected=draggedstartText.getTag().toString();
                    switch (selected){

                        case "crl1":
                            if( (player%2==0 && draggedstartText.getDrawable().getConstantState().equals(yellowcrl.getDrawable().getConstantState())) ||
                                  (player%2==1 &&  draggedstartText.getDrawable().getConstantState().equals(browncrl.getDrawable().getConstantState())) )

                            {
                                if (circle2.getDrawable().getConstantState().equals(emptycrl.getDrawable().getConstantState()))
                                    circle2.setImageResource(R.drawable.redcircle);

                                if (circle5.getDrawable().getConstantState().equals(emptycrl.getDrawable().getConstantState()))
                                    circle5.setImageResource(R.drawable.redcircle);

                                if (circle4.getDrawable().getConstantState().equals(emptycrl.getDrawable().getConstantState()))
                                    circle4.setImageResource(R.drawable.redcircle);
                            }
                            break;

                        case "crl2":
                            if( (player%2==0 && draggedstartText.getDrawable().getConstantState().equals(yellowcrl.getDrawable().getConstantState())) ||
                                    (player%2==1 &&  draggedstartText.getDrawable().getConstantState().equals(browncrl.getDrawable().getConstantState())) )
                            {
                                if (circle1.getDrawable().getConstantState().equals(emptycrl.getDrawable().getConstantState()))
                                    circle1.setImageResource(R.drawable.redcircle);

                                if (circle3.getDrawable().getConstantState().equals(emptycrl.getDrawable().getConstantState()))
                                    circle3.setImageResource(R.drawable.redcircle);

                                if (circle5.getDrawable().getConstantState().equals(emptycrl.getDrawable().getConstantState()))
                                    circle5.setImageResource(R.drawable.redcircle);
                            }
                            break;

                        case "crl3":
                            if( (player%2==0 && draggedstartText.getDrawable().getConstantState().equals(yellowcrl.getDrawable().getConstantState())) ||
                                    (player%2==1 &&  draggedstartText.getDrawable().getConstantState().equals(browncrl.getDrawable().getConstantState())) ) {

                                if (circle2.getDrawable().getConstantState().equals(emptycrl.getDrawable().getConstantState()))
                                    circle2.setImageResource(R.drawable.redcircle);

                                if (circle5.getDrawable().getConstantState().equals(emptycrl.getDrawable().getConstantState()))
                                    circle5.setImageResource(R.drawable.redcircle);

                                if (circle6.getDrawable().getConstantState().equals(emptycrl.getDrawable().getConstantState()))
                                    circle6.setImageResource(R.drawable.redcircle);
                            }
                            break;

                        case "crl4":
                            if( (player%2==0 && draggedstartText.getDrawable().getConstantState().equals(yellowcrl.getDrawable().getConstantState())) ||
                                    (player%2==1 &&  draggedstartText.getDrawable().getConstantState().equals(browncrl.getDrawable().getConstantState())) ) {

                                if (circle1.getDrawable().getConstantState().equals(emptycrl.getDrawable().getConstantState()))
                                    circle1.setImageResource(R.drawable.redcircle);

                                if (circle5.getDrawable().getConstantState().equals(emptycrl.getDrawable().getConstantState()))
                                    circle5.setImageResource(R.drawable.redcircle);

                                if (circle7.getDrawable().getConstantState().equals(emptycrl.getDrawable().getConstantState()))
                                    circle7.setImageResource(R.drawable.redcircle);
                            }
                            break;

                        case "crl6":
                            if( (player%2==0 && draggedstartText.getDrawable().getConstantState().equals(yellowcrl.getDrawable().getConstantState())) ||
                                    (player%2==1 &&  draggedstartText.getDrawable().getConstantState().equals(browncrl.getDrawable().getConstantState())) ) {

                                if (circle3.getDrawable().getConstantState().equals(emptycrl.getDrawable().getConstantState()))
                                    circle3.setImageResource(R.drawable.redcircle);

                                if (circle5.getDrawable().getConstantState().equals(emptycrl.getDrawable().getConstantState()))
                                    circle5.setImageResource(R.drawable.redcircle);

                                if (circle9.getDrawable().getConstantState().equals(emptycrl.getDrawable().getConstantState()))
                                    circle9.setImageResource(R.drawable.redcircle);
                            }

                            break;

                        case "crl7":
                            if( (player%2==0 && draggedstartText.getDrawable().getConstantState().equals(yellowcrl.getDrawable().getConstantState())) ||
                                    (player%2==1 &&  draggedstartText.getDrawable().getConstantState().equals(browncrl.getDrawable().getConstantState())) )
                            {

                                    if (circle4.getDrawable().getConstantState().equals(emptycrl.getDrawable().getConstantState()))
                                    circle4.setImageResource(R.drawable.redcircle);

                                     if (circle5.getDrawable().getConstantState().equals(emptycrl.getDrawable().getConstantState()))
                                    circle5.setImageResource(R.drawable.redcircle);

                                    if (circle8.getDrawable().getConstantState().equals(emptycrl.getDrawable().getConstantState()))
                                    circle8.setImageResource(R.drawable.redcircle);
                                }

                            break;

                        case "crl8":
                            if( (player%2==0 && draggedstartText.getDrawable().getConstantState().equals(yellowcrl.getDrawable().getConstantState())) ||
                                    (player%2==1 &&  draggedstartText.getDrawable().getConstantState().equals(browncrl.getDrawable().getConstantState())) ) {

                                if (circle7.getDrawable().getConstantState().equals(emptycrl.getDrawable().getConstantState()))
                                    circle7.setImageResource(R.drawable.redcircle);

                                if (circle5.getDrawable().getConstantState().equals(emptycrl.getDrawable().getConstantState()))
                                    circle5.setImageResource(R.drawable.redcircle);

                                if (circle9.getDrawable().getConstantState().equals(emptycrl.getDrawable().getConstantState()))
                                    circle9.setImageResource(R.drawable.redcircle);
                            }
                            break;

                        case "crl9":
                            if( (player%2==0 && draggedstartText.getDrawable().getConstantState().equals(yellowcrl.getDrawable().getConstantState())) ||
                                    (player%2==1 &&  draggedstartText.getDrawable().getConstantState().equals(browncrl.getDrawable().getConstantState())) ) {

                                if (circle8.getDrawable().getConstantState().equals(emptycrl.getDrawable().getConstantState()))
                                    circle8.setImageResource(R.drawable.redcircle);

                                if (circle5.getDrawable().getConstantState().equals(emptycrl.getDrawable().getConstantState()))
                                    circle5.setImageResource(R.drawable.redcircle);

                                if (circle6.getDrawable().getConstantState().equals(emptycrl.getDrawable().getConstantState()))
                                    circle6.setImageResource(R.drawable.redcircle);
                            }
                            break;

                        case "crl5":
                            if( (player%2==0 && draggedstartText.getDrawable().getConstantState().equals(yellowcrl.getDrawable().getConstantState())) ||
                                    (player%2==1 &&  draggedstartText.getDrawable().getConstantState().equals(browncrl.getDrawable().getConstantState())) ) {


                                for (int i = 0; i < circles.length; i++) {
                                    if (circles[i].getDrawable().getConstantState().equals(emptycrl.getDrawable().getConstantState()))
                                        circles[i].setImageResource(R.drawable.redcircle);
                                }
                            }
                           break;
                    }

                    break;



                case DragEvent.ACTION_DRAG_ENTERED:
                    //dropText.setTextColor(Color.GREEN);
                    break;

                case DragEvent.ACTION_DRAG_EXITED:
                    //dropText.setTextColor(Color.RED);
                    break;

                case DragEvent.ACTION_DRAG_LOCATION  :


                    break;

                case DragEvent.ACTION_DROP:
                    Log.i(msg, "Action is DragEvent.ACTION_DRAG_LOCATION x: "+event.getX()+" y: "+event.getY());
                      ImageView draggedText = (ImageView)event.getLocalState();
                      ImageView dropText = (ImageView) v;
                      selected=(String)draggedText.getTag();
                      Boolean propdrag=false;

                    if(player%2==0 && yellowcrl.getDrawable().getConstantState().equals(draggedText.getDrawable().getConstantState()) ){
                                     propdrag=true;
                            }
                    else if(player%2!=0 && browncrl.getDrawable().getConstantState().equals(draggedText.getDrawable().getConstantState()) ){
                        propdrag=true;
                    }
                    else
                        propdrag=false;


                    switch (selected){

                        case "crl1":

                            if (dropText.getTag().equals("crl2") || dropText.getTag().equals("crl4") || dropText.getTag().equals("crl5")){
                               canplace=true;


                            }
                            break;


                        case "crl2":
                            if (dropText.getTag().equals("crl1") || dropText.getTag().equals("crl3") || dropText.getTag().equals("crl5")){
                                canplace=true;
                            }
                            break;


                        case "crl3":
                            if (dropText.getTag().equals("crl2") || dropText.getTag().equals("crl6") || dropText.getTag().equals("crl5")){
                                canplace=true;
                            }
                            break;


                        case "crl4":
                            if (dropText.getTag().equals("crl1") || dropText.getTag().equals("crl7") || dropText.getTag().equals("crl5")){
                                canplace=true;
                            }
                            break;


                        case "crl6":
                            if (dropText.getTag().equals("crl3") || dropText.getTag().equals("crl9") || dropText.getTag().equals("crl5")){
                                canplace=true;
                            }
                            break;


                        case "crl7":
                            if (dropText.getTag().equals("crl4") || dropText.getTag().equals("crl8") || dropText.getTag().equals("crl5")){
                                canplace=true;
                            }
                            break;


                        case "crl8":
                            if (dropText.getTag().equals("crl7") || dropText.getTag().equals("crl9") || dropText.getTag().equals("crl5")){
                                canplace=true;
                            }
                            break;


                        case "crl9":
                            if (dropText.getTag().equals("crl8") || dropText.getTag().equals("crl6") || dropText.getTag().equals("crl5")){
                                canplace=true;
                            }
                            break;

                        case "crl5":
                            canplace=true;
                            break;

                     }

                   // Log.v("","");
                   if( dropText.getDrawable().getConstantState().equals(redcircle.getDrawable().getConstantState()) && canplace==true && propdrag==true ){

                        dropText.setImageDrawable(draggedText.getDrawable());

                       if("SOUND ON".equals(setting.getText().toString())) {
                           sp.play(soundIds[0], 1, 1, 1, 0, 1);
                       }
                        draggedText.setImageDrawable(emptycrl.getDrawable());

                       linecheck((String)dropText.getTag(),player);
                       if(player%2==0)
                           nextplayer.setText(playername2+"'s turn");
                       else
                            nextplayer.setText(playername1+"'s turn");


                        player++;

                    }

                    break;

                case DragEvent.ACTION_DRAG_ENDED   :
                    Log.d(msg, "Action is DragEvent.ACTION_DRAG_ENDED");

                    for(int i=0;i<circles.length;i++){
                        if(circles[i].getDrawable().getConstantState().equals(redcircle.getDrawable().getConstantState())){
                            circles[i].setImageDrawable(emptycrl.getDrawable());
                        }
                    }


                    // Do nothing
                    break;

            }

            return true;
        }

    };


    public void linecheck(String placed,int playername){

       // Log.v("The functiom checking_____________________________","place: "+placed+"__________playername: "+playername);
        String colour="";
       if(playername%2==0)
           colour="yellow";
        else
            colour="brown";

        switch (placed){

            case "crl1":
              if(colour.equals("yellow")){

                  if (
                          (yellowcrl.getDrawable().getConstantState().equals(circle2.getDrawable().getConstantState()) && yellowcrl.getDrawable().getConstantState().equals(circle3.getDrawable().getConstantState())) ||
                                  (yellowcrl.getDrawable().getConstantState().equals(circle5.getDrawable().getConstantState()) && yellowcrl.getDrawable().getConstantState().equals(circle9.getDrawable().getConstantState())) ||
                                  (yellowcrl.getDrawable().getConstantState().equals(circle4.getDrawable().getConstantState()) && yellowcrl.getDrawable().getConstantState().equals(circle7.getDrawable().getConstantState()))
                          ) {
                                  result.setText(playername1+" Won The Match");
                                    won=true;
                                  result.setVisibility(TextView.VISIBLE);
                                  nextplayer.setVisibility(TextView.INVISIBLE);
                      if("SOUND ON".equals(setting.getText().toString())) {
                          sp.play(soundIds[1], 1, 1, 1, 0, 1);
                      }

                                    }}
              else{

                      if (
                              (browncrl.getDrawable().getConstantState().equals(circle2.getDrawable().getConstantState()) && browncrl.getDrawable().getConstantState().equals(circle3.getDrawable().getConstantState())) ||
                                      (browncrl.getDrawable().getConstantState().equals(circle5.getDrawable().getConstantState()) && browncrl.getDrawable().getConstantState().equals(circle9.getDrawable().getConstantState())) ||
                                      (browncrl.getDrawable().getConstantState().equals(circle4.getDrawable().getConstantState()) && browncrl.getDrawable().getConstantState().equals(circle7.getDrawable().getConstantState()))
                              ) {
                                        result.setText(playername2+" Won The Match");won=true;
                                        result.setVisibility(TextView.VISIBLE);
                                        nextplayer.setVisibility(TextView.INVISIBLE);
                          if("SOUND ON".equals(setting.getText().toString())) {
                              sp.play(soundIds[1], 1, 1, 1, 0, 1);
                          }
                                         }

                  }
              break;


            case "crl2":
                if(colour.equals("yellow")){

                    if (
                            (yellowcrl.getDrawable().getConstantState().equals(circle1.getDrawable().getConstantState()) && yellowcrl.getDrawable().getConstantState().equals(circle3.getDrawable().getConstantState())) ||
                            (yellowcrl.getDrawable().getConstantState().equals(circle5.getDrawable().getConstantState()) && yellowcrl.getDrawable().getConstantState().equals(circle8.getDrawable().getConstantState()))
                            ) {
                        result.setText(playername1+" Won The Match");won=true;
                        result.setVisibility(TextView.VISIBLE);
                        nextplayer.setVisibility(TextView.INVISIBLE);
                        if("SOUND ON".equals(setting.getText().toString())) {
                            sp.play(soundIds[1], 1, 1, 1, 0, 1);
                        }

                    }}
                    else{

                        if (
                                (browncrl.getDrawable().getConstantState().equals(circle1.getDrawable().getConstantState()) && browncrl.getDrawable().getConstantState().equals(circle3.getDrawable().getConstantState())) ||
                                        (browncrl.getDrawable().getConstantState().equals(circle5.getDrawable().getConstantState()) && browncrl.getDrawable().getConstantState().equals(circle8.getDrawable().getConstantState()))
                                )  {
                            result.setText(playername2+" Won The Match");won=true;
                            result.setVisibility(TextView.VISIBLE);
                            nextplayer.setVisibility(TextView.INVISIBLE);
                            if("SOUND ON".equals(setting.getText().toString())) {
                                sp.play(soundIds[1], 1, 1, 1, 0, 1);
                            }

                        }

                    }
                break;



            case "crl3":
                if(colour.equals("yellow")){

                    if (
                            (yellowcrl.getDrawable().getConstantState().equals(circle2.getDrawable().getConstantState()) && yellowcrl.getDrawable().getConstantState().equals(circle1.getDrawable().getConstantState())) ||
                                    (yellowcrl.getDrawable().getConstantState().equals(circle5.getDrawable().getConstantState()) && yellowcrl.getDrawable().getConstantState().equals(circle7.getDrawable().getConstantState())) ||
                                    (yellowcrl.getDrawable().getConstantState().equals(circle6.getDrawable().getConstantState()) && yellowcrl.getDrawable().getConstantState().equals(circle9.getDrawable().getConstantState()))
                            ) {
                        result.setText(playername1+" Won The Match");won=true;
                        result.setVisibility(TextView.VISIBLE);
                        nextplayer.setVisibility(TextView.INVISIBLE);
                        if("SOUND ON".equals(setting.getText().toString())) {
                            sp.play(soundIds[1], 1, 1, 1, 0, 1);
                        }

                    }}
                    else{

                        if (
                                (browncrl.getDrawable().getConstantState().equals(circle2.getDrawable().getConstantState()) && browncrl.getDrawable().getConstantState().equals(circle1.getDrawable().getConstantState())) ||
                                        (browncrl.getDrawable().getConstantState().equals(circle5.getDrawable().getConstantState()) && browncrl.getDrawable().getConstantState().equals(circle7.getDrawable().getConstantState())) ||
                                        (browncrl.getDrawable().getConstantState().equals(circle6.getDrawable().getConstantState()) && browncrl.getDrawable().getConstantState().equals(circle9.getDrawable().getConstantState()))
                                ) {
                            result.setText(playername2+" Won The Match");won=true;
                            result.setVisibility(TextView.VISIBLE);

                            nextplayer.setVisibility(TextView.INVISIBLE);
                            if("SOUND ON".equals(setting.getText().toString())) {
                                sp.play(soundIds[1], 1, 1, 1, 0, 1);
                            }

                        }

                    }
                break;



            case "crl4":
                if(colour.equals("yellow")){

                    if (
                            (yellowcrl.getDrawable().getConstantState().equals(circle1.getDrawable().getConstantState()) && yellowcrl.getDrawable().getConstantState().equals(circle7.getDrawable().getConstantState())) ||
                                    (yellowcrl.getDrawable().getConstantState().equals(circle5.getDrawable().getConstantState()) && yellowcrl.getDrawable().getConstantState().equals(circle6.getDrawable().getConstantState()))
                            ) {
                        result.setText(playername1+" Won The Match");won=true;
                        result.setVisibility(TextView.VISIBLE);
                        nextplayer.setVisibility(TextView.INVISIBLE);
                        if("SOUND ON".equals(setting.getText().toString())) {
                            sp.play(soundIds[1], 1, 1, 1, 0, 1);
                        }

                    }}
                    else{

                        if (
                                (browncrl.getDrawable().getConstantState().equals(circle1.getDrawable().getConstantState()) && browncrl.getDrawable().getConstantState().equals(circle7.getDrawable().getConstantState())) ||
                                        (browncrl.getDrawable().getConstantState().equals(circle5.getDrawable().getConstantState()) && browncrl.getDrawable().getConstantState().equals(circle6.getDrawable().getConstantState()))
                                )  {
                            result.setText(playername2+" Won The Match");won=true;
                            result.setVisibility(TextView.VISIBLE);
                            nextplayer.setVisibility(TextView.INVISIBLE);
                            if("SOUND ON".equals(setting.getText().toString())) {
                                sp.play(soundIds[1], 1, 1, 1, 0, 1);
                            }

                        }

                    }
                break;


            case "crl6":
                if(colour.equals("yellow")){

                    if (
                            (yellowcrl.getDrawable().getConstantState().equals(circle3.getDrawable().getConstantState()) && yellowcrl.getDrawable().getConstantState().equals(circle9.getDrawable().getConstantState())) ||
                                    (yellowcrl.getDrawable().getConstantState().equals(circle5.getDrawable().getConstantState()) && yellowcrl.getDrawable().getConstantState().equals(circle4.getDrawable().getConstantState()))
                            ) {
                        result.setText(playername1+" Won The Match");won=true;
                        result.setVisibility(TextView.VISIBLE);
                        nextplayer.setVisibility(TextView.INVISIBLE);
                        if("SOUND ON".equals(setting.getText().toString())) {
                            sp.play(soundIds[1], 1, 1, 1, 0, 1);
                        }

                    }}
                    else{

                        if (
                                (browncrl.getDrawable().getConstantState().equals(circle3.getDrawable().getConstantState()) && browncrl.getDrawable().getConstantState().equals(circle9.getDrawable().getConstantState())) ||
                                        (browncrl.getDrawable().getConstantState().equals(circle5.getDrawable().getConstantState()) && browncrl.getDrawable().getConstantState().equals(circle4.getDrawable().getConstantState()))
                                )  {
                            result.setText(playername2+" Won The Match");won=true;
                            result.setVisibility(TextView.VISIBLE);
                            nextplayer.setVisibility(TextView.INVISIBLE);
                            if("SOUND ON".equals(setting.getText().toString())) {
                                sp.play(soundIds[1], 1, 1, 1, 0, 1);
                            }

                        }

                    }
                break;



            case "crl7":
                if(colour.equals("yellow")){

                    if (
                            (yellowcrl.getDrawable().getConstantState().equals(circle4.getDrawable().getConstantState()) && yellowcrl.getDrawable().getConstantState().equals(circle1.getDrawable().getConstantState())) ||
                                    (yellowcrl.getDrawable().getConstantState().equals(circle5.getDrawable().getConstantState()) && yellowcrl.getDrawable().getConstantState().equals(circle3.getDrawable().getConstantState())) ||
                                    (yellowcrl.getDrawable().getConstantState().equals(circle8.getDrawable().getConstantState()) && yellowcrl.getDrawable().getConstantState().equals(circle9.getDrawable().getConstantState()))
                            ) {
                        result.setText(playername1+" Won The Match");won=true;
                        result.setVisibility(TextView.VISIBLE);
                        nextplayer.setVisibility(TextView.INVISIBLE);
                        if("SOUND ON".equals(setting.getText().toString())) {
                            sp.play(soundIds[1], 1, 1, 1, 0, 1);
                        }
                    }}
                    else{

                        if (
                                (browncrl.getDrawable().getConstantState().equals(circle4.getDrawable().getConstantState()) && browncrl.getDrawable().getConstantState().equals(circle1.getDrawable().getConstantState())) ||
                                        (browncrl.getDrawable().getConstantState().equals(circle5.getDrawable().getConstantState()) && browncrl.getDrawable().getConstantState().equals(circle3.getDrawable().getConstantState())) ||
                                        (browncrl.getDrawable().getConstantState().equals(circle8.getDrawable().getConstantState()) && browncrl.getDrawable().getConstantState().equals(circle9.getDrawable().getConstantState()))
                                ) {
                            result.setText(playername2+" Won The Match");won=true;
                            result.setVisibility(TextView.VISIBLE);
                            nextplayer.setVisibility(TextView.INVISIBLE);
                            if("SOUND ON".equals(setting.getText().toString())) {
                                sp.play(soundIds[1], 1, 1, 1, 0, 1);
                            }

                        }

                    }
                break;



            case "crl8":
                if(colour.equals("yellow")){

                    if (
                            (yellowcrl.getDrawable().getConstantState().equals(circle7.getDrawable().getConstantState()) && yellowcrl.getDrawable().getConstantState().equals(circle9.getDrawable().getConstantState())) ||
                                    (yellowcrl.getDrawable().getConstantState().equals(circle5.getDrawable().getConstantState()) && yellowcrl.getDrawable().getConstantState().equals(circle2.getDrawable().getConstantState()))
                            ) {
                        result.setText(playername1+" Won The Match");won=true;
                        result.setVisibility(TextView.VISIBLE);
                        nextplayer.setVisibility(TextView.INVISIBLE);
                        if("SOUND ON".equals(setting.getText().toString())) {
                            sp.play(soundIds[1], 1, 1, 1, 0, 1);
                        }

                    }}
                    else{

                        if (
                                (browncrl.getDrawable().getConstantState().equals(circle7.getDrawable().getConstantState()) && browncrl.getDrawable().getConstantState().equals(circle9.getDrawable().getConstantState())) ||
                                        (browncrl.getDrawable().getConstantState().equals(circle5.getDrawable().getConstantState()) && browncrl.getDrawable().getConstantState().equals(circle2.getDrawable().getConstantState()))
                                )  {
                            result.setText(playername2+" Won The Match");won=true;
                            result.setVisibility(TextView.VISIBLE);
                            nextplayer.setVisibility(TextView.INVISIBLE);
                            if("SOUND ON".equals(setting.getText().toString())) {
                                sp.play(soundIds[1], 1, 1, 1, 0, 1);
                            }

                        }

                    }
                break;


            case "crl9":
                if(colour.equals("yellow")){

                    if (
                            (yellowcrl.getDrawable().getConstantState().equals(circle6.getDrawable().getConstantState()) && yellowcrl.getDrawable().getConstantState().equals(circle3.getDrawable().getConstantState())) ||
                                    (yellowcrl.getDrawable().getConstantState().equals(circle5.getDrawable().getConstantState()) && yellowcrl.getDrawable().getConstantState().equals(circle1.getDrawable().getConstantState())) ||
                                    (yellowcrl.getDrawable().getConstantState().equals(circle8.getDrawable().getConstantState()) && yellowcrl.getDrawable().getConstantState().equals(circle7.getDrawable().getConstantState()))
                            ) {
                        result.setText(playername1+" Won The Match");won=true;
                        result.setVisibility(TextView.VISIBLE);

                        if("SOUND ON".equals(setting.getText().toString())) {
                            sp.play(soundIds[1], 1, 1, 1, 0, 1);
                        }
                        nextplayer.setVisibility(TextView.INVISIBLE);

                    }}

                else{

                        if (
                                (browncrl.getDrawable().getConstantState().equals(circle6.getDrawable().getConstantState()) && browncrl.getDrawable().getConstantState().equals(circle3.getDrawable().getConstantState())) ||
                                        (browncrl.getDrawable().getConstantState().equals(circle5.getDrawable().getConstantState()) && browncrl.getDrawable().getConstantState().equals(circle1.getDrawable().getConstantState())) ||
                                        (browncrl.getDrawable().getConstantState().equals(circle8.getDrawable().getConstantState()) && browncrl.getDrawable().getConstantState().equals(circle7.getDrawable().getConstantState()))
                                ) {
                            result.setText(playername2+" Won The Match");won=true;
                            result.setVisibility(TextView.VISIBLE);
                            nextplayer.setVisibility(TextView.INVISIBLE);
                            if("SOUND ON".equals(setting.getText().toString())) {
                                sp.play(soundIds[1], 1, 1, 1, 0, 1);
                            }
                        }

                    }

                break;


            case "crl5":
                if(colour.equals("yellow")){

                    if (
                            (yellowcrl.getDrawable().getConstantState().equals(circle1.getDrawable().getConstantState()) && yellowcrl.getDrawable().getConstantState().equals(circle9.getDrawable().getConstantState())) ||
                                    (yellowcrl.getDrawable().getConstantState().equals(circle2.getDrawable().getConstantState()) && yellowcrl.getDrawable().getConstantState().equals(circle8.getDrawable().getConstantState())) ||
                                    (yellowcrl.getDrawable().getConstantState().equals(circle3.getDrawable().getConstantState()) && yellowcrl.getDrawable().getConstantState().equals(circle7.getDrawable().getConstantState())) ||
                                    (yellowcrl.getDrawable().getConstantState().equals(circle6.getDrawable().getConstantState()) && yellowcrl.getDrawable().getConstantState().equals(circle4.getDrawable().getConstantState()))

                    ) {
                        result.setText(playername1+" Won The Match");won=true;
                        result.setVisibility(TextView.VISIBLE);
                        nextplayer.setVisibility(TextView.INVISIBLE);
                        if("SOUND ON".equals(setting.getText().toString())) {
                            sp.play(soundIds[1], 1, 1, 1, 0, 1);
                        }

                    }}

                else{

                    if (
                            (browncrl.getDrawable().getConstantState().equals(circle1.getDrawable().getConstantState()) && browncrl.getDrawable().getConstantState().equals(circle9.getDrawable().getConstantState())) ||
                                    (browncrl.getDrawable().getConstantState().equals(circle2.getDrawable().getConstantState()) && browncrl.getDrawable().getConstantState().equals(circle8.getDrawable().getConstantState())) ||
                                    (browncrl.getDrawable().getConstantState().equals(circle3.getDrawable().getConstantState()) && browncrl.getDrawable().getConstantState().equals(circle7.getDrawable().getConstantState()))  ||
                    (browncrl.getDrawable().getConstantState().equals(circle4.getDrawable().getConstantState()) && browncrl.getDrawable().getConstantState().equals(circle6.getDrawable().getConstantState()))

                    ) {
                        result.setText(playername2+" Won The Match");won=true;
                        result.setVisibility(TextView.VISIBLE);
                        nextplayer.setVisibility(TextView.INVISIBLE);
                        if("SOUND ON".equals(setting.getText().toString())) {
                            sp.play(soundIds[1], 1, 1, 1, 0, 1);
                        }

                    }

                }

                break;


        }

    }

}
