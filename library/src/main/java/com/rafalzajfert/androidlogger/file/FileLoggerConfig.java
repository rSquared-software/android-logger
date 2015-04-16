package com.rafalzajfert.androidlogger.file;

import com.rafalzajfert.androidlogger.Level;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * 
 * @author Rafal Zajfert
 * @version 1.1.0 (15/04/2015)
 */
public class FileLoggerConfig {
	File logFile = null;
	boolean writeTimeEnabled = true;
	String timeFormat = "dd/MM/yyyy HH:mm:ss:SSS ";
	Boolean enabled;
	Level logLevel;
	String tag;
	SimpleDateFormat format = new SimpleDateFormat(timeFormat, Locale.getDefault());

	private FileLoggerConfig(File logFile, boolean writeTimeEnabled, String timeFormat, Boolean enabled,
	                        Level logLevel, String tag) {
		this.logFile = logFile;
		this.writeTimeEnabled = writeTimeEnabled;
		this.timeFormat = timeFormat;
		this.enabled = enabled;
		this.logLevel = logLevel;
		this.tag = tag;

		this.format = new SimpleDateFormat(timeFormat, Locale.getDefault());
	}

	/**
	 *
	 * @return true if enabled or is not set
	 */
	public boolean isEnabled() {
		return enabled == null ? true : enabled;
	}

	public Level getLogLevel() {
		return logLevel;
	}

	public String getTag() {
		return tag;
	}

	/**
	 * @param level
	 * @return true if is allowed or level is not set
	 */
	public boolean isLevelAllowed(Level level) {
		return logLevel == null || level.ordinal() >= logLevel.ordinal();
	}

	public File getLogFile() {
		return logFile;
	}

	/** Check that writing current time is enabled or disabled  */
	public boolean isWriteTimeEnabled() {
		return writeTimeEnabled;
	}

	/** Get pattern used to format current time in logs */
	public String getTimeFormat() {
		return timeFormat;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public static class Builder{
		File logFile = null;
		boolean writeTimeEnabled = true;
		String timeFormat = "dd/MM/yyyy HH:mm:ss:SSS ";
		Boolean enabled;
		Level logLevel;
		String tag;

		public FileLoggerConfig build(){
			return new FileLoggerConfig(logFile, writeTimeEnabled, timeFormat, enabled, logLevel, tag);
		}

		/**
		 * Tag, used to identify source of a log message,<br>
		 * default is class name with line number<br>
		 * <br>
		 * You can also use auto generated values:<br>
		 * {@link com.rafalzajfert.androidlogger.Logger#PARAM_SIMPLE_CLASS_NAME PARAM_SIMPLE_CLASS_NAME}<br>
		 * {@link com.rafalzajfert.androidlogger.Logger#PARAM_CLASS_NAME PARAM_CLASS_NAME}<br>
		 * {@link com.rafalzajfert.androidlogger.Logger#PARAM_METHOD_NAME PARAM_METHOD_NAME}<br>
		 * {@link com.rafalzajfert.androidlogger.Logger#PARAM_FILE_NAME PARAM_FILE_NAME}<br>
		 * {@link com.rafalzajfert.androidlogger.Logger#PARAM_LINE_NUMBER PARAM_LINE_NUMBER}
		 */
		public Builder tag(String tag) {
			this.tag = tag;
			return this;
		}

		/** Set logger enabled or disabled<br>
		 * If the logger is disabled messages will not be logged */
		public Builder enabled(boolean enabled) {
			this.enabled = enabled;
			return this;
		}

		/**
		 * Minimal {@link com.rafalzajfert.androidlogger.Level Level} of message to sent
		 */
		public Builder logLevel(Level level) {
			this.logLevel = level;
			return this;
		}

		/**
		 * Set log file to write messages<br>
		 * <br>
		 * <b>Note:</b> if you want save log file on external storage you have to
		 * add to your Manifest permission:<br>
		 * <code>&lt;uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/&gt;</code>
		 */
		public Builder setLogFile(File logFile) {
			this.logFile = logFile;
			return this;
		}

		/**
		 * Enable or disable writing current time with log message
		 *
		 * @see {@link #setTimeFormat(String)}
		 */
		public Builder setWriteTimeEnabled(boolean enabled) {
			this.writeTimeEnabled = enabled;
			return this;
		}

		/**
		 * Set pattern to format current time in logs
		 *
		 * @see {@link java.text.SimpleDateFormat}
		 */
		public Builder setTimeFormat(String format) {
			this.timeFormat = format;
			return this;
		}

	}
}