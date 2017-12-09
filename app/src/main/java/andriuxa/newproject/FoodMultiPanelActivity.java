package andriuxa.newproject;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;


public class FoodMultiPanelActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_panel);
        if (savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().add(R.id.fragmentLeft, new
                    FoodItemFragment()).commit();
            getSupportFragmentManager().beginTransaction().add(R.id.fragmentRight, new
                    FoodDetailsFragment()).commit();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        FoodDetailsFragment foodDetailsFragmentNew = new FoodDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(FoodDetailsFragment.POSITION, position);
        foodDetailsFragmentNew.setArguments(bundle);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentRight, foodDetailsFragmentNew);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
        // If article frag is available, we're in two-pane layout...
        // Call a method in the ArticleFragment to update its content
        // ((TextView) findViewById(R.id.description)).setText(Candidates.candidateDetails[position]);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, FullscreenActivity.class));
    }
}