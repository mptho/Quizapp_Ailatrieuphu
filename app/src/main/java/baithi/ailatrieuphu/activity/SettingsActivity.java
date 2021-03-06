package baithi.ailatrieuphu.activity;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ToggleButton;

import baithi.ailatrieuphu.App;
import baithi.ailatrieuphu.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsActivity extends Activity implements View.OnClickListener {
    private ToggleButton togMusic;
    private ToggleButton togSound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        togMusic = ((ToggleButton) findViewById(R.id.tog_music));
        togSound = ((ToggleButton) findViewById(R.id.tog_sound));
        setBgTogMusic(App.getMusicPlayer().getStateMusic());
        setBgTogSound(App.getMusicPlayer().getStateSound());

        togMusic.setOnClickListener(this);
        togSound.setOnClickListener(this);
    }

    public void setBgTogMusic(boolean state) {
        if (state) {
            togMusic.setBackgroundResource(R.drawable.toggle_button_on);
            togMusic.setChecked(true);
        } else {
            togMusic.setBackgroundResource(R.drawable.toggle_button_off);
            togMusic.setChecked(false);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        App.getMusicPlayer().pauseBgMusic();
    }

    @Override
    protected void onResume() {
        super.onResume();
        App.getMusicPlayer().resumeBgMusic();

    }

    public void setBgTogSound(boolean state) {
        if (state) {
            togSound.setBackgroundResource(R.drawable.toggle_button_on);
            togSound.setChecked(true);
        } else {
            togSound.setBackgroundResource(R.drawable.toggle_button_off);
            togSound.setChecked(false);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tog_music:
                if (togMusic.isChecked()) {
                    togMusic.setBackgroundResource(R.drawable.toggle_button_on);
                    togMusic.setChecked(true);
                    App.getMusicPlayer().setStateMusic(true);
                    App.getMusicPlayer().playBgMusic(R.raw.bgmusic);
                } else {
                    togMusic.setBackgroundResource(R.drawable.toggle_button_off);
                    togMusic.setChecked(false);
                    App.getMusicPlayer().stopBgMusic();
                }
                break;
            case R.id.tog_sound:
                setBgTogSound(togSound.isChecked());
                break;
            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        App.getMusicPlayer().setting(togMusic.isChecked(), togSound.isChecked());

    }


}
