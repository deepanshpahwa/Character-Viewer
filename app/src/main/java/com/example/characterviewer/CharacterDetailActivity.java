package com.example.characterviewer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;

public class CharacterDetailActivity extends AppCompatActivity {

    /**
     * This activity is used when the app is launched in a phone. This activity doesn't do
     * much. It just inflates the fragment into the appropriate container.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setBackgroundColor(getResources().getColor(R.color.colorAccent));


        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            Bundle arguments = new Bundle();

            //attaching the position of the item clicked after getting it from the intent(putExtra
            arguments.putInt(CharacterDetailFragment.ARG_ID,
                    getIntent().getIntExtra(CharacterDetailFragment.ARG_ID,0));
            CharacterDetailFragment fragment = new CharacterDetailFragment();
            fragment.setArguments(arguments);

            //inflating the fragment into the container character_detail_container
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.character_detail_container, fragment)
                    .commit();
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            navigateUpTo(new Intent(this, CharacterListActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
