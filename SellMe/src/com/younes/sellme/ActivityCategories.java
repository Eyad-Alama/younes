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

import com.parse.ParseException;
import com.parse.ParseObject;

public class ActivityCategories extends ListActivity {

	private Context mContext;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_categories);
		mContext = this;
		// Create the database
		ListAdapterCategories adapter;
		try {
			adapter = new ListAdapterCategories(this, "Categories");
			adapter.setTextKey("Name");
			adapter.setImageKey("Image");

			adapter.setPlaceholder(getResources().getDrawable(
					R.drawable.ic_launcher));
			// Assign adapter to List
			setListAdapter(adapter);

		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {

		super.onListItemClick(l, v, position, id);

		ParseObject object = (ParseObject) l.getItemAtPosition(position);

		Intent intent = new Intent(this, ActivityProducts.class);

		intent.putExtra("cat_id", object.getObjectId().toString());
		startActivity(intent);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		MenuInflater inflater = getMenuInflater();

		inflater.inflate(R.menu.categories, menu);

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
