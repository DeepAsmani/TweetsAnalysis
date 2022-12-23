package com.tweets.tweetsanalysis;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class TweetActivity extends Activity implements View.OnClickListener {

    private static final String TWITTER_KEY = ""; // Use your twitter Consumer Key
    private static final String TWITTER_SECRET = ""; // Use your twitter Secret Key
    private static final String Access_Token = "";
    private static final String Access_Token_Secret = "";
    private ListView SearchList;
    private Button button;
    private String fetchChoice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey(TWITTER_KEY)               .setOAuthConsumerSecret(TWITTER_SECRET)
                .setOAuthAccessToken(Access_Token)                .setOAuthAccessTokenSecret(Access_Token_Secret);
        TwitterFactory tf = new TwitterFactory(cb.build());
        Twitter twitter = tf.getInstance();
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.tweetactivity_layout);

        button= findViewById(R.id.button2);
        button.setOnClickListener(this);

        Bundle p = getIntent().getExtras();
        String fetchQ = p.getString("searchQuery");
        fetchChoice = p.getString("choice");
        Toast.makeText(TweetActivity.this,"You searched for :"+fetchQ, Toast.LENGTH_SHORT).show();
        setProgressBarIndeterminateVisibility(true);
        Query query = new Query(fetchQ);
        query.setCount(25);
        query.setLang("en");
        //query.setResultType();
        SearchList = (ListView) findViewById(R.id.tweet_search);

        final ArrayList<String> lines = new ArrayList<>();
        Thread thread = new Thread(() -> {
            try {
                QueryResult result = twitter.search(query);
                setProgressBarIndeterminateVisibility(false);
                File file = new File(getBaseContext().getFilesDir(), "myFile.txt");
                String str = getFilesDir().toString();
                FileOutputStream outputStream;
                outputStream = openFileOutput("myFile.txt", Context.MODE_PRIVATE);
                int i;
                for (Status t:result.getTweets())
                    outputStream.write((t.getText().replaceAll("\n"," ")+"\n").getBytes());
                outputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        thread.start();
        try {
            FileInputStream fi = openFileInput("myFile.txt");
            BufferedReader bi = new BufferedReader(new InputStreamReader(fi));
            String oline;
            while(( oline = bi.readLine())!=null) {
                lines.add(oline);
            }
                ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, lines);
                SearchList.setAdapter(adapter);

        }catch (Exception e){}

    }

    @Override
    public void onClick(View v) {
        if (button.equals(v))
        {
            Toast.makeText(this, "Calculating Results...", Toast.LENGTH_SHORT).show();
            final Intent i = new Intent(TweetActivity.this, test.class);
            i.putExtra("choice",fetchChoice);
            Thread thread = new Thread(){
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000);
                        startActivity(i);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
            thread.start();
        }
    }


}

