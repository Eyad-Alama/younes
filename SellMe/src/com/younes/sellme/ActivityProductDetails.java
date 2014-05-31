package com.younes.sellme;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;

public class ActivityProductDetails extends Activity {
	
	ImageView ivImage;
	TextView tvDesc;
	TextView tvProductCondition;
	TextView tvProductPrice;
	LinearLayout llAddToCart;
	
	ParseObject Product;
	
	Bitmap bmp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_product_details);
		
		
		ivImage = (ImageView) findViewById(R.id.ivImage);
		tvDesc = (TextView) findViewById(R.id.tvDesc);
		tvProductCondition = (TextView) findViewById(R.id.tvProductCondition);
		tvProductPrice = (TextView) findViewById(R.id.tvProductPrice);
		llAddToCart = (LinearLayout) findViewById(R.id.llAddToCart);
		
		Product= MyApplication.Product;
		
		
		tvProductCondition.setText(Product.getString("Condition"));
		tvProductPrice.setText(Product.getString("Price"));
		tvDesc.setText(Product.getInt("Description"));
		
		ParseFile applicantResume = (ParseFile)Product.get("Image");
		applicantResume.getDataInBackground(new GetDataCallback() {
		  public void done(byte[] data, ParseException e) {
		    if (e == null) {
		      
		    	
				bmp = BitmapFactory.decodeByteArray(data, 0, data.length);
				Bitmap ss = bmp;
				
				
				
		    } else {
		      // something went wrong
		    }
		  }
		});
		
		
 		ivImage.setImageBitmap(bmp);
		

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

	
	public void btnBuy(View v)
    {
        ParseObject object = new ParseObject("Cart");
        object.put("prod_id", MyApplication.Product.getObjectId());
        object.increment("quantity");
        object.put("userID", ParseUser.getCurrentUser().getObjectId());
        object.saveInBackground();
        
        
    } 
	
}
