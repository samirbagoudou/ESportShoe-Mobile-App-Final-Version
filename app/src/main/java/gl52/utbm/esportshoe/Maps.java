package gl52.utbm.esportshoe;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.StringTokenizer;

import static gl52.utbm.esportshoe.R.id.map;

public class Maps extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    ArrayList<String> sensor2Values = new ArrayList<>();
    DBHelper esportshoeDB;
    String longi = "47.641647";
    String lati = "6.844289";
    String longLat[] = new String[2];
    LatLng place = new LatLng(Float.parseFloat(longi), Float.parseFloat(lati));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(map);
        mapFragment.getMapAsync(this);
        esportshoeDB = new DBHelper(this);
        sensor2Values = esportshoeDB.getAllSensor2Values();
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     * I am not tiping anythin
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMinZoomPreference(18.8f);
        mMap.setMaxZoomPreference(18.8f);
        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);

        if(sensor2Values.size()>0){
            for(int i=0; i<sensor2Values.size(); i++){

                StringTokenizer st = new StringTokenizer(sensor2Values.get(i), ",", false);
                int initialisator = 0;
                while(st.hasMoreTokens()){
                    longLat[initialisator]=st.nextToken();
                    initialisator++;
                }
                longi = longLat[0];
                lati = longLat[1];
                place = new LatLng(Float.parseFloat(longi), Float.parseFloat(lati));
                mMap.addMarker(new MarkerOptions().position(place));
            }
        }
        else{
            place = new LatLng(Float.parseFloat(longi), Float.parseFloat(lati));
            mMap.addMarker(new MarkerOptions().position(place));
        }

        mMap.moveCamera(CameraUpdateFactory.newLatLng(place));

        // Add a marker in Sydney and move the camera
        /*LatLng utbmParking0 = new LatLng(45.576166, 5.965252);
        LatLng utbmParking1 = new LatLng(45.576130, 5.965260);
        LatLng utbmParking2 = new LatLng(45.576230, 5.965163);
        LatLng utbmParking3 = new LatLng(45.576240, 5.965170);
        LatLng utbmParking4 = new LatLng(45.576050, 5.965105);
        LatLng utbmParking5 = new LatLng(45.576245, 5.965207);
        LatLng utbmParking6 = new LatLng(45.576203, 5.965211);
        LatLng utbmParking7 = new LatLng(45.576101, 5.965302);
        LatLng utbmParking8 = new LatLng(45.576105, 5.965315);


        mMap.addMarker(new MarkerOptions().position(utbmParking0));
        mMap.addMarker(new MarkerOptions().position(utbmParking1));
        mMap.addMarker(new MarkerOptions().position(utbmParking2));
        mMap.addMarker(new MarkerOptions().position(utbmParking3));
        mMap.addMarker(new MarkerOptions().position(utbmParking4));
        mMap.addMarker(new MarkerOptions().position(utbmParking5));
        mMap.addMarker(new MarkerOptions().position(utbmParking6));
        mMap.addMarker(new MarkerOptions().position(utbmParking7));
        mMap.addMarker(new MarkerOptions().position(utbmParking8));

        mMap.moveCamera(CameraUpdateFactory.newLatLng(utbmParking0));*/
       // mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(utbmParking0, 18.5f));
    }
}
