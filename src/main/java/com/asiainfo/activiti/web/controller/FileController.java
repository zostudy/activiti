package com.asiainfo.activiti.web.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.zip.ZipInputStream;

import javax.imageio.ImageIO;

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.EndEvent;
import org.activiti.bpmn.model.SequenceFlow;
import org.activiti.bpmn.model.StartEvent;
import org.activiti.bpmn.model.UserTask;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.repository.ProcessDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ClassUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/file")
public class FileController {

	
	/*@Autowired
	private ProcessEngineConfiguration processEngineConfiguration;*/
	
	@Autowired
	private ProcessEngine processEngine;
	
	@Autowired
	private RepositoryService repositoryService;
	
	/*@Autowired
	private TaskService taskService;*/
	
	@GetMapping("/zip")
	public String hello() {
		log.info(processEngine.getName());
		DeploymentBuilder deploymentBuilder = repositoryService.createDeployment();
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(ResourceUtils.getFile("classpath:test.zip"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		ZipInputStream zis = new ZipInputStream(fis);
		deploymentBuilder.addZipInputStream(zis);
		deploymentBuilder.deploy();
		return "Welcome to reactive world ~";
	}
	
	@GetMapping("/bpm")
	public String bpm() {
		log.info(processEngine.getName());
		DeploymentBuilder deploymentBuilder = repositoryService.createDeployment();
		
		deploymentBuilder.addBpmnModel("这是资源名称", createProcessModel()).name("流程模型定义");
		Deployment deployment = deploymentBuilder.deploy();
		// 依据部署主键和文件名称从 ACT_FK_BYTEARR 表中查出
		InputStream is = repositoryService.getResourceAsStream(deployment.getId(), "这是资源名称");
		try {
			int count = is.available();
			byte[] contens = new byte[count];
			is.read(contens);
			log.info(new String(contens));
		} catch (IOException e) {
			e.printStackTrace();
		}
		deploymentBuilder = repositoryService.createDeployment();
		deploymentBuilder.addClasspathResource("process/ReqProcess.bpmn");
		deployment = deploymentBuilder.deploy();
		// 查询流程模型文件 act_re_procdef
		ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().deploymentId(deployment.getId()).singleResult();
		is = repositoryService.getProcessModel(processDefinition.getId());
		try {
			int count = is.available();
			byte[] contens = new byte[count];
			is.read(contens);
			log.info(new String(contens));
		} catch (IOException e) {
			e.printStackTrace();
		}
		deploymentBuilder = repositoryService.createDeployment();
		deploymentBuilder.addClasspathResource("process/ReqProcess.bpmn").addClasspathResource("process/ReqProcess.png");
		deployment = deploymentBuilder.deploy();
		// 查询流程模型图片 act_re_procdef
		processDefinition = repositoryService.createProcessDefinitionQuery().deploymentId(deployment.getId()).singleResult();
		is = repositoryService.getProcessDiagram(processDefinition.getId());
		try {
			BufferedImage image = ImageIO.read(is);
			ClassLoader cl = ClassUtils.getDefaultClassLoader();
			URL url = (cl != null ? cl.getResource("image") : ClassLoader.getSystemResource("image"));
			File file = new File(url.getPath()+File.separatorChar+"ReqProcess.png");
			file = new File("src/main/resources/image/ReqProcess.png");
			log.info(file.getAbsolutePath());
			if (!file.exists()) file.createNewFile();
			FileOutputStream fos = new FileOutputStream(file);
			ImageIO.write(image, "png", fos);
			fos.close();
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "Welcome to reactive world ~";
		
	}
	
	private static BpmnModel createProcessModel() {
		// 创建 BPMN 模型对象
		BpmnModel bpmnModel = new BpmnModel();
		// 新建流程对象
		org.activiti.bpmn.model.Process process = new org.activiti.bpmn.model.Process();
		bpmnModel.addProcess(process);
		process.setId("FirstProcess");
		process.setName("第一个流程模型定义");
		// 开始事件
		StartEvent startEvent = new StartEvent();
		startEvent.setId("startEvent");
		startEvent.setName("启动事件");
		process.addFlowElement(startEvent);
		// 人工任务
		UserTask userTask = new UserTask();
		userTask.setId("userTask");
		userTask.setName("人工任务");
		process.addFlowElement(userTask);
		// 结束事件
		EndEvent endEvent = new EndEvent();
		endEvent.setId("endEvent");
		endEvent.setName("结束任务");
		process.addFlowElement(endEvent);
		// 设定流程顺序
		process.addFlowElement(new SequenceFlow("startEvent", "userTask"));
		process.addFlowElement(new SequenceFlow("userTask", "endEvent"));
		return bpmnModel;
	}
	
	@GetMapping("/resources")
	public String resources() {
		log.info(processEngine.getName());
		DeploymentBuilder deploymentBuilder = repositoryService.createDeployment();
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(ResourceUtils.getFile("classpath:test.zip"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		ZipInputStream zis = new ZipInputStream(fis);
		deploymentBuilder.addZipInputStream(zis).name("zipFile");
		Deployment deployment = deploymentBuilder.deploy();
		// 依据部署主键和文件名称从 ACT_FK_BYTEARR 表中查出
		InputStream is = repositoryService.getResourceAsStream(deployment.getId(), "test_2.txt");
		try {
			int count = is.available();
			byte[] contens = new byte[count];
			is.read(contens);
			log.info(new String(contens));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "Welcome to reactive world ~";
	}
	
	@GetMapping("/delete")
	public String delete() {
		// 删除流程部署, 级联删除会删除所有的数据, 非级联删除只有在流程没有流程实例的时候可以使用, 如果有流程实例不进行级联删除就会导致删除失败
		log.info(processEngine.getName());
		DeploymentBuilder deploymentBuilder = repositoryService.createDeployment();
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(ResourceUtils.getFile("classpath:test.zip"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		ZipInputStream zis = new ZipInputStream(fis);
		deploymentBuilder.addZipInputStream(zis).name("zipFile");
		Deployment deployment = deploymentBuilder.deploy();
		// 依据部署主键和文件名称从 ACT_FK_BYTEARR 表中查出
		InputStream is = repositoryService.getResourceAsStream(deployment.getId(), "test_2.txt");
		try {
			int count = is.available();
			byte[] contens = new byte[count];
			is.read(contens);
			log.info(new String(contens));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "Welcome to reactive world ~";
	}
	
}
