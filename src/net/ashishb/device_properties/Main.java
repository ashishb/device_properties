package net.ashishb.device_properties;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.TextView;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;

import java.io.IOException;

public class Main extends Activity
{

  public static final String TAG = "DeviceProperties";
  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
    TextView mainTextView = (TextView) findViewById(R.id.mainTextView);
    fillText(mainTextView);
  }

  private void fillText(TextView textView) {
    Context context = getApplicationContext();
    TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
    String text = "";
    final String SEPARATOR = "\n\n";
    // Add IMEI
    text += "IMEI: " + telephonyManager.getDeviceId() + SEPARATOR;
    // Add IMSI
    text += "IMSI: " + telephonyManager.getSubscriberId() + SEPARATOR;
    // Add MCC
    text += "MCC: " + context.getResources().getConfiguration().mcc + SEPARATOR;
    // Add MNC
    text += "MNC: " + context.getResources().getConfiguration().mnc + SEPARATOR;
    // Add Phone number
    text += "Phone number: " + telephonyManager.getLine1Number() + SEPARATOR;
    // Add carrier name
    text += "Carrier: " + telephonyManager.getNetworkOperatorName() + SEPARATOR;
    // Add Android ID
    text += "Android ID: " + Secure.getString(context.getContentResolver(), Secure.ANDROID_ID) + SEPARATOR;
    // Add IMEI SV
    text += "IMEI SV: " + telephonyManager.getDeviceSoftwareVersion() + SEPARATOR;
    // Add internet reachable
    text += "Google.com reachable?: " + (canReachUrl("http://www.google.com") ? "Yes" : "No") + SEPARATOR;
    text += "TODO(ashishb): Add refresh button";
    textView.setText(text);
  }

  private boolean canReachUrl(String url) {
    HttpClient httpclient = new DefaultHttpClient();
    HttpResponse response = null;
    try {
      response = httpclient.execute(new HttpGet(url));
    } catch (IOException e) {
      Log.e(TAG, "Error creating HttpGet object.");
      e.printStackTrace();
      return false;
    }
    StatusLine statusLine = response.getStatusLine();
    if(statusLine.getStatusCode() == HttpStatus.SC_OK){
      return true;
    } else {
      Log.e(TAG, "http status code is " + statusLine.getStatusCode());
      return false;
    }
  }
}
