package clysmauk.jjbrestful.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import clysmauk.jjbrestful.R;
import clysmauk.jjbrestful.dummy.Ipsum;

public class ArticleFragment extends Fragment {

    public final static String ARG_POSITION = "position";
    int mCurrentPosition = -1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // If activity recreated (such as from screen rotate), restore
        // the previous article selection set by onSaveInstanceState().
        // This is primarily necessary when in the two-pane layout.
        if (savedInstanceState != null) {
            mCurrentPosition = savedInstanceState.getInt(ARG_POSITION);
        }

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.article_view, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        // During startup, check if there are arguments passed to the fragment.
        // onStart is a good place to do this because the layout has already been
        // applied to the fragment at this point so we can safely call the method
        // below that sets the article text.
        Bundle args = getArguments();
        if (args != null) {
            // Set article based on argument passed in
            updateArticleView(args.getInt(ARG_POSITION));
        } else if (mCurrentPosition != -1) {
            // Set article based on saved instance state defined during onCreateView
            updateArticleView(mCurrentPosition);
        }

        //JB. Preferences not added for now.
        /*
        SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        int newHighScore = 20;
        editor.putInt(getString(R.string.saved_high_score), newHighScore);
        editor.commit(); */



    }

    public void updateArticleView(int position) {
        TextView article = (TextView) getActivity().findViewById(R.id.article);
        article.setText(Ipsum.Articles[position]);
        mCurrentPosition = position;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // Save the current article selection in case we need to recreate the fragment
        outState.putInt(ARG_POSITION, mCurrentPosition);
    }

    public void savePreferenceMan()
    {
        SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        int newHighScore = 20;
        editor.putInt(getString(R.string.saved_high_score), newHighScore);
        editor.commit();
    }

    public long readMe()
    {
        SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        int defaultValue = getResources().getInteger(R.integer.saved_high_score_Default);
        long highScore = sharedPref.getInt(getString(R.string.saved_high_score), defaultValue);
        return highScore;

    }

}
