package milo.app.ezlo.test.ui.adapters;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import milo.app.ezlo.test.R;
import milo.app.ezlo.test.data.model.Devices;
import milo.app.ezlo.test.databinding.MyListBinding;
import milo.app.ezlo.test.ui.activity.Constants;
import milo.app.ezlo.test.ui.activity.LoginActivity;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private ArrayList<Devices> arrayList;
    private Activity context;
    private LayoutInflater layoutInflater;
    MyListBinding myListBinding;

    public RecyclerViewAdapter(ArrayList<Devices> arrayList, Activity context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
//       MyListBinding myListBinding = DataBindingUtil.setContentView(context, R.layout.listitem);
        myListBinding = DataBindingUtil.inflate(layoutInflater, R.layout.listitem, parent, false);
        return new ViewHolder(myListBinding);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Devices myListViewModel = arrayList.get(position);
        if (myListViewModel.getPlatform() == null) {
            myListBinding.image.setImageDrawable(context.getDrawable(R.drawable.vera_edge_big));
        } else if (myListViewModel.getPlatform().equals("Sercomm G450")) {
            myListBinding.image.setImageDrawable(context.getDrawable(R.drawable.vera_plus_big));
        } else if (myListViewModel.getPlatform().equals("Sercomm G550")) {
            myListBinding.image.setImageDrawable(context.getDrawable(R.drawable.vera_secure_big));
        } else {
            myListBinding.image.setImageDrawable(context.getDrawable(R.drawable.vera_edge_big));
        }
        holder.bind(myListViewModel);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private MyListBinding myListBinding;

        public ViewHolder(@NonNull MyListBinding myListBinding) {
            super(myListBinding.getRoot());
            this.myListBinding = myListBinding;
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                     myListBinding.getMyListViewModel();
                     Intent act;
                    act = new Intent(context, LoginActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("index", getAdapterPosition());
                    act.putExtras(bundle);
                    context.startActivity(act);
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    AlertDialog alertDialog = new AlertDialog.Builder(context).create();
                    alertDialog.setTitle("Alert");
                    alertDialog.setIcon(myListBinding.image.getDrawable());
                    alertDialog.setMessage("Are U Sure? \n You Want To Delete " +
                            "\n" + arrayList.get(getAdapterPosition()).getPlatform());
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Yes",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    Constants.response.getDevices().remove(getAdapterPosition());
                                    notifyItemRemoved(getAdapterPosition());
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();
                    return true;
                }
            });
        }

        public void bind(Devices myli) {
            this.myListBinding.setMyListViewModel(myli);
            myListBinding.executePendingBindings();
        }

        public MyListBinding getMyListBinding() {
            return myListBinding;
        }
    }
}
