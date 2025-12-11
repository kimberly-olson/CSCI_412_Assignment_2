package com.example.greetingcard

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.Until
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ExampleUiAutomatorTest {

    private val timeout = 5000L // 5000 milliseconds
    private lateinit var device: UiDevice

    // runs before each test
    @Before
    fun setup() {
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
        device.pressHome() // makes it start from the home screen
    }

    @Test
    fun assignment5TestExplicitStartActivity() {

        val appContext = InstrumentationRegistry.getInstrumentation().context
        val packageManager = appContext.packageManager

        val launchIntentForApp = packageManager.getLaunchIntentForPackage("com.example.greetingcard")
        val appLabel = packageManager.resolveActivity(launchIntentForApp!!, 0)?.loadLabel(packageManager).toString()

        var appIcon = device.wait(Until.findObject(By.desc(appLabel)), timeout)
        if (appIcon == null) {
            appIcon = device.wait(Until.findObject(By.text(appLabel)), timeout)
        }
        assertTrue("Launcher icon not found on home screen", appIcon != null)
        appIcon!!.click()

        device.wait(Until.hasObject(By.pkg("com.example.greetingcard").depth(0)), timeout)

        val explicitStartButton = device.wait(
            Until.findObject(By.text("Start Activity Explicitly")),
            timeout
        )
        assertTrue("Explicit Start Activity Button not found", explicitStartButton != null)
        explicitStartButton!!.click()

        device.wait(Until.hasObject(By.pkg("com.example.greetingcard").depth(0)), timeout)

        val mobileSoftwareEngineeringChallenge = device.wait(
            Until.findObject(By.textContains("Device Fragmentation")),
            timeout
        )
        assertTrue("Challenge text not found in SecondActivity", mobileSoftwareEngineeringChallenge != null)
    }
}
