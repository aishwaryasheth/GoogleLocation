package com.example.aishwaryasheth.googlelocation;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.lang.reflect.Array;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    Button Dial;
    String a;
    GoogleMap mgoogleMap;
    Marker m;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Dial = (Button) findViewById(R.id.button1);
        if (GoogleServicesAvail())
        {
            Toast.makeText(this,"Google play services is available",Toast.LENGTH_LONG).show();
            setContentView(R.layout.activity_main);
           initMap();


        }
        else{
            //no gmaps fragment
        }

    }

    private void initMap() {



        //mp.getMapAsync(this );
    }

    public boolean GoogleServicesAvail()
    {
        GoogleApiAvailability api = GoogleApiAvailability.getInstance();
        int isavail = api.isGooglePlayServicesAvailable(this);
        if(isavail== ConnectionResult.SUCCESS) {
            return true;
        }
        else if(api.isUserResolvableError(isavail))
        {
            Dialog dialog = api.getErrorDialog(this,isavail,0);
            dialog.show();

        } else{
            Toast.makeText(this,"Cant connect to play services",Toast.LENGTH_LONG).show();
        }


        return false;
    }


    public void call(View view)
    {
        a ="+918149446190";
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+a));

        startActivity(intent);

    }

    public void maps(View view)
    {
        Intent intent = null,chooser=null;
        Double myLatitude = 18.490189;
        Double myLongitude =73.852103;
        String labellocation = "Muktangan English School and Jr College";
        if (view.getId()==R.id.button2)
        {
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:<" + myLatitude  + ">,<" + myLongitude + ">?q=<" + myLatitude  + ">,<" + myLongitude + ">"));
           /* mgoogleMap.addMarker(new MarkerOptions()
                    .position(new LatLng(18.490047, 73.852242))
                    .title("Hello World"));
*/


            chooser=intent.createChooser(intent,"Launch Maps");
            startActivity(chooser);
            //jkjkjkjkjk
        }

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mgoogleMap =googleMap;
        GoToLocationzoom(18.505700, 73.832133,15);
    }

    private void GoToLocation(double lat, double lon) {
        LatLng ll = new LatLng(lat,lon);
        CameraUpdate update = CameraUpdateFactory.newLatLng(ll);
        mgoogleMap.moveCamera(update);

    }
    private void GoToLocationzoom(double lat, double lon,int zoom) {
        LatLng ll = new LatLng(lat,lon);
        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(ll,zoom);
        mgoogleMap.moveCamera(update);

    }
}



