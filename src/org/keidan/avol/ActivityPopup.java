package org.keidan.avol;

import android.app.Activity;
import android.os.Bundle;
import android.os.Process;
import android.view.Window;
import android.view.WindowManager;

public abstract class ActivityPopup extends Activity {

  protected void onCreate(final Bundle savedInstanceState, final boolean title,
      final boolean modal, final int layout_id) {
    super.onCreate(savedInstanceState);
    if (title)
      requestWindowFeature(Window.FEATURE_LEFT_ICON);
    else
      requestWindowFeature(Window.FEATURE_NO_TITLE);
    setContentView(layout_id);
    if (title) {
      setTitle(getResources().getText(R.string.title));
      setFeatureDrawableResource(Window.FEATURE_LEFT_ICON,
          R.drawable.ic_launcher);
    }
    overrideTransition(true);
    if (!modal)
      getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL,
          WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL);
  }

  private void overrideTransition(final boolean open) {
    if (open)
      overridePendingTransition(R.anim.animation_enter_in,
          R.anim.animation_enter_out);
    else
      overridePendingTransition(R.anim.animation_leave_in,
          R.anim.animation_leave_out);
  }
  
  private void exit() {
    atexit();
    Process.killProcess(Process.myPid());
    System.exit(0);
  }

  @Override
  public void onBackPressed() {
    super.onBackPressed();
    overrideTransition(false);
    finish();
    exit();
  }

  @Override
  public void onDestroy() {
    super.onDestroy();
    overrideTransition(false);
    exit();
  }
  
  protected abstract void atexit();
}
