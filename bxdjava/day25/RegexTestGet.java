package com.bxd.day25;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class RegexTestGet {

	public static void main(String[] args) {
		// һ��������ʽ�����ַ���
		String regex = "\\b[a-z]+\\b";
		// Ҫ������ַ���
		String data = "ads asd gfh 1213 afse 2345 4tfed s2 sad 35t";
		// ��������ʽ��װ��Patternģʽ����
		Pattern ptn = Pattern.compile(regex);
		// ��Patternģʽ������Ҫ������ַ��������,��ȡƥ����(����)
		Matcher mtr = ptn.matcher(data);

		// matches�൱�ڵ�����find,����һֱƥ��ֱ����β,������ַ�����β������regex,
		// �򷵻�true,һ���в�ƥ������������false.
		// ��Ҫ��,��ʱ�����ָ���ͣ���ڵ�һ��ƥ��ĵط�.
		System.out.println(mtr.matches());

		while (mtr.find())
			System.out.println(mtr.group());

		System.out.println(mtr.replaceAll("#"));

	}
}
