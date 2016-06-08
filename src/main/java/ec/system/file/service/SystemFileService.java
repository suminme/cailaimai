package ec.system.file.service;

import java.io.File;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase.FileSizeLimitExceededException;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ec.system.file.exception.SystemFileSizeLimitException;
import ec.system.file.exception.SystemFileStorageException;
import ec.system.file.exception.SystemFileUploadException;
import ec.system.file.model.SystemFile;
import framework.data.date.DateUtil;
import framework.service.rds.RdsService;
import framework.service.storage.StorageService;

/**
 * @author SUMIN
 * @version 0.1
 * @since 2016-05-23
 */
@Service
@Transactional
public class SystemFileService {

	/**
	 * 文件上传
	 */
	public SystemFile fileUpload(String type, String typeId, String fileName, File file) {
		String key = type + "/" + typeId + "/" + DateUtil.getDateString() + "/";
		key = key + new Date().getTime() + "-" + fileName;
		try {
			this.getStorageService().storageFile(file, key);
		} catch (Exception e) {
			throw new SystemFileStorageException(e);
		}
		SystemFile fileModel = new SystemFile();
		fileModel.setType(typeId);
		fileModel.setTypeId(typeId);
		fileModel.setBucketKey(key);
		fileModel.setFileName(fileName);
		fileModel.setCreateTime(DateUtil.getDateTimeString());
		this.getRdsService().save(fileModel);
		return fileModel;
	}

	/**
	 * 从表单中获取上传的文件
	 */
	@SuppressWarnings("unchecked")
	public File getFileFromRequest(HttpServletRequest request, int maxMb) throws SystemFileSizeLimitException {
		String tempPath = System.getProperty("java.io.tmpdir", "tmp");
		File dir = new File(tempPath + "/upload/" + new Date().getTime() + "/");
		if (!dir.exists()) {
			dir.mkdirs();
		}
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setRepository(dir);
		ServletFileUpload upload = new ServletFileUpload(factory);
		if (maxMb > 0) {
			upload.setFileSizeMax(maxMb * 1024 * 1024);
		}
		List<FileItem> list = null;
		try {
			list = upload.parseRequest(request);
		} catch (FileSizeLimitExceededException e) {
			throw new SystemFileSizeLimitException(maxMb);
		} catch (FileUploadException e) {
			throw new SystemFileUploadException(e);
		}
		if (null == list || list.size() < 1) {
			return null;
		}
		File uploadFile = null;
		try {
			for (FileItem item : list) {
				if (item.isFormField()) {
					continue;
				}
				uploadFile = new File(dir.getPath() + item.getName());
				item.write(uploadFile);
				break;
			}
		} catch (Exception e) {
			throw new SystemFileUploadException(e);
		}
		return uploadFile;
	}

	/**
	 * 
	 */
	@Resource
	private RdsService rdsService;

	/**
	 * 
	 */
	@Resource
	private StorageService storageService;

	public RdsService getRdsService() {
		return rdsService;
	}

	public void setRdsService(RdsService rdsService) {
		this.rdsService = rdsService;
	}

	public StorageService getStorageService() {
		return storageService;
	}

	public void setStorageService(StorageService storageService) {
		this.storageService = storageService;
	}
}