package com.fanhero.chatview.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.fanhero.chatview.util.CircleTransformation;
import com.google.gson.JsonObject;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.TreeSet;

import org.appcelerator.titanium.util.TiRHelper;
import org.appcelerator.titanium.util.TiRHelper.ResourceNotFoundException;

public class MessageAdapter extends BaseAdapter {
    private static final int TYPE_COMMENT = 0;
    private static final int TYPE_JOINED = 1;
    private static final int TYPE_MAX_COUNT = TYPE_JOINED + 1;
    private static final int MAX_COMMENT_COUNT = 15;

    private LayoutInflater inflater;

    private List<JsonObject> messages = new ArrayList<>();
    private TreeSet<Integer> joins = new TreeSet<>();

    private Context context;
    private Properties localization;

    public MessageAdapter(Context context, Properties localization) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.context = context;
        this.localization = localization;
    }

    @Override
    public int getCount() {
        return messages.size();
    }

    @Override
    public Object getItem(int position) {
        return messages.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        return TYPE_MAX_COUNT;
    }

    @Override
    public int getItemViewType(int position) {
        return (joins.contains(position)) ? TYPE_JOINED : TYPE_COMMENT;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CommentViewHolder commentHolder;
        JoinedViewHolder joinedHolder;

        int type = getItemViewType(position);
        JsonObject message = (JsonObject) getItem(position);

        try {
        	if (type == TYPE_COMMENT) {
                if (convertView == null) {
    				int item_comment = TiRHelper.getApplicationResource("layout.item_comment");
    				int usernameTextView = TiRHelper.getApplicationResource("id.usernameTextView");
                	int commentTextView = TiRHelper.getApplicationResource("id.commentTextView");
                	int profileImageView = TiRHelper.getApplicationResource("id.profileImageView");
                	
                	convertView = inflater.inflate(item_comment, null);

                    commentHolder = new CommentViewHolder();
                    commentHolder.usernameTextView = (TextView) convertView
                            .findViewById(usernameTextView);
                    commentHolder.commentTextView = (TextView) convertView
                            .findViewById(commentTextView);
                    commentHolder.profileImageView = (ImageView) convertView
                            .findViewById(profileImageView);

                    convertView.setTag(commentHolder);
                } else {
                    commentHolder = (CommentViewHolder) convertView.getTag();
                }
                
                commentHolder.usernameTextView.setText(message.get("username").getAsString());
                commentHolder.commentTextView.setText(message.get("text").getAsString());
                
                int photo = TiRHelper.getApplicationResource("drawable.photo");
                if (message.has("picture") && !message.get("picture").getAsString().equalsIgnoreCase("")) {
                    Picasso.with(context).load(message.get("picture").getAsString())
                        .placeholder(context.getDrawable(photo))
                        .transform(new CircleTransformation())
                        .into(commentHolder.profileImageView);
                } else {
                    Picasso.with(context).load(photo)
                        .transform(new CircleTransformation())
                        .into(commentHolder.profileImageView);
                }
            } else {
                if (convertView == null) {
                	int item_joined = TiRHelper
                			.getApplicationResource("layout.item_joined");
                	int joinedTextViewId = TiRHelper
                			.getApplicationResource("id.joinedTextView");
                	
                    convertView = inflater.inflate(item_joined, null);

                    joinedHolder = new JoinedViewHolder();
                    joinedHolder.joinedTextView = (TextView) convertView
                    		.findViewById(joinedTextViewId);

                    convertView.setTag(joinedHolder);
                } else {
                    joinedHolder = (JoinedViewHolder) convertView.getTag();
                }

                joinedHolder.joinedTextView.setText(String.format("%s %s",
                		message.get("username").getAsString(),
                        localization.getProperty("joined")));
            }

            return convertView;
        } catch (ResourceNotFoundException e) {
        	e.printStackTrace();
        	return null;
        }
    }

    public void addMessage(JsonObject message) {
        if (messages.size() == MAX_COMMENT_COUNT) {
            messages.remove(0);
        }

        messages.add(message);

        if (message.get("type").getAsString().equals("join")) {
            joins.add(messages.size() - 1);
        }

        notifyDataSetChanged();
    }

    // region ViewHolder for comments
    static class CommentViewHolder {
        private TextView usernameTextView;
        private TextView commentTextView;
        private ImageView profileImageView;
    }
    // endregion

    // region ViewHolder for comments
    static class JoinedViewHolder {
        private TextView joinedTextView;
    }
    // endregion
}