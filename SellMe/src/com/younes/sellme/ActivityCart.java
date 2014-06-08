package com.younes.sellme;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

public class ActivityCart extends ListActivity {
	ArrayList<String> prod_ids = new ArrayList<String>();
	private Context mContext;
	ListAdapterCarts adapter;
	TextView txtTotal;
	private ProgressDialog mPD;
	List<ParseObject> CartList;
	TextView txtEmpty;
	Button btnClear;
	TextView txtLabel;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cart);
		mContext = this;
		txtTotal = (TextView) findViewById(R.id.txtTotal);
		txtEmpty = (TextView) findViewById(R.id.txtEmpty);
		txtLabel = (TextView) findViewById(R.id.txtLabel);
		btnClear = (Button) findViewById(R.id.btnClear);
		
		
		getData();
	}

	public void getData() {
		showWait("Updating Cart");
		final String userID = ParseUser.getCurrentUser().getObjectId();
		
		
		ParseQuery<ParseObject> query = ParseQuery.getQuery("Cart");
		query.whereEqualTo("userID", userID);
		query.findInBackground(new FindCallback<ParseObject>() {
		    public void done(List<ParseObject> cartItems, ParseException e) {
		        if (e == null) {
		        	//Another query to get the products
		        	CartList  = cartItems;
		        	for (ParseObject parseObject : cartItems) {
		        		int quantity = parseObject.getInt("quantity");
		        		for(int i=0; i<quantity; i++){
		        			prod_ids.add(parseObject.getString("prod_id"));    
		               }
		        		
		        		
					}
		        	Log.d("Prod_ids count : ",String.valueOf(prod_ids.size()));
		        	ParseQuery<ParseObject> query1 = ParseQuery.getQuery("Products");
		    		query1.whereContainedIn("objectId", prod_ids);
		    		
		    		query1.findInBackground(new FindCallback<ParseObject>() {
		    		    

						public void done(List<ParseObject> products_list, ParseException e) {
		    		        if (e == null) {
		    		        	//Here is a list of products that we want the names from
		    		        	if(products_list.size()==0)
		    		        	{
		    		        		txtEmpty.setVisibility(View.VISIBLE);
		    		        		btnClear.setVisibility(View.GONE);
		    		        		txtLabel.setVisibility(View.GONE);
		    		        	}
		    		        	
		    		        	else
		    		        	{
		    		        		
		    		        	adapter =
		    		             		  new ListAdapterCarts(mContext,R.layout.row_cart, products_list);
		    		        	setListAdapter(adapter);
		    		        	
		    		        	int Tot = 0;
		    		        	
		    		        	for (ParseObject prod : products_list) {
									Tot += prod.getInt("Price");
								}
		    		        	
		    		        	txtTotal.setText("RM "+String.valueOf(Tot));
		    		        	
		    		        	
		    		        }	
		    		        	hideWait();
		    		            
		    		        } else {
		    		            Log.d("score", "Error: " + e.getMessage());
		    		        }
		    		    }
		    		});
		            
		        } else {
		            Log.d("score", "Error: " + e.getMessage());
		        }
		    }
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_cart, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	
	public void showWait(String txt) {
		if (mPD == null || !mPD.isShowing()) {
			mPD = new ProgressDialog(this);
			mPD.setCancelable(false);
			mPD.setCanceledOnTouchOutside(false);
			mPD.setMessage(txt);
			mPD.show();
		}
	}

    
    public void hideWait() {
		if (mPD != null && mPD.isShowing()) {

			mPD.cancel();
		} 
	}
    
    public void clearCart(View v)
    {
    	showWait("Clearing Cart");
    	for (ParseObject parseObject : CartList)
    	{
    		parseObject.deleteInBackground();
    	}
    	
    	adapter.clear();
    	txtTotal.setText("");
    	txtEmpty.setVisibility(View.VISIBLE);
    	txtLabel.setVisibility(View.GONE);
    	v.setVisibility(View.GONE);
    	hideWait();
    	//getData();
    	
    }

	
}
