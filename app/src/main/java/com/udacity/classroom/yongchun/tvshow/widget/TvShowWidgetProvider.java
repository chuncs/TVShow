package com.udacity.classroom.yongchun.tvshow.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.widget.RemoteViews;

import com.udacity.classroom.yongchun.tvshow.R;
import com.udacity.classroom.yongchun.tvshow.ui.DetailActivity;
import com.udacity.classroom.yongchun.tvshow.ui.MainActivity;

public class TvShowWidgetProvider extends AppWidgetProvider {

    public static final String OPEN_ACTION = "com.yongchun.tvshow.OPEN_ACTION";
    public static final String EXTRA_ITEM = "com.yongchun.tvshow.EXTRA_ITEM";

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.tv_show_widget);

        //Create an Intent to launch MainActivity when clicked
        Intent mainIntent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, mainIntent, 0);

        //Widgets allow click handlers to only launch pending intents
        views.setOnClickPendingIntent(R.id.widget_title, pendingIntent);

        //Remote Adapter
        Intent widgetIntent = new Intent(context, TvShowWidgetService.class);
        widgetIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
        widgetIntent.setData(Uri.parse(widgetIntent.toUri(Intent.URI_INTENT_SCHEME)));
        views.setRemoteAdapter(R.id.widget_listView, widgetIntent);
        views.setEmptyView(R.id.widget_listView, R.id.empty_view);

        //Start detail activity
        Intent openIntent = new Intent(context, TvShowWidgetProvider.class);
        openIntent.setAction(OPEN_ACTION);
        PendingIntent openPendingIntent = PendingIntent.getBroadcast(context, 0,
                openIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        views.setPendingIntentTemplate(R.id.widget_listView, openPendingIntent);

        // Instruct the widget manager to update the widget
        appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetId, R.id.widget_listView);
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction() != null && intent.getAction().equals(OPEN_ACTION)) {
            String tvId = intent.getStringExtra(EXTRA_ITEM);
            Intent detailIntent = new Intent(context, DetailActivity.class);

            SharedPreferences.Editor sharedPref = context
                    .getSharedPreferences(context.getString(R.string.preference_file_key), 0)
                    .edit();
            sharedPref.putString(context.getString(R.string.CURRENT_TV_ID), tvId);
            sharedPref.apply();

            detailIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
            context.startActivity(detailIntent);
        }

        super.onReceive(context, intent);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

