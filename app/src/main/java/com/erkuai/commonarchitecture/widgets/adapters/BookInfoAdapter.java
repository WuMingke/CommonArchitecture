package com.erkuai.commonarchitecture.widgets.adapters;

import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.erkuai.commonarchitecture.R;
import com.erkuai.commonarchitecture.bean.BookInfo;
import com.erkuai.commonarchitecture.utils.ImageLoader;

import java.util.List;
import java.util.logging.Handler;

/**
 * Created by Administrator on 2019/8/9.
 */

public class BookInfoAdapter extends BaseQuickAdapter<BookInfo.DataBean, BaseViewHolder> {

    public BookInfoAdapter(@Nullable List<BookInfo.DataBean> data) {
        super(R.layout.adapter_book_info, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BookInfo.DataBean item) {

        ImageView cover = helper.getView(R.id.cover);
        ImageLoader.loadImage(item.getCover(), cover);

        TextView title = helper.getView(R.id.title);
        title.setText(item.getTitle());

        TextView author = helper.getView(R.id.author);
        author.setText(item.getAuthor());
    }
}
