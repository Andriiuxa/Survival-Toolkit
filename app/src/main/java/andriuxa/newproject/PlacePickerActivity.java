package andriuxa.newproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;

public class PlacePickerActivity extends AppCompatActivity {

    int PLACE_PICKER_REQUEST = 1;
    TextView tvPlace;
    private SharedPreferences sharedPreferences;
    public static final String PHONE_KEY = "PHONE_KEY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_picker);
        tvPlace = (TextView)findViewById(R.id.tvPlace);
        sharedPreferences = getSharedPreferences("MySharedPreMain", Context.MODE_PRIVATE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if (sharedPreferences.contains(PHONE_KEY)) {
            tvPlace.setText(sharedPreferences.getString(PHONE_KEY, ""));
        }

    }

    public void goPlacePicker(View view){
        //This is the place to call place picker function

        com.google.android.gms.location.places.ui.PlacePicker.IntentBuilder builder = new com.google.android.gms.location.places.ui.PlacePicker.IntentBuilder();

        try {
            startActivityForResult(builder.build(PlacePickerActivity.this),PLACE_PICKER_REQUEST);
        }
        catch (GooglePlayServicesRepairableException e){
            e.printStackTrace();
        }
        catch (GooglePlayServicesNotAvailableException e){
            e.printStackTrace();
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == PLACE_PICKER_REQUEST){
            if(resultCode == RESULT_OK){
                Place place = com.google.android.gms.location.places.ui.PlacePicker.getPlace(PlacePickerActivity.this, data);
                tvPlace.setText(place.getPhoneNumber());
                save(tvPlace);
            }
        }
    }

    public void save(View v){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PHONE_KEY, tvPlace.getText().toString());
        editor.commit();
        Toast.makeText(v.getContext(),"data persisted",Toast.LENGTH_SHORT).show();
    }
}
