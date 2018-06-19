package indah.suaraku;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

public class SuarakuActivity extends AppCompatActivity {
    Button btn_play, btn_pause, btn_stop;
    MediaPlayer media_player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suaraku);

        btn_play = (Button) findViewById(R.id.button);
        btn_pause = (Button) findViewById(R.id.button2);
        btn_stop = (Button) findViewById(R.id.button3);

        stateAwal();

        btn_play.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                play();
                btn_play.setEnabled(false);
                btn_pause.setEnabled(true);
                btn_stop.setEnabled(true);
            }
        });

        btn_pause.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                pause();
            }
        });

        btn_stop.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                stop();
            }
        });
    }

    public void stateAwal(){
        btn_play.setEnabled(true);
        btn_pause.setEnabled(false);
        btn_stop.setEnabled(false);
    }

    private void play(){
        media_player = MediaPlayer.create(this,R.raw.fatihah);
        try {
            media_player.prepare();
        }catch (IllegalStateException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }

        media_player.start();

        media_player.setOnCompletionListener(new MediaPlayer.OnCompletionListener(){
            @Override
            public void onCompletion (MediaPlayer mediaPlayer){
                stateAwal();
            }
        });
    }

    private void pause(){
        if(media_player!=null) {
            if (media_player.isPlaying()) {
                media_player.pause();
            } else {
                media_player.start();
            }
        }
    }

    public void stop(){
        media_player.stop();
        try {
            media_player.prepare();
            media_player.seekTo(0);
        }catch (Throwable t){
            t.printStackTrace();
        }
        stateAwal();
    }
}
