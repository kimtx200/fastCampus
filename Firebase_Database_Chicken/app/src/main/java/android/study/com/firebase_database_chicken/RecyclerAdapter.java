package android.study.com.firebase_database_chicken;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by user on 2016-09-28.
 */
public class RecyclerAdapter<T> extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {


    ArrayList<T> datas;
    int itemLayout;
    Context context;

    //Ani
    public RecyclerAdapter(ArrayList<T> datas, int itemLayout, Context context) {

        this.datas = datas;
        this.itemLayout = itemLayout;
        this.context = context;  //ani
    }

    //View 를 만들어서 홀더에 저장하는 역할
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent, false);
        return new ViewHolder(view);
    }

    //일반 ListAdapter 의 getView를 대체하는 함수
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        //final 넘길땐 final로 바꾸네
        T data = datas.get(position);

        if (data instanceof Branch) {
            Glide.with(context).load(((Branch) data).getLOGO()).into(holder.image);
            holder.name.setText(((Branch) data).getBRANCH());
            holder.price.setText(((Branch) data).getDELIVERY_FEE() + "");

            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DetailActivity.class);
                    intent.putExtra("position", position);
                    context.startActivity(intent);
                }
            });
        } else if (data instanceof Menu) {
            Glide.with(context).load(((Menu) data).getMENU_IMAGE()).into(holder.image);
            holder.name.setText(((Menu) data).getMENU_NAME());
            holder.price.setText(((Menu) data).getMENU_PRICE());

            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DetailActivity.class);
                    intent.putExtra("position", position);
                    context.startActivity(intent);
                }
            });
        }
        //Auto Increment 처럼 data 에 자동적으로 넘버링?을 해 준다.
        holder.itemView.setTag(data);
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name;
        TextView price;
        CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.imageView);
            name = (TextView) itemView.findViewById(R.id.tvName);
            price = (TextView) itemView.findViewById(R.id.tvPrice);

            cardView = (CardView) itemView.findViewById(R.id.cardView);
        }
    }
}