package com.example.characterviewer;

import android.util.Log;

import com.example.characterviewer.Bean.Example;
import com.example.characterviewer.Bean.RelatedTopic;

import org.w3c.dom.Text;

import java.util.List;

public class Utils {

    /**
     * This static variable is used to save the entire character set when it is pulled from the API. This way we
     * have don't have to make unnecessary API calls to get this data.
     */
    private static Example characterDataFromAPI;

    /**
     * Instead of making the RelatedTopics POJO parcelable to be attached to the
     * intent, I just created a static variable and used getters and setters. Before
     * firing the intent, the set method is called and the item_detail_Activity uses the get method to retrieve it.
     */
    private static List<RelatedTopic> filteredCharacterList;

    public static List<RelatedTopic> getFilteredCharacterList() {
        return filteredCharacterList;
    }

    public static void setFilteredCharacterList(List<RelatedTopic> mValues) {
        filteredCharacterList = mValues;
    }

    public static void setCharacterDataFromAPI(Example body) {
        characterDataFromAPI = body;
    }

    public static Example getCharacterDataFromAPI() {
        return characterDataFromAPI;
    }


    /**
     * The methods below are used to parse the character name and description from the POJOs retured by the API call.
     */
    public static CharSequence parseCharacterName(String text) {
        try {
            return text.substring(0, text.indexOf('-')).trim();
        }catch (Exception e){
            Log.d("DEBUG",e.getMessage());
            return text;
        }
    }

    public static CharSequence parseCharacterDescription(String text) {
        try {
            return text.substring(text.indexOf('-')+2).trim();
        }catch (Exception e){
            Log.d("DEBUG",e.getMessage());
            return text;
        }
    }


}
