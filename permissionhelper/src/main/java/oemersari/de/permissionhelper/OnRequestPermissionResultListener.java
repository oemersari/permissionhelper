package oemersari.de.permissionhelper;

public interface OnRequestPermissionResultListener {
    void onGranted();
    void onDenied();
    void onDontAskAgain();
}
