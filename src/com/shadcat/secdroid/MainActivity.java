package com.shadcat.secdroid;
/*Copyright (C) 2013  x942 AKA Kyle Davidson

*This program is free software; you can redistribute it and/or
*modify it under the terms of the GNU General Public License
*as published by the Free Software Foundation; either version 2
*of the License, or (at your option) any later version.
*
*This program is distributed in the hope that it will be useful,
*but WITHOUT ANY WARRANTY; without even the implied warranty of
*MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
*GNU General Public License for more details.
*
*You should have received a copy of the GNU General Public License
*along with this program; if not, write to the Free Software
*Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.shadcat.secdroid.R;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		final Button button1 = (Button)  findViewById(R.id.button1);
		
		button1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// When button is pressed run shell script
				// This part copies the "hardened.sh from assests to the ineternal memory of the device
				// Can this be done purely withing the app? No need to copy out the script?
				File destFile = new File(getFilesDir(),"hardened.sh");
				try {

				        InputStream in = getAssets().open("hardened.sh");
				        OutputStream out = new FileOutputStream(destFile);

				        byte[] buf = new byte[1024];
				        int len;
				        while ((len = in.read(buf)) > 0) {
				            out.write(buf, 0, len);
				        }
				        in.close();
				        out.close();
				        System.out.println("File copied.");
				    } catch (FileNotFoundException ex) {
				        System.out
				                .println(ex.getMessage() + " in the specified directory.");
				        System.exit(0);
				    } catch (IOException e) {
				        System.out.println(e.getMessage());
				    }
				//Here we change the permissions of the app to execute"
				//Using "Runtime.getRuntime()" to execute variable "permisions"
			String permissions[]={"su","-c", "chmod 0700 /data/data/com.shadcat.secdroid/files/hardened.sh"}; //Setting variable 'permissions' to make our script executable
			try {
				Process process = Runtime.getRuntime().exec(permissions); //Executing variable "permissions"
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//Now we run our script via "Runtime.getRuntime().exec"
			//No longer need seperate shell class
			String execscript[]={"su","-c", "/data/data/com.shadcat.secdroid/files/hardened.sh"}; //Setting variable 'execscript' to run our script
			try {
				Process process = Runtime.getRuntime().exec(execscript); //Executing variable "execscript"
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//Here we display text showing we are secure now!
			String text = "You Device is now Secure! Thanks for using SecDroid!";
			setNewTextInTextView(text);
			}
		});
		
		// XDA Thread button 
		// Having the XDA Thread button open XDA Thread
		
		Button button2 = (Button) findViewById(R.id.button2);
		button2.setOnClickListener(new View.OnClickListener() {
	        public void onClick(View v) {
	            Intent myWebLink = new Intent(android.content.Intent.ACTION_VIEW);
	            myWebLink.setData(Uri.parse("http://forum.xda-developers.com/showthread.php?t=2086276"));
	                startActivity(myWebLink);
	         }
		
		});
		
		// WebSite button 
				// Having the WebSite button the website
				
				Button button3 = (Button) findViewById(R.id.button3);
				button3.setOnClickListener(new View.OnClickListener() {
			        public void onClick(View v) {
			        	File destFile = new File(getFilesDir(),"enableadb.sh");
						try {

						        InputStream in = getAssets().open("enableadb.sh");
						        OutputStream out = new FileOutputStream(destFile);

						        byte[] buf = new byte[1024];
						        int len;
						        while ((len = in.read(buf)) > 0) {
						            out.write(buf, 0, len);
						        }
						        in.close();
						        out.close();
						        System.out.println("File copied.");
						    } catch (FileNotFoundException ex) {
						        System.out
						                .println(ex.getMessage() + " in the specified directory.");
						        System.exit(0);
						    } catch (IOException e) {
						        System.out.println(e.getMessage());
						    }
						//Here we change the permissions of the app to execute"
						//Using "Runtime.getRuntime()" to execute variable "permisions"
					String adbperm[]={"su","-c", "chmod 0700 /data/data/com.shadcat.secdroid/files/enableadb.sh"}; //Setting variable 'permissions' to make our script executable
					try {
						Process process = Runtime.getRuntime().exec(adbperm); //Executing variable "permissions"
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//Now we run our script via "Runtime.getRuntime().exec"
					//No longer need seperate shell class
					String execadb[]={"su","-c", "/data/data/com.shadcat.secdroid/files/enableadb.sh"}; //Setting variable 'execscript' to run our script
					try {
						Process process = Runtime.getRuntime().exec(execadb); //Executing variable "execscript"
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					//Here we display text showing we are secure now!
					String text = "ADBD is ENABLED!";
					setNewTextInTextView(text);
					}
			           
				
				});
	}
	//This section let's us see the output of the script (Verbose)
	// We can use this to see if it executed properly and what did or did not work 
	// Thanks again to Adam Outler and XDA-TV for their demo on showing how to do this.
	public void setNewTextInTextView(String text){
		
		TextView tv= new TextView(this); 
		tv.setText(text);
		setContentView(tv);
	}

}
