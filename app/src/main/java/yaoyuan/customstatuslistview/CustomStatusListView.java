package yaoyuan.customstatuslistview;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import yaoyuan.customstatuslistview.xlistview.ExListView;

/**
 * 可获取到普通的或者可下拉刷新上拉加载的两种ListView（同时只能获取其中一个）<br>
 * ListView状态有如下四种：showLoading()加载中、showNoData()无数据、showNetError()网络错误、
 * showPullListView()/showListView()有数据<br>
 * <p/>
 * 使用：可当作一个正常的listView进行使用
 * <CustomStatusListView
 * android:id="@+id/status_listview"
 * android:layout_width="match_parent"
 * android:layout_height="match_parent" >
 * </CustomStatusListView>
 * <p/>
 * 代码中：statusListView = (CustomStatusListView) findViewById(R.id.status_listview);
 * history_list = statusListView.getPullListView();
 * history_list.setOnRefreshListener(this);
 * statusListView.showLoading();//要写在获取listView之后，否则不会显示
 * 使用参见：ZoneChatHistoryActivity.class及其布局
 */
public class CustomStatusListView extends LinearLayout {
    private Context context;
    private CustomFrameLayout customFrameLayout;

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public CustomStatusListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }

    public CustomStatusListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    public CustomStatusListView(Context context) {
        super(context);
        this.context = context;
        init();
    }

    private void init() {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.custom_status_listview, this);
        customFrameLayout = (CustomFrameLayout) view.findViewById(R.id.customFrameLayout);
        customFrameLayout.setList(new int[]{R.id.common_ex_listview,
                R.id.common_listview, R.id.common_nodata, R.id.common_loading, R.id.common_net_error});
    }

    /**
     * 获得下拉加载的listView
     *
     * @return
     */
    public ExListView getExListView() {
        ExListView exListView = (ExListView) customFrameLayout.findViewById(R.id.common_ex_listview);
        return exListView;
    }

    /**
     * 获得普通的listView
     *
     * @return
     */
    public ListView getListView() {
        ListView listView = (ListView) customFrameLayout.findViewById(R.id.common_listview);
        return listView;
    }

    /**
     * 显示loading
     */
    public void showLoading() {
        customFrameLayout.show(R.id.common_loading);
    }

    /**
     * 显示网络错误
     */
    public void showNetError() {
        customFrameLayout.show(R.id.common_net_error);
    }

    public void showNetError(String btn_text, OnClickListener listener) {
        customFrameLayout.show(R.id.common_net_error);
        TextView btn = (TextView) findViewById(R.id.error_btn);
        btn.setVisibility(VISIBLE);
        btn.setText(btn_text);
        btn.setOnClickListener(listener);
    }

    /**
     * 显示无数据状态
     */
    public void showNoData() {
        customFrameLayout.show(R.id.common_nodata);
    }

    public void showNoData(String str) {
        customFrameLayout.show(R.id.common_nodata);
        TextView nodata_txt = (TextView) findViewById(R.id.nodata_txt);
        nodata_txt.setText(str);
    }

    public void showNoData(String content, String btn_txt, OnClickListener listener) {
        customFrameLayout.show(R.id.common_nodata);
        TextView nodata_txt = (TextView) findViewById(R.id.nodata_txt);
        nodata_txt.setText(content);
        TextView btn = (TextView) findViewById(R.id.nodata_btn);
        btn.setText(btn_txt);
        btn.setVisibility(VISIBLE);
        btn.setOnClickListener(listener);
    }

    public void showExListView() {
        customFrameLayout.show(R.id.common_ex_listview);
    }

    /**
     * 显示list
     */
    public void showListView() {
        customFrameLayout.show(R.id.common_listview);
    }

    /**
     * 隐藏所有
     */
    public void GoneAll() {
        customFrameLayout.GoneAll();
    }
}
