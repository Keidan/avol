package org.keidan.avol.main;

import static org.keidan.avol.Config.CFG;

import org.keidan.avol.Config;
import org.keidan.avol.R;
import org.keidan.avol.VolActivity;

import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class Components {

  private ImageButton silent        = null;
  private ImageButton settings      = null;
  private TextView    labelSettings = null;

  private SeekBar     alarm         = null;
  private SeekBar     dtmf          = null;
  private SeekBar     music         = null;
  private SeekBar     notification  = null;
  private SeekBar     ring          = null;
  private SeekBar     system        = null;
  private SeekBar     voiceCall     = null;
  private VolActivity adaptee       = null;

  public Components(final VolActivity adaptee) {
    this.adaptee = adaptee;
  }

  public void setOnSeekBarChangeListeners(final OnSeekBarChangeListener actions) {
    getAlarm().setOnSeekBarChangeListener(actions);
    getDtmf().setOnSeekBarChangeListener(actions);
    getMusic().setOnSeekBarChangeListener(actions);
    getNotification().setOnSeekBarChangeListener(actions);
    getRing().setOnSeekBarChangeListener(actions);
    getSystem().setOnSeekBarChangeListener(actions);
    getVoiceCall().setOnSeekBarChangeListener(actions);
  }

  public void reloadComponents(final Config cfg) {
    if (cfg.get(Config.KEY_STREAM_ALARM, true)) {
      getAlarm().setVisibility(View.VISIBLE);
      adaptee.findViewById(R.id.textViewAlarm).setVisibility(View.VISIBLE);
      adaptee.findViewById(R.id.layoutAlarm).setVisibility(View.VISIBLE);
      adaptee.findViewById(R.id.imageViewAlarm).setVisibility(View.VISIBLE);
    } else {
      adaptee.getComponents().getAlarm().setVisibility(View.GONE);
      adaptee.findViewById(R.id.textViewAlarm).setVisibility(View.GONE);
      adaptee.findViewById(R.id.layoutAlarm).setVisibility(View.GONE);
      adaptee.findViewById(R.id.imageViewAlarm).setVisibility(View.GONE);
    }
    if (cfg.get(Config.KEY_STREAM_DTMF, true)) {
      adaptee.getComponents().getDtmf().setVisibility(View.VISIBLE);
      adaptee.findViewById(R.id.textViewDTMF).setVisibility(View.VISIBLE);
      adaptee.findViewById(R.id.layoutDTMF).setVisibility(View.VISIBLE);
      adaptee.findViewById(R.id.imageViewDTMF).setVisibility(View.VISIBLE);
    } else {
      adaptee.getComponents().getDtmf().setVisibility(View.GONE);
      adaptee.findViewById(R.id.textViewDTMF).setVisibility(View.GONE);
      adaptee.findViewById(R.id.layoutDTMF).setVisibility(View.GONE);
      adaptee.findViewById(R.id.imageViewDTMF).setVisibility(View.GONE);
    }
    if (cfg.get(Config.KEY_STREAM_MUSIC, true)) {
      adaptee.getComponents().getMusic().setVisibility(View.VISIBLE);
      adaptee.findViewById(R.id.textViewMusic).setVisibility(View.VISIBLE);
      adaptee.findViewById(R.id.layoutMusic).setVisibility(View.VISIBLE);
      adaptee.findViewById(R.id.imageViewMusic).setVisibility(View.VISIBLE);
    } else {
      adaptee.getComponents().getMusic().setVisibility(View.GONE);
      adaptee.findViewById(R.id.textViewMusic).setVisibility(View.GONE);
      adaptee.findViewById(R.id.layoutMusic).setVisibility(View.GONE);
      adaptee.findViewById(R.id.imageViewMusic).setVisibility(View.GONE);
    }
    if (cfg.get(Config.KEY_STREAM_NOTIFICATION, true)) {
      adaptee.getComponents().getNotification().setVisibility(View.VISIBLE);
      adaptee.findViewById(R.id.textViewNotification).setVisibility(
          View.VISIBLE);
      adaptee.findViewById(R.id.layoutNotification).setVisibility(View.VISIBLE);
      adaptee.findViewById(R.id.imageViewNotification).setVisibility(
          View.VISIBLE);
    } else {
      adaptee.getComponents().getNotification().setVisibility(View.GONE);
      adaptee.findViewById(R.id.textViewNotification).setVisibility(View.GONE);
      adaptee.findViewById(R.id.layoutNotification).setVisibility(View.GONE);
      adaptee.findViewById(R.id.imageViewNotification).setVisibility(View.GONE);
    }
    if (cfg.get(Config.KEY_STREAM_RING, true)) {
      adaptee.getComponents().getRing().setVisibility(View.VISIBLE);
      adaptee.findViewById(R.id.textViewRing).setVisibility(View.VISIBLE);
      adaptee.findViewById(R.id.layoutRing).setVisibility(View.VISIBLE);
      adaptee.findViewById(R.id.imageViewRing).setVisibility(View.VISIBLE);
    } else {
      adaptee.getComponents().getRing().setVisibility(View.GONE);
      adaptee.findViewById(R.id.textViewRing).setVisibility(View.GONE);
      adaptee.findViewById(R.id.layoutRing).setVisibility(View.GONE);
      adaptee.findViewById(R.id.imageViewRing).setVisibility(View.GONE);
    }
    if (cfg.get(Config.KEY_STREAM_SYSTEM, true)) {
      adaptee.getComponents().getSystem().setVisibility(View.VISIBLE);
      adaptee.findViewById(R.id.textViewSystem).setVisibility(View.VISIBLE);
      adaptee.findViewById(R.id.layoutSystem).setVisibility(View.VISIBLE);
      adaptee.findViewById(R.id.imageViewSystem).setVisibility(View.VISIBLE);
    } else {
      adaptee.getComponents().getSystem().setVisibility(View.GONE);
      adaptee.findViewById(R.id.textViewSystem).setVisibility(View.GONE);
      adaptee.findViewById(R.id.layoutSystem).setVisibility(View.GONE);
      adaptee.findViewById(R.id.imageViewSystem).setVisibility(View.GONE);
    }
    if (cfg.get(Config.KEY_STREAM_VOICE_CALL, true)) {
      adaptee.getComponents().getVoiceCall().setVisibility(View.VISIBLE);
      adaptee.findViewById(R.id.textViewVoiceCall).setVisibility(View.VISIBLE);
      adaptee.findViewById(R.id.layoutVoiceCall).setVisibility(View.VISIBLE);
      adaptee.findViewById(R.id.imageViewVoiceCall).setVisibility(View.VISIBLE);
    } else {
      adaptee.getComponents().getVoiceCall().setVisibility(View.GONE);
      adaptee.findViewById(R.id.textViewVoiceCall).setVisibility(View.GONE);
      adaptee.findViewById(R.id.layoutVoiceCall).setVisibility(View.GONE);
      adaptee.findViewById(R.id.imageViewVoiceCall).setVisibility(View.GONE);
    }
  }

  public boolean isPromptFeedbackChecked() {
    return CFG.get(Config.KEY_PROMPT_FEEDBACK, true);
  }

  public ImageButton getSilent() {
    if (silent == null)
      silent = (ImageButton) adaptee.findViewById(R.id.btSilent);
    return silent;
  }

  public TextView getLabelSettings() {
    if (labelSettings == null)
      labelSettings = (TextView) adaptee.findViewById(R.id.textViewSettings);
    return labelSettings;
  }

  public ImageButton getSettings() {
    if (settings == null)
      settings = (ImageButton) adaptee.findViewById(R.id.btSettings);
    return settings;
  }

  public SeekBar getAlarm() {
    if (alarm == null)
      alarm = (SeekBar) adaptee.findViewById(R.id.seekBarAlarm);
    return alarm;
  }

  public SeekBar getDtmf() {
    if (dtmf == null)
      dtmf = (SeekBar) adaptee.findViewById(R.id.seekBarDTMF);
    return dtmf;
  }

  public SeekBar getMusic() {
    if (music == null)
      music = (SeekBar) adaptee.findViewById(R.id.seekBarMusic);
    return music;
  }

  public SeekBar getNotification() {
    if (notification == null)
      notification = (SeekBar) adaptee.findViewById(R.id.seekBarNotification);
    return notification;
  }

  public SeekBar getRing() {
    if (ring == null)
      ring = (SeekBar) adaptee.findViewById(R.id.seekBarRing);
    return ring;
  }

  public SeekBar getSystem() {
    if (system == null)
      system = (SeekBar) adaptee.findViewById(R.id.seekBarSystem);
    return system;
  }

  public SeekBar getVoiceCall() {
    if (voiceCall == null)
      voiceCall = (SeekBar) adaptee.findViewById(R.id.seekBarVoiceCall);
    return voiceCall;
  }

}
