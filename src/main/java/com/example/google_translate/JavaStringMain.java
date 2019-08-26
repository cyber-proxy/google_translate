package com.example.google_translate;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import org.json.JSONArray;
import org.json.JSONObject;

public class JavaStringMain {
	
	private static final String data = "{\r\n" + 
			"    'auto': 'Automatic',\r\n" + 
			"    'af': 'Afrikaans',\r\n" + 
			"    'sq': 'Albanian',\r\n" + 
			"    'am': 'Amharic',\r\n" + 
			"    'ar': 'Arabic',\r\n" + 
			"    'hy': 'Armenian',\r\n" + 
			"    'az': 'Azerbaijani',\r\n" + 
			"    'eu': 'Basque',\r\n" + 
			"    'be': 'Belarusian',\r\n" + 
			"    'bn': 'Bengali',\r\n" + 
			"    'bs': 'Bosnian',\r\n" + 
			"    'bg': 'Bulgarian',\r\n" + 
			"    'ca': 'Catalan',\r\n" + 
			"    'ceb': 'Cebuano',\r\n" + 
			"    'ny': 'Chichewa',\r\n" + 
			"    'zh-cn': 'Chinese Simplified',\r\n" + 
			"    'zh-tw': 'Chinese Traditional',\r\n" + 
			"    'co': 'Corsican',\r\n" + 
			"    'hr': 'Croatian',\r\n" + 
			"    'cs': 'Czech',\r\n" + 
			"    'da': 'Danish',\r\n" + 
			"    'nl': 'Dutch',\r\n" + 
			"    'en': 'English',\r\n" + 
			"    'eo': 'Esperanto',\r\n" + 
			"    'et': 'Estonian',\r\n" + 
			"    'tl': 'Filipino',\r\n" + 
			"    'fi': 'Finnish',\r\n" + 
			"    'fr': 'French',\r\n" + 
			"    'fy': 'Frisian',\r\n" + 
			"    'gl': 'Galician',\r\n" + 
			"    'ka': 'Georgian',\r\n" + 
			"    'de': 'German',\r\n" + 
			"    'el': 'Greek',\r\n" + 
			"    'gu': 'Gujarati',\r\n" + 
			"    'ht': 'Haitian Creole',\r\n" + 
			"    'ha': 'Hausa',\r\n" + 
			"    'haw': 'Hawaiian',\r\n" + 
			"    'iw': 'Hebrew',\r\n" + 
			"    'hi': 'Hindi',\r\n" + 
			"    'hmn': 'Hmong',\r\n" + 
			"    'hu': 'Hungarian',\r\n" + 
			"    'is': 'Icelandic',\r\n" + 
			"    'ig': 'Igbo',\r\n" + 
			"    'id': 'Indonesian',\r\n" + 
			"    'ga': 'Irish',\r\n" + 
			"    'it': 'Italian',\r\n" + 
			"    'ja': 'Japanese',\r\n" + 
			"    'jw': 'Javanese',\r\n" + 
			"    'kn': 'Kannada',\r\n" + 
			"    'kk': 'Kazakh',\r\n" + 
			"    'km': 'Khmer',\r\n" + 
			"    'ko': 'Korean',\r\n" + 
			"    'ku': 'Kurdish (Kurmanji)',\r\n" + 
			"    'ky': 'Kyrgyz',\r\n" + 
			"    'lo': 'Lao',\r\n" + 
			"    'la': 'Latin',\r\n" + 
			"    'lv': 'Latvian',\r\n" + 
			"    'lt': 'Lithuanian',\r\n" + 
			"    'lb': 'Luxembourgish',\r\n" + 
			"    'mk': 'Macedonian',\r\n" + 
			"    'mg': 'Malagasy',\r\n" + 
			"    'ms': 'Malay',\r\n" + 
			"    'ml': 'Malayalam',\r\n" + 
			"    'mt': 'Maltese',\r\n" + 
			"    'mi': 'Maori',\r\n" + 
			"    'mr': 'Marathi',\r\n" + 
			"    'mn': 'Mongolian',\r\n" + 
			"    'my': 'Myanmar (Burmese)',\r\n" + 
			"    'ne': 'Nepali',\r\n" + 
			"    'no': 'Norwegian',\r\n" + 
			"    'ps': 'Pashto',\r\n" + 
			"    'fa': 'Persian',\r\n" + 
			"    'pl': 'Polish',\r\n" + 
			"    'pt': 'Portuguese',\r\n" + 
			"    'ma': 'Punjabi',\r\n" + 
			"    'ro': 'Romanian',\r\n" + 
			"    'ru': 'Russian',\r\n" + 
			"    'sm': 'Samoan',\r\n" + 
			"    'gd': 'Scots Gaelic',\r\n" + 
			"    'sr': 'Serbian',\r\n" + 
			"    'st': 'Sesotho',\r\n" + 
			"    'sn': 'Shona',\r\n" + 
			"    'sd': 'Sindhi',\r\n" + 
			"    'si': 'Sinhala',\r\n" + 
			"    'sk': 'Slovak',\r\n" + 
			"    'sl': 'Slovenian',\r\n" + 
			"    'so': 'Somali',\r\n" + 
			"    'es': 'Spanish',\r\n" + 
			"    'su': 'Sundanese',\r\n" + 
			"    'sw': 'Swahili',\r\n" + 
			"    'sv': 'Swedish',\r\n" + 
			"    'tg': 'Tajik',\r\n" + 
			"    'ta': 'Tamil',\r\n" + 
			"    'te': 'Telugu',\r\n" + 
			"    'th': 'Thai',\r\n" + 
			"    'tr': 'Turkish',\r\n" + 
			"    'uk': 'Ukrainian',\r\n" + 
			"    'ur': 'Urdu',\r\n" + 
			"    'uz': 'Uzbek',\r\n" + 
			"    'vi': 'Vietnamese',\r\n" + 
			"    'cy': 'Welsh',\r\n" + 
			"    'xh': 'Xhosa',\r\n" + 
			"    'yi': 'Yiddish',\r\n" + 
			"    'yo': 'Yoruba',\r\n" + 
			"    'zu': 'Zulu'\r\n" + 
			"}";
	
	public static void main(String[] args) throws IOException {
		try {
			JSONObject jsonObject = new JSONObject(data);
			File outFlie = new File("out");
			JSONArray names = jsonObject.names();
			for (int i = 0; i < names.length(); i++) {
				PrintWriter out = new PrintWriter(new File(outFlie, "run-" + jsonObject.optString(names.getString(i)) + "-" + names.getString(i) + ".bat"));
				out.write("java -jar translate.jar " + names.getString(i));
				out.flush();
				out.close();

			}
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}
