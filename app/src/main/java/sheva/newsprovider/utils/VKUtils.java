package sheva.newsprovider.utils;

import android.content.Context;
import android.util.Log;

import com.vk.sdk.util.VKUtil;

/**
 * Created by shevc on 26.04.2017.
 */

public class VKUtils {

    public static void getFingerprint(Context context) {
        String[] fingerprints =
                VKUtil.getCertificateFingerprint(context, context.getPackageName());
        for (String s : fingerprints) {
            Log.d("MY", s);
        }
    }
}
