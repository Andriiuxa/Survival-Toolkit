package andriuxa.newproject;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import andriuxa.newproject.data.Food;


/**
 * A simple {@link Fragment} subclass.
 */
public class FoodDetailsFragment extends Fragment {


    public static final String POSITION = "position";

    public FoodDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onStart() {
        super.onStart();
        Bundle args = getArguments();
        if (args != null) {
            int position = args.getInt(POSITION);
            TextView description = (TextView) getActivity().findViewById(R.id.description);
            description.setText(Food.foodDetails[position]);
            ImageView imageView = (ImageView) getActivity().findViewById(R.id.imageView);
            imageView.setImageResource(Food.foodPhotos[position]);
        } else {
            TextView description = (TextView) getActivity().findViewById(R.id.description);
            description.setText("Click on the names on the left to see details");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details, container, false);
    }

}

