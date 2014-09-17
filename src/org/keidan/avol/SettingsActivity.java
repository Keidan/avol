package org.keidan.avol;

import static org.keidan.avol.Config.CFG;

import java.util.ArrayList;

import org.keidan.avol.settings.VolumeModel;
import org.keidan.avol.settings.VolumesArrayAdapter;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ListView;

public class SettingsActivity extends ActivityPopup implements
    OnCheckedChangeListener {
  private ListView            volumes        = null;
  private VolumesArrayAdapter adapter        = null;
  private CheckBox            promptFeedback = null;
  
  @Override
  public void onCreate(final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState, false, true, false, R.layout.settings);

    try {
      CFG.create(this);
    } catch (final Exception e) {
      e.printStackTrace();
    }
    volumes = (ListView) findViewById(R.id.listSounds);
    final ArrayList<VolumeModel> list = new ArrayList<VolumeModel>();
    list.add(new VolumeModel(this, CFG, R.string.stream_alarm,
        R.drawable.alarms, Config.KEY_STREAM_ALARM));
    list.add(new VolumeModel(this, CFG, R.string.stream_dtmf, R.drawable.dtmf,
        Config.KEY_STREAM_DTMF));
    list.add(new VolumeModel(this, CFG, R.string.stream_music,
        R.drawable.music, Config.KEY_STREAM_MUSIC));
    list.add(new VolumeModel(this, CFG, R.string.stream_notification,
        R.drawable.notification, Config.KEY_STREAM_NOTIFICATION));
    list.add(new VolumeModel(this, CFG, R.string.stream_ring, R.drawable.ring,
        Config.KEY_STREAM_RING));
    list.add(new VolumeModel(this, CFG, R.string.stream_system,
        R.drawable.system, Config.KEY_STREAM_SYSTEM));
    list.add(new VolumeModel(this, CFG, R.string.stream_voice_call,
        R.drawable.voice_call, Config.KEY_STREAM_VOICE_CALL));
    adapter = new VolumesArrayAdapter(this, R.layout.settings_volume_1x3, list);
    volumes.setAdapter(adapter);

    promptFeedback = (CheckBox) findViewById(R.id.chkPromptFeedback);
    promptFeedback.setChecked(CFG.get(Config.KEY_PROMPT_FEEDBACK, true));
    promptFeedback.setOnCheckedChangeListener(this);

  }

  @Override
  protected void atexit() {
  }

  @Override
  public void onCheckedChanged(final CompoundButton buttonView,
      final boolean isChecked) {
    if (buttonView.equals(promptFeedback)) {
      CFG.add(Config.KEY_PROMPT_FEEDBACK, isChecked);
    }
  }
}
