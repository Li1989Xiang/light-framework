package up.light.utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Layout;
import org.apache.log4j.spi.ErrorCode;

import up.light.Setting;

public class IndependentFileAppender extends FileAppender {
	private static final String DEFAULT_FILENAME_PATTERN = "'log.'yyyy.MM.dd-HH.mm.ss'.txt'";
	
	private String mFolder;
	private String mNamePattern;

	public IndependentFileAppender() {
	}

	/*
	 * folder should end with "/"
	 */
	public IndependentFileAppender(Layout layout, String folder, String namePattern) throws IOException {
		super(layout, folder + DateUtil.getDateString(namePattern));
	}

	public String getFolder() {
		return mFolder;
	}

	public void setFolder(String folder) {
		mFolder = folder;
	}

	public String getNamePattern() {
		return mNamePattern;
	}

	public void setNamePattern(String namePattern) {
		mNamePattern = namePattern;
	}

	@Override
	public void activateOptions() {
		handleField();
		String f = mFolder + DateUtil.getDateString(mNamePattern);
		
		try {
			setFile(f, fileAppend, bufferedIO, bufferSize);
		} catch (java.io.IOException e) {
			errorHandler.error("setFile(" + fileName + "," + fileAppend + ") call failed.", e,
					ErrorCode.FILE_OPEN_FAILURE);
		}
	}

	private void handleField() {
		if (StringUtils.isBlank(mFolder)) {
			mFolder = Setting.getLogPath();
		} else {
			mFolder = mFolder.replace("/", File.separator);

			if (!mFolder.endsWith(File.separator)) {
				mFolder = mFolder + File.separator;
			}
		}
		
		if(StringUtils.isBlank(mNamePattern)) {
			mNamePattern = DEFAULT_FILENAME_PATTERN;
		}
	}
}
