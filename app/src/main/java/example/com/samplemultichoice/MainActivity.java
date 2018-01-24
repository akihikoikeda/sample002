package example.com.samplemultichoice;

import android.app.AlertDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TextView btnOpenDialog; // タップでDialogを開くボタン

    private ArrayList<Item> allItems;   // 選択肢情報の格納場所
    private Context con = MainActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnOpenDialog = (TextView) findViewById(R.id.btnOpenDialog);

        // 選択肢を用意
        allItems = this.setData();

        // ボタンクリックによるイベント
        btnOpenDialog.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // 選択肢を用意
                DialogAdapter adapter = new DialogAdapter(getApplicationContext(), 0, allItems);
                ListView listView = new ListView(con);
                AlertDialog.Builder dialog = new AlertDialog.Builder(con);

                // 選択肢をタップした時の処理を用意
                listView.setOnItemClickListener(new ListView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        // チェック状態の切り替え
                        allItems.get(position).setFlg(!(allItems.get(position).isFlg()));

                        // チェック画像の表示を切り替え
                        ImageView checkImg = view.findViewById(R.id.agentCheck);
                        if (allItems.get(position).isFlg()) {
                            checkImg.setImageResource(R.drawable.check_on);
                        } else {
                            checkImg.setImageResource(R.drawable.check_off);
                        }
                    }
                });

                // タイトルの用意
                TextView newTitle = new TextView(con);
                newTitle.setText("選択してね");
                newTitle.setTextSize(24.0f);

                // ダイアログ表示
                listView.setAdapter(adapter);
                dialog.setView(listView).create();
                dialog.setPositiveButton("完了", null);
                dialog.setCustomTitle(newTitle);
                dialog.show();
            }
        });
    }

    // 独自アダプタ
    private class DialogAdapter extends ArrayAdapter<Item> {
        private LayoutInflater inflater;

        private DialogAdapter(Context context, int resource, List<Item> objects) {
            super(context, resource, objects);
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public View getView(int position, View v, ViewGroup parent) {
            Item item = getItem(position);
            if (null == v) {
                v = inflater.inflate(R.layout.choice, null);
            }

            // メイン表示項目
            TextView textMain = v.findViewById(R.id.textMain);
            textMain.setText(item.getName());

            // サブ表示項目
            TextView textSub = v.findViewById(R.id.textSub);
            textSub.setText(item.getSub());

            // チェック状態により、チェックボックス画像を切り替える
            ImageView checkImg = v.findViewById(R.id.agentCheck);
            if (item.isFlg()) {
                checkImg.setImageResource(R.drawable.check_on);
            } else {
                checkImg.setImageResource(R.drawable.check_off);
            }

            return v;
        }
    }

    // 選択肢として表示される情報を保有する為のサブクラス
    private class Item {
        private int id      = 0;     // システム識別用
        private String name = "";    // メイン表示項目
        private String sub  = "";    // サブ表示項目
        private boolean flg = false; // チェック状態

        private int getId() {
            return id;
        }
        private void setId(int id) {
            this.id = id;
        }
        private String getName() {
            return name;
        }
        private void setName(String name) {
            this.name = name;
        }
        private String getSub() {
            return sub;
        }
        private void setSub(String sub) {
            this.sub = sub;
        }
        private boolean isFlg() {
            return flg;
        }
        private void setFlg(boolean flg) {
            this.flg = flg;
        }
    }

    // テスト用の選択肢データを作成する
    private ArrayList<Item> setData() {
        Item item;
        ArrayList<Item> testData = new ArrayList<>();

        item = new Item();
        item.setId(1);item.setName("手向　健二");item.setSub("副首都推進局長");item.setFlg(false);
        testData.add(item);
        item = new Item();
        item.setId(2);item.setName("井上　幸浩");item.setSub("副首都推進局理事");item.setFlg(true);
        testData.add(item);
        item = new Item();
        item.setId(3);item.setName("田中　義浩");item.setSub("副首都推進局理事");item.setFlg(false);
        testData.add(item);
        item = new Item();
        item.setId(4);item.setName("水守　勝裕");item.setSub("副首都推進局総務担当部長");item.setFlg(true);
        testData.add(item);
        item = new Item();
        item.setId(5);item.setName("吉村　公秀");item.setSub("副首都推進局総務担当部長");item.setFlg(false);
        testData.add(item);
        item = new Item();
        item.setId(6);item.setName("城間　正樹");item.setSub("副首都推進局総務担当課長");item.setFlg(true);
        testData.add(item);
        item = new Item();
        item.setId(7);item.setName("西岡　大造");item.setSub("副首都推進局総務担当課長");item.setFlg(false);
        testData.add(item);
        item = new Item();
        item.setId(8);item.setName("松井　芳和");item.setSub("副首都推進局副首都企画推進担当部長");item.setFlg(true);
        testData.add(item);
        item = new Item();
        item.setId(9);item.setName("阪田　洋");item.setSub("副首都推進局副首都企画推進担当部長");item.setFlg(false);
        testData.add(item);
        item = new Item();
        item.setId(10);item.setName("川口　祐司");item.setSub("副首都推進局企画担当課長");item.setFlg(true);
        testData.add(item);
        item = new Item();
        item.setId(11);item.setName("橋本　志津子");item.setSub("副首都推進局企画担当課長");item.setFlg(false);
        testData.add(item);
        item = new Item();
        item.setId(12);item.setName("奥村　格一");item.setSub("副首都推進局事業再編担当課長");item.setFlg(true);
        testData.add(item);
        item = new Item();
        item.setId(13);item.setName("橋本　浩典");item.setSub("副首都推進局事業再編担当課長");item.setFlg(false);
        testData.add(item);
        item = new Item();
        item.setId(14);item.setName("井上　智宏");item.setSub("副首都推進局制度企画担当部長");item.setFlg(true);
        testData.add(item);
        item = new Item();
        item.setId(15);item.setName("福岡　弘髙");item.setSub("副首都推進局制度企画担当部長");item.setFlg(false);
        testData.add(item);
        item = new Item();
        item.setId(16);item.setName("榎下　朋浩");item.setSub("副首都推進局制度企画担当課長");item.setFlg(true);
        testData.add(item);
        item = new Item();
        item.setId(17);item.setName("増田　健次");item.setSub("副首都推進局制度企画担当課長");item.setFlg(false);
        testData.add(item);
        item = new Item();
        item.setId(18);item.setName("小林　眞澄");item.setSub("副首都推進局制度企画担当課長");item.setFlg(true);
        testData.add(item);
        item = new Item();
        item.setId(19);item.setName("芦原　武司");item.setSub("副首都推進局財政調整担当課長");item.setFlg(false);
        testData.add(item);
        item = new Item();
        item.setId(20);item.setName("楠見　雅信");item.setSub("副首都推進局財政調整担当課長");item.setFlg(true);
        testData.add(item);
        item = new Item();
        item.setId(21);item.setName("川田　正明");item.setSub("副首都推進局資産債務担当課長");item.setFlg(false);
        testData.add(item);
        item = new Item();
        item.setId(22);item.setName("中川　善之");item.setSub("副首都推進局資産債務担当課長");item.setFlg(true);
        testData.add(item);
        item = new Item();
        item.setId(23);item.setName("川平　眞善");item.setSub("副首都推進局制度調整担当部長");item.setFlg(false);
        testData.add(item);
        item = new Item();
        item.setId(24);item.setName("大下　一志");item.setSub("副首都推進局制度調整担当部長");item.setFlg(true);
        testData.add(item);
        item = new Item();
        item.setId(25);item.setName("黒田　一人");item.setSub("副首都推進局戦略調整担当課長");item.setFlg(false);
        testData.add(item);
        item = new Item();
        item.setId(26);item.setName("水野　英明");item.setSub("副首都推進局戦略調整担当課長");item.setFlg(true);
        testData.add(item);
        item = new Item();
        item.setId(27);item.setName("辻本　誠");item.setSub("副首都推進局事務事業担当課長");item.setFlg(false);
        testData.add(item);
        item = new Item();
        item.setId(28);item.setName("齊藤　満");item.setSub("副首都推進局事務事業担当課長");item.setFlg(true);
        testData.add(item);
        item = new Item();
        item.setId(29);item.setName("谷口　吉宏");item.setSub("副首都推進局組織体制担当課長");item.setFlg(false);
        testData.add(item);
        item = new Item();
        item.setId(30);item.setName("世古口　隆志");item.setSub("副首都推進局組織体制担当課長");item.setFlg(true);
        testData.add(item);

        return testData;
    }
}