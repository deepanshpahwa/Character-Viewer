package com.example.characterviewer;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.characterviewer.Bean.RelatedTopic;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * This class is used to load the different widgets of the detail screen/fragment and load
 * appropriate data into them.
 */
public class CharacterDetailFragment extends Fragment {

    CollapsingToolbarLayout appBarLayout;
    public static final String ARG_ID = "character_id";
    private RelatedTopic character;


    public CharacterDetailFragment() {//Mandatory empty constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ID)) {

            int position = getArguments().getInt(ARG_ID);
            List<RelatedTopic> filteredCharacterList = Utils.getFilteredCharacterList();//fetching the filtered data set
            character = filteredCharacterList.get(position);

            Activity activity = this.getActivity();
            appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);

            if (appBarLayout != null) {
                //appbar will not be displayed in tablets
                appBarLayout.setTitle(Utils.parseCharacterName(character.getText()));
            }

        }

        //The code below hides the soft keyboard
        View view = getActivity().getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.character_detail, container, false);
        TextView characterTitle = rootView.findViewById(R.id.character_detail_title);

        /**
         * if appbar is available(on phone), name is displayed there. Else it is displayed on a text box.
         */
        if (appBarLayout != null) {
            appBarLayout.setTitle(Utils.parseCharacterName(character.getText()));
            characterTitle.setVisibility(View.INVISIBLE);
        }else {
            characterTitle.setText(Utils.parseCharacterName(character.getText()));
        }

        if (character != null) {
            ((TextView) rootView.findViewById(R.id.character_detail)).setText(Utils.parseCharacterDescription(character.getText()));

            ImageView imageView = rootView.findViewById(R.id.character_detail_image);
            String url;

            /**
             * if the URL is empty, this if statement assigns placeholder image URLs instead.
             */
            if (character.getIcon().getURL().equals("")){
                if (BuildConfig.FLAVOR.equals(getString(R.string.wire_flavor))){
                    url = "https://www.whats-on-netflix.com/wp-content/uploads/2016/01/similar-to-the-wire-streaming-on-netflix.jpg";
                }else {
                    url = "https://dotesports-media.nyc3.cdn.digitaloceanspaces.com/wp-content/uploads/2019/03/15110309/SimpsonsLogo.png";
                }
            }else{
                url = character.getIcon().getURL() ;
            }
            Picasso.get().load(url).into(imageView);//Using simple one line code to load image from URL into ImageView using Picasso library


        }

        return rootView;
    }

}
