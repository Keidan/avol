package org.keidan.avol;

import static org.keidan.avol.Config.CFG;


import android.widget.CheckBox;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class Components {
  
  public static final String KEY_PROMPT_FEEDBACK = "prompt.feedback";
  private ImageButton        silent              = null;
  private SeekBar            alarm               = null;
  private SeekBar            dtmf                = null;
  private SeekBar            music               = null;
  private SeekBar            notification        = null;
  private SeekBar            ring                = null;
  private SeekBar            system              = null;
  private SeekBar            voiceCall           = null;
  private CheckBox           promptFeedback      = null;
  private VolActivity        adaptee             = null;
  
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
  
  public void setOnCheckedChangeListeners(final OnCheckedChangeListener actions) {
    getPromptFeedback().setOnCheckedChangeListener(actions);
  }
  
  public boolean isPromptFeedbackChecked() {
    return getPromptFeedback().isChecked();
  }
  
  public CheckBox getPromptFeedback() {
    if (promptFeedback == null) {
      promptFeedback = (CheckBox) adaptee.findViewById(R.id.chkPromptFeedback);
      promptFeedback.setChecked(CFG.get(KEY_PROMPT_FEEDBACK, true));
    }
    return promptFeedback;
  }
  
  public ImageButton getSilent() {
    if (silent == null)
      silent = (ImageButton) adaptee.findViewById(R.id.btSilent);
    return silent;
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
