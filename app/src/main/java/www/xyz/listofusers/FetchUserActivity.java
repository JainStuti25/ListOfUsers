package www.xyz.listofusers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FetchUserActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Users> users;
    private static String JSON_URL = "https://gorest.co.in/public-api/users";
    public Adapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fetch_user);


        recyclerView = findViewById(R.id.usersList);
        users = new ArrayList<>();
        extractUser();
    }

    private void extractUser() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, JSON_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject obj = new JSONObject(response);

                    JSONArray uArray = obj.getJSONArray("data");


                    for (int i = 0; i < uArray.length(); i++) {
                        JSONObject uObject = uArray.getJSONObject(i);
                        Users hero = new Users(uObject.getString("name"), uObject.getString("email"),uObject.getString("gender"),uObject.getString("status"));

                        users.add(hero);
                    }
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    adapter = new Adapter(getApplicationContext(),users);
                    recyclerView.setAdapter(adapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });


        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}