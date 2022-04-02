package com.onlineexam.app.utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

public class FileUtility {

	private static final Logger LOGGER = LoggerFactory.getLogger(FileUtility.class);

	public File writeFiles(MultipartFile file, File dir, long partnerMobileId, long filetypeId) {
		BufferedOutputStream outputStream = null;
		File uploadFile = null;
		try {
			byte[] bytes = file.getBytes();
			if (bytes == null) {
				LOGGER.info("Bytes is null ##################################################");
			}
			LOGGER.info("##################################################");
			if (!dir.exists())
				dir.mkdirs();
			String fileName[] = file.getOriginalFilename().split("\\.");
			System.out.println(fileName[0] + ":::::::" + fileName[1]);
			uploadFile = new File(dir.getAbsolutePath() + File.separator + fileName[0].concat("_" + partnerMobileId)
					.concat("" + filetypeId).concat(".").concat(fileName[1]));
			if (uploadFile.exists()) {
				uploadFile.delete();
			}
			outputStream = new BufferedOutputStream(new FileOutputStream(uploadFile));
			outputStream.write(bytes);
		} catch (IOException ioe) {
			LOGGER.error("IOException while uploading file in server: " + ioe);
		} finally {
			try {
				if (outputStream != null)
					outputStream.close();
			} catch (IOException ioee) {
				LOGGER.error("IOException while closing output stream: " + ioee);
			}
		}
		return uploadFile;
	}
}
