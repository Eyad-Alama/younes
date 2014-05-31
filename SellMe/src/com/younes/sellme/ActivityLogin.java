package com.younes.sellme;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class ActivityLogin extends Activity {
	
	
	private ProgressDialog mPD;
	
	LinearLayout llSignUp ;
	LinearLayout llSignIn ;
	TextView tvHaveAccount;
	TextView etLoginUsername;
	TextView etLoginPassword;
	TextView etSignUpUsername;
	TextView etSignUpPassword;
	TextView txtHaveAccount2;
	Button btnLogin;
	Button btnSignUp;
	
	private Context mContext;
	

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        
        mContext = this;
        
        
        
        llSignUp = (LinearLayout) findViewById(R.id.llSignUp);
        llSignIn = (LinearLayout) findViewById(R.id.llSignIn);
        tvHaveAccount = (TextView) findViewById(R.id.txtHasAccount);
        etLoginUsername = (TextView) findViewById(R.id.etLoginUsername);
        etLoginPassword = (TextView) findViewById(R.id.etLoginPassword);
        etSignUpUsername = (TextView) findViewById(R.id.etSignUpUsername);
        etSignUpPassword = (TextView) findViewById(R.id.etSignUpPassword);
        txtHaveAccount2 = (TextView) findViewById(R.id.txtHaveAccount2);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnSignUp = (Button) findViewById(R.id.btnSignUp);
        
        
        tvHaveAccount.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				if(llSignIn.getVisibility() == View.GONE)
				{
					llSignIn.setVisibility(View.VISIBLE);
					llSignUp.setVisibility(View.GONE);
					
				}
			
				
			}
		});
        
        txtHaveAccount2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				llSignIn.setVisibility(View.GONE);
				llSignUp.setVisibility(View.VISIBLE);
				
			}
		});
        
        btnSignUp.setOnClickListener(new OnClickListener() {
			
			

			@Override
			public void onClick(View v) {
				
				String UserName = etSignUpUsername.getText().toString();
				String Password = etSignUpPassword.getText().toString();
				
				if(!UserName.isEmpty() && !Password.isEmpty() )
				{
					showWait("Creating Account");
					//SignUp the user
					ParseUser user = new ParseUser();
					user.setUsername(UserName);
					user.setPassword(Password);
					 
					// other fields can be set just like with ParseObject
					 
					user.signUpInBackground(new SignUpCallback() {
					  public void done(ParseException e) {
						  hideWait();
						  if (e == null) {
					    	//Account created
					    	
					    	Toast.makeText(mContext, "Account created successfully! ", Toast.LENGTH_LONG).show();
					    	Intent intent = new Intent(mContext,ActivityCategories.class);
					    	intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
					    	mContext.startActivity(intent);
					    	
					    } else {
					    	Toast.makeText(mContext, "There was an issue when creating the account ", Toast.LENGTH_LONG).show();
					    }
					  }
					});
					
				}
				else
				{
					Toast.makeText(mContext, "Please fill up all the fields", Toast.LENGTH_LONG).show();
				}
				
			}
		});
        
        
        btnLogin.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				String Username = etLoginUsername.getText().toString();
				String Password = etLoginPassword.getText().toString();
				
				if(!Username.isEmpty() && !Password.isEmpty())
				{
					showWait("Signing In");
					ParseUser.logInInBackground(Username,Password, new LogInCallback() {
						  public void done(ParseUser user, ParseException e) {
							  hideWait();
							  if (user != null) {
						    	Toast.makeText(mContext, "Success! " , Toast.LENGTH_LONG).show();
						    	Intent intent = new Intent(mContext,ActivityCategories.class);
						    	intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
						    	mContext.startActivity(intent);
						    	
						    	
						    } else {
						      Toast.makeText(mContext, "Wrong Username/Password combination " , Toast.LENGTH_LONG).show();
						    }
						  }
						});
				}
				
			}
		});
        
       
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.login, menu);
        
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

   
}
