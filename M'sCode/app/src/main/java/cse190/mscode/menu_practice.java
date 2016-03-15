package cse190.mscode;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class menu_practice extends AppCompatActivity {

    // Controls Media Player volume.
    private AudioManager mAudioManager;

    public final static String PASS_DATA = "mode";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_practice);

        // Set the the Background wallpaper here to prevent keyboard from resizing image.
        /**
         * MUST include this in the menu_practice's manifest activity:
         * android:windowSoftInputMode="adjustPan"
         */
        getWindow().setBackgroundDrawableResource(R.mipmap.hatched_paper2);
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
        // The activity is done and destroying
        // Nullify the audio manager
        if (mAudioManager != null) {
            mAudioManager = null;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_menu_practice, menu);
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
     * When the user clicks on the button.
     */
    public void option1(View v) {
        final int option1 = 1;

        // Selects the PracticeMorseCode java code to run
        Intent intent = new Intent(this, PracticeMorseCode.class);

        intent.putExtra(PASS_DATA, option1);

        startActivity(intent);
    }

    /**
     * When the user clicks on the button.
     */
    public void option2(View v) {
        final int option2 = 2;

        // Selects the PracticeMorseCode java code to run
        Intent intent = new Intent(this, PracticeMorseCode.class);

        intent.putExtra(PASS_DATA, option2);

        startActivity(intent);
    }

    /**
     * When the user clicks on the button.
     */
    public void option3(View v) {
        final int option3 = 3;

        // Selects the PracticeMorseCode java code to run
        Intent intent = new Intent(this, PracticeMorseCode.class);

        intent.putExtra(PASS_DATA, option3);

        startActivity(intent);
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
