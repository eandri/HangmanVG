package se.iths.andri.hangmanvg;


import android.support.constraint.ConstraintLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


public class MainActivity extends AppCompatActivity {

    private boolean swap = true;
    private ConstraintLayout mainLayout;
    private ConstraintLayout gamelayout;
    private ConstraintLayout menulayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //creating the menu fragment
        if (savedInstanceState == null) {
            menuCreate();
        }

        findViewById(R.id.floating_forward).setOnClickListener(view -> floatingForward());
        findViewById(R.id.floating_back).setOnClickListener(view -> floatingBack());
        findViewById(R.id.radioButton).setOnClickListener(view -> changeTheme());

         mainLayout = findViewById(R.id.main_layout);
         gamelayout = findViewById(R.id.game_fragment_layout);
         menulayout = findViewById(R.id.menu_fragment_layout);

    }

    private void floatingForward() {
        GameFragment gameFragment = new GameFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentTarget, gameFragment);
        fragmentTransaction.commit();
    }

    private void floatingBack() {
        MenuFragment menuFragment = new MenuFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentTarget, menuFragment);
        fragmentTransaction.commit();
    }


    private void changeTheme() {
        if (swap == true){
            swap = false;
            mainLayout.setBackgroundResource(R.drawable.halloween);
            gamelayout.setBackgroundResource(R.drawable.halloween);
            menulayout.setBackgroundResource(R.drawable.halloween);
        }
        if (swap == false){
            swap = true;
            mainLayout.setBackgroundResource(R.drawable.hangman2);
            menulayout.setBackgroundResource(R.drawable.hangman2);
            gamelayout.setBackgroundResource(R.drawable.hangman2);
        }

    }

    private void menuCreate() {

        MenuFragment menuFragment = new MenuFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragmentTarget, menuFragment);
        fragmentTransaction.commit();
    }
    private void snacBar(){


    }


}
