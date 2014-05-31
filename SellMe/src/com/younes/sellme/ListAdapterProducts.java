package com.younes.sellme;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQueryAdapter;
import com.parse.ParseQueryAdapter.QueryFactory;

public class ListAdapterProducts extends ParseQueryAdapter {
	
	Context mContext;
	
	public ListAdapterProducts(Context context, String className) throws ParseException {
		super(context, className);
		mContext = context;
		
		

	}
	
	
	
	public ListAdapterProducts(ActivityProducts context,
			QueryFactory<ParseObject> queryFactory) {
		
		super(context,queryFactory);
		// TODO Auto-generated constructor stub
	}



	@Override
	public View getItemView(ParseObject object, View v, ViewGroup parent) {
		 if (v == null) {
			    v = View.inflate(getContext(), R.layout.row_product, null);
			    
			  }
		 
		 super.getItemView(object, v, parent);
		 
		 TextView txtAmount = (TextView) v.findViewById(R.id.txtAmount);
		 txtAmount.setText("RM " + object.getInt("Price".toString()));
		 
		 TextView txtName = (TextView) v.findViewById(R.id.txtCondition);
		 txtName.setText("Condition : " + object.getString("Condition"));
		 
		 return v;
			 
	}


}
