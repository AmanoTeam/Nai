package com.amanoteam.nai;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;
import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;

public class Nai implements IXposedHookLoadPackage {
	
	private static final String dialogsActivityClass = "org.telegram.ui.DialogsActivity";
	private static final String launchActivityClass = "org.telegram.ui.LaunchActivity";
	
	private static final String PACKAGES[] = {
		"com.cool2645.nekolite",
		"com.iMe.android",
		"ellipi.messenger",
		"it.owlgram.android",
		"nekox.messenger",
		"org.aka.messenger",
		"org.forkclient.messenger",
		"org.forkclient.messenger.beta",
		"org.nift4.catox",
		"org.telegram.BifToGram",
		"org.telegram.messenger.beta",
		"org.telegram.messenger.web",
		"org.telegram.plus",
		"ua.itaysonlab.messenger",
		"org.telegram.messenger"
	};
	
	public void handleLoadPackage(final LoadPackageParam loadPackageParam) throws Throwable {
		
		boolean matches = false;
		
		for (String name : PACKAGES) {
			matches = loadPackageParam.packageName.equals(name);
			
			if (matches) {
				break;
			}
		}
		
		if (!matches) {
			return;
		}
		
		findAndHookMethod(dialogsActivityClass, loadPackageParam.classLoader, "isCacheHintVisible", new XC_MethodHook() {
				
				@Override
				protected void beforeHookedMethod(final MethodHookParam param) throws Throwable {
					param.setResult((Object) false);
				}
				
			}
			
		);
		
		findAndHookMethod(launchActivityClass, loadPackageParam.classLoader, "checkFreeDiscSpace", int.class, new XC_MethodHook() {
				
				@Override
				protected void beforeHookedMethod(final MethodHookParam param) throws Throwable {
					param.setResult((Object) null);
				}
				
			}
			
		);
		
	}
	
}
