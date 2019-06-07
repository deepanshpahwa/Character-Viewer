package com.example.characterviewer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.characterviewer.API.DuckDuckGoAPI;
import com.example.characterviewer.Bean.Example;
import com.example.characterviewer.Bean.RelatedTopic;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * This activity has different presentations for mobile phones and tablets. On
 * mobile phones, a list of characters is displayed, which when touched, lead
 * to a page with details (and picture) of the character. On tablets, the
 * list of characters and details are presented side-by-side using two
 * vertical panes.
 */
public class CharacterListActivity extends AppCompatActivity {

    private static final String TAG = "CharacterListActivity";
    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;
    final CharacterListActivity ref = this;
    RecyclerView recyclerView;
    ArrayList<RelatedTopic> filteredList;
    android.support.v7.widget.SearchView searchView;
    String searchQuery;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());
        toolbar.setBackgroundColor(getResources().getColor(R.color.colorAccent));

        searchView = findViewById(R.id.search_bar);
        recyclerView = findViewById(R.id.character_list);
        assert recyclerView != null;
        searchQuery="";

        final LinearLayout layout = findViewById(R.id.linear_layout);
        final TextView messageTv = findViewById(R.id.error_message);

        searchView.setQueryHint(getResources().getString(R.string.search_hint));
        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchView.setIconified(false);
                /**
                 * This allows the whole searchbar into focus for typing in of query
                 */
            }
        });
        searchView.setOnQueryTextListener(new android.support.v7.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                onQueryFromSearchBar(query, layout, messageTv);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                onQueryFromSearchBar(query, layout, messageTv);
                return true;
            }

            private void onQueryFromSearchBar(String query, LinearLayout layout, TextView textView) {
                /**
                 * This method filters the data set according to the query. If the query is empty, all the
                 * characters are shown. If no characters are found for the query, error message is displayed.
                 */
                searchQuery = query;
                if (query.isEmpty()) {
                    showRecyclerView(layout);
                    populateRecyclerView(Utils.getCharacterDataFromAPI().getRelatedTopics(), recyclerView);
                } else {
                    List<RelatedTopic> list = filter(query);
                    populateRecyclerView(list, recyclerView);
                    if (list.isEmpty()) {
                        hideRecyclerView(layout);
                        textView.setText(getResources().getString(R.string.empty_result_list_text) + " " + query + ".");
                    } else {
                        showRecyclerView(layout);
                    }
                }
            }

            private void hideRecyclerView(LinearLayout layout) {
                /**
                 * This method hides the recycler view and shows the linear
                 * layout consisting of no character message.
                 */
                layout.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.GONE);
            }

            private void showRecyclerView(LinearLayout layout) {
                /**
                 * This method hides the linear
                 * layout consisting of error message and shows the recycler view.
                 */
                layout.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
            }

        });



        if (findViewById(R.id.character_detail_container) != null) {
        /**The layout of this activity is R.layout.activity_character_list which in turn contains the R.layout.character_list .
         * If the app is launched in a phone, the character_list in layout folder is used. However, if the app is opened
         * in a tablet, or in a screen large enough, tje character_list from the layout-w900dp will be used. The latter
         * character_list contains a Frame layout (R.id.character_detail_container). This if statement just checks if
         * character_detail_container has been loaded or not. If it has, then the variable mTwoPane will be set to True.
         * This will be useful in presenting the CharacterDetailFragment next to the recyclerView.
         */
            mTwoPane = true;
        }

        setupRecyclerView((RecyclerView) recyclerView);
    }

    private List<RelatedTopic> filter(String query){
        /**
         * This method filters the list of characters. If the character name or description contains the passed
         * query, the character POJO is added into the list that will be returned.
         */
        Example completeCharactersList = Utils.getCharacterDataFromAPI();
        filteredList = new ArrayList<>();

        List<RelatedTopic> items = completeCharactersList.getRelatedTopics();

        if (!query.isEmpty()) {
            query = query.toLowerCase();
            for(RelatedTopic item: items){
                if(item.getText().toLowerCase().contains(query)){
                    filteredList.add(item);
                }
            }
        }
        return filteredList;
    }

    private void setupRecyclerView(@NonNull final RecyclerView recyclerView) {
        String query;

        if (BuildConfig.FLAVOR.equals(getString(R.string.simpsons_flavor))){
            query = "simpsons characters";
            /**
              This if statement checks to see the run time build flavor and then chooses the query appropriately
             */
        }else{
            query  =  "the wire characters";
        }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();

        DuckDuckGoAPI duckDuckGoAPI = retrofit.create(DuckDuckGoAPI.class);

        Call<Example> call = duckDuckGoAPI.getCharacters(query,"json");

        call.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                if(response.isSuccessful()){
                    /**
                     * When the API call returns and is successful, this method will be called. The fetched data
                     * is saved in static variable in the Utils class for convenience. I didn't use local storage
                     * libraries (like SQLite, Realm) as I don't necessarily want the data to persist after the current instance
                     * of the app gets destroyed. Merely saving the fetched data for the current instance in a static variable
                     * ensures no unnecessary calls to the API
                     */
                    populateRecyclerView(response.body().getRelatedTopics(), recyclerView);
                    Utils.setCharacterDataFromAPI(response.body());
                }
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                Log.d(TAG,t.getMessage());
            }
        });


    }

    @Override
    public void onBackPressed() {
        /**
         * The purpose of overriding this method is to make sure that before the app closes on pressing the back
         * button, the search bar gets cleared. This is for the users convenience as they might be pressing the
         * back button to clear out the text .
         */
        if (!searchQuery.isEmpty()){
            searchView.setQuery("",false);
            searchView.clearFocus();
        }
        else {
            super.onBackPressed();
        }

    }

    private void populateRecyclerView(List<RelatedTopic> response, @NonNull RecyclerView recyclerView) {
        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(ref, response, mTwoPane));
    }

    public static class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        private final CharacterListActivity mParentActivity;
        private final List<RelatedTopic> mValues;
        private final boolean mTwoPane;

        private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = (int) view.getTag();

                if (mTwoPane) {
                    /**
                     * This variable(mTwoPane) was set in the if check in onCreateMethod. If mTwoPane
                     * is true, the app opens up the character detail fragment right next to the
                     * recyclerView. If it is false, a new activity is launched (item_detail_activity)
                     * which solely contains the character details fragment.
                     * We also pass the position of the recycler view item that was clicked
                     * */
                    Bundle arguments = new Bundle();
                    arguments.putInt(CharacterDetailFragment.ARG_ID, position);//adding the position to arguments.

                    CharacterDetailFragment fragment = new CharacterDetailFragment();
                    fragment.setArguments(arguments);//attaching the arguments to the fragment to be launched.
                    Utils.setFilteredCharacterList(mValues);


                    /**
                     * This container (character_detail_container) is part of both the character_list layout files. This
                     * container is the layout in which the character_detail layout will be inflated.
                     */
                    mParentActivity.getSupportFragmentManager().beginTransaction()
                            .replace(R.id.character_detail_container, fragment)
                            .commit();
                } else {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, CharacterDetailActivity.class);
                    intent.putExtra(CharacterDetailFragment.ARG_ID, position);//attaching position to intent using putExtra.
                    Utils.setFilteredCharacterList(mValues);
                    context.startActivity(intent);//firing intent.
                }
            }
        };

        SimpleItemRecyclerViewAdapter(CharacterListActivity parent,
                                      List<RelatedTopic> items,
                                      boolean twoPane) {
            mValues = items;
            mParentActivity = parent;
            mTwoPane = twoPane;
        }


        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.character_list_content, parent, false);

            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            /**
             * Character details of the character is loaded into the variable
             * characterText. This CharacterText is the parsed to get the characters name
             * which is displayed on the rows of the recycler view. The holder is also given
             * a position tag which will be useful when the row is clicked.
             */
            String characterText = mValues.get(position).getText();
            holder.mIdView.setText(Utils.parseCharacterName(characterText));
//            holder.mContentView.setText(mValues.get(position).content);
//            holder.itemView.setTag(mValues.getRelatedTopics().get(position));
            holder.itemView.setTag(position);
            holder.itemView.setOnClickListener(mOnClickListener);

        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            final TextView mIdView;
//            final TextView mContentView;

            ViewHolder(View view) {
                super(view);
                mIdView = (TextView) view.findViewById(R.id.character_name);
            }
        }
    }
}
