package oemersari.de.permissionhelper;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;


public class PermissionManager extends Activity {
    private static final int REQUEST_CODE = 54626;//random number;
    private static String manifest;
    private static PermissionManager permissionManager = new PermissionManager();
    private Activity context;
    private OnRequestPermissionResultListener onRequestPermissionResultListener;
    private OnCheckPermissionListenerListener onCheckPermissionListenerListener;

    public static PermissionManager initilize(Activity context, String _manifest) {
        manifest = _manifest;
        permissionManager.setContext(context);
        return permissionManager;
    }

    private void setContext(Activity context) {
        this.context = context;
    }

    public String checkPermissionStatus(OnCheckPermissionListenerListener onCheckPermissionListenerListener) {
        this.onCheckPermissionListenerListener = onCheckPermissionListenerListener;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int permissionResult = this.context.checkSelfPermission(manifest);
            if (permissionResult == PackageManager.PERMISSION_GRANTED) {
                this.onCheckPermissionListenerListener.onGranted();
                return PermissionMessages.GRANTED;
            } else if (permissionResult == PackageManager.PERMISSION_DENIED) {
                if (context.shouldShowRequestPermissionRationale(manifest)) {
                    this.onCheckPermissionListenerListener.onDenied();
                    return PermissionMessages.DENIED;
                } else {
                    this.onCheckPermissionListenerListener.onDontAskAgain();
                    return PermissionMessages.DO_NOT_ASK_AGAIN;
                }
            } else {
                return null;
            }
        }
        return null;
    }

    public String checkPermissionStatus() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int permissionResult = this.context.checkSelfPermission(manifest);
            if (permissionResult == PackageManager.PERMISSION_GRANTED) {
                return PermissionMessages.GRANTED;
            } else if (permissionResult == PackageManager.PERMISSION_DENIED) {
                if (context.shouldShowRequestPermissionRationale(manifest)) {
                    return PermissionMessages.DENIED;
                } else {
                    return PermissionMessages.DO_NOT_ASK_AGAIN;
                }
            } else {
                return null;
            }
        }
        return null;
    }

    public void requestPermission(OnRequestPermissionResultListener onRequestPermissionResultListener) {
        this.onRequestPermissionResultListener = onRequestPermissionResultListener;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            this.context.requestPermissions(new String[]{manifest}, REQUEST_CODE);
        } else {
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                this.onRequestPermissionResultListener.onGranted();
            } else if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_DENIED) {
                if (context.shouldShowRequestPermissionRationale(manifest)) {
                    this.onRequestPermissionResultListener.onDenied();
                } else {
                    this.onRequestPermissionResultListener.onDontAskAgain();
                }
            }
        } else {
        }
    }
}

