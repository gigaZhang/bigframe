package com.ldl.code.mojo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
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
	 * @parameter expression="${outputDirectory}" default-value="${project.build.directory}"
	 * @required
	 */
	private File outputDirectory;
	
	/**
	 * @parameter expression="${templateDirectory}" default-value="${project.build.directory}"
	 * @required
	 */
	private File templateDirectory;
	
	/**
	 * @parameter expression="${pdmFile}" default-value="${project.build.directory}"
	 * @required
	 */
	private File pdmFile;
	
	/**
	 * @parameter expression="${module}" default-value=""
	 */
	private String module;
	
	private Configuration configuration;
	private List<Table> tableList;
	
	protected final String PLACEHOLDER_MODULE = "${module}";
	protected final String PLACEHOLDER_NEEDLIST = "${needList_";
	protected final String PLACEHOLDER_NEEDLIST_PREFIX = "${needList_prefix}";
	protected final String PLACEHOLDER_NEEDLIST_TABLENAME1 = "${needList_tableName}";
	protected final String PLACEHOLDER_NEEDLIST_TABLENAME2 = "${needList_TableName}";
	private final String ENCODING_GB18030 = "gb18030";
	private final String PARAMS_TEMPLATE = "template";
	private final String PARAMS_PDM = "pdm";
	private final String PARAMS_OUTPUT = "output";
	
    public void execute()throws MojoExecutionException{
    	templateDirectory = validateDirectory(templateDirectory,PARAMS_TEMPLATE);
    	outputDirectory = validateDirectory(outputDirectory,PARAMS_OUTPUT);
    	pdmFile = validateDirectory(pdmFile,PARAMS_PDM);
    	getLog().info("templateDirectory:" + templateDirectory.getPath());
    	getLog().info("outputDirectory:" + outputDirectory.getPath());
		getLog().info("pdmFile:" + pdmFile.getPath());
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
    
    /**
     *检验参数outputDirectory、、templateDirectory、pdmFile
     * @param file
     * @param config
     * @return
     * @throws MojoExecutionException 
     */
    private File validateDirectory(File directory,String config) throws MojoExecutionException{
    	if("target".equals(directory.getName())){
    		if(PARAMS_PDM.equals(config)){
    			directory = new File(directory.getParent() + File.separator + "code.pdm");
    		}else{
    			directory = new File(directory.getParent() + File.separator + config);
    		}
    	}
    	if(!directory.exists()){
    		if(PARAMS_TEMPLATE.equals(config)){
    			throw new MojoExecutionException("ERROR:templateDirectory文件夹不存在。");
    		}else if(PARAMS_PDM.equals(config)){
    			throw new MojoExecutionException("ERROR:pdmFile文件不存在。");
    		}else if(PARAMS_OUTPUT.equals(config)){
    			directory.mkdirs();
    		}
    	}
    	
    	if(directory.isFile()){
    		if(PARAMS_TEMPLATE.equals(config)){
    			throw new MojoExecutionException("ERROR:templateDirectory文件夹不存在。");
    		}else if(PARAMS_OUTPUT.equals(config)){
    			throw new MojoExecutionException("ERROR:outputDirectory文件夹不存在。");
    		}   		
    	}
    	
    	return directory;
    }
	@SuppressWarnings("unchecked")
	private void recursionFile(File file) throws MojoExecutionException{
		if(!file.exists() || file.isHidden())return;
		String relativePath = file.getPath().substring(templateDirectory.getPath().length());
		File outputFile = new File(outputDirectory.getPath() + relativePath.replace(File.separator + PLACEHOLDER_MODULE, !"".equals(module)?File.separator + module:module).replace(PLACEHOLDER_MODULE, module));
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
			if(!outputFile.getParentFile().exists() && !outputFile.getParentFile().getName().startsWith(PLACEHOLDER_NEEDLIST)){
				outputFile.getParentFile().mkdirs();
			}
			try {
				boolean isStartWithPlaceHolder = outputFile.getName().startsWith(PLACEHOLDER_NEEDLIST);
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
						if("${needList_prefix}list.ftl".equals(outputFile.getName())){
							File newOutFile = new File(outputFile.getParent().replace(PLACEHOLDER_NEEDLIST_TABLENAME1, StringUtils.uncapitalize(table.getTableName())).replace(PLACEHOLDER_NEEDLIST_PREFIX, "") + "/list.ftl");
							if(!newOutFile.getParentFile().exists()){
								newOutFile.getParentFile().mkdirs();
							}
							out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(newOutFile),ENCODING_GB18030));
						}else{
							String outFileName = outputFile.getName().replace(PLACEHOLDER_NEEDLIST_TABLENAME2, table.getTableName())
							                                         .replace(PLACEHOLDER_NEEDLIST_TABLENAME1, StringUtils.uncapitalize(table.getTableName()))
							                                         .replace(PLACEHOLDER_NEEDLIST_PREFIX, "");
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
