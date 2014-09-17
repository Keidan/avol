package org.keidan.avol.settings;

import org.keidan.avol.Config;

import android.app.Activity;

public class VolumeModel {
  private boolean enable   = false;
  private String  label    = null;
  private int     resource = 0;
  private Config  cfg      = null;
  private String  cfg_name = null;

  public VolumeModel(final Activity a, final Config cfg, final int label_id,
      final int resource, final String cfg_name) {
    this.label = a.getString(label_id);
    this.resource = resource;
    this.enable = cfg.get(cfg_name, true);
    this.cfg = cfg;
    this.cfg_name = cfg_name;
  }

  public void update() {
    enable = !enable;
    cfg.add(cfg_name, enable);
  }

  public boolean isEnable() {
    return enable;
  }

  public void setEnable(final boolean enable) {
    this.enable = enable;
  }

  public String getLabel() {
    return label;
  }

  public void setLabel(final String label) {
    this.label = label;
  }

  public int getResource() {
    return resource;
  }

  public void setResource(final int resource) {
    this.resource = resource;
  }

}