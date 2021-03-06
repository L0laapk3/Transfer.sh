/**
 * Copyright 2017 Kartik Arora
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package me.kartikarora.transfersh.applications;

import android.app.Application;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.stetho.Stetho;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.springrole.stats.sdk.StatSDK;

import org.acra.ACRA;
import org.acra.annotation.ReportsCrashes;

import me.kartikarora.transfersh.BuildConfig;
import me.kartikarora.transfersh.R;
import me.kartikarora.transfersh.helpers.UtilsHelper;

/**
 * Developer: chipset
 * Package : me.kartikarora.transfersh.applications
 * Project : ProjectSevenEight
 * Date : 30/6/16
 */

@ReportsCrashes(mailTo = "aawaazdo@kartikarora.me")
public class TransferApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        if (!BuildConfig.DEBUG) {
            ACRA.init(this);
        }
        MobileAds.initialize(getApplicationContext(), getString(R.string.app_id));
        UtilsHelper.getInstance().scheduleServiceJob(TransferApplication.this);
        Stetho.initializeWithDefaults(this);
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        StatSDK.initSDK(this);
    }

    private FirebaseAnalytics mFirebaseAnalytics;

    synchronized public FirebaseAnalytics getDefaultTracker() {
        if (mFirebaseAnalytics == null) {
            mFirebaseAnalytics = FirebaseAnalytics.getInstance(getApplicationContext());
        }
        return mFirebaseAnalytics;
    }
}
