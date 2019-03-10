package iics.casino.dominicmichael.healthy_foodie_mobile.app;

import android.app.Application;
import com.android.volley.Request;
import com.android.volley.RequestQueue; //volley.jar lai libs folder vitra paste garera, import as library gareko
import com.android.volley.toolbox.Volley;

public class AppController extends Application {
    public static final String TAG = AppController.class.getSimpleName();

    private RequestQueue mRequestQueue;

    private static AppController mInstance;

    public static String baseUrl= "http://192.168.1.4/codes/healthy_foodie_web/fourV2/student/ConnDB/"; //http://192.168.1.7/codes/healthy_foodie_web/fourV2/student/ConnDB/
    //https://healthyfoodieweb.000webhostapp.com/student/ConnDB/
    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    public static synchronized AppController getmInstance(){
        return mInstance;
    }

    public RequestQueue getRequestQueue(){
        if(mRequestQueue == null){
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req){
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag){
        if(mRequestQueue != null){
            mRequestQueue.cancelAll(tag);
        }
    }

}