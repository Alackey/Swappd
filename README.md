# Swappd

This is a base app for Swappd that shows button clicks, navigating to an activity, and navigating back.

## Map
Instructions:
1. Get an API key from here: https://developers.google.com/maps/documentation/android-api/config
2. Put the API key in the file "$HOME/.gradle/gradle.properties" on your computer with the syntax: GOOGLE_MAPS_API_KEY="YOUR_API_KEY"
3. Verify you have Google Repository in the SDK Manage > SDK Tools installed. More details at the top of this page: https://developers.google.com/android/guides/setup

Configurations I made:
* Added compile 'com.google.android.gms:play-services-maps:10.2.0' to build.gradle in app folder
* How you securely add API key's so they aren't in Github example:
    * Put the API key in the file "$HOME/.gradle/gradle.properties" on your computer with the syntax: GOOGLE_MAPS_API_KEY="YOUR_API_KEY"
    * Add this to build.gradle in app folder: resValue "string", "GOOGLE_MAPS_API_KEY", GOOGLE_MAPS_API_KEY
    * Added this to to AndroidManifest.xml:
        ```XML
        <meta-data
            android:name="com.google.android.geo.API_KEY"
            android:value="@string/GOOGLE_MAPS_API_KEY" />
        ```

## Topics
* Navigating to activity
* Navigating back from activity
* Button click events
* Logging
* "Best" practice with:
  * XML variables in XML
  * Using strings from strings.xml in layout
