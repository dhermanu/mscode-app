package cse190.mscode;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TableRow;
import android.widget.TextView;

public class LearnMorseCode extends AppCompatActivity {

    private MediaPlayer soundPlay;

    // Controls Media Player volume.
    private AudioManager mAudioManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_morse_code);

        // Set the the Background wallpaper here to prevent keyboard from resizing image.
        /**
         * MUST include this in the learn's manifest activity:
         * android:windowSoftInputMode="adjustPan"
         */
        getWindow().setBackgroundDrawableResource(R.mipmap.linen_white);

        // textView set for font
        Typeface myTypeFace = Typeface.createFromAsset(getAssets(), "Bariol_Regular.otf");
        TableRow aLetter = (TableRow) findViewById(R.id.A);
        TableRow bLetter = (TableRow) findViewById(R.id.B);
        TableRow cLetter = (TableRow) findViewById(R.id.C);
        TableRow dLetter = (TableRow) findViewById(R.id.D);
        TableRow eLetter = (TableRow) findViewById(R.id.E);
        TableRow fLetter = (TableRow) findViewById(R.id.F);
        TableRow gLetter = (TableRow) findViewById(R.id.G);
        TableRow hLetter = (TableRow) findViewById(R.id.H);
        TableRow iLetter = (TableRow) findViewById(R.id.I);
        TableRow jLetter = (TableRow) findViewById(R.id.J);
        TableRow kLetter = (TableRow) findViewById(R.id.K);
        TableRow lLetter = (TableRow) findViewById(R.id.L);
        TableRow mLetter = (TableRow) findViewById(R.id.M);
        TableRow nLetter = (TableRow) findViewById(R.id.N);
        TableRow oLetter = (TableRow) findViewById(R.id.O);
        TableRow pLetter = (TableRow) findViewById(R.id.P);
        TableRow qLetter = (TableRow) findViewById(R.id.Q);
        TableRow rLetter = (TableRow) findViewById(R.id.R);
        TableRow sLetter = (TableRow) findViewById(R.id.S);
        TableRow tLetter = (TableRow) findViewById(R.id.T);
        TableRow uLetter = (TableRow) findViewById(R.id.U);
        TableRow vLetter = (TableRow) findViewById(R.id.V);
        TableRow wLetter = (TableRow) findViewById(R.id.W);
        TableRow xLetter = (TableRow) findViewById(R.id.X);
        TableRow yLetter = (TableRow) findViewById(R.id.Y);
        TableRow zLetter = (TableRow) findViewById(R.id.Z);
        //TableRow ad1 = (TableRow) findViewById(R.id.ad);
        TableRow nOne = (TableRow) findViewById(R.id.one);
        TableRow nTwo = (TableRow) findViewById(R.id.two);
        TableRow nThree = (TableRow) findViewById(R.id.three);
        TableRow nFour = (TableRow) findViewById(R.id.four);
        TableRow nFive = (TableRow) findViewById(R.id.five);
        TableRow nSix = (TableRow) findViewById(R.id.six);
        TableRow nSeven = (TableRow) findViewById(R.id.seven);
        TableRow nEight = (TableRow) findViewById(R.id.eight);
        TableRow nNine = (TableRow) findViewById(R.id.nine);
        TableRow nZero = (TableRow) findViewById(R.id.zero);
        TableRow period = (TableRow) findViewById(R.id.dot);
        TableRow comma = (TableRow) findViewById(R.id.comma);
        TableRow colon = (TableRow) findViewById(R.id.colon);
        TableRow semi = (TableRow) findViewById(R.id.semi);
        TableRow question = (TableRow) findViewById(R.id.qmark);
        TableRow exclamation = (TableRow) findViewById(R.id.apos);
        TableRow apost = (TableRow) findViewById(R.id.left);
        TableRow hyphen = (TableRow) findViewById(R.id.dash);
        TableRow slash = (TableRow) findViewById(R.id.right);
        TableRow quote = (TableRow) findViewById(R.id.up);
        TableRow at = (TableRow) findViewById(R.id.at);
        TableRow equal = (TableRow) findViewById(R.id.equals);
        TableRow open = (TableRow) findViewById(R.id.obrac);
        TableRow close = (TableRow) findViewById(R.id.cbrac);
        TableRow plus = (TableRow) findViewById(R.id.plus);


        // set letter for custom
        TextView aLetter1 = (TextView) findViewById(R.id.A1);
        TextView aLetter2 = (TextView) findViewById(R.id.A2);
        aLetter1.setTypeface(myTypeFace);
        aLetter2.setTypeface(myTypeFace);
        aLetter1.setTextColor(Color.parseColor("#212121"));
        aLetter2.setTextColor(Color.parseColor("#212121"));

        // set letter for custom
        TextView bLetter1 = (TextView) findViewById(R.id.B1);
        TextView bLetter2 = (TextView) findViewById(R.id.B2);
        bLetter1.setTypeface(myTypeFace);
        bLetter2.setTypeface(myTypeFace);
        bLetter1.setTextColor(Color.parseColor("#212121"));
        bLetter2.setTextColor(Color.parseColor("#212121"));

        // set letter for custom
        TextView cLetter1 = (TextView) findViewById(R.id.C1);
        TextView cLetter2 = (TextView) findViewById(R.id.C2);
        cLetter1.setTypeface(myTypeFace);
        cLetter2.setTypeface(myTypeFace);
        cLetter1.setTextColor(Color.parseColor("#212121"));
        cLetter2.setTextColor(Color.parseColor("#212121"));

        // set letter for custom
        TextView dLetter1 = (TextView) findViewById(R.id.D1);
        TextView dLetter2 = (TextView) findViewById(R.id.D2);
        dLetter1.setTypeface(myTypeFace);
        dLetter2.setTypeface(myTypeFace);
        dLetter1.setTextColor(Color.parseColor("#252729"));
        dLetter2.setTextColor(Color.parseColor("#252729"));

        // set letter for custom
        TextView eLetter1 = (TextView) findViewById(R.id.E1);
        TextView eLetter2 = (TextView) findViewById(R.id.E2);
        eLetter1.setTypeface(myTypeFace);
        eLetter2.setTypeface(myTypeFace);
        eLetter1.setTextColor(Color.parseColor("#252729"));
        eLetter2.setTextColor(Color.parseColor("#252729"));

        // set letter for custom
        TextView fLetter1 = (TextView) findViewById(R.id.F1);
        TextView fLetter2 = (TextView) findViewById(R.id.F2);
        fLetter1.setTypeface(myTypeFace);
        fLetter2.setTypeface(myTypeFace);
        fLetter1.setTextColor(Color.parseColor("#252729"));
        fLetter2.setTextColor(Color.parseColor("#252729"));

        // set letter for custom
        TextView gLetter1 = (TextView) findViewById(R.id.G1);
        TextView gLetter2 = (TextView) findViewById(R.id.G2);
        gLetter1.setTypeface(myTypeFace);
        gLetter2.setTypeface(myTypeFace);
        gLetter1.setTextColor(Color.parseColor("#252729"));
        gLetter2.setTextColor(Color.parseColor("#252729"));

        // set letter for custom
        TextView hLetter1 = (TextView) findViewById(R.id.H1);
        TextView hLetter2 = (TextView) findViewById(R.id.H2);
        hLetter1.setTypeface(myTypeFace);
        hLetter2.setTypeface(myTypeFace);
        hLetter1.setTextColor(Color.parseColor("#252729"));
        hLetter2.setTextColor(Color.parseColor("#252729"));

        // set letter for custom
        TextView iLetter1 = (TextView) findViewById(R.id.I1);
        TextView iLetter2 = (TextView) findViewById(R.id.I2);
        iLetter1.setTypeface(myTypeFace);
        iLetter2.setTypeface(myTypeFace);
        iLetter1.setTextColor(Color.parseColor("#252729"));
        iLetter2.setTextColor(Color.parseColor("#252729"));

        // set letter for custom
        TextView jLetter1 = (TextView) findViewById(R.id.J1);
        TextView jLetter2 = (TextView) findViewById(R.id.J2);
        jLetter1.setTypeface(myTypeFace);
        jLetter2.setTypeface(myTypeFace);
        jLetter1.setTextColor(Color.parseColor("#252729"));
        jLetter2.setTextColor(Color.parseColor("#252729"));

        // set letter for custom
        TextView kLetter1 = (TextView) findViewById(R.id.K1);
        TextView kLetter2 = (TextView) findViewById(R.id.K2);
        kLetter1.setTypeface(myTypeFace);
        kLetter2.setTypeface(myTypeFace);
        kLetter1.setTextColor(Color.parseColor("#252729"));
        kLetter2.setTextColor(Color.parseColor("#252729"));

        // set letter for custom
        TextView lLetter1 = (TextView) findViewById(R.id.L1);
        TextView lLetter2 = (TextView) findViewById(R.id.L2);
        lLetter1.setTypeface(myTypeFace);
        lLetter2.setTypeface(myTypeFace);
        lLetter1.setTextColor(Color.parseColor("#252729"));
        lLetter2.setTextColor(Color.parseColor("#252729"));

        // set letter for custom
        TextView mLetter1 = (TextView) findViewById(R.id.M1);
        TextView mLetter2 = (TextView) findViewById(R.id.M2);
        mLetter1.setTypeface(myTypeFace);
        mLetter2.setTypeface(myTypeFace);
        mLetter1.setTextColor(Color.parseColor("#252729"));
        mLetter2.setTextColor(Color.parseColor("#252729"));

        // set letter for custom
        TextView nLetter1 = (TextView) findViewById(R.id.N1);
        TextView nLetter2 = (TextView) findViewById(R.id.N2);
        nLetter1.setTypeface(myTypeFace);
        nLetter2.setTypeface(myTypeFace);
        nLetter1.setTextColor(Color.parseColor("#252729"));
        nLetter2.setTextColor(Color.parseColor("#252729"));

        // set letter for custom
        TextView oLetter1 = (TextView) findViewById(R.id.O1);
        TextView oLetter2 = (TextView) findViewById(R.id.O2);
        oLetter1.setTypeface(myTypeFace);
        oLetter2.setTypeface(myTypeFace);
        oLetter1.setTextColor(Color.parseColor("#252729"));
        oLetter2.setTextColor(Color.parseColor("#252729"));

        // set letter for custom
        TextView pLetter1 = (TextView) findViewById(R.id.P1);
        TextView pLetter2 = (TextView) findViewById(R.id.P2);
        pLetter1.setTypeface(myTypeFace);
        pLetter2.setTypeface(myTypeFace);
        pLetter1.setTextColor(Color.parseColor("#252729"));
        pLetter2.setTextColor(Color.parseColor("#252729"));

        // set letter for custom
        TextView qLetter1 = (TextView) findViewById(R.id.Q1);
        TextView qLetter2 = (TextView) findViewById(R.id.Q2);
        qLetter1.setTypeface(myTypeFace);
        qLetter2.setTypeface(myTypeFace);
        qLetter1.setTextColor(Color.parseColor("#252729"));
        qLetter2.setTextColor(Color.parseColor("#252729"));

        // set letter for custom
        TextView rLetter1 = (TextView) findViewById(R.id.R1);
        TextView rLetter2 = (TextView) findViewById(R.id.R2);
        rLetter1.setTypeface(myTypeFace);
        rLetter2.setTypeface(myTypeFace);
        rLetter1.setTextColor(Color.parseColor("#252729"));
        rLetter2.setTextColor(Color.parseColor("#252729"));

        // set letter for custom
        TextView sLetter1 = (TextView) findViewById(R.id.S1);
        TextView sLetter2 = (TextView) findViewById(R.id.S2);
        sLetter1.setTypeface(myTypeFace);
        sLetter2.setTypeface(myTypeFace);
        sLetter1.setTextColor(Color.parseColor("#252729"));
        sLetter2.setTextColor(Color.parseColor("#252729"));

        // set letter for custom
        TextView tLetter1 = (TextView) findViewById(R.id.T1);
        TextView tLetter2 = (TextView) findViewById(R.id.T2);
        tLetter1.setTypeface(myTypeFace);
        tLetter2.setTypeface(myTypeFace);
        tLetter1.setTextColor(Color.parseColor("#252729"));
        tLetter2.setTextColor(Color.parseColor("#252729"));

        // set letter for custom
        TextView uLetter1 = (TextView) findViewById(R.id.U1);
        TextView uLetter2 = (TextView) findViewById(R.id.U2);
        uLetter2.setTypeface(myTypeFace);
        uLetter1.setTypeface(myTypeFace);
        uLetter1.setTextColor(Color.parseColor("#252729"));
        uLetter2.setTextColor(Color.parseColor("#252729"));

        // set letter for custom
        TextView vLetter1 = (TextView) findViewById(R.id.V1);
        TextView vLetter2 = (TextView) findViewById(R.id.V2);
        vLetter2.setTypeface(myTypeFace);
        vLetter1.setTypeface(myTypeFace);
        vLetter1.setTextColor(Color.parseColor("#252729"));
        vLetter2.setTextColor(Color.parseColor("#252729"));

        // set letter for custom
        TextView wLetter1 = (TextView) findViewById(R.id.W1);
        TextView wLetter2 = (TextView) findViewById(R.id.W2);
        wLetter2.setTypeface(myTypeFace);
        wLetter1.setTypeface(myTypeFace);
        wLetter1.setTextColor(Color.parseColor("#252729"));
        wLetter2.setTextColor(Color.parseColor("#252729"));

        // set letter for custom
        TextView xLetter1 = (TextView) findViewById(R.id.X1);
        TextView xLetter2 = (TextView) findViewById(R.id.X2);
        xLetter2.setTypeface(myTypeFace);
        xLetter1.setTypeface(myTypeFace);
        xLetter1.setTextColor(Color.parseColor("#252729"));
        xLetter2.setTextColor(Color.parseColor("#252729"));

        // set letter for custom
        TextView yLetter1 = (TextView) findViewById(R.id.Y1);
        TextView yLetter2 = (TextView) findViewById(R.id.Y2);
        yLetter2.setTypeface(myTypeFace);
        yLetter1.setTypeface(myTypeFace);
        yLetter1.setTextColor(Color.parseColor("#252729"));
        yLetter2.setTextColor(Color.parseColor("#252729"));

        // set letter for custom
        TextView zLetter1 = (TextView) findViewById(R.id.Z1);
        TextView zLetter2 = (TextView) findViewById(R.id.Z2);
        zLetter2.setTypeface(myTypeFace);
        zLetter1.setTypeface(myTypeFace);
        zLetter1.setTextColor(Color.parseColor("#252729"));
        zLetter2.setTextColor(Color.parseColor("#252729"));

        // The ads
        TextView ad2 = (TextView) findViewById(R.id.ad);
        ad2.setTypeface(myTypeFace);
        ad2.setTextColor(Color.parseColor("#ff3aff52"));

        // The ads
        TextView ad1 = (TextView) findViewById(R.id.ad1);
        ad1.setTypeface(myTypeFace);
        ad1.setTextColor(Color.parseColor("#ff3aff52"));

        // set letter for custom
        TextView nOne1 = (TextView) findViewById(R.id.one1);
        TextView nOne2 = (TextView) findViewById(R.id.one2);
        nOne1.setTypeface(myTypeFace);
        nOne2.setTypeface(myTypeFace);
        nOne1.setTextColor(Color.parseColor("#252729"));
        nOne2.setTextColor(Color.parseColor("#252729"));

        // set letter for custom
        TextView nTwo1 = (TextView) findViewById(R.id.two1);
        TextView nTwo2 = (TextView) findViewById(R.id.two2);
        nTwo1.setTypeface(myTypeFace);
        nTwo2.setTypeface(myTypeFace);
        nTwo1.setTextColor(Color.parseColor("#252729"));
        nTwo2.setTextColor(Color.parseColor("#252729"));

        // set letter for custom
        TextView nThree1 = (TextView) findViewById(R.id.three1);
        TextView nThree2 = (TextView) findViewById(R.id.three2);
        nThree1.setTypeface(myTypeFace);
        nThree2.setTypeface(myTypeFace);
        nThree1.setTextColor(Color.parseColor("#252729"));
        nThree2.setTextColor(Color.parseColor("#252729"));

        // set letter for custom
        TextView nFour1 = (TextView) findViewById(R.id.four1);
        TextView nFour2 = (TextView) findViewById(R.id.four2);
        nFour1.setTypeface(myTypeFace);
        nFour2.setTypeface(myTypeFace);
        nFour1.setTextColor(Color.parseColor("#252729"));
        nFour2.setTextColor(Color.parseColor("#252729"));

        // set letter for custom
        TextView nFive1 = (TextView) findViewById(R.id.five1);
        TextView nFive2 = (TextView) findViewById(R.id.five2);
        nFive1.setTypeface(myTypeFace);
        nFive2.setTypeface(myTypeFace);
        nFive1.setTextColor(Color.parseColor("#252729"));
        nFive2.setTextColor(Color.parseColor("#252729"));

        // set letter for custom
        TextView nSix1 = (TextView) findViewById(R.id.six1);
        TextView nSix2 = (TextView) findViewById(R.id.six2);
        nSix1.setTypeface(myTypeFace);
        nSix2.setTypeface(myTypeFace);
        nSix1.setTextColor(Color.parseColor("#252729"));
        nSix2.setTextColor(Color.parseColor("#252729"));

        // set letter for custom
        TextView nSeven1 = (TextView) findViewById(R.id.seven1);
        TextView nSeven2 = (TextView) findViewById(R.id.seven2);
        nSeven1.setTypeface(myTypeFace);
        nSeven2.setTypeface(myTypeFace);
        nSeven1.setTextColor(Color.parseColor("#252729"));
        nSeven2.setTextColor(Color.parseColor("#252729"));

        // set letter for custom
        TextView nEight1 = (TextView) findViewById(R.id.eight1);
        TextView nEight2 = (TextView) findViewById(R.id.eight2);
        nEight1.setTypeface(myTypeFace);
        nEight2.setTypeface(myTypeFace);
        nEight1.setTextColor(Color.parseColor("#252729"));
        nEight2.setTextColor(Color.parseColor("#252729"));

        // set letter for custom
        TextView nNine1 = (TextView) findViewById(R.id.nine1);
        TextView nNine2 = (TextView) findViewById(R.id.nine2);
        nNine1.setTypeface(myTypeFace);
        nNine2.setTypeface(myTypeFace);
        nNine1.setTextColor(Color.parseColor("#252729"));
        nNine2.setTextColor(Color.parseColor("#252729"));

        // set letter for custom
        TextView nZero1 = (TextView) findViewById(R.id.zero1);
        TextView nZero2 = (TextView) findViewById(R.id.zero2);
        nZero1.setTypeface(myTypeFace);
        nZero2.setTypeface(myTypeFace);
        nZero1.setTextColor(Color.parseColor("#252729"));
        nZero2.setTextColor(Color.parseColor("#252729"));

        // set letter for custom
        TextView period1 = (TextView) findViewById(R.id.dot1);
        TextView period2 = (TextView) findViewById(R.id.dot2);
        period1.setTypeface(myTypeFace);
        period2.setTypeface(myTypeFace);
        period1.setTextColor(Color.parseColor("#252729"));
        period2.setTextColor(Color.parseColor("#252729"));

        // set letter for custom
        TextView comma1 = (TextView) findViewById(R.id.comma1);
        TextView comma2 = (TextView) findViewById(R.id.comma2);
        comma1.setTypeface(myTypeFace);
        comma2.setTypeface(myTypeFace);
        comma1.setTextColor(Color.parseColor("#252729"));
        comma2.setTextColor(Color.parseColor("#252729"));

        // set letter for custom
        TextView colon1 = (TextView) findViewById(R.id.colon1);
        TextView colon2 = (TextView) findViewById(R.id.colon2);
        colon1.setTypeface(myTypeFace);
        colon2.setTypeface(myTypeFace);
        colon1.setTextColor(Color.parseColor("#252729"));
        colon2.setTextColor(Color.parseColor("#252729"));

        // set letter for custom
        TextView semi1 = (TextView) findViewById(R.id.semi1);
        TextView semi2 = (TextView) findViewById(R.id.semi2);
        semi1.setTypeface(myTypeFace);
        semi2.setTypeface(myTypeFace);
        semi1.setTextColor(Color.parseColor("#252729"));
        semi2.setTextColor(Color.parseColor("#252729"));

        // set letter for custom
        TextView qmark1 = (TextView) findViewById(R.id.qmark1);
        TextView qmark2 = (TextView) findViewById(R.id.qmark2);
        qmark1.setTypeface(myTypeFace);
        qmark2.setTypeface(myTypeFace);
        qmark1.setTextColor(Color.parseColor("#252729"));
        qmark2.setTextColor(Color.parseColor("#252729"));

        // set letter for custom
        TextView apos1 = (TextView) findViewById(R.id.apos1);
        TextView apos2 = (TextView) findViewById(R.id.apos2);
        apos1.setTypeface(myTypeFace);
        apos2.setTypeface(myTypeFace);
        apos1.setTextColor(Color.parseColor("#252729"));
        apos2.setTextColor(Color.parseColor("#252729"));

        // set letter for custom
        TextView left1 = (TextView) findViewById(R.id.left1);
        TextView left2 = (TextView) findViewById(R.id.left2);
        left1.setTypeface(myTypeFace);
        left2.setTypeface(myTypeFace);
        left1.setTextColor(Color.parseColor("#252729"));
        left2.setTextColor(Color.parseColor("#252729"));

        // set letter for custom
        TextView dash1 = (TextView) findViewById(R.id.dash1);
        TextView dash2 = (TextView) findViewById(R.id.dash2);
        dash1.setTypeface(myTypeFace);
        dash2.setTypeface(myTypeFace);
        dash1.setTextColor(Color.parseColor("#252729"));
        dash2.setTextColor(Color.parseColor("#252729"));

        // set letter for custom
        TextView right1 = (TextView) findViewById(R.id.right1);
        TextView right2 = (TextView) findViewById(R.id.right2);
        right1.setTypeface(myTypeFace);
        right2.setTypeface(myTypeFace);
        right1.setTextColor(Color.parseColor("#252729"));
        right2.setTextColor(Color.parseColor("#252729"));

        // set letter for custom
        TextView up1 = (TextView) findViewById(R.id.up1);
        TextView up2 = (TextView) findViewById(R.id.up2);
        up1.setTypeface(myTypeFace);
        up2.setTypeface(myTypeFace);
        up1.setTextColor(Color.parseColor("#252729"));
        up2.setTextColor(Color.parseColor("#252729"));

        // set letter for custom
        TextView at1 = (TextView) findViewById(R.id.at1);
        TextView at2 = (TextView) findViewById(R.id.at2);
        at1.setTypeface(myTypeFace);
        at2.setTypeface(myTypeFace);
        at1.setTextColor(Color.parseColor("#252729"));
        at2.setTextColor(Color.parseColor("#252729"));

        // set letter for custom
        TextView equals1 = (TextView) findViewById(R.id.equals1);
        TextView equals2 = (TextView) findViewById(R.id.equals2);
        equals1.setTypeface(myTypeFace);
        equals2.setTypeface(myTypeFace);
        equals1.setTextColor(Color.parseColor("#252729"));
        equals2.setTextColor(Color.parseColor("#252729"));

        // set letter for custom
        TextView obrac1 = (TextView) findViewById(R.id.obrac1);
        TextView obrac2 = (TextView) findViewById(R.id.obrac2);
        obrac1.setTypeface(myTypeFace);
        obrac2.setTypeface(myTypeFace);
        obrac1.setTextColor(Color.parseColor("#252729"));
        obrac2.setTextColor(Color.parseColor("#252729"));

        // set letter for custom
        TextView cbrac1 = (TextView) findViewById(R.id.cbrac1);
        TextView cbrac2 = (TextView) findViewById(R.id.cbrac2);
        cbrac1.setTypeface(myTypeFace);
        cbrac2.setTypeface(myTypeFace);
        cbrac1.setTextColor(Color.parseColor("#252729"));
        cbrac2.setTextColor(Color.parseColor("#252729"));

        // set letter for custom
        TextView plus1 = (TextView) findViewById(R.id.plus1);
        TextView plus2 = (TextView) findViewById(R.id.plus2);
        plus1.setTypeface(myTypeFace);
        plus2.setTypeface(myTypeFace);
        plus1.setTextColor(Color.parseColor("#252729"));
        plus2.setTextColor(Color.parseColor("#252729"));


        /* setting up sound play for each translation */
        // play sound for a
        aLetter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(soundPlay!= null)
                {
                    soundPlay.release();
                    soundPlay = null;
                }
                soundPlay = MediaPlayer.create(LearnMorseCode.this, R.raw.a);
                soundPlay.start();
            }
        });

        bLetter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(soundPlay!= null)
                {
                    soundPlay.release();
                    soundPlay = null;
                }
                soundPlay = MediaPlayer.create(LearnMorseCode.this, R.raw.b);
                soundPlay.start();
            }
        });

        cLetter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(soundPlay!= null)
                {
                    soundPlay.release();
                    soundPlay = null;
                }
                soundPlay = MediaPlayer.create(LearnMorseCode.this, R.raw.c);
                soundPlay.start();
            }
        });

        dLetter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(soundPlay!= null)
                {
                    soundPlay.release();
                    soundPlay = null;
                }
                soundPlay = MediaPlayer.create(LearnMorseCode.this, R.raw.d);
                soundPlay.start();
            }
        });

        eLetter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(soundPlay!= null)
                {
                    soundPlay.release();
                    soundPlay = null;
                }
                soundPlay = MediaPlayer.create(LearnMorseCode.this, R.raw.e);
                soundPlay.start();
            }
        });

        fLetter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(soundPlay!= null)
                {
                    soundPlay.release();
                    soundPlay = null;
                }
                soundPlay = MediaPlayer.create(LearnMorseCode.this, R.raw.f);
                soundPlay.start();
            }
        });

        gLetter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(soundPlay!= null)
                {
                    soundPlay.release();
                    soundPlay = null;
                }
                soundPlay = MediaPlayer.create(LearnMorseCode.this, R.raw.g);
                soundPlay.start();
            }
        });

        hLetter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(soundPlay!= null)
                {
                    soundPlay.release();
                    soundPlay = null;
                }
                soundPlay = MediaPlayer.create(LearnMorseCode.this, R.raw.h);
                soundPlay.start();
            }
        });


        iLetter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(soundPlay!= null)
                {
                    soundPlay.release();
                    soundPlay = null;
                }
                soundPlay = MediaPlayer.create(LearnMorseCode.this, R.raw.i);
                soundPlay.start();
            }
        });

        jLetter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(soundPlay!= null)
                {
                    soundPlay.release();
                    soundPlay = null;
                }
                soundPlay = MediaPlayer.create(LearnMorseCode.this, R.raw.j);
                soundPlay.start();
            }
        });

        kLetter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(soundPlay!= null)
                {
                    soundPlay.release();
                    soundPlay = null;
                }
                soundPlay = MediaPlayer.create(LearnMorseCode.this, R.raw.k);
                soundPlay.start();
            }
        });

        lLetter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(soundPlay!= null)
                {
                    soundPlay.release();
                    soundPlay = null;
                }
                soundPlay = MediaPlayer.create(LearnMorseCode.this, R.raw.l);
                soundPlay.start();
            }
        });

        mLetter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(soundPlay!= null)
                {
                    soundPlay.release();
                    soundPlay = null;
                }
                soundPlay = MediaPlayer.create(LearnMorseCode.this, R.raw.m);
                soundPlay.start();
            }
        });

        nLetter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(soundPlay!= null)
                {
                    soundPlay.release();
                    soundPlay = null;
                }
                soundPlay = MediaPlayer.create(LearnMorseCode.this, R.raw.n);
                soundPlay.start();
            }
        });

        oLetter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(soundPlay!= null)
                {
                    soundPlay.release();
                    soundPlay = null;
                }
                soundPlay = MediaPlayer.create(LearnMorseCode.this, R.raw.o);
                soundPlay.start();
            }
        });

        pLetter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(soundPlay!= null)
                {
                    soundPlay.release();
                    soundPlay = null;
                }
                soundPlay = MediaPlayer.create(LearnMorseCode.this, R.raw.p);
                soundPlay.start();
            }
        });

        qLetter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(soundPlay!= null)
                {
                    soundPlay.release();
                    soundPlay = null;
                }
                soundPlay = MediaPlayer.create(LearnMorseCode.this, R.raw.q);
                soundPlay.start();
            }
        });

        rLetter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(soundPlay!= null)
                {
                    soundPlay.release();
                    soundPlay = null;
                }
                soundPlay = MediaPlayer.create(LearnMorseCode.this, R.raw.r);
                soundPlay.start();
            }
        });

        sLetter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(soundPlay!= null)
                {
                    soundPlay.release();
                    soundPlay = null;
                }
                soundPlay = MediaPlayer.create(LearnMorseCode.this, R.raw.s);
                soundPlay.start();
            }
        });

        tLetter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(soundPlay!= null)
                {
                    soundPlay.release();
                    soundPlay = null;
                }
                soundPlay = MediaPlayer.create(LearnMorseCode.this, R.raw.t);
                soundPlay.start();
            }
        });

        uLetter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(soundPlay!= null)
                {
                    soundPlay.release();
                    soundPlay = null;
                }
                soundPlay = MediaPlayer.create(LearnMorseCode.this, R.raw.u);
                soundPlay.start();
            }
        });

        vLetter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(soundPlay!= null)
                {
                    soundPlay.release();
                    soundPlay = null;
                }
                soundPlay = MediaPlayer.create(LearnMorseCode.this, R.raw.v);
                soundPlay.start();
            }
        });

        wLetter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(soundPlay!= null)
                {
                    soundPlay.release();
                    soundPlay = null;
                }
                soundPlay = MediaPlayer.create(LearnMorseCode.this, R.raw.w);
                soundPlay.start();
            }
        });

        xLetter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(soundPlay!= null)
                {
                    soundPlay.release();
                    soundPlay = null;
                }
                soundPlay = MediaPlayer.create(LearnMorseCode.this, R.raw.x);
                soundPlay.start();
            }
        });

        yLetter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(soundPlay!= null)
                {
                    soundPlay.release();
                    soundPlay = null;
                }
                soundPlay = MediaPlayer.create(LearnMorseCode.this, R.raw.y);
                soundPlay.start();
            }
        });

        zLetter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(soundPlay!= null)
                {
                    soundPlay.release();
                    soundPlay = null;
                }
                soundPlay = MediaPlayer.create(LearnMorseCode.this, R.raw.z);
                soundPlay.start();
            }
        });

        nOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(soundPlay!= null)
                {
                    soundPlay.release();
                    soundPlay = null;
                }
                soundPlay = MediaPlayer.create(LearnMorseCode.this, R.raw.one);
                soundPlay.start();
            }
        });

        nTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(soundPlay!= null)
                {
                    soundPlay.release();
                    soundPlay = null;
                }
                soundPlay = MediaPlayer.create(LearnMorseCode.this, R.raw.two);
                soundPlay.start();
            }
        });

        nThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(soundPlay!= null)
                {
                    soundPlay.release();
                    soundPlay = null;
                }
                soundPlay = MediaPlayer.create(LearnMorseCode.this, R.raw.three);
                soundPlay.start();
            }
        });

        nFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(soundPlay!= null)
                {
                    soundPlay.release();
                    soundPlay = null;
                }
                soundPlay = MediaPlayer.create(LearnMorseCode.this, R.raw.four);
                soundPlay.start();
            }
        });

        nFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(soundPlay!= null)
                {
                    soundPlay.release();
                    soundPlay = null;
                }
                soundPlay = MediaPlayer.create(LearnMorseCode.this, R.raw.five);
                soundPlay.start();
            }
        });

        nSix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(soundPlay!= null)
                {
                    soundPlay.release();
                    soundPlay = null;
                }
                soundPlay = MediaPlayer.create(LearnMorseCode.this, R.raw.six);
                soundPlay.start();
            }
        });

        nSeven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(soundPlay!= null)
                {
                    soundPlay.release();
                    soundPlay = null;
                }
                soundPlay = MediaPlayer.create(LearnMorseCode.this, R.raw.seven);
                soundPlay.start();
            }
        });

        nEight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(soundPlay!= null)
                {
                    soundPlay.release();
                    soundPlay = null;
                }
                soundPlay = MediaPlayer.create(LearnMorseCode.this, R.raw.eight);
                soundPlay.start();            }
        });

        nNine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(soundPlay!= null)
                {
                    soundPlay.release();
                    soundPlay = null;
                }
                soundPlay = MediaPlayer.create(LearnMorseCode.this, R.raw.nine);
                soundPlay.start();
            }
        });

        nZero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(soundPlay!= null)
                {
                    soundPlay.release();
                    soundPlay = null;
                }
                soundPlay = MediaPlayer.create(LearnMorseCode.this, R.raw.zero);
                soundPlay.start();            }
        });

        period.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(soundPlay!= null)
                {
                    soundPlay.release();
                    soundPlay = null;
                }
                soundPlay = MediaPlayer.create(LearnMorseCode.this, R.raw.period);
                soundPlay.start();            }
        });

        comma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(soundPlay!= null)
                {
                    soundPlay.release();
                    soundPlay = null;
                }
                soundPlay = MediaPlayer.create(LearnMorseCode.this, R.raw.comma);
                soundPlay.start();            }
        });
        colon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(soundPlay!= null)
                {
                    soundPlay.release();
                    soundPlay = null;
                }
                soundPlay = MediaPlayer.create(LearnMorseCode.this, R.raw.colon);
                soundPlay.start();            }
        });
        semi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(soundPlay!= null)
                {
                    soundPlay.release();
                    soundPlay = null;
                }
                soundPlay = MediaPlayer.create(LearnMorseCode.this, R.raw.semi);
                soundPlay.start();            }
        });
        question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(soundPlay!= null)
                {
                    soundPlay.release();
                    soundPlay = null;
                }
                soundPlay = MediaPlayer.create(LearnMorseCode.this, R.raw.question);
                soundPlay.start();            }
        });
        exclamation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(soundPlay!= null)
                {
                    soundPlay.release();
                    soundPlay = null;
                }
                soundPlay = MediaPlayer.create(LearnMorseCode.this, R.raw.exclam);
                soundPlay.start();            }
        });
        apost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(soundPlay!= null)
                {
                    soundPlay.release();
                    soundPlay = null;
                }
                soundPlay = MediaPlayer.create(LearnMorseCode.this, R.raw.apost);
                soundPlay.start();            }
        });
        quote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(soundPlay!= null)
                {
                    soundPlay.release();
                    soundPlay = null;
                }
                soundPlay = MediaPlayer.create(LearnMorseCode.this, R.raw.quote);
                soundPlay.start();            }
        });
        slash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(soundPlay!= null)
                {
                    soundPlay.release();
                    soundPlay = null;
                }
                soundPlay = MediaPlayer.create(LearnMorseCode.this, R.raw.slash);
                soundPlay.start();            }
        });
        hyphen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(soundPlay!= null)
                {
                    soundPlay.release();
                    soundPlay = null;
                }
                soundPlay = MediaPlayer.create(LearnMorseCode.this, R.raw.hyphen);
                soundPlay.start();            }
        });
        at.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(soundPlay!= null)
                {
                    soundPlay.release();
                    soundPlay = null;
                }
                soundPlay = MediaPlayer.create(LearnMorseCode.this, R.raw.at);
                soundPlay.start();            }
        });
        equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(soundPlay!= null)
                {
                    soundPlay.release();
                    soundPlay = null;
                }
                soundPlay = MediaPlayer.create(LearnMorseCode.this, R.raw.equal);
                soundPlay.start();            }
        });
        open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(soundPlay!= null)
                {
                    soundPlay.release();
                    soundPlay = null;
                }
                soundPlay = MediaPlayer.create(LearnMorseCode.this, R.raw.open);
                soundPlay.start();            }
        });
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(soundPlay!= null)
                {
                    soundPlay.release();
                    soundPlay = null;
                }
                soundPlay = MediaPlayer.create(LearnMorseCode.this, R.raw.close);
                soundPlay.start();            }
        });
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(soundPlay!= null)
                {
                    soundPlay.release();
                    soundPlay = null;
                }
                soundPlay = MediaPlayer.create(LearnMorseCode.this, R.raw.plus);
                soundPlay.start();            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        // The activity is about to become visible.
    }
    @Override
    protected void onResume() {
        super.onResume();
        // The activity has become visible (it is now "resumed").
    }
    @Override
    protected void onPause() {
        super.onPause();
        // Another activity is taking focus (this activity is about to be "paused").
        // Safety, just in case sound is still playing when user exit page
        if(soundPlay!= null)
        {
            soundPlay.release();
            soundPlay = null;
        }
        // Nullify the audio manager
        if (mAudioManager != null) {
            mAudioManager = null;
        }
    }
    @Override
    protected void onStop() {
        super.onStop();
        // The activity is no longer visible (it is now "stopped")
        // Safety, just in case sound is still playing when user exit page
        if(soundPlay!= null)
        {
            soundPlay.release();
            soundPlay = null;
        }
        // Nullify the audio manager
        if (mAudioManager != null) {
            mAudioManager = null;
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        // The activity is about to be destroyed.
        // Safety, just in case sound is still playing when user exit page
        if(soundPlay!= null)
        {
            soundPlay.release();
            soundPlay = null;
        }
        // Nullify the audio manager
        if (mAudioManager != null) {
            mAudioManager = null;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_learn_morse_code, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    /**
     * The KeyDown event is triggered when the user presses a Key.
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        mAudioManager = (AudioManager) this.getSystemService(Context.AUDIO_SERVICE);

        if ((keyCode == KeyEvent.KEYCODE_VOLUME_UP)){       // KEYCODE_VOLUME_UP = 24
            //Do something
            //mAudioManager = (AudioManager) this.getSystemService(Context.AUDIO_SERVICE);
            mAudioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC,
                    AudioManager.ADJUST_RAISE, AudioManager.FLAG_SHOW_UI);
        }
        else if ((keyCode == KeyEvent.KEYCODE_VOLUME_DOWN)){     // KEYCODE_VOLUME_DOWN = 25
            //Do something
            //mAudioManager = (AudioManager) this.getSystemService(Context.AUDIO_SERVICE);
            mAudioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC,
                    AudioManager.ADJUST_LOWER, AudioManager.FLAG_SHOW_UI);
        }
        return true;
    }
}
