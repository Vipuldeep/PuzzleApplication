package com.example.jc497971.ourpuzzleapp;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class TwoOptiontwoActivity extends AppCompatActivity {

    private final int NO_OF_PIECES = 4;
    private final String BUTTON_NAME_PREFIX = "btn";
    int time_to_solve_puzzle = 1;
    //an array of buttons
    Button[] btn = new Button[NO_OF_PIECES];
    //correct sequence of IDs of buttons
    int[] correct_id_seq = new int[NO_OF_PIECES];
    /*this array is the working array.
      Its element's values are similar to correct_id_seq[] except diff locations*/
    int[] rand_id_seq = new int[NO_OF_PIECES];
    int two_indexes_to_swap_img[] = {-1, -1};
    int num_of_clicks = 0; //need 2 clicks to swap images
    Button two_buttons_to_swap[] = {null, null};
    MediaPlayer completed;
    TextView timeTextView;
    Timer T;Bundle bundle;
    DBHelper dbHelper;
    String name;

    public void rand_arr_elements(int[] arr) {
        Random random = new Random();
        int temp_index;
        int temp_obj;
        for (int i = 0; i < arr.length - 1; i++) {
            //a random number between i+1 and arr.length - 1
            temp_index = i + 1 + random.nextInt(arr.length - 1 - i);
            //swap the element at i with the element at temp_index
            temp_obj = arr[i];
            arr[i] = arr[temp_index];
            arr[temp_index] = temp_obj;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_optionone);


        timeTextView = (TextView) findViewById(R.id.timeTextView);
        dbHelper =new DBHelper(this);

        // get buttons
        for(int i = 0; i < NO_OF_PIECES; i++){
            btn[i] = (Button) findViewById(this.getResources().getIdentifier(
                    BUTTON_NAME_PREFIX + Integer.toString(i),"id", this.getPackageName()));

        }
        //first puzzle
        play_game(10, "e");
    }

    public void play_game(int perusal_time_by_seconds, String image_name) {

        //set the values for the correct_id_seq array
        for(int i = 0; i < NO_OF_PIECES; i++){
            correct_id_seq[i] = this.getResources().getIdentifier(image_name
                    + Integer.toString(i),"drawable", this.getPackageName());
        }

        // based on the values of correct_id_seq, set the button background
        for(int i = 0; i < NO_OF_PIECES; i++){
            btn[i].setBackgroundResource(correct_id_seq[i]);
        }

        for(int i = 0; i < NO_OF_PIECES; i++){
            btn[i].setClickable(false);
        }

        //display image in an amount of perusal_time_by_seconds
        new CountDownTimer(perusal_time_by_seconds * 1000, 1000){
            @Override
            public void onTick(long millisUntilFinished) {
                timeTextView.setText("TIME: " + Long.toString(millisUntilFinished/1000));
            }
            @Override
            public void onFinish() {

                for(int i = 0; i < NO_OF_PIECES; i++){
                    btn[i].setClickable(true);
                }
                make_puzzle_with_time_tick();
            }
        }.start();
    }


    public void make_puzzle_with_time_tick(){

        rand_id_seq = Arrays.copyOf(correct_id_seq, correct_id_seq.length);

        //call 2 times for better results
        rand_arr_elements(rand_id_seq); rand_arr_elements(rand_id_seq);

        // based on the values of rand_id_seq, set the buttons' background
        for(int i = 0; i < NO_OF_PIECES; i++){
            btn[i].setBackgroundResource(rand_id_seq[i]);
        }

        //counting time by seconds
        time_to_solve_puzzle = -1;
        T = new Timer();
        T.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        time_to_solve_puzzle++;
                        timeTextView.setText("TIME: " + time_to_solve_puzzle);
                    }
                });
            }
        }, 1000, 1000);
    }

    public void on_click_image(View v){
        Button button = (Button)v;
        completed = MediaPlayer.create(this,R.raw.completed);

        String temp_str = button.getResources().getResourceName(button.getId());

        int id_pos = temp_str.indexOf("id/" + BUTTON_NAME_PREFIX);

        int index = Integer.parseInt(temp_str.substring(id_pos +
                ("id/" + BUTTON_NAME_PREFIX).length()));

        two_indexes_to_swap_img[num_of_clicks] = index;
        two_buttons_to_swap[num_of_clicks] = button;

        if (num_of_clicks == 1) {
            //2 clicks already - swap images now
            two_buttons_to_swap[0].setBackgroundResource(rand_id_seq[two_indexes_to_swap_img[1]]);
            two_buttons_to_swap[1].setBackgroundResource(rand_id_seq[two_indexes_to_swap_img[0]]);
            //update the rand_id_seq array
            int temp = rand_id_seq[two_indexes_to_swap_img[0]];
            rand_id_seq[two_indexes_to_swap_img[0]] = rand_id_seq[two_indexes_to_swap_img[1]];
            rand_id_seq[two_indexes_to_swap_img[1]] = temp;

            //if it is then the user wins
            if (Arrays.equals(correct_id_seq, rand_id_seq)) {
                if (T != null)
                    T.cancel();
                Log.i("Time = ", Integer.toString(time_to_solve_puzzle));
                for(int i = 0; i < NO_OF_PIECES; i++){
                    btn[i].setClickable(false);
                    completed.start();
                    Context context = getApplicationContext();
                    CharSequence text = "Completed!";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();

                }
                test_db();
            }
        }
        num_of_clicks++;

        if (num_of_clicks == 2)
            num_of_clicks = 0;
    }


    private void test_db(){

        bundle = getIntent().getExtras();
        name = bundle.getString("name");

        int time = time_to_solve_puzzle;
        Log.i("details"," "+name+ " "+ time + " "+1+" e");
        dbHelper.insertPlayer(name, time, 1, "e");

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
