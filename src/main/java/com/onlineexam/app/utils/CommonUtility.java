package com.onlineexam.app.utils;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletContext;
import org.apache.commons.lang3.RandomStringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommonUtility {

	private static final int[][] D = { { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 }, { 1, 2, 3, 4, 0, 6, 7, 8, 9, 5 },
			{ 2, 3, 4, 0, 1, 7, 8, 9, 5, 6 }, { 3, 4, 0, 1, 2, 8, 9, 5, 6, 7 }, { 4, 0, 1, 2, 3, 9, 5, 6, 7, 8 },
			{ 5, 9, 8, 7, 6, 0, 4, 3, 2, 1 }, { 6, 5, 9, 8, 7, 1, 0, 4, 3, 2 }, { 7, 6, 5, 9, 8, 2, 1, 0, 4, 3 },
			{ 8, 7, 6, 5, 9, 3, 2, 1, 0, 4 }, { 9, 8, 7, 6, 5, 4, 3, 2, 1, 0 } };
	private static final int[] INV = { 0, 4, 3, 2, 1, 5, 6, 7, 8, 9 };
	private static final int[][] P = { { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 }, { 1, 5, 7, 6, 2, 8, 3, 0, 9, 4 },
			{ 5, 8, 0, 3, 7, 9, 6, 1, 4, 2 }, { 8, 9, 1, 6, 0, 4, 3, 5, 2, 7 }, { 9, 4, 5, 3, 1, 2, 6, 8, 7, 0 },
			{ 4, 2, 8, 6, 5, 7, 3, 9, 0, 1 }, { 2, 7, 9, 3, 8, 0, 6, 4, 1, 5 }, { 7, 0, 4, 6, 9, 1, 3, 2, 5, 8 } };

	private static final String DATE_FROMAT_TYPE_1 = "yyyy-MM-dd HH:mm:ss";
	private static final String SALT_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
	private static final String LOCALE_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

	private static final int minLength = 100;
	private static final int maxLength = 1000;

	private static final Logger LOGGER = LoggerFactory.getLogger(CommonUtility.class);

	/**
	 * It takes a numerical value as string and generates a checksum value based on
	 * Verhoeff Algorithm
	 * 
	 * @param number
	 * @return checksum
	 */

	private CommonUtility() {
		throw new IllegalStateException("CommonUtility Exception");
	}

	public static int createCheckSumDigit(String number) {
		int[] digits = reverseArray(createEachDigitsArray(number));
		digits = addElement(digits, 0);
		int i = 0;
		int checksum = 0;
		for (int j = (digits.length - 1); j > -1; j--) {
			int imod8 = i % 8;
			int p = P[imod8][digits[j]];
			checksum = D[checksum][p];
			i++;
		}
		checksum = INV[checksum];
		return checksum;
	}

	/**
	 * It takes any numeric value as string and return an array of its digits
	 * 
	 * @param number
	 * @return digitsArray
	 */
	public static int[] createEachDigitsArray(String number) {
		int numberLength = number.length();
		int[] digitsArray = new int[numberLength];
		Long currNumber = Long.parseLong(number);
		for (int i = 0; i < numberLength; i++) {
			digitsArray[i] = (int) (currNumber % 10);
			currNumber = (Long) (currNumber / 10);
		}
		return digitsArray;
	}

	/**
	 * It takes any numeric value and return an array of its digits
	 * 
	 * @param number
	 * @return digitsArray
	 */
	public static int[] createEachDigitsArray(int number) {
		int numberLength = (number + "").length();
		int[] digitsArray = new int[numberLength];
		int currNumber = number;
		for (int i = 0; i < numberLength; i++) {
			digitsArray[i] = (currNumber % 10);
			currNumber = (Integer) (currNumber / 10);
		}
		return digitsArray;
	}

	public static int genRandom() {
		Random r = new Random(System.currentTimeMillis());
		return 10000 + r.nextInt(20000);
	}

	/**
	 * Takes an integer array and returns an integer array with elements are in
	 * reverse order from the first one
	 * 
	 * @param array
	 * @return reversedArray
	 */
	public static int[] reverseArray(int[] array) {
		int[] reversedArray = new int[array.length];
		for (int i = 0; i < array.length; i++) {
			reversedArray[i] = array[array.length - 1 - i];
		}
		return reversedArray;
	}

	/**
	 * Takes an integer array and an integer number to add in the given array
	 * 
	 * @param array
	 * @param num
	 * @return array
	 */
	static int[] addElement(int[] array, int num) {
		array = Arrays.copyOf(array, array.length + 1);
		array[array.length - 1] = num;
		return array;
	}

	/**
	 * Takes the date in database format (yyyy-MM-dd) and convert it into dd/MM/yyyy
	 * format
	 * 
	 * @param date
	 * @return
	 */
	public static String getCurMySQLDateWithTime() {
		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat formatter = new SimpleDateFormat(DATE_FROMAT_TYPE_1);
		return formatter.format(date);
	}

	public static String convertDBDateToNormalDate(String date) {
		String[] dateArr = date.split("-");
		return (dateArr[2] + "-" + dateArr[1] + "-" + dateArr[0]);
	}

	/**
	 * Returns the current system date
	 * 
	 * @return currentDate
	 */
	public static Date getCurrentDate() {
		Calendar cal = Calendar.getInstance();
		return new Date(cal.getTime().getTime());
	}

	public static String getCurrentDateTime() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FROMAT_TYPE_1);
		LocalDateTime currentDateTime = LocalDateTime.now();
		return currentDateTime.format(formatter);
	}

	public static String getFutureDateTime() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FROMAT_TYPE_1);
		long days = LocalDateTime.now().getMonth().length(true);
		LocalDateTime currentDateTime = LocalDateTime.now().plusDays(days);
		return currentDateTime.format(formatter);
	}

	public static Date getFutureDate() {
		long days = LocalDateTime.now().getMonth().length(true);
		LocalDateTime currentDateTime = LocalDateTime.now().plusDays(days);

		Date date = Date.from(currentDateTime.atZone(ZoneId.systemDefault()).toInstant());
		return date;
	}

	/**
	 * Returns the current system time
	 * 
	 * @return currentTime
	 */
	public static Time getCurrentTime() {
		Calendar cal = Calendar.getInstance();
		return new Time(cal.getTime().getTime());
	}

	public static String getFtpPath(ServletContext servletContext) {
		try {
			String absolutePath = servletContext.getRealPath("/");
			absolutePath = absolutePath.substring(0, absolutePath.length() - 1);
			absolutePath = absolutePath.substring(0, absolutePath.lastIndexOf('/') + 1).concat("ftpwebapps/");
			return absolutePath;
		} catch (Exception e) {
			LOGGER.error("GET PATH ER: ", e);
			return null;
		}
	}

	public static int generateOtp() {
		Random random = null;
		try {
			random = SecureRandom.getInstanceStrong();
		} catch (NoSuchAlgorithmException nse) {
			LOGGER.error("NoSuchAlgorithmException : ", nse);
		}
		if (random != null)
			return (random.nextInt() * 900000) + 100000;
		else
			return (1 * 900000) + 100000;
	}

	public static List<Integer> getListOfStringArray(String values) {
		String[] spliteValue = values.replace("[", "").replace("]", "").split(",");
		List<Integer> valueCollection = new ArrayList<>();
		for (String value : spliteValue) {
			valueCollection.add(Integer.parseInt(value));
		}
		return valueCollection;
	}

	public static String generatedRandomPassword() {
		StringBuilder salt = null;
		salt = new StringBuilder(RandomStringUtils.random(8, 0, 35, true, true, SALT_STRING.toCharArray()));
		return salt.toString();
	}

	public static org.joda.time.LocalDateTime getDateTime(String dateTimeValue) {
		org.joda.time.format.DateTimeFormatter formatter = DateTimeFormat.forPattern("dd-MM-yyyy HH:mm");
		DateTime dateTime = DateTime.parse(dateTimeValue, formatter);
		return dateTime.toLocalDateTime();
	}

	public static String formatDateTime(LocalDateTime localDateTime) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(LOCALE_DATE_TIME_FORMAT);
		return localDateTime.format(formatter);
	}

	public static LocalDateTime formatToLocalDateTime(String localDateTime) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(LOCALE_DATE_TIME_FORMAT);
		return LocalDateTime.parse(localDateTime, formatter);
	}

	public static String generateRandomAlphaNumbericValue(String transcationId) {
		return RandomStringUtils.randomAlphanumeric(9).concat(transcationId);
	}

	public static String generateRandomNumbericValue(String transcationId, String userid, int length, int beginIndex,
			int endIndex) {
		String number = RandomStringUtils.randomNumeric(length);
		return userid.concat(transcationId).concat(number).substring(beginIndex, endIndex);
	}

	private static final Random RANDOM = new SecureRandom();
	public static final int PASSWORD_LENGTH = 4;

	public static String generateOTP() {
		Pattern digit = Pattern.compile("[0-9]");
		String lettersCombination = "123456789";
		String password = "";
		for (int i = 0; i < PASSWORD_LENGTH; i++) {
			int index = (int) (RANDOM.nextDouble() * lettersCombination.length());
			password += lettersCombination.substring(index, index + 1);
		}
		Matcher hasDigit = digit.matcher(password);
		if (hasDigit.find()) {
			return password;
		} else {
			return password = generateOTP();
		}
	}

	/*
	 * This method returns current timestamp
	 */

	/*
	 * public static int getTimeStamp() { int timeStamp = 0; Timestamp timestamp =
	 * new Timestamp(System.currentTimeMillis()); timeStamp = (int)
	 * timestamp.getTime(); return timeStamp; }
	 */

	/*
	 * This method returns current timestamp added by 5 minutes
	 */

	public static long getTimeStampPlus5Minutes() {
		long timeStampValue = 0;
		long nowPlus5Minutes = System.currentTimeMillis() + 5 * 60 * 1000;// TimeUnit.MINUTES.toMinutes(15);
		Timestamp timestamp = new Timestamp(nowPlus5Minutes);
		timeStampValue = timestamp.getTime();
		return timeStampValue;
	}

	/*
	 * Validation of time stamp from current timestamp
	 * 
	 */

	public static boolean timeStampValidation(long timeStamp) {
		boolean status = false;
		Timestamp currenttTimestamp = new Timestamp(System.currentTimeMillis());
		long l2 = currenttTimestamp.getTime();
		if (timeStamp > l2) {
			status = true;
		} else {
			status = false;
		}
		return status;
	}

	public static Timestamp getTimeStamp(int timeStamp) {
		Timestamp timestamp = new Timestamp(timeStamp);
		return timestamp;
	}

	/*
	 * This method is for validating mobile number for India only
	 */

	public static boolean isValidMobileNumber(String mobileNumber) {
		Pattern p = Pattern.compile("(0/91)?[7-9][0-9]{9}");
		Matcher m = p.matcher(mobileNumber);
		return (m.find() && m.group().equals(mobileNumber));
	}

	public static boolean isValidStringLength(String data) {
		boolean status = true;
		int length = data.length();
		if (length < minLength) {
			status = false;
		} else if (length > maxLength) {
			status = false;
		}
		return status;
	}
}