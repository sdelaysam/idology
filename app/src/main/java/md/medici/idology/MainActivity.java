package md.medici.idology;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.idology.cameralibrary.CameraCallBack2;
import com.idology.cameralibrary.CameraLib;
import com.idology.cameralibrary.IActivationKeyProvider;
import com.idology.cameralibrary.IPublicKeyProvider;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Created on 4/1/19.
 *
 * @author sdelaysam
 */
public class MainActivity extends AppCompatActivity implements IActivationKeyProvider, IPublicKeyProvider {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launch();
            }
        });
    }

    @Override
    public InputStream getActivationKeyInputStream() {
        return getResources().openRawResource(R.raw.ac);
    }

    @Override
    public InputStream getPublicKeyInputStream() {
        return getResources().openRawResource(R.raw.idology_pub);
    }

    private void launch() {
        CameraLib cameraLib = new CameraLib();
        CameraCallBack2 cameraCallBack2 = new CameraCallBack2() {
            @Override
            public void captureResults(HashMap<String, String> hashMap) {
                for (Map.Entry<String, String> e : hashMap.entrySet()) {
                    Log.d("WTF", e.getKey() + ": " + e.getValue());
                }
            }
        };
        cameraLib.showCameraUtility(this, cameraCallBack2, this, this);
    }
}
