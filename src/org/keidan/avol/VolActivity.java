package org.keidan.avol;

import static org.keidan.avol.Config.CFG;
import android.os.Bundle;
import android.view.KeyEvent;

public class VolActivity extends ActivityPopup {

  private ComponentsListener actions    = null;
  private Components         components = null;

  @Override
  public void onCreate(final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState, false, true, R.layout.main);

    try {
      CFG.create(this);
    } catch (final Exception e) {
      e.printStackTrace();
    }
    actions = new ComponentsListener(this);
    actions.refreshSilent();
    findViewById(R.id.btSilent).setOnTouchListener(actions);
    actions.initSeekBars();
    getComponents().setOnCheckedChangeListeners(actions);
  }

  @Override
  public boolean onKeyDown(final int keyCode, final KeyEvent event) {
    switch (keyCode) {
    case KeyEvent.KEYCODE_VOLUME_UP:
      actions.onVolumeChange(true);
      return true;
    case KeyEvent.KEYCODE_VOLUME_DOWN:
      actions.onVolumeChange(false);
      return true;
    case KeyEvent.KEYCODE_BACK:
      onBackPressed();
      return true;
    default:
      return false;
    }
  }

  /* disable audio prompt when volume up/down are invoked */
  @Override
  public boolean onKeyUp(final int keyCode, final KeyEvent event) {
    switch (keyCode) {
    case KeyEvent.KEYCODE_VOLUME_UP:
      if (getComponents().isPromptFeedbackChecked())
        return false;
      return true;
    case KeyEvent.KEYCODE_VOLUME_DOWN:
      if (getComponents().isPromptFeedbackChecked())
        return false;
      return true;
    default:
      return false;
    }
  }

  protected Components getComponents() {
    if (components == null)
      components = new Components(this);
    return components;
  }

  @Override
  protected void atexit() {
    actions.stopMedia();
  }
}
