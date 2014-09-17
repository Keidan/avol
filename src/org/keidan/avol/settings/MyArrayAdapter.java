package org.keidan.avol.settings;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

public abstract class MyArrayAdapter<T> extends ArrayAdapter<T> {
  protected Context          c            = null;
  protected int              id           = 0;
  protected List<T>          items        = null;
  
  static class ViewHolder {
    public ImageView   image;
    public TextView    text;
    public CheckBox    check;
  }

  public MyArrayAdapter(final Context context, final int textViewResourceId,
      final List<T> objects) {
    super(context, textViewResourceId, objects);
    c = context;
    id = textViewResourceId;
    items = objects;
  }

  @Override
  public T getItem(final int i) {
    return items.get(i);
  }

  public int getItemCount() {
    return items.size();
  }

  public void addItem(final T t) {
    items.add(t);
    super.notifyDataSetChanged();
  }

  public void removeItem(final T t) {
    items.remove(t);
    super.notifyDataSetChanged();
  }

  public void removeItem(final int i) {
    items.remove(i);
    super.notifyDataSetChanged();
  }
  
  public void clear() {
    items.clear();
    super.clear();
  }

  @Override
  public abstract View getView(final int position, final View convertView,
      final ViewGroup parent);
}
