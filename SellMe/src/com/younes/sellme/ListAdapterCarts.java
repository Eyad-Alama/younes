package com.younes.sellme;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQueryAdapter;
import com.parse.ParseQueryAdapter.QueryFactory;

public class ListAdapterCarts extends ParseQueryAdapter {
	
	Context mContext;
	
	public ListAdapterCarts(Context context, String className) throws ParseException {
		super(context, className);
		mContext = context;
		
	}
	
	
	public ListAdapterCarts(ActivityCart context,
			QueryFactory<ParseObject> queryFactory) {
		
		super(context,queryFactory);
		mContext = context;
	}




	@Override
	public View getItemView(ParseObject object, View v, ViewGroup parent) {
		 if (v == null) {
			    v = View.inflate(getContext(), R.layout.row_product, null);
			    
			  }
		 
		 super.getItemView(object, v, parent);
		 
		
		 return v;
			 
	}


}
