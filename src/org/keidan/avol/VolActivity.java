package org.keidan.avol;

import static org.keidan.avol.Config.CFG;

import org.keidan.avol.main.Components;
import org.keidan.avol.main.ComponentsListener;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;

public class VolActivity extends ActivityPopup {

  private ComponentsListener actions    = null;
  private Components         components = null;
  
  @Override
  protected void onResume() {
    String action = getIntent().getAction();
    // Prevent endless loop by adding a unique action, don't restart if action is present
    if(action == null || !action.equals("Already created")) {
        Log.v(getClass().getSimpleName(), "Force restart");
        Intent intent = new Intent(this, VolActivity.class);
        startActivity(intent);
        finish();
    }
    // Remove the unique action so the next time onResume is called it will restart
    else
        getIntent().setAction(null);

    super.onResume();
  }
  
  @Override
  public void onCreate(final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState, false, true, true, R.layout.main);
    getIntent().setAction("Already created");
    try {
      CFG.create(this);
    } catch (final Exception e) {
      e.printStackTrace();
    }
    getComponents().reloadComponents(CFG);
    actions = new ComponentsListener(this);
    actions.refreshSilent();
    actions.refreshSettings();
    findViewById(R.id.btSilent).setOnTouchListener(actions);
    findViewById(R.id.btSettings).setOnClickListener(actions);
    findViewById(R.id.textViewSettings).setOnClickListener(actions);
    actions.initSeekBars();
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

  public Components getComponents() {
    if (components == null)
      components = new Components(this);
    return components;
  }

  @Override
  protected void atexit() {
    actions.stopMedia();
  }
}
