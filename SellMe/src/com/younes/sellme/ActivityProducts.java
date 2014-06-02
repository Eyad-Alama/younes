package com.younes.sellme;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.parse.Parse;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;

public class ActivityProducts extends ListActivity  {
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        
        final String cat_id = getIntent().getStringExtra("cat_id");
        
        //Create the database
        ListAdapterProducts adapter =
        		  new ListAdapterProducts(this, new ParseQueryAdapter.QueryFactory<ParseObject>() {
        		    public ParseQuery<ParseObject> create() {
        		      // Here we can configure a ParseQuery to our heart's desire.
        		      ParseQuery<ParseObject> query = new ParseQuery("Products");
        		      query.whereEqualTo("cat_id", cat_id);
        		      return query;
        		    }
        		  });
        
        adapter.setTextKey("Name");
        adapter.setImageKey("Image");
        
        adapter.setPlaceholder(getResources().getDrawable(R.drawable.ic_launcher));
        // Assign adapter to List
        setListAdapter(adapter); 
   }

    
  @Override
  protected void onListItemClick(ListView l, View v, int position, long id) {
        
        super.onListItemClick(l, v, position, id);
        
        ParseObject object = (ParseObject) l.getItemAtPosition(position);
        MyApplication.Product = object ;
        
		Intent intent = new Intent(this, ActivityProductDetails.class);
		startActivity(intent);
           
              
           
  }
}
