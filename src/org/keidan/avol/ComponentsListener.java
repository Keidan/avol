package org.keidan.avol;

import static org.keidan.avol.Config.CFG;
import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.RingtoneManager;
import android.net.Uri;
import android.util.Log;
import android.view.HapticFeedbackConstants;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class ComponentsListener implements OnTouchListener,
    OnSeekBarChangeListener, OnCheckedChangeListener, OnCompletionListener {

  private int          previousMode = -1;
  private MediaPlayer  mediaPlayer  = null;
  private AudioManager audioManager = null;
  private VolActivity  adaptee      = null;

  public ComponentsListener(final VolActivity adaptee) {
    this.adaptee = adaptee;
    mediaPlayer = new MediaPlayer();
    mediaPlayer.setOnCompletionListener(this);
    mediaPlayer.setLooping(false);
  }
  
  protected void onVolumeChange(final boolean up) {
    int volume = getAudioManager().getStreamVolume(
        AudioManager.STREAM_RING);
    if(up) {
        if (volume < adaptee.getComponents().getRing().getMax()) {
          volume++;
          seekUpdate(adaptee.getComponents().getRing(), volume, true);
          adaptee.getComponents().getRing().setProgress(volume);
        }
    } else {
        if (volume > 0) {
          volume--;
          seekUpdate(adaptee.getComponents().getRing(), volume, true);
          adaptee.getComponents().getRing().setProgress(volume);
        }
    }
  }

  protected void initSeekBars() {
    adaptee
        .getComponents()
        .getAlarm()
        .setMax(getAudioManager().getStreamMaxVolume(AudioManager.STREAM_ALARM));
    adaptee.getComponents().getDtmf()
        .setMax(getAudioManager().getStreamMaxVolume(AudioManager.STREAM_DTMF));
    adaptee
        .getComponents()
        .getMusic()
        .setMax(getAudioManager().getStreamMaxVolume(AudioManager.STREAM_MUSIC));
    adaptee
        .getComponents()
        .getNotification()
        .setMax(
            getAudioManager().getStreamMaxVolume(
                AudioManager.STREAM_NOTIFICATION));
    adaptee.getComponents().getRing()
        .setMax(getAudioManager().getStreamMaxVolume(AudioManager.STREAM_RING));
    adaptee
        .getComponents()
        .getSystem()
        .setMax(
            getAudioManager().getStreamMaxVolume(AudioManager.STREAM_SYSTEM));
    adaptee
        .getComponents()
        .getVoiceCall()
        .setMax(
            getAudioManager()
                .getStreamMaxVolume(AudioManager.STREAM_VOICE_CALL));

    adaptee.getComponents().setOnSeekBarChangeListeners(this);
    refreshSeekBars();
  }

  protected void refreshSeekBars() {
    adaptee
        .getComponents()
        .getAlarm()
        .setProgress(
            getAudioManager().getStreamVolume(AudioManager.STREAM_ALARM));
    adaptee
        .getComponents()
        .getDtmf()
        .setProgress(
            getAudioManager().getStreamVolume(AudioManager.STREAM_DTMF));
    adaptee
        .getComponents()
        .getMusic()
        .setProgress(
            getAudioManager().getStreamVolume(AudioManager.STREAM_MUSIC));
    adaptee
        .getComponents()
        .getNotification()
        .setProgress(
            getAudioManager().getStreamVolume(AudioManager.STREAM_NOTIFICATION));
    adaptee
        .getComponents()
        .getRing()
        .setProgress(
            getAudioManager().getStreamVolume(AudioManager.STREAM_RING));
    adaptee
        .getComponents()
        .getSystem()
        .setProgress(
            getAudioManager().getStreamVolume(AudioManager.STREAM_SYSTEM));
    adaptee
        .getComponents()
        .getVoiceCall()
        .setProgress(
            getAudioManager().getStreamVolume(AudioManager.STREAM_VOICE_CALL));
    initText(adaptee, R.string.stream_alarm, R.id.textViewAlarm, adaptee
        .getComponents().getAlarm());
    initText(adaptee, R.string.stream_dtmf, R.id.textViewDTMF, adaptee
        .getComponents().getDtmf());
    initText(adaptee, R.string.stream_music, R.id.textViewMusic, adaptee
        .getComponents().getMusic());
    initText(adaptee, R.string.stream_notification, R.id.textViewNotification,
        adaptee.getComponents().getNotification());
    initText(adaptee, R.string.stream_ring, R.id.textViewRing, adaptee
        .getComponents().getRing());
    initText(adaptee, R.string.stream_system, R.id.textViewSystem, adaptee
        .getComponents().getSystem());
    initText(adaptee, R.string.stream_voice_call, R.id.textViewVoiceCall,
        adaptee.getComponents().getVoiceCall());
  }

  protected static void initText(final Activity ctx, final int string_id,
      final int tv_id, final SeekBar seek) {
    initText(ctx, string_id, tv_id, seek.getMax(), seek.getProgress());
  }

  protected static void initText(final Activity ctx, final int string_id,
      final int tv_id, final int max_progress, final int progress) {
    final StringBuilder text = new StringBuilder();
    text.append(ctx.getResources().getString(string_id));
    text.append(" (").append(progress).append("/").append(max_progress)
        .append(")");
    text.append(":");
    final TextView tv = (TextView) ctx.findViewById(tv_id);
    tv.setText(text.toString());
  }

  protected AudioManager getAudioManager() {
    if (audioManager == null)
      audioManager = (AudioManager) adaptee
          .getSystemService(Context.AUDIO_SERVICE);
    return audioManager;
  }

  protected void refreshSilent() {
    previousMode = getAudioManager().getRingerMode();
    switch (getAudioManager().getRingerMode()) {
    case AudioManager.RINGER_MODE_NORMAL:
      adaptee.getComponents().getSilent()
          .setBackgroundResource(R.drawable.unmuted);
      break;
    case AudioManager.RINGER_MODE_VIBRATE:
      adaptee.getComponents().getSilent()
          .setBackgroundResource(R.drawable.vibrate);
      break;
    case AudioManager.RINGER_MODE_SILENT:
      adaptee.getComponents().getSilent()
          .setBackgroundResource(R.drawable.muted);
      break;
    }
  }

  protected synchronized void stopMedia() {
    // if (mediaPlayer.isPlaying())
    // mediaPlayer.stop();
    mediaPlayer.release();
  }

  @Override
  public boolean onTouch(final View v, final MotionEvent event) {
    if (v.equals(adaptee.getComponents().getSilent())) {
      // stopMedia(); /* free resources */
      if (event.getAction() == MotionEvent.ACTION_DOWN) {
        if (previousMode == AudioManager.RINGER_MODE_SILENT)
          getAudioManager().setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
        else if (previousMode == AudioManager.RINGER_MODE_VIBRATE)
          getAudioManager().setRingerMode(AudioManager.RINGER_MODE_NORMAL);
        else
          getAudioManager().setRingerMode(AudioManager.RINGER_MODE_SILENT);
        refreshSilent();
        adaptee.getComponents().getSilent()
            .performHapticFeedback(HapticFeedbackConstants.LONG_PRESS);
        /* inside your non-UI thread */
        adaptee.getComponents().getSilent().post(new Runnable() {
          @Override
          public void run() {
            refreshSeekBars();
          }
        });
      }
    }
    return false;
  }

  @Override
  public void onProgressChanged(final SeekBar seekBar, final int progress,
      final boolean fromUser) {
    if (fromUser)
      seekUpdate(seekBar, progress, false);
  }

  protected void seekUpdate(final SeekBar seekBar, final int progress,
      final boolean forceNoPlay) {
    int type = -1, string_id = -1, tv_id = -1;
    if (seekBar.equals(adaptee.getComponents().getAlarm())) {
      type = AudioManager.STREAM_ALARM;
      string_id = R.string.stream_alarm;
      tv_id = R.id.textViewAlarm;
    } else if (seekBar.equals(adaptee.getComponents().getDtmf())) {
      type = AudioManager.STREAM_DTMF;
      string_id = R.string.stream_dtmf;
      tv_id = R.id.textViewDTMF;
    } else if (seekBar.equals(adaptee.getComponents().getMusic())) {
      type = AudioManager.STREAM_MUSIC;
      string_id = R.string.stream_music;
      tv_id = R.id.textViewMusic;
    } else if (seekBar.equals(adaptee.getComponents().getNotification())) {
      type = AudioManager.STREAM_NOTIFICATION;
      string_id = R.string.stream_notification;
      tv_id = R.id.textViewNotification;
    } else if (seekBar.equals(adaptee.getComponents().getRing())) {
      type = AudioManager.STREAM_RING;
      string_id = R.string.stream_ring;
      tv_id = R.id.textViewRing;
    } else if (seekBar.equals(adaptee.getComponents().getSystem())) {
      type = AudioManager.STREAM_SYSTEM;
      string_id = R.string.stream_system;
      tv_id = R.id.textViewSystem;
    } else if (seekBar.equals(adaptee.getComponents().getVoiceCall())) {
      type = AudioManager.STREAM_VOICE_CALL;
      string_id = R.string.stream_voice_call;
      tv_id = R.id.textViewVoiceCall;
    }
    if (type != -1) {
      initText(adaptee, string_id, tv_id, seekBar.getMax(), progress);
      getAudioManager().setMode(AudioManager.MODE_NORMAL);
      stopMedia();

      int rmType = 0, flag = 0;
      if (type == AudioManager.STREAM_ALARM)
        rmType = RingtoneManager.TYPE_ALARM;
      else if (type == AudioManager.STREAM_RING)
        rmType = RingtoneManager.TYPE_RINGTONE;
      else if (type == AudioManager.STREAM_NOTIFICATION
          || type == AudioManager.STREAM_SYSTEM)
        rmType = RingtoneManager.TYPE_NOTIFICATION;
      else if (adaptee.getComponents().isPromptFeedbackChecked())
        flag = AudioManager.FLAG_PLAY_SOUND;

      getAudioManager().setStreamVolume(type, progress, flag);
      if (rmType != 0 && adaptee.getComponents().isPromptFeedbackChecked()
          && !forceNoPlay) {
        final Uri ringtoneUri = RingtoneManager.getDefaultUri(rmType);
        try {

          mediaPlayer.setDataSource(adaptee.getApplicationContext(),
              ringtoneUri);
          mediaPlayer.setAudioStreamType(type);
          mediaPlayer.prepare();
          mediaPlayer.start();
        } catch (final Exception e) {
          Log.e(getClass().getSimpleName(), "Exception: ", e);
          e.printStackTrace();
        }
      }
    }
  }

  @Override
  public void onCheckedChanged(final CompoundButton buttonView,
      final boolean isChecked) {
    if (buttonView.equals(adaptee.getComponents().getPromptFeedback())) {
      CFG.add(Components.KEY_PROMPT_FEEDBACK, isChecked);
    }
  }

  @Override
  public void onStartTrackingTouch(final SeekBar seekBar) {
  }

  @Override
  public void onStopTrackingTouch(final SeekBar seekBar) {
  }

  @Override
  public void onCompletion(final MediaPlayer mp) {
    Log.e(getClass().getSimpleName(), "******** onCompletion");
  }

}
