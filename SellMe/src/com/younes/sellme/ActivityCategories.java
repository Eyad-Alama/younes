package com.younes.sellme;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.parse.Parse;
import com.parse.ParseObject;
import com.parse.ParseQueryAdapter;

public class ActivityCategories extends ListActivity  {
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        
       
        
        //Create the database
        ParseQueryAdapter<ParseObject> adapter = new ParseQueryAdapter<ParseObject>(this, "Categories");
        adapter.setTextKey("Name");
        adapter.setImageKey("Image");
        
        // Assign adapter to List
        setListAdapter(adapter); 
   }

    
  @Override
  protected void onListItemClick(ListView l, View v, int position, long id) {
        
        super.onListItemClick(l, v, position, id);
        
        ParseObject object = (ParseObject) l.getItemAtPosition(position);
        
		Intent intent = new Intent(this, ActivityProducts.class);
		intent.putExtra("Category",object.getObjectId());
		startActivity(intent);
           
              
           
  }
}
