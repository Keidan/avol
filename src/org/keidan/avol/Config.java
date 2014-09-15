package org.keidan.avol;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class Config {
  public static final Config       CFG;
  private SharedPreferences        preferences = null;
  private SharedPreferences.Editor editor      = null;
  private static Config            instance    = null;

  static {
    CFG = getInstance();
  }

  public static Config getInstance() {
    if (instance == null)
      instance = new Config();
    return instance;
  }

  public void create(final Activity activity) {
    if (preferences == null) {
      preferences = activity.getPreferences(Context.MODE_PRIVATE);
      editor = preferences.edit();
    }
  }

  public boolean add(final String key, final String value) {
    editor.putString(key, value); // value to store
    return editor.commit();
  }

  public boolean add(final String key, final boolean value) {
    editor.putBoolean(key, value); // value to store
    return editor.commit();
  }

  public boolean del(final String key) {
    editor.remove(key);
    return editor.commit();
  }

  public boolean clear() {
    editor.clear();
    return editor.commit();
  }

  public boolean contains(final String key) {
    return preferences.contains(key);
  }

  public String get(final String key, final String defaults) {
    if (!contains(key))
      return defaults;
    final String tmp = preferences.getString(key, defaults);
    if (tmp == null) return defaults;
    return tmp;
  }

  public boolean get(final String key, final boolean defaults) {
    if (!contains(key)) return defaults;
    return preferences.getBoolean(key, defaults);
  }
}
