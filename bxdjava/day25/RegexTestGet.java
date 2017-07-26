package com.bxd.day25;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class RegexTestGet {

	public static void main(String[] args) {
		// 一个正则表达式规则字符串
		String regex = "\\b[a-z]+\\b";
		// 要处理的字符串
		String data = "ads asd gfh 1213 afse 2345 4tfed s2 sad 35t";
		// 将正则表达式封装成Pattern模式对象
		Pattern ptn = Pattern.compile(regex);
		// 将Pattern模式对象与要处理的字符串相关联,获取匹配器(引擎)
		Matcher mtr = ptn.matcher(data);

		// matches相当于调用了find,不过一直匹配直到结尾,如果到字符串结尾都满足regex,
		// 则返回true,一旦有不匹配则立即返回false.
		// 重要的,此时引擎的指针会停留在第一次匹配的地方.
		System.out.println(mtr.matches());

		while (mtr.find())
			System.out.println(mtr.group());

		System.out.println(mtr.replaceAll("#"));

	}
}
