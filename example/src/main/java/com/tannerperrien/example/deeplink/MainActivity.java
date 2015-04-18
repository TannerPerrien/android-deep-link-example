/*
 * Copyright (C) 2015 Tanner Perrien
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.tannerperrien.example.deeplink;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

    DeepLinker mDeepLinker = new DeepLinker();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        handleIntent();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        // Override previous intent
        setIntent(intent);

        // Handle new intent
        handleIntent();
    }

    /**
     * Entry point for handling the activity's intent
     */
    private void handleIntent() {
        // Get the intent set on this activity
        Intent intent = getIntent();

        // Get the uri from the intent
        Uri uri = intent.getData();

        // Do not continue if the uri does not exist
        if (uri == null) {
            return;
        }

        // Let the deep linker do its job
        Bundle data = mDeepLinker.buildBundle(uri);
        if (data == null) {
            return;
        }

        // See if we have a valid link
        DeepLinker.Link link = DeepLinker.getLinkFromBundle(data);
        if (link == null) {
            return;
        }

        // Do something with the link
        switch (link) {
            case HOME:
                break;
            case PROFILE:
                break;
            case PROFILE_OTHER:
                break;
            case SETTINGS:
                break;
        }

        String msg;
        long id = DeepLinker.getIdFromBundle(data);
        if (id == 0) {
            msg = String.format("Link: %s", link);
        } else {
            msg = String.format("Link: %s, ID: %s", link, id);
        }

        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }
}
