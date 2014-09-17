package org.keidan.avol;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public abstract class ActivityPopup extends Activity {
  private boolean back_finish = false;
  
  protected void onCreate(final Bundle savedInstanceState, final boolean title,
      final boolean modal, final boolean back_finish, final int layout_id) {
    super.onCreate(savedInstanceState);
    this.back_finish = back_finish;
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

  @Override
  public void onBackPressed() {
    super.onBackPressed();
    overrideTransition(false);
    if(back_finish)
      finish();
    atexit();
  }

  @Override
  public void onDestroy() {
    super.onDestroy();
    overrideTransition(false);
    atexit();
  }

  protected abstract void atexit();
  
  public void switchTo(final Class<?> c) {
    switchTo(c, null, null);
  }
  
  public void switchTo(final Class<?> c, final String extraKey, final String extraValue) {
    final Intent i = new Intent(getApplicationContext(), c);
    if (extraKey != null && extraValue != null)
      i.putExtra(extraKey, extraValue);
    startActivity(i);
    overrideTransition(true);
  }
}
