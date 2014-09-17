package org.keidan.avol.settings;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.keidan.avol.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

@SuppressLint("ViewConstructor")
public class VolumesArrayAdapter extends MyArrayAdapter<VolumeModel> {

  public VolumesArrayAdapter(final Context context,
      final int textViewResourceId, final List<VolumeModel> objects) {
    super(context, textViewResourceId, objects);
  }

  public void sort() {
    Collections.sort(items, new Comparator<VolumeModel>() {
      @Override
      public int compare(final VolumeModel lhs, final VolumeModel rhs) {
        return lhs.getLabel().compareTo(rhs.getLabel());
      }
    });
  }

  public boolean contains(final VolumeModel p) {
    for (final VolumeModel sm : items) {
      if (sm.getLabel().equals(p.getLabel()))
        return true;
    }
    return false;
  }

  @Override
  public View getView(final int position, final View convertView,
      final ViewGroup parent) {
    View v = convertView;
    if (v == null) {
      final LayoutInflater vi = (LayoutInflater) c
          .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
      final LayoutInflater inflater = vi;
      v = inflater.inflate(id, null);
      final ViewHolder viewHolder = new ViewHolder();
      viewHolder.check = (CheckBox) v.findViewById(R.id.check);
      viewHolder.image = (ImageView) v.findViewById(R.id.image);
      viewHolder.text = (TextView) v.findViewById(R.id.text);
      v.setTag(viewHolder);
      viewHolder.check.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(final View v) {
          final CheckBox cb = (CheckBox) v;
          final VolumeModel vm = (VolumeModel) cb.getTag();
          vm.update();
        }
      });
    }

    final ViewHolder holder = (ViewHolder) v.getTag();
    final VolumeModel o = items.get(position);
    holder.text.setText(o.getLabel());
    holder.check.setChecked(o.isEnable());
    holder.image.setImageResource(o.getResource());
    holder.check.setTag(o);
    return v;
  }

}
