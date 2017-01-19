package com.bxd.day24;

import java.io.BufferedInputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class URLTest2 {

	public static void main(String[] args) {
		URL url = null;
		try {
			// url = new URL("https://www.baidu.com");
			url = new URL("http://0.0.0.0:10101");
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (url == null)
			return;

		try {
			URLConnection urlcon = url.openConnection();
			urlcon.connect();

			Map<String, List<String>> map = urlcon.getHeaderFields();
			Set<Map.Entry<String, List<String>>> set = map.entrySet();
			Iterator<Entry<String, List<String>>> it = set.iterator();
			while (it.hasNext()) {
				Map.Entry<String, List<String>> maps = it.next();
				System.out.println(maps.getKey() + ": " + maps.getValue().toString());
			}

			System.out.println();

			BufferedInputStream bufis = new BufferedInputStream(urlcon.getInputStream());
			byte[] buf = new byte[1024 * 64];
			while ((bufis.read(buf)) != -1)
				System.out.println(new String(buf, "GBK"));// Baidu is UTF-8

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
