package cse190.mscode;

import android.content.Context;
import android.content.Intent;
import android.hardware.Camera;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.View.OnKeyListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class TranslateMorseCode extends AppCompatActivity {

    // Finds the input editText box
    private EditText mEditText;

    //set up MediaPlayer
    private MediaPlayer mMediaPlayer = new MediaPlayer();   // A media play to play Sound
    // Controls Media Player volume.
    private AudioManager mAudioManager;

    private Thread t;                                       // A thread

    private String toRet = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translate_morse_code);

        // Set the the Background wallpaper here to prevent keyboard from resizing image.
        /**
         * MUST include this in the translate's manifest activity:
         * android:windowSoftInputMode="adjustPan"
         */
        getWindow().setBackgroundDrawableResource(R.mipmap.hatched_paper);

        // Makes it so that the keyboard is not automatically shown when user visit the page.
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
        );

        // mEditText is the EditText box that we want to set setOnEditorActionListener to listen for
        // a specific key input
        mEditText = (EditText) findViewById(R.id.edit_message_toMorse);
        // This monitors the keys that are being inputted. IF EditorInfo.IME_ACTION_SEARCH,
        // EditorInfo.IME_ACTION_DONE, KeyEvent.ACTION_DOWN, or KeyEvent.KEYCODE_ENTER is detected,
        // It will hide the keyboard and call encode on the View.
        // http://stackoverflow.com/questions/8063439/android-edittext-finished-typing-event
        // https://androidpadanam.wordpress.com/2013/05/28/android-edittext-imeoptions-done-track-finish-typing/
/* Keep as backup
        mEditText.setOnEditorActionListener(
                new EditText.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                        if (actionId == EditorInfo.IME_ACTION_SEARCH ||
                                actionId == EditorInfo.IME_ACTION_DONE ||
                                event.getAction() == KeyEvent.ACTION_DOWN &&
                                        event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                            if (!event.isShiftPressed()) {
                                // the user is done typing.
                                hideKeyboard();                 // hides Keyboard
                                encode(mEditText);              // calls encode()
                                //System.out.println("!!!!!! event.getKeyCode() " + event.getKeyCode() + " !!!!!!");
                                //System.out.println("!!!!!! KeyEvent.KEYCODE_ENTER = " + KeyEvent.KEYCODE_ENTER + " !!!!!!");
                                return true; // consume.
                            }
                        }
                        return false; // pass on to other listeners.
                    }
                });
*/
        // http://stackoverflow.com/questions/4451374/use-enter-key-on-softkeyboard-instead-of-clicking-button
        mEditText.setOnKeyListener(
                new OnKeyListener() {
                    public boolean onKey(View v, int keyCode, KeyEvent event) {
                        if (event.getAction() == KeyEvent.ACTION_DOWN) {

                            String message = mEditText.getText().toString();

                            switch (keyCode) {
                                case KeyEvent.KEYCODE_UNKNOWN:      // Catches all unknowns
                                case KeyEvent.KEYCODE_SEARCH:       // Not sure if it does anything
                                case KeyEvent.KEYCODE_DPAD_CENTER:
                                case KeyEvent.KEYCODE_ENTER:
                                    // If the EditText input is empty, do nothing and hide keyboard.
                                    if (message.isEmpty()) {
                                        hideKeyboard();
                                        return true;                    // consume.
                                    }
                                    else {
                                        hideKeyboard();                 // hides Keyboard
                                        encode(mEditText);              // calls encode()
                                    }
                                    //System.out.println("!!!!!! event.getKeyCode() " + event.getKeyCode() + " !!!!!!");
                                    //System.out.println("!!!!!! KeyEvent.KEYCODE_ENTER = " + KeyEvent.KEYCODE_ENTER + " !!!!!!");
                                    return true;                        // consume.
                                default:
                                    //System.out.println("!!!!!! UNDEFINED KEY: event.getKeyCode()= " + event.getKeyCode() + " !!!!!!");
                                    break;
                            }
                        }

                        return false;       // pass on to other listeners.
                    }
                }
        );

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
        // Close and release the Camera/Flashlight

        // close any existing thread
        if (t != null) {
            t.interrupt();

            // attempts to join thread.
            try {
                t.join();
            } catch (Exception e) {
                e.printStackTrace();
            }

            // Null the thread
            t = null;
            if (mCamera != null) {
                count = 0;
                mCamera.stopPreview();
                mCamera.release();
                mCamera = null;
            }

            // Safety Release
            if (mMediaPlayer != null) {
                mMediaPlayer.release();
                mMediaPlayer = null;
            }

            // Turn OFF flashlight if its on during the interrupt.
            processOffClick();
        }

        if (mCamera != null) {
            count = 0;
            mCamera.stopPreview();
            mCamera.release();
            mCamera = null;
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
        // Close and release the Camera/Flashlight


        // close any existing thread
        if (t != null) {
            t.interrupt();

            // attempts to join thread.
            try {
                t.join();
            } catch (Exception e) {
                e.printStackTrace();
            }

            // Null the thread
            t = null;
            if (mCamera != null) {
                count = 0;
                mCamera.stopPreview();
                mCamera.release();
                mCamera = null;
            }
            // Safety Release
            if (mMediaPlayer != null) {
                mMediaPlayer.release();
                mMediaPlayer = null;
            }

            // Turn OFF flashlight if its on during the interrupt.
            processOffClick();
        }
        if (mCamera != null) {
            count = 0;
            mCamera.stopPreview();
            mCamera.release();
            mCamera = null;
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
//        System.out.println("!!!!!!!!!!!!!!!!!!!ON Destroy WAS CALLED!!!!!!!!!!!!!!!!!!!")



        // close any existing thread
        if (t != null) {
            t.interrupt();

            // attempts to join thread.
            try {
                t.join();
            } catch (Exception e) {
                e.printStackTrace();
            }

            // Null the thread
            t = null;

            // Close and release the Camera/Flashlight
            if (mCamera != null) {
                count = 0;
                mCamera.stopPreview();
                mCamera.release();
                mCamera = null;
            }

            // Safety Release
            if (mMediaPlayer != null) {
                mMediaPlayer.release();
                mMediaPlayer = null;
            }

            // Turn OFF flashlight if its on during the interrupt.
            processOffClick();
        }

        if (mCamera != null) {
            count = 0;
            mCamera.stopPreview();
            mCamera.release();
            mCamera = null;
        }

        // Nullify the audio manager
        if (mAudioManager != null) {
            mAudioManager = null;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_translate_morse_code, menu);
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
    public void encode(View view) {
        // Do something in response to button
        EditText editText = (EditText) findViewById(R.id.edit_message_toMorse);
        String message = editText.getText().toString().toLowerCase();
        toRet = "";

        for(int i = 0; i < message.length(); i++)
        {
            switch(message.charAt(i))
            {
                //letters
                case 'a': toRet = toRet + ".- ";
                    break;
                case 'b': toRet = toRet + "-... ";
                    break;
                case 'c': toRet = toRet + "-.-. ";
                    break;
                case 'd': toRet = toRet + "-.. ";
                    break;
                case 'e': toRet = toRet + ". ";
                    break;
                case 'f': toRet = toRet + "..-. ";
                    break;
                case 'g': toRet = toRet + "--. ";
                    break;
                case 'h': toRet = toRet + ".... ";
                    break;
                case 'i': toRet = toRet + ".. ";
                    break;
                case 'j': toRet = toRet + ".--- ";
                    break;
                case 'k': toRet = toRet + "-.- ";
                    break;
                case 'l': toRet = toRet + ".-.. ";
                    break;
                case 'm': toRet = toRet + "-- ";
                    break;
                case 'n': toRet = toRet + "-. ";
                    break;
                case 'o': toRet = toRet + "--- ";
                    break;
                case 'p': toRet = toRet + ".--. ";
                    break;
                case 'q': toRet = toRet + "--.- ";
                    break;
                case 'r': toRet = toRet + ".-. ";
                    break;
                case 's': toRet = toRet + "... ";
                    break;
                case 't': toRet = toRet + "- ";
                    break;
                case 'u': toRet = toRet + "..- ";
                    break;
                case 'v': toRet = toRet + "...- ";
                    break;
                case 'w': toRet = toRet + ".-- ";
                    break;
                case 'x': toRet = toRet + "-..- ";
                    break;
                case 'y': toRet = toRet + "-.-- ";
                    break;
                case 'z': toRet = toRet + "--.. ";
                    break;

                //numbers
                case '0': toRet = toRet + "----- ";
                    break;
                case '1': toRet = toRet + ".---- ";
                    break;
                case '2': toRet = toRet + "..--- ";
                    break;
                case '3': toRet = toRet + "...-- ";
                    break;
                case '4': toRet = toRet + "....- ";
                    break;
                case '5': toRet = toRet + "..... ";
                    break;
                case '6': toRet = toRet + "-.... ";
                    break;
                case '7': toRet = toRet + "--... ";
                    break;
                case '8': toRet = toRet + "---.. ";
                    break;
                case '9': toRet = toRet + "----. ";
                    break;

                //punctuation
                case '.': toRet = toRet + ".-.-.- ";
                    break;
                case ',': toRet = toRet + "--..-- ";
                    break;
                case ':': toRet = toRet + "---... ";
                    break;
                case ';': toRet =  toRet + "-.-.-. ";
                    break;
                case '?': toRet = toRet + "..--.. ";
                    break;
                case '!': toRet = toRet + "..--. ";
                    break;
                case '\'': toRet = toRet + ".----. ";
                    break;
                case '-': toRet = toRet + "-....- ";
                    break;
                case '/': toRet = toRet + "-..-. ";
                    break;
                case '"': toRet = toRet + ".-..-. ";
                    break;
                case '@': toRet = toRet + ".--.-. ";
                    break;
                case '=': toRet = toRet + "-...- ";
                    break;
                case '(': toRet = toRet + "-.--. ";
                    break;
                case ')': toRet = toRet + "-.--.- ";
                    break;
                case '+': toRet = toRet + ".-.-. ";
                    break;

                // handles spaces. Outputs 2 spaces.
                case ' ': toRet = toRet + "  ";
                    break;

                // handles newline character
                case '\n': toRet = toRet + "\n";
                    break;

                // '?' resembles a character that is not supported by Morse Code.
                default: toRet = toRet + "? ";
                    break;

            }
        }
        // Find the .xml TextView to display toRet
        TextView displayMorse = (TextView) findViewById(R.id.output);
        // Font Size
        displayMorse.setTextSize(25);
        // Set the Text
        displayMorse.setText(toRet);

        // Display the scrollbar for the output
        displayMorse.setMovementMethod(new ScrollingMovementMethod());

        // Hide Keyboard
        hideKeyboard();
    }


    /** Called when the user touches the button: to Character */
    public void decode(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, DecodeMorseCode.class);
        //EditText editText = (EditText) findViewById(R.id.edit_message);
        //String message = editText.getText().toString();
        //intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }


    private int delay;
    private int dot_length = 150;               // milliseconds
    private int element_length = 150;           // milliseconds
    private int dash_length = dot_length * 3;   // milliseconds
    private int word_length = dot_length * 7;   // milliseconds

    /** Called when the user touches the button: Light */
    public void light(View view) {
        // Do something in response to button

        // Called to translates the Characters to Morse Code; Then Flash the Morse Code.
        encode(view);

        // if the user hits the light button multiple times.
        // This will close the previous thread and Turn OFF the Flashlight.
        // Pressing the Light button will start a new one.
        if (t != null && t.isAlive()) {
            t.interrupt();

            // attempts to join thread.
            try {
                t.join();
            } catch (Exception e) {
                e.printStackTrace();
            }

            // Null the thread
            t = null;

            // Safety Release
            if (mMediaPlayer != null) {
                mMediaPlayer.release();
                mMediaPlayer = null;
            }

            // Turn OFF flashlight if its on during the interrupt.
            processOffClick();
            return;
        }

        /* Dash length                      -->    Dot length x 3
           Pause between elements           -->    Dot length
           Pause between characters         -->    Dot length x 3
           Pause between words (see note)   -->    Dot length x 7
         */
        t = new Thread() {
            public void run() {
                try {
                    for(int i = 0; i < toRet.length(); i++) {
                        switch(toRet.charAt(i)) {
                            //letters
                            case '.':
                                delay = dot_length;
                                processOnClick();
                                break;
                            case '-':
                                delay = dash_length;
                                processOnClick();
                                break;
                            case ' ':
                                // check to see if the next character is a space;
                                // this means it's a word
                                if (toRet.charAt(i) + 1 == ' ') {
                                    delay = word_length;
                                }
                                // Otherwise it's a Pause between elements.
                                else {
                                    delay = element_length;
                                }
                                break;
                            // just a random default length
                            default:
                                delay = word_length;
                                break;
                        }
                        sleep(delay);
                        processOffClick();
                    }
                } catch (Exception e){
                    e.printStackTrace();
                    System.out.println("Flashlight Failed!");
                }
            }
        }; t.start();

    }


    // Camera Variables
    private Camera mCamera;
    private int count;

    /**
     * Turns ON Flashlight when called
     */
    private void processOnClick() {

            if (mCamera == null) {
                try {
                    mCamera = Camera.open();
                } catch (Exception e) {
                    e.printStackTrace();
                }


            if (mCamera != null) {

                final Camera.Parameters params = mCamera.getParameters();

                List<String> flashModes = params.getSupportedFlashModes();

                if (flashModes == null) {
                    return;
                } else {
                    if (count == 0) {
                        params.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
                        mCamera.setParameters(params);
                        mCamera.startPreview();
                    }

                    String flashMode = params.getFlashMode();

                    if (!Camera.Parameters.FLASH_MODE_TORCH.equals(flashMode)) {

                        if (flashModes.contains(Camera.Parameters.FLASH_MODE_TORCH)) {
                            params.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                            mCamera.setParameters(params);
                        } else {
                            // Toast.makeText(this,
                            // "Flash mode (torch) not supported",Toast.LENGTH_LONG).show();

                            params.setFlashMode(Camera.Parameters.FLASH_MODE_ON);

                            mCamera.setParameters(params);
                            try {
                                mCamera.autoFocus(new Camera.AutoFocusCallback() {
                                    public void onAutoFocus(boolean success, Camera camera) {
                                        count = 1;
                                    }
                                });
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }

        if (mCamera == null) {
            return;
        }
    }


    /**
     * Turns OFF Flashlight when called
     */
    private void processOffClick() {

            if (mCamera != null) {
                count = 0;
                mCamera.stopPreview();
                mCamera.release();
                mCamera = null;
            }
    }

    /** Called when the user touches the button: Sound */
    public void sound(View view) {
        // Called to translates the Characters to Morse Code; Then Flash the Morse Code.
        encode(view);

        // if the user hits the Sound button multiple times.
        // This will close the previous thread and Turn OFF the Audio Playing.
        // Pressing the Sound button will start a new one.
        if (t != null && t.isAlive()) {
            t.interrupt();

            // attempts to join thread.
            try {
                t.join();
            } catch (Exception e) {
                e.printStackTrace();
            }

            // Null the thread
            t = null;

            // Safety Release
            if (mMediaPlayer != null) {
                mMediaPlayer.release();
                mMediaPlayer = null;
            }

            // Turn OFF flashlight if its on during the interrupt.
            processOffClick();
            return;
        }

        /* Dash length                      -->    Dot length x 3
           Pause between elements           -->    Dot length
           Pause between characters         -->    Dot length x 3
           Pause between words (see note)   -->    Dot length x 7
         */
        t = new Thread() {
            public void run() {
                try {
                    for(int i = 0; i < toRet.length(); i++) {
                        switch(toRet.charAt(i)) {
                            //letters
                            case '.':
                                try {
                                    mMediaPlayer = MediaPlayer.create(TranslateMorseCode.this, R.raw.dot);
                                    delay = mMediaPlayer.getDuration();
                                    mMediaPlayer.start();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    System.out.println("Dot Audio Playback Failed!");
                                }
                                break;
                            case '-':
                                try {
                                    mMediaPlayer = MediaPlayer.create(TranslateMorseCode.this, R.raw.dash);
                                    delay = mMediaPlayer.getDuration();
                                    mMediaPlayer.start();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    System.out.println("Dash Audio Playback Failed!");
                                }
                                break;
                            case ' ':
                                // check to see if the next character is a space;
                                // this means it's a word
                                if (toRet.charAt(i) + 1 == ' ') {
                                    mMediaPlayer = MediaPlayer.create(TranslateMorseCode.this, R.raw.dash);
                                    delay = mMediaPlayer.getDuration() * 7;
                                }
                                // Otherwise it's a Pause between elements.
                                else {
                                    mMediaPlayer = MediaPlayer.create(TranslateMorseCode.this, R.raw.dot);
                                    delay = mMediaPlayer.getDuration();
                                }
                                break;
                                // just a random default length
                            default:
                                mMediaPlayer = MediaPlayer.create(TranslateMorseCode.this, R.raw.dash);
                                delay = mMediaPlayer.getDuration() * 7;
                                break;
                        }
                        sleep(delay+325);   // Remove the +325 if you want REAL Morse Code Play Speed.
                        mMediaPlayer.stop();
                        mMediaPlayer.release();
                    }
                } catch (Exception e){
                    e.printStackTrace();
                    System.out.println("Dot & Dash Audio Playback Failed!");
                }
            }
        }; t.start();

    }


    /**
     * The KeyDown event is triggered when the user presses a Key.
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        //System.out.println("!!!!!! event.getKeyCode() " + event.getKeyCode() + " !!!!!!");
        //System.out.println("!!!!!! keyCode = " + keyCode + " !!!!!!");

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
    private void hideKeyboard() {
        // Check if no view has focus:
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager inputManager = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}
