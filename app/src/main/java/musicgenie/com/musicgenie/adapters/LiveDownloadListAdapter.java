package musicgenie.com.musicgenie.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import musicgenie.com.musicgenie.R;
import musicgenie.com.musicgenie.interfaces.DownloadCancelListener;
import musicgenie.com.musicgenie.models.DownloadTaskModel;

/**
 * Created by Ankit on 8/10/2016.
 */
public class LiveDownloadListAdapter extends ArrayAdapter<String> {

    private static Context context;
    private static LiveDownloadListAdapter mInstance;
    private ArrayList<DownloadTaskModel> downloadingList;
    private DownloadCancelListener downloadCancelListener;
    public LiveDownloadListAdapter(Context context) {
        super(context,0);
        this.context = context;
        downloadingList = new ArrayList<>();
    }

    public static LiveDownloadListAdapter getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new LiveDownloadListAdapter(context);
        }
        return mInstance;
    }

    public void setOnDownloadCancelListener(DownloadCancelListener listener){
        this.downloadCancelListener = listener;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


         ProgressBar progressBar;
         TextView progressText;
         Button cancelBtn;
         TextView taskTitle;

        View v  = convertView;
        if(v == null){
            v = LayoutInflater.from(context).inflate(R.layout.downloading_item,parent,false);

        }
        progressBar = (ProgressBar) v.findViewById(R.id.progressBar);
        progressText = (TextView) v.findViewById(R.id.progressText);
        cancelBtn = (Button) v.findViewById(R.id.cancelBtn);
        taskTitle = (TextView) v.findViewById(R.id.download_task_title);

        progressBar.setProgress(downloadingList.get(position).Progress);
        progressText.setText(downloadingList.get(position).Progress + " %");
        taskTitle.setText(downloadingList.get(position).Title);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        return v;
    }

    @Override
    public int getCount() {
        return downloadingList.size();
    }

    private class DownloadingTaskViewHolder{

        public DownloadingTaskViewHolder(View v) {

        }

    }

    private void cancelDownload(String taskID){
        if(this.downloadCancelListener!=null){
            downloadCancelListener.onDownloadCancel(taskID);
        }



    }


}
