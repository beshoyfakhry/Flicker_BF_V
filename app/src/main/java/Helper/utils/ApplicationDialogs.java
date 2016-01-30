package Helper.utils;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;


/**
 * @author Beshoy Fakhry
 * @email beshoyfakhry@gmail.com
 * @contact 01221231268
 */
public class ApplicationDialogs {
	public static final String NO_CONNECTION = "NO_CONNECTION";

	/**
	 * check if there is Internet connection or not
	 *
	 * @param activity
	 * @return
	 */
	public static boolean isConnectionOn(Activity activity) {
		ConnectivityManager connectivityManager = (ConnectivityManager) activity
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetworkInfo = connectivityManager
				.getActiveNetworkInfo();
		return activeNetworkInfo != null;
	}
	public static void  showToast(Activity activity)
	{
		String toast="Kindly connect first to the internet and then try again";
		Toast.makeText(activity,toast,Toast.LENGTH_LONG).show();
		activity.finish();
	}
}
