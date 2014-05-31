package com.younes.sellme;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQueryAdapter;

public class ListAdapterCategories extends ParseQueryAdapter {
	
	Context mContext;
	
	public ListAdapterCategories(Context context, String className) throws ParseException {
		super(context, className);
		mContext = context;

	



	}
	
	@Override
	public View getItemView(ParseObject object, View v, ViewGroup parent) {
		 if (v == null) {
			    v = View.inflate(getContext(), R.layout.row_category, null);
			    
			  }
		 
		 super.getItemView(object, v, parent);
		 
		 
		 return v;
			 
	}


}
