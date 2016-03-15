package cse190.mscode;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.graphics.Typeface;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    // Controls Media Player volume.
    private AudioManager mAudioManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Typeface myTypeFace = Typeface.createFromAsset(getAssets(), "Quicksand-Regular.otf");
        TextView aLetter = (TextView) findViewById(R.id.button3);
        TextView bLetter = (TextView) findViewById(R.id.button);
        TextView cLetter = (TextView) findViewById(R.id.button2);

        aLetter.setTypeface(myTypeFace);
        bLetter.setTypeface(myTypeFace);
        cLetter.setTypeface(myTypeFace);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    /** Called when the user touches the button: Practice */
    public void practice(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, menu_practice.class);
        //EditText editText = (EditText) findViewById(R.id.edit_message);
        //String message = editText.getText().toString();
        //intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    /** Called when the user touches the button: Learn */
    public void learn(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, LearnMorseCode.class);
        //EditText editText = (EditText) findViewById(R.id.edit_message);
        //String message = editText.getText().toString();
        //intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    /** Called when the user touches the button: Translate */
    public void translate(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, TranslateMorseCode.class);
        //EditText editText = (EditText) findViewById(R.id.edit_message);
        //String message = editText.getText().toString();
        //intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
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
        // Nullify the audio manager
        if (mAudioManager != null) {
            mAudioManager = null;
        }
    }
    @Override
    protected void onStop() {
        super.onStop();
        // The activity is no longer visible (it is now "stopped")
        // Nullify the audio manager
        if (mAudioManager != null) {
            mAudioManager = null;
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        // The activity is about to be destroyed.
        // Nullify the audio manager
        if (mAudioManager != null) {
            mAudioManager = null;
        }
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
