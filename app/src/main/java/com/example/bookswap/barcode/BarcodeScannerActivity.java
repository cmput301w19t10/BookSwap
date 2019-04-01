package com.example.bookswap.barcode;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.WindowManager;

import com.example.bookswap.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcode;
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcodeDetector;
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcodeDetectorOptions;

import java.io.IOException;
import java.util.List;

/**
 * Activity for the scanning of barcodes.
 * returns a string of the isbn upon recognition of a barcode
 */
public class BarcodeScannerActivity extends AppCompatActivity {

    private static int REQUEST_CAMERA = 9001;

    String TAG = "BARCODE_DEBUG";

    GraphicOverlay barcodeOverlay;
    CameraSourcePreview camSourcePreview;
    OverlayView overlayView;
    BarcodeScanningProcessor barcodeScanningProcessor;
    private CameraSource myCameraSource = null;


    /**
     * on create, instantiates some needed variables and classes
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barcode_scanner);

        barcodeOverlay = findViewById(R.id.barcodeOverlay);
        camSourcePreview = findViewById(R.id.camPreview);
        overlayView = findViewById(R.id.overlayView);

        if (getWindow() != null) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        } else {
            Log.e(TAG, "scanner could not fullscreen");
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 2);
        } else {
            if (camSourcePreview != null) {
                createCameraSource();
            }
        }
    }

    /**
     * creation of a camera source for the barcode scanner
     */
    private void createCameraSource() {

        Log.d(TAG, "Camera source created");

        //initialize detector
        FirebaseVisionBarcodeDetectorOptions options =
                new FirebaseVisionBarcodeDetectorOptions.Builder()
                .setBarcodeFormats(FirebaseVisionBarcode.FORMAT_EAN_13)
                .build();

        FirebaseVisionBarcodeDetector detector = FirebaseVision.getInstance()
                .getVisionBarcodeDetector(options);

        // Pray it connects to camera

        myCameraSource = new CameraSource(this,barcodeOverlay);
        myCameraSource.setFacing(CameraSource.CAMERA_FACING_BACK);

        barcodeScanningProcessor = new BarcodeScanningProcessor(detector);
        barcodeScanningProcessor.setBarcodeResultListener(getBarcodeResultListener());

        myCameraSource.setMachineLearningFrameProcessor(barcodeScanningProcessor);
        startCameraSource();
    }

    /**
     * start running the camera source for use
     */
    private void startCameraSource(){
        int code = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(getApplicationContext());

        if (code != ConnectionResult.SUCCESS){
            Dialog dlg = GoogleApiAvailability.getInstance().getErrorDialog(this, code, REQUEST_CAMERA);
            dlg.show();
        }

        if (myCameraSource != null && camSourcePreview != null && barcodeOverlay != null) {
            try {
                camSourcePreview.start(myCameraSource, barcodeOverlay);
            } catch (IOException e) {
                Log.d(TAG, "cam source fail to start", e);
                myCameraSource.release();
                myCameraSource = null;
            }
        } else {
            Log.d(TAG, "startCam: not started");
            if (myCameraSource == null ){
                Log.d(TAG, "source");
            }
        }
    }

    /**
     * asking for permission
     * @param requestCode request code for camera permission
     * @param permissions permissions granted via android
     * @param grantResults results granted via android
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        camSourcePreview.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    /**
     * start camera
     */
    @Override
    protected void onResume() {
        super.onResume();
        startCameraSource();
    }

    /**
     * stop camera
     */
    @Override
    protected void onPause() {
        super.onPause();
        if (camSourcePreview != null){
            camSourcePreview.stop();
        }
    }

    /**
     * release camera so other activities can use.
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (myCameraSource != null) {
            myCameraSource.release();
        }
    }


    /**
     * listener for barcode result. Finishes activity and passes intent with ISBN back
     * @return
     */
    public BarcodeScanningProcessor.BarcodeResultListener getBarcodeResultListener(){
        return new BarcodeScanningProcessor.BarcodeResultListener() {
            @Override
            public void onSuccess(@Nullable Bitmap originalCameraImage, @NonNull List<FirebaseVisionBarcode> barcodes, @NonNull FrameMetadata frameMetadata, @NonNull GraphicOverlay graphicOverlay) {
                for (FirebaseVisionBarcode barCode : barcodes){
                    if (barCode.getValueType() == FirebaseVisionBarcode.TYPE_ISBN) {
                        Intent intent = new Intent();
                        intent.putExtra("ISBN", barCode.getRawValue());
                        setResult(RESULT_OK, intent);
                        finish();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Exception e) {

            }
        };
    }
}
