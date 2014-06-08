package com.younes.sellme;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

public class ActivityProductDetails extends Activity {
	
	ImageView ivImage;
	TextView tvDesc;
	TextView tvProductCondition;
	TextView tvProductPrice;
	LinearLayout llAddToCart;
	
	ParseObject Product;
	
	Bitmap bmp;
	private Context mContext;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_product_details);
		
		mContext = this;
		ivImage = (ImageView) findViewById(R.id.ivImage);
		tvDesc = (TextView) findViewById(R.id.tvDesc);
		tvProductCondition = (TextView) findViewById(R.id.tvProductCondition);
		tvProductPrice = (TextView) findViewById(R.id.tvProductPrice);
		llAddToCart = (LinearLayout) findViewById(R.id.llAddToCart);
		
		llAddToCart.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				ParseQuery<ParseObject> query = ParseQuery.getQuery("Cart");
				query.whereEqualTo("prod_id",Product.getObjectId());
				query.whereEqualTo("userID",ParseUser.getCurrentUser().getObjectId());
				
				Log.d("prod_id", Product.getObjectId());
				Log.d("userID", ParseUser.getCurrentUser().getObjectId());
				query.findInBackground(new FindCallback<ParseObject>() {
				    public void done(List<ParseObject> scoreList, ParseException e) {
				        if (e == null) {
				        	if(scoreList.size() !=0)
				        	{
				            ParseObject ob = scoreList.get(0);
				            
				            
				            	ob.increment("quantity");
				            	ob.saveInBackground();
				            	Toast.makeText(mContext, "Item Added to cart !", Toast.LENGTH_SHORT).show();
				            	
				            }
				            else
				            {
				            	ParseObject object = new ParseObject("Cart");
						        object.put("prod_id", MyApplication.Product.getObjectId());
						        object.put("quantity",1);
						        object.put("userID", ParseUser.getCurrentUser().getObjectId());
						        object.saveInBackground();
						        Toast.makeText(mContext, "Item Added to cart !", Toast.LENGTH_SHORT).show();
				            }
				        } else {
				            Log.d("score", "Error: " + e.getMessage());
				        }
				    }
				});
				
				
				
		        
				
			}
		});
		
		Product= MyApplication.Product;
		
		
		tvProductCondition.setText(Product.getString("Condition"));
		tvProductPrice.setText("RM " + Product.getInt("Price"));
		tvDesc.setText(Product.getString("Description"));
		
		ParseFile applicantResume = (ParseFile)Product.get("Image");
		applicantResume.getDataInBackground(new GetDataCallback() {
		  public void done(byte[] data, ParseException e) {
		    if (e == null) {
		      
		    	
				bmp = BitmapFactory.decodeByteArray(data, 0, data.length);
				ivImage.setImageBitmap(bmp);
				Bitmap ss = bmp;
				
				
				
		    } else {
		      // something went wrong
		    }
		  }
		});
		
		
 		
 		
 		
		

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
		
		if(id == R.id.action_cart)
		{
			Intent cartInt = new Intent(mContext, ActivityCart.class);
			mContext.startActivity(cartInt);
		}
		return super.onOptionsItemSelected(item);
	}

	 
	
}
