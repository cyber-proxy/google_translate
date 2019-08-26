package com.example.google_translate;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONArray;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.ScriptableObject;

public class GoogleTranslateHelper {

	private static final String URL = "https://translate.google.com";
	private static final String SINGLE_URL = "https://translate.google.com/translate_a/single";
	private static final String TRANSLATE_URL = SINGLE_URL
			+ "?client=webapp&sl=%s&tl=%s&hl=%s&dt=at&dt=at&dt=at&dt=at&dt=at&dt=at&dt=at&"
			+ "dt=at&dt=at&dt=at&ie=UTF-8&oe=UTF-8&otf=1&ssel=0&tsel=0&kc=7&q=%s";

	public static final Pattern PATTERN = Pattern.compile("tkk:'([0-9]+\\.[0-9]+)'");// tkk:'431089.688004431'

	private static final String TKK = "TKK";

	private Map<String, String> mConfigs;
	private ScriptableObject mScriptableObject;

	public GoogleTranslateHelper() {
		this.mConfigs = new HashMap<String, String>();
		Context context = Context.enter();
		this.mScriptableObject = context.initStandardObjects();
		try {
			context.evaluateReader(this.mScriptableObject, new FileReader("D:\\securitycommonsdk\\google_translate\\token.js"), "D:\\securitycommonsdk\\google_translate\\token.js", 0, null);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			Context.exit();
		}
	}

	public String translate(String text, String form, String to) throws IOException {
		try {
			String jsonData = HttpUtil.get(getUrl(text, form, to));
			System.out.println("data:" + jsonData);
			JSONArray array = new JSONArray(jsonData);
			return array.getJSONArray(5).getJSONArray(0).getJSONArray(2).getJSONArray(0).getString(0);
		} catch (Throwable e) {
			updateTKK(true);
			return text;
		}
	}

	private String getUrl(String text, String form, String to) {
		String url = String.format(TRANSLATE_URL, form, to, to, text);
		return url + "" + getToken(text);
	}

	public void updateTKK(boolean isForce) {
		if (!isForce && mConfigs.containsKey(TKK)) {
			return;
		}
		try {
			String html = HttpUtil.get(URL);
			Matcher matcher = PATTERN.matcher(html);
			if (matcher.find()) {
				mConfigs.put(TKK, matcher.group(1));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public String getToken(String text) {
		updateTKK(false);
		try {
			Context context = Context.enter();
			return String.valueOf(context.evaluateString(mScriptableObject,
					String.format("sM(\"%s\",\"%s\");", text, getTKK()), null, 0, null));
		} finally {
			Context.exit();
		}

	}

	private String getTKK() {
		return mConfigs.get(TKK);
	}

}
