package id.or.redroid.biografi;

import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WebView view = (WebView) findViewById(R.id.webbio);
        view.setVerticalScrollBarEnabled(false);
        view.loadData(getString(R.string.me), "text/html; charset=utf-8", "utf-8");

        TextView tvGit = (TextView) findViewById(R.id.tvGit);
        TextView tvIg = (TextView) findViewById(R.id.tvIg);

        tvIg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri urlinst = Uri.parse("https://www.instagram.com/dmspt__/");
                Intent insta = new Intent(Intent.ACTION_VIEW, urlinst);

                insta.setPackage("com.instagram.android");

                try {
                    startActivity(insta);
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("https://www.instagram.com/dmspt__/")));
                }

            }
        });

        tvGit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri urlgit = Uri.parse("https://github.com/dmspt");
                Intent git = new Intent(Intent.ACTION_VIEW, urlgit);

                if (git.resolveActivity(getPackageManager()) != null) {
                    startActivity(git);
                } else {
                    Log.d("ImplicitIntents", "Can't handle this intent!");
                }

            }
        });

    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setMessage("Anda Yakin Ingin Keluar?");
        builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }
}
