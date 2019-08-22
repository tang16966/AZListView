package com.trs.plug;

import android.graphics.Color;
import android.os.Bundle;

import android.os.Handler;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.trs.azlist.R;

import com.trs.imagecarousel.DisplayType;
import com.trs.imagecarousel.ImageCarouselView;

import java.util.ArrayList;
import java.util.List;

/**
 * 轮播图使用
 */
public class CarouseActivity extends AppCompatActivity {
    private ImageCarouselView carouselView;
    private SwipeRefreshLayout swipeRefresh;
    private Handler handler = new Handler();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carouse);
        carouselView = findViewById(R.id.carousel_view);
        swipeRefresh = findViewById(R.id.swipe_refresh);
        initCarouse();
        initSwipeRefresh();

    }


    private void initSwipeRefresh() {
        swipeRefresh.setColorSchemeColors(Color.RED);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefresh.setRefreshing(false);
                    }
                },2000);
            }
        });

    }

    private void initCarouse() {
        final List<String> data = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            switch (i) {
                case 0:
                    data.add("https://pics2.baidu.com/feed/3b87e950352ac65c13b8ba36dedefd1492138ae3.png?token=502cadeb091ebd2d22f8980dec41b81d&s=58F309D619638E4F1AFF39830300708C");
                    break;
                case 1:
                    data.add("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1287295149,2990168467&fm=26&gp=0.jpg");
                    break;
                case 2:
                    data.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1566360698873&di=5862491c5367c01033460248b572078e&imgtype=0&src=http%3A%2F%2Fi2.hdslb.com%2Fbfs%2Farchive%2Ff7c0aab0369ab1319d176fcc8dc4a193c650ea74.jpg");
                    break;
                case 3:
                    data.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1566368968011&di=66e2633c960f2d8e203eb2b055246c91&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201804%2F25%2F20180425214412_kxdnu.jpg");
                    break;
                case 4:
                    data.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1566385855678&di=91b9edb7d2fab78394275cc48fd64ff3&imgtype=0&src=http%3A%2F%2Fp1.pstatp.com%2Flarge%2Fpgc-image%2F948b049950354e949b115205ceda925c");
                    break;
                case 5:
                    data.add("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=2139761842,1383333666&fm=26&gp=0.jpg");
                    break;
            }

        }
        carouselView.setImage(data)
                .setStateHeight(30)
                .setDisplayType(DisplayType.FILLET)
                .setStateBackgroudColor(Color.parseColor("#88cccccc"))
                .setStateMakerColor(Color.RED, Color.WHITE)
                .setStateTitles(data)
                .setPadding(10)
                .autoPaly(5000);
        carouselView.setOnItemClick(new ImageCarouselView.OnItemClick() {
            @Override
            public void onClick(int id) {
                Toast.makeText(CarouseActivity.this, data.get(id), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        carouselView.onDestroy();
    }
}
