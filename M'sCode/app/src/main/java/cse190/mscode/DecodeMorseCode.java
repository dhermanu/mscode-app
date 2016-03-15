package cse190.mscode;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.media.AudioManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.method.ScrollingMovementMethod;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

public class DecodeMorseCode extends AppCompatActivity {

    private KeyboardView mKeyboardView;

    // Controls Media Player volume.
    private AudioManager mAudioManager;

    private KeyboardView.OnKeyboardActionListener mOnKeyboardActionListener = new KeyboardView.OnKeyboardActionListener() {
        public final static int CodeDelete   = -5;      // Keyboard.KEYCODE_DELETE
        public final static int CodeCancel   = -3;      // Keyboard.KEYCODE_CANCEL
        public final static int Enter        = 66;      // Keyboard.KEYCODE_ENTER
        public static final int UNKNOWN      = 0;       // Keyboard.KEYCODE_UNKNOWN
        public static final int CodeClear    = 28;      // KeyEvent.KEYCODE_CLEAR
        public final static int CodePrev     = 55000;
        public final static int CodeAllLeft  = 55001;
        public final static int CodeLeft     = 55002;
        public final static int CodeRight    = 55003;
        public final static int CodeAllRight = 55004;
        public final static int CodeNext     = 55005;
        //public final static int CodeClear    = 55006;
        @Override public void onKey(int primaryCode, int[] keyCodes) {
            View focusCurrent = DecodeMorseCode.this.getWindow().getCurrentFocus();
            if( focusCurrent == null || focusCurrent.getClass() != EditText.class ) return;
            EditText edittext = (EditText) focusCurrent;
            Editable editable = edittext.getText();
            int start = edittext.getSelectionStart();
            System.out.println("hello key " + (char)primaryCode);

            // Handle key
            if( primaryCode==CodeDelete ) {
                if( editable != null && start > 0 ) editable.delete(start - 1, start);
            }
            else if (primaryCode == CodeClear) {
                if( editable != null && start > 0 ) {
                    editable.clear();
                    //editable.delete(start - edittext.getText().length(), start);  // 2nd way to clear
                }
            }
            else if (primaryCode == Enter){
                if( editable != null && start > 0 ) translate(edittext);
                // hide keyboard on editable having no text in the input field
                else hideCustomKeyboard();
            }
            else if (primaryCode == UNKNOWN) {
                /**
                 * THIS IS TO AVOID ADDING UNKNOWN CHARACTERS INTO THE INPUT FIELD.
                 *
                 * Uncomment the System.out.println below too see.
                 */
                //System.out.println("!!!!!! Unknown Key is pressed !!!!!!");
                //System.out.println("!!!!!! primaryCode = " + primaryCode + " !!!!!!");
                editable.insert(start, Character.toString(' '));    // action for UNKNOWN key press
            }
            else {// Insert character
                editable.insert(start, Character.toString((char) primaryCode));
            }
        }

        @Override public void onPress(int arg0) {
            View focusCurrent = DecodeMorseCode.this.getWindow().getCurrentFocus();
           // if( focusCurrent==null || focusCurrent.getClass()!=EditText.class ) return;
            EditText edittext = (EditText) findViewById(R.id.edit_message_toCharacter);
            Editable editable = edittext.getText();
            int start = edittext.getSelectionStart();

            // Handle key
            if( arg0 == CodeDelete ) {
                if( editable!=null && start>0 ) editable.delete(start - 1, start);
            }
            else if (arg0 == CodeClear) {
                if( editable != null && start > 0 ) {
                    editable.clear();
                    //editable.delete(start - edittext.getText().length(), start);  // 2nd way to clear
                }
            }
            else if (arg0 == Enter) {
                if( editable != null && start > 0 ) translate(edittext);
                // hide keyboard on editable having no text in the input field
                else hideCustomKeyboard();
            }
            else if (arg0 == UNKNOWN) {
                /**
                 * THIS IS TO AVOID ADDING UNKNOWN CHARACTERS INTO THE INPUT FIELD.
                 *
                 * Uncomment the System.out.println below too see.
                 */
                //System.out.println("!!!!!! Unknown Key is pressed !!!!!!");
                //System.out.println("!!!!!! arg0 = " + arg0 + " !!!!!!");
                editable.insert(start, Character.toString(' '));    // action for UNKNOWN key press
            }
            else {// Insert character
                editable.insert(start, Character.toString((char) arg0));
            }
        }

        @Override public void onRelease(int primaryCode) {
        }

        @Override public void onText(CharSequence text) {
        }

        @Override public void swipeDown() {
        }

        @Override public void swipeLeft() {
        }

        @Override public void swipeRight() {
        }

        @Override public void swipeUp() {
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.decode);

        // Set the the Background wallpaper here to prevent keyboard from resizing image.
        /**
         * MUST include this in the decode's manifest activity:
         * android:windowSoftInputMode="adjustPan"
         */
        getWindow().setBackgroundDrawableResource(R.mipmap.hatched_paper);

        // Create the Keyboard
        Keyboard mKeyboard= new Keyboard(this, R.xml.morsekey);

// Lookup the KeyboardView
        mKeyboardView = (KeyboardView)findViewById(R.id.keyboardview);
// Attach the keyboard to the view
        mKeyboardView.setKeyboard(mKeyboard);

// Install the key handler
        mKeyboardView.setOnKeyboardActionListener(mOnKeyboardActionListener);
        // Turn OFF the Key Preview when holding down the key for a long time.
        mKeyboardView.setPreviewEnabled(false);


        // Makes it so that the keyboard is not automatically shown when user visit the page.
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        EditText mEditText = (EditText)findViewById(R.id.edit_message_toCharacter);
        // Make the custom keyboard appear
       /* mEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) showCustomKeyboard(v);
                else hideCustomKeyboard();
            }
        });*/

        mEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomKeyboard(v);
            }
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
        // Nullify the soft keyboard
        if (mKeyboardView != null) {
            mKeyboardView = null;
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
        // Nullify the soft keyboard
        if (mKeyboardView != null) {
            mKeyboardView = null;
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
        // Nullify the soft keyboard
        if (mKeyboardView != null) {
            mKeyboardView = null;
        }
        // Nullify the audio manager
        if (mAudioManager != null) {
            mAudioManager = null;
        }
    }


    /**
     * Function to hide the soft keyboard
     */
    public void hideCustomKeyboard() {
        mKeyboardView.setVisibility(View.GONE);
        mKeyboardView.setEnabled(false);
    }

    /**
     * Function to show the soft keyboard
     */
    public void showCustomKeyboard( View v ) {
        mKeyboardView.setVisibility(View.VISIBLE);
        mKeyboardView.setEnabled(true);
        if( v!=null ) {
            ((InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
    }

    public boolean isCustomKeyboardVisible() {
        return mKeyboardView.getVisibility() == View.VISIBLE;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_decode, menu);
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

    /** Called when the user touches the button: to Morse */
    public void translate(View view) {
        // Do something in response to button
        EditText editText = (EditText) findViewById(R.id.edit_message_toCharacter);
        String message = editText.getText().toString().toLowerCase();
        String toRet = "";

        String[] manipulate = message.split(" ");
        for(String index: manipulate)
        {
            switch(index)
            {
                //letters
                case ".-": toRet = toRet + "a";
                    break;
                case "-...": toRet = toRet + "b";
                    break;
                case "-.-.": toRet = toRet + "c";
                    break;
                case "-..": toRet = toRet + "d";
                    break;
                case ".": toRet = toRet + "e";
                    break;
                case "..-.": toRet = toRet + "f";
                    break;
                case "--.": toRet = toRet + "g";
                    break;
                case "....": toRet = toRet + "h";
                    break;
                case "..": toRet = toRet + "i";
                    break;
                case ".---": toRet = toRet + "j";
                    break;
                case "-.-": toRet = toRet + "k";
                    break;
                case ".-..": toRet = toRet + "l";
                    break;
                case "--": toRet = toRet + "m";
                    break;
                case "-.": toRet = toRet + "n";
                    break;
                case "---": toRet = toRet + "o";
                    break;
                case ".--.": toRet = toRet + "p";
                    break;
                case "--.-": toRet = toRet + "q";
                    break;
                case ".-.": toRet = toRet + "r";
                    break;
                case "...": toRet = toRet + "s";
                    break;
                case "-": toRet = toRet + "t";
                    break;
                case "..-": toRet = toRet + "u";
                    break;
                case "...-": toRet = toRet + "v";
                    break;
                case ".--": toRet = toRet + "w";
                    break;
                case "-..-": toRet = toRet + "x";
                    break;
                case "-.--": toRet = toRet + "y";
                    break;
                case "--..": toRet = toRet + "z";
                    break;

                //numbers
                case "-----": toRet = toRet + "0";
                    break;
                case ".----": toRet = toRet + "1";
                    break;
                case "..---": toRet = toRet + "2";
                    break;
                case "...--": toRet = toRet + "3";
                    break;
                case "....-": toRet = toRet + "4";
                    break;
                case ".....": toRet = toRet + "5";
                    break;
                case "-....": toRet = toRet + "6";
                    break;
                case "--...": toRet = toRet + "7";
                    break;
                case "---..": toRet = toRet + "8";
                    break;
                case "----.": toRet = toRet + "9";
                    break;

                //punctuation
                case ".-.-.-": toRet = toRet + ".";
                    break;
                case "--..--": toRet = toRet + ",";
                    break;
                case "---...": toRet = toRet + ":";
                    break;
                case "-.-.-.": toRet =  toRet + ";";
                    break;
                case "..--..": toRet = toRet + "?";
                    break;
                case "..--.": toRet = toRet + "!";
                    break;
                case ".----.": toRet = toRet + "'";
                    break;
                case "-....-": toRet = toRet + "-";
                    break;
                case "-..-.": toRet = toRet + "/";
                    break;
                case ".-..-.": toRet = toRet + "\"";
                    break;
                case ".--.-.": toRet = toRet + "@";
                    break;
                case "-...-": toRet = toRet + "=";
                    break;
                case "-.--.": toRet = toRet + "(";
                    break;
                case "-.--.-": toRet = toRet + ")";
                    break;
                case ".-.-.": toRet = toRet + "+";
                    break;

                case "": toRet = toRet + " ";
                    break;

                default:
                    toRet = "Error: Check your Morse Code, there is an invalid entry.";
            }
        }

        // Find the .xml TextView to display toRet
        TextView displayMorse = (TextView) findViewById(R.id.output1);
        // Font Size
        displayMorse.setTextSize(25);
        // Set the Text
        displayMorse.setText(toRet);

        // Display the scrollbar for the output
        displayMorse.setMovementMethod(new ScrollingMovementMethod());

        // Hide Keyboard
        hideCustomKeyboard();
    }

    /** Called when the user touches the button: to Character */
    public void encode(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, TranslateMorseCode.class);
        //EditText editText = (EditText) findViewById(R.id.edit_message);
        //String message = editText.getText().toString();
        //intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    @Override public void onBackPressed() {
        if( isCustomKeyboardVisible() ) hideCustomKeyboard(); else this.finish();
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

    /** Hides Keyboard when called */
    /*
    private void hideKeyboard() {
        // Check if no view has focus:
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager inputManager = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
    */
}
