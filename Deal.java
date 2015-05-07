import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Deal {

	// 存在的问题：1.连续非字符会出现获取单词中断。

	public static void main(String[] args) {
		String test = "aa bb cc s aa";
		Deal dos = new Deal();
		// 获取文章中的所有单词，重复项不合并。
		List<String> list = dos.takeWord(test);
		// 从单词库中统计每个词的频率。
		HashMap<String, Integer> map = dos.getFrequencyEX(list);
		// 打印出频率库的数据
		Iterator iter = map.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry entry = (Map.Entry) iter.next();
			String key = (String) entry.getKey();
			int fre = (Integer) entry.getValue();
			System.out.print(key + ":" + fre);
			System.out.println();
		}
	}

	/**
	 * 此方法将传进的文件中将文章传递给String变量，并作为结果返回。
	 * 
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public String getString(File file) throws IOException {
		String result =null;
		if (file != null) {
			StringBuffer article = new StringBuffer();
			BufferedReader reader = new BufferedReader(new FileReader(file));
			if (reader != null) {
				String tmp = null;
				while ((tmp = reader.readLine()) != null) {
					article.append(tmp);
				}
			}
			result = article.toString();
		}
		return result;
	}

	/**
	 * 该方法用来取出文章str中所有的单词（计重复），以List形式返回、
	 * 
	 * @param str
	 * @return
	 */
	public List<String> takeWord(String str) {
		int count = 0;
		// 开始组建单词的标记
		boolean wordStart = false;
		// 单词容器，用来往里添加字符的容器
		StringBuffer wordPad = null;
		// 盛放单词的容器
		List<String> list = null;
		if (str != null) {
			list = new ArrayList<String>();
			char[] chars = str.toCharArray();
			int strLen = chars.length;
			for (int i = 0; i < strLen; i++) {
				char currentChar = chars[i];
				// 如果【开始组建单词】为false，遇到第一个字符，将【开始组建单词】设置为true，
				// 建立一个StringBuffer依次追加，直到遇到非字符，将【开始组建单词】设置为false，并将Stringbuffer添加到List中
				// 然后释放StringBuffer。
				if (Character.isLetter(currentChar)) {
					if (!wordStart) {
						wordPad = new StringBuffer();
						wordStart = true;
					}
					wordPad.append(currentChar);
				}
				if (!Character.isLetter(currentChar) || i == strLen - 1) {
					if (!wordStart) {
						break;
					}
					wordStart = false;
					list.add(wordPad.toString());
					wordPad = null;
				}
			}
		}
		return list;
	}

	/**
	 * 该方法用来从【单词库】List中统计单词的频率，并放在HashMap<单词，频率>中。
	 * 
	 * @param list
	 * @return
	 */
	public HashMap<String, Integer> getFrequency(List<String> list) {
		HashMap<String, Integer> map = null;
		if (list != null) {
			map = new HashMap<String, Integer>();
			boolean isMatching = false;
			// 循环遍历【单词库】中每一个单词
			for (String out : list) {
				Iterator iter = map.entrySet().iterator();
				// 【单词库】的每个单词与【频率库】中的单词比较，如果相同则将该单词的【频率】+1，否则向【频率库】中添加新的新的记录
				while (iter.hasNext()) {
					Map.Entry entry = (Map.Entry) iter.next();
					String in = (String) entry.getKey();
					// 如果有匹配，将该记录的【频率】+1,并跳出迭代。
					if (in.equals(out)) {
						int fre = (Integer) entry.getValue();
						int finalFre = fre + 1;
						map.put(out, finalFre);
						// 表示该轮单词已成功匹配。
						isMatching = true;
						break;
					}
				}
				// 如果单词未匹配，则添加新的记录
				if (!isMatching) {
					map.put(out, 1);
				}
				// 初始化匹配标记
				isMatching = false;
			}
		}
		return map;
	}

	
	public HashMap<String, Integer> getFrequencyEX(List<String> list) {
		HashMap<String, Integer> map = null;
		if (list != null) {
			map = new HashMap<String, Integer>();
			boolean isMatching = false;
			// 循环遍历【单词库】中每一个单词
			for (String out : list) {
				// 【单词库】的每个单词与【频率库】中的单词比较，如果相同则将该单词的【频率】+1，否则向【频率库】中添加新的新的记录
					// 如果有匹配，将该记录的【频率】+1,并跳出迭代。
					if (map.containsKey(out)) {
						int fre = map.get(out);
						int finalFre = fre + 1;
						map.put(out, finalFre);
						// 表示该轮单词已成功匹配。
						isMatching = true;
						break;
					}
				// 如果单词未匹配，则添加新的记录
				if (!isMatching) {
					map.put(out, 1);
				}
				// 初始化匹配标记
				isMatching = false;
			}
		}
		return map;
	}

}
