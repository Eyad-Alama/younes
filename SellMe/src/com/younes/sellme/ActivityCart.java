package com.younes.sellme;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;
import com.parse.ParseUser;

public class ActivityCart extends ListActivity {
	ArrayList<String> prod_ids = new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cart);
		
		
		final String userID = ParseUser.getCurrentUser().getObjectId();
		
		ParseQuery<ParseObject> query = ParseQuery.getQuery("Cart");
		query.whereEqualTo("userID", ParseUser.getCurrentUser().getObjectId());
		query.findInBackground(new FindCallback<ParseObject>() {
		    public void done(List<ParseObject> cartItems, ParseException e) {
		        if (e == null) {
		        	//Another query to get the products
		        	
		        	for (ParseObject parseObject : cartItems) {
						prod_ids.add(parseObject.getString("prod_id"));
					}
		        	ParseQuery<ParseObject> query = ParseQuery.getQuery("Products");
		    		query.whereContainedIn("cart_id", prod_ids);
		    		query.findInBackground(new FindCallback<ParseObject>() {
		    		    public void done(List<ParseObject> products_list, ParseException e) {
		    		        if (e == null) {
		    		        	//Here is a list of products that we want the names from
		    		        	
		    		            
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
		
		
		 ListAdapterCarts adapter =
       		  new ListAdapterCarts(this, new ParseQueryAdapter.QueryFactory<ParseObject>() {
       		    public ParseQuery<ParseObject> create() {
       		      // Here we can configure a ParseQuery to our heart's desire.
       		      ParseQuery<ParseObject> query = new ParseQuery("Cart");
       		      query.whereEqualTo("userID", userID);
       		      return query;
       		    }
       		  });
		 
       
       adapter.setTextKey("Name");
       adapter.setImageKey("Image");
       
       
       // Assign adapter to List
       setListAdapter(adapter);
		
		

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.product_details, menu);
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

	
}
