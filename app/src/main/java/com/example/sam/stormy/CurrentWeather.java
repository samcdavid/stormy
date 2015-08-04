package com.example.sam.stormy;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.TimeZone;

/**
 * Created by Sam on 8/3/15.
 */
public class CurrentWeather {
    private String mIcon;
    private long mTime;
    private double mTemperature;
    private double mHumidity;
    private double mPrecipChance;
    private String mSummary;
    private String mTimezone;

    public CurrentWeather(JSONObject jsonData) throws JSONException {
        JSONObject currentJsonData = jsonData.getJSONObject("currently");

        setTimezone(jsonData.getString("timezone"));
        setHumidity(currentJsonData.getDouble("humidity"));
        setTime(currentJsonData.getLong("time"));
        setIcon(currentJsonData.getString("icon"));
        setPrecipChance(currentJsonData.getDouble("precipProbability"));
        setSummary(currentJsonData.getString("summary"));
        setTemperature(currentJsonData.getDouble("temperature"));
    }

    public String getFormattedTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("h:mm a");
        formatter.setTimeZone(TimeZone.getTimeZone(getTimezone()));
        return formatter.format(new Date(getTime() * 1000));
    }

    public int getIconId() {
        HashMap<String, Integer> icons = new HashMap<String, Integer>();

        icons.put("clear-day", R.drawable.clear_day);
        icons.put("clear-night", R.drawable.clear_night);
        icons.put("rain", R.drawable.rain);
        icons.put("snow", R.drawable.snow);
        icons.put("sleet", R.drawable.sleet);
        icons.put("wind", R.drawable.wind);
        icons.put("fog", R.drawable.fog);
        icons.put("cloudy", R.drawable.cloudy);
        icons.put("partly-cloudy-day", R.drawable.partly_cloudy);
        icons.put("partly-cloudy-night", R.drawable.cloudy_night);

        return (icons.get(mIcon) != null) ? icons.get(mIcon) : R.drawable.clear_day;
    }

    public String getTimezone() {
        return mTimezone;
    }

    public void setTimezone(String timezone) {
        mTimezone = timezone;
    }

    public String getIcon() {
        return mIcon;
    }

    public void setIcon(String icon) {
        mIcon = icon;
    }

    public long getTime() {
        return mTime;
    }

    public void setTime(long time) {
        mTime = time;
    }

    public int getTemperature() {
        return (int) Math.round(mTemperature);
    }

    public void setTemperature(double temperature) {
        mTemperature = temperature;
    }

    public double getHumidity() {
        return mHumidity;
    }

    public void setHumidity(double humidity) {
        mHumidity = humidity;
    }

    public int getPrecipChance() {
        return (int) Math.round(mPrecipChance * 100);
    }

    public void setPrecipChance(double precipChance) {
        mPrecipChance = precipChance;
    }

    public String getSummary() {
        return mSummary;
    }

    public void setSummary(String summary) {
        mSummary = summary;
    }
}
