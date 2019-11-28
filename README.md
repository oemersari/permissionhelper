# Permission Helper

>maven { url "https://jitpack.io" }

>implementation 'com.github.oemersari:permissionhelper:master'

# Request Permission
```java 
        String manifest = Manifest.permission.WRITE_EXTERNAL_STORAGE;
        PermissionManager manager = PermissionManager.initilize(this, manifest);
        manager.requestPermission(new OnCheckPermissionListenerListener() {
            @Override
            public void onDontAskAgain() {
                Log.i("MA", "DontAskAgain");
            }

            @Override
            public void onGranted() {
                Log.i("MA", "Granted");
            }

            @Override
            public void onDenied() {
                Log.i("MA", "Denied");
            }
        });
```
# Check Permission Status
```java
        String manifest = Manifest.permission.WRITE_EXTERNAL_STORAGE;
        PermissionManager manager = PermissionManager.initilize(this, manifest);
        manager.checkPermissionStatus(new OnCheckPermissionListenerListener() {
            @Override
            public void onDontAskAgain() {
                Log.i("MA", "DontaskAgain");

            }

            @Override
            public void onGranted() {
                Log.i("MA", "Granted");
            }

            @Override
            public void onDenied() {
                Log.i("MA", "Denied");
            }
        });
```
``` java
@Override
public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

  super.onRequestPermissionsResult(requestCode, permissions, grantResults);
  
  permissionManager.onRequestPermissionsResult(requestCode,permissions,grantResults);
  
  }
