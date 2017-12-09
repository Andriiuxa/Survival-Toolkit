package andriuxa.newproject;


import android.graphics.Color;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import andriuxa.newproject.data.Food;

/**
 * A simple {@link Fragment} subclass.
 */
public class FoodItemFragment extends ListFragment implements AdapterView.OnItemClickListener {


    public FoodItemFragment() {
        // Required empty public constructor
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setListAdapter(new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, android.R.id.text1, Food.foodNames));
        if (getActivity() instanceof FoodListFragmentActivity) {
            getListView().setOnItemClickListener(this);
        }

        if (getActivity() instanceof FoodMultiPanelActivity) {
            getListView().setOnItemClickListener((FoodMultiPanelActivity) getActivity());
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getActivity(), Food.foodNames[position], Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStart() {
        super.onStart();
        getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        getListView().setBackgroundColor(Color.parseColor("#0E6655"));
        getListView().setSelector(android.R.color.darker_gray);
    }

}
