package com.example.felelszvagymersz_updated;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Vector;

import pl.droidsonroids.gif.GifAnimationMetaData;
import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

public class MainActivity extends AppCompatActivity
{
    int szam=0;
    int kör=0;
    int random_counter=0;
    boolean error=false;
    Vector<String>felelet=new Vector();
    Vector<String>meresek=new Vector();
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button felelek= findViewById(R.id.felelek);
        Button merek= findViewById(R.id.merek);
        Switch switch_rule=findViewById(R.id.switch_rule);
        TextView text_output=(TextView) findViewById(R.id.text_output);
        TextView text_output2=(TextView) findViewById(R.id.text_output2);
        TextView count=(TextView) findViewById(R.id.count);
        TextView difficulity=(TextView) findViewById(R.id.difficulity);
        Context context = getApplicationContext();
        Toast toast = Toast.makeText(context,"",Toast.LENGTH_SHORT);
        View screenView=findViewById(R.id.rView);
        final MediaPlayer hit=MediaPlayer.create(this, R.raw.hit);
        final MediaPlayer critical_shotdown= MediaPlayer.create(this, R.raw.cs);
        final MediaPlayer weed=MediaPlayer.create(this, R.raw.weed);
        final MediaPlayer coffin=MediaPlayer.create(this, R.raw.coffin);
        AlertDialog.Builder dialog_window=new AlertDialog.Builder(MainActivity.this);
        dialog_window.setTitle("CRITICAL ERROR!");
        dialog_window.setMessage("APPLICATION SHUTTING DOWN!");
        dialog_window.setNeutralButton("OK!", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                finish();
            }
        });
        ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1);
        try
        {
            InputStream in=getAssets().open("felel.txt");
            BufferedReader br=new BufferedReader(new InputStreamReader(in));
            String sor;
            while ((sor = br.readLine()) != null)
            {
                felelet.add(sor);
            }
            br.close();
        }
        catch(Exception er)
        {
            System.out.println("Error:" + er);
            er.printStackTrace();
            error=true;
            critical_shotdown.start();
            dialog_window.show();
            toast.setText("HIBA TÖRTÉNT A FÁLJ BEOLVASÁSA SORÁN!");
            toast.show();
        }
        try
        {
            InputStream in2=getAssets().open("mer.txt");
            BufferedReader br2=new BufferedReader(new InputStreamReader(in2));
            String sor;
            while ((sor = br2.readLine()) != null)
            {
                meresek.add(sor);
            }

            br2.close();
        }
        catch (Exception er)
        {
            System.out.println("Error:" + er);
            er.printStackTrace();
            error=true;
            critical_shotdown.start();
            dialog_window.show();
            toast.setText("HIBA TÖRTÉNT A FÁLJ BEOLVASÁSA SORÁN!");
            toast.show();
        }
        if(error==false)
        {
            toast.setText("SIKERES FÁJL BEOLVASÁS!");
            toast.show();
        }
        felelek.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                szam=(int)(Math.random()*felelet.size());
                random_counter=random_counter+szam;
                if((kör+2)%3==0&&switch_rule.isChecked())
                {
                    felelek.setEnabled(false);
                }
                else{felelek.setEnabled(true);}
                kör++;
                count.setText("kör:"+Integer.toString(kör));
                text_output.setText(felelet.get(szam));
                if(szam<55)
                {
                    text_output2.setText("Büntetés: 1 korty hosszú!");
                    difficulity.setText("KÖNNYŰ");
                    difficulity.setBackgroundResource(R.color.green);
                }
                else if(szam<110)
                {
                    text_output2.setText("Büntetés: 3 korty hosszú!");
                    difficulity.setText("KÖZEPES");
                    difficulity.setBackgroundResource(R.color.yellow);
                }
                else if(szam<felelet.size())
                {
                    text_output2.setText("Büntetés: 2cl feles!");
                    difficulity.setText("NEHÉZ");
                    difficulity.setBackgroundResource(R.color.red);
                }
                if(random_counter==420||random_counter%420==0||kör==420)
                {
                    Intent i =new Intent(MainActivity.this,activity_weed.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    startActivity(i);
                }
                if(felelet.get(szam).substring(0,7).equals("Instant"))
                {
                    coffin.start();
                }
            }
        });
        merek.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                szam=(int)(Math.random()*meresek.size());
                random_counter=random_counter+szam;
                if((kör+2)%3==0&&switch_rule.isChecked())
                {
                    felelek.setEnabled(false);
                }
                else{felelek.setEnabled(true);}
                kör++;
                count.setText("kör:"+Integer.toString(kör));
                text_output.setText(meresek.get(szam));
                if(szam<39)
                {
                    text_output2.setText("Büntetés: 1 korty hosszú!");
                    difficulity.setText("KÖNNYŰ");
                    difficulity.setBackgroundResource(R.color.green);
                }
                else if(szam<72)
                {
                    text_output2.setText("Büntetés: 3 korty hosszú!");
                    difficulity.setText("KÖZEPES");
                    difficulity.setBackgroundResource(R.color.yellow);
                }
                else if(szam<meresek.size())
                {
                    text_output2.setText("Büntetés: 2cl feles!");
                    difficulity.setText("NEHÉZ");
                    difficulity.setBackgroundResource(R.color.red);
                }
                if(random_counter==420||random_counter%420==0||kör==420)
                {
                    Intent i =new Intent(MainActivity.this,activity_weed.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    startActivity(i);
                }
                if(felelet.get(szam).substring(0,7).equals("Instant"))
                {
                    coffin.start();
                }
                if(kör==1000)
                {
                    /*
                        IGEN, HAZUDTAM! CSAK "POÉNKODNI" AKARTAM EGY HIBAÜZENETTEL! KAMU A HÁTTÉR CRASH ÉS A SZÖVEG HIBÁJA!
                        UNATKOZTAM ÉS CSAK FIGYELEM FELKELTÉSBŐL CSINÁLTAM!
                        SEMMI ÉRTELME NINCS ENNEK...
                    */
                    screenView.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.corruption));
                    felelek.setEnabled(false);
                    merek.setEnabled(false);
                    text_output.setText("N̵̡̞͍̗̳̺͇̝̩̦̟̘̭͓̱͈̉́̋̊̈́͐͛̿͋̾̚͜͜U̴̢̡̡̢̖͙̰̙̹͙̭̥̠̥̻̩̫͍͉̦͈̫̳͙͇̝͖̯̹̒̎͂̅̈́͗̓͛̆̓̒̂͒̆͗̋̿͘̚͘͝L̵̛̻̲͙̖̲̲͎͇̦̰̫̲̩͈̰̫͍̉͋͗̓̂̿̌͒̿̐̓̏̐̐̿̀̀̿̓̚L̵̨̡̡̨̧̛̟͕̮̞͔͎͔̖̘̝̜̓̃͆̃̋̎̋͐͋́͛͗̀̋̕̕");
                    text_output2.setText("N̵̡̞͍̗̳̺͇̝̩̦̟̘̭͓̱͈̉́̋̊̈́͐͛̿͋̾̚͜͜U̴̢̡̡̢̖͙̰̙̹͙̭̥̠̥̻̩̫͍͉̦͈̫̳͙͇̝͖̯̹̒̎͂̅̈́͗̓͛̆̓̒̂͒̆͗̋̿͘̚͘͝L̵̛̻̲͙̖̲̲͎͇̦̰̫̲̩͈̰̫͍̉͋͗̓̂̿̌͒̿̐̓̏̐̐̿̀̀̿̓̚L̵̨̡̡̨̧̛̟͕̮̞͔͎͔̖̘̝̜̓̃͆̃̋̎̋͐͋́͛͗̀̋̕̕");
                    critical_shotdown.start();
                    dialog_window.show();
                }
            }
        });
        switch_rule.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if(isChecked==true)
                {
                    if((kör+2)%3==0)
                    {
                        felelek.setEnabled(false);
                    }
                }
                else
                {
                    felelek.setEnabled(true);
                }
            }
        });
    }
}