package com.pdw.wechat;

import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 类描述
 *
 * @author Corgi
 * @date 2021/2/15
 */
public class ChatToolbarFragment extends DialogFragment implements View.OnClickListener {

    private RecyclerView mRecyclerView;
    private ViewGroup mInputLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.view_chat_toolbar, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mInputLayout = view.findViewById(R.id.rl_chat_input);
        mRecyclerView = view.findViewById(R.id.rv_more_list);

        mRecyclerView.setAdapter(new ListAdapter(view.getContext()));

        view.findViewById(R.id.iv_voice).setOnClickListener(this);
        view.findViewById(R.id.iv_emoji).setOnClickListener(this);
        view.findViewById(R.id.iv_more).setOnClickListener(this);
    }

    @Override
    public void onStart() {
        Window window = getDialog().getWindow();
        getDialog().setCanceledOnTouchOutside(false);
        getDialog().setCancelable(false);
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.gravity = Gravity.BOTTOM;
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.dimAmount = 0f;//调整透明度
        window.setAttributes(lp);
        super.onStart();
    }

    private static class ListAdapter extends RecyclerView.Adapter<ViewHolder> {

        private final List<String> data = new ArrayList<>();

        public ListAdapter(Context ctx) {
            String[] array = ctx.getResources().getStringArray(R.array.fun_array);
            Collections.addAll(data, array);
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ViewHolder(View.inflate(parent.getContext(), R.layout.view_fun_icon, null));
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.bind(data.get(position));
        }

        @Override
        public int getItemCount() {
            return data.size();
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.iv_emoji) {

        } else if (view.getId() == R.id.iv_more) {

        } else if (view.getId() == R.id.iv_voice) {

        }
    }

    private static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        void bind(String title) {
            ((TextView) (itemView.findViewById(R.id.tv_title))).setText(title);
            itemView.setOnClickListener(v -> {
                Toast.makeText(itemView.getContext(), "open:" + title, Toast.LENGTH_SHORT).show();
            });
        }
    }
}
