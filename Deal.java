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

	// ���ڵ����⣺1.�������ַ�����ֻ�ȡ�����жϡ�

	public static void main(String[] args) {
		String test = "aa bb cc s aa";
		Deal dos = new Deal();
		// ��ȡ�����е����е��ʣ��ظ���ϲ���
		List<String> list = dos.takeWord(test);
		// �ӵ��ʿ���ͳ��ÿ���ʵ�Ƶ�ʡ�
		HashMap<String, Integer> map = dos.getFrequencyEX(list);
		// ��ӡ��Ƶ�ʿ������
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
	 * �˷������������ļ��н����´��ݸ�String����������Ϊ������ء�
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
	 * �÷�������ȡ������str�����еĵ��ʣ����ظ�������List��ʽ���ء�
	 * 
	 * @param str
	 * @return
	 */
	public List<String> takeWord(String str) {
		int count = 0;
		// ��ʼ�齨���ʵı��
		boolean wordStart = false;
		// ����������������������ַ�������
		StringBuffer wordPad = null;
		// ʢ�ŵ��ʵ�����
		List<String> list = null;
		if (str != null) {
			list = new ArrayList<String>();
			char[] chars = str.toCharArray();
			int strLen = chars.length;
			for (int i = 0; i < strLen; i++) {
				char currentChar = chars[i];
				// �������ʼ�齨���ʡ�Ϊfalse��������һ���ַ���������ʼ�齨���ʡ�����Ϊtrue��
				// ����һ��StringBuffer����׷�ӣ�ֱ���������ַ���������ʼ�齨���ʡ�����Ϊfalse������Stringbuffer��ӵ�List��
				// Ȼ���ͷ�StringBuffer��
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
	 * �÷��������ӡ����ʿ⡿List��ͳ�Ƶ��ʵ�Ƶ�ʣ�������HashMap<���ʣ�Ƶ��>�С�
	 * 
	 * @param list
	 * @return
	 */
	public HashMap<String, Integer> getFrequency(List<String> list) {
		HashMap<String, Integer> map = null;
		if (list != null) {
			map = new HashMap<String, Integer>();
			boolean isMatching = false;
			// ѭ�����������ʿ⡿��ÿһ������
			for (String out : list) {
				Iterator iter = map.entrySet().iterator();
				// �����ʿ⡿��ÿ�������롾Ƶ�ʿ⡿�еĵ��ʱȽϣ������ͬ�򽫸õ��ʵġ�Ƶ�ʡ�+1��������Ƶ�ʿ⡿������µ��µļ�¼
				while (iter.hasNext()) {
					Map.Entry entry = (Map.Entry) iter.next();
					String in = (String) entry.getKey();
					// �����ƥ�䣬���ü�¼�ġ�Ƶ�ʡ�+1,������������
					if (in.equals(out)) {
						int fre = (Integer) entry.getValue();
						int finalFre = fre + 1;
						map.put(out, finalFre);
						// ��ʾ���ֵ����ѳɹ�ƥ�䡣
						isMatching = true;
						break;
					}
				}
				// �������δƥ�䣬������µļ�¼
				if (!isMatching) {
					map.put(out, 1);
				}
				// ��ʼ��ƥ����
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
			// ѭ�����������ʿ⡿��ÿһ������
			for (String out : list) {
				// �����ʿ⡿��ÿ�������롾Ƶ�ʿ⡿�еĵ��ʱȽϣ������ͬ�򽫸õ��ʵġ�Ƶ�ʡ�+1��������Ƶ�ʿ⡿������µ��µļ�¼
					// �����ƥ�䣬���ü�¼�ġ�Ƶ�ʡ�+1,������������
					if (map.containsKey(out)) {
						int fre = map.get(out);
						int finalFre = fre + 1;
						map.put(out, finalFre);
						// ��ʾ���ֵ����ѳɹ�ƥ�䡣
						isMatching = true;
						break;
					}
				// �������δƥ�䣬������µļ�¼
				if (!isMatching) {
					map.put(out, 1);
				}
				// ��ʼ��ƥ����
				isMatching = false;
			}
		}
		return map;
	}

}
