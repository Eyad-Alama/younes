package com.younes.sellme;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.parse.ParseObject;

public class ListAdapterCarts extends ArrayAdapter<String> {
	  private final Context mContext;
	  private final List<ParseObject> values;

	  public ListAdapterCarts(Context context, List<ParseObject> values) {
	    super(context, R.layout.row_cart);
	    this.mContext = context;
	    this.values = values;
	  }

	  @Override
	  public View getView(int position, View convertView, ViewGroup parent) {
	    LayoutInflater inflater = (LayoutInflater) mContext
	        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	    View rowView = inflater.inflate(R.layout.row_cart, parent, false);
	    
	    
	    
	    
	    return rowView;
	  }
	}