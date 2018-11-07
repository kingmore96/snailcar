import java.text.DecimalFormat;


/**
 * 编码工具类
 * 
 * @see BCD与十进制的转换
 */
public class BCDCode {

	/**
	 * @功能:测试用例
	 * @参数: 参数
	 */
	public static void main(String[] args) {
//		byte[] b = str2Bcd("2232.556");
//		for (byte c : b) {
//			System.out.print(c);
//		}
//		System.out.println();
//		System.out.println(Conver.bytes2HexString(b));
//		System.out.println(bcd2Str(b));
//		System.out.println();
//		b = str2Bcd("11405.281");
//		for (byte c : b) {
//			System.out.print(c);
//		}
//		System.out.println();
//		System.out.println(Conver.bytes2HexString(b));
//		System.out.println(bcd2Str(b));


		System.out.println(bcd2Str(str2Bcd("2222.534")));
	}

	/**
	 * 采用压缩BCD编码，但最高位是符号位。“正”表示“北纬”，“负”表示“南纬, 或“正”表示“东经”，“负”表示“西经”；
	 */
	public static String bcd2Str(byte[] bytes) {
		String data = bcd2str(bytes);
		Integer l = Integer.valueOf(data);
		StringBuilder sb = new StringBuilder();
		sb.append(l/1000);
		sb.append(".");
		sb.append(l%1000);
		return sb.toString();
	}

	/**
	 * @param asc  
	 * 采用压缩BCD编码，但最高位是符号位。
	 * “正”表示“北纬”，“负”表示“南纬, 或“正”表示“东经”，“负”表示“西经”；
	 * 表示范围：000.000分——89度59.999分 -> 
	 */
	public static byte[] str2Bcd(String asc) {
		DecimalFormat df = new DecimalFormat("#.000");
		asc = df.format(Double.valueOf(asc));
		asc = asc.replace(".", "");
	     
		return str2bcd(asc);
	}

	/**
	 * <编码> <数字字符串编成BCD格式字节数组>
	 * 
	 * @param bcd
	 *            数字字符串
	 * @return
	 */
	public static byte[] str2bcd(String bcd) {
		if (bcd == null || bcd.isEmpty()) {
			return null;
		} else {
			// 获取字节数组长度
			int size = bcd.length() / 2;
			int remainder = bcd.length() % 2;

			// 存储BCD码字节
			byte[] bcdByte = new byte[size + remainder];

			// 转BCD码
			for (int i = 0; i < size; i++) {
				int low = Integer.parseInt(bcd.substring(2 * i, 2 * i + 1));
				int high = Integer.parseInt(bcd.substring(2 * i + 1, 2 * i + 2));
				bcdByte[i] = (byte) ((high << 4) | low);
			}

			// 如果存在余数，需要填F
			if (remainder > 0) {
				int low = Integer.parseInt(bcd.substring(bcd.length() - 1));
				bcdByte[bcdByte.length - 1] = (byte) ((0xf << 4) | low);
			}

			// 返回BCD码字节数组
			return bcdByte;
		}
	}
	/**
	 * 字节转10进制
	 */
	public static int bytes2Int(byte[] b) {
		String r = "";
		for (byte by : b) {
			r = r + (int)by;
		}
		return Integer.valueOf(r);
	}
	/**
	 * <解码> <BCD格式的字节数组解成数字字符串>
	 * 
	 * @param bcd
	 *            字节数组
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public static String bcd2str(byte[] bcd) {
		if (null == bcd || bcd.length == 0) {
			return "";
		} else {
			// 存储转码后的字符串
			StringBuilder sb = new StringBuilder();

			// 循环数组解码
			for (int i = 0; i < bcd.length; i++) {
				// 转换低字节
				int low = (bcd[i] & 0x0f);
				sb.append(low);

				// 转换高字节
				int high = ((bcd[i] & 0xf0) >> 4);

				// 如果高字节等于0xf说明是补的字节，直接抛掉
				if (high != 0xf) {
					sb.append(high);
				}
			}

			// 返回解码字符串
			return sb.toString();
		}
	}
}
