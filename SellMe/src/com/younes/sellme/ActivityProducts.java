package com.younes.sellme;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;

public class ActivityProducts extends ListActivity {

	private Context mContext;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_categories);
		mContext = this;
		final String cat_id = getIntent().getStringExtra("cat_id");

		// Create the database
		ListAdapterProducts adapter = new ListAdapterProducts(this,
				new ParseQueryAdapter.QueryFactory<ParseObject>() {
					public ParseQuery<ParseObject> create() {
						// Here we can configure a ParseQuery to our heart's
						// desire.
						ParseQuery<ParseObject> query = new ParseQuery(
								"Products");
						query.whereEqualTo("cat_id", cat_id);
						return query;
					}
				});

		adapter.setTextKey("Name");
		adapter.setImageKey("Image");

		adapter.setPlaceholder(getResources().getDrawable(
				R.drawable.ic_launcher));
		// Assign adapter to List
		setListAdapter(adapter);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {

		super.onListItemClick(l, v, position, id);

		ParseObject object = (ParseObject) l.getItemAtPosition(position);
		MyApplication.Product = object;

		Intent intent = new Intent(this, ActivityProductDetails.class);
		startActivity(intent);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		MenuInflater inflater = getMenuInflater();

		inflater.inflate(R.menu.products, menu);

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
