package app.mobile.picopalaapp.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.List;

import app.mobile.picopalaapp.R;
import app.mobile.picopalaapp.controller.Controller;
import app.mobile.picopalaapp.model.Consultant;
import app.mobile.picopalaapp.view.adapters.ConsultantListAdapter;

public class ConsultantsActivity extends AppCompatActivity {

    private Context context;
    private Controller controller;
    private List<Consultant> consultantList;
    private ListView lvConsultants;
    private ConsultantListAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consultants_list);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        lvConsultants = findViewById(R.id.lvConsultants);

        context = ConsultantsActivity.this;
        controller = new Controller(context);

        new GetConsultants().execute();

    }



    private class GetConsultants extends AsyncTask<Void, Void, Void> {

        private ProgressDialog dialog;


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(context);
            dialog.setTitle(getString(R.string.app_name));
            dialog.setCancelable(false);
            dialog.setIcon(R.mipmap.ic_launcher);
            dialog.setMessage("Espere un momento...");
            dialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                consultantList = controller.getConsultants();
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            adapter = new ConsultantListAdapter(consultantList, context);
            lvConsultants.setAdapter(adapter);
            dialog.dismiss();

        }
    }
}