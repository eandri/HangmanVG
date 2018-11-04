package se.iths.andri.hangmanvg;


import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 */
public class GameFragment extends Fragment {

    private TextView usedLetters2;
    private TextView hiddenLetters;
    private ImageView gameImage;
    private String randomWord;
    private List<Drawable> gameImages;
    private static Random rng = new Random();
    private String[] wordList;
    private TextView userInput;
    private String stringInput1;
    private String usedLetters = " ";
    private StringBuilder hiddenWord = new StringBuilder();
    private  StringBuilder correctLetters;
    private StringBuilder wrongletters;
    boolean[] shown;
    boolean hasWon;
    public GameFragment() {

    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        gameImages = new ArrayList<>();
        gameImage = getActivity().findViewById(R.id.game_imageview);
        initWords();
        setList();
        getWord();

        for (int i = 0; i < randomWord.length(); i++){
            hiddenWord.append("-");
        }

        hiddenLetters = getActivity().findViewById(R.id.hidden_letters);
        hiddenLetters.setText(hiddenWord);

        usedLetters2 = getView().findViewById(R.id.used_letters);


        //my user input
        userInput = getActivity().findViewById(R.id.user_guess);
        userInput.setOnClickListener(view -> userInput.setText(" "));

        Button guess = getActivity().findViewById(R.id.button_guess);
        getActivity().findViewById(R.id.button_guess).setOnClickListener(view -> onGuessClicked());




    }


    private void noInput() {

    }

    int lives = 10;

    public void onGuessClicked() {
        stringInput1 = userInput.getText().toString();

        if (stringInput1 == " ") {
                Snackbar.make(getView(), "You need to type in a letter!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                return;

        }
        else if ( usedLetters.contains(stringInput1)){
                Snackbar.make(getView(), "You have already tried this letter!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                return;


        }
        if (!usedLetters.contains(stringInput1)){
        usedLetters += (" "+ stringInput1.charAt(0) + ",");
        usedLetters2.setText(usedLetters);
        }



        for(int i = 0; i < randomWord.length() ; i++) {



            if(stringInput1.charAt(0) == randomWord.charAt(i)) {
                //Match
                shown[i]= true;
                for (int j = 0; j< hiddenWord.length(); j++){
                    if (shown[j] == false){
                        hiddenWord.setCharAt(j,'-');
                    }
                    else if (shown[j] == true && hiddenWord.charAt(j) =='-'){
                        hiddenWord.setCharAt(j,randomWord.charAt(j));


                    }
                    hiddenLetters.setText(hiddenWord);

                    if (randomWord.equals(hiddenWord.toString())){
                        hasWon();
                    }




                }



                userInput.setText("");
                return;
            }
                //Update UI





        }
        ifLetterWrong();



    }
    private void ifLetterWrong() {
        Snackbar.make(getView(), "You chose the wrong letter!", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
        lives--;
        gameImage.setImageDrawable(gameImages.get(lives));
        if (lives == 0){
            hasLost();
        }

    }

    public void hasWon() {
        hasWon = true;

        ResultFragment resultFragment = new ResultFragment();
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        ResultFragment.setParameters(lives, randomWord, hasWon);
        fragmentTransaction.add(R.id.fragmentTarget, resultFragment);
        fragmentTransaction.commit();



    }

    public void hasLost() {
        hasWon = false;

        ResultFragment resultFragment = new ResultFragment();
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        ResultFragment.setParameters(lives, randomWord, hasWon);
        fragmentTransaction.add(R.id.fragmentTarget, resultFragment);
        fragmentTransaction.commit();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game2, container, false);



    }

    public void initWords() {
       wordList = getResources().getStringArray(R.array.word_list);
    }

    /*
    getts a max number to create a scope in wich we can ranomise the outcome.
     */
    public static int getRng(int max){

        return rng.nextInt(max +1);

    }


    public  void getWord() {
        //change this to 3 later. max = 3!
        int i = getRng(3);

        randomWord = wordList[i];
        shown = new boolean[randomWord.length()];

        }


    public void setList() {
        gameImages.add(getResources().getDrawable(R.drawable.hang0));
        gameImages.add(getResources().getDrawable(R.drawable.hang1));
        gameImages.add(getResources().getDrawable(R.drawable.hang2));
        gameImages.add(getResources().getDrawable(R.drawable.hang3));
        gameImages.add(getResources().getDrawable(R.drawable.hang4));
        gameImages.add(getResources().getDrawable(R.drawable.hang5));
        gameImages.add(getResources().getDrawable(R.drawable.hang6));
        gameImages.add(getResources().getDrawable(R.drawable.hang7));
        gameImages.add(getResources().getDrawable(R.drawable.hang8));
        gameImages.add(getResources().getDrawable(R.drawable.hang9));
        gameImages.add(getResources().getDrawable(R.drawable.hang10));
        }

}
