package com.example.crudoperation;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import com.example.crudoperation.Adapter.UserAdapter;
import com.example.crudoperation.Model.Library;
import com.example.crudoperation.Model.User;
import com.example.crudoperation.Model.UserAndLibrary;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.lang.ref.WeakReference;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        rvUsers = findViewById(R.id.recyclerView);
        LinearLayoutManager llm = new LinearLayoutManager(MainActivity.this);
        rvUsers.setLayoutManager(llm);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, AddUserActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        new GetAllUsers(MainActivity.this).execute();
    }

    public static class GetAllUsers extends AsyncTask<Void, Void, List<User>> {
        private WeakReference<Context> c;

        public GetAllUsers(Context c) {
            this.c = new WeakReference<>(c);
        }

        @Override
        protected List<User> doInBackground(Void... voids) {
            UserDatabase ud = UserDatabase.getAppDatabase(c.get());

            //List<UserAndLibrary> uObj = ud.userDao().getUsersAndLibraries();
            //Log.i("LibTag",uObj.get(0).LibList.get(0).book_name + " | " +uObj.get(0).LibList.get(0).book_type);

            return ud.userDao().getAllUsers();
        }

        @Override
        protected void onPostExecute(List<User> users) {
            super.onPostExecute(users);
            RecyclerView rv = ((Activity) c.get()).findViewById(R.id.recyclerView);

            UserAdapter ua = new UserAdapter(c.get(), users);
            rv.setAdapter(ua);

            //new GetAllLibAndUsers(c.get()).execute();
        }
    }


    public static class GetAllLibAndUsers extends AsyncTask<Void, Void, List<UserAndLibrary>> {
        private WeakReference<Context> c;

        public GetAllLibAndUsers(Context c) {
            this.c = new WeakReference<>(c);
        }

        @Override
        protected List<UserAndLibrary> doInBackground(Void... voids) {
            UserDatabase ud = UserDatabase.getAppDatabase(c.get());
            //UserAndLibrary uObj = ud.userDao().getUserAndLibrary(5);
           // Log.i("LibTag",uObj.get(0).LibList.get(0).book_name + " | " +uObj.get(0).LibList.get(0).book_type);
            return ud.userDao().getUsersAndLibraries();
        }

        @Override
        protected void onPostExecute(List<UserAndLibrary> userAndLib) {
            super.onPostExecute(userAndLib);
            for(int i=0; i<userAndLib.size() ;i++)
            {
                User user = userAndLib.get(i).user;
                List<Library> libs = userAndLib.get(i).LibList;
              //Log.i("LibTag","BookName "+userAndLib.get(i).LibList.get(i).book_name+" UserId "+userAndLib.get(i).LibList.get(i).userId);
                Log.i("LibTag","UserName "+user.getFirstName()+" UserAdd "+user.getAddress());
                for (Library lib:libs) {
                    Log.i("LibTag","BookName "+lib.getBookName()+" UserId "+lib.getuserId());
                }
            }
        }
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
