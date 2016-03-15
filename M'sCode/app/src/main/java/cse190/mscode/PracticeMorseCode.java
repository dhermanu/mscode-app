package cse190.mscode;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.os.Handler;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class PracticeMorseCode extends AppCompatActivity {

    private int lives = 3;
    private String currChar = "a";
    private String currMorse = "";
    private TextView goTime;
    private TextView curr_char;
    private TextView livesLeft;
    private int roundDuration = 10000;
    private boolean restart = false;
    private int amountTillDecrease = 0;
    private int decreaseThresh = 20;  //constant
    private int timeMin = 3000; // constant
    private int attempts = 2;
    private int letterAttempts = 2; //constant
    private int MorseAttempts = 7; //constant to set how many attempts for morse input
    private boolean onlyMorse = false; //can only have morse input
    private boolean letterToMorse = true; //determiens which text is displayed on the screen
    private boolean freeRun = true; //can be either morse or letter inputs
    private boolean canLetters = false; //allow or disallow letter input, mostly used by freeRun
    private int score = 0;
    final private int threshold = 25; //minimum before letter to morse can be activated
    private MediaPlayer soundEffect;
    // Controls Media Player volume.
    private AudioManager mAudioManager;
    private static final String SEC_FORMAT = "%02d";
    private CountDownTimer roundTimer_var;          // the current round timer
    private CountDownTimer getReady_var;            // get ready to start the game timer
    private Vibrator v;
    private Handler handler = new Handler();        // aka a seperate thread that must be null when DONE.

    /**
     * This is a task to call roundTimer by a handler after the handler waits (delays).
     */
    Runnable myFirstTask  = new Runnable (){
        @Override
        public void run() {
            roundTimer();
        } };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice_morse_code);

        // Set the the Background wallpaper here to prevent keyboard from resizing image.
        /**
         * MUST include this in the menu_practice's manifest activity:
         * android:windowSoftInputMode="adjustPan"
         */
        getWindow().setBackgroundDrawableResource(R.mipmap.hatched_paper2);

        // add custom controls
        addMorseControls();

        //set the flags for the correct game mode
        int mode = getIntent().getIntExtra("mode", 1);
        if(mode == 1)
        {
            onlyMorse = true;
            canLetters = false;
            letterToMorse = true;
            freeRun = false;
        }
        else if(mode == 2)
        {
            onlyMorse = false;
            canLetters = true;
            letterToMorse = false;
            freeRun = false;
        }
        else
        {
            freeRun = true;
            onlyMorse = false;
        }


        // Finds the view that holds the current char of the round
        curr_char = (TextView) findViewById(R.id.curr_char);

        // Finds the view that holds the timer
        goTime = (TextView) findViewById(R.id.timer);

        // Find the view that holds the lives
        livesLeft = (TextView) findViewById(R.id.lives);
        // Displays the lives remaining
        livesLeft.setText(Integer.toString(lives));

        // After User selects "Practice" this gives them 3 seconds before game starts
        getReady_var = new CountDownTimer(3000, 1000) { // adjust the milli seconds here
            public void onTick(long millisUntilFinished) {
                goTime.setText(""+String.format(SEC_FORMAT,
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished)));
            }
            public void onFinish() {
                curr_char.setText("GO!");      // Display GO!

                // Execute some code after 1 seconds have passed
                // Used as a "delay" NEED. DO NOT DELETE
                if(handler == null) {
                    // Blank on purpose
                }
                else {
                    handler.postDelayed(myFirstTask, 1000);
                }

            }
        }.start();
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
        if(soundEffect!=null)
        {
            soundEffect.release();
            soundEffect = null;
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
        if(soundEffect!=null)
        {
            soundEffect.release();
            soundEffect = null;
        }
        // Nullify the audio manager
        if (mAudioManager != null) {
            mAudioManager = null;
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();

        // Turn OFF ALL Timers, they WERE still running after onDestroy() was called.
        // This caused a bug where a vibrate would occur after going to a new Activit/Screen.
        if (getReady_var != null) {
            getReady_var.cancel();
        }
        if (roundTimer_var != null) {
            roundTimer_var.cancel();
        }

        if(soundEffect!=null)
        {
            soundEffect.release();
            soundEffect = null;
        }
        // The activity is about to be destroyed.
//        System.out.println("!!!!!!!!!!!!!!!!!!!ON Destroy WAS CALLED!!!!!!!!!!!!!!!!!!!");
        if(handler!=null) {
            //handler.removeCallbacksAndMessages(null);
            //handler.removeCallbacks(null);
            handler.removeCallbacks(myFirstTask);
            handler = null;
        }
        // Nullify the audio manager
        if (mAudioManager != null) {
            mAudioManager = null;
        }

    }

    public void addMorseControls()
    {
        GradientDrawable gdDefault = new GradientDrawable();
        gdDefault.setColor(Color.parseColor("#616161"));
        gdDefault.setCornerRadius(10);
        gdDefault.setStroke(2, Color.parseColor("#bdbdbd"));

        LinearLayout mid= (LinearLayout) findViewById(R.id.controls);
        mid.removeAllViewsInLayout();
        Button dot = new Button(this);
        dot.setText("\u25CF");              // THIS was our dot (.); NOW: unicode for a black circle
        dot.setTextSize(40);
        dot.setTypeface(null, Typeface.BOLD);
        dot.setTextColor(Color.parseColor("#fafafa"));
        dot.setBackgroundDrawable(gdDefault); //makes the button look rounded
        dot.setId(R.id.dotGame);
        dot.setLayoutParams(new LinearLayout.LayoutParams(
                0, LinearLayout.LayoutParams.MATCH_PARENT, 1
        ));

        //for input
        dot.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                if(soundEffect!= null)
                {
                    soundEffect.release();
                    soundEffect = null;
                }
                soundEffect = MediaPlayer.create(PracticeMorseCode.this, R.raw.dot);
                soundEffect.start();
                updateInputView(".");
            }
        });
        //LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //View v = inflater.inflate(R.layout.morsecontrols, null);
        mid.addView(dot);
        Button dash = new Button(this);
        dash.setText("\u2014");             // THIS was our dash (-); NOW: unicode for a en/em dash
        dash.setTextSize(40);
        dash.setTypeface(null, Typeface.BOLD);
        dash.setTextColor(Color.parseColor("#fafafa"));
        dash.setBackgroundDrawable(gdDefault);
        dash.setId(R.id.dashGame);
        dash.setLayoutParams(new LinearLayout.LayoutParams(
                0, LinearLayout.LayoutParams.MATCH_PARENT, 1
        ));
        //for input
        dash.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                if(soundEffect!= null)
                {
                    soundEffect.release();
                    soundEffect = null;
                }
                soundEffect = MediaPlayer.create(PracticeMorseCode.this, R.raw.dash);
                soundEffect.start();
                updateInputView("-");
            }
        });
        mid.addView(dash);
    }

    public void addLetterControls()
    {
        LinearLayout mid= (LinearLayout) findViewById(R.id.controls);
        mid.removeAllViewsInLayout();
        String first ="";
        String second = "";
        String third = "";
        String fourth = "";

        GradientDrawable gdDefault = new GradientDrawable();
        gdDefault.setColor(Color.parseColor("#616161"));
        gdDefault.setCornerRadius(10);
        gdDefault.setStroke(2, Color.parseColor("#bdbdbd"));

        int placeVar = (int)(Math.random()*4);
        switch(placeVar)
        {
            case 0: first = currChar;
                break;
            case 1: second = currChar;
                break;
            case 2: third = currChar;
                break;
            case 3: fourth = currChar;
                break;
        }

        Random r = new Random();

        // Must keep populating, it is small for now; Testing purposes
        String choices = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890!@,.:;'\"()?/+=-";

        // Picks a random number to index into our array

        //if the random number manages to get the same character twice then one will be blank
        //so freebie!
        for(int i = 0; i < 4; i++)
        {
            int indexInto = r.nextInt(choices.length());
            switch(i)
            {
                case 0: if(first.equals("") && !currChar.equals(choices.charAt(indexInto) + ""))
                {
                    first = choices.charAt(indexInto) + "";
                }
                    break;
                case 1: if(second.equals("") && !currChar.equals(choices.charAt(indexInto) + ""))
                {
                    second = choices.charAt(indexInto) + "";
                }
                    break;
                case 2: if(third.equals("") && !currChar.equals(choices.charAt(indexInto) + ""))
                {
                    third = choices.charAt(indexInto) + "";
                }
                    break;
                case 3: if(fourth.equals("") && !currChar.equals(choices.charAt(indexInto) + ""))
                {
                    fourth = choices.charAt(indexInto) + "";
                }
                    break;
            }
        }

        Button letter1 = new Button(this);
        letter1.setText(first);
        letter1.setTextSize(40);
        letter1.setId(R.id.letter1Game);
        letter1.setTypeface(null, Typeface.BOLD);
        letter1.setTextColor(Color.parseColor("#fafafa"));
        letter1.setBackgroundDrawable(gdDefault);
        letter1.setLayoutParams(new LinearLayout.LayoutParams(
                0, LinearLayout.LayoutParams.MATCH_PARENT, (float) 0.5
        ));

        //for input
        letter1.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Button temp = (Button) findViewById(R.id.letter1Game);
                checkButton((String) temp.getText());
            }
        });
        mid.addView(letter1);

        Button letter2 = new Button(this);
        letter2.setText(second);
        letter2.setTextSize(40);
        letter2.setId(R.id.letter2Game);
        letter2.setTypeface(null, Typeface.BOLD);
        letter2.setTextColor(Color.parseColor("#fafafa"));
        letter2.setBackgroundDrawable(gdDefault);
        letter2.setLayoutParams(new LinearLayout.LayoutParams(
                0, LinearLayout.LayoutParams.MATCH_PARENT, (float) 0.5
        ));
        //for input
        letter2.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Button temp = (Button) findViewById(R.id.letter2Game);
                checkButton((String) temp.getText());
            }
        });
        mid.addView(letter2);

        Button letter3 = new Button(this);
        letter3.setText(third);
        letter3.setTextSize(40);
        letter3.setId(R.id.letter3Game);
        letter3.setTypeface(null, Typeface.BOLD);
        letter3.setTextColor(Color.parseColor("#fafafa"));
        letter3.setBackgroundDrawable(gdDefault);
        letter3.setLayoutParams(new LinearLayout.LayoutParams(
                0, LinearLayout.LayoutParams.MATCH_PARENT, (float) 0.5
        ));
        //for input
        letter3.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Button temp = (Button) findViewById(R.id.letter3Game);
                checkButton((String) temp.getText());
            }
        });
        mid.addView(letter3);

        Button letter4 = new Button(this);
        letter4.setText(fourth);
        letter4.setTextSize(40);
        letter4.setId(R.id.letter4Game);
        letter4.setTypeface(null, Typeface.BOLD);
        letter4.setTextColor(Color.parseColor("#fafafa"));
        letter4.setBackgroundDrawable(gdDefault);
        letter4.setLayoutParams(new LinearLayout.LayoutParams(
                0, LinearLayout.LayoutParams.MATCH_PARENT, (float)0.5
        ));
        //for input
        letter4.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Button temp = (Button)findViewById(R.id.letter4Game);
                checkButton((String)temp.getText());
            }
        });
        mid.addView(letter4);
    }
    public void checkButton(String input)
    {
        if(input.equals(currChar))
        {
            roundTimer_var.cancel();// cancel the current round timer
            score++;
            amountTillDecrease++;
            roundTimer();
        }
        else //attempt logic and lose life
        {
             loseAttempt();
        }
    }

    public void addReset()
    {
        LinearLayout mid= (LinearLayout) findViewById(R.id.controls);
        mid.removeAllViewsInLayout();
        Button reset = new Button(this);
        reset.setText("RESTART");
        reset.setTextSize(40);
        reset.setId(R.id.resetGame);
        reset.setLayoutParams(new LinearLayout.LayoutParams(
                0, LinearLayout.LayoutParams.MATCH_PARENT, 2
        ));

        //for input
        reset.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                if(!restart) {
                    restart = true;
                    resetInput();                       // Clear input
                    replay(); //restart the game
                }
            }
        });
        mid.addView(reset);
    }

    public void loseAttempt()
    {
        attempts--;
        if(attempts <= 0)
        {
            roundTimer_var.cancel();
            roundTimer_var.onFinish();
        }
    }


    public void updateInputView(String input)
    {
        TextView playerInput = (TextView) findViewById(R.id.entered);
        String currInput = (String)playerInput.getText();
        int currLength = currInput.length();
        if(currInput.equals("success") || currMorse.length() == 0 || lives <= 0)//starting condition or waiting for timer to finish
        {

        }
        else if(currLength > 0 && currMorse.length() > currLength)
        {
            if (input.equals("" + currMorse.charAt(currLength)))
            {
                if(!(currMorse.equals(currInput+input)))
                {
                    playerInput.setText(currInput + input);
                }
                else
                {
                    playerInput.setText("success");
                    roundTimer_var.cancel();// cancel the current round timer
                    score++;
                    roundTimer();                   // grab a new char and restart timer: 10 sec
                }
            } else {
                playerInput.setText("");
                loseAttempt();
            }
        }
        else
        {
            if (input.equals("" + currMorse.charAt(0)))
            {
                if((currLength + 1) == currMorse.length())
                {
                    playerInput.setText("success");
                    roundTimer_var.cancel();        // cancel the current round timer
                    amountTillDecrease++;
                    score++;
                    roundTimer();
                }
                else {
                    playerInput.setText(input);
                }
            }
        }
    }

    public void resetInput()
    {
        TextView playerInput = (TextView) findViewById(R.id.entered);
        playerInput.setText("");
    }


    /** Each Round is 10 seconds, with a new character each time. Must REDO so that its a new
     * character everytime they get the correct answer. This is just a skeleton.
     */
    // NOTE!!!!!! The time skips seconds sometimes. Stackoverflow says that its because of rounding.
    // SHOULD try and fix... maybe
    public void roundTimer() {

        v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        if(score > threshold && (!onlyMorse || freeRun))//5 for testing
        {
            canLetters = true;
        }

        // Get the current round Character
        currentChar();

        if(amountTillDecrease == decreaseThresh && roundDuration > timeMin)
        {
            amountTillDecrease = 0;
            roundDuration -= 1000;
        }
        // set our current round timer to 10 seconds
        roundTimer_var = new CountDownTimer(roundDuration, 1000) { // adjust the milli seconds here
            public void onTick(long millisUntilFinished) {
                goTime.setText(""+String.format(SEC_FORMAT,
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished)));
            }
            public void onFinish() {

                // Display times out message
                goTime.setText("Times out!");
                resetInput();

                // Loses a life because time ran out
                lives--;
                // Vibrate for lose
                v.vibrate(500);
                // Updates the lives remaining
                livesLeft.setText(Integer.toString(lives));

                if (lives <= 0) {
                    /* Start without a delay
                       Vibrate for 250 milliseconds
                       Sleep for 50 milliseconds */
                    long[] pattern = {0, 250, 50, 250, 50};
                    v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    v.vibrate(pattern, -1);
                    canLetters = false;

                    // Updates the lives remaining
                    livesLeft.setText(Integer.toString(lives));
                    roundTimer_var.cancel();            // Turn off any timer
                    getReady_var.cancel();              // Turn off any timer
                    addReset();
                    resetInput();                       // Clear input
                    //replay();                           // user replays game
                }
                else {
                    // Execute some code after 1 seconds have passed
                    // Used as a "delay" NEED. DO NOT DELETE
                    if(handler == null) {
                        // Blank on purpose
                    }
                    else {
                        handler.postDelayed(myFirstTask, 1000);
                    }

                }

            }
        }.start();
    }

    /** Picks a Character Randomly to display/encode
     * This may have to return a char instead of being void. The
     * returned char would then be compared to the user input for correctnes.
     * Must handle case when user loses life for incorrect input. */
    public void currentChar() {

        // Find the View that will contain the character to encode
        curr_char = (TextView)findViewById(R.id.curr_char);

        Random r = new Random();

        // Must keep populating, it is small for now; Testing purposes
        String choices = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890!@,.:;'\"()?/+=-";

        // Picks a random number to index into our array
        int indexInto = r.nextInt(choices.length());
        currChar = "" + choices.charAt(indexInto);
        currMorse = getMorse(currChar);
        //attempts = 2;
        //change controls based on what we are expecting
    if(freeRun) {
        int changeItUp = r.nextInt(10);
        if (changeItUp < 3 && canLetters) //30% chance
        {
            letterToMorse = false;
            addLetterControls();
        } else {
            letterToMorse = true;
            addMorseControls();
        }
    }
    else if(onlyMorse)
    {
        letterToMorse = true;
        addMorseControls();
    }
    else
    {
        letterToMorse = false;
        addLetterControls();
    }

        // The character chosen

        resetInput();
        if(!letterToMorse) {
            attempts = letterAttempts;
            curr_char.setText(currMorse);
        }
        else {
            attempts = MorseAttempts;
            curr_char.setText(currChar);
        }
    }

    /**
     * Restarts the game after user has lost all lives.
     */
    public void replay() {
        // After User LOST this gives them 3 seconds before game starts.
        // A reason for this if asked, "its to show ads."
        getReady_var = new CountDownTimer(3000, 1000) { // adjust the milli seconds here
            public void onTick(long millisUntilFinished) {
                goTime.setText("Restarting: "+String.format(SEC_FORMAT,
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished)));
            }
            public void onFinish() {
                // Reset lives
                lives = 3;              // reset lives
                // Displays the lives remaining
                livesLeft.setText(Integer.toString(lives));
                restart =false;
                goTime.setText("GO!");      // Display GO!
                roundTimer_var.cancel();    // cancel any existing round timer. for SAFETY
                roundTimer();               // Code Executed: start the rounds.
            }
        }.start();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_practice_morse_code, menu);
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

    public String getMorse(String original)
    {
        String toRet = "";
        String manipulate = original.toLowerCase();

        switch(manipulate)
        {
            //letters
            case "a": toRet = ".-";
                break;
            case "b": toRet = "-...";
                break;
            case "c": toRet = "-.-.";
                break;
            case "d": toRet =  "-..";
                break;
            case "e": toRet =  ".";
                break;
            case "f": toRet =  "..-.";
                break;
            case "g": toRet =  "--.";
                break;
            case "h": toRet =  "....";
                break;
            case "i": toRet =  "..";
                break;
            case "j": toRet =  ".---";
                break;
            case "k": toRet =  "-.-";
                break;
            case "l": toRet =  ".-..";
                break;
            case "m": toRet =  "--";
                break;
            case "n": toRet =  "-.";
                break;
            case "o": toRet =  "---";
                break;
            case "p": toRet =  ".--.";
                break;
            case "q": toRet =  "--.-";
                break;
            case "r": toRet =  ".-.";
                break;
            case "s": toRet =  "...";
                break;
            case "t": toRet =  "-";
                break;
            case "u": toRet =  "..-";
                break;
            case "v": toRet =  "...-";
                break;
            case "w": toRet =  ".--";
                break;
            case "x": toRet =  "-..-";
                break;
            case "y": toRet =  "-.--";
                break;
            case "z": toRet =  "--..";
                break;
            //numbers
            case "0": toRet =  "-----";
                break;
            case "1": toRet =  ".----";
                break;
            case "2": toRet =  "..---";
                break;
            case "3": toRet =  "...--";
                break;
            case "4": toRet =  "....-";
                break;
            case "5": toRet =  ".....";
                break;
            case "6": toRet =  "-....";
                break;
            case "7": toRet =  "--...";
                break;
            case "8": toRet =  "---..";
                break;
            case "9": toRet =  "----.";
                break;

            //punctuation
            case ".": toRet =  ".-.-.-";
                break;
            case ",": toRet =  "--..--";
                break;
            case ":": toRet =  "---...";
                break;
            case ";": toRet =  "-.-.-.";
                break;
            case "?": toRet =  "..--..";
                break;
            case "!": toRet =  "..--.";
                break;
            case "\"": toRet =  ".----.";
                break;
            case "-": toRet =  "-....-";
                break;
            case "/": toRet =  "-..-.";
                break;
            case "'": toRet =  ".-..-.";
                break;
            case "@": toRet =  ".--.-.";
                break;
            case "=": toRet =  "-...-";
                break;
            case "(": toRet =  "-.--.";
                break;
            case ")": toRet =  "-.--.-";
                break;
            case "+": toRet =  ".-.-.";
                break;

            case " ": toRet =  " ";
                break;
            default:
        }
        return toRet;
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

    /**
     * The KeyUp event is triggered when the user releases a Key.
     */
    /*
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {

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
    */
}