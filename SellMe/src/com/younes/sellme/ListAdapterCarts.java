package com.younes.sellme;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.parse.ParseObject;

public class ListAdapterCarts extends ArrayAdapter<ParseObject> {
	  private final Context mContext;
	  private final List<ParseObject> values;

	  public ListAdapterCarts(Context context, int id,  List<ParseObject> values) {
	    super(context, id, values);
	    this.mContext = context;
	    this.values = values;
	  }

	  @Override
	  public View getView(int position, View convertView, ViewGroup parent) {
	    LayoutInflater inflater = (LayoutInflater) mContext
	        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	    View rowView = inflater.inflate(R.layout.row_cart, parent, false);
	    
	    TextView tvProductName = (TextView) rowView.findViewById(R.id.tvProductName);
	    TextView tvProductPrice = (TextView) rowView.findViewById(R.id.tvProductPrice);
	    
	    tvProductName.setText(values.get(position).getString("Name"));
	    tvProductPrice.setText("RM " + values.get(position).getInt("Price"));
	    
	    
	    
	    
	    return rowView;
	  }
	}