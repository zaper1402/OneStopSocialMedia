package jayasapp.ashir;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import java.util.HashMap;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static jayasapp.ashir.JsonFetch.PlayList;

public  class YoutubeActivity extends YouTubeBaseActivity {

    YouTubePlayerView mYoutubePlayerView;
    Button PlayBtn;
    YouTubePlayer.OnInitializedListener mOninitializeListener;
    public static TextView data;
      ListView listVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube);
//       data  = (TextView)findViewById(R.id.Textfetch);
        PlayBtn = (Button)findViewById(R.id.ytplaybtn);
    mYoutubePlayerView = (YouTubePlayerView)findViewById(R.id.youtubePlay);
    listVideo = (ListView)findViewById(R.id.listplay);
    CustomAdapter customAdapter = new CustomAdapter();
    listVideo.setAdapter(customAdapter);


    mOninitializeListener = new YouTubePlayer.OnInitializedListener() {
        @Override
        public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        youTubePlayer.loadPlaylist("LLax9CJCQ6aY0bntP3mcIWKQ");
        }

        @Override
        public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

        }
    };



    PlayBtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
                JsonFetch process = new JsonFetch();
                process.execute();
            mYoutubePlayerView.initialize(YoutubeConfig.getApiKey(),mOninitializeListener);
        }
    });
    }

     class CustomAdapter extends BaseAdapter{

         @Override
         public int getCount() {
             return PlayList.size();
         }

         @Override
         public Object getItem(int i) {
             return null;
         }

         @Override
         public long getItemId(int i) {
             return 0;
         }

         @Override
         public View getView(int i, View view, ViewGroup viewGroup) {
             view = getLayoutInflater().inflate(R.layout.activity_video_list,null);
//             ImageView imageView = (ImageView)view.findViewById(R.id.imageView);
             TextView textView = (TextView)view.findViewById(R.id.Title);
             textView.setText(PlayList.get(i).toString());
             return null;
         }
     }



}

