package com.lshug.sparkmicrogfix;

import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class Entrance implements IXposedHookLoadPackage {
    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        if (!lpparam.packageName.equals("com.positivegrid.spark"))
            return;
        Class<?> fclass = XposedHelpers.findClass("com.google.firebase.dynamiclinks.PendingDynamicLinkData", lpparam.classLoader);
        findAndHookMethod("com.positivegrid.spark.MainActivity$z", lpparam.classLoader, "a", fclass, new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                param.setResult(new Object());
            }
        });
    }
}
