package org.odk.collect.analytics

import android.app.Application
import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.crashlytics.FirebaseCrashlytics

class BlockableFirebaseAnalytics(application: Application) : Analytics {
    private val firebaseAnalytics = FirebaseAnalytics.getInstance(application)
    private val crashlytics = FirebaseCrashlytics.getInstance()

    override fun logEvent(event: String) {
        firebaseAnalytics.logEvent(event, null)
    }

    override fun logEventWithParam(event: String, key: String, value: String) {
        val bundle = Bundle()
        bundle.putString(key, value)
        firebaseAnalytics.logEvent(event, bundle)
    }

    override fun logNonFatal(throwable: Throwable) {
        crashlytics.recordException(throwable)
    }

    override fun logMessage(message: String) {
        crashlytics.log(message)
    }

    override fun setAnalyticsCollectionEnabled(isAnalyticsEnabled: Boolean) {
        firebaseAnalytics.setAnalyticsCollectionEnabled(isAnalyticsEnabled)
        crashlytics.setCrashlyticsCollectionEnabled(isAnalyticsEnabled)
    }

    override fun setUserProperty(name: String, value: String) {
        firebaseAnalytics.setUserProperty(name, value)
    }
}
