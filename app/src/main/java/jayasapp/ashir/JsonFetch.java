package jayasapp.ashir;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

class JsonFetch extends AsyncTask<String, String, Void> {
     String data;
//     VideoList Vid = new VideoList();
    static ArrayList PlayList = new ArrayList<>();
    @Override
    protected Void doInBackground(String... strings) {
       HTTPHandler sh = new HTTPHandler();
        data = sh.makeServiceCall("https://www.googleapis.com/youtube/v3/playlistItems?part=snippet&maxResults=40&playlistId=LLax9CJCQ6aY0bntP3mcIWKQ&key=AIzaSyCMw9Fu5MJ2QTNIJwzVlSenN1Dv9gweZhg");
       try {
           JSONObject YoutubeData = new JSONObject(data);
       JSONArray Youtubeplaylistdata = YoutubeData.getJSONArray("items");
           for(int i=0;i<Youtubeplaylistdata.length();i++){
            JSONObject Ytdata = Youtubeplaylistdata.getJSONObject(i);
               JSONObject Snippet = Ytdata.getJSONObject("snippet");
               //geting title
               String title = Snippet.getString("title");

               //heting thumbnailuRL
               JSONObject thumbnails = Snippet.getJSONObject("thumbnails");
               JSONObject thumbUrl = thumbnails.getJSONObject("standard");
               String ThumbURL = thumbUrl.getString("url");
               //getting video id
               JSONObject ResourceId = Snippet.getJSONObject("resourceId");
               String id = ResourceId.getString("videoId");

               HashMap<String,String> tmpList = new HashMap<String, String>();
//               Vid.setTitle(title);
//               Vid.setThumbnail(ThumbURL);
//               Vid.setVideoId(id);
               tmpList.put("title", title);
               tmpList.put("Thumbnail", ThumbURL);
               tmpList.put("videoId", id);
               PlayList.add(tmpList);
           }
       } catch (JSONException e) {
           e.printStackTrace();
       }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
//        YoutubeActivity.ListPlay.add(PlayList);
//        YoutubeActivity.data.setText(this.data);


    }


}