package preDo;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import org.hibernate.Session;

import model.Word;

public class GetBook {
	public static void run(String s,Session session) throws Throwable {
		BufferedReader br;
		FileInputStream fileInStr = new FileInputStream(s);
		InputStreamReader InSR = new InputStreamReader(fileInStr);
		br = new BufferedReader(InSR);
		String[] before = new String[2];
		before[0] = "";
		before[1] = "";
		while (br.ready()) {
			// before是每次读取上一行后保存的词，因为未确定后面是否还有后续，暂时不存储
			String line = br.readLine();
			before = handleLine(line, before,session);
		}
		//最后要把before里的输出来
		session.save(new Word(0,before[0],before[1],"new"));
		br.close();
	}

	public static String[] handleLine(String line, String[] before,Session session ) {
		String[] res = new String[2];
		for (int i = 0; i < line.length(); i++) {
			if (line.charAt(i) == '.' && line.charAt(i) != '/') {
				while (line.charAt(i) != ' ')
					i--;
				res[0] = line.substring(0, i);
				res[1] = line.substring(i, line.length());
				handle(res, before,session);
				return before;
			} else if (line.charAt(i) != ' ' && !isLetter(line.charAt(i))
					&& line.charAt(i) != '/') {
				res[0] = line.substring(0, i);
				res[1] = line.substring(i, line.length());
				handle(res, before,session);
				return before;
			}
		}
		//如果不符合拆分条件，直接赋值到res
		res[0] = "";
		res[1] = line;
		handle(res, before,session);
		return before;
	}

	public static void handle(String[] res, String[] before,Session session) {
		res[0] = res[0].trim();
		res[1] = res[1].trim();
		if (res[0].length() <= 0 || res[1].length() <= 0)
			if (res[1].length() <= 2 || res[0].length() <= 2) //为单个字母的情况，舍弃				
				;
			 else {//将本行添加到上一个单词的解释后面
				before[1] = before[1] + " " + res[1];
			}

		else {//如果本行是另一个单词，输出上一次缓存的单词
			if (before[0] != "" && before[1] != "") {				
				session.save(new Word(0,before[0],before[1],"new"));
			}
			//更新before
			before[0] = res[0];
			before[1] = res[1];
		}
	}



	public static boolean isLetter(Character c) {
		if (c >= 'a' && c <= 'z')
			return true;
		if (c >= 'A' && c <= 'Z')
			return true;
		return false;
	}
}
