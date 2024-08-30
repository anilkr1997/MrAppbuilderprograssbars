package com.mrappbuilder.mrappbuilderprograssbars;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.mrappbuilder.mabprograssbar.MrAppbuilderProgressBar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        Button indeterminate = (Button) findViewById(R.id.indeterminate);
        indeterminate.setOnClickListener(this);

        Button labelIndeterminate = (Button) findViewById(R.id.label_indeterminate);
        labelIndeterminate.setOnClickListener(this);

        Button detailIndeterminate = (Button) findViewById(R.id.detail_indeterminate);
        detailIndeterminate.setOnClickListener(this);

        Button graceIndeterminate = (Button) findViewById(R.id.grace_indeterminate);
        graceIndeterminate.setOnClickListener(this);

        Button determinate = (Button) findViewById(R.id.determinate);
        determinate.setOnClickListener(this);

        Button annularDeterminate = (Button) findViewById(R.id.annular_determinate);
        annularDeterminate.setOnClickListener(this);

        Button barDeterminate = (Button) findViewById(R.id.bar_determinate);
        barDeterminate.setOnClickListener(this);

        Button customView = (Button) findViewById(R.id.custom_view);
        customView.setOnClickListener(this);

        Button dimBackground = (Button) findViewById(R.id.dim_background);
        dimBackground.setOnClickListener(this);

        Button customColor = (Button) findViewById(R.id.custom_color_animate);
        customColor.setOnClickListener(this);
    }

    private MrAppbuilderProgressBar mrAppbuilderProgressBar;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.indeterminate:
                mrAppbuilderProgressBar = MrAppbuilderProgressBar.create(this)
                        .setStyle(MrAppbuilderProgressBar.Style.SPIN_INDETERMINATE);
                scheduleDismiss();
                break;
            case R.id.label_indeterminate:
                mrAppbuilderProgressBar = MrAppbuilderProgressBar.create(this)
                        .setStyle(MrAppbuilderProgressBar.Style.SPIN_INDETERMINATE)
                        .setLabel("Please wait")
                        .setCancellable(new DialogInterface.OnCancelListener()
                        {
                            @Override public void onCancel(DialogInterface
                                                                   dialogInterface)
                            {
                                Toast.makeText(MainActivity.this, "You " +
                                        "cancelled manually!", Toast
                                        .LENGTH_SHORT).show();
                            }
                        });

                scheduleDismiss();
                break;
            case R.id.detail_indeterminate:
                mrAppbuilderProgressBar = MrAppbuilderProgressBar.create(this)
                        .setStyle(MrAppbuilderProgressBar.Style.SPIN_INDETERMINATE)
                        .setLabel("Please wait")
                        .setDetailsLabel("Downloading data");
                scheduleDismiss();
                break;
            case R.id.grace_indeterminate:
                mrAppbuilderProgressBar = MrAppbuilderProgressBar.create(this)
                        .setStyle(MrAppbuilderProgressBar.Style.SPIN_INDETERMINATE)
                        .setGraceTime(1000);
                scheduleDismiss();
                break;
            case R.id.determinate:
                mrAppbuilderProgressBar = MrAppbuilderProgressBar.create(MainActivity.this)
                        .setStyle(MrAppbuilderProgressBar.Style.PIE_DETERMINATE)
                        .setLabel("Please wait");
                simulateProgressUpdate();
                break;
            case R.id.annular_determinate:
                mrAppbuilderProgressBar = MrAppbuilderProgressBar.create(MainActivity.this)
                        .setStyle(MrAppbuilderProgressBar.Style.ANNULAR_DETERMINATE)
                        .setLabel("Please wait")
                        .setDetailsLabel("Downloading data");
                simulateProgressUpdate();
                break;
            case R.id.bar_determinate:
                mrAppbuilderProgressBar = MrAppbuilderProgressBar.create(MainActivity.this)
                        .setStyle(MrAppbuilderProgressBar.Style.BAR_DETERMINATE)
                        .setLabel("Please wait");
                simulateProgressUpdate();
                break;
            case R.id.custom_view:
//                ImageView imageView = new ImageView(this);
//                imageView.setBackgroundResource(R.drawable.spin_animation);
//                AnimationDrawable drawable = (AnimationDrawable) imageView.getBackground();
//                drawable.start();
//                mrAppbuilderProgressBar = MrAppbuilderProgressBar.create(this)
//                        .setCustomView(imageView)
//                        .setLabel("This is a custom view");
//                scheduleDismiss();
                break;
            case R.id.dim_background:
                mrAppbuilderProgressBar = MrAppbuilderProgressBar.create(this)
                        .setStyle(MrAppbuilderProgressBar.Style.SPIN_INDETERMINATE)
                        .setDimAmount(0.5f);
                scheduleDismiss();
                break;
            case R.id.custom_color_animate:
                //noinspection deprecation
                mrAppbuilderProgressBar = MrAppbuilderProgressBar.create(this)
                        .setStyle(MrAppbuilderProgressBar.Style.SPIN_INDETERMINATE)
                        .setWindowColor(getResources().getColor(androidx.cardview.R.color.cardview_dark_background))
                        .setAnimationSpeed(2);
                scheduleDismiss();
                break;
        }

        mrAppbuilderProgressBar.show();
    }

    private void simulateProgressUpdate() {
        mrAppbuilderProgressBar.setMaxProgress(100);
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            int currentProgress;
            @Override
            public void run() {
                currentProgress += 1;
                mrAppbuilderProgressBar.setProgress(currentProgress);
                if (currentProgress == 80) {
                    mrAppbuilderProgressBar.setLabel("Almost finish...");
                }
                if (currentProgress < 100) {
                    handler.postDelayed(this, 50);
                }
            }
        }, 100);
    }

    private void scheduleDismiss() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mrAppbuilderProgressBar.dismiss();
            }
        }, 2000);
    }
}