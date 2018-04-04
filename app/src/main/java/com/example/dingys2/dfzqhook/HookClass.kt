package com.example.dingys2.dfzqhook

import android.R.attr.classLoader


import android.R.attr.classLoader
import de.robv.android.xposed.XposedHelpers.findAndHookMethod
import android.content.Context
import android.util.Log
import de.robv.android.xposed.IXposedHookLoadPackage
import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XposedBridge
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam

class HookClass : IXposedHookLoadPackage {

    @Throws(Throwable::class)
    fun handleLoadPackage(lpparam: LoadPackageParam) {
        // TODO Auto-generated method stub
        // filter the package

        Log.i("package", lpparam.packageName)
        if (!lpparam.packageName.equals("com.eastmoney.android.berlin"))
            return

        findAndHookMethod("com.geili.koudai.util.SafeUtil", lpparam.classLoader, "a", Context::class.java, ByteArray::class.java, String::class.java, object : XC_MethodHook() {
            @Throws(Throwable::class)
            protected fun beforeHookedMethod(param: MethodHookParam) {
                // this will be called before the clock was updated by the original method
                Log.i("Before Hook", "Before Hook")
                val para1 = param.args[1] as ByteArray
                val para2 = param.args[2] as String
                Log.i("para1:", String(para1))
                Log.i("para2:", para2)

            }

            @Throws(Throwable::class)
            protected fun afterHookedMethod(param: MethodHookParam) {
                // this will be called after the clock was updated by the original method
            }
        })

    }

}