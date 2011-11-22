package com.ldl.code.mojo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;

import com.ldl.code.freemarker.FreeMarkerConfig;
import com.ldl.code.pdm.PdmParser;
import com.ldl.code.pdm.Table;
import com.ldl.code.util.StringUtils;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * @goal generate
 * @author nandi.ldl
 *
 */
public class GeneratorMojo extends AbstractMojo{
	
	/**
	 * @parameter expression="${templateDirectory}" default-value="${user.home}/codeTemplate"
	 * @required
	 */
	private File templateDirectory;
	
	/**
	 * @parameter expression="${pdmFile}" default-value="${user.home}/code.pdm"
	 * @required
	 */
	private File pdmFile;
	
	/**
	 * @parameter expression="${outputDirectory}" default-value="${basedir}"
	 * @required
	 */
	private File outputDirectory;
	
	/**
	 * @parameter expression="${module}" default-value=""
	 */
	private String module;
	
	private Configuration configuration;
	private List<Table> tableList;
	
	protected final String PLACEHOLDER_MODULE = "${module}";
	protected final String PLACEHOLDER_ITERATOR = "${iterator}";
	protected final String PLACEHOLDER_CAPTABLENAME = "${TableName}";
	protected final String PLACEHOLDER_UNCAPTABLENAME = "${tableName}";
	private final String ENCODING_GB18030 = "gb18030";
	
    public void execute()throws MojoExecutionException{
    	try {
    		if(!templateDirectory.exists()){
    			throw new MojoExecutionException("ERROR:templateDirectory--" + templateDirectory.getCanonicalPath() + "文件夹不存在。");
    		}else if(!templateDirectory.isDirectory()){
    			throw new MojoExecutionException("ERROR:templateDirectory--" + templateDirectory.getCanonicalPath() + "不是文件夹。");
    		}
    		if(!outputDirectory.exists()){
    			outputDirectory.mkdirs();
    		}else if(!outputDirectory.isDirectory()){
    			throw new MojoExecutionException("ERROR:outputDirectory--" + outputDirectory.getCanonicalPath() + "不是文件夹。");
    		}
    		
	    	if(!pdmFile.exists()){
	    		throw new MojoExecutionException("ERROR:pdmFile--" + pdmFile.getCanonicalPath() + "文件不存在。");
	    	}else if(!pdmFile.isFile()){
	    		throw new MojoExecutionException("ERROR:pdmFile--" + pdmFile.getCanonicalPath() + "不是文件。");
	    	}
	    	
    	} catch (IOException e) {
    		throw new MojoExecutionException("ERROR:资源文件夹不存在。");
    	}
		try {
			tableList = PdmParser.parse(pdmFile);
		} catch (Exception e) {
			throw new MojoExecutionException("解析pdm文件出错",e);
		}
		if(module == null){
			module = "";
		}
		configuration = FreeMarkerConfig.getInstance().getConfiguration(templateDirectory);
		generateCode();
    }
    /**
     * 生成代码的具体实现方法
     * @throws MojoExecutionException
     */
    public void generateCode() throws MojoExecutionException{
    	recursionFile(templateDirectory);
    }
	@SuppressWarnings("unchecked")
	private void recursionFile(File file) throws MojoExecutionException{
		if(!file.exists() || file.isHidden())return;
		String relativePath;
		File outputFile = null;
		try {
			relativePath = file.getCanonicalPath().substring(templateDirectory.getCanonicalPath().length());
			outputFile = new File(outputDirectory.getCanonicalPath() + relativePath.replace(File.separator + PLACEHOLDER_MODULE, !"".equals(module)?File.separator + module:module).replace(PLACEHOLDER_MODULE, module));
		} catch (IOException e) {
			 throw new MojoExecutionException("模板资源文件不存在", e);
		}
		if(file.isDirectory()){
			File []childFiles = file.listFiles(); 
			if(childFiles.length <= 0){
				if(!outputFile.exists()){
					outputFile.mkdirs();
				}
			}
			for (int i = 0; i < childFiles.length; i++) {
				recursionFile(childFiles[i]);
			}	
		}else{
			if(!outputFile.getParentFile().exists() && !outputFile.getParentFile().getName().startsWith(PLACEHOLDER_ITERATOR)){
				outputFile.getParentFile().mkdirs();
			}
			try {
				boolean isStartWithPlaceHolder = outputFile.getName().startsWith(PLACEHOLDER_ITERATOR);
				if(!outputFile.exists() && !isStartWithPlaceHolder){
					outputFile.createNewFile();
				}
				Map root = new HashMap();
				root.put("module", !"".equals(module)?"." + module:module);
				root.put("tableList", tableList);
				Template template = configuration.getTemplate(relativePath);
				if(isStartWithPlaceHolder){
					for (Table table : tableList) {
						root.put("table", table);
						Writer out = null;
						if("${iterator}list.ftl".equals(outputFile.getName())){
							File newOutFile = new File(outputFile.getParent().replace(PLACEHOLDER_UNCAPTABLENAME, StringUtils.uncapitalize(table.getTableName())).replace(PLACEHOLDER_ITERATOR, "") + "/list.ftl");
							if(!newOutFile.getParentFile().exists()){
								newOutFile.getParentFile().mkdirs();
							}
							out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(newOutFile),ENCODING_GB18030));
						}else{
							String outFileName = outputFile.getName().replace(PLACEHOLDER_CAPTABLENAME, table.getTableName())
							                                         .replace(PLACEHOLDER_UNCAPTABLENAME, StringUtils.uncapitalize(table.getTableName()))
							                                         .replace(PLACEHOLDER_ITERATOR, "");
							out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(outputFile.getParentFile(),outFileName)),ENCODING_GB18030));
						}
						template.process(root, out);
						out.flush();
						out.close();
					}
				}else{
					Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFile),ENCODING_GB18030));
					template.process(root, out);
					out.flush();
					out.close();
				}
			} catch (Exception e) {
				throw new MojoExecutionException("ERROR:根据模板生成代码出错",e);
			}			
		}
	}
}