package net.ashishb.device_properties;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.widget.TextView;

public class Main extends Activity
{
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
      text += "MNC: " + context.getResources().getConfiguration().mcc + SEPARATOR;
      // Add Phone number
      text += "Phone number: " + telephonyManager.getLine1Number() + SEPARATOR;
      // Add carrier name
      text += "Carrier: " + telephonyManager.getNetworkOperatorName() + SEPARATOR;
      // Add Android ID
      text += "Android ID: " + Secure.getString(context.getContentResolver(), Secure.ANDROID_ID) + SEPARATOR;
      // Add IMEI SV
      text += "IMEI SV: " + telephonyManager.getDeviceSoftwareVersion() + SEPARATOR;
      textView.setText(text);
    }
}
