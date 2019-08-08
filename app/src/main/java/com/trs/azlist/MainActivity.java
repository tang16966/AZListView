package com.trs.azlist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.trs.azlist2.AZListView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final String names = "亚瑟，鬼谷子，铠，橘右京，项羽，梦奇，白起，赵云，李白，" +
            "韩信，刘备，鲁班七号，墨子，孙膑，周瑜，庄周，廉颇，程咬金，典韦，后羿，扁鹊，李元芳，" +
            "张飞，刘禅，兰陵王，达摩，曹操，钟馗，高渐离，宫本武藏，吕布，嬴政，姜子牙，老夫子，狄仁杰" +
            "，夏侯惇，关羽，哪吒，太乙真人，干将莫邪，成吉思汗，牛魔，百里守约，百里玄策，苏烈，" +
            "黄忠，诸葛亮，东皇太一，杨戬，后羿，孙悟空，张良，韩信，刘邦，马可波罗，娜可露露，" +
            "露娜，妲己，甄姬，虞姬，大乔，小乔，王昭君，貂蝉，芈月，阿珂，花木兰，武则天，不知火舞，" +
            "孙尚香，蔡文姬，安琪拉，钟无艳，女蜗，雅典娜，艾琳";
    private AZListView azListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        azListView = findViewById(R.id.az_list_view);

        String[] split = names.split("，");
        List<User> users = new ArrayList<>();
        for (String s : split) {
            User user = new User();
            user.setName(s);
            users.add(user);
        }
        azListView.setAdapter(new MyAdapter(MainActivity.this,users));
    }


}
