package com.pdw.wechat;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.pdw.wechat.entity.Message;
import com.pdw.wechat.entity.TextMessage;
import com.pdw.wechat.entity.User;
import com.pdw.wechat.view.ChatToolbarView;
import com.pdw.wechat.view.KeyboardWatchLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * 类描述
 *
 * @author Corgi
 * @date 2021/2/16
 */
public class ChatFragment extends Fragment implements MessageSender, KeyboardWatchLayout.KeyboardStateChangedListener {
    private RecyclerView mChatListView;
    private BaseAdapter mAdapter;

    private ChatToolbarView mChatToolView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return View.inflate(container.getContext(), R.layout.fragment_chat, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mChatListView = view.findViewById(R.id.rv_chat_list);
        mChatListView.addItemDecoration(new ChatListDecoration(getContext()));
        mAdapter = new BaseAdapter(new ArrayList<>());
        mChatListView.setAdapter(mAdapter);

        mChatToolView = view.findViewById(R.id.chat_tool_view);
        mChatToolView.setMessageSender(this);
    }

    @Override
    public void onResume() {
        super.onResume();

        for (int i = 0; i < 10; i++) {
            TextMessage message = new TextMessage();
            message.setId(System.nanoTime());
            message.setFrom(i % 3 == 0 ? User.ME : i);
            message.setTo(User.ME);
            message.setCtx(getContext());
            message.setText("这是一条来自demo的消息");
            mAdapter.add(message);
        }

//        showToolBar();
    }

    @Override
    public boolean send(Message message) {
        message.setCtx(getContext());
        mAdapter.add(message);
        mChatListView.scrollToPosition(mAdapter.getItemCount() - 1);
        return false;
    }

    @Override
    public void onKeyboardStateChanged(boolean active, int keyboardHeight) {
        mChatToolView.onKeyboardStateChanged(active, keyboardHeight);
    }

    /******************************** 内部类  *******************************/

    private static class BaseAdapter extends RecyclerView.Adapter<ViewHolder> {

        private final List<Message> messageList;

        public BaseAdapter(List<Message> messageList) {
            this.messageList = messageList;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ViewHolder(parent.getContext());
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.bind(messageList.get(position));
        }

        @Override
        public int getItemCount() {
            return messageList.size();
        }

        void add(Message message) {
            messageList.add(message);
            notifyItemInserted(messageList.size() - 1);
        }
    }

    private static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(Context ctx) {
            super(View.inflate(ctx, R.layout.view_chat_item_container, null));
        }

        void bind(Message message) {
            ViewGroup parent = (ViewGroup) itemView;
            parent.removeAllViews();
            parent.addView(message.getViewHandler().getView(message));
        }
    }
}
