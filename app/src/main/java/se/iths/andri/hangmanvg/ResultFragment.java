package se.iths.andri.hangmanvg;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import javax.xml.transform.Result;

import static se.iths.andri.hangmanvg.R.id.hasWon;


/**
 * A simple {@link Fragment} subclass.
 */
public class ResultFragment extends Fragment {


    private static int lives;
    private static String word;
    private static boolean hasWon;
    private Object GameFragment;

    public ResultFragment() {
        // Required empty public constructor
    }
    protected TextView result;
    protected TextView theWordWas;
    protected TextView triesLeft;



    private GameFragment gameFragment;

    public static void setParameters(int lives, String randomWord, boolean hasWon) {
        ResultFragment.lives = lives;
        ResultFragment.word = randomWord;
        ResultFragment.hasWon = hasWon;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_result, container, false);


    }
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        //ignore
        //Bundle bundle = getArguments();
        //bundle.getBoolean(haswin);
        Button main = getActivity().findViewById(R.id.button_mainmenu);
        main.setOnClickListener(view -> mainOnClick());

        result = getActivity().findViewById(R.id.result);
        theWordWas = getActivity().findViewById(R.id.the_word_was);
        triesLeft = getActivity().findViewById(R.id.tries_left);

        if(hasWon == true){
            result.setText("You have won!");
        }

        else{
            result.setText("You have lost!");
        }

        theWordWas.setText("The word was: "+word);

        triesLeft.setText("Tries left: "+lives);
    }

    private void mainOnClick() {
        MenuFragment menuFragment = new MenuFragment();
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentTarget, menuFragment);
        fragmentTransaction.commit();
    }

}
