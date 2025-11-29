package org.labcluster.crm.android.widget

import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_SINGLE_TOP
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.Image
import androidx.glance.ImageProvider
import androidx.glance.action.clickable
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.action.actionStartActivity
import androidx.glance.appwidget.cornerRadius
import androidx.glance.appwidget.provideContent
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Box
import androidx.glance.layout.Row
import androidx.glance.layout.Spacer
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.padding
import androidx.glance.layout.size
import org.labcluster.crm.android.MainActivity
import org.labcluster.crm.android.R


class Widget : GlanceAppWidget() {

    override suspend fun provideGlance(context: Context, id: GlanceId) {

        val intentCalendar = Intent(context, MainActivity::class.java)
        intentCalendar.putExtra("screen", "calendar")
        intentCalendar.flags += FLAG_ACTIVITY_SINGLE_TOP

        val intentGroups = Intent(context, MainActivity::class.java)
        intentGroups.putExtra("screen", "groups")
        intentGroups.flags += FLAG_ACTIVITY_SINGLE_TOP

        provideContent {
            Box(
                modifier = GlanceModifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                WidgetContent(intentCalendar, intentGroups)
            }
        }
    }

    @Composable
    fun WidgetContent(intentCalendar: Intent, intentGroups: Intent) {

        Row(
            modifier = GlanceModifier.fillMaxSize().background(Color.Black.copy(alpha = .3f)),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(GlanceModifier.defaultWeight())

            Image(
                provider = ImageProvider(R.drawable.calendar),
                contentDescription = "",
                modifier = GlanceModifier
                    .size(70.dp)
                    .padding(20.dp)
                    .background(Color.Black)
                    .cornerRadius(16.dp)
                    .clickable(actionStartActivity(intentCalendar))
            )

            Spacer(GlanceModifier.defaultWeight())

            Image(
                provider = ImageProvider(R.drawable.groups),
                contentDescription = "",
                modifier = GlanceModifier
                    .size(70.dp)
                    .padding(20.dp)
                    .background(Color.Black)
                    .cornerRadius(16.dp)
                    .clickable(actionStartActivity(intentGroups))
            )

            Spacer(GlanceModifier.defaultWeight())
        }
    }
}