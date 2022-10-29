package rs.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class FileUtil {

	public static final char DELETED = '*';
	public static final int SIZE_DATE = Integer.BYTES * 3;
	public static final int SIZE_DATE1 = Integer.BYTES * 3;

	public static String readString(RandomAccessFile file, int length) throws IOException {
		char[] s = new char[length];
		for (int i = 0; i < s.length; i++)
			s[i] = file.readChar();
		return new String(s).replace('\0', ' ').trim();
	}

	public static void writeString(RandomAccessFile file, String s, int length) throws IOException {
		StringBuffer buffer = null;
		if (s != null)
			buffer = new StringBuffer(s);
		else
			buffer = new StringBuffer(length);
		buffer.setLength(length);
		file.writeChars(buffer.toString());
	}

	public static LocalDate readDate(RandomAccessFile file) throws IOException {
		return LocalDate.of(file.readInt(),file.readInt(),file.readInt());
	}

	public static void writeDate(RandomAccessFile file, LocalDate date) throws IOException {

	    file.writeInt(date.getYear());
		file.writeInt(date.getMonthValue());
		file.writeInt(date.getDayOfMonth());
	}

    

	public static void copyFile(String nameSource, String nameDest) throws IOException {
		File source = new File (nameSource);
		File dest = new File (nameDest);
		InputStream is = null;
		OutputStream os = null;
		try {
			is = new FileInputStream(source);
			os = new FileOutputStream(dest);
			byte[] buffer = new byte[1024];
			int length;
			while ((length = is.read(buffer)) > 0) {
				os.write(buffer, 0, length);
			}
		} finally {
			is.close();
			os.close();
		}
	}
}
