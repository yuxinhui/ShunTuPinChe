package com.jinfukeji.shuntupinche.weather;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.jinfukeji.shuntupinche.R;
import com.jinfukeji.shuntupinche.weather.entity.City;
import com.jinfukeji.shuntupinche.weather.entity.District;
import com.jinfukeji.shuntupinche.weather.entity.Province;
import com.jinfukeji.shuntupinche.weather.json.entity.Result;
import com.jinfukeji.shuntupinche.weather.json.entity.Weather;
import com.jinfukeji.shuntupinche.weather.json.entity.Weather_data;
import com.jinfukeji.shuntupinche.weather.tool.HttpUtils;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.List;

/**
 * Created by "于志渊"
 * 时间:"16:14"
 * 包名:com.jinfukeji.shuntupinche.activity
 * 描述:个人中心天气查询界面
 */
public class MyTianQiActivity extends AppCompatActivity{
    private Spinner sp_province,sp_city,sp_district;
    private int currentPro;
    private ArrayAdapter<Province> provinceAdapter;
    private ArrayAdapter<City> cityAdapter;
    private ArrayAdapter<District> districtAdapter;
    private List<Province> provinces;

    private TextView tvCity,tvPM25,tvDate,tvweek1,tvwea1,tvwind1,tvtemper1,tvweek2,tvwea2,tvwind2,tvtemper2,tvweek3,tvwea3,tvwind3,tvtemper3;
    private ImageView ivpic11,ivpic12,ivpic21,ivpic22,ivpic31,ivpic32;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tianqi);
        sp_province = (Spinner) findViewById(R.id.spinner1);
        sp_city = (Spinner) findViewById(R.id.spinner2);
        sp_district = (Spinner) findViewById(R.id.spinner3);
        tvCity = (TextView) findViewById(R.id.tvCity);
        tvPM25 = (TextView) findViewById(R.id.tvPM25);
        tvDate = (TextView) findViewById(R.id.tvDate);

        //第二个城市
        ivpic21 = (ImageView) findViewById(R.id.ivpic21);
        ivpic22 = (ImageView) findViewById(R.id.ivpic22);

        tvweek2 = (TextView) findViewById(R.id.tvweek2);
        tvwea2 = (TextView) findViewById(R.id.tvwea2);
        tvwind2 = (TextView) findViewById(R.id.tvwind2);
        tvtemper2 = (TextView) findViewById(R.id.tvtemper2);

        // 第三个城市
        ivpic31 = (ImageView) findViewById(R.id.ivpic31);
        ivpic32 = (ImageView) findViewById(R.id.ivpic32);

        tvweek3 = (TextView) findViewById(R.id.tvweek3);
        tvwea3 = (TextView) findViewById(R.id.tvwea3);
        tvwind3 = (TextView) findViewById(R.id.tvwind3);
        tvtemper3 = (TextView) findViewById(R.id.tvtemper3);

        // 第一个城市
        ivpic11 = (ImageView) findViewById(R.id.ivpic11);
        ivpic12 = (ImageView) findViewById(R.id.ivpic12);

        tvweek1 = (TextView) findViewById(R.id.tvweek1);
        tvwea1 = (TextView) findViewById(R.id.tvwea1);
        tvwind1 = (TextView) findViewById(R.id.tvwind1);
        tvtemper1 = (TextView) findViewById(R.id.tvtemper1);

        // 获取到地区信息
        try {
            provinces = HttpUtils.getProvinces(this);
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        }

        // adapter，填充信息
        provinceAdapter = new ArrayAdapter<Province>(this,
                android.R.layout.simple_spinner_item, android.R.id.text1,
                provinces);
        provinceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_province.setAdapter(provinceAdapter);

        cityAdapter = new ArrayAdapter<City>(this,
                android.R.layout.simple_spinner_item, android.R.id.text1,
                provinces.get(0).getCitys());
        cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_city.setAdapter(cityAdapter);

        districtAdapter = new ArrayAdapter<District>(this,
                android.R.layout.simple_spinner_item, android.R.id.text1,
                provinces.get(0).getCitys().get(0).getDisList());
        districtAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_district.setAdapter(districtAdapter);

        // 当选择省份时，城市和地方列表会变化
        sp_province.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                currentPro = position;
                cityAdapter = new ArrayAdapter<City>(MyTianQiActivity.this,
                        android.R.layout.simple_spinner_item,
                        android.R.id.text1, provinces.get(position).getCitys());
                cityAdapter
                        .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                sp_city.setAdapter(cityAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        // 当选择城市时，地方列表会变化
        sp_city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                districtAdapter = new ArrayAdapter<District>(MyTianQiActivity.this,
                        android.R.layout.simple_spinner_item,
                        android.R.id.text1, provinces.get(currentPro)
                        .getCitys().get(position).getDisList());
                districtAdapter
                        .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                sp_district.setAdapter(districtAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        // 当选择地方时，显示具体的天气情况
        sp_district.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                // 选择的城市
                District dis = districtAdapter.getItem(position);
                // Log.i("i", dis.getName());
                new WeatherAsyncTask().execute(dis.getName());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
    // 异步类，获取天气数据
    class WeatherAsyncTask extends AsyncTask<String, Void, Weather>{

        @Override
        protected Weather doInBackground(String... params) {
            String url = HttpUtils.getURl(params[0]);
            String jsonStr = HttpUtils.getJsonStr(url);
            Weather weather = HttpUtils.fromJson(jsonStr);
            Result r = weather.getResults().get(0);
			/*List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
			list = HttpUtils.toListMap(r);*/
            for(int i = 0;i<3;i++){
                Weather_data w = r.getWeather_data().get(i);
                w.setDayPicture(HttpUtils.getImage(w.getDayPictureUrl()));
                w.setNightPicture(HttpUtils.getImage(w.getNightPictureUrl()));
            }
            return weather;
        }

        @Override
        protected void onPostExecute(Weather result) {
            Result res = result.getResults().get(0);
            Weather_data wa = res.getWeather_data().get(0);
            // System.out.println(res.getWeather_data());
            tvCity.setText("城市:" + res.getCurrentCity());
            String pm2_5 = "".equals(res.getPm25()) ? "75" : res.getPm25();
//			Log.i("i",res.getPm25()+"wwwww");
            tvPM25.setText("PM2.5:" + pm2_5);
            tvDate.setText("日期:" + result.getDate());
            // 应该为从网络上获取到的
//			ivpic11.setImageResource(R.drawable.d00);
//			ivpic12.setImageResource(R.drawable.d01);
            ivpic11.setImageBitmap(wa.getDayPicture());
            ivpic12.setImageBitmap(wa.getNightPicture());
            String str = wa.getDate();
            tvweek1.setText(str.substring(0, 2));
            tvwea1.setText(wa.getWeather());
            tvwind1.setText(wa.getWind());
            tvtemper1.setText(str.substring(14, str.length()-1));
            wa = res.getWeather_data().get(1);
            // System.out.println(wa2);

            tvtemper2.setText(wa.getTemperature());
            ivpic21.setImageBitmap(wa.getDayPicture());
            ivpic22.setImageBitmap(wa.getNightPicture());
            tvweek2.setText(wa.getDate());
            tvwea2.setText(wa.getWeather());
            tvwind2.setText(wa.getWind());
            tvtemper2.setText(wa.getTemperature());

            wa = res.getWeather_data().get(2);
            // System.out.println(wa4);
            ivpic31.setImageBitmap(wa.getDayPicture());
            ivpic32.setImageBitmap(wa.getNightPicture());
            tvweek3.setText(wa.getDate());
            tvwea3.setText(wa.getWeather());
            tvwind3.setText(wa.getWind());
            tvtemper3.setText(wa.getTemperature());
        }
    }
}
