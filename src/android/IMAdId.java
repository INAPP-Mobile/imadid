package kr.imapp.hybrid.imadid;

import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import com.google.android.gms.common.GooglePlayServicesAvailabilityException;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.text.TextUtils;
import android.util.Log;


// Do not call this function from the main thread. Otherwise, 
// an IllegalStateException will be thrown.
public Info getIdThread() {
  Info adInfo = null;
  try {
    adInfo = AdvertisingIdClient.getAdvertisingIdInfo(mContext);

  } catch (IOException e) {
    // Unrecoverable error connecting to Google Play services (e.g.,
    // the old version of the service doesn't support getting AdvertisingId).
 
  } catch (GooglePlayServicesAvailabilityException e) {
    // Encountered a recoverable error connecting to Google Play services. 

  } catch (GooglePlayServicesNotAvailableException e) {
    // Google Play services is not available entirely.
  }
  final String id = adInfo.getId();
  final boolean isLAT = adInfo.isLimitAdTrackingEnabled();
  if ( isLAT ) {
    adInfo = null;
  }
  return adInfo;
}

public class IMAdId extends CordovaPlugin {
  public static final String TAG = "IMAdId";
  private static final boolean DEBUG = false;

  public static final String ACTION_GET = "get";

  @Override
  public boolean execute(String action, JSONArray args,
      final CallbackContext callbackContext) throws JSONException {
    if (DEBUG) {
      Log.d(TAG, "execute | action=" + action);
    }
    try {
      if (ACTION_GET.equals(action)) {
      Info info = getIdThread();
      if ( info != null )
        callbackContext.success(info.getId());
      else
        callbackContext.success();

      return true;

    } catch (Exception e) {
      System.err.println("Exception: " + e.getMessage());
      callbackContext.error(e.getMessage());
      return false;
    }
  }
  
}
